package employee;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class Main {

  public static void main(String[] args) throws Exception {
    EmployeeInterface dao = new EmplyoyeeImpl();
    //int id;
    String fullName;
    int salary;
    int age;
    String dob;
    String department;
    @SuppressWarnings("resource")
    Scanner sc = new Scanner(System.in);
    do {
      System.out.println("1. Add Employee\n" +
          "2. Show All Employees\n" +
          "3. Delete an Employee\n" +
          "4. Search for an Employee\n"+
          "5. Update an Employee\n"+ 
          "6. Filter Employee By Dept \n"+
          "7. Calculate average Salary Of a Department\n"+
          "8. Average Salary of all the employees\n"+
          "9. Exit\n");

      System.out.println("Enter choice: ");
      int ch = sc.nextInt();
      switch (ch) {
        case 1:
          Employee emp = new Employee();
          
          System.out.println("Enter Full Name");
          fullName = sc.next();
          System.out.println("Enter salary");
          salary = sc.nextInt();
          System.out.println("Enter date of birth in this format dd-MM-YYYY");
          dob = sc.next();
          System.out.println("Enter age");
          age = sc.nextInt();

          LocalDate date = LocalDate.parse(dob,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
          Period p = Period.between(date, LocalDate.now());
          int calculatedAge = p.getYears();
          if (age == calculatedAge){
            emp.setAge(age);

          }
          else{
            emp.setAge(calculatedAge);
          }

          
          SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
          java.util.Date date1 = sdf.parse(dob);
          java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

          System.out.println("Enter department");
          department = sc.next();
          emp.setDOB(sqlDate);
          emp.setDept(department);
          emp.setName(fullName);
          emp.setSalary(salary);
          dao.createEmployee(emp);
          break;
        case 2:
          dao.showAllEmployees();
          break;
        case 3:
          System.out.println("Enter the id to delete");
          int newId = sc.nextInt();
          dao.deleteEmployee(newId);
          break;
        case 4:
          System.out.println("Enter the id to search ");
          int searchId = sc.nextInt();
          dao.searchEmployee(searchId);
          break;
        case 5:
          System.out.println("Enter the id of the employee to update");
          int updateID = sc.nextInt();
          dao.updateEmployee(updateID);
        case 6:
          System.out.println("Enter the dept name to filter");
          String filterDept = sc.next();
          dao.filterEmployeeByDept(filterDept);
          break;
        case 7:
          System.out.println("Enter the Department name");
          String deptName = sc.next();
          dao.calculateAvgSalaryByDept(deptName);
          break;
        case 8:
          dao.calculateAvgSalary();
          break;

        case 9:
          System.exit(0);
          break;

      }
    } while (true);

  }

}