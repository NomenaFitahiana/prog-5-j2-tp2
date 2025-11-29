package com.school.HEI.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.HEI.exception.RepositoryException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class JsonRepository<T> {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file;
    private final TypeReference<List<T>> typeRef;

    protected JsonRepository(String filePath, TypeReference<List<T>> typeRef) {
        this.file = new File(filePath);
        this.typeRef = typeRef;
    }

    protected List<T> readAll() {
        try {
            if (!file.exists()) {
                log.warn("JSON file {} not found, creating new list", file.getName());
                return new ArrayList<>();
            }

            log.debug("Reading data from {}", file.getName());
            return mapper.readValue(file, typeRef);
        } catch (IOException e) {
            log.error("Failed to read {}: {}", file.getName(), e.getMessage());
            throw new RepositoryException("Failed to read JSON data", e);
        }
    }

    protected void saveAll(List<T> list) {
        try {
            log.debug("Saving {} elements into {}", list.size(), file.getName());
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, list);
        } catch (IOException e) {
            log.error("Failed to write into {}: {}", file.getName(), e.getMessage());
            throw new RepositoryException("Failed to write JSON data", e);
        }
    }
}
