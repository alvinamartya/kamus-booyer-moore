package com.example.kamusjaringan.presenter.process;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kamusjaringan.room.Database;

public interface IProcess {
    interface IPresenter {
        void loadData(RecyclerView recyclerView, Database database);

        void processLoadData();

        void backToHome();
    }

    interface IView {
        void showData();
    }
}
