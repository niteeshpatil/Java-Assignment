package com.example.Java.Assignment.Services;

import com.example.Java.Assignment.Data.DataStorage;
import com.example.Java.Assignment.Models.Post;

import java.time.LocalDateTime;

public class PostService {
    private int postIdCounter = 1;

    public String uploadPost(int userId, String content) {
        if (DataStorage.users.containsKey(userId)) {
            String postId = String.format("%03d", postIdCounter++);
            Post post = new Post(postId, userId, content);
            DataStorage.posts.put(postId, post);
            DataStorage.users.get(userId).getPosts().add(String.valueOf(post));
            return "Upload Successful with post id: " + postId;
        }
        return "User not found!";
    }
}
