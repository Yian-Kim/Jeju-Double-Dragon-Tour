package com.project.jejutvl.newschedule;

import java.util.Calendar;
import java.util.Scanner;

import com.project.jejutvl.homescreen.HomeScreen;
import com.project.jejutvl.homescreen.Cls;
import com.project.jejutvl.homescreen.Resource;


public class TravelDay {

	Scanner scan = new Scanner(System.in);
	Calendar start;
	Calendar end;
	
	// 3단계. 날짜 선택
	public void start() throws Exception {
		
		Calendar start = travelDay();
		String select = printTravelDay(start);

		if(select.equals("0")) {
				Cls.clearScreen();
				HomeScreen.start();
		} else if(Integer.parseInt(select) > 0 
				&& Integer.parseInt(select) <= Resource.getInterval() + 1) {
			
			Cls.clearScreen();
			TravelTheme theme = new TravelTheme();
			theme.start(select);
		} else {
			System.out.println("\n\t\t\t\t<ERROR>\n\t\t\t\t올바르지 않은 입력입니다.");	
		}
	} // Method : start
	

	public String printTravelDay(Calendar start) {
		
		System.out.println("\t\t\t\t[새로운 일정 작성]\n");
		System.out.printf("\t\t\t\t3단계)\n\t\t\t\t날짜 선택\n\t\t\t\t%s 회원님의 여행일정\n\t\t\t\t%tF ~ %tF\n\n", Resource.getMemberName(), start, end);

		for (int i=1; i<=Resource.getInterval(); i++) {
			System.out.printf("\t\t\t\t[%d] %d일차(%tF)\n", i, i, start);
			start.add(Calendar.DATE, + 1);
		}

		// 어차피 메인메뉴로 돌아가서 일정표 보기 메뉴 선택하면 되니까 없애는게 좋을 듯 합니다.
		// System.out.println("\n\t\t\t\t[0] 일정표 보기");
		System.out.println("\n\t\t\t\t[0] 메인메뉴로 돌아가기");
		System.out.println("\t\t\t\t--------------------------------");
		System.out.println("\t\t\t\t[ ] 안의 번호를 입력하세요");
		System.out.print("\t\t\t\t선택 : ");
		String select = scan.nextLine();
		
		return select;
	} // Method : printTravelDay

	
	public Calendar travelDay() {
		start = Calendar.getInstance();
		end = Calendar.getInstance();

		String[] tempStart = Resource.getStartDate().split("-");
		int startYear = Integer.parseInt(tempStart[0]);
		int startMonth = Integer.parseInt(tempStart[1]);
		int startDate = Integer.parseInt(tempStart[2]);

		String[] tempEnd = Resource.getEndDate().split("-");
		int endYear = Integer.parseInt(tempEnd[0]);
		int endMonth = Integer.parseInt(tempEnd[1]);
		int endDate = Integer.parseInt(tempEnd[2]);

		start.set(startYear, startMonth - 1, startDate);
		end.set(endYear, endMonth - 1, endDate);

		// 출발 날짜와 돌아오는 날짜 사이의 일수 계산하기
		long startTick = start.getTime().getTime();
		long endTick = end.getTime().getTime();
		Resource.setInterval(((endTick - startTick) / 1000 / 60 / 60 / 24) + 1);
	
		return start;
	} // Method : travelDay
}
