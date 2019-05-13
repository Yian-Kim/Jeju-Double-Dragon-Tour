package com.project.jejutvl.messagemanagement;

public class UiMessageManagement { //>>>>>>>>>>>>>>>>>>>>>>>> TODO 주석 추가 : 전체 UI 클래스

	public void start() { //>>>>>>>>>>>>>>>>>>>>>>> Title
		
		System.out.println("\n\t\t----------------------------------------------------------------");
		System.out.println("\t\t================================================================\n\n");
		System.out.print("\t\t███╗   ███╗███████╗███████╗███████╗ █████╗  ██████╗ ███████╗\r\n" + 
				"\t\t████╗ ████║██╔════╝██╔════╝██╔════╝██╔══██╗██╔════╝ ██╔════╝\r\n" + 
				"\t\t██╔████╔██║█████╗  ███████╗███████╗███████║██║  ███╗█████╗  \r\n" + 
				"\t\t██║╚██╔╝██║██╔══╝  ╚════██║╚════██║██╔══██║██║   ██║██╔══╝  \r\n" + 
				"\t\t██║ ╚═╝ ██║███████╗███████║███████║██║  ██║╚██████╔╝███████\r\n");
		System.out.println();
		System.out.println("\t\t----------------------------------------------------------------");
		System.out.println("\t\t\t\t[	메시지관리	   ]");
	}
	
	public void end() {
		
		System.out.println("\t\t\t\t종료되었습니다.");
		
	}
	public void menu() { // >>>>>>>>>>> 메시지 관리 메뉴 UI
		
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
	
}
