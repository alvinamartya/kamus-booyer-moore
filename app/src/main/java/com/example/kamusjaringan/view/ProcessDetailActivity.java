package com.example.kamusjaringan.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.kamusjaringan.R;
import com.example.kamusjaringan.presenter.processdetail.IProcessDetail;
import com.example.kamusjaringan.presenter.processdetail.ProcessDetailPresenter;
import com.example.kamusjaringan.room.Database;
import com.example.kamusjaringan.room.Dictionary;

public class ProcessDetailActivity extends AppCompatActivity implements IProcessDetail.IView {

    // global variables and components declaration
    private Database database;
    private EditText edtWord, edtDescription;
    private Button btnProcess, btnBack;
    private ProcessDetailPresenter presenter;
    private Dictionary dictionary;

    // onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_detail);

        // declare presenter
        presenter = new ProcessDetailPresenter(this, this);

        // declare components
        edtWord = findViewById(R.id.edt_word_process_detail);
        edtDescription = findViewById(R.id.edt_detail_process_detail);
        btnBack = findViewById(R.id.btn_back_process_detail);
        btnProcess = findViewById(R.id.btn_process_detail);

        // declare database
        database = Room.databaseBuilder(getApplicationContext(),
                Database.class, "dictionary").allowMainThreadQueries().build();

        // get model and then set status this activity
        if (getIntent().getParcelableExtra("data") != null) {
            dictionary = getIntent().getParcelableExtra("data");
            btnProcess.setText(getResources().getString(R.string.ubah_kata));
        } else {
            btnProcess.setText(getResources().getString(R.string.tambah_kata));
        }


        // events
        if (dictionary != null)
            presenter.processLoadDataUpdate();

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.processData();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // view events
    @Override
    public void loadDataUpdate() {
        presenter.loadDataUpdate(edtWord, edtDescription, dictionary);
    }

    @Override
    public void processData() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu sebentar");

        if (dictionary != null) {
            progressDialog.setTitle("Ubah data");
            dictionary.setMeaning(edtDescription.getText().toString());
            dictionary.setWord(edtWord.getText().toString());
            presenter.updateData(database, dictionary, progressDialog);
        } else {
            progressDialog.setTitle("Tambah data");
            presenter.addData(database, edtWord.getText().toString(), edtDescription.getText().toString(), progressDialog);
        }
    }
}
