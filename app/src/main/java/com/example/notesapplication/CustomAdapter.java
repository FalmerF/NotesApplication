package com.example.notesapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapplication.data.model.Note;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final Note[] localDataSet;
    private final NavController navController;

    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }

        public void setData(Note data) {
            ((TextView)view.findViewById(R.id.note_title_text)).setText(data.getTitle());
            ((TextView)view.findViewById(R.id.note_content_text)).setText(data.getContent());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_block, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.setData(localDataSet[position]);
        viewHolder.getView().setOnClickListener(event -> {
            Bundle bundle = new Bundle();
            bundle.putInt("note_id", localDataSet[position].getId());

            navController.navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
