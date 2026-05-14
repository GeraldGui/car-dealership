package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Dealership dealership = null;

    public UserInterface() {
    }

    public void display() {
        init();
        Scanner scanner = new Scanner(System.in);

        int choice = -1;
        while (choice != 99) {
            System.out.println("\nWelcome to the Dealership!");
            System.out.println("1. Find vehicles within a price range");
            System.out.println("2. Find vehicles by make / model");
            System.out.println("3. Find vehicles by year range");
            System.out.println("4. Find vehicles by color");
            System.out.println("5. Find vehicles by mileage range");
            System.out.println("6. Find vehicles by type (car, truck, SUV, van)");
            System.out.println("7. List ALL vehicles ");
            System.out.println("8. Add a vehicle ");
            System.out.println("9. Remove a vehicle");
            System.out.println("99. Quit");
            System.out.print("Your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter 1, 2, 3...");
                scanner.nextLine();                 // discard bad input
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();                     // clear newline

            switch (choice) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 99 -> System.out.println("Thank you for browsing!");
                default -> System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }

    public void processGetByPriceRequest() {

    }

    public void processGetByMakeModelRequest() {

    }

    public void processGetByYearRequest() {

    }

    public void processGetByColorRequest() {

    }

    public void processGetByMileageRequest() {

    }

    public void processGetByVehicleTypeRequest() {

    }

    public void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());

    }

    public void processAddVehicleRequest() {

    }

    public void processRemoveVehicleRequest() {

    }

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealerShip();
    }

    private void displayVehicles(List<Vehicle> vehicle) {
        for (Vehicle lot : vehicle) {
            System.out.println(lot);
        }

    }
}
