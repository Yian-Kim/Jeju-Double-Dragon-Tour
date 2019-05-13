package com.project.jejutvl.travelplan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.jejutvl.homescreen.*;
//import com.project.jejutvl.newschedule.TravelTheme;

//총 일정표 클래스
public class TotalSchedule {
	
	private static Scanner scanner;
	
	static {
		scanner = new Scanner(System.in);
	}

//---------------------------------------------------------------------
	
	public void start() throws Exception {
		
		String select = printTotalSchedule();
	
		switch(select) {
		
			case "0" : // 세부 일정표 보기
				System.out.println("\n\t\t\t\t세부 일정표를 조회합니다.\n\t\t\t\t계속하시려면 ENTER키를 눌러주세요.");
				String next = scanner.nextLine();
				if (next.equals("")) {
					Cls.clearScreen();
					System.out.println("\t\t\t\tLOADING.....");
					Thread.sleep(700);
					Cls.clearScreen();
					DetailedSchedule detailedSKD = new DetailedSchedule();
					detailedSKD.start();
					break;
				}
				
	//===================================================================================================
				
			case "#" : //일정표 메뉴로 돌아가기
				System.out.println("\n\t\t\t\t일정표 메뉴로 돌아갑니다.\n\t\t\t\t계속하시려면 ENTER키를 눌러주세요.");
				next = scanner.nextLine();
				if (next.equals("")) {
					Cls.clearScreen();
					System.out.println("\t\t\t\tLOADING.....");
					Thread.sleep(700);
					Cls.clearScreen();
					TravelPlan tPlan = new TravelPlan();
					tPlan.start();
					break;
				}
				
	//===================================================================================================
				
			case "*" : // 메인메뉴로 돌아가기
				System.out.println("\n\t\t\t\t메인 메뉴로 돌아갑니다.\n\t\t\t\t계속하시려면 ENTER키를 눌러주세요.");
				next = scanner.nextLine();
				if (next.equals("")) {
					Cls.clearScreen();
					System.out.println("\t\t\t\tLOADING.....");
					Thread.sleep(700);
					Cls.clearScreen();
					HomeScreen.start();
					break;
				}
				
		}//switch문의 끝
		
	}//start 메소드

//------------------------------------------------------------------------------------------------------
	
