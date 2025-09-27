package kr.or.bit.service.Reply;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.ReplyDao;

public class ReplyWriteService implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ActionForward forward = null;
		try {
			int result;
			ReplyDao service = new ReplyDao();
			String writer = request.getParameter("reply_writer");
			String content = request.getParameter("reply_content");
			String pwd = request.getParameter("reply_pwd");
			String idx_fk = request.getParameter("idx");
			String userid = "empty";
			result = service.replywrite(Integer.parseInt(idx_fk), writer, userid, content, pwd);
			//처리하는 코드
		 	String msg="";
		    String url="";
		    
		    if(result > 0){
		    	msg ="댓글 입력 성공";
		    	url ="board_content.ok?idx="+idx_fk;
		    }else{
		    	msg="댓글 입력 실패";
		    	url="board_content.ok?idx="+idx_fk;
		    }
		    
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
