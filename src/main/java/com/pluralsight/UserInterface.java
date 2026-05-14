package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Dealership dealership = null;

    public UserInterface() {
    }

    public void display() {
        init();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to " + dealership.getName() + ", our address is " + dealership.getAddress() + ", our phone is " + dealership.getPhone() + "\n");

        int choice = -1;
        while (choice != 99) {
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
                case 1 -> processGetByPriceRequest(scanner);
                case 2 -> processGetByMakeModelRequest(scanner);
                case 3 -> processGetByYearRequest(scanner);
                case 4 -> processGetByColorRequest(scanner);
                case 5 -> processGetByMileageRequest(scanner);
                case 6 -> processGetByVehicleTypeRequest(scanner);
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest(scanner);
                case 9 -> processRemoveVehicleRequest(scanner);
                case 99 -> System.out.println("Thank you for browsing!");
                default -> System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }

    public void processGetByPriceRequest(Scanner scanner) {
        double min = -1;
        double max = -1;


        while (min == -1) {
            try {
                System.out.print("Enter your minimum price: ");
                min = scanner.nextDouble();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }

        while (max == -1) {
            try {
                System.out.print("Enter your max price: ");
                max = scanner.nextDouble();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
            displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    public void processGetByMakeModelRequest(Scanner scanner) {
        String make = "";
        String model = "";

        while (make.isEmpty() && model.isEmpty()) {
            System.out.print("Enter make (or press enter to skip): ");
            make = scanner.nextLine().trim();

            System.out.print("Enter model (or press enter to skip): ");
            model = scanner.nextLine().trim();

            if (make.isEmpty() && model.isEmpty()) {
                System.out.println("Enter at least a make or model");
            }
        }
            displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    public void processGetByYearRequest(Scanner scanner) {
        int min = -1;
        int max = -1;


        while (min == -1) {
            try {
                System.out.print("Enter the year to start at: ");
                min = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }

        while (max == -1) {
            try {
                System.out.print("Enter the year to end at: ");
                max = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
            displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    public void processGetByColorRequest(Scanner scanner) {
        String color = "";

        while (color.isEmpty()) {
            System.out.print("Enter the color you want: ");
            color = scanner.nextLine().trim();

            if (color.isEmpty()) {
                System.out.print("Enter at least a color.");
            }
        }
            displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest(Scanner scanner) {
        int min = -1;
        int max = -1;


        while (min == -1) {
            try {
                System.out.print("Enter minium mileage: ");
                min = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }

        while (max == -1) {
            try {
                System.out.print("Enter maximum mileage: ");
                max = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
            displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    public void processGetByVehicleTypeRequest(Scanner scanner) {
        String type = "";

        while (type.isEmpty()) {
            System.out.print("Enter the type of vehicle you want: ");
            type = scanner.nextLine().trim();

            if (type.isEmpty()) {
                System.out.print("Enter at least a type of vehicle.");
            }
        }
            displayVehicles(dealership.getVehiclesByType(type));
    }

    public void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest(Scanner scanner) {
        int vin;
        int year;
        int odometer;
        double price;
        String make;
        String model;
        String color;
        String vehicleType;

        vin = getIntInput(scanner,"Enter the vehicle Vin #: ");

        year = getIntInput(scanner, "Enter vehicle year: ");

        System.out.print("Enter vehicle make: ");
        make = scanner.nextLine();

        System.out.print("Enter vehicle model: ");
        model = scanner.nextLine();

        System.out.print("Enter vehicle vehicle type: ");
        vehicleType = scanner.nextLine();

        System.out.print("Enter vehicle color: ");
        color = scanner.nextLine();

        odometer = getIntInput(scanner, "Enter vehicle mileage: ");

        price = getDoubleInput(scanner, "Enter vehicle price: ");

        try {
            Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType,color, odometer, price);
            dealership.addVehicle(newVehicle);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("dealership.csv", true));
            bufferedWriter.write(vin + "|" + year + "|" + make + "|" + model + "|" + vehicleType + "|" + color + "|" + odometer + "|" + price);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error in writer.");
        }
    }

    public void processRemoveVehicleRequest(Scanner scanner) {
        int vin = getIntInput(scanner, "Enter the VIN of the vehicle to remove: ");

        Vehicle toRemove = null;

        for (Vehicle lot : dealership.inventory) {
            if (lot.getVin() == vin) {
                toRemove = lot;
                break;
            }
        }

            if (toRemove != null) {
                dealership.removeVehicle(toRemove);
                DealershipFileManager fileManager = new DealershipFileManager();
                fileManager.saveDealership(dealership);
                System.out.println("Vehicle has been removed");
            } else {
                System.out.println("Vehicle could not be found");
            }
    }

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealerShip();
    }

    private void displayVehicles(List<Vehicle> vehicle) {
        System.out.println("| VIN    | Year | Make       | Model      | Color    | Type   | Mileage    | Price      |");
        for (Vehicle lot : vehicle) {
            System.out.println(lot);
        }
    }

    private int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);

                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }
    }

    private double getDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);

                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }
    }
}