	public String printTotalSchedule() {
		
    	//일정더미.dat 파일에서 데이터 읽어오기
    	ArrayList<String> planTXT = new ArrayList<String>();
    	
    	try {
			BufferedReader source_reader = new BufferedReader(new FileReader(Resource.TRAVEL_PATH));
			String line = null;
			while ((line = source_reader.readLine()) != null) {
				if (line.indexOf(Resource.getMemberID()) > -1) { //해당ID가 포함된 라인이 발견되면
					planTXT.add(line); //해당 라인을 txt에 추가
				}
			}
			source_reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}	
		
		//1. 회원의 정보가 저장되어있는 리스트
		String[] memberData = Resource.getMemberTXT().split("■");
		String memberName = memberData[4];
		
	//=================================================================================	
		
		//2. 회원의 여행 출발 날짜
		String[] memberPlan = planTXT.get(0).split("■");
		Calendar startDate = Calendar.getInstance();
		startDate.set(Integer.parseInt(memberPlan[1].substring(0, 4)), 
				Integer.parseInt(memberPlan[1].substring(4, 6)) - 1, 
				Integer.parseInt(memberPlan[1].substring(6)));
		
	//=================================================================================
		
		//3. 회원의 여행 돌아오는 날짜
		memberPlan = planTXT.get(planTXT.size() - 1).split("■");
		Calendar endDate = Calendar.getInstance();
		endDate.set(Integer.parseInt(memberPlan[1].substring(0, 4)), 
				Integer.parseInt(memberPlan[1].substring(4, 6)) - 1, 
				Integer.parseInt(memberPlan[1].substring(6)));
	
	//=================================================================================
		
		//4. 가는 교통편
		String[] memberTransport = planTXT.get(0).split("■");
		String keyword = memberTransport[memberTransport.length - 1]; //우선 식별자를 추출한 다음
		String departureInfo = "";
		
		String memberDeparture = "";
		
		//항공편의 경우
		if (keyword.substring(0, 2).equals("FG")) {

			try {
				
				BufferedReader source_reader = 
						new BufferedReader(new FileReader(Resource.FLIGHT_DEPARTURE_PATH));
				
				String line = null;
				while ((line = source_reader.readLine()) != null) {
					if (line.indexOf(keyword) > -1) {
						departureInfo += line; //식별자를 통해 해당 교통편의 정보를 읽어온다
					}
				}
				source_reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			//마지막으로 일정표 상에 출력할 형식을 만든다
			String[] departureTemp = departureInfo.split("■");
			memberDeparture = String.format("\t\t\t\t001\t항공\t%s->제주(%s, %s)"
					, departureTemp[1], departureTemp[0], departureTemp[3]);
			
		} 
		
		//배편의 경우
		else if (keyword.substring(0, 2).equals("SG")) {
			
			try {
				
				BufferedReader source_reader = 
						new BufferedReader(new FileReader(Resource.SHIP_DEPARTURE_PATH));
				
				String line = null;
				while ((line = source_reader.readLine()) != null) {
					if (line.indexOf(keyword) > -1) {
						departureInfo += line; //식별자를 통해 해당 교통편의 정보를 읽어온다
					}
				}
				source_reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			//마지막으로 일정표 상에 출력할 형식을 만든다
			String[] departureTemp = departureInfo.split("■");
			memberDeparture = String.format("\t\t\t\t001\t항공\t%s->제주(%s, %s)"
					, departureTemp[1], departureTemp[0], departureTemp[3]);
			
		}
		
	//=================================================================================
		
		//5. 돌아오는 교통편
		memberTransport = planTXT.get(planTXT.size()-1).split("■");
		keyword = memberTransport[memberTransport.length - 1]; //식별자
		String returnInfo = "";
		
		String memberReturn = "";
		
		//항공편의 경우
		if (keyword.substring(0, 2).equals("FC")) {
			
			try {
				
				BufferedReader source_reader = 
						new BufferedReader(new FileReader(Resource.FLIGHT_RETURN_PATH));
				
				String line = null;
				while ((line = source_reader.readLine()) != null) {
					if (line.indexOf(keyword) > -1) {
						returnInfo += line; //식별자를 통해 해당 교통편의 정보를 읽어온다
					}
				}
				source_reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			//일정표 상에 출력할 형식
			String[] returnTemp = returnInfo.split("■");
			memberReturn = String.format("항공\t제주->%s(%s, %s)"
					, returnTemp[1], returnTemp[0], returnTemp[3]);
			
		} 
		
		//배편의 경우
		else if (keyword.substring(0, 2).equals("SC")) {
			
			
			try {
				
				BufferedReader source_reader = 
						new BufferedReader(new FileReader(Resource.SHIP_RETURN_PATH));
				
				String line = null;
				while ((line = source_reader.readLine()) != null) {
					if (line.indexOf(keyword) > -1) {
						returnInfo += line;
					}
				}
				source_reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			//일정표 상에 출력할 형식
			String[] returnTemp = returnInfo.split("■");
			memberReturn = String.format("해운\t제주->%s(%s, %s)"
					, returnTemp[1], returnTemp[0], returnTemp[3]);
			
		}

		
		//총 일정표 출력
		System.out.println("\t\t--------------------------------------------------------------------------");
		System.out.println("\t\t==========================================================================\n");
		System.out.print("\t\t████████╗ ██████╗ ████████╗ █████╗ ██╗                           \r\n" + 
				"\t\t╚══██╔══╝██╔═══██╗╚══██╔══╝██╔══██╗██║                           \r\n" + 
				"\t\t   ██║   ██║   ██║   ██║   ███████║██║                           \r\n" + 
				"\t\t   ██║   ██║   ██║   ██║   ██╔══██║██║                           \r\n" + 
				"\t\t   ██║   ╚██████╔╝   ██║   ██║  ██║███████╗                      \r\n" + 
				"\t\t   ╚═╝    ╚═════╝    ╚═╝   ╚═╝  ╚═╝╚══════╝                      \r\n" + 
				"                                                                 \r\n" + 
				"\t\t███████╗ ██████╗██╗  ██╗███████╗██████╗ ██╗   ██╗██╗     ███████╗\r\n" + 
				"\t\t██╔════╝██╔════╝██║  ██║██╔════╝██╔══██╗██║   ██║██║     ██╔════╝\r\n" + 
				"\t\t███████╗██║     ███████║█████╗  ██║  ██║██║   ██║██║     █████╗  \r\n" + 
				"\t\t╚════██║██║     ██╔══██║██╔══╝  ██║  ██║██║   ██║██║     ██╔══╝  \r\n" + 
				"\t\t███████║╚██████╗██║  ██║███████╗██████╔╝╚██████╔╝███████╗███████╗\r\n" + 
				"\t\t╚══════╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═════╝  ╚═════╝ ╚══════╝╚══════╝\n");
		System.out.println("\t\t--------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t[총 일정표]\n");
		System.out.printf("\t\t\t\t\t'%s' 님의 여행일정\n", memberName);
		System.out.printf("\t\t\t\t\t %tF ~ %tF\n\n", startDate, endDate);
		
		//여행 일수 만큼 반복
		for (int i=0; i<planTXT.size(); i++) {
			
			int returnNumber = 0;
			
			System.out.println("\t\t\t\t=========================================");
			System.out.printf("\t\t\t\t(%d일차) %tF\n", i + 1, startDate);
			System.out.println("\t\t\t\t-----------------------------------------");
			System.out.println("\t\t\t\t번호\t항목\t주요일정");
			System.out.println("\t\t\t\t-----------------------------------------");
			
			startDate.add(Calendar.DATE, 1);
			
			//테마 출력
			String[] memberTheme = planTXT.get(i).split("■"); //1일차에 저장한 테마부터 차례로 불러옵니다

			//첫째 날
			if (i == 0) {
				
				//1일차의 경우 교통편 추가
				System.out.println(memberDeparture);
				
				//테마 식별자는 2번지부터 시작 -> 마지막 교통편 식별자는 제외
				for (int j=2; j<memberTheme.length - 1; j++) {
					
					String themeKeyword = memberTheme[j]; //테마 식별자
					
					//키워드가 들어있는 테마 리스트를 찾고 키워드에 해당하는 항목을 불러온다
					Theme theme = new Theme();
					String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
					String category = themeTXT[themeTXT.length-1];
					String detail = themeTXT[1];
					
					System.out.printf("\t\t\t\t%03d\t%s\t%s\n", j, category, detail);
					
				}
					
			} 
			
			//마지막 날짜
			else if (i == planTXT.size() - 1) {
				
				for (int j=2; j<memberTheme.length - 1; j++) {
					
					String themeKeyword = memberTheme[j];
					
					Theme theme = new Theme();
					String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
					String category = themeTXT[themeTXT.length-1];
					String detail = themeTXT[1];
					
					System.out.printf("\t\t\t\t%03d\t%s\t%s\n", j-1, category, detail);
					
					returnNumber++;
					
				}
				
			} 
			
			//그 외
			else {
				
				//교통편이 저장되어 있지 않은 날
				for (int j=1; j<memberTheme.length - 1; j++) {
					
					String themeKeyword = memberTheme[j+1];
					
					Theme theme = new Theme();
					String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
					String category = themeTXT[themeTXT.length-1];
					String detail = themeTXT[1];
					
					System.out.printf("\t\t\t\t%03d\t%s\t%s\n", j, category, detail);
					
				}
				
			}
			

			//마지막날의 경우에도 교통편 추가
			if (i == planTXT.size() - 1) {
				System.out.println(String.format("\t\t\t\t%03d\t%s", returnNumber + 1, memberReturn));
			}
			
		}//여행 일수 for문
		
	//==============================================================================================================
		
		System.out.println("\t\t\t\t=========================================");
		System.out.println("\t\t\t\t[0] 세부 일정표 보기");
		System.out.println("\t\t\t\t[#] 일정표 메뉴로 돌아가기");
		System.out.println("\t\t\t\t[*] 메인메뉴로 돌아가기");
		System.out.println("\t\t\t\t--------------------------------------");
		System.out.println("\t\t\t\t[ ] 안의 번호를 입력하세요");
		System.out.print("\t\t\t\t선택 : ");
		
		String select = scanner.nextLine();
		return select;
		
	}//printTotalSchedule 메소드
	
}//TotalSchedule 클래스




