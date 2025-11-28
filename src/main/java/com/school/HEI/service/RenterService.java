package com.school.HEI.service;

import com.school.HEI.entity.Renter;
import com.school.HEI.exception.ResourceNotFoundException;
import com.school.HEI.repository.RenterRepository;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RenterService implements ServiceInterface<Renter> {

    private final RenterRepository repository;

    @Override
    public List<Renter> getAll() {
        log.info("Fetching all renters");
        return repository.findAll();
    }

    @Override
    public Renter getById(UUID id) {
        log.info("Fetching renter {}", id);
        final Renter renter = repository.findById(id);
        if (renter == null)
            throw new ResourceNotFoundException("Renter not found: " + id);
        return renter;
    }

    @Override
    public Renter create(Renter renter) {
        log.info("Creating renter");
        renter.setId(UUID.randomUUID());
        repository.save(renter);
        return renter;
    }

    @Override
    public Renter update(Renter renter) {
        log.info("Updating renter {}", renter.getId());
        getById(renter.getId());
        repository.save(renter);
        return renter;
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting renter {}", id);
        getById(id);
        repository.delete(id);
    }
}
