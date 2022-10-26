package test;
import java.sql.*;
import java.util.ArrayList;

public class Database {
	
	Connection con;			// Connection Object
	Statement st;			// Statement Object
	
	String url = "jdbc:mysql://localhost:3306/employeemanagment";
	
	// constructor | Make connection
	public Database() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");			// Register Driver
		con = DriverManager.getConnection(url ,"root","851438"); // get Connection
		st = con.createStatement();					// get statement	
	}
	
	// function for checking the credentials
	public boolean login(String name, String password) throws Exception {
		String queryString = "select * from login where name = ? and password = ?";
		
		PreparedStatement pr = con.prepareStatement(queryString);
		pr.setString(1, name);
		pr.setString(2, password);
		
		ResultSet rSet = pr.executeQuery();
		
		if(rSet.next()) {
			return true;
		}
		
		return false;
	}
	
	public  ArrayList<Employee> getAllEmployees() throws SQLException{
		ArrayList<Employee> list = new ArrayList<>();
		String query = "select * from employee";
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			int age = rs.getInt(3);
			String address = rs.getNString(4);
			int salary = rs.getInt(5);
			
			Employee employee = new Employee(name, address, id, age, salary);
			list.add(employee);
		}
		return list;
		
	}
	// Insert New Employee
	void addEmployee(Employee employee) throws SQLException {
	
		String queryString = "insert into employee (id, name, age, address, salary) values (?, ?, ?, ?, ?);";
		PreparedStatement pr = con.prepareStatement(queryString);
		
		
		pr.setInt(1, employee.id);
		pr.setString(2, employee.name);
		pr.setInt(3, employee.age);
		pr.setString(4, employee.address);
		pr.setInt(5, employee.salary);

		pr.executeUpdate();
	}
	
	// Deletion
	void deleteEmployee(int id) throws SQLException {
		String queryString = "delete from employee where id = ?";
		PreparedStatement pr = con.prepareStatement(queryString);
		pr.setInt(1, id);
		pr.executeUpdate();
		
	}
	// Edit | Update Data
	
	void updateEmployee(int id, String name, String address, int age, int salary) throws SQLException {
		
		String query = "update employee set name = ?, age = ?, address = ?, salary = ? where id = ?";
		PreparedStatement pr = con.prepareStatement(query);
		
		pr.setString(1, name);
		pr.setInt(2, age);
		pr.setString(3, address);
		pr.setInt(4, salary);
		pr.setInt(5, id);
		
		
		pr.executeUpdate();
	}
	
}
