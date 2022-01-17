package tptty.example02;

import java.util.*;

import java.util.function.Predicate;
import java.util.stream.Collectors;

class StdComparator implements Comparator<Student>{

	int flag;
	
	//생성자 추가
	public StdComparator(int flag) {
		this.flag = flag;
	}


	@Override
	public int compare(Student o1, Student o2) {
		
		
		switch(flag) { //return문이 있으니까 굳이 break문 필요x
		
		case 1 :
			return (o1.name.compareTo(o2.name))*-1;
		case 2 :
			return (o1.sid.compareTo(o2.sid));
		case 0 :
		default :
			return (o1.score - o2.score)*(-1);
			
			
		}
		
	}
	
}

public class TestMain {

	public static void main(String[] args) {
		List<Student> list = new ArrayList<>();
		list.add(new Student("홍길동", "2000", 90));
		list.add(new Student("고길동", "2010", 85));
		list.add(new Student("이길동", "2001", 75));
		list.add(new Student("차길동", "2013", 90));
		list.add(new Student("김길동", "2007", 88));
		
		Collections.shuffle(list); //막 섞기
		
//		1. Stream<Student> stream = list.stream();
//		2. list.stream().sorted().forEach(std->System.out.println(std));
		//정렬하고 반복해서 std출력
//		3. list.stream().sorted().forEach(System.out::println);
		//sorted도 인자로 comparator받음, 못받았을 경우엔 자동으로 compareTo호출
//		4. list.stream().sorted((o1, o2)->o1.sid.compareTo(o2.sid)).forEach(System.out::println);
		//5. 
		list.stream().sorted((o1, o2)->o1.score-o2.score).forEach(System.out::println);
		//Stream객체를 반환하지 않고 Stream객체를 List로 변환
		List<Student> result = list.stream().sorted((o1, o2)->(o1.score-o2.score)*-1).collect(Collectors.toList());
		System.out.println(result);
		
		System.out.println("=======================");
		
		Predicate<Student> p1 = std->std.score>80;
		for(Student std : list) {
			if(p1.test(std)) { //조건식에 따라 boolean값 반환
				System.out.println(std);
			}
		}
		
		System.out.println("=======================");
		
		
		//Predicate Stream으로 작성하기
		list.stream().filter(p1).forEach(System.out::println);
		
		System.out.println("=======================");
		
		//점수 합 구하기
		/*int도 가능*/ Integer result1 = list.stream().collect(Collectors.summingInt(std->std.getScore())); //메소드참조 ver. Student::getScore (클래스이름::메소드)
		System.out.println(result1);
		
		//점수 평균 구하기
		/*double*/ Double result2 = list.stream().collect(Collectors.averagingInt(Student::getScore)); //int데이터에 대한 평균계산 : 반환값은 double
		
		//int형 요약
		IntSummaryStatistics result3 = list.stream().collect(Collectors.summarizingInt(Student::getScore)); //반환값은 IntSummaryStatistics
		System.out.println(result3.getAverage()); //요약된 정보의 평균확인
		
		Collections.sort(list);
		//이름순서로 정렬되어있을 때, binarySearch사용
		//1. Comparable사용 : compareTo가 0을 반환하는 순간 찾기
		int index = Collections.binarySearch(list, new Student("박길동")); //찾으려는 것도 Student타입으로 정의되어야함(Student에 생성자 필요)
		if(index>=0) { //데이터가 있다면
			System.out.println(list.get(index));
		}else
			System.out.println("등록되어 있지 않음");
		
		//2. Comparator사용
		StdComparator cmp = new StdComparator(2);
		//int compare(Student o1, Student o2)을 람다표현식으로 변경
		Collections.sort(list, cmp);
		//Collections.sort(list, (Student o1, Student o2)->{return o1.sid.compareTo(o2.sid);});
		//list에 들어있는 게 Student형이므로 위의 매개변수에서 Student생략 가능
		//Q : sort(list, comparator)가 들어가야하는데 왜 익명함수가 이를 대체할 수 있는 거지?
		//A : 함수형 인터페이스때문
		
		index = Collections.binarySearch(list, new Student("","2007",0), cmp //cmp대신 위의 람다표현식 사용가능
//				new Comparator<Student>() {
//
//					@Override
//					public int compare(Student o1, Student o2) {
//					
//						return o1.sid.compareTo(o2.sid); //학번순으로 오름차순 정렬
//						
//					}
//					
//				}
				); //Student에 학번만 있는 생성자가 없으므로
		
		if(index>=0) { //데이터가 있다면
			System.out.println(list.get(index));
		}else
			System.out.println("등록되어 있지 않음");
		
		
		
		System.out.println(list);
		//오류원인 : 그냥 sort를 쓰면, 사용자가 정의한 Student클래스는 컴파일러가 무엇을 기준으로 정렬해야할 지 알 수 없음.
		//해결방법 : Comparable인터페이스를 구현해서 기준 정의(compareTo)
		
		//P : 다양한 비교기준마다 compareTo를 재정의 할 수는 없음
		//S : Comparator인터페이스 구현
		
		//Comparable : 아무것도 주어지지 않았을 경우, 자동으로 호출(인터페이스 구현)->compareTo
		//Comparator : sort의 인자로 들어감 or Comparator를 구현한 클래스를 이용해도 됨
//		1. Collections.sort(list, new Comparator<Student>(){
//			
//			//익명객체 선언 : 딱 한번만 사용되는 객체
//			
//			@Override
//			public int compare(Student o1, Student o2) {
//				
//				return (o1.score - o2.score)*(-1); //정렬기준 : 내림차순
//			}
//			
//		});
		
		//2. 
//		Collections.sort(list, new StdComparator()); //생성자 추가하지 않았을 경우
		Collections.sort(list, new StdComparator(1));
		System.out.println(list);
		Collections.sort(list, new StdComparator(2));
		System.out.println(list);
		Collections.sort(list, new StdComparator(0));
		System.out.println(list);
		

	}

}
