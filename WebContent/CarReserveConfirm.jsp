<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<center>
		<%-- <����Ȯ��> �̹��� --%>
		<img src="img/hwakin.jpg">
	
	<form action="CarReserveConfirmController.do" method="post">	
		<table width="400" border="0">
			<tr height="60" align="center">
				<td align="center" width="200">��ȭ��ȣ �Է� : </td>
				<td align="center" width="200"><input type="text" name="memberphone"></td>
			</tr>
			<tr height="60" align="center">
				<td align="center" width="200">��й�ȣ �Է� : </td>
				<td align="center" width="200"><input type="password" name="memberpass"></td>
			</tr>
			<tr height="60" align="center">
				<td colspan="2" align="center"> <input type="submit" value="�˻�"> </td>
			</tr>
		</table>
	</form>	
		
	</center>



</body>
</html>