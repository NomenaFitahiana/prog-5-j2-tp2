package com.school.HEI.service;

import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.school.HEI.entity.Location;
import com.school.HEI.entity.LocationState;
import com.school.HEI.exception.BadRequestException;
import com.school.HEI.exception.ResourceNotFoundException;
import com.school.HEI.repository.LocationRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements ServiceInterface<Location> {

    private final LocationRepository repository;

    @Override
    public List<Location> getAll() {
        log.info("Fetching all locations");
        return repository.findAll();
    }

    @Override
    public Location getById(UUID id) {
        log.info("Fetching location {}", id);
        final Location loc = repository.findById(id);

        if (loc == null) {
            log.warn("Location {} not found", id);
            throw new ResourceNotFoundException("Location not found: " + id);
        }

        return loc;
    }

    @Override
    public Location create(Location location) {
        log.info("Creating new location");

        if (location.getRentedProperty() == null)
            throw new BadRequestException("Rented property is required");

        if (!location.getRentedProperty().isAvailable())
            throw new BadRequestException("Item is not available");

        if (location.getDueDate().isBefore(location.getLocationDate()))
            throw new BadRequestException("Due date cannot be before start date");

        location.setId(UUID.randomUUID());
        location.initAmount();
        location.setState(LocationState.TAKEN);
        location.getRentedProperty().setAvailable(false);

        repository.save(location);
        log.info("Location {} created successfully", location.getId());

        return location;
    }

    @Override
    public Location update(Location location) {
        log.info("Updating location {}", location.getId());

        getById(location.getId()); // Vérifie existence

        repository.save(location);
        log.info("Location {} updated", location.getId());

        return location;
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting location {}", id);
        final Location loc = getById(id);

        if (loc.getState() == LocationState.TAKEN || loc.getState() == LocationState.LATE)
            throw new BadRequestException("Cannot delete active or late location");

        repository.delete(id);
        log.info("Location {} deleted", id);
    }

    public void pay(UUID id, double amount) {
        log.info("Processing payment of {} for location {}", amount, id);

        if (amount <= 0)
            throw new BadRequestException("Amount must be positive");

        final Location loc = getById(id);
        loc.pay(amount);
        repository.save(loc);

        log.info("Payment processed. Remaining: {}", loc.getRemainingAmount());
    }

    public void returnItem(UUID id) {
        log.info("Returning item for location {}", id);
        final Location loc = getById(id);

        loc.returnItem();
        repository.save(loc);

        log.info("Item returned for location {}", id);
    }
}
