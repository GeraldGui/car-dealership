package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;

    ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle lot : inventory) {
            if (lot.getPrice() >= min && lot.getPrice() <= max) {
                vehicles.add(lot);
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle lot : inventory) {
            boolean makeMatch = make.isEmpty() || lot.getMake().equalsIgnoreCase(make);
            boolean modelMatch = model.isEmpty() || lot.getModel().equalsIgnoreCase(model);
            if (makeMatch && modelMatch) {
                vehicles.add(lot);
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle lot : inventory) {
            if (lot.getYear() >= min && lot.getYear() <= max) {
                vehicles.add(lot);
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle lot : inventory) {
            if (lot.getColor().equalsIgnoreCase(color)) {
                vehicles.add(lot);
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle lot : inventory) {
            if (lot.getOdometer() >= min && lot.getOdometer() <= max) {
                vehicles.add(lot);
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle lot : inventory) {
            if (lot.getVehicleType().equalsIgnoreCase(vehicleType)) {
                vehicles.add(lot);
            }
        }
        return vehicles;
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

}
