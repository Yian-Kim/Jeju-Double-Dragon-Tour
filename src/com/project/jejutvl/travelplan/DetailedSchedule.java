package com.project.jejutvl.travelplan;

class DetailedSchedule {

	public void start() throws Exception {
		// 세부 일정표
		String select = printDetailedSchedule();
		
		switch(select) {
			case "1" : // 추가하기
				break;
			case "2" : // 삭제하기
				break;
			case "0" : // 다음 일정 보기
				break;
			case "*" : // 일정표 선택 메뉴로 돌아가기
				TravelPlan tp = new TravelPlan();
				tp.start();
				break;
		}
	}

	public String printDetailedSchedule() {
		// 세부 일정표 화면 출력
		Schedule skd = new Schedule();
		
		System.out.println();
		System.out.println("\t[ 세부 일정표 ]");
		System.out.println();
		System.out.printf("\t%s 님의 여행일정\n", skd.getName());
		System.out.printf("\t%s ~ %s\n", skd.getStartDate(), skd.getFinishDate());
		System.out.println();
		System.out.println("===============================================================================");
		System.out.printf("\t(%d일차) %s\n", 1, skd.getStartDate());
		System.out.println("===============================================================================");
		System.out.println("번호\t항목\t주요일정\t\t\t\t\t\t비용");
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("001\t항공\t인천→제주(JA001, 09:00)\t\t\t\t70,000");
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("002\t명소\t제주항공우주박물관\t\t\t\t\t10,000");
		System.out.println("\t\t제주특별자치도 서귀포시 안덕면 녹차분재로 218");
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("003\t맛집\t제주김만복 동문시장점\t\t\t\t\t6,000");
		System.out.println("\t\t제주특별자치도 제주시 북성로 65");
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("004\t숙박\t제주 해비치 컨트리클럽\t\t\t\t\t158,730");
		System.out.println("\t\t제주특별자치도 서귀포시 남원읍 원님로 399번길 319");
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("[1] 추가하기");
		System.out.println("[2] 삭제하기");
		System.out.println();
		System.out.println("[0] 다음 일정 보기");
		System.out.println("[*] 일정표 선택 메뉴로 돌아가기");
		System.out.println("----------------------------------");
		System.out.println("[ ] 안의 번호를 입력하세요.");
		System.out.print("선택 : ");
		String select = skd.scan.nextLine();
	
		return select;
	}
}
