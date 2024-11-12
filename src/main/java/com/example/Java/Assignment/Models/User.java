package com.example.Java.Assignment.Models;
import java.util.HashSet;
import java.util.Set;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class User {
    private int userId;
    private String username;
    private Set<Integer> following = new HashSet<>();
    private Set<Integer> followers = new HashSet<>();
    private List<String> posts = new ArrayList<>(); // New field for storing post IDs

    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Set<Integer> getFollowing() {
        return following;
    }

    public Set<Integer> getFollowers() {
        return followers;
    }

    public List<String> getPosts() {
        return posts; // Getter for posts
    }
}

