package com.pluralsight;

import java.io.*;

public class DealershipFileManager {

public Dealership getDealerShip() {
    Dealership dealership = null;

    try {
        String file = "dealership.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String[] dealershipToken =  bufferedReader.readLine().split("\\|");

        String name = dealershipToken[0];
        String address = dealershipToken[1];
        String phone = dealershipToken[2];

        dealership = new Dealership(name, address, phone);

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] carTokens = line.split("\\|");

            if (line.isEmpty()) continue;

            int vin = Integer.parseInt(carTokens[0]);
            int year = Integer.parseInt(carTokens[1]);
            String make = carTokens[2];
            String model = carTokens[3];
            String vehicleType = carTokens[4];
            String color = carTokens[5];
            int odometer = Integer.parseInt(carTokens[6]);
            double price = Double.parseDouble(carTokens[7]);

            dealership.inventory.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
        }
        bufferedReader.close();
    } catch (IOException e) {
        System.out.println("Not able to read file.");
    }
    return dealership;
}

public void saveDealership(Dealership dealership) {
try {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("dealership.csv"));

    bufferedWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());

    for (Vehicle v : dealership.getAllVehicles()) {
        bufferedWriter.newLine();
        bufferedWriter.write(v.getVin() + "|" + v.getYear() + "|" + v.getMake() + "|" + v.getModel() + "|" + v.getVehicleType() + "|" + v.getColor() + "|" + v.getOdometer() + "|" + v.getPrice());
    }
    bufferedWriter.close();
} catch (IOException e) {
    System.out.println("Error saving dealership.");
}
}
}
