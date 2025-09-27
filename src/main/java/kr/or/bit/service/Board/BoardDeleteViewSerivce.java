package kr.or.bit.service.Board;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class BoardDeleteViewSerivce implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ActionForward forward = null;
		try {
			String idx = request.getParameter("idx");
			
			request.setAttribute("idx", idx);
			request.setAttribute("cp", request.getParameter("cp"));
			request.setAttribute("ps", request.getParameter("ps"));
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/board/board_delete.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
