package JavaJDBC.boardService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.JDBCUtil;

public class Board {
	public static void main(String[] args) {
		new boardService().start();
	}
}

class boardService {
	private Scanner scanner;
	private boolean isOn;
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public boardService() {
		scanner = new Scanner(System.in);
		isOn = true;
	}

	public void displayMenu() {
		System.out.println("          ************************          ");
		System.out.println("           WELCOME TO GYEOMII LOG           ");
		System.out.println("          ************************          ");
		System.out.println("--------------------------------------------");
		System.out.println("          어떤 업무를 하시겠습니까?         ");
		System.out.println("        1.전체목록 2.새글작성 3.수정        ");
		System.out.println("            4.삭제 5.검색 6.종료            ");
		System.out.println("--------------------------------------------");
	}

	public void start() {

		displayMenu();

		while (isOn) {
			switch (menu()) {
			case 1:
				displayAllPost();
				break;
			case 2:
				writePost();
				break;
			case 3:
				editPost();
				break;
			case 4:
				deletePost();
				break;
			case 5:
				searchPost();
				break;
			case 6:
				stop();
				break;
			default:
				System.out.println("메뉴에 있는 번호를 입력하세요.");

			}
		}
	}

	private void displayAllPost() {
		System.out.println("전체 게시판을 확인합니다.");
		System.out.println("============================================");
		System.out.println("번호 제목   작성자  작성일\t\t내용");
		System.out.println("============================================");
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
		}
	}

	private void writePost() {
		try {
			conn = JDBCUtil.getConnection();
			System.out.println("새로운 글을 작성합니다.");
			scanner.nextLine();
			System.out.print("제목   >> ");
			String title = scanner.nextLine();
			System.out.print("작성자 >> ");
			String writer = scanner.nextLine();
			System.out.print("내용   >> ");
			String content = scanner.nextLine();

			String sql = " INSERT INTO JDBC_BOARD(BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT) "
					+ " VALUES(BOARD_SEQ.NEXTVAL, ?, ?, sysdate, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("게시글 작성 완료");
			} else {
				System.out.println("게시글 작성 실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void editPost() {
		displayAllPost();
		System.out.println("==게시판 수정==");
		boolean chk = false;
		String boardNo = null;

		do {
			scanner.nextLine();
			System.out.println("수정할 게시글의 번호를 입력하세요");
			System.out.print("게시글 번호 > ");
			boardNo = scanner.nextLine();

			chk = checkBoard(boardNo);

			if (chk == false) {
				System.out.println("게시글 번호 : " + boardNo + "가 없습니다.");
				System.out.println("다시 입력하세요.");
			}
		} while (chk == false);

		try {
			conn = JDBCUtil.getConnection();
			scanner.nextLine();
			System.out.println("추가할 게시글을 정보를 입력하세요.");
			System.out.print("게시판 제목 > ");
			String boardTitle = scanner.nextLine();
			System.out.println("작성 내용 > ");
			String boardContent = scanner.nextLine();

			String sql = " UPDATE JDBC_BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ? WHERE BOARD_NO = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setString(3, boardNo);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("게시글 수정 완료");
			} else {
				System.out.println("게시글 수정 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private void deletePost() {
		System.out.println("==게시판 삭제==");
		boolean chk = false;
		String boardNo = null;

		do {
			scanner.nextLine();
			System.out.println("삭제할 게시글의 번호를 입력하세요");
			System.out.print("게시글 번호 > ");
			boardNo = scanner.nextLine();

			chk = checkBoard(boardNo);

			if (chk == false) {
				System.out.println("게시글 번호 : " + boardNo + "가 없습니다.");
				System.out.println("다시 입력하세요.");
			}
		} while (chk == false);

		try {
			conn = JDBCUtil.getConnection();

			String sql = " DELECT FROM JDBC_BOARD WHERE BOARD_NO=? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("게시글 삭제 완료");
			} else {
				System.out.println("게시글 삭제 실패");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private void searchPost() {
		// 전체 게시글 확인
		scanner.nextLine();
		System.out.println("검색할 게시판의 제목을 입력하세요.");
		System.out.print("검색할 글자(일부만 입력해도 됨) > ");
		String boardTitle = scanner.nextLine();
		System.out.println("검색할 작성자(정확히 입력할 것) > ");
		String boardWriter = scanner.nextLine();
		System.out.println("검색할 내용(일부만 입력해도 됨) > ");
		String boardContent = scanner.nextLine();

		try {
			conn = JDBCUtil.getConnection();

			String sql = " SELECT * FROM JDBC_BOARD " + "WHERE BOARD_TITLE LIKE '%'||?||'%' AND "
					+ "BOARD_WRITER = ? AND " + "BOARD_CONTENT LIKE '%'||?||'%' ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardWriter);
			pstmt.setString(3, boardContent);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				rs = pstmt.executeQuery();
				System.out.println("============================================");
				System.out.println("검색결과");
				System.out.println("============================================");
				while (rs.next()) {
					String boardNo = rs.getString("BOARD_NO");
					boardTitle = rs.getString("BOARD_TITLE");
					boardWriter = rs.getString("BOARD_WRITER");
					String boardDate = rs.getString("BOARD_DATE");
					boardContent = rs.getString("BOARD_CONTENT");

					System.out.println(
							boardNo + "\t" + boardTitle + "\t" + boardWriter + "\t" + boardDate + "\t" + boardContent);
				}
			} else {
				System.out.println("검색결과가 없습니다.");
			}

			System.out.println("============================================");
			System.out.println("검색 끝");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private boolean checkBoard(String boardNo) {
		boolean chk = false;

		try {
			conn = JDBCUtil.getConnection();

			String sql = " SELECT COUNT(*) CNT FROM JDBC_BOARD WHERE BOARD_NO=? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			rs = pstmt.executeQuery();

			int cnt = 0;
			while (rs.next()) {
				cnt = rs.getInt("CNT");
			}

			if (cnt > 0) {
				chk = true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return chk;
	}

	public void stop() {
		System.out.println("          ************************          ");
		System.out.println("           I HOPE TO SEE YOU SOON           ");
		System.out.println("          ************************          ");
		isOn = false;
	}

	public int menu() {
		while (true) {
			try { // 스캐너로 입력받은 번호를 반환
				System.out.println();
				System.out.print("메뉴 선택: ");
				int num = scanner.nextInt();
				return num;
			} catch (Exception e) { // 오류발생시 재실행
				System.out.println("Error : 잘못된 값이 입력되었습니다.");
				scanner.next();
			}
		}
	}
}