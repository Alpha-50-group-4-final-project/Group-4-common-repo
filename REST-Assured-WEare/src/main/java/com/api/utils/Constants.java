package com.api.utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;

public class Constants {
    public static final String dumboUserID = "39";
    public static final String annUserID = "134";
    public static final String EXISTING_USER = "Dumbo";
    public static final String EXISTING_USER_two = "Ann";
    public static final String EXISTING_USER_PASSWORD = "12345678";

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
    public static String TODAY_DATE = LocalDate.now().toString();
    public static String LAST_NAME = faker.name().lastName();

    public static String CITY_ID = faker.random().nextInt(1, 25).toString();

    public static String PERSONAL_REVIEW = faker.lorem().sentence(6);

    public static String AVAILABILITY = faker.random().nextInt(1, 20).toString();

    public static String EDITED_POST_CONTENT = faker.lorem().sentence(4);

    public static String COMMENT_CONTENT = faker.lorem().sentence(5);

    public static String EDITED_COMMENT_CONTENT=faker.lorem().sentence(7);
    public static String SKILL = faker.job().keySkills();
    public static String EDITED_SKILL = faker.job().keySkills();
    public static int CATEGORY_ID_SKILL = 100;
    public static String SKILL_ID = faker.random().nextInt(1, 999).toString();


}

