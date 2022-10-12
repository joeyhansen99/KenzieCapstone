package com.kenzie.capstone.service.dao;
import com.kenzie.capstone.service.model.CardRecord;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import java.util.List;
import javax.inject.Inject;

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
