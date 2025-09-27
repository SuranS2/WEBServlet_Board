package kr.or.bit.service.Board;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;
import kr.or.bit.dto.Reply;
import kr.or.bit.utils.ThePager;

public class BoardWriteService implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ActionForward forward = null;
		
		Board board = Board.builder().subject(request.getParameter("subject"))
				.writer(request.getParameter("writer")).email(request.getParameter("email"))
				.homepage(request.getParameter("homepage")).content(request.getParameter("content"))
				.pwd(request.getParameter("pwd")).filename(request.getParameter("filename")).build();
		System.out.println(board);
		int result;
		try {
			BoardDao service = new BoardDao();
			result = service.writeok(board);
			// write.jsp 화면 >> writeok.jsp 처리 >> service >> dao > DB 작업 >
			// return dao > return service > writeok.jsp 결과처리 >> 이동 (공통) >> redirect.jsp

			String msg = "";
			String url = "";
			if (result > 0) {
				msg = "insert success";
				url = "board_list.ok";
			} else {
				msg = "insert fail";
				url = "board_write.ok";
			}

			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
		

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/board/redirect.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
