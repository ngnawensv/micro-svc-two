package com.belrose.microsvctwo.service.impl;

import com.belrose.microsvctwo.model.Person;
import com.belrose.microsvctwo.repository.PersonRepository;
import com.belrose.microsvctwo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

  private WebClient micoServiceOneWebClient;
  private PersonRepository personRepository;
  private static final String URL_PERSON = "/person";

  public PersonServiceImpl(@Qualifier("micoServiceOneWebClient") WebClient micoServiceOneWebClient,PersonRepository personRepository){
    this.micoServiceOneWebClient=micoServiceOneWebClient;
    this.personRepository=personRepository;
  }
  @Override
  public Mono<Person> sentPersonToServiceOne(Person person) throws Exception {
    Mono<Person> response = micoServiceOneWebClient
        .post()
        .uri(uriBuilder -> uriBuilder.path(URL_PERSON).build())
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(person))
        .retrieve()
        .bodyToMono(Person.class);
    log.info("Data send.............");
    return response;
  }

  @Override
  public Mono<Person> savePerson(Person person) throws Exception {

    Mono<Person> personMono =  personRepository.save(person);

    return  sentPersonToServiceOne(personMono.block());

  }


}
