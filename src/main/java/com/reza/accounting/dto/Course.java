package com.reza.accounting.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
public class Course {

    private Long id;
    private String title;
    private LocalDate registerDate;
    private String mentor;
}
