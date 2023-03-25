package com.belrose.microsvctwo.controller;


import com.belrose.microsvctwo.model.Person;
import com.belrose.microsvctwo.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

//follow this link https://blog.knoldus.com/spring-webflux-how-to-test-your-controllers-using-webtestclient/

//This annotation tells JUnit to run using
@RunWith(SpringRunner.class)
// This annotation will disable full auto-configuration and instead apply only configuration relevant to webFluxTest tests
// (i.e @Controller, @Controller, @ControllerAdvice, @JsonComponent, converter/GenericConverter and WebFluxConfiguration beans
// but not @Component, @Service or @Repository beans)
@WebFluxTest(PersonController.class)
public class PersonControllerTest {

    //Object. It is a non-blocking, reactive client for testing web servers. It uses reactive webClient internally to perform
    // requests and provides a fluent API to verify responses.
    @Autowired
    WebTestClient webTestClient;

    @MockBean // TODO @Import
    private PersonService personService;

    @Test
    public void SendPerson_Person_thenReturnIs2xxSuccessful() throws Exception {

        Person personMock = new Person(100, "Adam", "Sandler");
        Mono<Person>  personMono = Mono.just(personMock);

        when(personService.sentPersonToServiceOne(personMock)).thenReturn(personMono);

        webTestClient.post()
                .uri("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(personMono, Person.class)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    public void SendPerson_Person_thenReturnIs4xxClientError() throws Exception {

        Person personMock = new Person(100, "Adam", "Sandler");
        Mono<Person>  personMono = Mono.just(personMock);

        when(personService.sentPersonToServiceOne(personMock)).thenReturn(personMono);

        webTestClient.post()
                .uri("/perso")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(personMono, Person.class)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    public void SendPerson_Person_thenReturnIs5xxServerError() throws Exception {

        Person personMock = new Person(100, "Adam", "Sandler");
        Mono<Person>  personMono = Mono.just(personMock);

        doThrow(new Exception()).when(personService).sentPersonToServiceOne(personMock);

        webTestClient.post()
                .uri("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(personMono, Person.class)
                .exchange()
                .expectStatus().is5xxServerError();
    }
}
