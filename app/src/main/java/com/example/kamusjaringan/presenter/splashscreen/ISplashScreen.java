package com.example.kamusjaringan.presenter.splashscreen;

import android.widget.ProgressBar;

import com.example.kamusjaringan.room.Database;

public interface ISplashScreen {
    interface IView {
        void loadData();
    }

    interface IPresenter {
        void loadData(ProgressBar progressBar, Database database);
        void startLoad();
    }
}
