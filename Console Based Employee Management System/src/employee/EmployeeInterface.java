package employee;

public interface EmployeeInterface {
	
	public void createEmployee(Employee emp);
	public void showAllEmployees();
	public void deleteEmployee(int id);
	public void searchEmployee(int id);
	public void updateEmployee(int id);
	public void filterEmployeeByDept(String dept);
	public void calculateAvgSalaryByDept(String cse);
	public void calculateAvgSalary();

}


