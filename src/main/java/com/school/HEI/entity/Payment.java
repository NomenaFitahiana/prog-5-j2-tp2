package com.school.HEI.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Payment {
    private LocalDate date;
    private double amount;
}