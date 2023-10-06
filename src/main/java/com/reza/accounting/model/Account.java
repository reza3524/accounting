package com.reza.accounting.model;

import com.reza.accounting.dto.Course;
import com.reza.accounting.dto.Person;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class Account {

    private Long id;
    private BigDecimal salary;
    private Person person;
    private List<Course> courses;
}
