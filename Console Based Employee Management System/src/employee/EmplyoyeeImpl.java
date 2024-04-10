package employee;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;



public class EmplyoyeeImpl implements EmployeeInterface{
	Connection con;
	public void createEmployee(Employee emp) {
		 con = DBConnection.createDBConnection();
	       String query="insert into emp (FullName, Age, DateOfBirth, salary, Department) values(?,?,?,?,?)";
	       try{
	           PreparedStatement pstm=con.prepareStatement(query);
						 pstm.setString(1,emp.getName());
						 pstm.setInt(2,emp.getAge());
						 pstm.setDate(3, emp.getDOB());
						 pstm.setInt(4,emp.getSalary());
						 pstm.setString(5, emp.getDept());
	          
	           int cnt= pstm.executeUpdate();
	          if(cnt!=0)
	              System.out.println("Employee Inserted Successfully !!!");

	       }
	       catch (Exception ex){
	           ex.printStackTrace();
	       }
	}


	public void showAllEmployees(){
		con = DBConnection.createDBConnection();
		String query = "select * from emp";
		System.out.println("EMPLOYEE DETAILS :");
		System.out.println("-----------------");


		try {
			Statement stmt = con.createStatement();
			ResultSet result= stmt.executeQuery(query);
			while (result.next()) {
				System.out.println("Employee ID:"+result.getInt(1));
				System.out.println("Full Name:"+result.getString(2));
				System.out.println("Age:"+result.getInt(3));
				System.out.println("Date of Birth:"+ result.getDate(4));
				System.out.println("Salary:"+result.getInt(5));
				System.out.println("Department:"+result.getString(6));
				
				System.out.println("-----------------");
				System.out.println("NEXT EMPLOYEE");
				
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void deleteEmployee(int id){
		con = DBConnection.createDBConnection();
		String query = "delete from emp where id=?";
		try{
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			int result = stmt.executeUpdate();
			if(result>0){
				System.out.println("Employee deleted successfully \n");
			}
			else {
				System.out.println("ID Not Found! \n");
			}

		}
		catch(Exception ex){
			ex.printStackTrace();;
		}

	}


	public void searchEmployee(int id){
		con = DBConnection.createDBConnection();
		String query = "select * from emp where id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			if(!result.isBeforeFirst()){
				System.out.println("Employee Not Found \n");
			}
			else {
				while(result.next()){
				System.out.println("Employee ID:"+result.getInt(1));
				System.out.println("Full Name:"+result.getString(2));
				System.out.println("Age:"+result.getInt(3));
				System.out.println("Date of Birth:"+ result.getDate(4));
				System.out.println("Salary:"+result.getInt(5));
				System.out.println("Department:"+result.getString(6));
				
				System.out.println("-----------------");
					
				}
			}

		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		

	}

	public void updateEmployee(int id){
		con = DBConnection.createDBConnection();
		String query = "select * from emp where id=?";
		
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			if(!result.isBeforeFirst()){
				System.out.println("Employee Not Found \n");
			}
			else {
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				System.out.println("Employee Found! \n");
				System.out.println("Enter the details! \n");

				System.out.println("Enter the name:");
				String nameValue= sc.next();

				System.out.println("Enter age");
        int age = sc.nextInt();
				
				System.out.println("Enter the DateOfBirth:");
				String dateValue = sc.next();


				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date1 = sdf.parse(dateValue);
        java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

				System.out.println("Enter the salary");
				int salary = sc.nextInt();

				System.out.println("Enter the department");
				String dept = sc.next();


				
				String updateQuery = " UPDATE emp SET FullName=?, Age=?, DateOfBirth=?, salary=?, Department=? WHERE id = ?";
				PreparedStatement stmt1 = con.prepareStatement(updateQuery);
				stmt1.setString(1, nameValue);
				stmt1.setInt(2, age);
				stmt1.setDate(3, sqlDate);
				stmt1.setInt(4, salary);
				stmt1.setString(5, dept);
				stmt1.setInt(6, id);
				stmt1.executeUpdate();
				System.out.println("Employee Updated Successfully");
			}

	}
	catch(Exception ex){
		ex.printStackTrace();
	}

}

public void filterEmployeeByDept(String dept){
	con = DBConnection.createDBConnection();
	String query = "select * from emp where Department = ?";
	try{
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, dept);
		ResultSet result = stmt.executeQuery();
		if(!result.isBeforeFirst()){
			System.out.println("Department Not Found \n");
		}

		else {
			while(result.next()){
				System.out.println("Employee ID:"+result.getInt(1));
				System.out.println("Full Name:"+result.getString(2));
				System.out.println("Age:"+result.getInt(3));
				System.out.println("Date of Birth:"+ result.getDate(4));
				System.out.println("Salary:"+result.getInt(5));
				System.out.println("Department:"+result.getString(6));
				
				System.out.println("-----------------");
					
				}

		}

	}

	catch (Exception ex){
		ex.printStackTrace();
	}

}

public void calculateAvgSalaryByDept(String dept){
	con = DBConnection.createDBConnection();
	String query = "select AVG(salary) FROM emp WHERE department = ?";
	try {
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, dept);
		ResultSet result = stmt.executeQuery();
		if(result.next()){
			int avg_salary = result.getInt(1);
			System.out.println("Average Salary of "+dept+" is "+avg_salary);
		}
		else{
			System.out.println("No data found!");
		}

	}
	catch(Exception ex){
		ex.printStackTrace();
	}


}

public void calculateAvgSalary(){
	con = DBConnection.createDBConnection();
	String query = "select AVG(salary) FROM emp";
	try{
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet result = stmt.executeQuery();
		if(result.next()){
			int avg_salary = result.getInt(1);
			System.out.println("Average Salary of all employees is "+avg_salary);
		}
		
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
}




}
