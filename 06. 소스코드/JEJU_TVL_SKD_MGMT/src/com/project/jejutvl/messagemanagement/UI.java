package com.project.jejutvl.messagemanagement;

import java.util.Scanner;

public class UI {

	public final static int ADD = 1;
	private static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
	}
	
//-----------------------------------------------------------------	
	
	public void start() {
		
		System.out.println("\t\t\t\t[	메시지관리	]");
	}

//-----------------------------------------------------------------	
	
	public void end() {
		
		System.out.println("\t\t\t\t종료되었습니다.");
		
	}
	
//-----------------------------------------------------------------	
	
	public void menu() {
		
		System.out.println("\t\t\t\t-------------------------");
		System.out.println();
		System.out.println("\t\t\t\t[1] 받은 메시지함");
		System.out.println("\t\t\t\t[2] 메시지 보내기");
		System.out.println();
		System.out.println("\t\t\t\t[*] 홈화면으로 돌아가기");
		System.out.println();
		System.out.println("\t\t\t\t-------------------------");
		System.out.println("\t\t\t\t[ ]안의 번호를 입력하세요.");
		System.out.print("\t\t\t\t선택 : ");
	}
	
//-----------------------------------------------------------------	
	
	public void title() {
		
		System.out.println("\t\t\t\t[	    메시지 작성	   ]");
		
	}
	
//-----------------------------------------------------------------	
	
	public void pause(int n) {

		if (n == UI.ADD) {
			System.out.println("\t\t\t\t저장 되었습니다. \n\t\t\t\t계속하시려면 엔터를 입력하세요.");			
		}
		scan.nextLine();
		
	}
	
}
