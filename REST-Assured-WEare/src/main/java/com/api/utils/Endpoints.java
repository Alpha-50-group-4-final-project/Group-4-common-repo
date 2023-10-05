package com.api.utils;

public class Endpoints {

    public static final String REGISTER_USER = "api/users/";
    public static final String GET_REGISTER_USERS = "api/users";
    public static final String LOGIN_USER = "authenticate";

    public static String UPGRADE_EXPERTISE_PROFILE="/api/users/auth/%s/expertise";

    public static String UPGRADE_PERSONAL_PROFILE="api/users/auth/%s/personal";

    public static  String CREATE_POST="api/post/auth/creator";

    public static String authenticate="http://localhost:8081/authenticate";
}
