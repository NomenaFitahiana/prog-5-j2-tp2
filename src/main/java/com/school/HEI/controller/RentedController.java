package com.school.HEI.controller;

import com.school.HEI.entity.Rented;
import com.school.HEI.service.RentedService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
@Slf4j
public class RentedController {

    private final RentedService service;

    @GetMapping
    public List<Rented> getAll() {
        log.info("API GET /items");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Rented getById(@PathVariable UUID id) {
        log.info("API GET /items/{}", id);
        return service.getById(id);
    }

    @PostMapping
    public Rented create(@RequestBody Rented rented) {
        log.info("API POST /items");
        return service.create(rented);
    }

    @PutMapping("/{id}")
    public Rented update(@PathVariable UUID id, @RequestBody Rented rented) {
        log.info("API PUT /items/{}", id);
        rented.setId(id);
        return service.update(rented);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        log.info("API DELETE /items/{}", id);
        service.delete(id);
    }
}
