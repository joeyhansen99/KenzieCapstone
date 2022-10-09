package com.kenzie.capstone.service;
import com.kenzie.capstone.service.converter.CardConverter;
import com.kenzie.capstone.service.dao.CardDao;
import com.kenzie.capstone.service.model.Card;
import com.kenzie.capstone.service.model.ExampleData;
import com.kenzie.capstone.service.dao.ExampleDao;
import com.kenzie.capstone.service.model.ExampleRecord;

import javax.inject.Inject;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LambdaService {

    private ExampleDao exampleDao;
    private CardDao cardDao;

    @Inject
    public LambdaService(CardDao cardDao) {
        this.cardDao = cardDao;
    }
//    public LambdaService(ExampleDao exampleDao) {
//        this.exampleDao = exampleDao;
//    }




    public ExampleData getExampleData(String id) {
        List<ExampleRecord> records = exampleDao.getExampleData(id);
        if (records.size() > 0) {
            return new ExampleData(records.get(0).getId(), records.get(0).getData());
        }
        return null;
    }

    public List<Card> getAllCards() {
        //CODE CITE: Jordan helped me with this.
        return cardDao.getAllCards().stream()
                .map(CardConverter::fromRecordtoCard)
                .collect(Collectors.toList());
    }

    public ExampleData setExampleData(String data) {
        String id = UUID.randomUUID().toString();
        ExampleRecord record = exampleDao.setExampleData(id, data);
        return new ExampleData(id, data);
    }
}
