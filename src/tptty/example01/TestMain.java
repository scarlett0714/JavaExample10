package tptty.example01;

import java.util.*;

public class TestMain {

	public static void main(String[] args) {
		
		Integer[] arr = {10,5,8,3,2,7,9};
//		int[] arr = {10,5,8,3,2,7,9};
		
//		Arrays.sort(arr); //오름차순으로 배열 정렬
		
		List<Integer> list = Arrays.asList(arr);
		//Arrays.asList : 일반 배열->list
		//오류원인 : Collection은 참조타입만 받을 수 있음 but 일반배열 arr이 int형임
		//해결방법 : 일반배열 arr의 자료형을 Integer[]로 변경
		
		List<Integer> list2 = new ArrayList<>(Arrays.asList(arr));
		//Q. ArrayList객체를 만들고 싶다면?
		//A. new ArrayList<>(arr); (x) -> new ArrayList<>(Arrays.asList(arr)); (o) or new ArrayList<>(list);
		//인자로 list가 들어가던지 list로 변환시켜준게 들어가던지
		
		List<Integer> list3 = new ArrayList<>();
		Collections.addAll(list3, arr); //addAll : list3에 일반배열인 arr의 요소들을 모두 add할 것
		//Collection->List구조여야만 Collection클래스에서 제공하는 알고리즘 사용 가능
		printArray(list);
		Collections.shuffle(list); //요소들 막 섞기
		printArray(list);
		Collections.sort(list, Collections.reverseOrder()); //Collections.reverseOrder() : Comparator기능(비교)반환 ->값을 비교해서 내림차순으로 반환
		printArray(list);
		Collections.sort(list); //오름차순 정렬
		printArray(list);
		Collections.reverse(list); //오름차순 후 reverse : 내림차순 정렬
		printArray(list);
		
		//사용자가 정의한 Class의 경우 어떻게 정렬할까?
		//기준을 정하는 방법
		//1. Comparable인터페이스 2. Comparator인터페이스
	}

	public static void printArray(List<Integer> list) {
		for(int num : list) {
			System.out.print(num+"\t");
		}
		
		System.out.println();
		
	}

}
