package com.belrose.microsvctwo.service;

import com.belrose.microsvctwo.model.Person;
import reactor.core.publisher.Mono;

public interface PersonService {
    Mono<Person> sentPersonToServiceOne(Person person) throws Exception;
    Mono<Person> savePerson(Person person) throws Exception;
}
