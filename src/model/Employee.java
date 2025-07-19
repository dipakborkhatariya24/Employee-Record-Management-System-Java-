package model;

public class Employee {
    private int id;
    private String name;
    private int age;
    private String department;
    private double salary;

    public Employee(int id, String name, int age, String department, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }
    //Getter methods
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    //setter methods
    public void setSalary(double salary) { this.salary = salary; }
}
