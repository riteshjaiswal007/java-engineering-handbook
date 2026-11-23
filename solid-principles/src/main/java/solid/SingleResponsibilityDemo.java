package solid;

public class SingleResponsibilityDemo {

    // Employee data responsibility
    static class Employee {
        private final String name;
        private final double monthlySalary;

        Employee(String name, double monthlySalary) {
            this.name = name;
            this.monthlySalary = monthlySalary;
        }

        public String getName() {
            return name;
        }

        public double getMonthlySalary() {
            return monthlySalary;
        }
    }

    // Salary calculation responsibility
    static class SalaryCalculator {

        public double calculateAnnualSalary(Employee employee) {
            return employee.getMonthlySalary() * 12;
        }
    }

    // Database responsibility
    static class EmployeeRepository {

        public void save(Employee employee) {
            System.out.println(
                "Employee saved to database: " + employee.getName()
            );
        }
    }

    // Report generation responsibility
    static class EmployeeReport {

        public void generate(Employee employee, double annualSalary) {
            System.out.println("Employee Report");
            System.out.println("Name: " + employee.getName());
            System.out.println("Annual Salary: " + annualSalary);
        }
    }

    public static void main(String[] args) {

        Employee employee = new Employee("John", 50000);

        SalaryCalculator calculator = new SalaryCalculator();
        EmployeeRepository repository = new EmployeeRepository();
        EmployeeReport report = new EmployeeReport();

        double annualSalary =
            calculator.calculateAnnualSalary(employee);

        repository.save(employee);
        report.generate(employee, annualSalary);
    }
}
