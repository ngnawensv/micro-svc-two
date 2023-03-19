package com.belrose.microsvctwo.service;

import com.belrose.microsvctwo.pojo.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class PersonServiceImplTest {
    @Autowired
    private PersonService personService;
    public static MockWebServer mockBackEnd;

    @Value("${micro.service.one.api.url.base}")
    private String baseUrl;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void setUp(@Value("${micro.service.one.api.url.port}") int port) throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start(port);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    public void sentPersonToServiceOne() throws Exception {

        Person mockPerson = new Person(100, "Adam", "Sandler");

        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(mockPerson))
                .addHeader("Content-Type", "application/json; charset=utf-8"));

        Mono<Person> response = Mono.just(personService.sentPersonToServiceOne(mockPerson));

        StepVerifier.create(response)
                .expectNextMatches(resp -> resp.getLastName().equals(mockPerson.getLastName()))
                .verifyComplete();

        RecordedRequest recordedRequest = mockBackEnd.takeRequest();

        assertEquals("POST", recordedRequest.getMethod());
        assertEquals("/person", recordedRequest.getPath());
    }

}