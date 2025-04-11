package com.heroku.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static com.heroku.util.TestConstants.CORRECT_USERNAME;
import static com.heroku.util.TestConstants.EMPTY_STRING;
import static com.heroku.util.TestConstants.INVALID_PASSWORD_ERROR;
import static com.heroku.util.TestConstants.INVALID_USERNAME_ERROR;

public class LoginArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(EMPTY_STRING, EMPTY_STRING, INVALID_USERNAME_ERROR),
                Arguments.of("username", EMPTY_STRING, INVALID_USERNAME_ERROR),
                Arguments.of(CORRECT_USERNAME, "password", INVALID_PASSWORD_ERROR)
        );
    }
}
