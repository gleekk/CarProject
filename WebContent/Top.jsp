<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<style type="text/css">
	/*하이퍼 링크 밑줄 없앰 , 글자색 #333*/
	#login a{
		text-decoration: none;
		color: #333;
	}
	
	/*하이퍼링크 hover 글자색 #F90 */
	#login a:hover {color: #F90}

	/*로고 이미지 부분*/
	#logo{
		float: left; 
		width: 265px;
		margin: 60px 0 0 40px;
	}

</style>

</head>
<body>
	<!-- 메인로고 이미지를 누르면 메인페이지로 이동하도록 하기  -->
	<div id="logo">
		<a href="./Main.me">
			<img src="img/RENT.jpg" width="300" height="80">
		</a>
	</div>
	<!-- 로그인 | 회원가입  링크-->
	<table width="1000" height="5">
		<tr>
			<td align="right" colspan="5">
			<%
				//세션값 전달 받기 
				String id = (String)session.getAttribute("id");
			
				if(id == null){//세션값 없으면.. 로그인 | 회원가입 링크가 나오도록 처리 
			%>	
				<div id="login">
					<a href="./MemberLogin.me">로그인</a> |
					<a href="./MemberJoin.me">회원가입</a>
				</div>
				
			<% 		
				}else{//세션값이 있으면  로그아웃 링크가 나오도록 
			%>	
				<div id="login">
					<%=id %>님 환영합니다. <a href="./MemberLogout.me">로그아웃</a>
				</div>			
			<%		
				}
			%>
			
			</td>
		</tr>
	</table>
	
	
	
	
	
	<!-- 메뉴 만들기  -->
	<table width="1000" background="img/aa.jpg" height="5">
		<tr>
			<td align="center" bgcolor="red">
				<a href="CarMain.jsp?center=CarReservation.jsp">
					<img src="img/bb.jpg"> <!-- 예약하기 메뉴 이미지 -->
				</a>
			</td>
			<td align="center" bgcolor="red">
				<a href="CarMain.jsp?center=CarReserveConfirm.jsp">
					<img src="img/cc.jpg"> <!-- 예약확인 메뉴 이미지  -->
				</a>
			</td>
			<td align="center" bgcolor="red">
				<a href="CarMain.jsp?center=BoardlistController.do"> <!-- 자유게시판 관련 컨트롤러 클래스를 요청할 가상주소 -->
					<img src="img/dd.jpg"> <!-- 자유게시판 메뉴 이미지  -->
				</a>
			</td>
			<td align="center" bgcolor="red">
				<a href="CarMain.jsp?center=CarEven.jsp"> 
					<img src="img/even.jpg"> <!-- 이벤트 메뉴 이미지  -->
				</a>
			</td>
			<td align="center" bgcolor="red">
				<a href="CarMain.jsp?center=AdminBoardListController.do"> <!-- 공지사항 관련 게시판 컨트롤러 요청 --> 
					<img src="img/ee.jpg"> <!-- 고객센터 메뉴 이미지  -->
				</a>
			</td>			
			
		
		</tr>
	
	
	</table>
</body>
</html>





















