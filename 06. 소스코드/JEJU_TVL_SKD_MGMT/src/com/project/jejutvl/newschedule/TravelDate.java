package com.project.jejutvl.newschedule;

import java.util.Scanner;

import com.project.jejutvl.homescreen.Cls;
import com.project.jejutvl.homescreen.Resource;

//1단계) 여행 날짜 입력
public class TravelDate { 
	
	private static Scanner scanner;
	
	static {
		scanner = new Scanner(System.in);
	}

//--------------------------------------------------------------------------------
	
	public void start() throws Exception {

		// 중요!! 가는날의 교통편 선택과 오는날의 교통편 선택의 분기 설정을 위한 값!!
		Boolean flag = true; // 초기값
		
		printTravelDate();
		
		Transport ts = new Transport();
		ts.start(flag);

	} // Method : start

//--------------------------------------------------------------------------------
	
	public void printTravelDate() throws Exception {
		
		Resource.save.add(0, Resource.getMemberID());
		
		// 1단계 여행 날짜 입력 화면 출력
		System.out.println("\t\t--------------------------------------------------------------------------");
		System.out.println("\t\t==========================================================================\n");
		System.out.print("\t\t███╗   ██╗███████╗██╗    ██╗                                     \r\n" + 
				"\t\t████╗  ██║██╔════╝██║    ██║                                     \r\n" + 
				"\t\t██╔██╗ ██║█████╗  ██║ █╗ ██║                                     \r\n" + 
				"\t\t██║╚██╗██║██╔══╝  ██║███╗██║                                     \r\n" + 
				"\t\t██║ ╚████║███████╗╚███╔███╔╝                                     \r\n" + 
				"\t\t╚═╝  ╚═══╝╚══════╝ ╚══╝╚══╝                                      \r\n" + 
				"                                                                 \r\n" + 
				"\t\t███████╗ ██████╗██╗  ██╗███████╗██████╗ ██╗   ██╗██╗     ███████╗\r\n" + 
				"\t\t██╔════╝██╔════╝██║  ██║██╔════╝██╔══██╗██║   ██║██║     ██╔════╝\r\n" + 
				"\t\t███████╗██║     ███████║█████╗  ██║  ██║██║   ██║██║     █████╗  \r\n" + 
				"\t\t╚════██║██║     ██╔══██║██╔══╝  ██║  ██║██║   ██║██║     ██╔══╝  \r\n" + 
				"\t\t███████║╚██████╗██║  ██║███████╗██████╔╝╚██████╔╝███████╗███████╗\r\n" + 
				"\t\t╚══════╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═════╝  ╚═════╝ ╚══════╝╚══════╝\n");
		System.out.println("\t\t--------------------------------------------------------------------------");
		System.out.println("\t\t\t\t         [새로운 일정 작성]\n");
		System.out.println("\t\t\t\t--------------------------------------");
		System.out.println("\t\t\t\t1단계)");
		System.out.println("\t\t\t\t여행 날짜 입력\n");
		
		// TODO : 출발 날짜 설정
		System.out.print("\t\t\t\t출발날짜(XXXX-XX-XX) : ");
		Resource.setStartDate(scanner.nextLine());
		
		// TODO : 도착 날짜 설정
		System.out.print("\t\t\t\t도착날짜(XXXX-XX-XX) : ");
		Resource.setEndDate(scanner.nextLine());
		
		System.out.println("\t\t\t\t--------------------------------------");
		System.out.printf("\t\t\t\t출발날짜는 %s 이며,\n", Resource.getStartDate());
		System.out.printf("\t\t\t\t도착날짜는 %s 입니다.\n", Resource.getEndDate());
		System.out.println("\t\t\t\t계속하시려면 ENTER키를 눌러주세요.");
		
		String next = scanner.nextLine();
		
		if (next.equals("")) {
			Cls.clearScreen();
			System.out.println("\t\t\t\t\tLOADING.....");
			Thread.sleep(700);
			Cls.clearScreen();
		}

	} // Method : printTravelDate
	
} // Class : TravelDate



