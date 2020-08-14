package com.sebix.dagger.di.auth;

import com.sebix.dagger.network.auth.AuthApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    @AuthScope
    @Provides
    static AuthApi provideAuthAPi(Retrofit retrofit){
        return retrofit.create(AuthApi.class);
    }

}
