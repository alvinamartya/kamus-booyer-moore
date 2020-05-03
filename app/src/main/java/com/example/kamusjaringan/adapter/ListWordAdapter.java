package com.example.kamusjaringan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kamusjaringan.R;
import com.example.kamusjaringan.room.Dictionary;
import com.example.kamusjaringan.view.DetailActivity;

import java.util.List;

public class ListWordAdapter extends BaseAdapter {
    private Context context;
    private List<Dictionary> dictionaries;

    public ListWordAdapter(Context context, List<Dictionary> dictionaries) {
        this.context = context;
        this.dictionaries = dictionaries;
    }

    @Override
    public int getCount() {
        return dictionaries.size();
    }

    @Override
    public Dictionary getItem(int position) {
        return dictionaries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) dictionaries.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = convertView != null ? convertView : LayoutInflater.from(context).inflate(R.layout.row_list_data_dictionaries, parent, false);

        final Dictionary dictionary = dictionaries.get(position);
        TextView tvWord = root.findViewById(R.id.tv_row_word_list_data);
        LinearLayout llData = root.findViewById(R.id.ll_list_data_dictionary);
        tvWord.setText(dictionary.getWord());
        llData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("data", dictionary);
                context.startActivity(intent);
            }
        });

        return root;
    }
}
