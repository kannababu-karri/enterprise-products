<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ILabs: Product</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/common/header.jsp" flush="true" />

<form:form action="${pageContext.request.contextPath}/product/productSearch" method="post" modelAttribute="productForm.product" id="productForm">
	<p class="searchTitle">&nbsp;&nbsp;Product Interface: Home</p>
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
		<input type="button" value="Add Product" onclick="javascript:jsProductSubmit('${pageContext.request.contextPath}/product/displayNewProduct');" />&nbsp;
		<input type="button" value="Return IL Home" onclick="javascript:jsProductSubmit('${pageContext.request.contextPath}/product/returnILHome');" />
	</p>
	
	<!-- global errors -->
    <form:errors path="*" cssClass="error-msg" element="div"/>
	<table width="100%">			
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Product Name:</b></td>
			<td width="30%" align="left">
				<input type="text" name="productName" value="${productForm.product.productName}" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Product Description:</b></td>
			<td width="30%" align="left">
				<input type="text" name="productDescription" value="${productForm.product.productDescription}" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Cas Number:</b></td>
			<td width="30%" align="left">
				<input type="text" name="casNumber" value="${productForm.product.casNumber}" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>		
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="50%" align="center" colspan="2">
				<input type="submit" value="Submit" />&nbsp;&nbsp;<input type="button" value="Cancel" onclick="javascript:jsProductSubmit('${pageContext.request.contextPath}/product/returnILHome');" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>				
		<tr>
			<td width="100%" colspan="4"><hr /></td>
		</tr>	
	</table>
	<!-- Display search results -->
	<c:if test="${productForm.showDetails}">
		<p class="searchTitle">&nbsp;&nbsp;Product Search Results:</p>
		<table class="searchDetail">			
			<tr>				
				<th>Product name</th>				
				<th>Product description</th>
				<th>Cas Number</th>
				<th>Update</th>
				<th>Delete</th>								
			</tr>
			<c:forEach items="${productForm.resultProducts}" var="product" varStatus="status">		
				<tr class="detailData">								
					<td class="center">${product.productName}</td>
					<td class="leftNoBold">${product.productDescription}</td>
					<td class="leftNoBold">${product.casNumber}</td>
					<td class="leftNoBold"><input type="button" value="Update" onclick="javascript:jsProductUorDSubmit('${pageContext.request.contextPath}/product/displayUpdateProduct','${product.productId}');" /></td>
					<td class="leftNoBold"><input type="button" value="Delete" onclick="javascript:jsProductUorDSubmit('${pageContext.request.contextPath}/product/displayDeleteProduct','${product.productId}');" /></td>											
				</tr>																		
			</c:forEach>								
		</table>	
	</c:if>
	<input type="hidden" name="productId" />
</form:form>
</body>
</html>


