package CollectionTest;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class T03ListSortTest {
	public static void main(String[] args) {
		/*
		 * 정렬과 관련된 Interface는 Comparable과 Comparator 두가지가 있다. 
		 * - 보통 객체 자체에 정렬기능을 넣기 위해서 Comparable을 구현하고
		 *   정렬기준을 별도로 구현하고 싶을 때는 Comparator를 구현하여 사용한다.
		 * 
		 * - Comparable에서는 compareTo()를 구현해야 하고, Comparator에서는 compare()를 구현해야 한다.
		 */
		List<String> list = new LinkedList<String>();

		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");

		System.out.println("정렬 전: " + list);

		/*
		 * 정렬은 Collections.sort()를 이용하여 정렬한다.
		 * 정렬은 기본적으로 '오름차순'정렬을 수행한다.
		 * 
		 * 정렬방식을 변경하려면 정렬방식을 결정하는 객체를 만들어서 Collections.sort()메소드의 인수로 넘겨주면 된다.
		 */
		
		Collections.sort(list); //오름차순으로 정렬
		System.out.println("오름차순 정렬 후 : " + list);
		
		Collections.shuffle(list);//섞기
		System.out.println("섞은 후: " + list);
		
		//외부정렬자를 이용한 리스트 정렬
		Collections.sort(list, new Desc());
		System.out.println("내림차순 정렬 후 : " + list);
	}
}

/*
 * 정렬방식을 결정하는 class는 Comparator라는 인터페이스를 구현해야 한다.
 * Comparator 인터페이스의 compare() 메소드를 재정의 하여 구현하면 된다. 
 */
class Desc implements Comparator<String> {
	/*
	 * compare()의 반환값을 결정하는 방법
	 * 오름차순이 기본.
	 * - 오름차순 일 경우
	 *   앞의 값이 크면 양수, 같으면 0, 작으면 음수를 반환하도록 구현
	 *   
	 * - 내림차순 일 경우
	 *   앞의 값이 크면 음수, 같으면 0, 작으면 양수를 반환하도록 구현
	 */
	@Override
	public int compare(String str1, String str2) {
		return str1.compareTo(str2) * -1; //compareTo는 오름차순을 반환하므로 -1을 곱하여 내림차순으로 만든다.
	}
}