package com.school.HEI.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.HEI.entity.Renter;
import com.school.HEI.service.RenterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/renters")
@Slf4j
public class RenterController {

    private final RenterService service;

    @GetMapping
    public List<Renter> getAll() {
        log.info("API GET /renters");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Renter getById(@PathVariable UUID id) {
        log.info("API GET /renters/{}", id);
        return service.getById(id);
    }

    @PostMapping
    public Renter create(@RequestBody Renter renter) {
        log.info("API POST /renters");
        return service.create(renter);
    }

    @PutMapping("/{id}")
    public Renter update(@PathVariable UUID id, @RequestBody Renter renter) {
        log.info("API PUT /renters/{}", id);
        renter.setId(id);
        return service.update(renter);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        log.info("API DELETE /renters/{}", id);
        service.delete(id);
    }
}
