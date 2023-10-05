package com.api.utils;

public class Endpoints {

    public static final String REGISTER_USER = "api/users/";
    public static final String GET_REGISTER_USERS = "api/users";
    public static final String LOGIN_USER = "authenticate";

    public static final String UPGRADE_EXPERTISE_PROFILE="api/users/auth/%s/expertise";

    public static final String UPGRADE_PERSONAL_PROFILE="api/users/auth/%s/personal";

    public static final String CREATE_POST="api/post/auth/creator";

    public static final String authenticate="http://localhost:8081/authenticate";

    public static final String GET_POSTS="api/post/";

    public static final String EDIT_POST="api/post/auth/editor?postId=%s";

    public static final String LIKE_POST="api/post/auth/likesUp?postId=%s";
    public static final  String SHOW_COMMENTS="api/post/Comments?postId=%s";
    public static final String DELETE_POST="api/post/auth/manager?postId=%s";
}
