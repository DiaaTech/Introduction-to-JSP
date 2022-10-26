<%@page import="java.util.ArrayList"%>
<%@page import="test.Database"%>
<%@page import="test.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Main</title>
<link rel = "stylesheet" href = "viewEmployee.css">
</head>
<body>

<%
	String name = (String) request.getParameter("username");	
	out.print(name);
	String password = (String) request.getParameter("password");	

	Database db = new Database();
	
	boolean login = db.login(name, password);
	
	if(login){
	
		ArrayList<Employee> emp = db.getAllEmployees();
		for(int i = 0; i < emp.size(); i++ ){
		
%>

 <div class="cards">
      <div class="card">
        <h2>
        <% 
        	out.print(emp.get(i).name);
        %>
        </h2>
        <h2>
         <% 
        	out.print(emp.get(i).age);
        %>
        </h2>
        <h2>Employee ID: 
         <% 
        	out.print(emp.get(i).id);
        %>
        </h2>
        <h2>Salary: 
         <% 
        	out.print(emp.get(i).salary);
        %>
        </h2>
        <h2>Address: 
        	<% 
        	out.print(emp.get(i).address);
        %>
        </h2>
      </div>
	

<%
	}
	}
	
	else{
		out.print("Error");
	}
%>


</body>
</html>