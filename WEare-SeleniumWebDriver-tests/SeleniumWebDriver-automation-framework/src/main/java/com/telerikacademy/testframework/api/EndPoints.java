package com.telerikacademy.testframework.api;

public class EndPoints {

        public static final String REGISTER_USER = "api/users/";

        public static final String UPGRADE_PERSONAL_PROFILE="api/users/auth/%s/personal";

        public static final String CREATE_POST="api/post/auth/creator";

        public static final String authenticate="http://localhost:8081/authenticate";

        public static final String LIKE_POST="api/post/auth/likesUp?postId=%s";

        public static final String DELETE_POST="api/post/auth/manager?postId=%s";

        public static final String API_COMMENTS="api/comment";
        public static final String CREATE_COMMENTS="/auth/creator";
        public static final String DELETE_COMMENT="api/comment/auth/manager";



    }

