public class Main {
    public static void main(String args[]){
        PayrollSystem payrollSystem = new PayrollSystem();

        FullTimeEmployee emp1 = new FullTimeEmployee("Sourav Sheoran", 101, 'F', 5000.0);
        PartTimeEmployee emp2 = new PartTimeEmployee("Aaakash Sheoran", 102, 'P', 30,  15);
        PartTimeEmployee emp3 = new PartTimeEmployee("Siddharth Sheoran", 103, 'P', 20,  10);
        payrollSystem.saveEmployeeToDB(emp1);
        payrollSystem.saveEmployeeToDB(emp2);
        payrollSystem.saveEmployeeToDB(emp3);

        System.out.println("All Employee Details:");
        payrollSystem.displayAllEmployeesFromDB();

        System.out.println("FullTime Employee Details:");
        payrollSystem.displayAllFullTimeEmployeesFromDB();

        System.out.println("PartTime Employee Details:");
        payrollSystem.displayAllPartTimeEmployeesFromDB();

        System.out.println("\nRemoving Employee...");
        payrollSystem.deleteEmployeeFromDB(101);

        System.out.println("\nRemaining Employee Details:");
        payrollSystem.displayAllEmployeesFromDB();
    }
}
