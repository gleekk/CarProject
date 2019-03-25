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
		<img  src="img/naeyeok.jpg">
		<p></p>
		<table width="1000" border="1" align="center">
			<tr align="center">
				<td align="center" width="150">차량이미지</td>
				<td align="center" width="100">차량명</td>
				<td align="center" width="100">대여일</td>
				<td align="center" width="50">대여기간</td>
				<td align="center" width="100">차량금액</td>
				<td align="center" width="70">보험여부</td>
				<td align="center" width="70">무선WIFI</td>
				<td align="center" width="70">네비게이션</td>
				<td align="center" width="70">베이비시트</td>
				<td align="center" width="100">수정</td>
				<td align="center" width="100">삭제(취소)</td>				
			</tr>
		<c:forEach var="carConfirmBean" items="${requestScope.v}">
			<tr align="center">
				<td align="center" width="150">
					<img src="img/${carConfirmBean.carimg}" width="140" height="90">
				</td>
				<td align="center" width="100">${carConfirmBean.carname}</td>
				<td align="center" width="100">${carConfirmBean.carbegindate}</td>
				<td align="center" width="50">${carConfirmBean.carreserveday}</td>
				<td align="center" width="100">${carConfirmBean.carprice}</td>
				<td align="center" width="70">
					<c:if test="${carConfirmBean.carins == 1}">
						보험가입
					</c:if>
					<c:if test="${carConfirmBean.carins == 0}">
						보험미가입
					</c:if>	
				</td>
				<td align="center" width="70">
					<c:if test="${carConfirmBean.carwifi == 1}">
						대여
					</c:if>
					<c:if test="${carConfirmBean.carwifi == 0}">
						미대여
					</c:if>									
				</td>
				<td align="center" width="70">
					<c:if test="${carConfirmBean.carnave == 1}">
						대여
					</c:if>
					<c:if test="${carConfirmBean.carnave == 0}">
						미대여
					</c:if>						
				</td>
				<td align="center" width="70">
					<c:if test="${carConfirmBean.carbabyseat == 1}">
						대여
					</c:if>
					<c:if test="${carConfirmBean.carbabyseat == 0}">
						미대여
					</c:if>					
				</td>
				<td align="center" width="100">
					<button onclick="location.href='CarConfirmUpdateController.do?orderid=${carConfirmBean.orderid}&carimg=${carConfirmBean.carimg}'">
						수정
					</button>	
				</td>
				<td align="center" width="100">
					<button onclick="location.href='CarMain.jsp?center=CarConfirmDelete.jsp&orderid=${carConfirmBean.orderid}'">
						삭제
					</button>						
				</td>				
			</tr>			 
		
		</c:forEach>

		</table>
	
	
	</center>

</body>
</html>







