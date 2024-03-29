package com.gadget_badget.investment.model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


@WebServlet("/InvestmentAPI")
public class InvestmentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	InvestmentServlet investmentObj = new InvestmentServlet();
	
	public InvestmentAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		String outputString = investmentObj.insertInvestment(request.getParameter("projectName"), 
				request.getParameter("researcherName"),
				request.getParameter("investorName"), 
				request.getParameter("investAmount"), 
				request.getParameter("cardNo"),
				request.getParameter("cvvNo"));

		response.getWriter().write(outputString);
	}	

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map paras = getParasMap(request);

		String outputString = investmentObj.updateInvestment(
				paras.get("investmentID").toString(),
				paras.get("projectName").toString(),
				paras.get("researcherName").toString(), 
				paras.get("investorName").toString(),
				paras.get("investAmount").toString(),
				paras.get("cardNo").toString(),
				paras.get("cvvNo").toString()); 

		response.getWriter().write(outputString);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = investmentObj.deleteInvestment(paras.get("investmentID").toString());
		response.getWriter().write(output); 		
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		try {			
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
			
		} catch (Exception e) {
		  }
		
		return map;
	}
}