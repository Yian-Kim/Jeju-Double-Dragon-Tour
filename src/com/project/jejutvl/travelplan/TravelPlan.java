package com.project.jejutvl.travelplan;

import java.util.Scanner;

public class TravelPlan {
//	public void sub (String[] args) throws Exception {
//		
//		TravelPlan tp = new TravelPlan();
//		tp.start();		
//	}

	public void start() throws Exception {
		// 여행일정표 메뉴 진입
		
		String select = printTravelPlan(); // 여행일정표 메뉴 출력
		
		switch(select) {
			case "1" : // 총 일정표
				TotalSchedule tSkd = new TotalSchedule();
				tSkd.start();
				// new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // 화면 지우기
				break;
		
			case "2" : // 날짜별 세부 일정표
				DetailedSchedule dSkd = new DetailedSchedule();
				dSkd.start();
				break;
				
			case "3" : // 매칭 일정표
				MatchSchedule mSkd = new MatchSchedule();
				mSkd.start();
				break;
			
			case "*" : // 메인 화면으로 돌아가기
				System.out.println("*"); // !] 코드 추가 필요
				break;
		}
	}

	public static String printTravelPlan() {
		// 여행일정표 메뉴 출력
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.println("\t[일 정 표    선 택]");
		System.out.println("\t***********************");
		System.out.println();
		System.out.println("\t[1] 총 일정표");
		System.out.println("\t[2] 날짜별 세부 일정표");
		System.out.println("\t[3] 매칭 일정표");
		System.out.println();
		System.out.println("\t[*] 메인화면으로 돌아가기");
		System.out.println();
		System.out.println("\t***********************");
		System.out.println("\t[ ] 안의 번호를 입력하세요.");
		System.out.print("\t선택 : ");
		String select = scan.nextLine();
		
		return select;
	}
}
