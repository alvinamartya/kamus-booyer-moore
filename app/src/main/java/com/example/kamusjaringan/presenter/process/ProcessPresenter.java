package com.example.kamusjaringan.presenter.process;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kamusjaringan.adapter.WordAdapter;
import com.example.kamusjaringan.room.Database;
import com.example.kamusjaringan.room.Dictionary;

import java.util.List;

public class ProcessPresenter implements IProcess.IPresenter {
    // global variables declaration
    private IProcess.IView view;
    private Context context;

    // contractor
    public ProcessPresenter(IProcess.IView view, Context context) {
        this.view = view;
        this.context = context;
    }

    // load data and then show to list
    @Override
    public void loadData(RecyclerView recyclerView, Database database) {
        List<Dictionary> dictionaries = database.dictionaryDao().getAllData();
        WordAdapter adapter = new WordAdapter(context, dictionaries);
        recyclerView.setAdapter(adapter);
    }

    // process to load data
    @Override
    public void processLoadData() {
        view.showData();
    }

    // back to home
    @Override
    public void backToHome() {
        ((Activity)context).finish();
    }
}
