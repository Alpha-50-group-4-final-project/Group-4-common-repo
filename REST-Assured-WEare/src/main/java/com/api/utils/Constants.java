package com.api.utils;

import com.github.javafaker.Faker;

public class Constants {

    private static final Faker faker = new Faker();

    public static final String EMAIL = faker.internet().emailAddress();
    public static final String USERNAME = faker.name().firstName();  // Generate a simple first name
    public static final String PASSWORD = faker.internet().password();
    public static final String BASE_URL = "http://localhost:8081/";

}

