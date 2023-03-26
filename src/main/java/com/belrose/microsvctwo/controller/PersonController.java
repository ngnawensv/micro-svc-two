package com.belrose.microsvctwo.controller;

import com.belrose.microsvctwo.exception.PersonNotFoundException;
import com.belrose.microsvctwo.model.Person;
import com.belrose.microsvctwo.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@Slf4j
public class PersonController {

  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping(path = "/person", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Person> sendPerson(@RequestBody @Valid Person person) {
    try {
     return personService.savePerson(person);
    } catch (Exception ex) {
      throw new PersonNotFoundException("Saved person in database fail");
    }

  }


  @GetMapping(path = "/person/{id}")
  public Mono<Person> getPerson(@PathVariable long id){
      return personService.getPerson(id).switchIfEmpty(Mono.error(new PersonNotFoundException("Person not foundxxxx")));
  }

  @GetMapping(path = "/person/all")
  public Flux<Person> getAllPerson()  throws  PersonNotFoundException{
    return personService.getAllPerson().switchIfEmpty(Mono.error(new PersonNotFoundException("Error to select all persons")));

  }
}
