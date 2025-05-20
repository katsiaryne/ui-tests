package org.example.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIResponse;
import org.example.response.ResourceListResponse;
import org.example.response.ResourceResponse;
import org.example.response.components.Resource;
import org.testng.annotations.Test;

import java.util.List;

import static org.example.helper.JsonMapper.toObject;
import static org.example.helper.ResourceSchemaValidator.validateResource;
import static org.example.helper.ResourceSchemaValidator.validateResourceList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ApiTest extends ApiBaseTest {
    @Test
    public void testGetSingleResource() {
        APIResponse response = context.get("/api/unknown/2");
        assertTrue(response.ok());
        JsonObject json = JsonParser.parseString(response.text()).getAsJsonObject();
        validateResource(json);
        ResourceResponse resourceResponse = toObject(response.text(), ResourceResponse.class);
        assertEquals(resourceResponse.data().id(), 2);
    }

    @Test
    public void checkListResource() {
        APIResponse response = context.get("/api/unknown");

        JsonObject json = JsonParser.parseString(response.text()).getAsJsonObject();
        System.out.println(json);
        validateResourceList(json);

        ResourceListResponse resourceListResponse = toObject(response.text(), ResourceListResponse.class);
        List<Integer> actualYears = resourceListResponse
                .data()
                .stream()
                .map(Resource::year)
                .toList();
        List<Integer> expectedYears = actualYears
                .stream()
                .sorted()
                .toList();

        assertTrue(response.ok());
        assertEquals(actualYears, expectedYears);
    }

    @Test
    public void testGetSingleResourceError() {
        APIResponse response = context.get("/api/unknown/23");
        assertEquals(response.status(), 404);
    }
}
