package com.example.kamusjaringan.presenter.processdetail;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kamusjaringan.room.Database;
import com.example.kamusjaringan.room.Dictionary;

public class ProcessDetailPresenter implements IProcessDetail.IPresenter {
    // declaration variable
    private Context context;
    private IProcessDetail.IView view;

    // contractor
    public ProcessDetailPresenter(Context context, IProcessDetail.IView view) {
        this.context = context;
        this.view = view;
    }

    // load data
    @Override
    public void loadDataUpdate(EditText edtWord, EditText edtDescription, Dictionary dictionary) {
        edtWord.setText(dictionary.getWord());
        edtDescription.setText(dictionary.getMeaning());
    }

    // process to load data
    @Override
    public void processLoadDataUpdate() {
        view.loadDataUpdate();
    }

    // add data to database
    @Override
    public void addData(Database database, String word, String description, ProgressDialog progressDialog) {
        database.dictionaryDao().insert(new Dictionary(word, description));
        progressDialog.dismiss();

        Toast.makeText(context, "Tambah data berhasil", Toast.LENGTH_SHORT).show();
        ((Activity)context).finish();
    }

    // update data to database
    @Override
    public void updateData(Database database, Dictionary dictionary, ProgressDialog progressDialog) {
        database.dictionaryDao().update(dictionary);
        progressDialog.dismiss();

        Toast.makeText(context, "Ubah data berhasil", Toast.LENGTH_SHORT).show();
        ((Activity)context).finish();
    }

    // run to process data
    @Override
    public void processData() {
        view.processData();
    }
}
