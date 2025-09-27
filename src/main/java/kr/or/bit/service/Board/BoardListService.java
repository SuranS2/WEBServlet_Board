package kr.or.bit.service.Board;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;
import kr.or.bit.utils.ThePager;

public class BoardListService implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ActionForward forward = null;
		// 화면 ...
		// 게시물 총 건수
		int totalboardcount;
		try {
			BoardDao service = new BoardDao();
			totalboardcount = service.totalBoardCount();
			// 상세보기 >> 다시 LIST 넘어올때 >> 현재 페이지 설정
			String ps = request.getParameter("ps");
			// pagesize
			String cp = request.getParameter("cp"); // current page

			// List 페이지 처음 호출 ...
			if (ps == null || ps.trim().equals("")) { // default 값 설정
				ps = "5"; // 5개씩
			}

			if (cp == null || cp.trim().equals("")) { // default 값 설정
				cp = "1";
				// 1번째 페이지보겠다
			}

			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);
			int pagecount = 0;

			// 23건 % 5
			if (totalboardcount % pagesize == 0) {
				pagecount = totalboardcount / pagesize;
				// 20 << 100/5
			} else {
				pagecount = (totalboardcount / pagesize) + 1;
			}
			// 102건 : pagesize=5 >> pagecount=21페이지

			// 전체 목록 가져오기

//						list >> 1 , 20
			List<Board> boardList = service.list(cpage, pagesize);
			// 페이지사이즈
			request.setAttribute("ps", ps);
			// cpage
			request.setAttribute("cp", cp);
			request.setAttribute("boardList", boardList);
			request.setAttribute("totalboardcount", totalboardcount);

			int pagersize = 3; // [1][2][3]
			ThePager pager = new ThePager(totalboardcount, cpage, pagesize, pagersize, "board_list.do");
			request.setAttribute("pager", pager);
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/board/board_list.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return forward;
	}
}
