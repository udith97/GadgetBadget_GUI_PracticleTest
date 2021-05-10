<%@page import="com.gadget_badget.investment.model.InvestmentServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>Investment Management - GadgetBadget</title>
	
		<link href="css/stylsheet.css" rel="stylesheet" />
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="Components/jquery-3.5.0.min.js"></script>
		<script src="Components/Investment.js"></script>

	</head>
	
	<body>
		<div class="container">
	
			<p class="font-weight-bold">
				<center>
					<h1><b>Investment Management - GadgetBadget</b></h1>
				</center>
			</p>
			<br><br>
			
			<fieldset>
					<form id="INVESTMENT" name="INVESTMENT" class="border border-light p-5">
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Project Name:</label>
						    <input type="text" id="projectName" class="form-control" name="projectName" placeholder="Enter Project Name..">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Researcher Name:</label>
						    <input type="text" id="researcherName" class="form-control" name="researcherName" placeholder="Enter Researcher Name..">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Investor Name:</label>
						    <input type="text" id="investorName" class="form-control" name="investorName" placeholder="Enter Investor name..">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Invest Amount:</label>
						    <input type="text" id="investAmount" class="form-control" name="investAmount" placeholder="Rs.">						    
						</div>
					
						 
						<div class="row mb-4">
						    <div class="col">
						      <div class="form-outline">
						        <label class="form-label" for="form6Example1" class="col-sm-2 col-form-label col-form-label-sm">Credit/Debit Card No:</label>
						        <input type="text" id="cardNo" class="form-control" name="cardNo" placeholder="xxxx-xxxx-xxxx.">						        
						      </div>
						    </div>
						    <div class="col">
						      <div class="form-outline">
								<label class="form-label" for="form6Example2" class="col-sm-2 col-form-label col-form-label-sm">CVV No:</label>
						        <input type="text" id="cvvNo" class="form-control" name="cvvNo" aria-describedby="passwordHelpInline" placeholder="xxx">
						      </div>
						    </div>
						  </div>						
						<br> 
						
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary btn-lg btn-block"> 
						<input type="hidden" id="hidInvestmentIDSave" name="hidInvestmentIDSave" value="">
						<br>
						<br>
						<input type="reset"  id="btnClear" name="btnClear" class="btn btn-primary btn-lg btn-block">
					</form>
				
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>			
			</fieldset>
			
			<br> 
			
			<div class="container" id="InvestmentGrid">
				<fieldset>
					<legend><b>View Investment Details</b></legend>
					<form method="post" action="Investments.jsp" class="table table-striped">
						<%
							InvestmentServlet viewInvestment = new InvestmentServlet();
											out.print(viewInvestment.readInvestment());
						%>
					</form>
					<br>
				</fieldset>
			</div>
		</div>
	</body>
</html>



