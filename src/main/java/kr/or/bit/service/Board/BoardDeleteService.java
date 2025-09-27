package kr.or.bit.service.Board;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;

public class BoardDeleteService implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ActionForward forward = null;
		try {
			BoardDao service = new BoardDao();
			String idx = request.getParameter("idx");
			String pwd = request.getParameter("pwd");
			
			int result =service.deleteOk(idx, pwd);
			
			String msg="";
			String url="";
			if(result > 0){
				msg="delete success";
				url="board_list.ok";
			}else{
				msg="delete fail";
				url="board_list.ok";
			}
			request.setAttribute("board_msg",msg);
			request.setAttribute("board_url",url);
			
		

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/board/redirect.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
