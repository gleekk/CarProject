<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<center>
		<h1>차량 주문 정보 삭제 (취소)</h1>
	
	<form action="CarConfirmDeleteController.do" method="post">
		<table width="400" border="0">
			
			<c:set var="result" value="${requestScope.result}"/>
		
			<c:if test="${result == null }">
				<c:set var="result" value="${1}"/>
			</c:if>
			
			<c:if test="${result == 0 }">
				<script>
					alert("비밀번호가 틀립니다.");
				</script>
			</c:if>
		
		
			<tr align="center">
				<td align="center">
							   <input type="hidden" value="${param.orderid }" name="orderid" >
					비밀번호 입력 : <input type="password" name="memberpass"> &nbsp;&nbsp;&nbsp;
							  <input type="submit" value="취소(삭제)">
				</td>
			</tr>	
		
		</table>
	</form>
	
	</center>



</body>
</html>


