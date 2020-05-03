package com.example.kamusjaringan.presenter.dictionary;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.example.kamusjaringan.adapter.ListWordAdapter;
import com.example.kamusjaringan.algorithm.BoyerMooreAlgorithm;
import com.example.kamusjaringan.room.Database;
import com.example.kamusjaringan.room.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class DictionaryPresenter implements IDictionary.IPresenter {
    private IDictionary.IView view;
    private Context context;

    public DictionaryPresenter(IDictionary.IView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void searchData(String word, Database database, ListView listView) {
        List<Dictionary> data = new ArrayList<>();
        List<Dictionary> dictionaries = database.dictionaryDao().getAllData();
        BoyerMooreAlgorithm algorithm = new BoyerMooreAlgorithm();
        int index = algorithm.getIndex(word.toLowerCase(), getData(dictionaries));
        try {
            if (index > -1) {
                Dictionary dictionary;
                if (!dictionaries.get(index).getWord().toLowerCase().equals(word.toLowerCase())) {
                    dictionary = getData(dictionaries, word);
                } else {
                    dictionary = dictionaries.get(index);
                }

                data.add(dictionary);
                List<Dictionary> otherDictionaries = database.dictionaryDao().getOtherData(dictionary.getId());
                data.addAll(getDictionaries(otherDictionaries, word));
            } else {
                int index2 = getIndexData(dictionaries, word);
                if (index2 > -1) {
                    data.add(dictionaries.get(index2));
                    List<Dictionary> otherDictionaries = database.dictionaryDao().getOtherData(dictionaries.get(index2).getId());
                    data.addAll(getDictionaries(otherDictionaries, word));
                } else {
                    data.addAll(getDictionaries(dictionaries, word));
                }
            }
        } catch (Exception ex) {
            Log.e("searchData", ex.getMessage());
            data.addAll(getErrorData(dictionaries, word));
        }

        List<Dictionary> dataAdapter = new ArrayList<>();
        for (Dictionary checkData : data) {
            if(checkData != null) dataAdapter.add(checkData);
        }
        ListWordAdapter adapter = new ListWordAdapter(context, dataAdapter);
        listView.setAdapter(adapter);
    }

    @Override
    public void processSearchData() {
        view.searchData();
    }

    @Override
    public void backToHome() {
        ((Activity) context).finish();
    }

    @Override
    public Dictionary getData(List<Dictionary> dictionaries, String word) {
        for (Dictionary dictionary : dictionaries) {
            if (dictionary.getWord().toLowerCase().equals(word.toLowerCase())) return dictionary;
        }
        return null;
    }

    @Override
    public List<Dictionary> getErrorData(List<Dictionary> dictionaries, String word) {
        List<Dictionary> result = new ArrayList<>();
        for (Dictionary data : dictionaries) {
            if (data.getWord().toLowerCase().contains(word.toLowerCase())) result.add(data);

        }
        return result;
    }

    @Override
    public int getIndexData(List<Dictionary> dictionaries, String word) {
        for (int i = 0; i < dictionaries.size(); i++) {
            if (dictionaries.get(i).getWord().toLowerCase().equals(word.toLowerCase())) return i;
        }
        return -1;
    }

    @Override
    public List<Dictionary> getDictionaries(List<Dictionary> dictionaries, String word) {
        List<Dictionary> result = new ArrayList<>();
        for (Dictionary dictionary : dictionaries) {
            for (int i = 1; i < word.length() - 1; i++) {
                if (!dataHasAdded(result, dictionary) && dictionary.getWord().toLowerCase().contains(word.toLowerCase().substring(i))) {
                    result.add(dictionary);
                }
            }

            for (int i = word.length(); i > 1; i--) {
                if (!dataHasAdded(result, dictionary) && dictionary.getWord().toLowerCase().contains(word.toLowerCase().substring(0, i))) {
                    result.add(dictionary);
                }
            }
        }
        return result;
    }

    private boolean dataHasAdded(List<Dictionary> dictionaries, Dictionary dictionary) {
        for (Dictionary data : dictionaries) {
            if (data.getId() == dictionary.getId()) return true;
        }

        return false;
    }

    private String getData(List<Dictionary> dictionaries) {
        StringBuilder result = new StringBuilder();
        for (Dictionary data : dictionaries) {
            result.append(data.getWord().toLowerCase()).append(";");
        }

        return result.toString();
    }
}
