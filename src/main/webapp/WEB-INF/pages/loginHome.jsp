<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ILabs: Login</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/common/header.jsp" flush="true" />

<form:form action="${pageContext.request.contextPath}/login" method="post" modelAttribute="user" id="loginForm">
	<p class="searchTitle">&nbsp;&nbsp;Innovare Labs: Login</p>
	<p class="searchTitle">&nbsp;&nbsp;Please enter login credentials.</p>
	<p class="searchTitle">
		<c:if test="${not empty error}">
		    <div class="error-msg" style="color:red;">
		        &nbsp;&nbsp;${error}
		    </div>
		</c:if>
	</p>
	
	<!-- global errors -->
    <form:errors path="*" cssClass="error-msg" element="div"/>

	<table class="login-table">			
		<tr>			
			<td width="25%" align="right"><b>User Id:</b>&nbsp;&nbsp;</td>
			<td width="75%" align="left"><form:input path="userName" /></td>
		</tr>
		<tr>
			<td width="100%" colspan="2">&nbsp;</td>
		</tr>		
		<tr>
			<td width="25%" align="right"><b>Password:</b>&nbsp;&nbsp;</td>
			<td width="75%" align="left"><form:password path="password" /></td>			
		</tr>
		<tr>
			<td width="100%" colspan="2">&nbsp;</td>
		</tr>
		<tr>					
			<td width="25%" align="right">&nbsp;&nbsp;</td>
			<td width="75%" align="left"><input type="submit" value="Submit" />&nbsp;&nbsp;<input type="button" value="Reset" onclick="javascript:jsResetHome('${pageContext.request.contextPath}/login/reset');" /></td>
		</tr>		
	</table>	
</form:form>
</body>
</html>


