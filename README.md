# 🚗 Car Rental System (Java OOP Project)

A **fully interactive console-based Car Rental System** built using **Core Java** that demonstrates real-world application of **Object-Oriented Programming (OOP)** concepts like **abstraction**, **inheritance**, **encapsulation**, and **polymorphism**. This project simulates a real-life rental workflow — from viewing and renting vehicles to calculating dynamic rental prices and managing returns.

---

## 🎯 Project Goals

- Apply OOP principles in a practical domain
- Simulate real-world vehicle rental management
- Build a clean, modular, and testable backend logic using Core Java

---

## 🛠️ Key Features

- ✅ **Add and manage customers** with license validation
- ✅ **Rent vehicles** (cars, motorcycles, trucks) with price variation by type
- ✅ **Return rented vehicles** with status update
- ✅ **Price calculation** based on rental days and vehicle type
- ✅ **View active rentals** and availability of vehicles
- ✅ **Formatted console output** for a smooth user experience

---

## 🧠 Core OOP Concepts Applied

| Concept         | Usage Example                                         |
|-----------------|-------------------------------------------------------|
| **Abstraction** | `Vehicle` is an abstract class with abstract methods |
| **Encapsulation** | Private fields with public getters/setters         |
| **Inheritance** | `Car`, `Truck`, `Motorcycle` extend `Vehicle`        |
| **Polymorphism** | Overridden `calculateRentalPrice()` methods         |
| **Interface** | `RentalService` interface defines service operations   |
| **Association** | Between `Customer` and `Vehicle` through `Rental`    |

---

## 📁 Project Structure

├── Vehicle.java (abstract base class)
├── Car.java / Motorcycle.java / Truck.java
├── Customer.java
├── Rental.java
├── CarRentalSystem.java (rental service logic)
├── CarRentalSystemDemo.java (main method with user interface)

### 💻 Requirements:
- Java 8 or above
- Command-line terminal (Git Bash, CMD, or IDE terminal)

### 🧪 Run the app:
```bash
javac CarRentalSystemDemo.java
java CarRentalSystemDemo
No external dependencies required – it's a pure Java application!

📸 Console Preview
🎉 WELCOME TO CAR RENTAL SYSTEM 🎉
═══════════════════════════════════════
1. 🚗 View Available Vehicles
2. 🔑 Rent a Vehicle
3. 🔄 Return a Vehicle
4. 📊 View Active Rentals
5. 👤 Add New Customer
6. 🚪 Exit
🧪 Sample Rental Flow
Add a new customer

Choose a vehicle to rent (e.g., a car or motorcycle)

Enter rental duration, start and end date

System calculates and displays total cost

Vehicle marked as unavailable until returned

🧑‍💻 Author
Developed with 💻 by Harshit Suthar
📫 Email: palechaharshit@gmail.com
🔗 GitHub: HarshitSuthar1

🙌 Contributions
Contributions, ideas, and suggestions are welcome!
Feel free to open issues or fork the repo and create a PR.

⭐ Star the Repository
If you like this project or learned something new, consider giving it a ⭐ on GitHub. It motivates me to build more open-source projects like this!

---

Would you also like:
- A `LICENSE` file (MIT or Apache)?
- A GitHub Actions badge or README image header?

Let me know and I can include it in the final version.
