package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그아웃처리를 위한  세션값 제거
		HttpSession session = request.getSession(true);
		session.invalidate(); //세션영역의 모든값 제거(초기화) 
		
		//"로그아웃" 메세지 띄우고
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.println("<script>");
		printWriter.println("alert('로그아웃')");
		//CarMain.jsp로 이동~ 
		printWriter.println("location.href='./Main.me'");
		printWriter.println("</script>");
		
		return null;
	}

}



