package com.project.jejutvl.homescreen;
import java.io.IOException;
import java.util.Scanner;

import com.github.lalyos.jfiglet.FigletFont;

public class HomeScreen {

	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException, Exception {

		start(); // 메인메뉴 실행

	}
	
	public static void start() throws Exception {
		
		String select = printHomeScreen(); // 메뉴에서 선택된 번호 리턴
		homeScreen(select);

	} // start

	public static void homeScreen(String select) throws Exception {
		
		String next = ""; // ENTER키를 입력받을 변수
		
		switch (select) {

		case "1":
			
			
//			System.out.print("\n\t\t\t\t새로운 일정을 만듭니다.\n");
//			System.out.println("\t\t\t\tENTER키를 눌러 진행해주세요.");
//			next = scan.nextLine();
//			
//			System.out.println("\t\t\t\t\tLOADING.....");
//			Thread.sleep(1000);
//			NamePot.clearScreen(); // !] 추후 수정 ****
//			
//			TravelDate td = new TravelDate();
//			td.start(); // 출력문으로 이동
//			break;

		case "2":
			// 회원가입 후, 그 회원 이름으로 파일 하나가 만들어질 것임
			// 그 파일에 아무런 내용이 없으면 새로운 일정을 만들라는 메시지가 나와야 한다
			IDSkdCheck isCheck = new IDSkdCheck();
			isCheck.idSkdCheck(); // 검사해서 일정표 메뉴로 이동
			break;

		case "3":
			System.out.print("\n\t\t\t\t여행경비 메뉴로 이동합니다.\n\t\t\t\tENTER키를 눌러 진행해주세요.");
			next = scan.nextLine();
			
//			System.out.println("\t\t\t\t\tLOADING.....");
//			Thread.sleep(1000);
			Cls.clearScreen();
			
			break;

		case "4":
			System.out.print("\n\t\t\t\t메시지 관리.\n\t\t\t\tENTER키를 눌러 진행해주세요.");
			next = scan.nextLine();
			break;

		case "5":
			System.out.print("\n\t\t\t\t랜덤여행 메뉴로 이동합니다.\n\t\t\t\tENTER키를 눌러 진행해주세요.");
			next = scan.nextLine();
			break;

		case "0":
			System.out.print("\n\t\t\t\t프로그램을 종료합니다.\n\t\t\t\tENTER키를 눌러주세요.");
			next = scan.nextLine();
			break;
		}
		
	}

	public static String printHomeScreen() throws IOException { // !] 기존 MainMenu 메소드

		// using default font standard.flf, obtained from maven artifact
		System.out.println("\n\n\n\n\n\n\n\t\t*********************************************************************\n\n");
		String asciiArt1 = FigletFont.convertOneLine("     Jeju Travel");
		String asciiArt2 = FigletFont.convertOneLine("       Home Menu");
		System.out.println(asciiArt1);
		System.out.println(asciiArt2);
		System.out.println("\t\t*********************************************************************\n\n");
		System.out.println("\t\t\t\t[          메  뉴          ]");
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

	} // printHomeScreen
}
