package com.melontalk.footlessbird.melontalk.model;

import java.util.HashMap;
import java.util.Map;

public class ChatModel {

    public Map<String, Boolean> users = new HashMap<>();    //  users in chatting room
    public Map<String, Comment> comments = new HashMap<>();

    public static class Comment {

       public String uId;
       public String message;
    }

}
