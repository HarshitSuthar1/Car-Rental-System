package com.library.carrentalsystem;
import java.util.*;
// Base Vehicle class (Abstraction & Encapsulation)
// Base Vehicle class (Abstraction & Encapsulation)
abstract class Vehicle {
    // Private fields (Encapsulation)
    private String vehicleId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    // Constructor
    public Vehicle(String vehicleId, String brand, String model, double basePricePerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }

    // Getters and Setters (Encapsulation)
    public String getVehicleId() { return vehicleId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getBasePricePerDay() { return basePricePerDay; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }

    // Abstract method (Abstraction - must be implemented by subclasses)
    public abstract double calculateRentalPrice(int days);

    // Concrete method
    public String getVehicleInfo() {
        return String.format("%s %s %s (ID: %s) - $%.2f/day",
                brand, model, this.getClass().getSimpleName(), vehicleId, basePricePerDay);
    }
}

// Car class (Inheritance)
class Car extends Vehicle {
    private int seatingCapacity;
    private String fuelType;

    public Car(String vehicleId, String brand, String model, double basePricePerDay,
               int seatingCapacity, String fuelType) {
        super(vehicleId, brand, model, basePricePerDay);
        this.seatingCapacity = seatingCapacity;
        this.fuelType = fuelType;
    }

    // Polymorphism - Override abstract method
    @Override
    public double calculateRentalPrice(int days) {
        double price = getBasePricePerDay() * days;
        // Premium fuel cars cost 20% more
        if (fuelType.equals("Premium")) {
            price *= 1.2;
        }
        return price;
    }

    public int getSeatingCapacity() { return seatingCapacity; }
    public String getFuelType() { return fuelType; }
}

// Motorcycle class (Inheritance)
class Motorcycle extends Vehicle {
    private int engineCapacity;
    private boolean hasHelmet;

    public Motorcycle(String vehicleId, String brand, String model, double basePricePerDay,
                      int engineCapacity, boolean hasHelmet) {
        super(vehicleId, brand, model, basePricePerDay);
        this.engineCapacity = engineCapacity;
        this.hasHelmet = hasHelmet;
    }

    // Polymorphism - Override abstract method
    @Override
    public double calculateRentalPrice(int days) {
        double price = getBasePricePerDay() * days;
        // Discount for motorcycles (cheaper than cars)
        price *= 0.8;
        // Extra charge if helmet included
        if (hasHelmet) {
            price += 5 * days; // $5 per day for helmet
        }
        return price;
    }

    public int getEngineCapacity() { return engineCapacity; }
    public boolean hasHelmet() { return hasHelmet; }
}

// Truck class (Inheritance)
class Truck extends Vehicle {
    private double loadCapacity;
    private boolean requiresCDL;

    public Truck(String vehicleId, String brand, String model, double basePricePerDay,
                 double loadCapacity, boolean requiresCDL) {
        super(vehicleId, brand, model, basePricePerDay);
        this.loadCapacity = loadCapacity;
        this.requiresCDL = requiresCDL;
    }

    // Polymorphism - Override abstract method
    @Override
    public double calculateRentalPrice(int days) {
        double price = getBasePricePerDay() * days;
        // Trucks are more expensive due to size and fuel consumption
        price *= 1.5;
        // CDL required trucks cost more
        if (requiresCDL) {
            price *= 1.3;
        }
        return price;
    }

    public double getLoadCapacity() { return loadCapacity; }
    public boolean requiresCDL() { return requiresCDL; }
}

// Customer class (Encapsulation)
class Customer {
    private String customerId;
    private String name;
    private String phoneNumber;
    private String licenseNumber;
    private boolean hasValidLicense;

    public Customer(String customerId, String name, String phoneNumber, String licenseNumber) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.licenseNumber = licenseNumber;
        this.hasValidLicense = true; // Assume valid for demo
    }

    // Getters
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getLicenseNumber() { return licenseNumber; }
    public boolean hasValidLicense() { return hasValidLicense; }

    public String getCustomerInfo() {
        return String.format("Customer: %s (ID: %s) | Phone: %s | License: %s",
                name, customerId, phoneNumber, licenseNumber);
    }
}

// Rental class (Association between Customer and Vehicle)
class Rental {
    private String rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private int rentalDays;
    private double totalCost;
    private String startDate;
    private String endDate;

