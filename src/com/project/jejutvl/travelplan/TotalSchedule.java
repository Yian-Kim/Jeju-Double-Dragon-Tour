package com.project.jejutvl.travelplan;

import java.util.Scanner;

class TotalSchedule {
	
	public void start() throws Exception {
		
		String select = printTotalSchedule();
	
		switch(select) {
			case "0" : // 세부 일정표 보기
				DetailedSchedule dSkd = new DetailedSchedule();
				dSkd.start();
				break;
			case "#" : // 일정표 선택 베뉴로 돌아가기
				TravelPlan tp = new TravelPlan();
				tp.start();
				break;
			case "*" : // 메인메뉴로 돌아가기
				System.out.println(); // !] 소스코드 추가 필요
				break;
		}
	}

	public String printTotalSchedule() {
		// 총 일정표 출력 화면
		Schedule skd = new Schedule();
		Scanner scan = new Scanner(System.in);

		// 일정에 따라 스케줄 늘이고 줄이기 변수 추가
		
		System.out.println();
		System.out.println("\t[총 일정표]");
		System.out.println();
		System.out.printf("\t%s 님의 여행일정\n", skd.getName());
		System.out.printf("\t%s ~ %s\n", skd.getStartDate(), skd.getFinishDate());
		System.out.println();
		System.out.println("=======================================");
		System.out.printf("(%d일차) %s\n", 1, skd.getStartDate());
		System.out.println("---------------------------------------");
		System.out.println("번호\t항목\t주요일정"); // 여기서부터는 그냥 텍스트
		System.out.println("---------------------------------------");
		System.out.println("001\t항공\t인천→제주(JA001, 09:00)");
		System.out.println("002\t명소\t제주항공우주박물관");
		System.out.println("003\t맛집\t제주김만복 동문시장점");
		System.out.println("004\t숙박\t제주 해비치 컨트리클럽");
		System.out.println();
		
		// =========================================================
		
		System.out.println("=======================================");
		System.out.printf("(%d일차) %s\n", 2, "2019-03-07");
		System.out.println("---------------------------------------");
		System.out.println("번호\t항목\t주요일정"); // 여기서부터는 그냥 텍스트
		System.out.println("---------------------------------------");
		System.out.print("001\t맛집\t춘심이네 본점\n");
		System.out.print("002\t명소\t제주공룡랜드\n");
		System.out.print("003\t숙박\t호텔테디밸리제주\n");
		System.out.println();
		System.out.println("=======================================");
		System.out.printf("(%d일차) %s\n", 3, "2019-03-08");
		System.out.println("---------------------------------------");
		System.out.println("번호\t항목\t주요일정"); // 여기서부터는 그냥 텍스트
		System.out.println("---------------------------------------");
		System.out.print("001\t맛집\t신라원중문점\n");
		System.out.print("002\t명소\t헬로키티아일랜드\n");
		System.out.print("003\t숙박\t시워터스파 호텔 코자\n");
		System.out.println();
		System.out.println("=======================================");
		System.out.printf("(%d일차) %s\n", 4, skd.getFinishDate());
		System.out.println("---------------------------------------");
		System.out.println("번호\t항목\t주요일정"); // 여기서부터는 그냥 텍스트
		System.out.println("---------------------------------------");
		System.out.print("001\t해운\t제주→부산(NG003, 11:45)\n");
		System.out.print("002\t맛집\t시골가마솥돗궤기\n");
		System.out.println();
		System.out.println("=======================================");
		System.out.println("[0] 세부 일정표 보기");
		System.out.println("[#] 일정표 선택 메뉴로 돌아가기");
		System.out.println("[*] 메인메뉴로 돌아가기");
		System.out.println("---------------------------------------");
		System.out.println("[ ] 안의 번호를 입력하세요.");
		System.out.print("선택 : ");
		String select = scan.nextLine();
		
		return select;
	}
}
