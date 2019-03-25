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


//CarConfirmUpdate.jsp페이지에서 .. 예약한 정보를 수정 (선택)하고  수정버튼을 눌러 요청이 들어 왔을떄 호출 되는 서블릿
@WebServlet("/CarConfirmUpdateProcController.do")
public class CarConfirmUpdateProcController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//CarConfirmUpdate.jsp페이지에서  수정을 위해 선택한 값 얻기 (요청값 얻기)
			int orderid = Integer.parseInt(request.getParameter("orderid"));
			int carreserveday = Integer.parseInt(request.getParameter("carreserveday"));
			int carqty = Integer.parseInt(request.getParameter("carqty"));
			int carins = Integer.parseInt(request.getParameter("carins"));
			int carwifi = Integer.parseInt(request.getParameter("carwifi"));
			int carbabyseat = Integer.parseInt(request.getParameter("carbabyseat"));
			String carbegindate = request.getParameter("carbegindate");
			String memberpass =  request.getParameter("memberpass");
			
			//CarOrderBean객체를 생성 하여   수정을 위해 선택한 값을 각각의 변수에 저장 
			CarOderBean bean = new CarOderBean();
			bean.setOrderid(orderid);
			bean.setCarreserveday(carreserveday);
			bean.setCarbabyseat(carbabyseat);
			bean.setCarbegindate(carbegindate);
			bean.setCarqty(carqty);
			bean.setCarins(carins);
			bean.setCarwifi(carwifi);
			bean.setMemberpass(memberpass);
			
			
			//UPDATE 수정 DB작업을 위해.. CarDAO객체 생성
			CarDAO cdao = new CarDAO();
			
			//UPDATE작업 하는 메소드(carOrderUpdate) 호출시 ~ 
			//CarOrderBean객체를 매개변수 전달 하여 실제 UPDATE 작업하기
			cdao.carOrderUpdate(bean);
			
			
			//위의 UPDATE작업에 성공하면 View페이지(중앙화면 CarList.jsp)로 이동 시키자 
		    RequestDispatcher dis = request.getRequestDispatcher("CarListController.do");
		    dis.forward(request, response);
	}
	
	
	
}








