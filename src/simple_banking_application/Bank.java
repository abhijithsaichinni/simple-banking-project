package simple_banking_application;

import java.util.*;

class User {
    private int userId;
    private String name;
    private double balance;

    public User(String name) {
        this.userId = generateRandomUserId();
        this.name = name;
        this.balance = 0.0;
    }

    private int generateRandomUserId() {
        Random random = new Random();
        return 10000 + random.nextInt(90000); // Generates a random 5-digit number
    }
    
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("$" + amount + " has been deposited to your account. New Balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            System.out.println("$" + amount + " has been withdrawn from your account. New Balance: $" + balance);
        }
    }

    public void showStatus() {
        System.out.println("\nUser ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Balance: $" + balance);
    }
}

public class Bank {
    static List<User> users = new ArrayList<>();

    public static void createUser(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.next();
        User user = new User(name);
        users.add(user);
        System.out.println("Account created successfully. Your User ID is: " + user.getUserId());
    }

    public static User findUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        System.out.println("User not found.");
        return null;
    }

    public static void checkBalance(Scanner scanner) {
        System.out.print("Enter your User ID: ");
        int userId = scanner.nextInt();
        User user = findUserById(userId);
        if (user != null) {
            user.showStatus();
        }
    }

    public static void deposit(Scanner scanner) {
        System.out.print("Enter your User ID: ");
        int userId = scanner.nextInt();
        User user = findUserById(userId);
        if (user != null) {
            System.out.print("Enter the amount to deposit: $");
            double amount = scanner.nextDouble();
            user.deposit(amount);
        }
    }

    public static void withdraw(Scanner scanner) {
        System.out.print("Enter your User ID: ");
        int userId = scanner.nextInt();
        User user = findUserById(userId);
        if (user != null) {
            System.out.print("Enter the amount to withdraw: $");
            double amount = scanner.nextDouble();
            user.withdraw(amount);
        }
    }

    public static void exit() {
        System.out.println("Thank you for banking with us. Have a great day!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option != 5) {
            System.out.println("Welcome to the Bank of Java");
            System.out.println("1. Create Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");
            System.out.print("Enter an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    createUser(scanner);
                    break;
                case 2:
                    checkBalance(scanner);
                    break;
                case 3:
                    deposit(scanner);
                    break;
                case 4:
                    withdraw(scanner);
                    break;
                case 5:
                    exit();
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
        scanner.close();
    }
}
