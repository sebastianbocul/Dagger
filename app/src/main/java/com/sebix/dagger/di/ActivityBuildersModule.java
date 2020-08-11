package com.sebix.dagger.di;

import com.sebix.dagger.di.auth.AuthModule;
import com.sebix.dagger.di.auth.AuthViewModelsModule;
import com.sebix.dagger.ui.auth.AuthActivity;
import com.sebix.dagger.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class,
                    AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
