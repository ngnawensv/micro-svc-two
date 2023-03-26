package com.belrose.microsvctwo.service;

import com.belrose.microsvctwo.exception.PersonNotFoundException;
import com.belrose.microsvctwo.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {
    Mono<Person> sentPersonToServiceOne(Person person) throws PersonNotFoundException;
    Mono<Person> savePerson(Person person) throws PersonNotFoundException;
    Mono<Person> getPerson(long id) throws PersonNotFoundException;
    Flux<Person> getAllPerson() throws PersonNotFoundException;
}
