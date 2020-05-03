package com.example.kamusjaringan.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.kamusjaringan.R;
import com.example.kamusjaringan.presenter.splashscreen.ISplashScreen;
import com.example.kamusjaringan.presenter.splashscreen.SplashScreenPresenter;
import com.example.kamusjaringan.room.Database;

public class SplashScreenActivity extends AppCompatActivity implements ISplashScreen.IView {

    // global variables declaration
    private Database database;
    private ISplashScreen.IPresenter presenter;
    ProgressBar progressBar;


    // lifecycle onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // binding component
        progressBar = findViewById(R.id.progressBar);

        // declare database
        database = Room.databaseBuilder(getApplicationContext(),
                Database.class, "dictionary").allowMainThreadQueries().build();

        // declare presenter
        presenter = new SplashScreenPresenter(this, this);
        presenter.startLoad();
    }

    // view events
    @Override
    public void loadData() {
        presenter.loadData(progressBar, database);
    }
}
