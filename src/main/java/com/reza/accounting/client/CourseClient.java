package com.reza.accounting.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.reza.accounting.dto.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseClient {

    private final WebClient.Builder webClient;

    @HystrixCommand(fallbackMethod = "getByIdFallback")
    public List<Course> getById(Long id) {
        return webClient.build().get()
                .uri("http://training/course/all")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Course>>() {
                })
                .block()
                .stream()
                .filter(course -> course.getId().equals(id))
                .toList();
    }

    public List<Course> getByIdFallback(Long id) {
        return Collections.singletonList(
                new Course()
                        .setId(id)
                        .setTitle("Test")
                        .setMentor("Mentor")
                        .setRegisterDate(LocalDate.now())
        );
    }
}
