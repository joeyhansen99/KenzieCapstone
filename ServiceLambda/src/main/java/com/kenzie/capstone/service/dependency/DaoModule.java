package com.kenzie.capstone.service.dependency;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.capstone.service.dao.CardDao;
import com.kenzie.capstone.service.dao.ExampleDao;
import com.kenzie.capstone.service.dao.ExternalCardDao;

import com.kenzie.capstone.service.util.DynamoDbClientProvider;
import dagger.Module;
import dagger.Provides;

import java.net.http.HttpClient;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

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

    // code added from original file - BEGINNING
    @Singleton
    @Provides
    @Named("DynamoDBMapper")
    public DynamoDBMapper provideDynamoDBMapper() {
        return new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
    }

    @Singleton
    @Provides
    @Named("ExampleDao")
    @Inject
    public ExampleDao provideExampleDao(@Named("DynamoDBMapper") DynamoDBMapper mapper) {
        return new ExampleDao(mapper);
    }
    // code added from original file - END

    //Code By Jet
    @Singleton
    @Provides
    @Named("CardDao")
    @Inject
    public CardDao provideCardDao(@Named("DynamoDBMapper") DynamoDBMapper mapper) {
        return new CardDao(mapper);
    }
}
