package com.telerikacademy.testframework.api;

public class JsonBodies {

    protected static final String REGISTER_USER_BODY = "{  \n" +
            "    \"authorities\": [\n" +
            "    \"ROLE_USER\"\n" +
            "  ],\n" +
            "    \"category\": {\n" +
            "    \"id\": \"%s\",\n" +
            "    \"name\": \"%s\"\n" +
            "  },    \n" +
            "  \"confirmPassword\": \"%s\",\n" +
            "  \"email\": \"%s\",\n" +
            "  \"password\": \"%s\",\n" +
            "  \"username\": \"%s\"\n" +
            "}";


    protected static final String UPGRADE_PERSONAL_PROFILE_BODY = "{\n" +
            "  \"birthYear\": \"%s\",\n" +
            "  \"firstName\": \"%s\",\n" +
            "  \"id\": %s,\n" +
            "  \"lastName\": \"%s\",\n" +
            "  \"location\": {\n" +
            "    \"city\": {\n" +
            "      \"city\": \"\",\n" +
            "      \"country\": {},\n" +
            "      \"id\": %s \n" +
            "    },\n" +
            "    \"id\": 0\n" +
            "  },\n" +
            "  \"memberSince\": \"\",\n" +
            "  \"personalReview\": \"%s\",\n" +
            "  \"picture\": \"no picki\",\n" +
            "  \"picturePrivacy\": true,\n" +
            "  \"sex\": \"MALE\"\n" +
            "}";


    protected static final String CREATE_POST_BODY = "{\n" +
            "  \"content\": \"%s\",\n" +
            "  \"picture\": \"%s\",\n" +
            "  \"public\": \"%s\"\n" +
            "}";

    protected static final String COMMENT_BODY = "{\n" +
            "  \"commentId\": 0,\n" +
            "  \"content\":\"%s\",\n" +
            "  \"deletedConfirmed\": true,\n" +
            "  \"postId\": \"%s\",\n" +
            "  \"userId\": \"%s\"\n" +
            "}";
    protected static final String SEND_CONNECTION_REQ_BODY = "{\n" +
            "  \"id\": \"%s\",\n" +
            "  \"username\": \"%s\"\n" +
            "}";

    protected static String getCreatePostBody() {
        return CREATE_POST_BODY;
    }
}
