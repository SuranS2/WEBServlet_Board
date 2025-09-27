package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import jakarta.*;
import jakarta.servlet.http.HttpServletRequest;

import javax.sql.DataSource;

import kr.or.bit.dto.Board;
import kr.or.bit.dto.Reply;

//CRUD 함수 > ConnectionPool > 함수단위 연결 ,받환 
public class ReplyDao {
	DataSource ds = null;

	public ReplyDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}

	// 댓글 입력하기 (Table reply : fk(jspboard idx) )
	public int replywrite(int idx_fk, String writer, String userid, String content, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			conn = ds.getConnection();
			String sql = "insert into reply(no,writer,userid,content,pwd,idx_fk) "
					+ " values(reply_no.nextval,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, userid);
			pstmt.setString(3, content);
			pstmt.setString(4, pwd);
			pstmt.setInt(5, idx_fk);

			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();// 반환
			} catch (Exception e) {

			}
		}

		return row;
	}

	// 댓글 조회하기
	public List<Reply> replylist(String idx_fk) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Reply> list = null;

		try {
			conn = ds.getConnection();
			String reply_sql = "select * from reply where idx_fk=? order by no desc";

			pstmt = conn.prepareStatement(reply_sql);
			pstmt.setString(1, idx_fk);

			rs = pstmt.executeQuery();

			list = new ArrayList<>();
			while (rs.next()) {
				int no = Integer.parseInt(rs.getString("no"));
				String writer = rs.getString("writer");
				String userid = rs.getString("userid");
				String pwd = rs.getString("pwd");
				String content = rs.getString("content");
				java.sql.Date writedate = rs.getDate("writedate");
				int idx = Integer.parseInt(rs.getString("idx_fk"));

				Reply replydto = new Reply(no, writer, userid, pwd, content, writedate, idx);
				list.add(replydto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 반환
			} catch (Exception e) {

			}
		}

		return list;
	}

	// 댓글 삭제하기
	public int replyDelete(String no, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;

		try {

			String replyselect = "select pwd from reply where no=?";
			String replydelete = "delete from reply where no=?";

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(replyselect);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbpwd = rs.getString("pwd");
				if (pwd.equals(dbpwd)) {
					pstmt.close();
					pstmt = conn.prepareStatement(replydelete);
					pstmt.setString(1, no);
					row = pstmt.executeUpdate();
				} else {
					row = 0;
				}
			} else {
				row = -1;
			}
		} catch (Exception e) {

		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 반환
			} catch (Exception e) {

			}
		}

		return row;
	}
}
