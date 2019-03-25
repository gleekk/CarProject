package net.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

//하는일
//1.회원가입 폼(join.jsp)에서 입력한 정보들을 MemberBean객체(자바빈,DTO)에 저장 시키고....
//2.저장시킨 MemberBean객체를 DB작업을 하기위한 DAO객체에 전달하여 insert회원가입 한다
//3.회원가입 성공시.. 로그인 페이지로 이동 시키기 위해...
//  페이지 이동방식 여부값, 이동할 페이지 경로 값을 new ActionForward();객체에 저장하여..
//  MemberFrontController로 리턴 해주는 역할.
public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("MemberJoinAction클래스의 execute()메소드 호출 당했다~ ");
		
		//회원가입 폼(join.jsp)에서 입력한 정보 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		//1,2 클라이언트 요청정보  받기 
		//3 자바빈 DTO에 저장 
		MemberBean mb = new MemberBean(); //DTO
		//회원가입 join.jsp에서 입력한 정보들을 reuqest영역에서 꺼내서 MemberBean객체에 저장
		mb.setId(request.getParameter("id")); //입력한 회원 id저장
		mb.setPass(request.getParameter("pass"));//입력한 회원 pass저장
		mb.setName(request.getParameter("name"));//입력한 회원 이름 저장
		mb.setAddress(request.getParameter("address"));
		mb.setEmail(request.getParameter("email"));
		mb.setMobile(request.getParameter("mobile"));
		mb.setPhone(request.getParameter("phone"));
		mb.setDate(new Timestamp(System.currentTimeMillis())); //입력한 시간 저장
		
		//회원가입 성공 여부를 담을 변수 선언
		boolean result = false;
		
		MemberDAO mdao = new MemberDAO();
		
		//입력한 회원내용을 저장하고 있는 위의 MemberBean객체를  insertMember()메소드에 전달하여  insert DB작업 하기		
		result = mdao.insertMember(mb); //회원가입에 성공하면 true, 실패하면 false리턴
		
		
		if(result == false){ //회우너가입에 실패 할경우
			System.out.println("회원가입 실패");
			return null;
		}
		//회원가입 성공시 ~  로그인페이지로 이동 시키기위해 
		//페이지이동방식 여부값, 이동할페이지 경로 값을 저장하여 리턴 해주는 객체 생성
		ActionForward forward = new ActionForward();
		
		//Response.sendRedirect() 방식  <-- 노출함.
		forward.setRedirect(true);
		
		// ./member/login.jsp 이동할 페이지 주소 저장
		forward.setPath("./MemberLogin.me");
		
		
		return forward;
	}

}













