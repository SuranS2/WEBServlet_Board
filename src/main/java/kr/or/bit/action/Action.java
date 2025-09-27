package kr.or.bit.action;

//Tomcat 9.x javax
//Tomcat 10.x jakarta
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//앞으로 생성되는 모든 서비스는 Action 인터페이스를 구현하게 하겠다.
public interface Action {
	ActionForward excute(HttpServletRequest request,HttpServletResponse response);
}
