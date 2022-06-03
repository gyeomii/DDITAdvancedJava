package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * db.properties파일의 내용으로 DB정보를 설정하는 방법
 * 방법2) ResourceBundle 객체 이용하기
 * @author Gyeomii
 */
public class JDBCUtil {
	static ResourceBundle bundle;
	//드라이버 로딩(옵션)
		static {
			bundle = ResourceBundle.getBundle("db"); //객체 생성
			
			try {
				
				Class.forName(bundle.getString("driver"));
				
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
				e.printStackTrace();
			}
		}
		
		/**
		 * Connection 연결
		 * @return Connection 객체
		 */
		public static Connection getConnection() {
			try {
				return DriverManager.getConnection(
						bundle.getString("url"),
						bundle.getString("user"),
						bundle.getString("password"));
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} 
		}
		
		/**
		 * 자원 반납
		 * @param conn
		 * @param stmt
		 * @param pstmt
		 * @param rs
		 */
		public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
			if (rs != null) try { rs.close(); } catch (SQLException e) {}
			if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
}
