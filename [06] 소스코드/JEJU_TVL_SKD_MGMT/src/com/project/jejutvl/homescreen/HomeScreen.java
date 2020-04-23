package com.project.jejutvl.homescreen;

import java.io.IOException;
import java.util.Scanner;

import com.project.jejutvl.messagemanagement.MessageManagement;
import com.project.jejutvl.newschedule.TravelDate;
import com.project.jejutvl.randomtravel.RandomTravel;

public class HomeScreen {

	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException, Exception {

		start(); // 메인메뉴 실행

	}
	
//--------------------------------------------------------------------------
	
	public static void start() throws Exception {
		
		// 메뉴에서 선택된 번호 리턴
		String select = printHomeScreen();
		
		homeScreen(select);

	} // start

//--------------------------------------------------------------------------
	
	public static void homeScreen(String select) throws Exception {
		
		String next = ""; // ENTER키를 입력받을 변수
		
		switch (select) {
		
		// TODO : 새 일정 만들기
		case "1":
			
			System.out.print("\n\t\t\t\t새로운 일정을 만듭니다.\n");
			System.out.println("\t\t\t\tENTER키를 눌러 진행해주세요.");
			next = scan.nextLine();
			
			if (next.equals("")) {
				Cls.clearScreen();
				System.out.println("\t\t\t\t\tLOADING.....");
				Thread.sleep(700);
				Cls.clearScreen();
				
				//일정 만들기 시작 - 날짜 입력 화면으로 이동
				TravelDate td = new TravelDate();
				td.start();
				break;	
			}
		
			
		// TODO : 일정표 확인하기	
		case "2":
			
			// 검사해서 일정표 메뉴로 이동
			String menuType = "TravelPlan";
			IDSkdCheck isCheck = new IDSkdCheck();
			isCheck.idSkdCheck(menuType);
			break;

			
		// TODO : 여행 경비 확인하기
		case "3":
			
			// 검사해서 여행 경비 메뉴로 이동
			menuType = "TravelCost";
			IDSkdCheck isCheckCost = new IDSkdCheck();
			isCheckCost.idSkdCheck(menuType);
			break;

			
		// TODO : 메시지 관리
		case "4":
			
			System.out.print("\n\t\t\t\t메시지 관리.\n\t\t\t\tENTER키를 눌러 진행해주세요.");
			next = scan.nextLine();
			
			Cls.clearScreen();
			System.out.println("\t\t\t\t\tLOADING.....");
			Thread.sleep(700);
			Cls.clearScreen();
			MessageManagement.main(null);
			break;

		
		// 랜덤 매칭 
		case "5":
			
			System.out.print("\n\t\t\t\t랜덤여행 메뉴로 이동합니다.\n\t\t\t\tENTER키를 눌러 진행해주세요.");
			next = scan.nextLine();
			
			Cls.clearScreen();
			System.out.println("\t\t\t\t\tLOADING.....");
			Thread.sleep(700);
			Cls.clearScreen();
			RandomTravel.start();	
			break;

		
		//프로그램 종료	
		case "0":
			
			System.out.print("\n\t\t\t\t프로그램을 종료합니다.\n\t\t\t\tENTER키를 눌러주세요.");
			next = scan.nextLine();
			
			Cls.clearScreen();
			System.out.println("\t\t\t\t\tLOADING.....");
			Thread.sleep(500);
			Cls.clearScreen();
			System.out.println("\t\t\t\t\t[시스템이 종료되었습니다]");
			System.exit(0);
			break;
			
		} // Switch
		
	} // Method : homeScreen

//--------------------------------------------------------------------------------------------------------------------
	
	public static String printHomeScreen() throws IOException { // !] 기존 MainMenu 메소드

		System.out.println("\n\t\t==========================================================================");
		System.out.println("\t\t--------------------------------------------------------------------------\n\n");

		System.out.print("\t\t██╗  ██╗ ██████╗ ███╗   ███╗███████╗    ███╗   ███╗███████╗███╗   ██╗██╗   ██╗\r\n" + 
				"\t\t██║  ██║██╔═══██╗████╗ ████║██╔════╝    ████╗ ████║██╔════╝████╗  ██║██║   ██║\r\n" + 
				"\t\t███████║██║   ██║██╔████╔██║█████╗      ██╔████╔██║█████╗  ██╔██╗ ██║██║   ██║\r\n" + 
				"\t\t██╔══██║██║   ██║██║╚██╔╝██║██╔══╝      ██║╚██╔╝██║██╔══╝  ██║╚██╗██║██║   ██║\r\n" + 
				"\t\t██║  ██║╚██████╔╝██║ ╚═╝ ██║███████╗    ██║ ╚═╝ ██║███████╗██║ ╚████║╚██████╔╝\r\n" + 
				"\t\t╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝    ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝ \r\n");
		
		System.out.println("\n\t\t--------------------------------------------------------------------------\n\n");
		System.out.println("\t\t\t\t[           메   뉴                  ]");
		System.out.println();
		System.out.println("\t\t\t\t----------------------------");
		System.out.println();
		System.out.println("\t\t\t\t  [1] 새로운 일정");
		System.out.println("\t\t\t\t  [2] 여행 일정표");
		System.out.println("\t\t\t\t  [3] 여행 경비");
		System.out.println("\t\t\t\t  [4] 메시지 관리");
		System.out.println("\t\t\t\t  [5] 랜덤 여행");

		System.out.println("\n\t\t\t\t  [0] 프로그램 종료");

		System.out.println("\n\t\t\t\t----------------------------");
		System.out.println("\t\t\t\t[ ] 안의 번호를 입력하세요");
		System.out.print("\t\t\t\t선택(번호) : ");
		String select = scan.nextLine();

		return select;

	} // Method : printHomeScreen
	
} // Class : HomeScreen



