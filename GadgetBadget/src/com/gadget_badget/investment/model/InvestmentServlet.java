package com.gadget_badget.investment.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class InvestmentServlet {
	
	//Connect to the MySQL DB
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver");  
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database?useTimezone=true&serverTimezone=UTC", "root", ""); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();} 
		 	return con; 
		} 

		public String insertInvestment(String projectName, String researcherName, String investorName, String investAmount, String cardNo, String cvvNo) 
		 { 
			 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for inserting."; 
				 } 
			 	 	 // create a prepared statement
				 	 String query = "INSERT INTO investment_gui(`investmentID`,`projectName`,`researcherName`,`investorName`,`investAmount`,`cardNo`,`cvvNo`)" + " VALUES (?, ?, ?, ?, ?, ?, ?)"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 					 
					 
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, projectName);
					 preparedStmt.setString(3, researcherName);
					 preparedStmt.setString(4, investorName);
					 preparedStmt.setDouble(5, Double.parseDouble(investAmount));
					 preparedStmt.setString(6, cardNo);
					 preparedStmt.setString(7, cvvNo);
					 				 
					 preparedStmt.execute(); 
					 con.close(); 
					 
					 String newInvestment = readInvestment(); 
					 output = "{\"status\":\"success\", \"data\": \"" + newInvestment + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while inserting the investment.\"}";
				 System.out.println(e.getMessage());
					System.out.println(e);
					e.printStackTrace();
			 } 
		 	return output; 
		 } 

		//Read Investments
		 public String readInvestment() 
		 { 
			 String output = ""; 

			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for reading."; 
				 } 
				 
				 // Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>investmentID</th>"
						 + "<th>Project Name</th>" +	 
				  "<th>Researcher Name</th>" +
				 "<th>Investor Name</th>" + 
				 "<th>Invest Amount</th>" + 
				 "<th>Card No</th>" +
				 "<th>CVV No</th>" +
				 "<th>Update</th><th>Remove</th></tr>"; 
			 
				 
				 String query = "SELECT * FROM investment_gui"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 // iterate through the rows in the result set
				 while (rs.next()) 
				 { 
					 String investmentID = Integer.toString(rs.getInt("investmentID")); 
					 String projectName = rs.getString("projectName"); 
					 String researcherName = rs.getString("researcherName"); 
					 String investorName = rs.getString("investorName"); 
					 String investAmount = Double.toString(rs.getDouble("investAmount")); 
					 String cardNo = rs.getString("cardNo");
					 String cvvNo = rs.getString("cvvNo");
					 
					 
					 // Add into the html table
					 output += "<tr><td>" + investmentID + "</td>";
					 output += "<td>" + projectName + "</td>";
					 output += "<td>" + researcherName + "</td>"; 
					 output += "<td>" + investorName + "</td>"; 
					 output += "<td>" + investAmount + "</td>"; 
					 output += "<td>" + cardNo + "</td>"; 
					 output += "<td>" + cvvNo + "</td>"; 					 
					 
					 
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
							 + "<td><button class='btnRemove btn btn-danger' name='btnRemove' id ='btnRemove' value='"+ investmentID +"' >Remove</button></td></tr>";
				 } 
				 	 con.close(); 
				 	 // Complete the html table
				 	 output += "</table>"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "Error while reading the investment"; 
				 System.out.println(e.getMessage());
					System.out.println(e);
					e.printStackTrace(); 
			 } 
		 	 return output; 
		 } 
				
		//Update Investments
		public String updateInvestment(String investmentID, String projectName, String researcherName, String investorName, String investAmount , String cardNo , String cvvNo)
		{ 
			 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for updating."; 
				 } 
				 
				 // create a prepared statement
				 String query = "UPDATE investment_gui SET projectName=? , researcherName=? , investorName=? , investAmount=? , cardNo=? , cvvNo=?  WHERE investmentID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setString(1, projectName); 
				 preparedStmt.setString(2, researcherName); 
				 preparedStmt.setString(3, investorName);  
				 preparedStmt.setDouble(4, Double.parseDouble(investAmount)); 
				 preparedStmt.setString(5, cardNo); 
				 preparedStmt.setString(6, cvvNo); 
				 preparedStmt.setInt(7, Integer.parseInt(investmentID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 String newInvestment = readInvestment(); output = "{\"status\":\"success\", \"data\": \"" + newInvestment + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while updating the investment.\"}"; 
				 System.out.println(e.getMessage());
					System.out.println(e);
					e.printStackTrace(); 
			 } 
			 	return output; 
			 } 
		
			//Delete Investments
			 public String deleteInvestment(String investmentID) 
			 { 
				 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for deleting."; 
			 } 
			 
			 	 // create a prepared statement
				 String query = "DELETE FROM investment_gui WHERE investmentID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(investmentID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 String newInvestment = readInvestment(); output = "{\"status\":\"success\", \"data\": \"" + newInvestment + "\"}";
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while deleting the investment.\"}"; 
				 System.out.println(e.getMessage());
					System.out.println(e);
					e.printStackTrace();
			 } 
			 return output; 
		} 
} 