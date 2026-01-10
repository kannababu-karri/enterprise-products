<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ILabs: Manufacturer</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/common/header.jsp" flush="true" />

<form:form action="${pageContext.request.contextPath}/manufacturer/manufacturerSearch" method="post" modelAttribute="manufacturerForm.manufacturer" id="manufacturerForm">
	<p class="searchTitle">&nbsp;&nbsp;Manufacturer Interface: Home</p>
		<p class="searchTitle">
		<c:if test="${not empty error}">
		    <div class="error-msg" style="color:red;">
		        &nbsp;&nbsp;${error}
		    </div>
		</c:if>
		<c:if test="${not empty msg}">
		    <div style="color: green;"><b>&nbsp;&nbsp;${msg}</b></div>
		</c:if>
	</p>
	<p>&nbsp;&nbsp;
		<input type="button" value="Add Manufacturer" onclick="javascript:jsManufacturerSubmit('${pageContext.request.contextPath}/manufacturer/displayNewManufacturer');" />&nbsp;
		<input type="button" value="Return IL Home" onclick="javascript:jsManufacturerSubmit('${pageContext.request.contextPath}/manufacturer/returnILHome');" />
	</p>
	
	<!-- global errors -->
    <form:errors path="*" cssClass="error-msg" element="div"/>
	<table width="100%">			
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Manufacturer Name:</b></td>
			<td width="30%" align="left">
				<input type="text" name="mfgName" value="${manufacturerForm.manufacturer.mfgName}" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>		
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="50%" align="center" colspan="2">
				<input type="submit" value="Submit" />&nbsp;&nbsp;<input type="button" value="Cancel" onclick="javascript:jsManufacturerSubmit('${pageContext.request.contextPath}/manufacturer/returnILHome');" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>				
		<tr>
			<td width="100%" colspan="4"><hr /></td>
		</tr>	
	</table>
	<!-- Display search results -->
	<c:if test="${manufacturerForm.showDetails}">
		<p class="searchTitle">&nbsp;&nbsp;Manufacturer Search Results:</p>
		<table class="searchDetail">			
			<tr>				
				<th>Manufacturer name</th>				
				<th>Products</th>
				<th>Address1</th>
				<th>Address2</th>
				<th>City</th>
				<th>State</th>
				<th>Zip</th>
				<th>Zip Ext</th>
				<th>Update</th>
				<th>Delete</th>								
			</tr>
			<c:forEach items="${manufacturerForm.resultManufacturers}" var="manufacturer" varStatus="status">		
				<tr class="detailData">								
					<td class="center">${manufacturer.mfgName}</td>
					<td class="leftNoBold">
						<c:forEach items="${manufacturer.products}" var="product" varStatus="status">
			            	${product.productName}
			        	</c:forEach>
					</td>
					<td class="leftNoBold">${manufacturer.address1}</td>
					<td class="leftNoBold">${manufacturer.address2}</td>
					<td class="leftNoBold">${manufacturer.city}</td>
					<td class="leftNoBold">${manufacturer.state}</td>
					<td class="leftNoBold">${manufacturer.zip}</td>
					<td class="leftNoBold">${manufacturer.zipExt}</td>
					<td class="leftNoBold"><input type="button" value="Update" onclick="javascript:jsManufacturerUorDSubmit('${pageContext.request.contextPath}/manufacturer/displayUpdateManufacturer','${manufacturer.manufacturerId}');" /></td>
					<td class="leftNoBold"><input type="button" value="Delete" onclick="javascript:jsManufacturerUorDSubmit('${pageContext.request.contextPath}/manufacturer/displayDeleteManufacturer','${manufacturer.manufacturerId}');" /></td>											
				</tr>																		
			</c:forEach>								
		</table>	
	</c:if>
	<input type="hidden" name="manufacturerId" />
</form:form>
</body>
</html>


