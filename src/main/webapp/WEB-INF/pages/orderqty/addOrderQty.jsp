<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ILabs: Order</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/common/header.jsp" flush="true" />

<form:form action="${pageContext.request.contextPath}/orderqty/saveNewOrderQty" method="post" modelAttribute="orderQtyForm.orderQty" id="orderQtyForm">
	<p class="searchTitle">&nbsp;&nbsp;Order Interface: Add New Order</p>
		<p class="searchTitle">
		<c:if test="${not empty errors}">
		    <ul style="color: red;">
		        <c:forEach items="${errors}" var="err">
		            <li>${err}</li>
		        </c:forEach>
		    </ul>
		</c:if>
	</p>

	<!-- global errors -->
    <form:errors path="*" cssClass="error-msg" element="div"/>
	<table width="100%">		
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>	
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Manufacturer Name:</b></td>
			<td width="30%" align="left">
				<select name="manufacturer.manufacturerId" id="manufacturer.manufacturerId">
			        <option value="">Choose one...</option>
			        <c:forEach items="${orderQtyForm.manufacturers}" var="manufacturer" varStatus="status">
			            <option value="${manufacturer.manufacturerId}" <c:if test="${orderQtyForm.manufacturer.manufacturerId == manufacturer.manufacturerId}">selected</c:if>>${manufacturer.mfgName}</option>
			        </c:forEach>
	    		</select>
			</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Product Name:</b></td>
			<td width="30%" align="left">
				<select name="product.productId" id="product.productId">
			        <option value="">Choose one...</option>
			        <c:forEach items="${orderQtyForm.products}" var="product" varStatus="status">
			            <option value="${product.productId}" <c:if test="${orderQtyForm.product.productId == product.productId}">selected</c:if>>${product.productName}</option>
			        </c:forEach>
	    		</select>
			</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Quantity:</b></td>
			<td width="30%" align="left"><input type="text" name="quantity" value="${orderQtyForm.orderQty.quantity}" /></td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>		
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="50%" align="center" colspan="2">
				<input type="submit" value="Submit" />&nbsp;&nbsp;<input type="button" value="Cancel" onclick="javascript:jsOrderQtySubmit('${pageContext.request.contextPath}/orderqty/showOrderQtyDetails');" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>				
	</table>
</form:form>
</body>
</html>


