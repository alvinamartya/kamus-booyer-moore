package com.example.kamusjaringan.room;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Dictionary.class}, version = 5)
public abstract class Database extends RoomDatabase {
    public abstract DictionaryDao dictionaryDao();
}
