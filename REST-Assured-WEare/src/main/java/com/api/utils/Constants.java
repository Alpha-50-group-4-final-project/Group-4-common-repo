package com.api.utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;

public class Constants {

    public static final Faker faker = new Faker();

    public static final String EMAIL = faker.internet().emailAddress();
    public static final String USERNAME = faker.name().firstName();  // Generate a simple first name
    public static final String PASSWORD = faker.internet().password();
    public static final String BASE_URL = "http://localhost:8081/";
    public static final String CATEGORY_ID = "100";
    public static final String CATEGORY_NAME = "All";
    public static String ADMIN_USERNAME = faker.name().firstName() + "admin";

    public static String POST_CONTENT = faker.lorem().sentence(10);
    public static String FIRSTNAME = faker.name().firstName();
    public static String TODAY_DATE= LocalDate.now().toString();
    public static String LAST_NAME = faker.name().lastName();

    public static String CITY_ID = faker.random().nextInt(1, 25).toString();

    public static String PERSONAL_REVIEW = faker.lorem().sentence(6);

    public static String AVAILABILITY = faker.random().nextInt(1, 20).toString();

    public static String EDITED_POST_CONTENT=faker.lorem().sentence(4);

}

