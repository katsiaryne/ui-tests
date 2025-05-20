package org.example.helper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class ResourceSchemaValidator {
    public static void validateResource(JsonObject json){
        assertNotNull(json);
        assertTrue(json.has("data"));
        assertTrue(json.has("support"));

        JsonObject data = json.getAsJsonObject("data");
        assertTrue(data.has("id"));
        assertTrue(data.has("name"));
        assertTrue(data.has("year"));
        assertTrue(data.has("color"));
        assertTrue(data.has("pantone_value"));

        JsonObject support = json.getAsJsonObject("support");
        assertTrue(support.has("url"));
        assertTrue(support.has("text"));
    }

    public static void validateResourceList(JsonObject json){
        assertTrue(json.has("page"));
        assertTrue(json.has("per_page"));
        assertTrue(json.has("total"));
        assertTrue(json.has("total_pages"));
        assertTrue(json.has("data"));
        assertTrue(json.has("support"));

        JsonArray dataArray = json.getAsJsonArray("data");
        assertFalse(dataArray.isEmpty());

        for (JsonElement element : dataArray) {
            JsonObject item = element.getAsJsonObject();
            assertTrue(item.has("id"));
            assertTrue(item.has("name"));
            assertTrue(item.has("year"));
            assertTrue(item.has("color"));
            assertTrue(item.has("pantone_value"));
        }

        JsonObject support = json.getAsJsonObject("support");
        assertNotNull(support);
        assertTrue(support.has("url"));
        assertTrue(support.has("text"));
    }
}
