package com.telerikacademy.testframework.api;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.List;

import static com.telerikacademy.testframework.api.ValidationHelpers.userName;
import static weare.database.manipulation.BaseSetup.usernames;
import static weare.database.manipulation.SelectAllUsers.selectAllUsers;

public class Constants {

        public static final Faker faker = new Faker();
        public static final String EMAIL = faker.internet().emailAddress();

        public static final String BASE_URL = "http://localhost:8081/";
        public static final String CATEGORY_ID = "100";
        public static final String CATEGORY_NAME = "All";

        public static String TODAY_DATE = LocalDate.now().toString();

        public static String CITY_ID = faker.random().nextInt(1, 25).toString();
        public static String PERSONAL_REVIEW = faker.lorem().sentence(6);

        public static String PUBLIC_CONTENT = "true";

        public static final String NO_PICTURE = "No picture";


        private static String generatedName;

        public static String generateUsername() {
                generatedName = faker.name().firstName();
                if (usernames.size() == 0) {
                        selectAllUsers();
                }
                checkUsername(generatedName);
                System.out.println("Username  passed validation and was generated.");
                usernames.add(generatedName);

                return generatedName;
        }
        protected static void checkUsername(String name) {
                if (usernames.contains(name) || userName(name, 2, 31, "Username should be between 2 and 31 symbols.") == false) {
                        System.out.println("Username is incorrect or already exist");
                        generatedName = faker.name().firstName();
                        checkUsername(generateUsername());
                }
        }

    }
