package com.school.HEI.service;

import com.school.HEI.entity.Rented;
import com.school.HEI.exception.ResourceNotFoundException;
import com.school.HEI.repository.RentedRepository;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RentedService implements ServiceInterface<Rented> {

    private final RentedRepository repository;

    @Override
    public List<Rented> getAll() {
        log.info("Fetching all rented items");
        return repository.findAll();
    }

    @Override
    public Rented getById(UUID id) {
        log.info("Fetching rented item {}", id);
        final Rented rented = repository.findById(id);
        if (rented == null)
            throw new ResourceNotFoundException("Rented item not found: " + id);
        return rented;
    }

    @Override
    public Rented create(Rented rented) {
        log.info("Creating rented item");
        rented.setId(UUID.randomUUID());
        repository.save(rented);
        return rented;
    }

    @Override
    public Rented update(Rented rented) {
        log.info("Updating rented item {}", rented.getId());
        getById(rented.getId());
        repository.save(rented);
        return rented;
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting rented item {}", id);
        getById(id);
        repository.delete(id);
    }
}
