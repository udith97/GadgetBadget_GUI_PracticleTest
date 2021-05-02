//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidInvestmentIDSave").val("");
	$("#INVESTMENT")[0].reset();
});

$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#hidInvestmentIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "InvestmentAPI",
		type : type,
		data : $("#INVESTMENT").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});

});

function onItemSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#InvestmentGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hidInvestmentIDSave").val("");
	$("#INVESTMENT")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "InvestmentAPI",
		type : "DELETE",
		data : "investmentID=" + $(this).data("investmentID"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#InvestmentGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error") {
		
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event)
		{
			$("#hidInvestmentIDSave").val($(this).data("investmentID"));
			$("#projectName").val($(this).closest("tr").find('td:eq(0)').text());
			$("#researcherName").val($(this).closest("tr").find('td:eq(1)').text());
			$("#investorName").val($(this).closest("tr").find('td:eq(2)').text());
			$("#investAmount").val($(this).closest("tr").find('td:eq(3)').text());
			$("#cardNo").val($(this).closest("tr").find('td:eq(4)').text());
			$("#cvvNo").val($(this).closest("tr").find('td:eq(5)').text());		
		});


// CLIENTMODEL=========================================================================
function validateItemForm() {
	
	// projectName
	if ($("#projectName").val().trim() == "") {
		return "Please insert projectName.";
	}
	
	// researcherName
	if ($("#researcherName").val().trim() == "") {
		return "Please insert researcherName.";
	}
	
	// investorName
	if ($("#investorName").val().trim() == "") {
		return "Please insert investorName.";
	}

	// investAmount
	if ($("#investAmount").val().trim() == "") {
		return "Please insert investAmount.";
	}
	
	// Card No
	if ($("#cardNo").val().trim() == "") {
		return "Please insert card no.";
	}
	
	// CVV No
	if ($("#cvvNo").val().trim() == "") {
		return "Please insert cvv no.";
	}
	
	return true;
}
