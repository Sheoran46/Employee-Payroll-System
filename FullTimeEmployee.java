class FullTimeEmployee extends Employee {
    char type;
    double salary;

    public FullTimeEmployee(String name, int id, char type, double salary) {
        super(name, id);
        this.type = type;
        this.salary = salary;
    }

    @Override
    public double salaryCalculator() {
        return salary;
    }

    @Override
    public char employeeType() {
        return this.type;
    }
}
