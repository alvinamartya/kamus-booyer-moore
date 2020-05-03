package com.example.kamusjaringan.algorithm;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BoyerMooreAlgorithm {
    // get table value
    private List<AlgorithmValueModel> tableValue(final String word) {
        List<AlgorithmValueModel> result = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (result.isEmpty()) {
                result.add(
                        new AlgorithmValueModel(
                                word.charAt(i),
                                formulaValueLetter(word.length(), i)
                        )
                );
            } else {
                AlgorithmValueModel value = null;
                for (AlgorithmValueModel data : result) {
                    if (data.getLeter() == word.charAt(i)) value = data;
                }

                if (value == null) {
                    result.add(
                            new AlgorithmValueModel(
                                    word.charAt(i),
                                    formulaValueLetter(word.length(), i)
                            )
                    );
                } else {
                    value.setValue(formulaValueLetter(word.length(), i));
                }
            }
        }

        result.add(new AlgorithmValueModel('*', word.length()));
        return result;
    }

    // get index model
    public int getIndex(String word, String dictionary) {
        String data = getData(word, dictionary);
        int result = -1;
        for (char letter : data.toCharArray()) {
            if (letter == ';') result++;
        }
        return result;
    }

    // boyer moore
    private String getData(String word, String dictionary) {
        int pos = word.length() - 1;
        int posValue = 0;
        int posWord = word.length() - 1;
        List<AlgorithmValueModel> table = tableValue(word);

        while (pos < dictionary.length()) {
            if (posWord == -1) {
                return dictionary.substring(0, pos + 2 + word.length());
            } else if (dictionary.charAt(pos) != word.charAt(posWord)) {
                posWord = word.length() - 1;
                pos = pos + getValueLetter(dictionary.charAt(pos), table) + posValue;
                posValue = 0;
            } else {
                pos--;
                posWord--;
                posValue++;
            }
        }

        return "";
    }

    // get value letter
    private int getValueLetter(char letter, List<AlgorithmValueModel> values) {
        int finalValue = 0;
        for (AlgorithmValueModel value : values) {
            if (value.getLeter() == '*') {
                finalValue = value.getValue();
                continue;
            }
            if (value.getLeter() == letter) return value.getValue();
        }

        return finalValue;
    }

    // formula for getting value
    private int formulaValueLetter(int lengthOfPattern, int indexOfActualCharacter) {
        return Math.max(1, lengthOfPattern - indexOfActualCharacter - 1);
    }
}
