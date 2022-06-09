package MVCBoardService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import MVCBoardService.vo.BoardVO;
import util.JDBCUtil;

public class BoardDaoImpl implements IBoardDao {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int cnt = 0;

	private static IBoardDao boardDao;

	private BoardDaoImpl() {
		
	}

	public static IBoardDao getInstance() {
		if (boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}

	@Override
	public List<BoardVO> getAllPostList() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			conn = JDBCUtil.getConnection();

			String sql = "select * from jdbc_board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO bv = new BoardVO();
				String boardNo = rs.getString("BOARD_NO");
				String title = rs.getString("BOARD_TITLE");
				String writer = rs.getString("BOARD_WRITER");
				String date = rs.getString("BOARD_DATE");
				String content = rs.getString("BOARD_CONTENT");

				bv.setBoardNo(boardNo);
				bv.setTitle(title);
				bv.setWriter(writer);
				bv.setDate(date);
				bv.setContent(content);

				boardList.add(bv);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return boardList;
	}

	@Override
	public int writePost(BoardVO bv) {
		try {
			conn = JDBCUtil.getConnection();

			String sql = " INSERT INTO JDBC_BOARD(BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT) "
					+ " VALUES(BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getTitle());
			pstmt.setString(2, bv.getWriter());
			pstmt.setString(3, bv.getContent());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deletePost(String boardNo) {
		try {
			String sql = " DELETE FROM JDBC_BOARD WHERE BOARD_NO=? ";
			System.out.println("boardNo" + boardNo);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int editPost(BoardVO bv) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = " UPDATE JDBC_BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ? WHERE BOARD_NO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getTitle());
			pstmt.setString(2, bv.getContent());
			pstmt.setString(3, bv.getBoardNo());

			cnt = pstmt.executeUpdate();

			System.out.println("update 문 실행 결과 : " + cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<BoardVO> searchPost(BoardVO bv) {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			conn = JDBCUtil.getConnection();
			//and를 쓰기위해 1=1을 사용(다이나믹 쿼리 위함)
			String sql = "select * from JDBC_BOARD where 1=1";
//			갖고온게 null이 아니고 빈칸이 아니면
			if (bv.getTitle() != null && !bv.getTitle().equals("")) {
				sql += " and BOARD_TITLE like '%' || ? || '%' ";
			}
			if (bv.getWriter() != null && !bv.getWriter().equals("")) {
				sql += " and BOARD_WRITER = ?";
			}
			if (bv.getContent() != null && !bv.getContent().equals("")) {
				sql += " and BOARD_CONTENT like '%' || ? || '%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			//값이 존재할 때 index변수를 사용하여 ? 에 데이터 삽입
			int index = 1;
			if (bv.getTitle() != null && !bv.getTitle().equals("")) {
				pstmt.setString(index++, bv.getTitle());
			}
			if (bv.getWriter() != null && !bv.getWriter().equals("")) {
				pstmt.setString(index++, bv.getWriter());
			}
			if (bv.getContent() != null && !bv.getContent().equals("")) {
				pstmt.setString(index++, bv.getContent());
			}
		
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO bv1 = new BoardVO();
				String boardNo = rs.getString("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String boardWriter = rs.getString("BOARD_WRITER");
				String boardDate = rs.getString("BOARD_DATE");
				String boardContent = rs.getString("BOARD_CONTENT");

				bv1.setBoardNo(boardNo);
				bv1.setTitle(boardTitle);
				bv1.setWriter(boardWriter);
				bv1.setDate(boardDate);
				bv1.setContent(boardContent);

				boardList.add(bv1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return boardList;
	}

	@Override
	public boolean checkBoard(String boardNo) {
		boolean chk = false;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT COUNT(*) CNT FROM JDBC_BOARD WHERE BOARD_NO=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			rs = pstmt.executeQuery();

			int cnt = 0;
			while (rs.next()) {
				cnt = rs.getInt("cnt");
			}

			if (cnt > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return chk;
	}

}