    public Rental(String rentalId, Customer customer, Vehicle vehicle, int rentalDays,
                  String startDate, String endDate) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = vehicle.calculateRentalPrice(rentalDays);
    }

    // Getters
    public String getRentalId() { return rentalId; }
    public Customer getCustomer() { return customer; }
    public Vehicle getVehicle() { return vehicle; }
    public int getRentalDays() { return rentalDays; }
    public double getTotalCost() { return totalCost; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }

    public String getRentalDetails() {
        return String.format("""
            ═══════════════════════════════════════
            RENTAL AGREEMENT
            ═══════════════════════════════════════
            Rental ID: %s
            %s
            %s
            Duration: %d days (%s to %s)
            Total Cost: $%.2f
            ═══════════════════════════════════════
            """, rentalId, customer.getCustomerInfo(), vehicle.getVehicleInfo(),
                rentalDays, startDate, endDate, totalCost);
    }
}

// Interface for different rental services (Polymorphism)
interface RentalService {
    boolean rentVehicle(Customer customer, Vehicle vehicle, int days, String startDate, String endDate);
    boolean returnVehicle(String rentalId);
    void displayAvailableVehicles();
}

// Car Rental System class (Main business logic)
class CarRentalSystem implements RentalService {
    private java.util.List<Vehicle> vehicles;
    private java.util.List<Customer> customers;
    private java.util.List<Rental> rentals;
    private int rentalCounter;

    public CarRentalSystem() {
        vehicles = new java.util.ArrayList<>();
        customers = new java.util.ArrayList<>();
        rentals = new java.util.ArrayList<>();
        rentalCounter = 1;
        initializeVehicles();
    }

    // Initialize sample vehicles
    private void initializeVehicles() {
        vehicles.add(new Car("C001", "Toyota", "Camry", 50.0, 5, "Regular"));
        vehicles.add(new Car("C002", "BMW", "X5", 120.0, 7, "Premium"));
        vehicles.add(new Car("C003", "Honda", "Civic", 40.0, 4, "Regular"));
        vehicles.add(new Motorcycle("M001", "Harley-Davidson", "Street 750", 80.0, 750, true));
        vehicles.add(new Motorcycle("M002", "Yamaha", "R15", 35.0, 150, false));
        vehicles.add(new Truck("T001", "Ford", "F-150", 100.0, 2.5, false));
        vehicles.add(new Truck("T002", "Volvo", "VNL", 200.0, 40.0, true));
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void displayAvailableVehicles() {
        System.out.println("\n🚗 AVAILABLE VEHICLES 🚗");
        System.out.println("═══════════════════════════════════════");
        boolean hasAvailable = false;

        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable()) {
                System.out.println("✅ " + vehicle.getVehicleInfo());
                hasAvailable = true;
            }
        }

        if (!hasAvailable) {
            System.out.println("❌ No vehicles available at the moment.");
        }
        System.out.println("═══════════════════════════════════════");
    }

    @Override
    public boolean rentVehicle(Customer customer, Vehicle vehicle, int days, String startDate, String endDate) {
        if (!vehicle.isAvailable()) {
            System.out.println("❌ Vehicle is not available for rent.");
            return false;
        }

        if (!customer.hasValidLicense()) {
            System.out.println("❌ Customer does not have a valid license.");
            return false;
        }

        // Create rental
        String rentalId = "R" + String.format("%03d", rentalCounter++);
        Rental rental = new Rental(rentalId, customer, vehicle, days, startDate, endDate);

        // Update vehicle availability
        vehicle.setAvailable(false);

        // Add rental to system
        rentals.add(rental);

        System.out.println("✅ RENTAL SUCCESSFUL!");
        System.out.println(rental.getRentalDetails());

        return true;
    }

    @Override
    public boolean returnVehicle(String rentalId) {
        for (Rental rental : rentals) {
            if (rental.getRentalId().equals(rentalId)) {
                rental.getVehicle().setAvailable(true);
                System.out.println("✅ Vehicle returned successfully!");
                System.out.println("Thank you, " + rental.getCustomer().getName() + "!");
                return true;
            }
        }
        System.out.println("❌ Rental ID not found.");
        return false;
    }

    public void displayAllRentals() {
        System.out.println("\n📋 ACTIVE RENTALS 📋");
        System.out.println("═══════════════════════════════════════");

        if (rentals.isEmpty()) {
            System.out.println("No active rentals.");
        } else {
            for (Rental rental : rentals) {
                if (!rental.getVehicle().isAvailable()) {
                    System.out.println(rental.getRentalDetails());
                }
            }
        }
    }

    public Vehicle findVehicleById(String vehicleId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equals(vehicleId)) {
                return vehicle;
            }
        }
        return null;
    }
}

