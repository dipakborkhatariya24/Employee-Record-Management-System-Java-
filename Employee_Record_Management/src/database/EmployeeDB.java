package database;

import model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employees_DB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public boolean addEmployee(Employee emp) {
        String sql = "INSERT INTO employees (id, name, age, department, salary) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emp.getId());
            stmt.setString(2, emp.getName());
            stmt.setInt(3, emp.getAge());
            stmt.setString(4, emp.getDepartment());
            stmt.setDouble(5, emp.getSalary());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error in adding employee: " + e.getMessage());
            return false;
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getDouble("salary")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching employees: " + e.getMessage());
        }
        return list;
    }

    public boolean updateSalary(int id, double salary) {
        String sql = "UPDATE employees SET salary = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, salary);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating salary: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
            return false;
        }
    }
    public List<Employee> searchEmployees(String keyword) {
        List<Employee> results = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE name LIKE ? OR department LIKE ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getDouble("salary")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error searching employees: " + e.getMessage());
        }
        return results;
    }

}
