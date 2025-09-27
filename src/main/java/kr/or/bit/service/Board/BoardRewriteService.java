package kr.or.bit.service.Board;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class BoardRewriteService implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ActionForward forward = null;
		try {
			BoardDao service = new BoardDao();
			Board board = Board.builder().subject(request.getParameter("subject"))
					.writer(request.getParameter("writer")).email(request.getParameter("email"))
					.homepage(request.getParameter("homepage")).content(request.getParameter("content"))
					.pwd(request.getParameter("pwd")).filename(request.getParameter("filename"))
					.idx(Integer.parseInt(request.getParameter("idx"))).
					build();
			
			System.out.println(board);
			int result = service.reWriteOk(board);
			System.out.println(result);
			
			//list 이동시 현재 pagesize , cpage
			String cpage = request.getParameter("cp"); //current page
			String pagesize = request.getParameter("ps"); //pagesize
			//코드는 필요에 따라서  url ="board_list.jsp?cp=<%=cpage";
			String msg="";
		    String url="";
		    if(result > 0){
		    	msg ="rewrite insert success";
		    	url ="board_list.ok";
		    }else{
		    	msg="rewrite insert fail";
		    	url="board_content.ok?idx="+request.getParameter("idx");
		    }
		    
		    request.setAttribute("cp", cpage);
			request.setAttribute("ps", pagesize);
		    request.setAttribute("board_msg",msg);
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
