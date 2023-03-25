package com.belrose.microsvctwo.appconfig;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class AppConfig {

    @Value("${micro.service.one.api.url.base}")
    private String baseUrl;

    @Bean
    @Qualifier("micoServiceOneWebClient")
    WebClient getMicoServiceOneWebClient(){
        HttpClient httpClient=HttpClient.create();
        return WebClient.builder().baseUrl(baseUrl).clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }
}
