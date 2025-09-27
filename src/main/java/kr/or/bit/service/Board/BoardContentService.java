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

public class BoardContentService implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ActionForward forward = null;
	
		// 화면 ...
		// 게시물 총 건수
		int totalboardcount;
		
		try {
			String idx = request.getParameter("idx"); // 글번호 받기

			// 글 번호를 가지고 오지 않았을 경우 예외처리
			if (idx == null || idx.trim().equals("")) {
				response.sendRedirect("board.ok");
				return forward; // 더 이상 아래 코드가 실행되지 않고 클라이언트에게 바로 코드 전달
			}
			idx = idx.trim();
			// http://192.168.0.12:8090/WebServlet_5_Board_Model1_Sample/board/board_content.jsp?idx=19&cp=1&ps=5
			// board_content.jsp?idx=19&cp=1&ps=5 //다시 목록으로 갔을때 ... cp , ps 가지고 ...
			// why: 목록으로 이동시 현재 page 유지하고 싶어요
			String cpage = request.getParameter("cp"); // current page
			String pagesize = request.getParameter("ps"); // pagesize

			// List 페이지 처음 호출 ...
			if (cpage == null || cpage.trim().equals("")) {
				// default 값 설정
				cpage = "1";
			}

			if (pagesize == null || pagesize.trim().equals("")) {
				// default 값 설정
				pagesize = "5";
			}
			// 옵션
			// 조회수 증가
			boolean isread;
			BoardDao service = new BoardDao();
			isread = service.getReadNum(idx);
			if (isread)
				System.out.println("조회증가 : " + isread);
			Board board = service.getContent(Integer.parseInt(idx));
			String content = board.getContent();
			if (content != null) {
				content = content.replace("\n", "<br>");
			}
			// 데이터 조회 (1건 (row))
			request.setAttribute("idx", idx);
			request.setAttribute("cp", cpage);
			request.setAttribute("ps", pagesize);
			request.setAttribute("board", board);
			request.setAttribute("content", content);

			// 덧글 목록 보여주기
			List<Reply> replyList = service.replylist(idx); // 참조하는 글번호
			if (replyList != null && replyList.size() > 0) {
				request.setAttribute("replyList", replyList);
			}
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/board/board_content.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return forward;
	}
}
