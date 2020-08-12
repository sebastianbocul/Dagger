package com.sebix.dagger.di.main;

import androidx.lifecycle.ViewModel;

import com.sebix.dagger.di.ViewModelKey;
import com.sebix.dagger.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);
}
