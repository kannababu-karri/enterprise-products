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

<form:form action="/user/userSearch" method="post" modelAttribute="userForm.user" id="userForm">
	<p class="searchTitle">&nbsp;&nbsp;User Interface: Home</p>
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
		<input type="button" value="Add User" onclick="javascript:jsUserSubmit('/user/displayNewUser');" />&nbsp;
		<input type="button" value="Return IL Home" onclick="javascript:jsUserSubmit('/user/returnILHome');" />
	</p>
	
	<!-- global errors -->
    <form:errors path="*" cssClass="error-msg" element="div"/>
	<table width="100%">			
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>User Name:</b></td>
			<td width="30%" align="left">
				<input type="text" name="userName" value="${userForm.user.userName}" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>			
			<td width="20%" align="right"><b>Role:</b></td>
			<td width="30%" align="left">
			    <select name="role" id="role">
			        <option value="">Choose one...</option>
			        <c:forEach items="${userForm.roles}" var="role" varStatus="status">
			            <option value="${role}" <c:if test="${userForm.user.role == role}">selected</c:if>>${role}</option>
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
				<input type="submit" value="Submit" />&nbsp;&nbsp;<input type="button" value="Cancel" onclick="javascript:jsUserSubmit('/user/returnILHome');" />
			</td>
			<td width="49%">&nbsp;</td>
		</tr>				
		<tr>
			<td width="100%" colspan="4"><hr /></td>
		</tr>	
	</table>
	<!-- Display search results -->
	<c:if test="${userForm.showDetails}">
		<p class="searchTitle">&nbsp;&nbsp;User Search Results:</p>
		<table class="searchDetail">			
			<tr>				
				<th>User name</th>				
				<th>Role</th>
				<th>Question 1</th>
				<th>Answer 1</th>
				<th>Question 2</th>
				<th>Answer 2</th>
				<th>Update User</th>
				<th>Delete User</th>										
			</tr>
			<c:forEach items="${userForm.resultUsers}" var="user" varStatus="status">		
				<tr class="detailData">								
					<td class="center">${user.userName}</td>
					<td class="leftNoBold">${user.role}</td>
					<td class="leftNoBold">${user.question1}</td>
					<td class="leftNoBold">${user.answer1}</td>
					<td class="leftNoBold">${user.question2}</td>
					<td class="leftNoBold">${user.answer2}</td>
					<td class="leftNoBold"><input type="button" value="Update" onclick="javascript:jsUserUorDSubmit('/user/displayUpdateUser','${user.userId}');" /></td>
					<td class="leftNoBold"><input type="button" value="Delete" onclick="javascript:jsUserUorDSubmit('/user/displayDeleteUser','${user.userId}');" /></td>											
				</tr>																		
			</c:forEach>								
		</table>	
	</c:if>
	<input type="hidden" name="userId" />
</form:form>
</body>
</html>


