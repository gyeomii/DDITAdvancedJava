package CollectionTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class T08MapTest {
/*
 	Map : key갑과 value값을 한 쌍으로 관리하는 객체
 		  key값은 중복을 허용하지 않고 순서가 없다.(Set의 특징)
 		  value값은 중복을 허용한다.(List의 특징)
 */
		public static void main(String[] args) {
			Map<String, String> map = new HashMap<String, String>();
			
			//자료추가 : put(key, value)
			map.put("name", "홍길동");
			map.put("address", "대전");
			map.put("tel", "010-1234-5678");
			
			System.out.println("map : " + map);
			 
			//자료 수정 : 데이터를 저장할 때 key값이 같으면 나중에 입력한 값이 저장된다.
			//			 put(수장할 key, 수정할 value)
			map.put("address", "서울");
			System.out.println("map : " + map);
			
			//자료 삭제 : remove(삭제할 key) 삭제성공시 삭제된 value반환, 실패시 null반환
			map.remove("name");
			System.out.println("map : " + map);
			
			//자료 읽기 : get(key)
			System.out.println("address : " + map.get("address"));
			System.out.println("=================================");
			
			//key값들을 읽어와 자료를 출력하는 방법
			
			//방법1 : keySet()메소드 이용하기
			//keySet()메소드 : Map의 key값들만 읽어와 Set형으로 반환
			Set<String> keySet = map.keySet();
			
			System.out.println("Iterator를 이용한 방법");
			Iterator<String> it = keySet.iterator();
			while(it.hasNext()) {
				String key = it.next();
				System.out.println(key + " : "+ map.get(key));
			}
			System.out.println("*********************************");
			
			//방법2 : Set형의 데이터를 '향상된 for문'으로 처리
			System.out.println("향상된 for문을 이용한 방법");
			for (String key : keySet) {
				System.out.println(key + " : "+ map.get(key));
			}
			System.out.println("*********************************");
			
			//방법3 : value값만 읽어와 출력하기 > values()이용	
			System.out.println("values()를 이용한 방법");
			for (String value : map.values()) {
				System.out.println(value);
			}
			System.out.println("*********************************");
			
			//방법4 : Map관련 클래스에는 Map.Entry 타입의 내부class가 만들어져 있다.
			//이 내부 클래스는 key와 value라는 멤버변수로 구성되어 있다.
			//Map에서 이 Map.Entry타입의 객체들을 Set형으로 가져올 수 있다.
			//entrySet()메소드 이용
			Set<Map.Entry<String, String>> mapSet = map.entrySet();
			
			//가져온 Entry객체들을 순서대로 처리하기 위해서 Iterator객체로 반환
			Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
			while(entryIt.hasNext()) {
				Map.Entry<String, String> entry = entryIt.next();
				System.out.println("key값 : " + entry.getKey());
				System.out.println("value값 : " + entry.getValue());
				System.out.println();
			}
			
		}
}
