<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ILabs: Manufacturer vs Product</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/common/header.jsp" flush="true" />

<form:form action="/mfgvsproduct/mfgvsproductSearch" method="post" modelAttribute="mfgvsproductForm.mfgvsproduct" id="mfgvsproductForm">
	<p class="searchTitle">&nbsp;&nbsp;Manufacturer vs Product Interface: Home</p>
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
		<input type="button" value="Add Manufacturer vs Product" onclick="javascript:jsMfgvsproductSubmit('/mfgvsproduct/displayNewMfgvsproduct');" />&nbsp;
		<input type="button" value="Return IL Home" onclick="javascript:jsMfgvsproductSubmit('/mfgvsproduct/returnILHome');" />
	</p>
	
	<!-- global errors -->
    <form:errors path="*" cssClass="error-msg" element="div"/>
	<table width="100%">	
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Manufacturer Name:</b></td>
			<td width="30%" align="left">
				<input type="text" name="manufacturerId" value="" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>		
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Product Name:</b></td>
			<td width="30%" align="left">
				<input type="text" name="productId" value="" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>		
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="50%" align="center" colspan="2">
				<input type="submit" value="Submit" />&nbsp;&nbsp;<input type="button" value="Cancel" onclick="javascript:jsMfgvsproductSubmit('/mfgvsproduct/returnILHome');" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>				
		<tr>
			<td width="100%" colspan="4"><hr /></td>
		</tr>	
	</table>
	<!-- Display search results -->
	<c:if test="${mfgvsproductForm.showDetails}">
		<p class="searchTitle">&nbsp;&nbsp;Manufacturer vs Product Search Results:</p>
		<table class="searchDetail">			
			<tr>				
				<th>Manufacturer name</th>				
				<th>Product name</th>
				<th>Update</th>
				<th>Delete</th>								
			</tr>
			<c:forEach items="${mfgvsproductForm.resultMfgVsProducts}" var="mfgvsproduct" varStatus="status">		
				<tr class="detailData">								
					<td class="center">${mfgvsproduct.manufacturer.productName}</td>
					<td class="leftNoBold">
						<c:forEach items="${mfgvsproduct.manufacturer.products}" var="product" varStatus="status">
			            	${product.productName}
			        	</c:forEach>
					</td>
					<td class="leftNoBold"><input type="button" value="Update" onclick="javascript:jsMfgvsproductUorDSubmit('/mfgvsproduct/displayUpdateMfgvsproduct','${mfgvsproduct.mfgvsproductId}');" /></td>
					<td class="leftNoBold"><input type="button" value="Delete" onclick="javascript:jsMfgvsproductUorDSubmit('/mfgvsproduct/displayDeleteMfgvsproduct','${mfgvsproduct.mfgvsproductId}');" /></td>											
				</tr>																		
			</c:forEach>								
		</table>	
	</c:if>
	<input type="hidden" name="mfgvsproductId" />
</form:form>
</body>
</html>


