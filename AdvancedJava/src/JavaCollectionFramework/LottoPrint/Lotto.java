package JavaCollectionFramework.LottoPrint;

import java.util.*;

//set
public class Lotto {
	public static void main(String[] args) {
		LottoPrint lottoProgram = new LottoPrint();
		lottoProgram.menu();
	}
}

class LottoPrint {
	Scanner scanner = new Scanner(System.in);

	public void menu() {
		boolean isCountinue = true; // while문을 실행할 boolean값
		while (isCountinue) {
			System.out.println("================");
			System.out.println("Lotto 프로그램");
			System.out.println("----------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("================");
			switch (menuNum()) {
			case 1: // 로또 구매 프로그램 실행
				try {
					buyLotto();
				} catch (Exception e) {
					System.out.println("Error : 잘못된 값이 입력되었습니다.");
					System.out.println("Error : 프로그램을 재시작합니다.");
					scanner.next();
				}
				break;
			case 2:
				System.out.println("감사합니다.");
				isCountinue = false; // while에 false를 반환
				break;
			default:
				System.out.println("잘못입력하셨습니다.");
			}
		}
	}

	// 로또 구입
	public void buyLotto() throws Exception {
		int money; // 입력할 금액
		int change;// 거스름돈

		System.out.println("<<<<<<<<Lotto 구입 시작>>>>>>>>");
		System.out.println("[1000원에 로또번호 하나입니다.]");
		System.out.print("금액을 입력하세요 : ");

		money = scanner.nextInt();// 금액 입력
		change = money % 1000;// 거스름돈
		System.out.println();

		// 로또번호 출력
		if (money >= 1000) {
			System.out.println("행운의 로또번호는 아래와 같습니다.");
			for (int i = 0; i < (money / 1000); i++) {
				System.out.print("로또번호" + (i + 1) + ": ");
				getLottoNum();
				System.out.println();
			}
			System.out.println();
		} else { // 금액이 부족한 경우
			System.out.println("금액이 부족합니다.");
		}
		// 거스름돈 출력
		System.out.println("받은금액은 " + money + "원이고 " + "거스름돈은 " + change + "원 입니다.");
	}

	// 로또 배열 생성
	public void getLottoNum() {
		Set<Integer> tsLotto = new TreeSet<>();
		Random rndNum = new Random();
		// TreeSet에 로또번호 담기
		while (tsLotto.size() < 6) {
			tsLotto.add(rndNum.nextInt(45) + 1);
		}
		// '숫자, 숫자, 숫자' 로 출력하기 위한 for문
		int cnt = 0;
		for (int num : tsLotto) {
			if (cnt == 0) {
				System.out.print(num);
			} else {
				System.out.print(", " + num);
			}
			cnt++;
		}
	}

	// 예외처리
	public int menuNum() {
		while (true) {
			try { // 스캐너로 입력받은 번호를 반환
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