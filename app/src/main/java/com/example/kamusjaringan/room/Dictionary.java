package com.example.kamusjaringan.room;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Dictionary implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "word")
    String word;
    @ColumnInfo(name = "meaning")
    String meaning;

    public Dictionary(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    protected Dictionary(Parcel in) {
        id = in.readInt();
        word = in.readString();
        meaning = in.readString();
    }

    public static final Creator<Dictionary> CREATOR = new Creator<Dictionary>() {
        @Override
        public Dictionary createFromParcel(Parcel in) {
            return new Dictionary(in);
        }

        @Override
        public Dictionary[] newArray(int size) {
            return new Dictionary[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(word);
        dest.writeString(meaning);
    }
}
