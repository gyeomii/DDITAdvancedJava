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
	private Map<String, Room> RoomMap;

	public HotelService() {
		scanner = new Scanner(System.in);
		isOpen = true;
		RoomMap = new HashMap<String, Room>();
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
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomInfo();
				break;
			case 4:
				close();
				break;
			default:
				System.out.println("메뉴에 있는 번호를 입력하세요.");

			}
		}
	}

	public void checkIn() {
		String roomNum;
		System.out.println();
		System.out.println("어느 방에 체크인 하시겠습니까?");
		do {
			System.out.print("방 번호 입력 : ");
			roomNum = scanner.next();
			if (RoomMap.get(roomNum) != null) { // 중복데이터 검사
				System.out.println(roomNum + "호는 사용중입니다.");
				System.out.println("다른 방 번호를 입력해주세요.");
			}
		} while (RoomMap.get(roomNum) != null);
		System.out.println("");
		System.out.print("이름 입력 : ");
		scanner.nextLine();
		String name = scanner.nextLine();

		RoomMap.put(roomNum, new Room(roomNum, name));
		System.out.println(name + "님 체크인 되었습니다.");
	}

	public void checkOut() {

	}

	public void roomInfo() {

	}

	public void close() {
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
				System.out.println("Error : 잘못된 값이 입력되었습니다.");
				scanner.next();
			}
		}
	}
}

class Room {
	private String roomNum;
	private String name;

	public Room(String roomNum, String name) {
		super();
		this.roomNum = roomNum;
		this.name = name;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
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