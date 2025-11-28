package com.school.HEI.entity;

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
public class Rented {
    private UUID id;
    private String name;
    private double value;
    private String description;
    private State state;
    private boolean available;
}
