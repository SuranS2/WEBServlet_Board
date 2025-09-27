package kr.or.bit.service.Reply;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.ReplyDao;

public class ReplyDeleteService implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ActionForward forward = null;
		try {
			ReplyDao service = new ReplyDao();
			String idx_fk=request.getParameter("idx");//댓글의 원본 게시글 번호
			String no = request.getParameter("no");//댓글의 순번(PK)
			String pwd = request.getParameter("delPwd");//댓글의 암호
			System.out.println(idx_fk + "/" + no + "/" + pwd + "/");
			//parameter 정상인 경우
			int result = service.replyDelete(no, pwd);
			//처리하는 코드
		 	String msg="";
		    String url="";
		    
		    if(result > 0){
		    	msg ="댓글 삭제 성공";
		    	url ="board_content.ok?idx="+idx_fk;
		    }else{
		    	msg="댓글 삭제 실패";
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
