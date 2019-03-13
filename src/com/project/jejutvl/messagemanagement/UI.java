package com.project.jejutvl.messagemanagement;

import java.util.Scanner;

public class UI {

	public final static int ADD = 1;

	
	public void start() {
		
		System.out.println("[	메시지관리	]");
	}
	
	public void end() {
		
		System.out.println("종료되었습니다.");
		
	}
	public void menu() {
		
		System.out.println("*************************");
		System.out.println();
		System.out.println("[1] 받은 메시지함");
		System.out.println("[2] 메시지 보내기");
		System.out.println();
		System.out.println("[*] 홈화면으로 돌아가기");
		System.out.println();
		System.out.println("*************************");
		System.out.println("[ ]안의 번호를 입력하세요.");
		System.out.print("선택 : ");
	}
	public void title(int n) {
		
		
	}
	
	public void pause(int n) {
		
		Scanner scan = new Scanner(System.in);
		
		if (n == UI.ADD) {
			System.out.println("저장 되었습니다. \n계속하시려면 엔터를 입력하세요.");			
		}
		scan.nextLine();
	}
}
