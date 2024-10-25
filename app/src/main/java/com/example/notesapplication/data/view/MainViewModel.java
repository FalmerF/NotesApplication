package com.example.notesapplication.data.view;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesapplication.data.DatabaseAccess;
import com.example.notesapplication.data.dao.NoteDao;
import com.example.notesapplication.data.database.AppDatabase;
import com.example.notesapplication.data.model.Note;

import java.util.List;

import lombok.Getter;

@Getter
public class MainViewModel extends AndroidViewModel {
    private final LiveData<List<Note>> notes;

    public MainViewModel(Application application) {
        super(application);
        AppDatabase database = DatabaseAccess.getNotesDatabase(application);
        NoteDao userDao = database.noteDao();
        notes = userDao.getAll();
    }
}
