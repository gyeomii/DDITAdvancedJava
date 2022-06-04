package JavaJDBC.HotelServiceJDBC;

import java.sql.*;
import java.util.*;
import util.JDBCUtil;

public class Hotel3 {
	public static void main(String[] args) {
		new HotelService().open();
	}
}

class HotelService {
	private Scanner scanner;
	private boolean isOpen;
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public HotelService() {
		scanner = new Scanner(System.in);
		isOpen = true;
	}

	public void displayMenu() {
		System.out.println("          *************************          ");
		System.out.println("            호텔 델루나 업무 시작            ");
		System.out.println("          *************************          ");
		System.out.println("---------------------------------------------");
		System.out.println("          어떤 업무를 하시겠습니까?          ");
		System.out.println("  1.체크인 2.체크아웃 3.객실상태 4.업무종료  ");
		System.out.println("---------------------------------------------");
	}

	public void open() {

		displayMenu();

		while (isOpen) {
			switch (menu()) {
			case 1:
				try {
					checkIn();
				} catch (Exception e) {
					System.out.println("호실을 정수로 입력하세요.");
				}
				break;
			case 2:
				try {
					checkOut();
				} catch (Exception e) {
					System.out.println("호실을 정수로 입력하세요.");
				}
				break;
			case 3:
				roomInfo();
				break;
			case 4:
				close();
				break;
			default:
				System.out.println("메뉴에 있는 번호를 입력하세요.");

			}
		}
	}

	public void checkIn() throws Exception {
		int roomNum;
		boolean isExist = false;
		System.out.println();
		System.out.println("몇호실에 체크인 하시겠습니까?");
		do {
			System.out.print("실 번호 입력 : ");
			roomNum = Integer.parseInt(scanner.next());

			isExist = checkRoomNum(roomNum);

			if (isExist) { // 중복데이터 검사
				System.out.println(roomNum + "호는 사용중입니다.");
				System.out.println("다른 호실 번호를 입력해주세요.");
			}
		} while (isExist);

		System.out.println("");
		System.out.print("이름 입력 : ");
		String name = scanner.next();

		try {
			conn = JDBCUtil.getConnection();

			String sql = "INSERT INTO HOTEL_MNG (ROOM_NUM, GUEST_NAME) VALUES ( ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			pstmt.setString(2, name);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(name + "님 체크인 되었습니다.");
			} else {
				System.out.println(name + "님 체크인 실패하였습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

//----------------------------------------------------------
	private boolean checkRoomNum(int roomNum) {
		boolean isExist = false;

		try {
			conn = JDBCUtil.getConnection();

			String sql = "select count(*) as cnt from HOTEL_MNG where ROOM_NUM = ?";
			// PrepareStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			// 쿼리를 실행하여 ResultSet을 반환받음
			rs = pstmt.executeQuery();
			// rs에서 자료 출력
			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}

			if (cnt > 0) {
				isExist = true;
			} else {
				isExist = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return isExist;
	}

//---------------------------------------------------------------------
	public void checkOut() throws Exception {
		int roomNum;
		boolean isExist = false;

		do {
			System.out.println("몇호실을 체크아웃 하시겠습니까?");
			System.out.print("실 번호 입력 : ");

			roomNum = Integer.parseInt(scanner.next());
			isExist = checkRoomNum(roomNum);
			if (!isExist) {
				System.out.println(roomNum + "호에는 체크인한 사람이 없습니다.");
			}
		} while (!isExist);

		try {
			conn = JDBCUtil.getConnection();

			String sql = "Delete from HOTEL_MNG WHERE ROOM_NUM = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(roomNum + "호 체크아웃 되었습니다.");
			} else {
				System.out.println(roomNum + "호 체크아웃 되었습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

//------------------------------------------------------------------
	public void roomInfo() {
		if (countRoom() == 0) {
			System.out.println("모든 객실이 비어있습니다.");
		} else {
			System.out.println("=============================");
			System.out.println(" 실 번호    이름 ");
			System.out.println("=============================");

			try {
				conn = JDBCUtil.getConnection();

				String sql = "select * from HOTEL_MNG";

				stmt = conn.createStatement();

				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					int roomNum = rs.getInt("ROOM_NUM");
					String name = rs.getString("GUEST_NAME");

					System.out.println("  " + roomNum + "      " + name);
				}
				System.out.println("=============================");
				System.out.println("출력 작업 완료");

			} catch (SQLException e) {
				System.out.println("객실 정보 가져오기 실패");
				e.printStackTrace();
			} finally {
				JDBCUtil.close(conn, stmt, pstmt, rs);
			}
		}
	}

	private int countRoom() {
		int count = 0;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "SELECT count(*) as cnt from HOTEL_MNG";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return count;
	}

	public void close() {
		System.out.println("          *************************          ");
		System.out.println("            호텔 델루나 업무 종료            ");
		System.out.println("          *************************          ");
		isOpen = false;
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