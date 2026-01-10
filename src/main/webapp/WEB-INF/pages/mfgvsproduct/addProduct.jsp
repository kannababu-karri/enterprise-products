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

<form:form action="/product/saveNewProduct" method="post" modelAttribute="productForm.product" id="productForm">
	<p class="searchTitle">&nbsp;&nbsp;Product Interface: Add New Product</p>
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
			<td width="20%" align="right"><b>Product Name:</b></td>
			<td width="30%" align="left"><input type="text" name="productName" value="${productForm.product.productName}" /></td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Product Description:</b></td>
			<td width="30%" align="left"><input type="text" name="productDescription" value="${productForm.product.productDescription}" /></td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Cas Number:</b></td>
			<td width="30%" align="left"><input type="text" name="casNumber" value="${productForm.product.casNumber}" /></td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>		
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="50%" align="center" colspan="2">
				<input type="submit" value="Submit" />&nbsp;&nbsp;<input type="button" value="Cancel" onclick="javascript:jsProductSubmit('/product/showProductDetails');" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>				
	</table>
</form:form>
</body>
</html>


