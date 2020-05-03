package com.example.kamusjaringan.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kamusjaringan.R;
import com.example.kamusjaringan.presenter.process.IProcess;
import com.example.kamusjaringan.presenter.process.ProcessPresenter;
import com.example.kamusjaringan.presenter.processdetail.ProcessDetailPresenter;
import com.example.kamusjaringan.room.Database;

public class ProcessDictionaryActivity extends AppCompatActivity implements IProcess.IView {

    // global variables and components declaration
    private ProcessPresenter presenter;
    private RecyclerView recyclerView;
    private Button btnAdd, btnBack;
    private Database database;

    // onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_dictionary);

        // binding component
        recyclerView = findViewById(R.id.rv_data_process);
        btnAdd = findViewById(R.id.btn_add_word_process);
        btnBack = findViewById(R.id.btn_back_process);

        // declare presenter
        presenter = new ProcessPresenter(this, this);

        // declare database
        database = Room.databaseBuilder(getApplicationContext(),
                Database.class, "dictionary").allowMainThreadQueries().build();

        // events
        presenter.processLoadData();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.backToHome();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProcessDictionaryActivity.this, ProcessDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    // onResume
    @Override
    protected void onResume() {
        super.onResume();
        presenter.processLoadData();
    }

    // view events
    @Override
    public void showData() {
        presenter.loadData(recyclerView, database);
    }
}
