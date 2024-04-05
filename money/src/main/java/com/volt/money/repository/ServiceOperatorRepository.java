package com.volt.money.repository;

import com.volt.money.model.ServiceOperator;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceOperatorRepository extends MongoRepository<ServiceOperator, String> {
    ServiceOperator findByName(String name);
}