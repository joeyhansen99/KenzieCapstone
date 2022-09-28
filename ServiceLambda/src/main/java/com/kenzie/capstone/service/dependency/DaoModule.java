package com.kenzie.capstone.service.dependency;


import com.kenzie.capstone.service.dao.ExternalCardDao;

import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.net.http.HttpClient;

@Module
public class DaoModule {

    @Singleton
    @Provides
    @Named("HttpClient")
    public HttpClient provideHttpClient() {
        return HttpClient.newHttpClient();
    }


    @Singleton
    @Provides
    @Named("ExternalCardDao")
    @Inject
    public ExternalCardDao provideExternalCardDao(@Named("HttpClient") HttpClient httpClient) {
        return new ExternalCardDao(httpClient);
    }

}
