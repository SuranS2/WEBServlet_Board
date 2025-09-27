package kr.or.bit.service.Board;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class BoardRewriteViewService implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ActionForward forward = null;
		try {
			String idx = request.getParameter("idx");
			String cpage = request.getParameter("cp");
			String pagesize = request.getParameter("ps");
			String subject = request.getParameter("subject"); // 답글의 제목으로 사용
			
			if(idx == null || subject == null || idx.trim().equals("") || subject.trim().equals("")){
				response.sendRedirect("board_list.ok");
				return forward;
			}
			if(cpage == null || pagesize == null){
				cpage ="1";
				pagesize = "5";
			}
			request.setAttribute("idx", idx);
			request.setAttribute("cp", cpage);
			request.setAttribute("ps", pagesize);
			request.setAttribute("subject", subject);

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/board/board_rewrite.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
