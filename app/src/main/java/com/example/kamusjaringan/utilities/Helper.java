package com.example.kamusjaringan.utilities;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Helper {
    // read json data
    public String readJSON(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = reader.readLine();

        while (line != null) {
            stringBuilder.append(line);
            line = reader.readLine();
        }

        return stringBuilder.toString();
    }
}
