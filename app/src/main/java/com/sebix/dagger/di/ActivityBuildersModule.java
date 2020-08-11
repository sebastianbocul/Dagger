package com.sebix.dagger.di;

import com.sebix.dagger.di.auth.AuthModule;
import com.sebix.dagger.di.auth.AuthViewModelsModule;
import com.sebix.dagger.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class,
                    AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();


}
