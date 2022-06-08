package MVCBoardService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		try {
			conn = JDBCUtil.getConnection();

			String sql = "select * from jdbc_board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String boardNo = rs.getString("BOARD_NO");
				String title = rs.getString("BOARD_TITLE");
				String writer = rs.getString("BOARD_WRITER");
				String date = rs.getString("BOARD_DATE");
				String content = rs.getString("BOARD_CONTENT");

				System.out.println(boardNo + "   " + title + "  " + writer + "  " + date + "\t" + content);
			}
			System.out.println("============================================");
			System.out.println("출력작업 끝");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}		return null;
	}

	@Override
	public int writePost(Connection conn, BoardVO bv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePost(Connection conn, String boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editPost(Connection conn, BoardVO bv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVO> searchPost(Connection conn, BoardVO bv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkBoard(Connection conn, String boardNo) {
		// TODO Auto-generated method stub
		return false;
	}

}
