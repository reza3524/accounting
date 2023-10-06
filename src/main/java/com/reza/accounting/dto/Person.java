package com.reza.accounting.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
public class Person {

    private Long id;
    private String nationalCode;
    private String fullName;
    private LocalDate birthDate;
}
