package com.ulanzhasssanov.writer_crud.view;

import com.ulanzhasssanov.writer_crud.controller.LabelController;
import com.ulanzhasssanov.writer_crud.enums.Status;
import com.ulanzhasssanov.writer_crud.model.Label;

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
            System.out.println();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    Label label = new Label(name, Status.ACTIVE);
                    labelController.saveLabel(label);
                    System.out.println("Label saved to json file");
                    System.out.println();
                    break;
                case 2:
                    System.out.println("All labels:");
                    System.out.println(labelController.getAllLabels());
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter id to find: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Label with id " + id + ":");
                    System.out.println(labelController.getLabelById(id));
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Enter id to update: ");
                    int labelUpdateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(labelController.getLabelById(labelUpdateId));

                    System.out.print("Enter new name: ");
                    String labelUpdateName = scanner.nextLine();
                    Label labelUpdate = new Label(labelUpdateId, labelUpdateName, Status.ACTIVE);

                    System.out.println("Updated label " + labelUpdateId + ":");
                    System.out.println(labelController.updateLabel(labelUpdate));
                    System.out.println();
                    break;
                case 5:
                    System.out.print("Enter id to delete: ");
                    int labelDeleteId = scanner.nextInt();
                    scanner.nextLine();
                    labelController.deleteLabel(labelDeleteId);
                    System.out.println("Label deleted");
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

}
