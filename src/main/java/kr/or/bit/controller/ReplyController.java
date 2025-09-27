package kr.or.bit.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dto.Board;
import kr.or.bit.dto.Reply;
import kr.or.bit.service.Board.BoardEditViewService;
import kr.or.bit.service.Reply.ReplyDeleteService;
import kr.or.bit.service.Reply.ReplyWriteService;
import kr.or.bit.utils.ThePager;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.naming.NamingException;

@WebServlet("*.reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReplyController() {
		super();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlCommand = requestUri.substring(contextPath.length());

		System.out.println(urlCommand);

		Action action = null;
		ActionForward forward = null;

		if (urlCommand.equals("/board_reply.reply")) {
			action = new ReplyWriteService();
			forward = action.excute(request, response);
		} else if (urlCommand.equals("/boardreply_delete.reply")) {
			action = new ReplyDeleteService();
			forward = action.excute(request, response);
		} 
		if(forward != null){
			if(forward.isRedirect()){ // true location.href = "페이지 이동"
				// 5. 뷰지정
				response.sendRedirect(forward.getPath()); // 주소값이 바뀌어서 잘 안씀
			}else{
				//보낼 곳 있을 경우 => 데이터 처리 반환
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request,response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
