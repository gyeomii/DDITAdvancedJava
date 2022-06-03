package JavaIO.HotelServiceIO;

import java.io.*;
import java.util.*;

public class Hotel2 {
	public static void main(String[] args) {
		new HotelService().open();
	}
}

class HotelService {
	private Scanner scanner;
	private boolean isOpen;
	private TreeMap<Integer, Room> roomMap;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;

	public HotelService() {
		scanner = new Scanner(System.in);
		isOpen = true;
		roomMap = new TreeMap<Integer, Room>();
	}

	public void displayMenu() {
		System.out.println("          *************************          ");
		System.out.println("            호텔 델루나 업무 시작            ");
		System.out.println("          *************************          ");

		File file = new File("d:/Others/roomInfo.bin");

		if (file.isFile()) {
			try {
				ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/Others/roomInfo.bin")));

				Object obj = null;
				// readObject() 호출시 역직렬화 발생함.
				while ((obj = ois.readObject()) != null) {
					// 마지막에 다다르면 EOF 예외가 발생함.

					// 읽어온 데이터를 원래의 객체형으로 변환후 사용한다.
					roomMap = (TreeMap<Integer, Room>) obj;
				}

			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				// ex.printStackTrace();
				System.out.println("          *************************          ");
				System.out.println("           호텔정보 불러오기  완료           ");
				System.out.println("          *************************          ");
			} finally {
				roomInfo();
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else {
			System.out.println("          *************************          ");
			System.out.println("             호텔정보가 없습니다             ");
			System.out.println("          *************************          ");
		}
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
					System.out.println("호실을 정수로 입력하세요.");
				}
				break;
			case 2:
				try {
					checkOut();
				} catch (Exception e) {
					System.out.println("호실을 정수로 입력하세요.");
				}
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

	public void checkIn() throws Exception {
		int inNum;
		System.out.println();
		System.out.println("몇호실에 체크인 하시겠습니까?");
		do {
			System.out.print("실 번호 입력 : ");
			inNum = Integer.parseInt(scanner.next());
			if (roomMap.get(inNum) != null) { // 중복데이터 검사
				System.out.println(inNum + "호는 사용중입니다.");
				System.out.println("다른 호실 번호를 입력해주세요.");
			}
		} while (roomMap.get(inNum) != null);

		System.out.println("");
		System.out.print("이름 입력 : ");
		String name = scanner.next();

		roomMap.put(inNum, new Room(inNum, name));
		System.out.println(name + "님 체크인 되었습니다.");
	}

	public void checkOut() throws Exception {
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
			System.out.println("           등록된 정보가 없습니다.");
		} else {
			System.out.println("          =========================");
			System.out.println("             실 번호     이름 ");
			System.out.println("          =========================");

			for (Integer roomNum : keySet) {
				Room r = roomMap.get(roomNum);
				System.out.println("               " + r.getRoomNum() + "      " + r.getName());
			}
		}
	}

	public void close() {
		try {
			// 객체를 파일에 저장하기

			// 출력용 스트림 객체 생성
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("d:/Others/roomInfo.bin")));

			// 쓰기 작업 시작..
			oos.writeObject(roomMap); // 직렬화

			System.out.println("          *************************          ");
			System.out.println("           호텔정보 출력 작업 완료           ");
			System.out.println("          *************************          ");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				oos.close(); // 스트림 닫기
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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
				System.out.println("Error : 잘못된 값이 입력되었습니다.");
				scanner.next();
			}
		}
	}
}

class Room implements Serializable {
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