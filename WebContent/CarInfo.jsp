<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--  JSTL태그중 core라이브러리에 속해 있는 태그를 사용하기 위한 선언 -->    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<img alt="" src="img/cis.jpg" border="0">
		
		<!-- 자동차 렌트 예약시 옵션 선택하기 버튼을 눌렀을떄.. 옵션을 선택 할수 있는 화면으로 이동하라라는 요청 -->
		<form action="CarMain.jsp?center=CarOption.jsp" method="post">
			
			<!-- 옵션 선택하기 버튼을 클릭했을떄... 예약할 차번호, 차이미지명, 차가격 넘기기  -->
			<input type="hidden" name="carno" value="${bean.carno}">
			<input type="hidden" name="carimg" value="${bean.carimg}">
			<input type="hidden" name="carprice" value="${bean.carprice}">
			
		
			<table width="1000" border="0">
				<tr align="center">
					<td rowspan="6" width="600">
						<img src="img/${bean.carimg}" width="500" border="0">
					</td>
					<td align="center" width="200">차량 이름</td>
					<td align="center" width="200">${bean.carname}</td>
				</tr>
				<tr>
					<td align="center" width="200">대여수량</td>
					<td align="center" width="200">
						<select name="carqty">
							<option value="1">1대</option>
							<option value="2">2대</option>
							<option value="3">3대</option>
							<option value="4">4대</option>
							<option value="5">5대</option>																												
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" width="200">차량분류</td>
					<td align="center" width="200">${requestScope.bean.carcategory}</td>		
				</tr>
				<tr>
					<td align="center" width="200">대여금액</td>
					<td align="center" width="200">${bean.carprice}</td>		
				</tr>
				<tr>
					<td align="center" width="200">제조회사</td>
					<td align="center" width="200">${bean.carcompany}</td>		
				</tr>				
				<tr>
					<td align="center" width="200">
						<input type="button" value="이전" onclick="location.href='CarListController.do'">
					</td>
					<td align="center" width="200">
						<input type="submit" value="옵션 선택하기">
					</td>		
				</tr>				
			
			</table>
		</form>
		<p>
			<b>차량 정보 상세 보기</b>
			${bean.carinfo}
		</p>
	</center>
</body>
</html>








