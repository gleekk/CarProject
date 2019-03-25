package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.rowset.internal.CachedRowSetReader;

import db.CarOderBean;

// CarOption.jsp에서 (옵션선택 화면) 옵션들을 선택하고 [예약하기]버튼을 클릭 했을때.. 요청받는 서블릿
@WebServlet("/CarOptionController.do")
public class CarOptionController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	//get방식 또는 Post방식의 요청이 들어오면 호출되는 메소드 
	protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//CarOption.jsp페이지에서 전달한 금액 연산을 위해  request영역에서 요청값 얻기
		int carqty = Integer.parseInt(request.getParameter("carqty")); //대여수량
		int carprice = Integer.parseInt(request.getParameter("carprice")); //대여 금액 
		int carreserveday = Integer.parseInt(request.getParameter("carreserveday"));//대여 날짜 
		
		//보험적용 여부 받기  = 보험적용 1일이면 : 값 1받기 
		//보험적용 여부 받기  = 보험미적용 이면   : 값 0받기
		int carins = Integer.parseInt(request.getParameter("carins"));
		
		//무선WIFI 적용여부 받기  = 적용 1일이면 : 값 1받기
		//무선WIFI 적용여부 받기  = 비적용이면 : 값 0받기
		int carwifi = Integer.parseInt(request.getParameter("carwifi"));
		
		//네비 적용 여부 받기 =  적용(무료)이면 : 값 1받기 
		//네비 적용 여부 받기 =  비적용이면 :  값 0받기 
		int carnave = Integer.parseInt(request.getParameter("carnave"));
		
		//베이비시트 적용 여부 받기  = 적용1일면 : 값 1받기
		//베이비시트 적용 여부 받기 =  미 적용 이면 : 값 0받기 
		int carbabyseat = Integer.parseInt(request.getParameter("carbabyseat"));
		
		//차량 가액 =  수량 * 대여기간 * 차량 가격
		int totalreserve = carqty * carreserveday * carprice;
		
		//옵션 금액 =  각종옵션에 대여기간과 수량을 곱해서 리턴 한값
		int totaloption = 
				((carins * carreserveday) + 
				 (carwifi * carreserveday) + 
				 (carbabyseat * carreserveday)) *10000 * carqty;
		
		//CarOrder.jsp쪽으로 선택했던 데이터를 모두 넘겨 주어야함
		//그러기에 앞서.. 위의 CarOption.jsp에서 전달한 데이터를 담을  자바빈 클래스(CarOderBean.java)를 만들자
		//------> db패키지에  CarOderBean.java 클래스 만들기 
		
		CarOderBean cbean = new CarOderBean();
		cbean.setCarno(Integer.parseInt(request.getParameter("carno")));//렌트할 차량 번호 받아와 DTO에 저장
		cbean.setCarqty(carqty); //렌트할 차량 수량(대여수량)을 DTO에 저장
		cbean.setCarreserveday(carreserveday); //대여기간 을 DTO에 저장
		cbean.setCarins(carins);//보험 적용 여부값 저장 
		cbean.setCarnave(carnave);
		cbean.setCarbabyseat(carbabyseat);
		cbean.setCarbegindate(request.getParameter("carbegindate"));
				
		//CarOrder.jsp로 데이터를 보내주기 위해.. request영역에 저장 하기 
		request.setAttribute("cbean", cbean);
		request.setAttribute("totalreserve", totalreserve); //차량 가액
		request.setAttribute("totaloption", totaloption);// 옵션금액 
				
		//CarOrder.jsp로 이동시 request영역 전달 
		RequestDispatcher dis = 
				request.getRequestDispatcher("CarMain.jsp?center=CarOrder.jsp");
		
		dis.forward(request, response);				
	}	
}






