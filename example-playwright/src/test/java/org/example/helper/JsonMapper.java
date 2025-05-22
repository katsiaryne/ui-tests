package org.example.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JsonMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T toObject(String json, Class<T> objectClass) throws AssertionError {
        try {
            return objectMapper.readValue(json, objectClass);
        } catch (JsonProcessingException e) {
            throw new AssertionError("JSON-десериализация не удалась");
        }
    }
}
