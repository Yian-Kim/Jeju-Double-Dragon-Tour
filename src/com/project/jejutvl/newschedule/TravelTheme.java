package com.project.jejutvl.newschedule;

import java.util.Calendar;
import java.util.Scanner;

import com.project.jejutvl.homescreen.HomeScreen;
import com.project.jejutvl.homescreen.Cls;
import com.project.jejutvl.homescreen.Resource;
import com.project.jejutvl.travelplan;

public class TravelTheme {

	Calendar start = Calendar.getInstance();
	Scanner scan = new Scanner(System.in);

	// 4단계. 테마선택
	public void start(String day) throws Exception {
		
		calcDate(day);
		String select = printTravelTheme(day); // 테마선택 화면 출력
		int menu = travelTheme(select, day);	// 테마 선택 시 처리
		
		switch(menu) {
			case 1 : // 테마 추천리스트 출력
				RecommendList rList = new RecommendList();
				rList.start(select);
				break;
			case 2 : // 일자선택으로 돌아가기
				TravelDay tDay = new TravelDay();
				tDay.start();
				break;
			case 3 : // 메인메뉴로 돌아가기
				HomeScreen.start();
				break;
		}

	} // Method : start

	// =================================== 테마 선택 시 처리해주는 메소드
	
	public int travelTheme(String select, String day) throws Exception {

		switch(select) {
		case "1" :
		case "2" :
		case "3" :
		case "4" :
		case "5" :
			Cls.clearScreen();
			return 1;
//		case "0" :
//			
//			if (Resource.getMemberTheme().size() == 0) {
//
//				System.out.println("\n\t\t\t\t선택하신 일정이 없습니다.");
//				System.out.println("\t\t\t\t일정을 선택해주세요.");
//				System.out.println("\t\t\t\tENTER키를 눌러 진행합니다.");
//				
//				String next = scan.nextLine();
//				
//				if (next.equals("")) {
//					NamePot.clearScreen();
//					start(day);
//				}
//
//			} else {
//				System.out.printf("\n\t\t\t\t선택하신 일정을 %s일차에 저장합니다.", day);
//				System.out.println("\n\t\t\t\t날짜선택 화면으로 이동합니다.");
//				System.out.println("\t\t\t\tENTER키를 눌러 진행해주세요.");
//				String next = scan.nextLine();
//				if (next.equals("")) {
//					NamePot.clearScreen();
//					// ===========================================================================================
//					// ===========================================================================================
//					// save.add(6, memberTheme.get(Integer.parseInt(dateSelect)-1)); //일차별로 선택한 테마들의
//					// 모음
//					// saveInfo(); //데이터 저장!!
//					// 만약 3박4일 여행의 경우 interval은 '4'이다
//					// TODO : 일차별로 저장할 때 마다 카운트 변수를 1씩 증가 -> interval변수와 동일해지면 거기서 STOP.
//					// TODO : 만약 모든 일차를 채웠을 경우 모든 일정을 저장했다는 문구를 띄우고 메인메뉴로 보낸다.
//					// ===========================================================================================
//					// ===========================================================================================
//					Resource.getMemberTheme().clear();
//					TravelDay tDay = new TravelDay();
//					tDay.start();
//				}
//			}
//			
//			return 0;
		case "#" :
			Cls.clearScreen();
			return 2;
		case "*" :
			Cls.clearScreen();
			return 3;
		default : 
			System.out.println("\t\t\t\t<ERROR>\n\t\t\t\t올바르지 않은 입력입니다.");
			return 0;	
		}
		
	} // Method : travelTheme

	// =================================== 테마 선택 화면 출력

	public String printTravelTheme(String day) {

//		int dayNum = Integer.parseInt(day);
//		start.add(Calendar.DATE, dayNum);
		
		DetailedSchedule dSkd = new DetailedSchedule();
		
		if (dSkd.flag == true) {
			
			System.out.println("\t\t\t\t[일정 수정]\n");
			System.out.println("\t\t\t\t--------------------------------");
			System.out.println("\t\t\t\t[1] 카페");
			System.out.println("\t\t\t\t[2] 맛집");
			System.out.println("\t\t\t\t[3] 숙박");
			System.out.println("\t\t\t\t[4] 명소");
			System.out.println("\t\t\t\t[5] 액티비티");
			System.out.println("\t\t\t\t--------------------------------");
			System.out.println("\t\t\t\t[ ] 안의 번호를 입력하세요");
			System.out.print("\t\t\t\t선택 : ");
			
		} else {
			
			Calendar start = Calendar.getInstance();
			
			String[] tempStart = Resource.getStartDate().split("-");
			int startYear = Integer.parseInt(tempStart[0]);
			int startMonth = Integer.parseInt(tempStart[1]);
			int startDate = Integer.parseInt(tempStart[2]);
			
			start.set(startYear, startMonth-1, startDate + Integer.parseInt(dateSelect) - 1);
			
			System.out.println("\t\t\t\t[새로운 일정 작성]\n");
			System.out.printf("\t\t\t\t4단계)\n\t\t\t\t테마선택 - %s일차(%tF)\n\n", dateSelect, start);
			
			System.out.println("\t\t\t\t[1] 카페");
			System.out.println("\t\t\t\t[2] 맛집");
			System.out.println("\t\t\t\t[3] 숙박");
			System.out.println("\t\t\t\t[4] 명소");
			System.out.println("\t\t\t\t[5] 액티비티");
			
			System.out.println("\n\t\t\t\t[0] 선택한 일정 저장하기");
			System.out.println("\t\t\t\t[#] 일자선택으로 돌아가기");
			System.out.println("\t\t\t\t[*] 메인메뉴로 돌아가기");
			System.out.println("\t\t\t\t--------------------------------");
			System.out.println("\t\t\t\t[ ] 안의 번호를 입력하세요");
			System.out.print("\t\t\t\t선택 : ");
			
		}
		String select = scan.nextLine();

		return select;

	} // Method : printTravelTheme
	
	private void calcDate(String day) {
		
		
		String[] tempStart = Resource.getStartDate().split("-");
		int startYear = Integer.parseInt(tempStart[0]);
		int startMonth = Integer.parseInt(tempStart[1]);
		int startDate = Integer.parseInt(tempStart[2]);
		
		start.set(startYear, startMonth-1, startDate + Integer.parseInt(day) - 1);
	} // Method : calcDate
}
