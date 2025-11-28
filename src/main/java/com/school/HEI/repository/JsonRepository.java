package com.school.HEI.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, typeRef);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from JSON file: " + file.getName(), e);
        }
    }

    protected void saveAll(List<T> list) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, list);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to JSON file: " + file.getName(), e);
        }
    }
}
