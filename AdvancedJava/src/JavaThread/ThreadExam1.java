package JavaThread;

import java.util.*;

import javax.swing.JOptionPane;

public class ThreadExam1 {

	public static void main(String[] args) {
		Thread th1 = new Input();
		Thread th2 = new CountDown();

		th1.start();
		th2.start();

	}
}

//입력을 처리하는 스레드 클래스
class Input extends Thread {
	private static String userInput = "";
	private static String com = "";
	private static ArrayList<Integer> list = new ArrayList<>();
	static boolean inputCheck = false;
	Random rnd = new Random();

	@Override
	public void run() {
		// 입력이 완료되면 inputCheck변수를 true로 변경
		do {
			userInput = JOptionPane.showInputDialog("가위 바위 보 시작(5초안에 입력하세요)");
		} while (!"가위".equals(userInput) && !"바위".equals(userInput) && !"보".equals(userInput));

		inputCheck = true;

		list.add(1);
		list.add(2);
		list.add(3);

		Collections.shuffle(Input.list);

		if (list.get(0) == 1) {
			com = "가위";
		} else if (list.get(0) == 2) {
			com = "바위";
		} else if (list.get(0) == 3) {
			com = "보";
		}
		
		String result = "";
		if(userInput.equals(com)) {
			result = "무승부";
		}else if(com.equals("가위") && userInput.equals("바위")
				 ||com.equals("바위") && userInput.equals("보")
				 ||com.equals("보") && userInput.equals("가위")){
			 result = "게이머 승리";
		}else {
			result = "인공지능 승리";
		}
		System.out.println("<게이머 : " + userInput+ ">" + "\n<컴퓨터 : " + com + ">");
		System.out.println("<결과 : " + result + ">");

	}
}

//카운트 다운을 처리하는 스레드 클래스
class CountDown extends Thread {
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			// 입력이 완료되었는지 검사하고 입력이 완료되면 run()을 종료시킨다. 즉 현재스레드를 종료시킨다.
			if (Input.inputCheck == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("<제한시간 종료>");
		System.out.println("<게이머 패배>");
		System.exit(0);
		}
}