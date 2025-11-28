package com.school.HEI.repository;

import com.school.HEI.entity.Rented;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RentedRepository extends JsonRepository<Rented> {

    public RentedRepository(String filePath) {
        super(filePath, new TypeReference<List<Rented>>() { });
    }

    public List<Rented> findAll() {
        return readAll();
    }

    public Rented findById(UUID id) {
        return readAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Rented rented) {
        final List<Rented> all = readAll();
        all.removeIf(r -> r.getId().equals(rented.getId()));
        all.add(rented);
        saveAll(all);
    }

    public void delete(UUID id) {
        List<Rented> all = readAll();
        all = all.stream().filter(l -> !l.getId().equals(id)).collect(Collectors.toList());
        saveAll(all);
    }
}
