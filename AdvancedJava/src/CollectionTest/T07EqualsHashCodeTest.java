package CollectionTest;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class T07EqualsHashCodeTest {
/*
 	해시함수(hash function)는 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수이다.
 	해시함수에 의해 얻어지는 값은 해시값, 해시코드, 해시체크섬 또는 간단하게 해시라고 한다.
 	
 	HashSet, HashMap, Hashtable과 같은 컬렉션 객체들을 사용할 경우
 	객체가 서로 같은지를 비교하기 위해 equals()메소드와 hashCode()메소드를 호출 한다.
 	그래서 객체가 서로 같은지 여부를 결정하려면 두 메소드를 재정의 해야한다.
 	객체가 같은지 여부는 데이터를 추가할 때 검사한다.
 	
 	- equals()메소드는 두 객체의 내용(값)이 같은지 비교하기 위한 메소드이고,
 	  hashCode()메소드는 객체에 대한 해시코드값을 반환하는 메소드이다.
 	
 	- equals()메소드와 hashCode()메소드에 관련된 규칙
 	  1. 두 객체가 같으면 반드시 같은 hashcode를 가져야 한다.
 	  2. 두 객체가 같으면 equals()를 호출했을 때 true를 반환해야 한다.
 	  	 즉, 객체 a, b가 같다면 a.equlas(b)와 b.equals(a) 둘 다 true여야 한다.
 	  3. 두 객체의 hashcode가 같다고 해서 두 객체가 반드시 같은 객체는 아니다.
 	     하지만, 두 객체가 같으면 반드시 hashcode가 같아야 한다.
 	  4. equals()메소드를 override하면 반드시 hashCode()도 override 해야한다.
 	  5. hashCode()는 기본적으로 Heap메모리에 있는 각 객체의 메모리 주소 매핑정보를 기반으로한 정수값을 반환한다.
 	  	 그러므로, 클래스에서 hashCode()메소드를 override하지 않으면 절대로 두 객체가 같은 것으로 간주될 수 없다.
 */
	public static void main(String[] args) {
		String str3 = "FB";// (Aa와 BB도 같은 해시코드를 갖는다.
		String str4 = "Ea";
		System.out.println(str3.equals(str4) + " : " + str4.equals(str3));
		System.out.println("str3 해시코드: " + str3.hashCode() + " : " + "str4 해시코드: " + str4.hashCode());
		System.out.println("-------------------------------------");

		Person p1 = new Person(1, "홍길동");
		Person p2 = new Person(1, "홍길동");
		Person p3 = new Person(1, "이순신");
		
		System.out.println("p1.equals(p2) : " + p1.equals(p2));
		System.out.println("p1 == p2 : " + (p1 == p2));
		System.out.println();
		
		Set<Person> set = new HashSet<Person>();
		
		set.add(p1);
		set.add(p2);
		
		System.out.println("p1, p2 등록 후 데이터");
		for (Person p : set) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		System.out.println();
		System.out.println("add(p3) 성공여부 : " + set.add(p3));
		System.out.println("p3 등록 후 데이터");
		for (Person p : set) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		System.out.println();
		System.out.println("remove(p2) 성공여부 : " + set.remove(p2));
		System.out.println("p2 삭제 후 데이터");
		for (Person p : set) {
			System.out.println(p.getId() + " : " + p.getName());
		}
	}
}

class Person {
	private int id;
	private String name;

	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Person)) {
			return false;
		}
		Person other = (Person) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

//	@Override
//	public int hashCode() {
//		return (name + id).hashCode();
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		Person p = (Person) obj;
//		return this.id == p.getId() && this.getName().equals(p.getName());
//	}
	

}