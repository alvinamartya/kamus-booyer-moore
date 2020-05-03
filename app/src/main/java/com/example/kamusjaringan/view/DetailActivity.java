package com.example.kamusjaringan.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.kamusjaringan.R;
import com.example.kamusjaringan.room.Dictionary;

public class DetailActivity extends AppCompatActivity {

    // global component declaration
    private TextView tvTitle, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // binding component
        tvTitle = findViewById(R.id.tv_title_detail);
        tvDescription = findViewById(R.id.tv_description_detail);

        // set data to component
        Dictionary dictionary = getIntent().getParcelableExtra("data");
        tvTitle.setText(dictionary.getWord());
        tvDescription.setText(dictionary.getMeaning());
    }
}
