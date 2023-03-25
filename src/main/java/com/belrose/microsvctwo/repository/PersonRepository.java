package com.belrose.microsvctwo.repository;

import com.belrose.microsvctwo.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonRepository extends ReactiveMongoRepository<Person, Long> {

}
