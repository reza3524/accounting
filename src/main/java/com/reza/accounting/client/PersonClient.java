package com.reza.accounting.client;

import com.reza.accounting.dto.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PersonClient {

    private final WebClient.Builder webClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public Person getById(Long id) {
        return circuitBreakerFactory.create("hrm-getById").run(() ->
                        webClient.build().get()
                                .uri("http://hrm/person/{id}", id)
                                .retrieve()
                                .bodyToMono(Person.class)
                                .block(),
                throwable -> new Person()
                        .setId(id)
                        .setFullName("Name")
                        .setNationalCode("4567890123")
                        .setBirthDate(LocalDate.now())
        );
    }
}
