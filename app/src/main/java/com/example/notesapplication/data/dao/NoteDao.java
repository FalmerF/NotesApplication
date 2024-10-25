package com.example.notesapplication.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Upsert;

import com.example.notesapplication.data.model.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAll();

    @Query("SELECT * FROM note WHERE id=:id")
    Note findById(int id);

    @Upsert
    void updateAll(Note... notes);

    @Delete
    void delete(Note note);
}
