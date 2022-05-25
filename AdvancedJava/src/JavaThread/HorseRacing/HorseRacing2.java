package JavaThread.HorseRacing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HorseRacing2 {
	static ArrayList<Horse> horse = new ArrayList<>();

	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			horse.add(new Horse(i + "번마"));
		}

		PlayState state = new PlayState(horse);

		for (Horse h : horse) {
			h.start();
		}
		
		state.start();

		for (Horse h : horse) {
			try {
				h.join();
			} catch (InterruptedException e) {
			}
		}
		try {
			state.join();
		} catch (InterruptedException e) {
		}

		System.out.println();

		// 경기 종료 후 등수순으로 정렬하기
		Collections.sort(horse);

		System.out.println("경기 결과");
		for (Horse h : horse) {
			System.out.println(h);
		}
	}

}

class Horse extends Thread implements Comparable<Horse> {
	public static int currentRank = 0;
	private String horseName; // 말이름
	private int rank; // 등수
	private int location; // 현재위치
	Random rnd = new Random();
	int randomTime = rnd.nextInt(50) + 200;

	// 생성자
	public Horse(String horseName) {
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return rank + "등\t: " + horseName;
	}

	@Override
	public int compareTo(Horse h) {
		return Integer.compare(getRank(), h.getRank());
	}

	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			location = i;
			try {
				Thread.sleep(randomTime);
			} catch (InterruptedException e) {
			}
		}
		setRank(++currentRank);
	}

}

// 경기 중의 현재 상황을 출력하는 쓰레드
class PlayState extends Thread {
	ArrayList<Horse> horse;

	// 생성자
	public PlayState(ArrayList<Horse> horses) {
		this.horse = horses;
	}

	@Override
	public void run() {
		while (true) {
			if (Horse.currentRank == horse.size()) { // 경기가 종료되면...
				break;
			}

			for (int i = 1; i <= 5; i++) {
				System.out.println();
			}

			for (int i = 0; i < horse.size(); i++) {
				System.out.print(horse.get(i).getHorseName() + "\t: ");
				for (int j = 1; j <= 50; j++) {
					if (horse.get(i).getLocation() == j) { // 현재위치 표시
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
			}

			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
			}

		}
	}

}
