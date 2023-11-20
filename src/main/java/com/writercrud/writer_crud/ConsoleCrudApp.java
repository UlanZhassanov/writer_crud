package com.writercrud.writer_crud;

import com.writercrud.writer_crud.controller.LabelController;
import com.writercrud.writer_crud.model.Label;
import com.writercrud.writer_crud.model.PostStatus;

import java.util.Scanner;

public class ConsoleCrudApp {
    public static void main(String[] args) {
        LabelController labelController = new LabelController();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create Label");
            System.out.println("2. Display All Labels");
            System.out.println("3. Update Label");
            System.out.println("4. Delete Label");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.println(name);
                    Label label = new Label(id, name, PostStatus.ACTIVE);
                    labelController.saveLabel(label);
                    break;
                case 2:
//                    labelController.displayAllPersons();
                    break;
                case 3:
                    System.out.print("Enter index to update: ");
                    int indexUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new age: ");
                    int newAge = scanner.nextInt();
//                    labelController.updatePerson(indexUpdate, newName, newAge);
                    break;
                case 4:
                    System.out.print("Enter index to delete: ");
                    int indexDelete = scanner.nextInt();
//                    labelController.deletePerson(indexDelete);
                    break;
                case 0:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
