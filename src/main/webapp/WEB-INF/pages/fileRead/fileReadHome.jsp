<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ILabs: File</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/common/header.jsp" flush="true" />

<form:form action="/fileRead/fileReadSearch" method="post" modelAttribute="fileReadForm.orderDocument" id="fileReadForm">
	<p class="searchTitle">&nbsp;&nbsp;File Interface: Home</p>
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
		<input type="button" value="Return IL Home" onclick="javascript:jsFileReadSubmit('/fileRead/returnILHome');" />
	</p>
	
	<!-- global errors -->
    <form:errors path="*" cssClass="error-msg" element="div"/>

	<!-- Display search results -->
	<c:if test="${fileReadForm.showDetails}">
		<p class="searchTitle">&nbsp;&nbsp;File Data Results:</p>
		<table class="searchDetail">			
			<tr>
				<th>Type</th>
				<th>Order id</th>
				<th>Mfg id</th>
				<th>Mfg name</th>	
				<th>Product id</th>				
				<th>Product name</th>				
				<th>Product description</th>
				<th>Cas Number</th>
				<th>Quantity</th>
				<th>Created date</th>								
			</tr>
			<c:forEach items="${fileReadForm.resultOrderDocuments}" var="orderDocument" varStatus="status">		
				<tr class="detailData">		
					<td class="leftNoBold">${orderDocument.documentType}</td>	
					<td class="leftNoBold">${orderDocument.orderQtyId}</td>					
					<td class="leftNoBold">${orderDocument.manufacturerId}</td>
					<td class="leftNoBold">${orderDocument.mfgName}</td>
					<td class="leftNoBold">${orderDocument.productId}</td>
					<td class="leftNoBold">${orderDocument.productName}</td>
					<td class="leftNoBold">${orderDocument.productDescription}</td>
					<td class="leftNoBold">${orderDocument.casNumber}</td>
					<td class="leftNoBold">${orderDocument.quantity}</td>
					<td class="leftNoBold">${orderDocument.createdAt}</td>
				</tr>																
			</c:forEach>								
		</table>	
	</c:if>
</form:form>
</body>
</html>


