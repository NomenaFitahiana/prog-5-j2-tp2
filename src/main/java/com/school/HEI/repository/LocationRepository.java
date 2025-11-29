package com.school.HEI.repository;

import com.school.HEI.entity.Location;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public class LocationRepository extends JsonRepository<Location> {

    public LocationRepository(String filePath) {
        super(filePath, new TypeReference<List<Location>>() {
        });
    }

    public List<Location> findAll() {
        log.debug("Fetching all locations");
        return readAll();
    }

    public Location findById(UUID id) {
        log.debug("Searching for location {}", id);
        return readAll().stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Location location) {
        log.debug("Saving location {}", location.getId());
        final List<Location> all = readAll();
        all.removeIf(l -> l.getId().equals(location.getId()));
        all.add(location);
        saveAll(all);
    }

    public void delete(UUID id) {
        log.debug("Deleting location {}", id);
        final List<Location> all = readAll();
        all.removeIf(l -> l.getId().equals(id));
        saveAll(all);
    }
}
