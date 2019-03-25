package controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CarConfirmBean;
import db.CarDAO;


/*
 CarReserveConfirm.jsp페이지에서.. <예약확인>을 하기 위해  입력한 폰번호와 비밀번호를 전달받아..
 DB에 해당하는 레코드가 있는지 검사하는 서블릿 클래스 
 */
@WebServlet("/CarReserveConfirmController.do")
public class CarReserveConfirmController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//요청값 전달 받기 
		String memberphone =  req.getParameter("memberphone");
		String memberpass = req.getParameter("memberpass");
		
		//예약확인(검색)을 위해  요청값(입력했던 폰번호,비밀번호)을 이용하여 ... 검색!
		//1. DB작업을 위한 CarDAO객체 생성
		CarDAO cdao  = new CarDAO();
		//검색조건1. 전화번호와 비밀번호를 기준으로 하여 검색 
		//검색조건2. 현재 날짜보다 이전 날짜에 예약한 현황은 보여 주지 말아라! 
		
		//2. 예약확인(검색)을 위한 메소드 호출시 ~  입력했던 폰번호, 비밀번호를 전달 ~ 
		Vector<CarConfirmBean>  v = cdao.getAllCarOrder(memberphone,memberpass);
		
		//CarReserveResult.jsp(예약현황을 확인 하는 페이지 View)로...
		//응답데이터를 전달 하기 위해.. request(탁자)에  백터(응답서류)를 올려 놓는다
		req.setAttribute("v", v);
		
		RequestDispatcher dis = 
				req.getRequestDispatcher("CarMain.jsp?center=CarReserveResult.jsp");
		dis.forward(req, resp);
		
		
		
	}
	
	
}







