package com.example.Java.Assignment.Data;

import com.example.Java.Assignment.Models.Post;
import com.example.Java.Assignment.Models.User;

import java.util.HashMap;
import java.util.Map;

public class DataStorage {
    public static Map<Integer, User> users = new HashMap<>();
    public static Map<String, Post> posts = new HashMap<>();
}