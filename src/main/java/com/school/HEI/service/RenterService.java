package com.school.HEI.service;

import com.school.HEI.entity.Renter;
import com.school.HEI.exception.BadRequestException;
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

        if (renter == null) {
            log.warn("Renter {} not found", id);
            throw new ResourceNotFoundException("Renter not found: " + id);
        }

        return renter;
    }

    @Override
    public Renter create(Renter renter) {
        log.info("Creating renter");

        if (renter.getName() == null || renter.getName().isBlank()) {
            throw new BadRequestException("Name is required");
        }

        renter.setId(UUID.randomUUID());
        repository.save(renter);

        log.info("Renter {} created", renter.getId());
        return renter;
    }

    @Override
    public Renter update(Renter renter) {
        log.info("Updating renter {}", renter.getId());

        if (renter.getId() == null) {
            throw new BadRequestException("Renter ID cannot be null for update");
        }

        getById(renter.getId());
        repository.save(renter);

        log.info("Renter {} updated", renter.getId());
        return renter;
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting renter {}", id);

        getById(id);

        repository.delete(id);

        log.info("Renter {} deleted", id);
    }
}
