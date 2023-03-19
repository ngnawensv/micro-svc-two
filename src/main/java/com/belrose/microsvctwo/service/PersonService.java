package com.belrose.microsvctwo.service;

import com.belrose.microsvctwo.pojo.Person;

public interface PersonService {
    Person sentPersonToServiceOne(Person person) throws Exception;
}
