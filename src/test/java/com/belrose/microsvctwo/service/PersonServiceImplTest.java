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
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class PersonServiceImplTest {
    @Autowired
    private PersonService personService;
    public static MockWebServer  mockWebServer;

    @Value("${micro.service.one.api.url.base}")
    private String baseUrl;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void setUp(@Value("${micro.service.one.api.url.port}") int port) throws IOException {
        // Create a MockWebServer. These are lean enough that you can create a new
        // instance for every unit test.
        mockWebServer = new MockWebServer();
        mockWebServer.start(port);
    }

    @AfterAll
    static void tearDown() throws IOException {
        // Shut down the server. Instances cannot be reused.
        mockWebServer.shutdown();
    }

    @Test
     void sentPersonToServiceOne() throws Exception {

        Person mockPerson = new Person(100, "Adam", "Sandler");

        // Schedule some responses.
        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(mockPerson))
                .addHeader("Content-Type", "application/json; charset=utf-8"));

        // Exercise your application code, which should make those HTTP requests.
        // Responses are returned in the same order that they are enqueued.
        Mono<Person> response = personService.sentPersonToServiceOne(mockPerson);

        StepVerifier.create(response)
                .expectNextMatches(resp -> resp.getLastName().equals(mockPerson.getLastName()))
                .verifyComplete();

        // Optional: confirm that your app made the HTTP requests you were expecting.
        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        assertEquals("POST", recordedRequest.getMethod());
        assertEquals("/person", recordedRequest.getPath());
    }

}
