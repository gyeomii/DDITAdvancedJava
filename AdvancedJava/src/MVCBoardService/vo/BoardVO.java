package MVCBoardService.vo;
/**
 * DB 테이블에 있는 컬럼을 기준으로 데이터를 객체화한 클래스이다.
 * @author Gyeomii
 * <p>
 * DB테이블의 '컬럼'이 이 클래스의 '멤버변수' 가 된다. <br>
 * DB테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행한다. <br>
 * </p>
 */
public class BoardVO {
	private String boardNo; // board_no number not null, -- 번호(자동증가)
	private String title; // board_title varchar2(100) not null, -- 제목
	private String writer; // board_writer varchar2(50) not null, -- 작성자
	private String date; // board_date date not null, -- 작성날짜
	private String content; // board_content clob, -- 내용

	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", title=" + title + ", writer=" + writer + ", date=" + date
				+ ", content=" + content + "]";
	}

}