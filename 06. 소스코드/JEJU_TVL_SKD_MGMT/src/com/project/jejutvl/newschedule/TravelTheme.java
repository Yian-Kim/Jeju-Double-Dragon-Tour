package com.project.jejutvl.newschedule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import com.project.jejutvl.homescreen.Cls;
import com.project.jejutvl.homescreen.HomeScreen;
import com.project.jejutvl.homescreen.Resource;
import com.project.jejutvl.travelplan.DetailedSchedule;
import com.project.jejutvl.travelplan.Theme;
import com.project.jejutvl.travelplan.transportExpense;

//import planList.Theme;

//4단계. 테마선택
public class TravelTheme {

	private static Scanner scanner;
	
	static {
		scanner = new Scanner(System.in);
	}

//----------------------------------------------------------------------------------------------------------	

	public void start(String day) throws Exception {
		
		// TODO : 테마선택 화면 출력
		String select = printTravelTheme(day);
		
		// TODO : 테마 선택 시 처리
		int menu = travelTheme(select, day);
		
		switch(menu) {
		
			case 1 : // 테마별 추천리스트 출력
				RecommendList rList = new RecommendList();
				rList.start(select, day);
				break;
				
				
			case 2 : // 일자선택으로 돌아가기
				TravelDay tDay = new TravelDay();
				tDay.start();
				break;
				
				
			case 3 : // 메인메뉴로 돌아가기
				HomeScreen.start();
				break;
				
				
			case 4 : //일정 저장하기
				HomeScreen.start();
				break;
				
				
			case 0 :
				start(day);
				break;
				
		}

	} // Method : start

//----------------------------------------------------------------------------------------------------------	
	
	// TODO : 테마 선택 시 처리해주는 메소드
	public int travelTheme(String select, String day) throws Exception {

		switch(select) {
		
		case "1" :	case "2" :	case "3" :	case "4" :case "5" :
			
			Cls.clearScreen();
			return 1;
		
			
		case "#" :
			Cls.clearScreen();
			return 2;
		
			
		case "*" :
			Cls.clearScreen();
			return 3;
			
			
		case "0" :
		
		if (Resource.getMemberTheme().size() == 0) {

			System.out.println("\n\t\t\t\t선택하신 일정이 없습니다.");
			System.out.println("\t\t\t\t일정을 선택해주세요.");
			System.out.println("\t\t\t\tENTER키를 눌러 진행합니다.");
			
			String next = scanner.nextLine();
			
			if (next.equals("")) {
				
				Cls.clearScreen();
				start(day);
				
			}

		} 
		
		// TODO : 저장 시작
		else {
			
			System.out.println("\t\t\t\t--------------------------------");
			System.out.printf("\t\t\t\t%s일차에서 선택한 항목은 아래와 같습니다.\n", day);
			
			String saveItem = "";
			String[] txt = Resource.getMemberTheme().get(Integer.parseInt(day)-1).split("■");
			for (String keyword : txt) {
				Theme theme = new Theme();
				String[] themeTXT = theme.pathSetter(keyword).split("■");
				saveItem += "\t\t\t\t[" + themeTXT[1] + "]\r\n";
			}
			System.out.print(saveItem);

			System.out.println("\t\t\t\t--------------------------------");
			System.out.println("\t\t\t\t저장을 원하시면 ENTER키를 누르세요.");
			String next = scanner.nextLine();
			System.out.println("\t\t\t\tLOADING.....");
			Thread.sleep(700);
			System.out.println("\t\t\t\t--------------------------------");
	    	System.out.println("\t\t\t\t저장이 완료되었습니다.");
	    	System.out.println("\t\t\t\tENTER키를 누르시면 날짜선택 화면으로 이동합니다.");
			next = scanner.nextLine();
			if (next.equals("")) {
		//===========================================================================================	
				
				//interval = 출발 날짜와 돌아오는 날짜 간의 차이 -> 만약 3박4일 여행의 경우 interval은 '4'입니다
				//dateCount = 일차별로 저장할 때 마다 dateCount를 1씩 증가시킵니다 -> interval변수와 동일해지면 거기서 STOP
				
				Resource.save.add(3, Resource.getMemberTheme().get(Integer.parseInt(day)-1)); //일차별로 선택한 테마들의 모음
				saveInfo(day); //데이터 저장!!
				Resource.dateCount++;
				
				// TODO : 저장 완료 후 메인 메뉴로 복귀
				if (Resource.dateCount == Resource.interval) { //모든 저장을 완료하면 메인메뉴로 복귀시킵니다.
					System.out.println("\t\t\t\t--------------------------------");
					System.out.println("\t\t\t\t모든 일정을 저장 완료하였습니다.\n\t\t\t\t메인메뉴로 돌아갑니다.");
					System.out.println("\t\t\t\t메인메뉴에서 일정표 확인 메뉴를 통해 확인하세요.");
					System.out.println("\t\t\t\tENTER를 누르시면 진행합니다.");
					String yes = scanner.nextLine();
					
					if (yes.equals("")) {
						Cls.clearScreen();
						System.out.println("\t\t\t\t\tLOADING.....");
						Thread.sleep(700);
						Cls.clearScreen();
						HomeScreen.start();
					}
				} 
				
				// TODO : 하루치 데이터 저장 후 날짜 선택 화면으로 복귀
				else {
					Cls.clearScreen();
					Resource.getMemberTheme().clear();
					System.out.println("\t\t\t\t\tLOADING.....");
					Thread.sleep(700);
					Cls.clearScreen();
					TravelDay tDay = new TravelDay();
					tDay.start();
					
				}
				
			}  
			
		}
		//===========================================================================================	
			return 4;
		
		default : 
			System.out.println("\t\t\t\t<ERROR>\n\t\t\t\t올바르지 않은 입력입니다.\n\t\t\t\t다시 입력 해 주세요.");
			Cls.clearScreen();
			return 0;	
		}
		
	} // Method : travelTheme

//----------------------------------------------------------------------------------------------------------	
	
