package com.sebix.dagger.di.main;

import com.sebix.dagger.ui.main.posts.PostsFragment;
import com.sebix.dagger.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment constributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostsFragment constributePostsFragment();
}
