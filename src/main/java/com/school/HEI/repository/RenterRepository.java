package com.school.HEI.repository;

import com.school.HEI.entity.Renter;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public class RenterRepository extends JsonRepository<Renter> {

    public RenterRepository(String filePath) {
        super(filePath, new TypeReference<List<Renter>>() {
        });
    }

    public List<Renter> findAll() {
        log.debug("Fetching all renters");
        return readAll();
    }

    public Renter findById(UUID id) {
        log.debug("Searching for renter {}", id);
        return readAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Renter renter) {
        log.debug("Saving renter {}", renter.getId());
        final List<Renter> all = readAll();
        all.removeIf(r -> r.getId().equals(renter.getId()));
        all.add(renter);
        saveAll(all);
    }

    public void delete(UUID id) {
        log.debug("Deleting renter {}", id);
        final List<Renter> all = readAll();
        all.removeIf(r -> r.getId().equals(id));
        saveAll(all);
    }
}
