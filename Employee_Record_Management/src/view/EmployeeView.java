package view;

import java.util.Scanner;

public class EmployeeView {

    private final Scanner scanner = new Scanner(System.in);

    public int showMenu() {
        System.out.println("\n===== Employee Management System =====");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Update Employee Salary");
        System.out.println("4. Delete Employee");
        System.out.println("5. Search Employee by Name or Department");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");
        return scanner.nextInt();
    }

    public int getInt(String message) {
        System.out.print(message);
        return scanner.nextInt();
    }

    public double getDouble(String message) {
        System.out.print(message);
        return scanner.nextDouble();
    }

    public String getString(String message) {
        System.out.print(message);
        scanner.nextLine();
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
