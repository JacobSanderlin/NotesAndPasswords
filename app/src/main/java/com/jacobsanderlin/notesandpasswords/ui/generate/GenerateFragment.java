package com.jacobsanderlin.notesandpasswords.ui.generate;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.jacobsanderlin.notesandpasswords.R;
import com.jacobsanderlin.notesandpasswords.databinding.FragmentGenerateBinding;
import com.jacobsanderlin.notesandpasswords.models.generators.LowerCaseGenerator;
import com.jacobsanderlin.notesandpasswords.models.generators.NumericGenerator;
import com.jacobsanderlin.notesandpasswords.models.generators.PasswordGenerator;
import com.jacobsanderlin.notesandpasswords.models.generators.SpecialCharacterGenerator;
import com.jacobsanderlin.notesandpasswords.models.generators.UpperCaseGenerator;

public class GenerateFragment extends Fragment {

    private FragmentGenerateBinding binding;
    private Button btnGenerate, btnSave, btnCopy;
    private TextView textGenerate;
    private EditText editSize;
    private CheckBox checkLower, checkUpper, checkSpecial, checkNumerical;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GenerateViewModel generateViewModel =
                new ViewModelProvider(this).get(GenerateViewModel.class);

        binding = FragmentGenerateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initViews();
        clickListeners();



        final TextView textView = binding.textGenerated;
        generateViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void clickListeners(){
        btnGenerate.setOnClickListener(view -> {
            int size = Integer.parseInt(editSize.getText().toString());

            if (size < 8) {
//                Toast.makeText(new MainActivity(), "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "Password must be at least 8 characters long", Snackbar.LENGTH_SHORT)
                        .setAnchorView(R.id.fab).show();
                return;
            }
            if (size > 128) {
//                Toast.makeText(new MainActivity(), "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "Password can not be more than 128 characters long", Snackbar.LENGTH_SHORT)
                        .setAnchorView(R.id.fab).show();
                return;
            }

            PasswordGenerator.clear();
            if (checkLower.isChecked()) PasswordGenerator.add(new LowerCaseGenerator());
            if (checkUpper.isChecked()) PasswordGenerator.add(new UpperCaseGenerator());
            if (checkNumerical.isChecked()) PasswordGenerator.add(new NumericGenerator());
            if (checkSpecial.isChecked()) PasswordGenerator.add(new SpecialCharacterGenerator());

            if (PasswordGenerator.isEmpty()) {
                Snackbar.make(view, "Please select one password parameter", Snackbar.LENGTH_SHORT)
                        .setAnchorView(R.id.fab).show();
                return;
            }

            String password = PasswordGenerator.generatePassword(size);
            textGenerate.setText(password);

            btnSave.setEnabled(true);
        });

        btnCopy.setOnClickListener(view ->{
            ClipboardManager manager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            manager.setPrimaryClip(ClipData.newPlainText("password", textGenerate.getText().toString()));
            Toast.makeText(view.getContext(), "Password Copied", Toast.LENGTH_SHORT).show();

        });

        btnSave.setOnClickListener(view ->{

        });
    }

    private void initViews() {
        btnGenerate = binding.btnGenerate;
        btnSave = binding.btnSave;
        btnCopy = binding.btnCopy;
        textGenerate = binding.textGenerated;
        editSize = binding.editSize;
        checkLower = binding.checkLower;
        checkUpper = binding.checkUpper;
        checkNumerical = binding.checkNumerical;
        checkSpecial = binding.checkSpecial;
        btnSave.setEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}