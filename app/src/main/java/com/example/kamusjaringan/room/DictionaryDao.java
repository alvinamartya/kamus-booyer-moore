package com.example.kamusjaringan.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DictionaryDao {
    @Query("SELECT * FROM Dictionary ORDER BY word ASC")
    List<Dictionary> getAllData();

    @Query("SELECT * FROM Dictionary WHERE id != :id")
    List<Dictionary> getOtherData(int id);

    @Insert
    void insert(Dictionary dictionary);

    @Delete
    void delete(Dictionary dictionary);

    @Update
    void update(Dictionary dictionary);
}
