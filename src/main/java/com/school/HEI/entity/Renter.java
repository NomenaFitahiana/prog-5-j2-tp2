package com.school.HEI.entity;

import java.util.List;
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
public class Renter {
    private UUID id;
    private String name;
    private RenterType type;
    private String contact;
    private String address;
    private List<Rented> rentedProperties;
}
