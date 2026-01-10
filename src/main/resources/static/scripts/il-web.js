/**
 * Model deciles java script files
 */
//Checking for decimals
function checkKeyDecimal(e) {
 	if((e.keyCode < 48 || e.keyCode > 57) && e.keyCode!=45 && e.keyCode!=46) e.returnValue = false;
}

//Checking for decimals.
//Not include negative
function checkKeyDecimalNoNegative(e) {
 	if((e.keyCode < 48 || e.keyCode > 57) && e.keyCode!=46) e.returnValue = false;
}

//Checking keystrokes accepts only numbers
function checkKeyNumber(e) {
 	if((e.keyCode < 48 || e.keyCode > 57)) e.returnValue = false;
}

//Checking keystrokes accepts only numbers no negative
function checkKeyNumberNoNegative(e) {
 	if((e.keyCode < 48 || e.keyCode > 57) && e.keyCode!=46) e.returnValue = false;
}

//Process data load
function jsResetHome(url) {
	const form = document.getElementById("loginForm");
	form.action = url;   // change form action to reset mapping
	form.method = "get"; // reset can be GET
	form.submit();       // submit the form
} 

//User add user
function jsUserSubmit(url) {
	const form = document.getElementById("userForm");
	form.action = url;   // change form action to reset mapping
	form.method = "get"; // reset can be GET
	form.submit();       // submit the form
} 

//User update/delete user
function jsUserUorDSubmit(url, userId) {
	const form = document.getElementById("userForm");
	form.action = url;   // change form action to reset mapping
	form.userId.value = userId; // set hidden field
	form.method = "get"; // reset can be GET
	form.submit();       // submit the form
} 

//User add manufacturer
function jsManufacturerSubmit(url) {
	const form = document.getElementById("manufacturerForm");
	form.action = url;   // change form action to reset mapping
	form.method = "get"; // reset can be GET
	form.submit();       // submit the form
} 

//User update/delete manufacturer
function jsManufacturerUorDSubmit(url, manufacturerId) {
	const form = document.getElementById("manufacturerForm");
	form.action = url;   // change form action to reset mapping
	form.manufacturerId.value = manufacturerId; // set hidden field
	form.method = "get"; // reset can be GET
	form.submit();       // submit the form
} 

//User add product
function jsProductSubmit(url) {
	const form = document.getElementById("productForm");
	form.action = url;   // change form action to reset mapping
	form.method = "get"; // reset can be GET
	form.submit();       // submit the form
} 

//User update/delete product
function jsProductUorDSubmit(url, productId) {
	const form = document.getElementById("productForm");
	form.action = url;   // change form action to reset mapping
	form.productId.value = productId; // set hidden field
	form.method = "get"; // reset can be GET
	form.submit();       // submit the form
}

//User add order qty
function jsOrderQtySubmit(url) {
	const form = document.getElementById("orderQtyForm");
	form.action = url;   // change form action to reset mapping
	form.method = "get"; // reset can be GET
	form.submit();       // submit the form
} 

//User update/delete order qty
function jsOrderQtyUorDSubmit(url, orderId) {
	const form = document.getElementById("orderQtyForm");
	form.action = url;   // change form action to reset mapping
	form.orderId.value = orderId; // set hidden field
	form.method = "get"; // reset can be GET
	form.submit();       // submit the form
}

//User order document mongodb
function jsOrderDocumentSubmit(url) {
	const form = document.getElementById("orderDocumentForm");
	form.action = url;   // change form action to reset mapping
	form.method = "get"; // reset can be GET
	form.submit();       // submit the form
}

//User order document from file
function jsFileReadSubmit(url) {
	const form = document.getElementById("fileReadForm");
	form.action = url;   // change form action to reset mapping
	form.method = "get"; // reset can be GET
	form.submit();       // submit the form
}

//User add mfg vs product
function jsMfgvsproductSubmit(url) {
	const form = document.getElementById("mfgvsproductForm");
	form.action = url;   // change form action to reset mapping
	form.method = "get"; // reset can be GET
	form.submit();       // submit the form
} 

//User update/delete mfg vs product
function jsMfgvsproductUorDSubmit(url, mfgvsproductId) {
	const form = document.getElementById("mfgvsproductForm");
	form.action = url;   // change form action to reset mapping
	form.mfgvsproductId.value = mfgvsproductId; // set hidden field
	form.method = "get"; // reset can be GET
	form.submit();       // submit the form
}

//Process retrieve day by day expenses
function jsWebInterface(requestType) {
	if(requestType == "retrieveAllDeptExpenseType") {
		document.webInterfaceForm.type.value = "retrieveAllDeptExpenseType";
	} else if(requestType == "retrieveDeptExpenseTypeByDepartment") {
		document.webInterfaceForm.type.value = "retrieveDeptExpenseTypeByDepartment";
	} else if(requestType == "retrieveDayByDayExpenses") {
		document.webInterfaceForm.type.value = "retrieveDayByDayExpenses";	
	} else if(requestType == "retrieveVendorMaterialExpenses") {
		document.webInterfaceForm.type.value = "retrieveVendorMaterialExpenses";
	} else if(requestType == "retrieveMaterialGroupByDepartmentId") {
		document.webInterfaceForm.type.value = "retrieveMaterialGroupByDepartmentId";	
	} else if(requestType == "retrieveVendorMachineExpenses") {
		document.webInterfaceForm.type.value = "retrieveVendorMachineExpenses";
	} else if(requestType == "retrieveMachineGroupByDepartmentId") {
		document.webInterfaceForm.type.value = "retrieveMachineGroupByDepartmentId";			
	} else if(requestType == "cancelHome") {
		document.webInterfaceForm.type.value = "cancelHome";
	}
	document.webInterfaceForm.submit();	
} 