package com.example.notesapplication;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.notesapplication.data.DatabaseAccess;
import com.example.notesapplication.data.dao.NoteDao;
import com.example.notesapplication.data.database.AppDatabase;
import com.example.notesapplication.data.model.Note;
import com.example.notesapplication.databinding.FragmentNoteEditBinding;

import java.util.concurrent.CompletableFuture;

public class NoteEditFragment extends Fragment {

    private FragmentNoteEditBinding binding;
    private Note note;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentNoteEditBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int noteId = getArguments().getInt("note_id");

        AppDatabase database = DatabaseAccess.getNotesDatabase(getContext());
        NoteDao userDao = database.noteDao();

        binding.contentTextInput.setExpandedHintEnabled(false);

        if (noteId == -1)
            note = new Note();
        else {
            CompletableFuture.supplyAsync(() -> userDao.findById(noteId))
                    .thenAccept(findNote -> {
                        note = findNote;
                        binding.titleTextInput.setText(note.getTitle());
                        EditText editText = binding.contentTextInput.getEditText();
                        if (editText != null) {
                            Editable editable = editText.getText();
                            editable.append(note.getContent());
                        }
                    });
        }

        binding.saveButton.setOnClickListener(v -> {
            String title = binding.titleTextInput.getText().toString();
            String content = binding.contentTextInput.getEditText().getText().toString();

            note.setTitle(title);
            note.setContent(content);

            CompletableFuture.runAsync(() -> userDao.updateAll(note));

            NavHostFragment.findNavController(NoteEditFragment.this)
                    .navigate(R.id.action_SecondFragment_to_FirstFragment);

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}