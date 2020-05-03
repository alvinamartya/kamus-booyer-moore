package com.example.kamusjaringan.presenter.dictionary;

import android.widget.ListView;


import com.example.kamusjaringan.room.Database;
import com.example.kamusjaringan.room.Dictionary;

import java.util.List;

public interface IDictionary {
    interface IView {
        void searchData();
    }

    interface IPresenter {
        void searchData(String word, Database database, ListView listView);
        void processSearchData();
        void backToHome();
        Dictionary getData(List<Dictionary> dictionaries, String word);
        List<Dictionary> getErrorData(List<Dictionary> dictionaries, String word);
        int getIndexData(List<Dictionary> dictionaries, String word);
        List<Dictionary> getDictionaries(List<Dictionary> dictionaries, String word);
    }
}
