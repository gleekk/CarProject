package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CarDAO;

//CarConfirmDelete.jsp화면에서 예약내역을 삭제 하기 위해...
//비밀번호 입력후 삭제 버튼을 클릭 했을떄...
//삭제 요청을 전달 받는 서블릿 
@WebServlet("/CarConfirmDeleteController.do")
public class CarConfirmDeleteController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청 값 전달 받기 
		int orderid = Integer.parseInt(req.getParameter("orderid"));
		String memberpass = req.getParameter("memberpass");
		
		//DB작업을 위한 DAO객체 생성
		CarDAO cdao = new CarDAO();
		int result = cdao.carOrderDelete(orderid,memberpass);
		
		if(result != 0){ //삭제에 성공 했다면 ~ 
			
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('렌트카 예약정보를 삭제 하였습니다.')");
			out.println("location.href='CarListController.do'");
			out.println("</script>");
				
		}else{ //삭제에 실패 했다면
			
			req.setAttribute("result", result);
			
			//실제 이동
			RequestDispatcher dis = 
					req.getRequestDispatcher("CarMain.jsp?center=CarConfirmDelete.jsp");
			dis.forward(req, resp);
			
		}
		
		
	}
	
	
	
	
}






