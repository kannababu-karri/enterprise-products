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

<form:form action="${pageContext.request.contextPath}/orderqty/orderQtySearch" method="post" modelAttribute="orderQtyForm.orderQty" id="orderQtyForm">
	<p class="searchTitle">&nbsp;&nbsp;Order Interface: Home</p>
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
		<input type="button" value="Add Order" onclick="javascript:jsOrderQtySubmit('${pageContext.request.contextPath}/orderqty/displayNewOrderQty');" />&nbsp;
		<input type="button" value="Return IL Home" onclick="javascript:jsOrderQtySubmit('${pageContext.request.contextPath}/orderqty/returnILHome');" />
	</p>
	
	<!-- global errors -->
    <form:errors path="*" cssClass="error-msg" element="div"/>
	<table width="100%">			
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
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>		
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="50%" align="center" colspan="2">
				<input type="submit" value="Submit" />&nbsp;&nbsp;<input type="button" value="Cancel" onclick="javascript:jsOrderQtySubmit('${pageContext.request.contextPath}/orderqty/returnILHome');" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>				
		<tr>
			<td width="100%" colspan="4"><hr /></td>
		</tr>	
	</table>
	<!-- Display search results -->
	<c:if test="${orderQtyForm.showDetails}">
		<p class="searchTitle">&nbsp;&nbsp;Order Search Results:</p>
		<table class="searchDetail">			
			<tr>
				<th>Manufacturer name</th>				
				<th>Product name</th>				
				<th>Product description</th>
				<th>Cas Number</th>
				<th>Quantity</th>
				<th>Status</th>
				<th>Update</th>
				<th>Delete</th>								
			</tr>
			<c:forEach items="${orderQtyForm.resultOrderQtys}" var="orderQty" varStatus="status">		
				<tr class="detailData">		
					<td class="center">${orderQty.manufacturer.mfgName}</td>						
					<td class="center">${orderQty.product.productName}</td>
					<td class="leftNoBold">${orderQty.product.productDescription}</td>
					<td class="leftNoBold">${orderQty.product.casNumber}</td>
					<td class="leftNoBold">${orderQty.quantity}</td>
					<td class="leftNoBold">${orderQty.status}</td>
					<td class="leftNoBold"><input type="button" value="Update" onclick="javascript:jsOrderQtyUorDSubmit('${pageContext.request.contextPath}/orderqty/displayUpdateOrderQty','${orderQty.orderId}');" /></td>
					<td class="leftNoBold"><input type="button" value="Delete" onclick="javascript:jsOrderQtyUorDSubmit('${pageContext.request.contextPath}/orderqty/displayDeleteOrderQty','${orderQty.orderId}');" /></td>											
				</tr>																		
			</c:forEach>								
		</table>	
	</c:if>
	<input type="hidden" name="orderId" />
</form:form>
</body>
</html>


