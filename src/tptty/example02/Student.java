package tptty.example02;

public class Student implements Comparable<Student>{ //<-public class Student

	String name;
	String sid;
	int score;
	
	
	public Student(String name) {
		this(name, "noinfo", 0);
	}


	public Student(String name, String sid, int score) {
		this.name = name;
		this.sid = sid;
		this.score = score;
	}



	public int getScore() {
		return score;
	}


	@Override
	public String toString() {
		
		return this.name+" : "+this.sid+" : "+this.score;
	}


	//Comparable인터페이스를 implements하면 compareTo메소드 오버라이딩 가능(기준 재정의)
	@Override
	public int compareTo(Student o) { //기준은 o
		
		//이름을 기준으로 정렬
		return this.name.compareTo(o.name); //String의 비교는 compareTo사용 (사전순)
		
		//점수를 기준으로 정렬 (숫자값 비교)
//		return this.score - o.score; //오름차순으로 정렬
//		return (this.score - o.score)*(-1); //내림차순으로 정렬
	}
	
	
	
	
}
