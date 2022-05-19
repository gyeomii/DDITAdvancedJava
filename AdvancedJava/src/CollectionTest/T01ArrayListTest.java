package CollectionTest;

import java.util.ArrayList;
import java.util.List;

public class T01ArrayListTest {
	public static void main(String[] args) {
		// List특징 : 인덱스가 있음, 중복을 허용함
		// ArrayList 장점 : 인덱스값만 알면 데이터 찾기가 용이 (검색속도 빠름)
		// ArrayList 단점 : 데이터를 추가하려면 데이터를 다 한칸씩 밀어야함 (시간 오래걸림)
		List list1 = new ArrayList();

		// add()메서드를 사용해서 데이터를 추가한다.
		// add(데이터) , add(인덱스, 데이터)
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);
		list1.add('k');
		list1.add(true);
		list1.add(12.34);

		// size() => 데이터의 개수
		System.out.println("size: " + list1.size());

		// get(인덱스)으로 데이터 꺼내오기
		System.out.println("1번째 자료: " + list1.get(0));

		// 데이터 끼워넣기도 같다.
		list1.add(0, "zzz");
		System.out.println("list1: " + list1);

		// 데이터 변형하기 set(인덱스, 데이터)
		String temp = (String) list1.set(0, "yyy");
		System.out.println("temp: " + temp);
		System.out.println("list1: " + list1);

		// 삭제하기도 같다.
		// remove(인덱스), remove(인덱스, 데이터)
		list1.remove(0);
		System.out.println("삭제 후: " + list1);

		list1.remove("bbb");
		System.out.println("bbb 삭제 후: " + list1);

		// Integer타입 데이터 111을 입력하면 인덱스로 인식하기때문에 객체로 표현
		list1.remove(new Integer(111));
		System.out.println("111 삭제 후: " + list1);
		System.out.println("========================================");

		// 제너릭을 지정하여 선언할 수 있다. List<타입>
		List<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");

		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " : " + list2.get(i));
		}
		System.out.println("-----------------------------------");

		// contains(비교객체) : 리스트에 '비교객체'가 있으면 true 아니면 false 반환
		System.out.println(list2.contains("DDD"));
		System.out.println(list2.contains("ZZZ"));

		// indexOf(비교객체) : 리스트에서 '비교객체'를 찾아 '비교객체'가 존재하는 index값을 반환
		// 				   	   리스트에 존재하지 않으면 -1을 반환
		System.out.println("DDD의 index값: " + list2.indexOf("DDD"));
		System.out.println("ZZZ의 index값: " + list2.indexOf("ZZZ"));
		System.out.println("------------------------------------");

		// toArray() : 리스트안의 데이터들을 배열로 변환하여 반환
		// 			   기본적으로 Object형 배열로 저장
		Object[] strArr = list2.toArray();
		//String형 배열
		String[] strArr1 = list2.toArray(new String[0]);
		System.out.println("배열의 갯수: " + strArr.length);
//
//		for (int i = 0; i < list2.size(); i++) {
//			list2.remove(i);
//		} // 이렇게 지우면 데이터가 남는다
//		
		for (int i = (list2.size()-1); i >= 0  ; i--) {
			list2.remove(i);
		}
		
		System.out.println("list2의 크기: " + list2.size());
	}
}
