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

}

