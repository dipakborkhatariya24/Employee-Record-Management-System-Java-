package controller;

import database.EmployeeDB;
import model.Employee;
import view.EmployeeView;

import java.util.List;

public class EmployeeController {
    private final EmployeeDB database;
    private final EmployeeView view;

    public EmployeeController(EmployeeDB database, EmployeeView view) {
        this.database = database;
        this.view = view;
    }

    public void start() {
        int choice;
        do {
            choice = view.showMenu();
            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> updateSalary();
                case 4 -> deleteEmployee();
                case 5 -> searchEmployee();
                case 6 -> view.showMessage("Exiting program.");
                default -> view.showMessage("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    private void addEmployee() {
        int id = view.getInt("Enter ID: ");
        String name = view.getString("Enter Name: ");
        int age = view.getInt("Enter Age: ");
        String dept = view.getString("Enter Department: ");
        double salary = view.getDouble("Enter Salary: ");
        Employee emp = new Employee(id, name, age, dept, salary);
        if (database.addEmployee(emp)) view.showMessage("Employee added successfully.");
    }

    private void viewEmployees() {
        List<Employee> list = database.getAllEmployees();
        view.showMessage("\n--- Employee Records ---");
        for (Employee emp : list) {
            System.out.printf("ID: %d | Name: %s | Age: %d | Department: %s | Salary: %.2f\n",
                    emp.getId(), emp.getName(), emp.getAge(), emp.getDepartment(), emp.getSalary());
        }
    }

    private void updateSalary() {
        int id = view.getInt("Enter Employee ID to update salary: ");
        double salary = view.getDouble("Enter new Salary: ");
        if (database.updateSalary(id, salary)) view.showMessage("Salary updated successfully.");
        else view.showMessage("Employee not found.");
    }

    private void deleteEmployee() {
        int id = view.getInt("Enter Employee ID to delete: ");
        if (database.deleteEmployee(id)) view.showMessage("Employee deleted successfully.");
        else view.showMessage("Employee not found.");
    }

    private void searchEmployee() {
        String keyword = view.getString("Enter name or department to search: ");
        List<Employee> results = database.searchEmployees(keyword);
        if (results.isEmpty()) {
            view.showMessage("No employees found matching the keyword.");
        } else {
            view.showMessage("\n--- Search Results ---");
            for (Employee emp : results) {
                System.out.printf("ID: %d | Name: %s | Age: %d | Department: %s | Salary: %.2f\n",
                        emp.getId(), emp.getName(), emp.getAge(), emp.getDepartment(), emp.getSalary());
            }
        }
    }

}
