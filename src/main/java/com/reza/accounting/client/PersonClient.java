package com.reza.accounting.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.reza.accounting.dto.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PersonClient {

    private final WebClient.Builder webClient;

    @HystrixCommand(fallbackMethod = "getByIdFallback")
    public Person getById(Long id) {
        return webClient.build().get()
                .uri("http://hrm/person/{id}", id)
                .retrieve()
                .bodyToMono(Person.class)
                .block();
    }

    public Person getByIdFallback(Long id) {
        return new Person()
                .setId(id)
                .setFullName("Name")
                .setNationalCode("4567890123")
                .setBirthDate(LocalDate.now());
    }
}
