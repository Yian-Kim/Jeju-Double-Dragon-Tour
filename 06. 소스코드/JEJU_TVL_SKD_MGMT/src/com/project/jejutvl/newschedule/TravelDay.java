package com.project.jejutvl.newschedule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Scanner;

import com.project.jejutvl.homescreen.HomeScreen;
import com.project.jejutvl.homescreen.Cls;
import com.project.jejutvl.homescreen.Resource;

// 3단계) 날짜 선택
public class TravelDay {

	private static Scanner scanner;
	
	static {
		scanner = new Scanner(System.in);
	}
	
//--------------------------------------------------------------------------------------------------------	
	
	public void start() throws Exception {
		
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
	
		//TODO : 출발날짜, 도착날짜, 두 날짜 사이의 간격 값 설정 (setInterval)
		travelDay(start, end);
		
		//TODO : 날짜 선택
		String day = printTravelDay(start, end);
		Resource.setDateSelect(day);
		
		if(day.equals("0")) {
			
			//TODO : 메인 메뉴
			Cls.clearScreen();
			HomeScreen.start();
				
		} else if(Integer.parseInt(day) > 0 
				&& Integer.parseInt(day) <= Resource.getInterval() + 1) {
			
			// TODO : 선택한 날짜에서 테마 선택
			Cls.clearScreen();
			TravelTheme theme = new TravelTheme();
			theme.start(day);
			
		} else {
			System.out.println("\n\t\t\t\t<ERROR>\n\t\t\t\t올바르지 않은 입력입니다.");	
		}
		
	} // Method : start
	
//--------------------------------------------------------------------------------------------------------

	public void travelDay(Calendar start, Calendar end) {

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

		// TODO : 출발 날짜와 돌아오는 날짜 사이의 일수 계산하기
		long startTick = start.getTime().getTime();
		long endTick = end.getTime().getTime();
		Resource.setInterval(((endTick - startTick) / 1000 / 60 / 60 / 24) + 1);
		
	} // Method : travelDay	
	
//--------------------------------------------------------------------------------------------------------
	
	public String printTravelDay(Calendar start, Calendar end) {
				
		try {
			BufferedReader source_reader = new BufferedReader(new FileReader(Resource.MEMBER_PATH));
			String line = null;
			while ((line = source_reader.readLine()) != null) {
				if (line.indexOf(Resource.getMemberID()) > -1) { // 해당ID가 포함된 라인이 발견되면
					Resource.setMemberTXT(line); // 해당 라인을 txt에 추가
				}
			}
			source_reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		// 회원의 정보가 저장되어있는 리스트
		String[] memberData = Resource.getMemberTXT().split("■");
		String memberName = memberData[4];
		
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
		
		System.out.println("\t\t\t\t[새로운 일정 작성]\n");
		System.out.printf("\t\t\t\t3단계)\n\t\t\t\t날짜 선택\n\t\t\t\t%s 회원님의 여행일정\n\t\t\t\t%tF ~ %tF\n\n"
				, memberName, start, end);

		for (int i=1; i<=Resource.getInterval(); i++) {
			System.out.printf("\t\t\t\t[%d] %d일차(%tF)\n", i, i, start);
			start.add(Calendar.DATE, + 1);
		}

		System.out.println("\n\t\t\t\t[0] 메인메뉴로 돌아가기");
		System.out.println("\t\t\t\t--------------------------------");
		System.out.println("\t\t\t\t[ ] 안의 번호를 입력하세요");
		System.out.print("\t\t\t\t선택 : ");
		
		String day = scanner.nextLine();
		return day;
		
	} // Method : printTravelDay
	
} // Class : TravelDay



