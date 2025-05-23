package org.example.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIResponse;
import org.example.request.UserProfileRequest;
import org.example.response.UserProfileResponse;
import org.testng.annotations.Test;

import static org.example.helper.JsonMapper.toObject;
import static org.example.helper.UserProfileGenerator.generateUserProfileRequest;
import static org.example.helper.UserProfileSchemaValidator.validateUserProfileCreate;
import static org.example.helper.UserProfileSchemaValidator.validateUserProfileUpdate;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class UserProfileTest extends ApiBaseTest {
    @Test
    public void testUpdateUserProfile() {
        UserProfileRequest expectedUser = generateUserProfileRequest();

        APIResponse response = context.put("/api/user/2");
        JsonObject json = JsonParser.parseString(response.text()).getAsJsonObject();
        validateUserProfileUpdate(json);
        UserProfileResponse userProfileResponse = toObject(response.text(), UserProfileResponse.class);

        assertTrue(response.ok());
        assertEquals(userProfileResponse.name(), expectedUser.name());
        assertEquals(userProfileResponse.job(), expectedUser.job());
        assertNotNull(userProfileResponse.updatedAt());
    }

    @Test
    public void testAddNewUserProfile() {
        UserProfileRequest expectedUser = generateUserProfileRequest();

        APIResponse response = context.post("/api/user");
        JsonObject json = JsonParser.parseString(response.text()).getAsJsonObject();
        validateUserProfileCreate(json);
        UserProfileResponse userProfileResponse = toObject(response.text(), UserProfileResponse.class);

        assertEquals(response.status(), 201);
        assertEquals(userProfileResponse.name(), expectedUser.name());
        assertEquals(userProfileResponse.job(), expectedUser.job());
        assertNotNull(userProfileResponse.id());
        assertNotNull(userProfileResponse.createdAt());
    }

    @Test
    public void testDeleteUserProfile() {
        APIResponse response = context.delete("/api/user");
        assertEquals(response.status(), 204);
        assertTrue(response.text().isEmpty());
    }
}
