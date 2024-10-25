package com.example.notesapplication.data;

import android.content.Context;

import androidx.room.Room;

import com.example.notesapplication.data.database.AppDatabase;

public class DatabaseAccess {
    private static AppDatabase notesDatabase;

    public static AppDatabase getNotesDatabase(Context context) {
        if (notesDatabase == null)
            notesDatabase = Room.databaseBuilder(context, AppDatabase.class, "notes").build();

        return notesDatabase;
    }
}
