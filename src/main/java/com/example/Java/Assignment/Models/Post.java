package com.example.Java.Assignment.Models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Post {
    private String postId;
    private int userId; // Added field to store the author's user ID
    private String content;
    private LocalDateTime postTime;
    private int likes;
    private int dislikes;

    public Post(String postId, int userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.postTime = LocalDateTime.now();
        this.likes = 0;
        this.dislikes = 0;
    }

    // Getters and Setters

    public String getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void addLike() {
        likes++;
    }

    public void addDislike() {
        dislikes++;
    }

    public String getRelativeTime() {
        Duration duration = Duration.between(postTime, LocalDateTime.now());
        long minutes = duration.toMinutes();
        long hours = duration.toHours();
        long days = duration.toDays();

        if (days > 0) {
            return days + " days ago";
        } else if (hours > 0) {
            return hours + " hr ago";
        } else if (minutes > 0) {
            return minutes + " min ago";
        } else {
            return "Just now";
        }
    }

    public String getFormattedPostTime() {
        // Format the post time in a human-readable format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        return postTime.format(formatter);
    }
}
