package com.belrose.microsvctwo.controller;

import com.belrose.microsvctwo.pojo.Person;
import com.belrose.microsvctwo.service.PersonService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(path = "/person",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person SendPerson(@Validated @RequestBody Person person){
        try{
            Person response = personService.sentPersonToServiceOne(person);
            return response;
        }catch (Exception ex){
            ex.getStackTrace();
            return null;
        }

    }
}
