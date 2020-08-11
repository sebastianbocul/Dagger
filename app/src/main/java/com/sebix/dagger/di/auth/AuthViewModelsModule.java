package com.sebix.dagger.di.auth;

import androidx.lifecycle.ViewModel;

import com.sebix.dagger.di.ViewModelKey;
import com.sebix.dagger.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);

}
