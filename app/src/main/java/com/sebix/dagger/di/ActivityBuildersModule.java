package com.sebix.dagger.di;

import com.sebix.dagger.AuthActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();


}
