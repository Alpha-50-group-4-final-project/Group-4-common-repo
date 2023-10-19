package com.api.utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import static com.api.utils.ValidationHelpers.*;
import static dataBaseManipulations.SelectAllUsers.selectAllUsers;

public class Constants {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final Faker faker = new Faker();
    public static final String EMAIL = faker.internet().emailAddress();
    public static String USERNAME;
    public static final String PASSWORD = "Pass_12345";
    public static final String BASE_URL = "http://localhost:8081/";
    public static final String CATEGORY_ID = "100";
    public static final String CATEGORY_NAME = "All";
    public static String ADMIN_USERNAME = faker.name().firstName() + "admin";
    public static String POST_CONTENT = faker.lorem().sentence(10);
    public static String POST_CONTENT_1001_CHARS = faker.lorem().characters(1001, true, true);
    public static String FIRSTNAME = faker.name().firstName();
    public static String TODAY_DATE = LocalDate.now().toString();
    public static String LAST_NAME = faker.name().lastName();
    public static String CITY_ID = faker.random().nextInt(1, 25).toString();
    public static String PERSONAL_REVIEW = faker.lorem().sentence(6);
    public static String AVAILABILITY = faker.random().nextInt(1, 20).toString();
    public static String EDITED_POST_CONTENT = faker.lorem().sentence(4);
    public static String COMMENT_CONTENT = faker.lorem().sentence(5);
    public static String COMMENT_CONTENT_1001_CHARS = faker.lorem().characters(1001, true, true);
    public static String EDITED_COMMENT_CONTENT = faker.lorem().sentence(7);
    public static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    public static String SKILL = faker.job().keySkills();
    public static String EDIT_SKILL = faker.job().keySkills() + timeStamp;
    public static int CATEGORY_ID_SKILL = 100;
    public static String SKILL_ID = faker.random().nextInt(1, 999).toString();
    public static String PUBLIC_CONTENT = "true";
    public static String PRIVATE_CONTENT = "false";
    public static final String CONTENT_SIZE_ERROR = "Content size must be up to 1000 symbols";
    public static final String BAD_REQUEST_ERROR = "Bad Request";
    public static final String NO_PICTURE = "No picture";

    private static List<String>existingUsersInDataBase=selectAllUsers();

    public static String generateUsername()  {
        String generatedName = faker.name().firstName();
        while (!userName(generatedName, 2, 31, "Username should be between 2 and 31 symbols.") && existingUsersInDataBase.contains(generatedName)) {
            generatedName = faker.name().firstName();
        }
//        existingUsers.add(generatedName);
        return generatedName;
    }
}

