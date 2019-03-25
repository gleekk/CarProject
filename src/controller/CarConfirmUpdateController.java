package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CarConfirmBean;
import db.CarDAO;


//CarReserveResult.jsp페이지에서 ..수정버튼을 클릭했을떄.. 예약된 차량의 주문 id, 예약된 차량의 이미지 이름을 전달받는 서블릿 
@WebServlet("/CarConfirmUpdateController.do")
public class CarConfirmUpdateController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트의 요청값 전달 받기
		String carimg = request.getParameter("carimg");
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		
		//데이터 베이스 작업을 위한 자바빈 객체 생성
		CarDAO cdao = new CarDAO();
		//예약한 주문 id를 전달하여 하나의 주문정보를 검색해서 가져오는 메소드 호출
		CarConfirmBean cbean = cdao.getOneOrder(orderid);
		//차량 이미지명을  CarConfirmBean객체에 저장
		cbean.setCarimg(carimg);
		
		
		//request영역에 응답결과를 저장
		//참고 : 차량 주문 정보 수정 페이지로 응답데이터를 전달 해야함.
		//대여기간, 대여일, 차량 수량, 보험적용, 무선 WIFI, 베이비시트 등을 저장하고 있는 CarConfirmBean객체를 request에 담기
		request.setAttribute("cbean",cbean);
		
		
		//view페이지로 응답데이터를 전달 ~ 
		//CarConfirmUpdate.jsp페이지(차량 주문 정보 수정페이지)로 이동하면서 request영역 전달
		RequestDispatcher  dis = 
				request.getRequestDispatcher("CarMain.jsp?center=CarConfirmUpdate.jsp");
		//실제이동시 request영역전달
		dis.forward(request, response);
		
		
	}
	
	

}







