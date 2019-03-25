package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;


//login.jsp에서 사용자가 입력한 id와 pass를  userCheck메소드로 전달하여..
//사용자가 입력한 값과 DB에 저장되어 있는  값을 비교 하여  같으면~~ 로그인 처리하는 클래스 
public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//요청값 전달 받기
		//login.jsp에서 입력한 id,pass값을 request영역에서 얻기 
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		
		//DB작업을 위해 객체 생성
		MemberDAO mdao = new MemberDAO();
		
		//login.jsp에서 사용자가 입력한 id와 pass를 userCheck함수로 전달하여..
		//사용자가 입력한 값과 DB에 저장된 값을 비교하여 로그인 처리 ~ 
		//check = 1 -> 아이디, 비밀번호 맞음
		//check = 0 -> 아이디, 비밀번호 틀림
		//check = -1 -> 아이디 틀림
		int check = mdao.userCheck(id,pass);
		
		 
		if(check == 0){ //아이디 ,비밀번호 틀림
			//아이디 , 비밀번호 가 틀림 <-- 메세지를 띄우고 
			//login.jsp로 이동 ~ 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 또는 비밀번호 틀림');");
			out.println("history.go(-1);"); //(이전페이지)login.jsp로 이동
			out.println("</script>");
			return null;
		}else if(check == -1){ //아이디 없음 , login.jsp로 이동
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 없음');");
			out.println("history.back();"); //(이전페이지)login.jsp로 이동
			out.println("</script>");			
			return null;
		}
		
		//check == 1
		//사용자가 입력한 값과  DB에 저장되어 있는  id,pass가 동일 ~ 
		
		//세션얻기
		HttpSession session = request.getSession();
		
		//login.jsp에서 입력한 아이디값을  세션 영역에 저장
		session.setAttribute("id", id);
		
		//로그인 성공시 ~ CarMain.jsp로 이동 시키자~
		//페이지 이동 방식 여부값, 이동할 페이지 경로값을 저장하여 리턴해주는 역할을 하는 객체 생성
		ActionForward forward = new ActionForward();
		
		//페이지 이동방식 여부값 true로 저장
		//true? ->  Response.sendRedirect(); 방식 -> 실제 이동할페이지 주소경로를 웹브라우저 주소창에 노출함.
		forward.setRedirect(true);
		
		//이동할 페이지 경로값(가상요청주소값) 저장
		forward.setPath("./Main.me");
		
	
		return forward;// MemberFrontController클래스의 doProcess()메소드 내부로  리턴
	}

}





