package com.jacobsanderlin.notesandpasswords.ui.savepassword;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jacobsanderlin.notesandpasswords.databinding.FragmentSavePasswordBinding;

public class SavePasswordFragment extends Fragment {

    private FragmentSavePasswordBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SavePasswordViewModel savePasswordViewModel =
                new ViewModelProvider(this).get(SavePasswordViewModel.class);

        binding = FragmentSavePasswordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSavePassword;
        savePasswordViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}