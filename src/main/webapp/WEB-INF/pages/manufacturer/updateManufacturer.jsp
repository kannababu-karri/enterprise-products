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

<form:form action="${pageContext.request.contextPath}/manufacturer/updateManufacturer" method="post" modelAttribute="manufacturerForm.manufacturer" id="manufacturerForm">
	<p class="searchTitle">&nbsp;&nbsp;Manufacturer Interface: Update Manufacturer</p>
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
			<td width="30%" align="left"><input type="text" name="mfgName" value="${manufacturerForm.manufacturer.mfgName}" /></td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Address 1:</b></td>
			<td width="30%" align="left"><input type="text" name="address1" value="${manufacturerForm.manufacturer.address1}" /></td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Address 2:</b></td>
			<td width="30%" align="left"><input type="text" name="address2" value="${manufacturerForm.manufacturer.address2}" /></td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>City:</b></td>
			<td width="30%" align="left"><input type="text" name="city" value="${manufacturerForm.manufacturer.city}" maxlength="50" /></td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>State:</b></td>
			<td width="30%" align="left"><input type="text" name="state" value="${manufacturerForm.manufacturer.state}" maxlength="2"/></td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Zip:</b></td>
			<td width="30%" align="left"><input type="text" name="zip" value="${manufacturerForm.manufacturer.zip}" maxlength="5" /></td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Zip Ext:</b></td>
			<td width="30%" align="left"><input type="text" name="zipExt" value="${manufacturerForm.manufacturer.zipExt}" maxlength="4"/></td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>		
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="50%" align="center" colspan="2">
				<input type="submit" value="Submit" />&nbsp;&nbsp;<input type="button" value="Cancel" onclick="javascript:jsManufacturerSubmit('${pageContext.request.contextPath}/manufacturer/showManufacturerDetails');" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>					
	</table>
	<input type="hidden" name="manufacturerId" value="${manufacturerForm.manufacturer.manufacturerId}" />
</form:form>
</body>
</html>


