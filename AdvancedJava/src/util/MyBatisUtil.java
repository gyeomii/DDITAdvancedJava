package util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory sessionFactory;
	
	static {
		SqlSession sqlSession = null;
		// mybatis를 이용하여 DB자료를 처리하는 작업 순서
		// 1.mybatis의 환경설정파일을 읽어와 실행시킨다.
		try {
			// 1-1. xml문서 읽어오기
			// 설정파일의 인코딩 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("mybatis-config.xml");

			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체를 생성하기
			sessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close();// Reader 객체 닫기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getInstance() {
		return sessionFactory.openSession(false); //오토커밋 false
	}
	/**
	 * SqlSession객체를 제공하는 팩토리 메소드
	 * @param autoCommit 오토커밋 여부
	 * @return SqlSession 객체
	 */
	public static SqlSession getInstance(boolean autoCommit) {
		return sessionFactory.openSession(autoCommit); //오토커밋 선택
	}
}
