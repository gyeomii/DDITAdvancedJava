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

public class BoardDaoImpl implements IBoardDao{
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int cnt=0;
	
private static IBoardDao boardDao;
	
	private BoardDaoImpl() {
		
	}
	
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}
	
	@Override
	public List<BoardVO> getAllPostList(Connection conn) {
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
	public int writePost(Connection conn, BoardVO bv) {
		try {
		conn = JDBCUtil.getConnection();
		
		String sql = " INSERT INTO JDBC_BOARD(BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT) " + 
				" VALUES(BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, ?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bv.getTitle());
		pstmt.setString(2, bv.getWriter());
		pstmt.setString(3, bv.getContent());
		
		int cnt = pstmt.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deletePost(Connection conn, String boardNo) {
		try {
		String sql = " DELETE FROM JDBC_BOARD WHERE BOARD_NO=? ";
		System.out.println("boardNo" + boardNo);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardNo);
		int cnt = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int editPost(Connection conn, BoardVO bv) {
		try {
		String sql = " UPDATE JDBC_BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ? WHERE BOARD_NO = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bv.getTitle());
		pstmt.setString(2, bv.getContent());
		pstmt.setString(3, bv.getBoardNo());
		
		int cnt = pstmt.executeUpdate();
		
		System.out.println("update 문 실행 결과 : " + cnt);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;	
	}

	@Override
	public List<BoardVO> searchPost(Connection conn, BoardVO bv) {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
		String sql = " SELECT * FROM JDBC_BOARD \"\n"
				+ "WHERE BOARD_TITLE LIKE '%'||?||'%' AND \"\n"
				+ "BOARD_WRITER = ? AND \"\n"
				+ "BOARD_CONTENT LIKE '%'||?||'%' ";
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String boardNo = rs.getString("BOARD_NO");
			String boardTitle = rs.getString("BOARD_TITLE");
			String boardWriter = rs.getString("BOARD_WRITER");
			String boardDate = rs.getString("BOARD_DATE");
			String boardContent = rs.getString("BOARD_CONTENT");
			
			bv.setBoardNo(boardNo);
			bv.setTitle(boardTitle);
			bv.setWriter(boardWriter);
			bv.setDate(boardDate);
			bv.setContent(boardContent);
			
			boardList.add(bv);
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return boardList;
	}

	@Override
	public boolean checkBoard(Connection conn, String boardNo) {
		boolean chk = false;
		try {
		String sql = " SELECT COUNT(*) CNT FROM JDBC_BOARD WHERE BOARD_NO=? ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardNo);
		rs = pstmt.executeQuery();
		
		int cnt = 0;
		while(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		if(cnt > 0) {
			chk = true;
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return chk;
	}

}
