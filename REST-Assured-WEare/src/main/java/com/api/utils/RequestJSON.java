package com.api.utils;

public class RequestJSON {

    public static final String REGISTER_USER_BODY = "{  \n" +
            "    \"authorities\": [\n" +
            "    \"ROLE_USER\"\n" +
            "  ],\n" +
            "    \"category\": {\n" +
            "    \"id\": 119,\n" +
            "    \"name\": \"Gardener\"\n" +
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

    public static final String REGISTER_ADMIN_BODY = "{\n" +
            "  \"authorities\": [\n" +
            "    \"ROLE_ADMIN\"\n" +
            "  ],\n" +
            "  \n" +
            "  \"category\": {\n" +
            "    \"id\": %s,\n" +
            "    \"name\": \"%s\"\n" +
            "\n" +
            "  },\n" +
            "  \"confirmPassword\": \"%s\",\n" +
            "  \"email\": \"%s\",\n" +
            "  \"password\": \"%s\",\n" +
            "  \"username\": \"%s\"\n" +
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
            "  \"picture\": \"no picki\",\n" +
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
            "  \"skill3\": \"%s\",\n" +
            "  \"skill4\": \"%s\",\n" +
            "  \"skill5\": \"%s\",\n" +
            "  \"skills\": [\n" +
            "    \"%s\"\n" +
            "  ]\n" +
            "}";



    public static final String CREATE_POST_BODY="{\n" +
            "  \"content\": \"%s\",\n" +
            "  \"picture\": \"No picture\",\n" +
            "  \"public\": true\n" +
            "}";
}

