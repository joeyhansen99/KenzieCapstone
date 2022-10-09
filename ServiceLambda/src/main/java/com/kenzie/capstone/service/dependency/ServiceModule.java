package com.kenzie.capstone.service.dependency;
import com.kenzie.capstone.service.ExternalCardService;
import com.kenzie.capstone.service.LambdaService;
import com.kenzie.capstone.service.dao.CardDao;
import com.kenzie.capstone.service.dao.ExampleDao;
import com.kenzie.capstone.service.dao.ExternalCardDao;

import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Module(
    includes = DaoModule.class
)
public class ServiceModule {

    @Singleton
    @Provides
    @Inject
    public ExternalCardService provideExternalCardService(@Named("ExternalCardDao") ExternalCardDao externalCardDao) {
        return new ExternalCardService(externalCardDao);
    }

    // code added from original file - BEGINNING
//    @Singleton
//    @Provides
//    @Inject
//    public LambdaService provideLambdaService(@Named("ExampleDao") ExampleDao exampleDao) {
//        return new LambdaService(exampleDao);
//    }
    // code added from original file - END

    //Code by Jet
    @Singleton
    @Provides
    @Inject
    public LambdaService provideLambdaService(@Named("CardDao") CardDao cardDao) {
        return new LambdaService(cardDao);
    }

}




