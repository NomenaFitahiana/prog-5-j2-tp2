package com.school.HEI.repository;

import com.school.HEI.entity.Rented;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public class RentedRepository extends JsonRepository<Rented> {

    public RentedRepository(String filePath) {
        super(filePath, new TypeReference<List<Rented>>() {
        });
    }

    public List<Rented> findAll() {
        log.debug("Fetching all rented items");
        return readAll();
    }

    public Rented findById(UUID id) {
        log.debug("Searching rented item {}", id);
        return readAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Rented rented) {
        log.debug("Saving rented item {}", rented.getId());
        final List<Rented> all = readAll();
        all.removeIf(r -> r.getId().equals(rented.getId()));
        all.add(rented);
        saveAll(all);
    }

    public void delete(UUID id) {
        log.debug("Deleting rented item {}", id);
        final List<Rented> all = readAll();
        all.removeIf(r -> r.getId().equals(id));
        saveAll(all);
    }
}
