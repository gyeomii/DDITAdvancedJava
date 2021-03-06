package JavaCollectionFramework.HotelService;

import java.util.*;

public class Hotel {
	public static void main(String[] args) {
			new HotelService().open();
	}
}

class HotelService {
	private Scanner scanner;
	private boolean isOpen;
	private TreeMap<Integer, Room> roomMap;

	public HotelService() {
		scanner = new Scanner(System.in);
		isOpen = true;
		roomMap = new TreeMap<Integer, Room>();
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
					System.err.println("호실을 정수로 입력하세요.");
				}
				break;
			case 2:
				try {
					checkOut();
				} catch (Exception e) {
					System.err.println("호실을 정수로 입력하세요.");
				}
				break;
			case 3:
				roomInfo();
				break;
			case 4:
				close();
				break;
			default:
				System.err.println("메뉴에 있는 번호를 입력하세요.");

			}
		}
	}

	public void checkIn() throws Exception{
		int inNum;
		System.out.println();
		System.out.println("몇호실에 체크인 하시겠습니까?");
		do {
			System.out.print("실 번호 입력 : ");
			inNum = Integer.parseInt(scanner.next());
			if (roomMap.get(inNum) != null) { // 중복데이터 검사
				System.err.println(inNum + "호는 사용중입니다.");
				System.out.println("다른 호실 번호를 입력해주세요.");
			}
		} while (roomMap.get(inNum) != null);

		System.out.println("");
		System.out.print("이름 입력 : ");
		String name = scanner.next();

		roomMap.put(inNum, new Room(inNum, name));
		System.out.println(name + "님 체크인 되었습니다.");
	}

	public void checkOut() throws Exception{
		System.out.println("몇호실을 체크아웃 하시겠습니까?");
		System.out.print("실 번호 입력 : ");
		int outNum = Integer.parseInt(scanner.next());
		Room r = roomMap.get(outNum);
		if (roomMap.remove(outNum) == null) {
			System.out.println(outNum + "호실에는 체크인한 사람이 없습니다.");
			return;
		} else {
			String outName = r.getName();
			System.out.println(outName + "님 체크아웃 되었습니다.");
		}
	}

	public void roomInfo() {
		Set<Integer> keySet = roomMap.keySet();

		if (keySet.size() == 0) {
			System.out.println("등록된 정보가 없습니다.");
		} else {
			System.out.println("=============================");
			System.out.println(" 실 번호    이름 ");
			System.out.println("=============================");

			for (Integer roomNum : keySet) {
				Room r = roomMap.get(roomNum);
				System.out.println("  " + r.getRoomNum() + "\t" + r.getName());
			}
		}
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
				System.err.println("Error : 잘못된 값이 입력되었습니다.");
				scanner.next();
			}
		}
	}
}

class Room {
	private int roomNum;
	private String name;

	public Room(int inNum, String name) {
		super();
		this.roomNum = inNum;
		this.name = name;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Room [roomNum=" + roomNum + ", name=" + name + "]";
	}

}