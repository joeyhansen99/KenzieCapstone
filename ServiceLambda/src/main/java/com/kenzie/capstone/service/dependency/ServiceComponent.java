package com.kenzie.capstone.service.dependency;

import com.kenzie.capstone.service.ExternalCardService;

import com.kenzie.capstone.service.LambdaService;
import dagger.Component;

import javax.inject.Singleton;


@Singleton
@Component(modules = {DaoModule.class, ServiceModule.class})
public interface ServiceComponent {
    ExternalCardService provideExternalCardService();
    LambdaService provideLambdaService();
}
