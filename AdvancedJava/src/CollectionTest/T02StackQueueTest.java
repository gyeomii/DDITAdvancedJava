package CollectionTest;

import java.util.LinkedList;

public class T02StackQueueTest {
	public static void main(String[] args) {
		/*
		 * Stack : 후입선출(LIFO) 자료구조
		 * Queue : 선입선출(FIFO) 자료구조
		 * 
		 * LinkedList를 이용한 스택 및 큐 예제
		 */
		// LinkedList 장점 : 데이터 추가 속도가 빠름
		// LinkedList 단점 : 검색속도가 느림
		LinkedList<String> stack = new LinkedList<String>();
		/*
		 * Stack의 명령
		 * 1) 자료 입력 : push(저장할값)
		 * 2) 자료 출력 : pop() -> 자료를 꺼내온 후 꺼내온 자료를 stack에서 삭제함
		 */
		stack.push("홍길동");
		stack.push("일지매");
		stack.push("변학도");
		stack.push("강감찬");
		System.out.println("현재 stack값들 : " + stack);
		
		String date = stack.pop();
		System.out.println("꺼내온 자료 : " + date);
		System.out.println("꺼내온 자료 : " + stack.pop());
		System.out.println("현재 stack값들 : " + stack);
		
		stack.push("성춘향");
		System.out.println("현재 stack값들 : " + stack);
		System.out.println("꺼내온 자료 : " + stack.pop());
		System.out.println("==============================");
		System.out.println();
		
		LinkedList<String> queue = new LinkedList<String>();
		/*
		 * Queue의 명령
		 * 1) 자료 입력 : offer(저장할 값)
		 * 2) 자료 출력 : poll() -> 자료를 Queue에서 꺼내온 후 꺼내온 자료는 Queue에서 삭제함
		 */
		queue.offer("홍길동");
		queue.offer("일지매");
		queue.offer("변학도");
		queue.offer("강감찬");
		System.out.println("현재 Queue의 값 : " + queue);
		
		String temp = queue.poll();
		System.out.println("꺼내온 자료 : " + temp);
		System.out.println("꺼내온 자료 : " + queue.poll());
		System.out.println("현재 Queue의 값 : " + queue);
		
		if (queue.offer("성춘향")) {
			System.out.println("신규 등록 자료: 성춘향");
		}
		System.out.println("현재 Queue의 값 : " + queue);
		System.out.println("꺼내온 자료 : " + queue.poll());
	}
}
