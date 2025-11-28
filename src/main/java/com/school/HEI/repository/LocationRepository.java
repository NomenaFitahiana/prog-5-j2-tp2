package com.school.HEI.repository;

import com.school.HEI.entity.Location;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LocationRepository extends JsonRepository<Location> {

    public LocationRepository(String filePath) {
        super(filePath, new TypeReference<List<Location>>() {
        });
    }

    public List<Location> findAll() {

        return readAll();
    }

    public Location findById(UUID id) {
        return readAll().stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Location location) {
        final List<Location> all = readAll();
        all.removeIf(l -> l.getId().equals(location.getId()));
        all.add(location);
        saveAll(all);
    }

    public void delete(UUID id) {
        List<Location> all = readAll();
        all = all.stream().filter(l -> !l.getId().equals(id)).collect(Collectors.toList());
        saveAll(all);
    }
}
