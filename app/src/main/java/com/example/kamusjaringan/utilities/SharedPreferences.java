package com.example.kamusjaringan.utilities;

import android.content.Context;

public class SharedPreferences {
    // global variables declaration
    private final static String PREF_NAME = "shared_pref";
    private final static String KEY_IS_LOADED = "is_loaded";
    private Context context;
    private android.content.SharedPreferences sharedPreferences;
    private android.content.SharedPreferences.Editor editor;

    // session for saving data to local device
    public SharedPreferences(Context context) {
        this.context = context;
        sharedPreferences = (android.content.SharedPreferences) context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // get status data
    public boolean getIsLoaded() {
        return sharedPreferences.getBoolean(KEY_IS_LOADED, false);
    }

    // set status data
    public void setIsLoaded(boolean isLoaded) {
        editor.putBoolean(KEY_IS_LOADED, isLoaded);
        editor.commit();
    }
}
