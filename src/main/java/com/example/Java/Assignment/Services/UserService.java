package com.example.Java.Assignment.Services;


import com.example.Java.Assignment.Data.DataStorage;
import com.example.Java.Assignment.Models.Post;
import com.example.Java.Assignment.Models.User;


public class UserService {

    private int postIdCounter = 1; // Moved here to ensure unique post IDs across all users

    public String registerUser(int userId, String username) {
        if (DataStorage.users.containsKey(userId)) {
            return "User ID already exists!";
        }
        User user = new User(userId, username);
        DataStorage.users.put(userId, user);
        return username + " Registered!!";
    }

    public String followUser(int followerId, int followeeId) {
        if (DataStorage.users.containsKey(followerId) && DataStorage.users.containsKey(followeeId)) {
            User follower = DataStorage.users.get(followerId);
            User followee = DataStorage.users.get(followeeId);

            follower.getFollowing().add(followeeId);
            followee.getFollowers().add(followerId);
            return "Followed " + followee.getUsername() + "!!";
        } else {
            return "One or both users not found!";
        }
    }

    public String unfollowUser(int followerId, int followeeId) {
        if (DataStorage.users.containsKey(followerId) && DataStorage.users.containsKey(followeeId)) {
            User follower = DataStorage.users.get(followerId);
            User followee = DataStorage.users.get(followeeId);

            follower.getFollowing().remove(followeeId);
            followee.getFollowers().remove(followerId);
            return "Unfollowed " + followee.getUsername() + "!!";
        } else {
            return "One or both users not found!";
        }
    }

    public String uploadPost(int userId, String content) {
        User user = DataStorage.users.get(userId);
        if (user == null) {
            return "User not found!";
        }

        String postId = String.format("%03d", postIdCounter++);
        Post post = new Post(postId, userId, content);

        // Store the post in DataStorage
        DataStorage.posts.put(postId, post);
        user.getPosts().add(postId);

        return "Upload Successful with post id: " + postId;
    }

    public String likePost(int userId, String postId) {
        Post post = DataStorage.posts.get(postId);
        if (post == null) return "Post not found!";

        post.addLike();
        return "Post Liked!!";
    }

    public String dislikePost(int userId, String postId) {
        Post post = DataStorage.posts.get(postId);
        if (post == null) return "Post not found!";

        post.addDislike();
        return "Post Disliked!!";
    }
}

