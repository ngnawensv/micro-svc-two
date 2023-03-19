package com.belrose.microsvctwo.service;

import com.belrose.microsvctwo.pojo.Person;
import reactor.core.publisher.Mono;

public interface PersonService {
    Person sentPersonToServiceOne(Person person) throws Exception;
}