	// TODO : 테마 선택 화면 출력
	public String printTravelTheme(String day) {

		//TODO : 세부일정표를 통해서 추가하러 왔을 경우
		if (DetailedSchedule.flag == true) {
			
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
			
			start.set(startYear, startMonth-1, startDate + Integer.parseInt(day) - 1);

			
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
			System.out.printf("\t\t\t\t4단계)\n\t\t\t\t테마선택 - %s일차(%tF)\n\n", day, start);
			
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
		
		String select = scanner.nextLine();

		return select;

	} // Method : printTravelTheme
	
//----------------------------------------------------------------------------------------------------------------

	/*
	 * TODO
	 * save 리스트에 넣어놓은 정보를 dat파일에 저장하는 메소드 save 리스트에 저장한 데이터 목록 0. ID 1. 가는 교통편 2.
	 * 돌아오는 교통편 3. 테마 (memberTheme hashMap을 한꺼번에 불러옵니다)
	 */

	private static void saveInfo(String dateSelect) {

		Calendar start = Calendar.getInstance();

		String[] temp = Resource.getStartDate().split("-");
		int year = Integer.parseInt(temp[0]);
		int month = Integer.parseInt(temp[1]);
		int date = Integer.parseInt(temp[2]);

		start.set(year, month - 1, date + Integer.parseInt(dateSelect) - 1);

		// ===============================================================================

		Calendar saveStart = Calendar.getInstance();
		Calendar saveEnd = Calendar.getInstance();

		String[] tempStart = Resource.getStartDate().split("-");
		int startYear = Integer.parseInt(tempStart[0]);
		int startMonth = Integer.parseInt(tempStart[1]);
		int startDate = Integer.parseInt(tempStart[2]);

		String[] tempEnd = Resource.getEndDate().split("-");
		int endYear = Integer.parseInt(tempEnd[0]);
		int endMonth = Integer.parseInt(tempEnd[1]);
		int endDate = Integer.parseInt(tempEnd[2]);

		saveStart.set(startYear, startMonth - 1, startDate);
		saveEnd.set(endYear, endMonth - 1, endDate);

		// ===============================================================================

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		// ===============================================================================
	
//====================================================================================================================
				
		//price.dat에 저장할 데이터
		try {
			
			BufferedWriter expenseWriter = new BufferedWriter(new FileWriter(Resource.PRICE_PATH, true));
			
			
			//선택한 일수(1일차)
			String item1 = Resource.getDateSelect();
			
			
			//선택한 일수의 날짜(2019-03-13)
			String item2 = format.format(start.getTime());
			
			
			//회원의 이름(엄현철)
			try {
				BufferedReader reader = new BufferedReader(new FileReader(Resource.MEMBER_PATH));
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf(Resource.getMemberID()) > -1) { // 해당ID가 포함된 라인이 발견되면
						Resource.setMemberTXT(line); // 해당 라인을 txt에 추가
					}
				}
				reader.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}	
			
			String[] memberData = Resource.getMemberTXT().split("■");
			String item3 = memberData[4];
			

			// 회원의 정보가 저장되어있는 리스트(가격)
			//교통편의 가격부터 더해주기
			int themeTotal = 0;
			
			//첫째 날
			if (start.compareTo(saveStart) == 0) {
				
				transportExpense TE = new transportExpense();
				int expense = TE.pathSetter(Resource.save.get(1));
				
				themeTotal = expense;
				
			} 
			
			//마지막 날
			else if (start.compareTo(saveEnd) == 0) {
				
				transportExpense TE = new transportExpense();
				int expense = TE.pathSetter(Resource.save.get(2));
				
				themeTotal = expense;
				
			} 
			
			//그 외
			else {
				
				themeTotal = 0;
				
			}
			
			//테마 각각의 가격 더해주기
			String[] themeKey = Resource.save.get(3).split("■");
			
			for (String themeKeyword : themeKey) {
				
				//키워드가 들어있는 테마 리스트를 찾고 키워드에 해당하는 항목을 불러온다
				Theme theme = new Theme();
				String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
				int price = Integer.parseInt(themeTXT[4]);
				
				themeTotal += price;
				
			}

			String item4 = themeTotal + "";
			
			String addTXT = item1 + "일차■" + item2 + "■" + item3 + "■" + item4 + "\r\n";
			
			expenseWriter.write(addTXT);
			
			expenseWriter.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}

//====================================================================================================================
		
		//일정더미.dat 파일에 저장
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(Resource.TRAVEL_PATH, true));

			if (start.compareTo(saveStart) == 0) {

				String tempDate = format.format(start.getTime());
				// ID■날짜■테마■교통편
				writer.write(String.format("%s■%s■%s■%s\n"
						, Resource.save.get(0), tempDate, Resource.save.get(3), Resource.save.get(1)));

			} else if (start.compareTo(saveEnd) == 0) {

				String tempDate = format.format(start.getTime());
				writer.write(String.format("%s■%s■%s■%s\n"
						, Resource.save.get(0), tempDate, Resource.save.get(3), Resource.save.get(2)));

			} else {

				String tempDate = format.format(start.getTime());
				writer.write(String.format("%s■%s■%s\n"
						, Resource.save.get(0), tempDate, Resource.save.get(3)));

			}

			writer.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}// saveInfo 메소드
		
} // Class : TravelTheme