// Main class with interactive user input
public class CarRentalSystemDemo {
    private static java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static CarRentalSystem rentalSystem = new CarRentalSystem();
    private static int customerCounter = 1;

    public static void main(String[] args) {
        System.out.println("🎉 WELCOME TO CAR RENTAL SYSTEM 🎉");
        System.out.println("═══════════════════════════════════════");

        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice (1-6): ");

            switch (choice) {
                case 1:
                    viewAvailableVehicles();
                    break;
                case 2:
                    rentVehicle();
                    break;
                case 3:
                    returnVehicle();
                    break;
                case 4:
                    viewActiveRentals();
                    break;
                case 5:
                    addNewCustomer();
                    break;
                case 6:
                    System.out.println("Thank you for using Car Rental System! 🚗");
                    System.exit(0);
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void displayMenu() {
        System.out.println("\n📋 MAIN MENU 📋");
        System.out.println("═══════════════════════════════════════");
        System.out.println("1. 🚗 View Available Vehicles");
        System.out.println("2. 🔑 Rent a Vehicle");
        System.out.println("3. 🔄 Return a Vehicle");
        System.out.println("4. 📊 View Active Rentals");
        System.out.println("5. 👤 Add New Customer");
        System.out.println("6. 🚪 Exit");
        System.out.println("═══════════════════════════════════════");
    }

    private static void viewAvailableVehicles() {
        rentalSystem.displayAvailableVehicles();
    }

    private static void rentVehicle() {
        System.out.println("\n🔑 RENT A VEHICLE 🔑");
        System.out.println("═══════════════════════════════════════");

        // Show available vehicles first
        rentalSystem.displayAvailableVehicles();

        // Get customer details
        System.out.println("\n--- Customer Information ---");
        String name = getStringInput("Enter customer name: ");
        String phone = getStringInput("Enter phone number: ");
        String license = getStringInput("Enter license number: ");

        // Create customer
        String customerId = "CUST" + String.format("%03d", customerCounter++);
        Customer customer = new Customer(customerId, name, phone, license);
        rentalSystem.addCustomer(customer);

        // Get vehicle selection
        String vehicleId = getStringInput("Enter vehicle ID to rent: ").toUpperCase();
        Vehicle vehicle = rentalSystem.findVehicleById(vehicleId);

        if (vehicle == null) {
            System.out.println("❌ Vehicle not found!");
            return;
        }

        if (!vehicle.isAvailable()) {
            System.out.println("❌ Vehicle is not available!");
            return;
        }

        // Get rental details
        int days = getIntInput("Enter number of rental days: ");
        String startDate = getStringInput("Enter start date (YYYY-MM-DD): ");
        String endDate = getStringInput("Enter end date (YYYY-MM-DD): ");

        // Show price preview
        double totalCost = vehicle.calculateRentalPrice(days);
        System.out.printf("\n💰 Total cost for %d days: $%.2f%n", days, totalCost);

        String confirm = getStringInput("Confirm rental? (yes/no): ");
        if (confirm.toLowerCase().startsWith("y")) {
            rentalSystem.rentVehicle(customer, vehicle, days, startDate, endDate);
        } else {
            System.out.println("❌ Rental cancelled.");
        }
    }

    private static void returnVehicle() {
        System.out.println("\n🔄 RETURN A VEHICLE 🔄");
        System.out.println("═══════════════════════════════════════");

        // Show active rentals first
        rentalSystem.displayAllRentals();

        String rentalId = getStringInput("Enter rental ID to return: ").toUpperCase();
        rentalSystem.returnVehicle(rentalId);
    }

    private static void viewActiveRentals() {
        rentalSystem.displayAllRentals();
    }

    private static void addNewCustomer() {
        System.out.println("\n👤 ADD NEW CUSTOMER 👤");
        System.out.println("═══════════════════════════════════════");

        String name = getStringInput("Enter customer name: ");
        String phone = getStringInput("Enter phone number: ");
        String license = getStringInput("Enter license number: ");

        String customerId = "CUST" + String.format("%03d", customerCounter++);
        Customer customer = new Customer(customerId, name, phone, license);
        rentalSystem.addCustomer(customer);

        System.out.println("✅ Customer added successfully!");
        System.out.println(customer.getCustomerInfo());
    }

    // Helper method to get string input
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Helper method to get integer input with validation
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("❌ Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }
}