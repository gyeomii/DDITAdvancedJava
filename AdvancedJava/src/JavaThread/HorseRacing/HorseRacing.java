package JavaThread.HorseRacing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HorseRacing {
	static int rankCnt = 0;
	static boolean end = false;
	static ArrayList<Horse> horse = new ArrayList<>();

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			horse.add(new Horse((i + 1) + "번마"));
		}
	
		ArrayList<RacingThread> racing = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			racing.add(new RacingThread(horse.get(i).getName()));
		}
		
		for (int i = 0; i < racing.size(); i++) {
			racing.get(i).start();
		}
		
		System.out.println("[시작]");
		while (!end) {
			for (RacingThread hr : racing) {
				try {
					System.out.println(hr);
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println();
		}		

		for (RacingThread hr : racing) {
			try {
				hr.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Collections.sort(horse);
		
		System.out.println("============================================================");
		System.out.println();
		System.out.println("[순위]");
		for (Horse h : horse) {
			System.out.println(h.getRank() + "등\t: " + h.getName());
		}
	}
}

class RacingThread extends Thread {
	private String name;
	private ArrayList<String> section = new ArrayList<>(); //구간 생성
	Random rnd = new Random();
	int randomTime = rnd.nextInt(300) + 201; //난수 생성

	// 생성자
	public RacingThread(String name) {
		this.name = name;
		addSection();
	}

	public void addSection() {
		for (int i = 0; i < 50; i++) {
			section.add("-");
		}
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			if (i != 0) {
				section.set(i - 1, "-");
			}
			section.set(i, ">");

			try {
				// sleep() 메서드의 값을 200~500 사이의 난수로 한다.
				Thread.sleep(randomTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// System.out.println(name + " 경주 끝");
		HorseRacing.rankCnt++;
		if (HorseRacing.rankCnt >= 10) {
			HorseRacing.end = true;
		}

		for (int i = 0; i < HorseRacing.horse.size(); i++) {
			if (HorseRacing.horse.get(i).getName().equals(name)) {
				HorseRacing.horse.get(i).setRank(HorseRacing.rankCnt);
			}
		}
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < section.size(); i++) {
			str += section.get(i);
		}
		return name + "\t| " + str;
	}
}
//Horse 클래스
class Horse implements Comparable<Horse> {
	private String name; // 말 이름
	private int rank; // 등수

	public Horse(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	// 등수(rank)의 오름차순으로 정렬
	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(getRank(), horse.getRank());
	}
}