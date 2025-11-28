package com.school.HEI.entity;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Location {
    private UUID id;
    private LocalDate locationDate;
    private LocalDate dueDate;
    private LocalDate returnDate = null;
    private LocationState state;
    private double totalAmount;
    private double remainingAmount;
    private Renter renter;
    private Rented rentedProperty;

}
