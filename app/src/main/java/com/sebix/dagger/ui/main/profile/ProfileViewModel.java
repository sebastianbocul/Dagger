package com.sebix.dagger.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sebix.dagger.SessionManager;
import com.sebix.dagger.models.User;
import com.sebix.dagger.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";
    private final SessionManager sessionManager;
    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager=sessionManager;
        Log.d(TAG, "ProfileViewModel: viewmodel is ready");
    }

    public LiveData<AuthResource<User>> getAuthenticatedUser(){
        return sessionManager.getAuthUser();
    }
}
