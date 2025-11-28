package com.school.HEI.repository;

import com.school.HEI.entity.Renter;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RenterRepository extends JsonRepository<Renter> {

    public RenterRepository(String filePath) {
        super(filePath, new TypeReference<List<Renter>>() { });
    }

    public List<Renter> findAll() {
        return readAll();
    }

    public Renter findById(UUID id) {
        return readAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Renter renter) {
        final List<Renter> all = readAll();
        all.removeIf(r -> r.getId().equals(renter.getId()));
        all.add(renter);
        saveAll(all);
    }

    public void delete(UUID id) {
        List<Renter> all = readAll();
        all = all.stream().filter(l -> !l.getId().equals(id)).collect(Collectors.toList());
        saveAll(all);
    }
}
