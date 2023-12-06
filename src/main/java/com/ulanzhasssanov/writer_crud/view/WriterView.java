package com.ulanzhasssanov.writer_crud.view;

import com.ulanzhasssanov.writer_crud.controller.PostController;
import com.ulanzhasssanov.writer_crud.controller.WriterController;
import com.ulanzhasssanov.writer_crud.enums.Status;
import com.ulanzhasssanov.writer_crud.model.Post;
import com.ulanzhasssanov.writer_crud.model.Writer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WriterView {

    public void writerOperations(){
        WriterController writerController = new WriterController();
        Scanner scanner = new Scanner(System.in);
        LocalDateTime dateTimeNow = LocalDateTime.now();

        while (true) {
            System.out.println("1. Create Writer");
            System.out.println("2. Display All Writers");
            System.out.println("3. Display Writer by ID");
            System.out.println("4. Update Writer");
            System.out.println("5. Delete Writer");
            System.out.println("0. Exit from Writer operations");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            System.out.println();

            switch (choice) {
                case 1:
                    System.out.print("Enter firstName: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter lastName: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter posts (enter post ids with commas): ");
                    String posts = scanner.nextLine();

                    List<Post> savePosts = getWriterPosts(posts);

                    Writer writer = new Writer(firstName, lastName, savePosts, Status.ACTIVE);
                    writerController.saveWriter(writer);
                    System.out.println("Writer saved to json file");
                    System.out.println();
                    break;
                case 2:
                    System.out.println("All writers:");
                    System.out.println(writerController.getAllWriters());
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter id to find: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Writer with id " + id + ":");
                    System.out.println(writerController.getWriterById(id));
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Enter id to update: ");
                    int writerUpdateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(writerController.getWriterById(writerUpdateId));

                    System.out.print("Enter new firstName: ");
                    String writerUpdateFirstName = scanner.nextLine();
                    System.out.print("Enter new lastName: ");
                    String writerUpdateLastName = scanner.nextLine();
                    System.out.print("Enter posts (enter post ids with commas): ");
                    String writerUpdatePostIds = scanner.nextLine();
                    List<Post> writerUpdatePosts = getWriterPosts(writerUpdatePostIds);
                    Writer writerUpdate = writerController.getWriterById(writerUpdateId);

                    writerUpdate.setFirstName(writerUpdateFirstName);
                    writerUpdate.setLastName(writerUpdateLastName);
                    writerUpdate.setPosts(writerUpdatePosts);

                    System.out.println("Updated writer " + writerUpdateId + ":");
                    System.out.println(writerController.updateWriter(writerUpdate));
                    System.out.println();
                    break;
                case 5:
                    System.out.print("Enter id to delete: ");
                    int writerDeleteId = scanner.nextInt();
                    scanner.nextLine();

                    writerController.deleteWriter(writerDeleteId);
                    System.out.println("Writer deleted");
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

    public List<Post> getWriterPosts(String postIds){
        PostController postController = new PostController();
        List<Post> postList = new ArrayList<>();

        boolean isValidNumbersSeparatedByCommas = Pattern.compile("\\d+(,\\d+)*").matcher(postIds).matches();

        if (isValidNumbersSeparatedByCommas){
            int[] ids = Arrays.stream(postIds.split(",")).mapToInt(Integer::parseInt).toArray();
            for (int id:ids) {
                postList.add(postController.getPostById(id));
            }
            return postList;
        }
        return null;
    }

}
