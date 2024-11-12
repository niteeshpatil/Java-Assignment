package com.example.Java.Assignment;

import com.example.Java.Assignment.Controllers.CommandController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaAssignmentApplication {

	public class Main {
		public static void main(String[] args) {
			CommandController controller = new CommandController();

			// Register Users
			controller.handleCommand("RegisterUser 1 Akash");
			controller.handleCommand("RegisterUser 2 Hemant");

			// Upload Posts
			controller.handleCommand("UploadPost 1 \"This is my first post. My name is xyz\"");
			controller.handleCommand("UploadPost 1 \"I work at flipkart as a SDE1\"");
			controller.handleCommand("UploadPost 2 \"I too worked at flipkart as a SDE1\"");

			// Follow/Unfollow
			controller.handleCommand("InteractWithUser FOLLOW 2 1");
			controller.handleCommand("InteractWithUser UNFOLLOW 2 1");

			// Like/Dislike Posts
			controller.handleCommand("InteractWithPost LIKE 2 001");
			controller.handleCommand("InteractWithPost DISLIKE 2 001");

			// Show Feed
			controller.handleCommand("ShowFeed 1");
			controller.handleCommand("ShowFeed 2");
		}
	}

}
