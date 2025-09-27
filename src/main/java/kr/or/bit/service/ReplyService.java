package kr.or.bit.service;

import java.util.List;

import javax.naming.NamingException;
import jakarta.*;
import jakarta.servlet.http.HttpServletRequest;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;
import kr.or.bit.dto.Reply;

public class ReplyService {
	private static ReplyService instance = new ReplyService();

	private ReplyService() {}

	public static ReplyService getInBoardService() {
		return instance;
	}

	// 서비스 요청(댓글 입력하기)
	public int replyWrite(int idx_fk, String writer, String userid, String content, String pwd) throws NamingException {
		return new BoardDao().replywrite(idx_fk, writer, userid, content, pwd);
	}

	// 서비스 요청(댓글 목록 조회하기)
	public List<Reply> replyList(String idx_fk) throws NamingException {
		return new BoardDao().replylist(idx_fk);
	}

	// 서비스 요청(댓글 삭제하기)
	public int replyDelete(String no, String pwd) throws NamingException {
		return new BoardDao().replyDelete(no, pwd);
	}
}
