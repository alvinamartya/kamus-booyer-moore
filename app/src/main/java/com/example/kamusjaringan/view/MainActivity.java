package com.example.kamusjaringan.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.kamusjaringan.R;
import com.example.kamusjaringan.algorithm.AlgorithmValueModel;
import com.example.kamusjaringan.algorithm.BoyerMooreAlgorithm;
import com.example.kamusjaringan.room.Database;
import com.example.kamusjaringan.room.Dictionary;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // binding components
        Button btnDictionary = findViewById(R.id.btn_dictionary_main);
        Button btnAdd = findViewById(R.id.btn_add_data_main);
        Button btnAbout = findViewById(R.id.btn_about_main);
        Button btnExit = findViewById(R.id.btn_exit_main);

        // events
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
        btnDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DictionaryActivity.class);
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProcessDictionaryActivity.class);
                startActivity(intent);
            }
        });
    }
}
