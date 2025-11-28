package com.school.HEI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.school.HEI.repository.RenterRepository;
import com.school.HEI.repository.RentedRepository;
import com.school.HEI.repository.LocationRepository;

@Configuration
public class RepositoryConfig {

    @Bean
    public RenterRepository renterRepository() {
        return new RenterRepository("src/main/resources/data/renters.json");
    }

    @Bean
    public RentedRepository rentedRepository() {
        return new RentedRepository("src/main/resources/data/rented.json");
    }

    @Bean
    public LocationRepository locationRepository() {
        return new LocationRepository("src/main/resources/data/locations.json");
    }
}
