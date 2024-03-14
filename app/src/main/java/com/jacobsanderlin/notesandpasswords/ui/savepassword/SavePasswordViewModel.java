package com.jacobsanderlin.notesandpasswords.ui.savepassword;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SavePasswordViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SavePasswordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the save password fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
    // TODO: Implement the ViewModel
}