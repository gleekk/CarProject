package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CarDAO;
import db.CarOderBean;

//CarOrder.jsp페이지에서  결제 하기 버튼 클릭시 ~ 요청받을 서블릿 
@WebServlet("/CarOrderController.do")
public class CarOrderController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	//get방식 또는 Post방식의 요청이 들어오면 호출되는 메소드 
	protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//CarOrder.jsp페이지에서 전달한 예약정보를  DTO에 저장
		CarOderBean cbBean = new CarOderBean(); //DTO
		cbBean.setCarno(Integer.parseInt(request.getParameter("carno")));
		cbBean.setCarqty(Integer.parseInt(request.getParameter("carqty")));
		cbBean.setCarreserveday(Integer.parseInt(request.getParameter("carreserveday")));
		cbBean.setCarbegindate(request.getParameter("carbegindate"));
		cbBean.setCarins(Integer.parseInt(request.getParameter("carins")));
		cbBean.setCarwifi(Integer.parseInt(request.getParameter("carwifi")));
		cbBean.setCarnave(Integer.parseInt(request.getParameter("carnave")));
		cbBean.setCarbabyseat(Integer.parseInt(request.getParameter("carbabyseat")));
		cbBean.setMemberphone(request.getParameter("memberphone"));
		cbBean.setMemberpass(request.getParameter("memberpass"));
		
		//데이터 베이스 작업 insert를 위한  DAO객체 생성
		CarDAO cdao = new CarDAO();
		
		cdao.insertCarOrder(cbBean); //예약정보를 DB에 insert하기 위해 메소드 호출시 DTO전달 ~ 
		
		//예약 성공후 이동할 뷰페이지(CarList.jsp) 지정  
		RequestDispatcher dis = request.getRequestDispatcher("CarListController.do");
		dis.forward(request, response);
		
	}	
}
















