package JavaCollectionFramework.StudentListSort;

class Student implements Comparable<Student> {
	private String stdNum; // 학번
	private String name; // 이름
	private int korScore; // 국어점수
	private int engscore; // 영어점수
	private int mathScore; // 수학점수
	private int totalScore; // 총점
	private int rank; // 등수

	public Student(String stdNum, String name, int korScore, int engscore, int mathScore) {
		super();
		this.stdNum = stdNum;
		this.name = name;
		this.korScore = korScore;
		this.engscore = engscore;
		this.mathScore = mathScore;
		this.totalScore = korScore + engscore + mathScore;
	}

	public String getStdNum() {
		return stdNum;
	}

	public void setStdNum(String stdNum) {
		this.stdNum = stdNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorScore() {
		return korScore;
	}

	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}

	public int getEngscore() {
		return engscore;
	}

	public void setEngscore(int engscore) {
		this.engscore = engscore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(Student std) {
		return this.getStdNum().compareTo(std.getStdNum());
	}

	@Override
	public String toString() {
		return "Student [학번: " + stdNum + ", 이름: " + name + ", 국어점수: " + korScore + ", 영어점수: " + engscore
				+ ", 수학점수: " + mathScore + ", 총점: " + totalScore + ", 등수: " + rank + "]";
	}

}
