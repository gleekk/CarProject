<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<style type="text/css">
	/*������ ��ũ ���� ���� , ���ڻ� #333*/
	#login a{
		text-decoration: none;
		color: #333;
	}
	
	/*�����۸�ũ hover ���ڻ� #F90 */
	#login a:hover {color: #F90}

	/*�ΰ� �̹��� �κ�*/
	#logo{
		float: left; 
		width: 265px;
		margin: 60px 0 0 40px;
	}

</style>

</head>
<body>
	<!-- ���ηΰ� �̹����� ������ ������������ �̵��ϵ��� �ϱ�  -->
	<div id="logo">
		<a href="./Main.me">
			<img src="img/RENT.jpg" width="300" height="80">
		</a>
	</div>
	<!-- �α��� | ȸ������  ��ũ-->
	<table width="1000" height="5">
		<tr>
			<td align="right" colspan="5">
			<%
				//���ǰ� ���� �ޱ� 
				String id = (String)session.getAttribute("id");
			
				if(id == null){//���ǰ� ������.. �α��� | ȸ������ ��ũ�� �������� ó�� 
			%>	
				<div id="login">
					<a href="./MemberLogin.me">�α���</a> |
					<a href="./MemberJoin.me">ȸ������</a>
				</div>
				
			<% 		
				}else{//���ǰ��� ������  �α׾ƿ� ��ũ�� �������� 
			%>	
				<div id="login">
					<%=id %>�� ȯ���մϴ�. <a href="./MemberLogout.me">�α׾ƿ�</a>
				</div>			
			<%		
				}
			%>
			
			</td>
		</tr>
	</table>
	
	
	
	
	
	<!-- �޴� �����  -->
	<table width="1000" background="img/aa.jpg" height="5">
		<tr>
			<td align="center" bgcolor="red">
				<a href="CarMain.jsp?center=CarReservation.jsp">
					<img src="img/bb.jpg"> <!-- �����ϱ� �޴� �̹��� -->
				</a>
			</td>
			<td align="center" bgcolor="red">
				<a href="CarMain.jsp?center=CarReserveConfirm.jsp">
					<img src="img/cc.jpg"> <!-- ����Ȯ�� �޴� �̹���  -->
				</a>
			</td>
			<td align="center" bgcolor="red">
				<a href="CarMain.jsp?center=BoardlistController.do"> <!-- �����Խ��� ���� ��Ʈ�ѷ� Ŭ������ ��û�� �����ּ� -->
					<img src="img/dd.jpg"> <!-- �����Խ��� �޴� �̹���  -->
				</a>
			</td>
			<td align="center" bgcolor="red">
				<a href="CarMain.jsp?center=CarEven.jsp"> 
					<img src="img/even.jpg"> <!-- �̺�Ʈ �޴� �̹���  -->
				</a>
			</td>
			<td align="center" bgcolor="red">
				<a href="CarMain.jsp?center=AdminBoardListController.do"> <!-- �������� ���� �Խ��� ��Ʈ�ѷ� ��û --> 
					<img src="img/ee.jpg"> <!-- ������ �޴� �̹���  -->
				</a>
			</td>			
			
		
		</tr>
	
	
	</table>
</body>
</html>





















