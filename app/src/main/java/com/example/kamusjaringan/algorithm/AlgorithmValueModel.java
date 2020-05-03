package com.example.kamusjaringan.algorithm;

public class AlgorithmValueModel {
    private char leter;
    private int value;

    public AlgorithmValueModel(char leter, int value) {
        this.leter = leter;
        this.value = value;
    }

    public AlgorithmValueModel() {
    }

    public char getLeter() {
        return leter;
    }

    public void setLeter(char leter) {
        this.leter = leter;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
