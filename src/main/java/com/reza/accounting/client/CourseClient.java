package com.reza.accounting.client;

import com.reza.accounting.dto.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseClient {

    private final WebClient.Builder webClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public List<Course> getById(Long id) {
        return circuitBreakerFactory.create("Cource-getById").run(() ->
                        Objects.requireNonNull(webClient.build().get()
                                        .uri("http://training/course/all")
                                        .retrieve()
                                        .bodyToMono(new ParameterizedTypeReference<List<Course>>() {
                                        })
                                        .block())
                                .stream()
                                .filter(course -> course.getId().equals(id))
                                .toList(),
                throwable ->
                        Collections.singletonList(
                                new Course()
                                        .setId(id)
                                        .setTitle("Test")
                                        .setMentor("Mentor")
                                        .setRegisterDate(LocalDate.now()))
        );
    }
}
