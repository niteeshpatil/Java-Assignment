package com.example.Java.Assignment.Services;

import com.example.Java.Assignment.Data.DataStorage;
import com.example.Java.Assignment.Models.Post;
import com.example.Java.Assignment.Models.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class FeedService {
    public List<Post> getFeedForUser(int userId) {
        User user = DataStorage.users.get(userId);
        if (user == null) return new ArrayList<>();

        List<Post> feed = new ArrayList<>();

        // Add posts from followed users
        for (Integer followedId : user.getFollowing()) {
            User followedUser = DataStorage.users.get(followedId);
            if (followedUser != null) {
                List<String> followedPostIds = followedUser.getPosts();
                List<Post> followedPosts = followedPostIds.stream()
                        .map(postId -> DataStorage.posts.get(postId))
                        .filter(post -> post != null) // Ensure the post exists
                        .collect(Collectors.toList());
                feed.addAll(followedPosts);
            }
        }

        // Add posts from non-followed users
        List<Post> nonFollowedPosts = DataStorage.users.values().stream()
                .filter(u -> !user.getFollowing().contains(u.getUserId()) && u.getUserId() != userId)
                .flatMap(u -> u.getPosts().stream())
                .map(postId -> DataStorage.posts.get(postId))
                .filter(post -> post != null) // Ensure the post exists
                .collect(Collectors.toList());

        feed.addAll(nonFollowedPosts);

        // Sort the feed based on the criteria
        return feed.stream()
                .sorted(Comparator.comparing(Post::getPostTime).reversed())
                .collect(Collectors.toList());
    }
}

