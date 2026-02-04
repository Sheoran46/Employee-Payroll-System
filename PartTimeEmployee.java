class PartTimeEmployee extends Employee {
    char type;
    double salaryPerHour;
    int hours;

    public PartTimeEmployee(String name, int id, char type, double salaryPerHour, int hours) {
        super(name, id);
        this.type = type;
        this.salaryPerHour = salaryPerHour;
        this.hours = hours;
    }

    @Override
    public double salaryCalculator() {
        return (double)salaryPerHour * hours;
    }

    @Override
    public char employeeType() {
        return this.type;
    }
}
