package com.project.jejutvl.newschedule;

import java.util.Scanner;

import com.project.jejutvl.homescreen.Cls;
import com.project.jejutvl.homescreen.Resource;

public class TravelDate { // 1단계) 여행 날짜 입력

	Scanner scan = new Scanner(System.in);
	Transport ts = new Transport();

	
	public void start() throws Exception {

		
		Boolean flag = true; // 초기값
		printTravelDate();
		travelDate(flag);
		ts.start(flag);

	} // TravelDate 클래스의 start 메소드

	private Boolean travelDate(Boolean flag) throws InterruptedException {

		// 중요!! 가는날의 교통편 선택과 오는날의 교통편 선택의 분기 설정을 위한 값!!

		flag = true;
		Cls.clearScreen();
//		System.out.println("\t\t\t\t\tLOADING.....");
//		Thread.sleep(1000);
//		NamePot.clearScreen();

		return flag;
	}

	public void printTravelDate() {
		// 1단계 여행 날짜 입력 화면 출력

		// TODO : 회원가입 후, 회원정보.dat파일에 저장된 ID 정보를 불러와 이곳에 저장
		Resource.save.add(0, "mygom1171");

		System.out.println("\t\t\t\t         [새로운 일정 작성]\n");
		System.out.println("\t\t\t\t--------------------------------------");
		System.out.println("\t\t\t\t1단계)");
		System.out.println("\t\t\t\t여행 날짜 입력");
		System.out.println();
		System.out.print("\t\t\t\t출발날짜(XXXX-XX-XX) : ");
		Resource.setStartDate(scan.nextLine());
		System.out.print("\t\t\t\t도착날짜(XXXX-XX-XX) : ");
		Resource.setEndDate(scan.nextLine());
		System.out.println("\t\t\t\t--------------------------------------");
		System.out.printf("\t\t\t\t출발날짜는 %s 이며,\n", Resource.getStartDate());
		System.out.printf("\t\t\t\t도착날짜는 %s 입니다.\n", Resource.getEndDate());
		System.out.println("\t\t\t\t계속하시려면 ENTER키를 눌러주세요.");
		// String next = scan.nextLine();
	}
}

// !] 임시

//Resource.save.add(0, Resource.getMemberName());
//Resource.save.add(1, Resource.getMemberID());

//Resource.save.add(2, Resource.getTravelStart());
//Resource.save.add(3, Resource.getTravelEnd());

//if (next.equals("")) {
//String flag = "go"; //중요!! 가는날의 교통편 선택과 오는날의 교통편 선택의 분기 설정을 위한 값!!
//ui.clearScreen();
//selectTransport(flag);
//}
