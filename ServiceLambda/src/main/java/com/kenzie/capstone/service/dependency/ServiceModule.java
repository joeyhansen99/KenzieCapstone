package com.kenzie.capstone.service.dependency;
import com.kenzie.capstone.service.ExternalCardService;
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
}

