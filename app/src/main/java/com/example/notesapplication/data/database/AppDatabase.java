package com.example.notesapplication.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.notesapplication.data.dao.NoteDao;
import com.example.notesapplication.data.model.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
