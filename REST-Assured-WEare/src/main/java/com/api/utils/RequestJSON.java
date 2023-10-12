package com.api.utils;

public class RequestJSON {

    public static final String REGISTER_USER_BODY = "{  \n" +
            "    \"authorities\": [\n" +
            "    \"%s\"\n" +
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
    public static final String GET_ALL_REGISTER_USERS_BODY = "{\n" +
            "  \"index\": 0,\n" +
            "  \"next\": true,\n" +
            "  \"searchParam1\": \"\",\n" +
            "  \"searchParam2\": \"\",\n" +
            "  \"size\": 15\n" +
            "}";
    public static final String UPGRADE_PERSONAL_PROFILE_BODY="{\n" +
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
            "  \"picture\": \"%s\",\n" +
            "  \"picturePrivacy\": true,\n" +
            "  \"sex\": \"MALE\"\n" +
            "}";
    public static final String UPGRADE_EXPERTISE_PROFILE_BODY="{\n" +
            "  \"availability\": %s,\n" +
            "  \"category\": {\n" +
            "    \"id\": %s,\n" +
            "    \"name\": \"%s\"\n" +
            "  },\n" +
            "  \"id\": %s,\n" +
            "  \"skill1\": \"%s\",\n" +
            "  \"skill2\": \"%s\",\n" +
            "  \"skills\": [\n" +
            "    \"%s\"\n" +
            "  ]\n" +
            "}";

    public static final String SHOW_USER_BY_ID_BODY="{\n" +
            "  \"index\": 0,\n" +
            "  \"next\": true,\n" +
            "  \"searchParam1\": \"string\",\n" +
            "  \"searchParam2\": \"string\",\n" +
            "  \"size\": 10\n" +
            "}";
    public static final String CREATE_POST_BODY="{\n" +
            "  \"content\": \"%s\",\n" +
            "  \"picture\": \"%s\",\n" +
            "  \"public\": \"%s\"\n" +
            "}";

    public static final String EDIT_POST_BODY="{\n" +
            "  \"content\": \"%s\",\n" +
            "  \"picture\": \"%s\",\n" +
            "  \"public\": \"%s\"\n" +
            "}";
    public static final String COMMENT_BODY ="{\n" +
            "  \"commentId\": 0,\n" +
            "  \"content\":\"%s\",\n" +
            "  \"deletedConfirmed\": true,\n" +
            "  \"postId\": \"%s\",\n" +
            "  \"userId\": \"%s\"\n" +
            "}";

    public static final String SKILL_BODY = "{\n" +
            "  \"category\": {\n" +
            "    \"id\": %d,\n" +
            "    \"name\": \"%s\"\n" +
            "  },\n" +
            "  \"skill\": \"%s\",\n" +
            "  \"skillId\": \"%s\"\n" +
            "}";

    public static final String SEND_CONNECTION_REQ_BODY = "{\n" +
            "  \"id\": \"%s\",\n" +
            "  \"username\": \"%s\"\n" +
            "}";
}

