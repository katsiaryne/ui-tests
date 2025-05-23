package org.example.helper;

import com.google.gson.JsonObject;
import org.assertj.core.api.SoftAssertions;

public abstract class UserProfileSchemaValidator {
    public static void validateUserProfileCreate(JsonObject json) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(json).as("Отсутствует тело ответа").isNotNull();
            softAssertions.assertThat(json.has("name")).as("нет поля name").isTrue();
            softAssertions.assertThat(json.has("job")).as("нет поля job").isTrue();
            softAssertions.assertThat(json.has("id")).as("нет поля id").isTrue();
            softAssertions.assertThat(json.has("createdAt")).as("нет поля createdAt").isTrue();
        });
    }

    public static void validateUserProfileUpdate(JsonObject json) {
        System.out.println(json);
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(json).as("Отсутствует тело ответа").isNotNull();
            softAssertions.assertThat(json.has("name")).as("нет поля name").isTrue();
            softAssertions.assertThat(json.has("job")).as("нет поля job").isTrue();
            softAssertions.assertThat(json.has("updatedAt")).as("нет поля updatedAt").isTrue();
        });
    }
}
