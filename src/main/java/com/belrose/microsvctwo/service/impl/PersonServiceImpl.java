package com.belrose.microsvctwo.service.impl;

import com.belrose.microsvctwo.pojo.Person;
import com.belrose.microsvctwo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    @Qualifier("micoServiceOneWebClient")
    private WebClient micoServiceOneWebClient;
    private static final String PATH="/person";

    @Override
    public Person sentPersonToServiceOne(Person person) throws Exception {
        try{
            Person response = micoServiceOneWebClient
                .post()
                .uri(uriBuilder -> uriBuilder.path(PATH).build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(person))
                .retrieve()
                .bodyToMono(Person.class)
                .block();

            System.out.println("Data send.............");
        return response;
        }catch (Exception ex){
            throw new Exception("Error  to send data..............",ex);
        }
    }
}
