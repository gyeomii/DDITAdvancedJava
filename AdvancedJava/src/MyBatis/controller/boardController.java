package MyBatis.controller;

import java.util.List;
import java.util.Scanner;

import MyBatis.service.BoardServiceImpl;
import MyBatis.service.IBoardService;
import MyBatis.vo.BoardVO;

public class boardController {
	private IBoardService boardService;
	Scanner scanner = new Scanner(System.in);
	private boolean isOn = true;

	public boardController() {
		boardService = new BoardServiceImpl();
	}

	// 프로그램 시작
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

	private void displayMenu() {
		System.out.println("          ************************          ");
		System.out.println("           WELCOME TO GYEOMII.LOG           ");
		System.out.println("          ************************          ");
		System.out.println("--------------------------------------------");
		System.out.println("          어떤 업무를 하시겠습니까?         ");
		System.out.println("        1.전체목록 2.새글작성 3.수정        ");
		System.out.println("            4.삭제 5.검색 6.종료            ");
		System.out.println("--------------------------------------------");
	}

	// 전체 글 출력
	private void displayAllPost() {
		System.out.println("전체 게시판을 확인합니다.");

		List<BoardVO> boardList = boardService.getAllPostList();

		for (BoardVO bv : boardList) {
			System.out.println();
			System.out.println("[" + bv.getBoardNo()+"번 게시글]");
			System.out.println("=================================================");
			System.out.println(" 작성자 : " + bv.getWriter() + " | 작성일자 : " + bv.getDate());
			System.out.println("-------------------------------------------------");
			System.out.println(" 제목 : " + bv.getTitle());
			System.out.println("-------------------------------------------------");
			System.out.println(" " + bv.getContent());
			System.out.println("=================================================");
		}
		System.out.println("출력작업 끝");
	}

	// 게시글 삽입
	private void writePost() {
		System.out.println("새로운 글을 작성합니다.");
		scanner.nextLine();
		System.out.print("제목   >> ");
		String title = scanner.nextLine();
		System.out.print("작성자 >> ");
		String writer = scanner.nextLine();
		System.out.print("내용   >> ");
		String content = scanner.nextLine();

		BoardVO bv = new BoardVO();
		bv.setTitle(title);
		bv.setWriter(writer);
		bv.setContent(content);

		int cnt = boardService.writePost(bv);

		if (cnt > 0) {
			System.out.println("게시글 작성 완료");
		} else {
			System.out.println("게시글 작성 실패");
		}
	}

	// 게시글 수정
	private void editPost() {
		boolean chk = false;
		String boardNo = null;
		scanner.nextLine();
		displayAllPost();
		do {
			System.out.println("수정할 게시글 번호를 입력하세요.");
			System.out.print("게시글 번호 >> ");
			boardNo = scanner.nextLine();

			chk = boardService.checkBoard(boardNo);

			if (chk == false) {
				System.out.println("게시글 번호 : " + boardNo + "가 없습니다.");
				System.out.println("다시 입력하세요.");
			}
		} while (chk == false);

		// 게시글 번호를 정상적으로 입력한 경우 아래 코드 실행
		System.out.println("수정할 정보를 입력하세요");
		System.out.print("게시글 제목 >> ");
		String title = scanner.nextLine();
		System.out.println("게시글 내용 >> ");
		String content = scanner.nextLine();

		BoardVO bv = new BoardVO();
		bv.setBoardNo(boardNo);
		bv.setTitle(title);
		bv.setContent(content);

		int cnt = boardService.editPost(bv);

		if (cnt > 0) {
			System.out.println(boardNo + "번 게시글 수정 작업 성공");
		} else {
			System.out.println(boardNo + "번 게시글 수정 작업 실패");
		}
	}

	// 게시글 삭제
	private void deletePost() {
		boolean chk = false;
		String boardNo = null;
		displayAllPost();
		scanner.nextLine();
		do {
			System.out.println("삭제할 게시글 번호를 입력하세요.");
			System.out.print("게시글 번호 >> ");
			boardNo = scanner.nextLine();

			chk = boardService.checkBoard(boardNo);

			if (chk == false) {
				System.out.println("게시글 번호 가" + boardNo + "인 게시글은 없습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (chk == false);

//		게시글 번호를 정상적으로 입력한 경우 아래 코드 실행
		int cnt = boardService.deletePost(boardNo);

		if (cnt > 0) {
			System.out.println(boardNo + "번 게시글 삭제 작업 성공");
		} else {
			System.out.println(boardNo + "번 게시글 삭제 작업 실패");
		}
	}

	// 게시글 검색
	private void searchPost() {
		// 전체 게시글 확인
		scanner.nextLine();
		System.out.println("<제목과 내용은 일부만 입력해도 검색 가능합니다>");
		System.out.print("검색할 제목   >> ");
		String title = scanner.nextLine();
		System.out.print("검색할 작성자 >> ");
		String writer = scanner.nextLine();
		System.out.print("검색할 내용   >> ");
		String content = scanner.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setTitle(title);
		bv.setWriter(writer);
		bv.setContent(content);

		List<BoardVO> boardList = boardService.searchPost(bv);

		System.out.println("=================================================");
		System.out.println("                  검색결과                  ");
		System.out.println("=================================================");
		for (BoardVO bv1 : boardList) {
			System.out.println();
			System.out.println("[" + bv1.getBoardNo()+"번 게시글]");
			System.out.println("=================================================");
			System.out.println(" 작성자 : " + bv1.getWriter() + " | 작성일자 : " + bv1.getDate());
			System.out.println("-------------------------------------------------");
			System.out.println(" 제목 : " + bv1.getTitle());
			System.out.println("-------------------------------------------------");
			System.out.println(" " + bv1.getContent());
			System.out.println("=================================================");
		}
		System.out.println("출력작업 끝");
	}

	// 종료
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
