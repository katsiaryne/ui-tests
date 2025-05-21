package org.example.helper;

import com.github.javafaker.Faker;
import org.example.request.UserProfileRequest;

public abstract class UserProfileGenerator {
    private static final Faker faker = new Faker();

    public static UserProfileRequest generateUserProfileRequest() {
        return new UserProfileRequest(
                faker.name().firstName(),
                faker.job().title()
        );
    }
}
