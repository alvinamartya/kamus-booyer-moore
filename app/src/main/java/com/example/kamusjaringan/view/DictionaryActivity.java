package com.example.kamusjaringan.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.kamusjaringan.R;
import com.example.kamusjaringan.presenter.dictionary.DictionaryPresenter;
import com.example.kamusjaringan.presenter.dictionary.IDictionary;
import com.example.kamusjaringan.room.Database;

public class DictionaryActivity extends AppCompatActivity implements IDictionary.IView {

    // global variables and components declaration
    private Database database;
    private DictionaryPresenter presenter;
    private EditText edtWord;
    private Button btnSearch, btnBack;
    private ListView lvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        // binding component
        edtWord = findViewById(R.id.edt_word_dictionary);
        btnSearch = findViewById(R.id.btn_search_dictionary);
        btnBack = findViewById(R.id.btn_back_dictionary);
        lvData = findViewById(R.id.lv_data_dictionary);

        // declare database
        database = Room.databaseBuilder(getApplicationContext(),
                Database.class, "dictionary").allowMainThreadQueries().build();

        // declare presenter
        presenter = new DictionaryPresenter(this, this);

        // events
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.processSearchData();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.backToHome();
            }
        });
    }

    @Override
    public void searchData() {
        presenter.searchData(edtWord.getText().toString(), database, lvData);
    }
}
