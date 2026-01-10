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

<form:form action="${pageContext.request.contextPath}/manufacturer/deleteManufacturer" method="post" modelAttribute="manufacturerForm.manufacturer" id="manufacturerForm">
	<p class="searchTitle">&nbsp;&nbsp;Manufacturer Interface: Delete Manufacturer</p>
		<p class="searchTitle">
		<c:if test="${not empty error}">
		    <div class="error-msg" style="color:red;">
		        &nbsp;&nbsp;${error}
		    </div>
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
			<td width="30%" align="left">${manufacturerForm.manufacturer.mfgName}</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Address 1:</b></td>
			<td width="30%" align="left">${manufacturerForm.manufacturer.address1}</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Address 2:</b></td>
			<td width="30%" align="left">${manufacturerForm.manufacturer.address2}</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>City:</b></td>
			<td width="30%" align="left">${manufacturerForm.manufacturer.city}</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>State:</b></td>
			<td width="30%" align="left">${manufacturerForm.manufacturer.state}</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Zip:</b></td>
			<td width="30%" align="left">${manufacturerForm.manufacturer.zip}</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Zip Ext:</b></td>
			<td width="30%" align="left">${manufacturerForm.manufacturer.zipExt}</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>		
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="50%" align="center" colspan="2">
				<input type="submit" value="Delete" />&nbsp;&nbsp;<input type="button" value="Cancel" onclick="javascript:jsManufacturerSubmit('${pageContext.request.contextPath}/manufacturer/showManufacturerDetails');" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>				
	</table>
	<input type="hidden" name="manufacturerId" value="${manufacturerForm.manufacturer.manufacturerId}" />
</form:form>
</body>
</html>


