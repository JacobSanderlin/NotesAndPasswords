package com.jacobsanderlin.notesandpasswords.ui.generate;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GenerateViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GenerateViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Click to Generate");
    }


    public LiveData<String> getText() {
        return mText;
    }
}