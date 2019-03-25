package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(request, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(request, resp);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
			
			//클라이언트의 요청 주소 알아내기 (컨텍스트 패스를 포함한 요청 주소 얻기)
	     	//    /CarProject/MemberJoin.me
			//    /CarProject/MemberJoinAction.me
			//    /CarProject/MemberLogin.me
			String RequestURI = request.getRequestURI();
			System.out.println(RequestURI);
			
			//클라이언트의 요청 주소 알아내기 (컨텍스트 패스만 얻기)
			//	 /CarProject
			//   /CarProject
			//   /CarProject
			String contextPath = request.getContextPath();
			
			//   /CarProject/MemberJoin.me 주소 중에서...
			//   /MemberJoin.me 주소 잘라내서  얻기 
			//   /CarProject/MemberJoinAction.me 주소중에서...
			//   /MemberJoinAction.me 주소 잘라내서 얻기 
			//   
			//   /MemberLogin.me 주소 잘라내서 저장
			String command = RequestURI.substring(contextPath.length());			
			
			//페이지 이동 방식 여부값 , 이동할페이지 경로값을 저장하여 리턴 해주는 역할을 하는 객체를 저장할 참조변수 선언
			ActionForward forward = null;
			
			Action action = null;
			
			
			//Top.jsp페이지에서  회원가입 링크를 눌러 회원가입 작성패이지로 이동시켜줘!! 라는 요청이 들어왔을때..
			if(command.equals("/MemberJoin.me")){ 
				
				forward = new ActionForward();
				
				//페이지 이동 방식 여부값을 false로 저장  -> RequestDispatcher  forward()  방식
				forward.setRedirect(false);
				
				//이동할 페이지 경로(회원가입 작성 페이지) 주소 값 저장
				forward.setPath("./CarMain.jsp?center=member/join.jsp");
		
			//join.jsp페이지에서 회원가입 내역을 입력하고  회원가입 버튼을 클릭한뒤 회원가입 요청이 들어 오면~ 
			}else if(command.equals("/MemberJoinAction.me")){
				
				//회원가입 DB처리를 위한 Action객체 생성
				action = new MemberJoinAction();
				
				//join.jsp에서 입력한 회원가입 내용을 담고 있는 request영역을 
				//execute()메소드의 매개변수로 전달하여....
				//회원가입 DB작업후 회원가입에 성공하면..
				//페이지 이동 방식 여부 값 true와 .. 
				//이동할 페이지 주소 (./member/login.jsp)를 담고 있는?
				//new ActionForward()객체를 리턴 받는다.
				try {
					forward = action.execute(request, resp);
				} catch (Exception e) {
					e.printStackTrace();
				}	
				
			//회원가입에 성공했을때.. 로그인 화면으로 이동하라라는 요청!
			//또는
			//top.jsp에서 로그인 링크를 클릭했을떄 로그인 화면으로 이동하라라는 요청!
			}else if(command.equals("/MemberLogin.me")){ //로그인페이지로 이동 시켜줘~ 라는 요청
				
				forward = new ActionForward();
				
				forward.setRedirect(false); 
				
				forward.setPath("./CarMain.jsp?center=member/login.jsp");
				
			}else if(command.equals("/Main.me")){//CarMain.jsp메인페이지 요청!
				
				forward = new ActionForward();
				
				forward.setRedirect(false); 
				
				forward.setPath("./CarMain.jsp");
			
			//login.jsp페이지에서.. "Sign in"로그인버튼을 눌렀을때... 로그인 처리 요청 받기 ~
			// 사용자가 입력한  id와 pass값을 request영역에 담아 오기 
			}else if(command.equals("/MemberLoginAction.me")){
				
				//로그인 처리를 위한 Action관련 객체 생성
				action = new MemberLoginAction();
							
				try {
					forward = action.execute(request, resp);
				} catch (Exception e) {
					e.printStackTrace();
				}	
				
			//top.jsp에서 로그아웃링크를 클릭하여 세션값을 초기화하고...
				//CarMain.jsp화면으로 다시 이동하라라는 요청이 들어 왔을때...
			}else if(command.equals("/MemberLogout.me")){ //로그아웃요청
				
				//로그아웃 처리하는 Action관련 객체 생성
				action = new MemberLogoutAction();
				
				try {
					//top.jsp에서 로그아웃 요청이 들어 왔을떄..
					//세션값 초기화후~ 로그아웃 메세지창을 띄워주고..
					//CarMain.jsp페이지로 이동하는 일을 하는 execute()메소드를 호출함
					forward = action.execute(request, resp);
				
				} catch (Exception e) {	
					e.printStackTrace();
				}
					
			}
			
			
			
			
			//뷰페이지로 이동 하는 역할
			if(forward != null){
				if(forward.isRedirect()){ //이동방식 여부 값이 true일때.. Response.sendRedirect()방식
										
					resp.sendRedirect(forward.getPath()); 				
				
				}else{//이동방식 여부값이 false일떄 .. RequestDispatcher  forward()방식 
					
					RequestDispatcher  dispatcher = 
							request.getRequestDispatcher(forward.getPath());
					dispatcher.forward(request, resp);
				
				}		
			}//if 끝 
			
	}//doProcess메소드 끝
	
}//클래스 끝 






