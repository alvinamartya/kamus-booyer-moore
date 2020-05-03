package com.example.kamusjaringan.presenter.splashscreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kamusjaringan.R;
import com.example.kamusjaringan.room.Database;
import com.example.kamusjaringan.room.Dictionary;
import com.example.kamusjaringan.utilities.Helper;
import com.example.kamusjaringan.utilities.SharedPreferences;
import com.example.kamusjaringan.view.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class SplashScreenPresenter implements ISplashScreen.IPresenter {
    // declare variable
    private final static String TAG = SplashScreenPresenter.class.getSimpleName();
    private ISplashScreen.IView view;
    private Context context;
    private Helper helper;
    private SharedPreferences sharedPreferences;

    // constructor
    public SplashScreenPresenter(ISplashScreen.IView view, Context context) {
        this.view = view;
        this.context = context;
        helper = new Helper();
        sharedPreferences = new SharedPreferences(context);
    }

    // load data
    @Override
    public void loadData(final ProgressBar progressBar, final Database database) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (sharedPreferences.getIsLoaded()) {
                        for (int i = 0; i < 100; i += 10) {
                            // set progress tio progressbar
                            progressBar.setProgress(i);
                            Thread.sleep(500);
                        }
                    } else {
                        JSONArray list = new JSONArray(helper.readJSON(context.getResources().openRawResource(R.raw.data)));
                        for (int i = 0; i < list.length(); i++) {
                            // set progress to progressbar
                            double progress = ((double) i / (double) list.length()) * (double) 100;
                            progressBar.setProgress((int) progress);
                            Thread.sleep(500);

                            // insert data to database
                            JSONObject obj = list.getJSONObject(i);
                            database.dictionaryDao().insert(new Dictionary(
                                    obj.getString("word"),
                                    obj.getString("meaning")
                            ));
                        }

                        sharedPreferences.setIsLoaded(true);
                    }

                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                } catch (Exception e) {
                    Log.e(TAG, "loadDataError: " + e.getMessage());
                }
            }
        }).start();
    }

    @Override
    public void startLoad() {
        view.loadData();
    }
}
