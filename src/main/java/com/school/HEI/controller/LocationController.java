package com.school.HEI.controller;

import com.school.HEI.entity.Location;
import com.school.HEI.service.LocationServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
@Slf4j
public class LocationController {

    private final LocationServiceImpl service;

    @GetMapping
    public List<Location> getAll() {
        log.info("API GET /locations");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Location getById(@PathVariable UUID id) {
        log.info("API GET /locations/{}", id);
        return service.getById(id);
    }

    @PostMapping
    public Location create(@RequestBody Location location) {
        log.info("API POST /locations");
        return service.create(location);
    }

    @PutMapping("/{id}")
    public Location update(@PathVariable UUID id, @RequestBody Location location) {
        log.info("API PUT /locations/{}", id);
        location.setId(id);
        return service.update(location);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        log.info("API DELETE /locations/{}", id);
        service.delete(id);
    }

    @PatchMapping("/{id}/pay")
    public void pay(@PathVariable UUID id, @RequestParam double amount) {
        log.info("API PATCH /locations/{}/pay amount={}", id, amount);
        service.pay(id, amount);
    }

    @PatchMapping("/{id}/return")
    public void returnItem(@PathVariable UUID id) {
        log.info("API PATCH /locations/{}/return", id);
        service.returnItem(id);
    }
}
