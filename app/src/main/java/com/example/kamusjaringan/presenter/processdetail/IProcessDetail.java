package com.example.kamusjaringan.presenter.processdetail;

import android.app.ProgressDialog;
import android.widget.EditText;

import com.example.kamusjaringan.room.Database;
import com.example.kamusjaringan.room.Dictionary;

public interface IProcessDetail {
    interface IView {
        void loadDataUpdate();

        void processData();
    }

    interface IPresenter {
        void loadDataUpdate(EditText edtWord, EditText edtDescription, Dictionary dictionary);

        void processLoadDataUpdate();

        void addData(Database database, String word, String description, ProgressDialog progressDialog);

        void updateData(Database database, Dictionary dictionary, ProgressDialog progressDialog);

        void processData();
    }
}
