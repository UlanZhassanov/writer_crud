package com.writercrud.writer_crud.view;

import com.writercrud.writer_crud.controller.LabelController;
import com.writercrud.writer_crud.controller.PostController;
import com.writercrud.writer_crud.model.Label;
import com.writercrud.writer_crud.model.Post;
import com.writercrud.writer_crud.model.PostStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PostView {

    public void postOperations(){
        PostController postController = new PostController();
        Scanner scanner = new Scanner(System.in);
        LocalDateTime dateTimeNow = LocalDateTime.now();

        while (true) {
            System.out.println("1. Create Post");
            System.out.println("2. Display All Posts");
            System.out.println("3. Display Post by ID");
            System.out.println("4. Update Post");
            System.out.println("5. Delete Post");
            System.out.println("0. Exit from Post operations");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            System.out.println();

            switch (choice) {
                case 1:
                    System.out.print("Enter content: ");
                    String content = scanner.nextLine();
                    System.out.print("Enter labels (enter label ids with commas): ");
                    String labels = scanner.nextLine();

                    List<Label> saveLabels = getPostLabels(labels);

                    Post post = new Post(content, dateTimeNow, dateTimeNow, saveLabels, PostStatus.ACTIVE);
                    postController.savePost(post);
                    System.out.println("Post saved to json file");
                    System.out.println();
                    break;
                case 2:
                    System.out.println("All posts:");
                    System.out.println(postController.getAllPosts());
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter id to find: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Post with id " + id + ":");
                    System.out.println(postController.getPostById(id));
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Enter id to update: ");
                    int postUpdateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(postController.getPostById(postUpdateId));

                    System.out.print("Enter new content: ");
                    String postUpdateContent = scanner.nextLine();
                    System.out.print("Enter labels (enter label ids with commas): ");
                    String postUpdateLabelIds = scanner.nextLine();
                    List<Label> postUpdateLabels = getPostLabels(postUpdateLabelIds);
                    Post postUpdate = postController.getPostById(postUpdateId);

                    postUpdate.setContent(postUpdateContent);
                    postUpdate.setLabels(postUpdateLabels);
                    postUpdate.setUpdated(dateTimeNow);

                    System.out.println("Updated post " + postUpdateId + ":");
                    System.out.println(postController.updatePost(postUpdate));
                    System.out.println();
                    break;
                case 5:
                    System.out.print("Enter id to delete: ");
                    int postDeleteId = scanner.nextInt();
                    scanner.nextLine();

                    postController.deletePost(postDeleteId);
                    System.out.println("Post deleted");
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println();
            }
        }
    }

    public List<Label> getPostLabels(String labelIds){
        LabelController labelController = new LabelController();
        List<Label> labelList = new ArrayList<>();

        boolean isValidNumbersSeparatedByCommas = Pattern.compile("\\d+(,\\d+)*").matcher(labelIds).matches();

        if (isValidNumbersSeparatedByCommas){
            int[] ids = Arrays.stream(labelIds.split(",")).mapToInt(Integer::parseInt).toArray();
            for (int id:ids) {
                labelList.add(labelController.getLabelById(id));
            }
            return labelList;
        }
        return null;
    }

}
