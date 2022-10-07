package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.CardRecord;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface CardRepository extends CrudRepository<CardRecord, String>  {

}
