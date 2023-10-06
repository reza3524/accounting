package com.reza.accounting.service;

import com.reza.accounting.client.CourseClient;
import com.reza.accounting.client.PersonClient;
import com.reza.accounting.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final PersonClient personClient;
    private final CourseClient courseClient;

    public Account findById(Long id) {
        return new Account()
                .setId(id)
                .setSalary(BigDecimal.TEN)
                .setPerson(personClient.getById(id))
                .setCourses(courseClient.getById(id));
    }
}
