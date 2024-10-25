package com.example.notesapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapplication.data.DatabaseAccess;
import com.example.notesapplication.data.dao.NoteDao;
import com.example.notesapplication.data.database.AppDatabase;
import com.example.notesapplication.data.model.Note;
import com.example.notesapplication.data.view.MainViewModel;
import com.example.notesapplication.databinding.FragmentMainBinding;

import java.util.concurrent.CompletableFuture;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = NavHostFragment.findNavController(MainFragment.this);
        Context context = getContext();

        RecyclerView recyclerView = binding.noteLayout;
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));

        MainViewModel model = new ViewModelProvider(this).get(MainViewModel.class);
        model.getNotes().observe(getViewLifecycleOwner(), notes -> {
            recyclerView.setAdapter(new CustomAdapter(notes.toArray(new Note[0]), navController));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}