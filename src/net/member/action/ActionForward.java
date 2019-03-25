package net.member.action;


/*
 ActionForward클래스는 Action인터페이스에 있는 추상메소드의 명령을 수행하고....
 결과값을 가지고 페이지를 이동(포워딩)할때 사용되는 클래스임.
 
 하는일1.
 	-> 페이지 이동 방식 여부값 저장후 리턴 해주는 역할.
 	       페이지 이동 방식 여부값이 true일때 -> Response.sendRedirect(); 방식
                 페이지 이동 방식 여부값이 false일떄 -> forward(); 방식
 
 하는일2.
 	-> 이동할 페이지 경로 값 저장 하여 리턴 해주는 역할.
 */

public class ActionForward {
	//페이지 이동 방식 여부값 저장 변수
	private boolean isRedirect = false;
	//true  Response.sendRedirect(); 방식 <-- 이방식은 이동할 페이지 주소 경로를 웹브라우저 주소창에 노출 함.
	//false forward(); 방식 <-- 이방식은 이동할 페이지 주소 경로를 웹브라우저에 노출 하지 않음.
		
	//이동할 페이지 경로 주소값을 저장할 변수 
	private String path = null;
	
	//페이지 이동 방식 여부값 리턴 메소드
	public boolean isRedirect(){
		return isRedirect;
	}
	//페이지 이동 방식 여부값을 저장할 용도의 메소드
	public void setRedirect(boolean isRedirect){
		this.isRedirect = isRedirect;
	}
	//이동페이지 경로 주소값을 리턴 해주는 메소드
	public String getPath(){
		return path;
	}
	//이동할 페이지 경로 주소값을 셋팅(저장) 해주는 메소드 
	public void setPath(String path){
		this.path = path;
	}
}



















