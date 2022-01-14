package com.example.completablefuture.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.completablefuture.configure.DynamoDbConf;
import com.example.completablefuture.entity.Pirates;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PirateRepository {
    private DynamoDBMapper dynamoDBMapper;

    public PirateRepository() {
        dynamoDBMapper = new DynamoDbConf().dynamoDBMapper();
    }


    public Pirates save(Pirates pirates){
        dynamoDBMapper.save(pirates);
        return pirates;
    }
    public Pirates getCrewById(String crewid) {
        return dynamoDBMapper.load(Pirates.class, crewid);
    }

    public List<Pirates> findAll() {
        return null;
    }
}
