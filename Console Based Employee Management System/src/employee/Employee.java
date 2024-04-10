package employee;
import java.sql.Date;

public class Employee {
    private String fullName;
    private int age;
    private Date dateOfBirth;
    private int salary;
    private String department;

    public Employee() {

    }

    public Employee(String fullName, int age, Date dateOfBirth, int salary, String department) {

        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.department = department;
    }

    public String getDept() {
        return department;
    }

    public void setDept(String department) {
        this.department = department;
    }

    public String getName() {
        return fullName;
    }

    public void setName(String name) {
        this.fullName = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDOB() {
        return dateOfBirth;
    }

    public void setDOB(Date DOB) {
        this.dateOfBirth = DOB;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + fullName + '\'' +
                ", salary=" + salary +
                ", dept=" + department +
                ", salary=" + salary +
                ", DOB=" + dateOfBirth +
                ", age=" + age +
                '}';
    }
}
