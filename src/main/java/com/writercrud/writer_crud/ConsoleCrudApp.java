package com.writercrud.writer_crud;

import com.writercrud.writer_crud.controller.LabelController;
import com.writercrud.writer_crud.model.Label;
import com.writercrud.writer_crud.model.PostStatus;
import com.writercrud.writer_crud.view.LabelView;
import com.writercrud.writer_crud.view.PostView;
import com.writercrud.writer_crud.view.WriterView;

import java.util.Scanner;

public class ConsoleCrudApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Label operations");
            System.out.println("2. Post operations");
            System.out.println("3. Writer operations");
            System.out.println("0. Exit from Application");
            System.out.print("Enter your choice: ");

            int mainChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            System.out.println();

            switch (mainChoice) {
                case 1:
                    LabelView labelView = new LabelView();
                    labelView.labelOperations();
                    break;
                case 2:
                    PostView postView = new PostView();
                    postView.postOperations();
                    break;
                case 3:
                    WriterView writerView = new WriterView();
                    writerView.writerOperations();
                    break;
                case 0:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println();
            }

        }
    }
}
