package com.example.completablefuture.entity;


import com.amazonaws.services.dynamodbv2.datamodeling.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "piratefuture")
public class Pirates {
    @DynamoDBHashKey
    private int id;

    private String snailPhoneNo;
    private String first_name;
    private String last_name;
    private String email;
    private String ip;
}
