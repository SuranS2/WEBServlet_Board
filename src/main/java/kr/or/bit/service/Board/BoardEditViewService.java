package kr.or.bit.service.Board;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class BoardEditViewService implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ActionForward forward = null;
		try {
			BoardDao service = new BoardDao();
			
			String idx = request.getParameter("idx");
			if (idx == null || idx.trim().equals("")) {
				response.sendRedirect("board_list.ok"); // cpage=1 , ps=5
				return forward;
			}
			// 수정 내용 보여주기~
			Board board;
			board = service.getEditContent(idx);
			
			request.setAttribute("idx", idx);
			request.setAttribute("board", board);

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/board/board_edit.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
