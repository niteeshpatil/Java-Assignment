package com.example.Java.Assignment.Controllers;

import com.example.Java.Assignment.Models.User;
import com.example.Java.Assignment.Services.FeedService;
import com.example.Java.Assignment.Services.UserService;
import com.example.Java.Assignment.Models.Post;
import com.example.Java.Assignment.Data.DataStorage;

import java.util.List;

public class CommandController {
    private UserService userService = new UserService();
    private FeedService feedService = new FeedService();

    public void handleCommand(String command) {
        String[] tokens = command.split(" ", 3);
        switch (tokens[0]) {
            case "RegisterUser":
                if (tokens.length < 3) {
                    System.out.println("Invalid command format for RegisterUser.");
                    break;
                }
                int userId = Integer.parseInt(tokens[1]);
                String username = tokens[2];
                System.out.println(userService.registerUser(userId, username));
                break;

            case "UploadPost":
                if (tokens.length < 3) {
                    System.out.println("Invalid command format for UploadPost.");
                    break;
                }
                String[] postTokens = tokens[2].split(" ", 2);
                if (postTokens.length < 2) {
                    System.out.println("Invalid post content.");
                    break;
                }
                int uploaderId = Integer.parseInt(tokens[1]);
                String postContent = postTokens[1].replaceAll("^\"|\"$", ""); // Remove quotes if present
                System.out.println(userService.uploadPost(uploaderId, postContent));
                break;

            case "InteractWithUser":
                if (tokens.length < 3) {
                    System.out.println("Invalid command format for InteractWithUser.");
                    break;
                }
                String interaction = tokens[1];
                String[] interactionTokens = tokens[2].split(" ");
                if (interactionTokens.length < 2) {
                    System.out.println("Invalid command format for InteractWithUser.");
                    break;
                }
                int user1 = Integer.parseInt(interactionTokens[0]);
                int user2 = Integer.parseInt(interactionTokens[1]);

                if (interaction.equalsIgnoreCase("FOLLOW")) {
                    System.out.println(userService.followUser(user1, user2));
                } else if (interaction.equalsIgnoreCase("UNFOLLOW")) {
                    System.out.println(userService.unfollowUser(user1, user2));
                } else {
                    System.out.println("Invalid interaction type.");
                }
                break;

            case "InteractWithPost":
                if (tokens.length < 3) {
                    System.out.println("Invalid command format for InteractWithPost.");
                    break;
                }
                String postInteraction = tokens[1];
                String[] myPostTokens = tokens[2].split(" ");
                if (myPostTokens.length < 2) {
                    System.out.println("Invalid command format for InteractWithPost.");
                    break;
                }
                int interactingUserId = Integer.parseInt(myPostTokens[0]);
                String postId = myPostTokens[1];

                if (postInteraction.equalsIgnoreCase("LIKE")) {
                    System.out.println(userService.likePost(interactingUserId, postId));
                } else if (postInteraction.equalsIgnoreCase("DISLIKE")) {
                    System.out.println(userService.dislikePost(interactingUserId, postId));
                } else {
                    System.out.println("Invalid interaction type.");
                }
                break;

            case "ShowFeed":
                if (tokens.length < 2) {
                    System.out.println("Invalid command format for ShowFeed.");
                    break;
                }
                int feedUserId = Integer.parseInt(tokens[1]);
                List<Post> feed = feedService.getFeedForUser(feedUserId);
                displayFeed(feedUserId, feed);
                break;

            default:
                System.out.println("Invalid command!");
        }
    }

    private void displayFeed(int userId, List<Post> feed) {
        for (Post post : feed) {
            String authorUsername = findUsernameByUserId(post.getUserId());
            System.out.println("UserName - " + authorUsername);
            System.out.println("# of Likes - " + post.getLikes());
            System.out.println("# of Dislikes - " + post.getDislikes());
            System.out.println("Post - " + post.getContent());
            System.out.println("Post time - " + post.getFormattedPostTime());
            System.out.println("Post time - " + post.getRelativeTime());
            System.out.println();
        }
    }

    private String findUsernameByUserId(int userId) {
        User user = DataStorage.users.get(userId);
        return (user != null) ? user.getUsername() : "Unknown User";
    }
}

