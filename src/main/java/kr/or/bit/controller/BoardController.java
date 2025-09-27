package kr.or.bit.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.Board.BoardContentService;
import kr.or.bit.service.Board.BoardDeleteService;
import kr.or.bit.service.Board.BoardDeleteViewSerivce;
import kr.or.bit.service.Board.BoardEditService;
import kr.or.bit.service.Board.BoardEditViewService;
import kr.or.bit.service.Board.BoardListService;
import kr.or.bit.service.Board.BoardRewriteService;
import kr.or.bit.service.Board.BoardWriteService;


@WebServlet("*.ok")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
		super();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 한글처리 request.setCharacterEncoding("UTF-8"); // 필터에서 처리됨
		// 2. 데이터 받기
		// 3. 모든 요청을 받고 처리 하겠다
		// 3.1 요청에 대한 판단
		// 3.1.1 command 방식 list.do?cmd=list , write.do?cmd-insert

		// url 방식 (뒷 주속값을 추룰해서 비교)
		// 마지막 주소 문자열 : localhost:8090/WEB/list.do
		// >>>> /list.do /insert.do 추출 ....

		// 데이터 받기
		// URL 방식
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlCommand = requestUri.substring(contextPath.length());

		System.out.println(urlCommand);
		
		// 3. 요청하기
		Action action = null;
		ActionForward forward = null;
		
		// 요구분석 (UI 보여주 , 데이터 처리해주)
		if (urlCommand.equals("/board.ok")) {
			action = new BoardListService();
			forward = action.excute(request, response);
		} else if (urlCommand.equals("/board_list.ok")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("board.ok");
		} else if (urlCommand.equals("/board_content.ok")) {
			action = new BoardContentService();
			forward = action.excute(request, response);
		} else if (urlCommand.equals("/board_write.ok")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath( "/WEB-INF/views/board/board_write.jsp");
		} else if (urlCommand.equals("/board_create.ok")) {
			action = new BoardWriteService();
			forward = action.excute(request, response);
		} else if (urlCommand.equals("/board_edit.ok")) {
			action = new BoardEditViewService();
			forward = action.excute(request, response);
		} else if (urlCommand.equals("/board_update.ok")) {
			action = new BoardEditService();
			forward = action.excute(request, response);
		} else if (urlCommand.equals("/board_delete.ok")) {
			//<a href="board_delete.ok?idx=${idx}&cp=${cp}&ps=${ps}">삭제</a> 
			action = new BoardDeleteViewSerivce();
			forward = action.excute(request, response);
		}else if (urlCommand.equals("/board_remove.ok")) {
			action = new BoardDeleteService();
			forward = action.excute(request, response);
		}else if (urlCommand.equals("/board_rewrite.ok")) {
			action = new BoardDeleteService();
			forward = action.excute(request, response);
		}else if (urlCommand.equals("/board_recreate.ok")) {
			action = new BoardRewriteService();
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
