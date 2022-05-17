package JavaCollectionFramework.StudentListSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentSort {
	public static void main(String[] args) {
		ArrayList<Student> stdList = new ArrayList<Student>();

		stdList.add(new Student("201602037", "김성겸", 100, 100, 100));
		stdList.add(new Student("201702157", "고양희", 95, 56, 78));
		stdList.add(new Student("201602049", "강아지", 86, 77, 96));
		stdList.add(new Student("201902055", "문솔희", 54, 68, 72));
		stdList.add(new Student("201602156", "오진어", 70, 80, 90));
		stdList.add(new Student("201802037", "박수처", 90, 80, 70));
		stdList.add(new Student("201502110", "황금색", 88, 88, 88));

		StudentSort.Ranking(stdList); // 랭킹 구하기
		System.out.println("정렬 전");
		for (Student str : stdList) {
			System.out.println(str);
		}
		System.out.println("===================");

		// 학번으로 오름차순으로 정렬하기
		Collections.sort(stdList);
		System.out.println("학번으로 오름차순으로 정렬");
		for (Student str : stdList) {
			System.out.println(str);
		}
		System.out.println("===========================");
		// 총점으로 내림차순
		Collections.sort(stdList, new TotalScoreSort());
		System.out.println("총점으로 내림차순으로 정렬");
		for (Student str : stdList) {
			System.out.println(str);
		}
		System.out.println("=====================================");
	}

	public static void Ranking(List<Student> stdList) {
		for (Student std1 : stdList) {
			int rank = 1;
			for (Student std2 : stdList) {
				if (std1.getTotalScore() < std2.getTotalScore()) {
					rank++;
				}
			}
			std1.setRank(rank);
		}
	}
}

class TotalScoreSort implements Comparator<Student> {

	@Override
	public int compare(Student std1, Student std2) {
		if (std1.getTotalScore() == std2.getTotalScore()) {
			return std1.getStdNum().compareTo(std2.getStdNum()) * -1;
		} else {
			return Integer.compare(std1.getTotalScore(), std2.getTotalScore()) * -1;
		}
	}
}
