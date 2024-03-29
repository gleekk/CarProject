package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Action관련 클래스 들이..
//Action인터페이스의 추상메소드를 오버라이딩 함으로써 클라이언트의 요청 처리 형태를 규격화시킴
public interface Action { //클래스를 만들기 위한 틀

	//특정 클라이언트의 요청을 수행하고 그결과값을 ActionForward객체 타입으로 반환함.
	//조건 : 추상메소드 -> 상속받은  클래스는 무조건 메서드 오버라이딩 해야함.
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
}
