//package JavaThread.HorseRacing;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Random;
//
//public class HorseRacing {
//	static int rankCnt = 0;
//	static boolean end = false;
//	static ArrayList<Horse> horse = new ArrayList<>();
//
//	public static void main(String[] args) {
//		ArrayList<RacingThread> racing = new ArrayList<>();
//		// horse리스트에 Horse객체 10마리 담기
//		for (int i = 1; i <= 10; i++) {
//			horse.add(new Horse(i + "번마"));
//		}
//		
//		for (int i = 0; i < 10; i++) {
//			// racing리스트.add(new RacingThread쓰레드(horse리스트.get(i번배열).getName()))
//			racing.add(new RacingThread(horse.get(i).getName()));
//		}
//
//		for (int i = 0; i < racing.size(); i++) {
//			// racing.get(i)[= i번 쓰레드].start()
//			racing.get(i).start();
//		}
//		
//
//		System.out.println("==========================[Bang]============================");
//
//		while (!end) {
//			for (RacingThread raceHorse : racing) { // Racing쓰레드가 담긴 racing리스트에서 쓰레드를 하나씩 꺼내서
//				try {
//					System.out.println(raceHorse); // 쓰레드 값 출력
//					Thread.sleep(50); // 출력텀
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			System.out.println();
//			
//		}
//		
//		for(RacingThread th: racing) {
//			try {
//				th.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//
//		Collections.sort(horse);// 순위정렬
//
//		System.out.println("============================================================");
//		System.out.println();
//		System.out.println("[순위]");
//		for (Horse h : horse) {
//			System.out.println(h.getRank() + "등\t: " + h.getName());
//		}
//	}
//}
//
//class RacingThread extends Thread {
//	private String name;
//	private ArrayList<String> section = new ArrayList<>(); // 구간 생성
//	Random rnd = new Random();
//	int randomTime = rnd.nextInt(50) + 200; // 난수 생성
//
//	// 생성자
//	public RacingThread(String name) {
//		this.name = name;
//		addSection();
//	}
//
//	public void addSection() {
//		for (int i = 0; i < 50; i++) {
//			section.add("-"); // 말이 달리는 구간 ------- 만들기
//		}
//	}
//
//	@Override
//	public void run() {
//		for (int i = 0; i < 50; i++) {
//			if (i != 0) { // i번 리스트에 ">"이 있으면 i-1번 배열에 ----를 출력하는 for문
//				section.set(i - 1, "-");// > 뒤에 "-"로 하나씩 대체해서
//			} // ">"이 앞으로 한칸씩 가는것처럼 보이게 하기
//			section.set(i, ">"); // i가 커질수록 ">"을 한칸 앞으로
//			
//			try {// sleep()난수 생성
//				Thread.sleep(randomTime);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		// static으로 선언된 rankCnt에 RacingHorse쓰레드가 종료된 순서대로 rankCnt++
//		HorseRacing.rankCnt++;
//		if (HorseRacing.rankCnt >= HorseRacing.horse.size()) { // 총 10마리 경주 완료시
//			HorseRacing.end = true;
//		}
//
//		for (int i = 0; i < HorseRacing.horse.size(); i++) {
//			if (HorseRacing.horse.get(i).getName().equals(name)) { // 경주가 종료된 말과 이름이 같은 horse객체에
//				HorseRacing.horse.get(i).setRank(HorseRacing.rankCnt);// 등수 저장
//			}
//		}
//	}
//
//	@Override
//	public String toString() {
//		String str = "";
//		for (int i = 0; i < section.size(); i++) {
//			str += section.get(i); // str에 for문 으로 만든 section을 담고
//		}
//		return name + "\t| " + str; // '이름 | ----------->-------------------- ' 형태로 출력
//	}
//}
//
////Horse 클래스
//class Horse implements Comparable<Horse> {
//	private String name; // 말 이름
//	private int rank; // 등수
//
//	public Horse(String name) {
//		super();
//		this.name = name;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getRank() {
//		return rank;
//	}
//
//	public void setRank(int rank) {
//		this.rank = rank;
//	}
//
//	// 등수(rank)의 오름차순으로 정렬
//	@Override
//	public int compareTo(Horse horse) {
//		return Integer.compare(getRank(), horse.getRank());
//	}
//}