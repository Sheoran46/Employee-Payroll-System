import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PayrollSystem {
    //private Map<Integer, Employee> employeeListFull;
    //private Map<Integer, Employee> employeeListPart;

    public PayrollSystem() {
        //employeeListFull = new HashMap<>();
        //employeeListPart = new HashMap<>();
    }

    /*public void addEmployee(Employee employee) {
        /*char type = employee.employeeType();
        if (type == 'F')
            employeeListFull.put(employee.getId(), employee);
        else if (type == 'P')
            employeeListPart.put(employee.getId(), employee);

        saveEmployeeToDB(employee);
    }*/

    /*public void removeEmployee(int id) {
        if (employeeListFull.containsKey(id)) {
            employeeListFull.remove(id);
        } else if (employeeListPart.containsKey(id)) {
            employeeListPart.remove(id);
        }

        deleteEmployeeFromDB(id);
    }*/

    public void displayEmployee(Employee e) {
        /*for (Employee e : employeeListFull.values()) {
            System.out.println(e);
        }
        for (Employee e : employeeListPart.values()) {
            System.out.println(e);
        }*/
        System.out.println(e);
        return;
    }

    /*public void displayFullEmployees() {
        for (Employee e : employeeListFull.values()) {
            System.out.println(e);
        }
    }*/

    /*public void displayPartEmployees() {
        for (Employee e : employeeListPart.values()) {
            System.out.println(e);
        }
    }*/

    public void saveEmployeeToDB(Employee employee) {
        try (Connection conn = DButil.getConnection()) {
            String query = "INSERT INTO employees (id, name, type, salary, hours, rate) VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE name = VALUES(name), type = VALUES(type), salary = VALUES(salary), hours = VALUES(hours), rate = VALUES(rate)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employee.getId());
            stmt.setString(2, employee.getName());
            stmt.setString(3, String.valueOf(employee.employeeType()));

            if (employee instanceof FullTimeEmployee) {
                stmt.setDouble(4, employee.salaryCalculator());
                stmt.setNull(5, 0);
                stmt.setNull(6, 0);
            } else if (employee instanceof PartTimeEmployee) {
                PartTimeEmployee pte = (PartTimeEmployee) employee;
                stmt.setDouble(4, pte.salaryCalculator());
                stmt.setInt(5, pte.hours);
                stmt.setDouble(6, pte.salaryPerHour);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployeeFromDB(int id) {
        try (Connection conn = DButil.getConnection()) {
            String query = "DELETE FROM employees WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAllEmployeesFromDB() {
        try (Connection conn = DButil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                char type = rs.getString("type").charAt(0);

                if (type == 'F') {
                    double salary = rs.getDouble("salary");
                    displayEmployee(new FullTimeEmployee(name, id, type, salary));
                } else if (type == 'P') {
                    double rate = rs.getDouble("rate");
                    int hours = rs.getInt("hours");
                    //addEmployee(new PartTimeEmployee(name, id, type, rate, hours));
                    displayEmployee(new PartTimeEmployee(name, id, type, rate, hours));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayAllPartTimeEmployeesFromDB() {
        try (Connection conn = DButil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees WHERE type='P' ");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                char type = rs.getString("type").charAt(0);
                double rate = rs.getDouble("rate");
                int hours = rs.getInt("hours");
                displayEmployee(new PartTimeEmployee(name, id, type, rate, hours));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayAllFullTimeEmployeesFromDB() {
        try (Connection conn = DButil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees WHERE type='F' ");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                char type = rs.getString("type").charAt(0);
                double salary=rs.getDouble("salary");
                displayEmployee(new FullTimeEmployee(name, id, type, salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
