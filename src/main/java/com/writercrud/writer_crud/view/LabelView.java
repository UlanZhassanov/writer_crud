package com.writercrud.writer_crud.view;

import com.writercrud.writer_crud.controller.LabelController;
import com.writercrud.writer_crud.model.Label;
import com.writercrud.writer_crud.model.PostStatus;

import java.util.Scanner;

public class LabelView {

    public void labelOperations(){
        LabelController labelController = new LabelController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create Label");
            System.out.println("2. Display All Labels");
            System.out.println("3. Display Label by ID");
            System.out.println("4. Update Label");
            System.out.println("5. Delete Label");
            System.out.println("0. Exit from Label operations");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    Label label = new Label(name, PostStatus.ACTIVE);
                    labelController.saveLabel(label);
                    System.out.println("Label saved to json file");
                    break;
                case 2:
                    System.out.println("All labels:");
                    System.out.println(labelController.getAllLabels());
                    break;
                case 3:
                    System.out.print("Enter id to find: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Label with id " + id + ":");
                    System.out.println(labelController.getLabelById(id));
                    break;
                case 4:
                    System.out.print("Enter id to update: ");
                    int labelUpdateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(labelController.getLabelById(labelUpdateId));

                    System.out.print("Enter new name: ");
                    String labelUpdateName = scanner.nextLine();
                    Label labelUpdate = new Label(labelUpdateId, labelUpdateName, PostStatus.ACTIVE);

                    System.out.println("Updated label " + labelUpdateId + ":");
                    System.out.println(labelController.updateLabel(labelUpdate));
                    break;
                case 5:
                    System.out.print("Enter id to delete: ");
                    int labelDeleteId = scanner.nextInt();
                    scanner.nextLine();
                    labelController.deleteLabel(labelDeleteId);
                    System.out.println("Label deleted");
                    break;
                case 0:
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
