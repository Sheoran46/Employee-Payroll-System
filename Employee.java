abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract double salaryCalculator();

    public abstract char employeeType();

    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + ", type=" + employeeType() + ", salary=" + salaryCalculator() + "]";
    }
}
