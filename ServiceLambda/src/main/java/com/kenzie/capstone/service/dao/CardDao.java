package com.kenzie.capstone.service.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.kenzie.capstone.service.model.Card;
import com.kenzie.capstone.service.model.CardRecord;

import javax.inject.Inject;
import java.util.List;

public class CardDao {
    private DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of Match objects from the data store.
     * @param mapper Access to DynamoDB
     */
    @Inject
    public CardDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public List<CardRecord> getAllCards() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("attribute_not_exists(id)");
        return mapper.scan(CardRecord.class, scanExpression);
    }
}

/*
https://stackoverflow.com/questions/31790815/get-all-the-table-items-from-dynamodb-table-using-java-high-level-api
DyanmoDBScanExpression scanExpression = new DynamoDBScanExpression();
List<Books> scanResult = mapper.scan(Books.class, scanExpression);

DyanmoDBScanExpression scanExpression = new DynamoDBScanExpression();
List<Books> scanResult = new ArrayList<Books>(mapper.scan(Books.class, scanExpression));

This will work, it will iterate all the items and then returns a list.
 */