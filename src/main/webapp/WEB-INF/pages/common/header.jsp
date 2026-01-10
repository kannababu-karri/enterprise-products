<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.enterprise.products.utils.Utils" %>
<%@ page import="com.enterprise.products.utils.StringUtility" %>
<%		
	String loginUser = Utils.getLoginUserName(request);
%>
<link type="text/css" rel="stylesheet" href="<c:url value='/styles/main.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/il-web.js'/>"></script>
<table class="headerMain" >
	<tr class="headerMainTr">
		<td width="25%"><img src="<c:url value='/images/il.gif'/>" alt="innovareLabs" height="100"></td>
		<td width="50%" align="center" class="headerMainTd" height="40%">Innovare Labs</td>
		<td width="25%" height="40%">&nbsp;</td>
	</tr>
	<tr class="loginUserMainTr">
		<td width="25%">&nbsp;</td>
		<td width="50%" align="center" class="loginUserMainTd">
			<%
				if(!StringUtility.isEmpty(loginUser)) {
			%>
					Login User: <%= loginUser %>
			<%
				}
			%>&nbsp;					
		</td>
		<td width="25%">
			<%
				if(!StringUtility.isEmpty(loginUser)) {
			%>
					<a href="<c:url value='/logout' />">Logout</a>
			<%
				}
			%>&nbsp;
		</td>
	</tr>
	<tr>
		<td width="100%" colspan="3"><hr /></td>
	</tr>	
</table>