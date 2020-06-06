package com.wangzhi.data.mongodemo.repository;

import com.wangzhi.data.mongodemo.model.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CoffeeRepository extends MongoRepository<Coffee, String> {

    List<Coffee> findByName(String name);
}
