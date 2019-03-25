<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%-- JSTL CORE���̺귯�� �±� ����� ���� ���� --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%-- 	
	<%
		String center = request.getParameter("center");
		      
		      
		if(center == null){
			center = "Center.jsp";
		}
	%>
	
 --%>	
 	<%--
 		���� ������ ����ڷ� ���� ����ؼ� ������ �ٲ�� �κ� �̱⿡ �ش� Center.jsp�� ������ �о� �鿩�� �Ѵ�.
 	 --%>							
 	<c:set var="center" value="${param.center}"/>
 	
 	<c:out value="${center}"/>
 	
 	
 	<%-- ó�� CarMain.jsp�������� �����ϸ� �翬�� ~ param.center����? null�̱⿡ ���� �߰�  --%>
 	<c:if test="${center == null }">
 		<c:set var="center" value="Center.jsp"/>
 	</c:if>
 	

	<center>
		<table  width="1000" height="700">
			<tr align="center">
				<td>
					<jsp:include page="Top.jsp"/>  <!-- ��� ���� ���� -->
				</td>
			</tr>
			<tr>
				<td height="500">
					<jsp:include page="${center}"/> <!-- ��� ���� -->
				</td>
			</tr>
			<tr>
				<td>
					<jsp:include page="Bottom.jsp"/> <!-- �ϴ� ���� ���� -->
				</td>
			</tr>
		</table>	
	
	</center>

</body>
</html>





