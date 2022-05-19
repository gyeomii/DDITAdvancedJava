package CollectionTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04ListSortTest2 {
	public static void main(String[] args) {
		List<Member> memList = new ArrayList<Member>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-2222-2222"));
		memList.add(new Member(4, "성춘향", "010-3333-3333"));
		memList.add(new Member(3, "이순신", "010-4444-4444"));
		memList.add(new Member(6, "강감찬", "010-5555-1111"));
		memList.add(new Member(2, "일지매", "010-1111-6666"));
		memList.add(new Member(7, "논개", "010-7777-1111"));
		
		System.out.println("정렬 전 : ");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-----------------------------------");
		
		Collections.sort(memList);
		System.out.println("정렬 후(이름의 오름차순) : ");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("------------------------------------");
		
		Collections.sort(memList, new SortNumDesc());
		System.out.println("정렬 후(번호의 내림차순) : ");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		
	}
}
class SortNumDesc implements Comparator<Member>{
	/*
	 * 외부정렬자 클래스
	 * (Member의 번호(Num)의 내림차순으로 정렬하기)
	 */

	@Override
	public int compare(Member mem1, Member mem2) {
//		1)
//		if(mem1.getNum() > mem2.getNum()) {
//			return -1;
//		}else if(mem1.getNum() < mem2.getNum()) {
//			return 1;
//		}else {
//			return 0;
//		}
		
//		2)
//		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
		
//		3)
		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
	}
}

class Member implements Comparable<Member> {
	/*
	 * 회원의 정보를 저장하는 클래스
	 * 오름차순으로 정렬하는 compareTo메소드를 오버라이딩 함
	 */
	private int num;
	private String name;
	private String tel;

	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public int compareTo(Member mem) {
		
		return this.getName().compareTo(mem.getName());
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}
	
}