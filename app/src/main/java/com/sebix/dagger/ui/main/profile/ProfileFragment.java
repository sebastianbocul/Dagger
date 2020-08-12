package com.sebix.dagger.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sebix.dagger.R;
import com.sebix.dagger.models.User;
import com.sebix.dagger.ui.auth.AuthResource;
import com.sebix.dagger.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";
    private ProfileViewModel viewModel;
    private TextView email,userName,website;
    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ProfileFragment created");
        viewModel = ViewModelProviders.of(getActivity(), providerFactory).get(ProfileViewModel.class);
        email = view.findViewById(R.id.email);
        userName = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);
        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null){
                    switch (userAuthResource.status){
                        case AUTHENTICATED:{
                            setUsersDetails(userAuthResource.data);
                            break;
                        }

                        case ERROR:{
                            setErrorDetails(userAuthResource.message);
                            break;
                        }
                    }
                }

            }
        });
    }

    private void setErrorDetails(String message) {
        email.setText(message);
        userName.setText("Error");
        website.setText("Error");
    }

    private void setUsersDetails(User data) {
        email.setText(data.getEmail());
        userName.setText(data.getUsername());
        website.setText(data.getWebsite());

    }
}
