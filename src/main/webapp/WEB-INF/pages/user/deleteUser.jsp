<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ILabs: User</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/common/header.jsp" flush="true" />

<form:form action="/user/deleteUser" method="post" modelAttribute="userForm.user" id="userForm">
	<p class="searchTitle">&nbsp;&nbsp;User Interface: Delete User</p>
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
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>User Name:</b></td>
			<td width="30%" align="left">${userForm.user.userName}</td>
			<td width="49%">&nbsp;</td>
		</tr>	
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Role:</b></td>
			<td width="30%" align="left">
			    <select name="role" id="role">
			        <option value="">Choose one...</option>
			        <c:forEach items="${userForm.roles}" var="role" varStatus="status">
			            <option value="${role}" <c:if test="${userForm.user.role.toLowerCase() == role.toLowerCase()}">selected</c:if>>${role}</option>
			        </c:forEach>
		    	</select>
			</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Question 1:</b></td>
			<td width="30%" align="left">${userForm.user.question1}</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Answer 1:</b></td>
			<td width="30%" align="left">${userForm.user.answer1}</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Question 2:</b></td>
			<td width="30%" align="left">${userForm.user.question2}</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Answer 2:</b></td>
			<td width="30%" align="left">${userForm.user.answer2}</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>		
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="50%" align="center" colspan="2">
				<input type="submit" value="Delete" />&nbsp;&nbsp;<input type="button" value="Cancel" onclick="javascript:jsUserSubmit('/user/showUserDetails');" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>				
	</table>
	<input type="hidden" name="userId" value="${userForm.user.userId}" />
</form:form>
</body>
</html>


