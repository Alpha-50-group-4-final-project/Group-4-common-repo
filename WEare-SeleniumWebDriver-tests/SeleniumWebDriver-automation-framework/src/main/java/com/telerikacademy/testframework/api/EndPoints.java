package com.telerikacademy.testframework.api;

public class EndPoints {

   protected static final String REGISTER_USER = "api/users/";

   protected static final String UPGRADE_PERSONAL_PROFILE = "api/users/auth/%s/personal";

   protected static final String CREATE_POST = "api/post/auth/creator";

   protected static final String authenticate = "http://localhost:8081/authenticate";

   protected static final String LIKE_POST = "api/post/auth/likesUp?postId=%s";

   protected static final String DELETE_POST = "api/post/auth/manager?postId=%s";

   protected static final String API_COMMENTS = "api/comment";
   protected static final String CREATE_COMMENTS = "/auth/creator";
   protected static final String DELETE_COMMENT = "api/comment/auth/manager";
   protected static final String SEND_REQUEST = "api/auth/request";


}

