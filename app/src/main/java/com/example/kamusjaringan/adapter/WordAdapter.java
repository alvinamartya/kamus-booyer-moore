package com.example.kamusjaringan.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.kamusjaringan.R;
import com.example.kamusjaringan.room.Database;
import com.example.kamusjaringan.room.Dictionary;
import com.example.kamusjaringan.view.ProcessDetailActivity;
import com.example.kamusjaringan.view.ProcessDictionaryActivity;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {
    // global variables declaration
    private Context context;
    private List<Dictionary> dictionaries;
    private Database database;

    // contractor
    public WordAdapter(Context context, List<Dictionary> dictionaries) {
        this.context = context;
        this.dictionaries = dictionaries;
        database = Room.databaseBuilder(context,
                Database.class, "dictionary").allowMainThreadQueries().build();
    }

    // onCreateViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.row_data_dictionaries, parent, false);
        return new ViewHolder(root);
    }

    // onBindViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // function variable declaration
        final Dictionary dictionary = dictionaries.get(position);

        // function events
        holder.tvWord.setText(dictionary.getWord());
        holder.llData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence[] items = {"Ubah Kata", "Hapus Kata"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(context)
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    // update data
                                    // move to process detail activity
                                    Intent intent = new Intent(context, ProcessDetailActivity.class);
                                    intent.putExtra("data", dictionary);
                                    context.startActivity(intent);
                                } else {
                                    // delete data
                                    database.dictionaryDao().delete(dictionary);
                                    Toast.makeText(context, "Hapus data berhasil", Toast.LENGTH_SHORT).show();
                                    ((ProcessDictionaryActivity)context).showData();
                                }
                            }
                        });

                dialog.show();
            }
        });
    }

    // get count items
    @Override
    public int getItemCount() {
        return dictionaries.size();
    }

    // binding components
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvWord;
        LinearLayout llData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWord = itemView.findViewById(R.id.tv_row_word);
            llData = itemView.findViewById(R.id.ll_data_dictionary);
        }
    }
}
