package com.project.jejutvl.travelplan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.jejutvl.homescreen.Cls;
import com.project.jejutvl.homescreen.Resource;
import com.project.jejutvl.newschedule.TravelTheme;


//세부 일정표 클래스
public class DetailedSchedule {
	
	//추가 혹은 삭제 시 어떤 날짜에서 작업을 할지 정하기 위한 값
	private static int dateIndex = 0;
	
	//추가 시에 테마를 선택하고 난 후, 다시 세부일정 화면으로 돌아오게 만드는 분기를 만드는 값
	public static boolean flag = false;
	public static String addItem = "";
	
	private static Scanner scanner;

	static {
		scanner = new Scanner(System.in);
	}

//-----------------------------------------------------------------------------
	
	public void start() throws Exception {
		
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
		
		// 세부 일정표
		String select = printDetailedSchedule();
		
		switch(select) {
		
		 	//새 테마 추가하기
			case "1" :
				System.out.println("\t\t----------------------------------");
				System.out.println("\t\tENTER키를 눌러 테마선택 화면으로 이동합니다.");
				String next = scanner.nextLine();
				if (next.equals("")) {
					Cls.clearScreen();
					System.out.println("\t\t\t\t\tLOADING.....");
					Thread.sleep(700);
					Cls.clearScreen();
					String dateSelect = String.format("%d", dateIndex);
					flag = true;
					TravelTheme tTheme = new TravelTheme();
					tTheme.start(dateSelect); //새로운 테마 선택하러 테마선택 메소드로 이동
				}
				
				
				//추가작업 시작
				
				//해당 날짜가 들어있는 라인 호출
				String[] addTemp = planTXT.get(dateIndex).split("■");
				
				//BufferedReader로 파일을 불러와서 BufferedWriter로 덮어쓴다
				String addTXT = "";
				String indexKey = addTemp[0] + "■" + addTemp[1] + "■";
				
				try {
					
					//1. 삭제하고자 하는 position의 바로 윗줄까지 이동하며 addTXT에 저장
					BufferedReader reader = new BufferedReader(new FileReader(Resource.TRAVEL_PATH));
					String line = null;
					while ((line = reader.readLine()) != null) {
						
						//2. 삭제해야 할 항목이 있는 라인에 도달하면 새로운 테마코드를 문자열에 추가해준다
						if (line.indexOf(indexKey) > -1) {
							String newTXT = planTXT.get(dateIndex).replace(indexKey, indexKey + addItem + "■");
							addTXT += newTXT + "\r\n";
						} else {
							addTXT += line + "\r\n";
						}
						
					}
					reader.close();

					//3. 덮어쓰기
					BufferedWriter writer = new BufferedWriter(new FileWriter(Resource.TRAVEL_PATH));
					writer.write(addTXT);
					writer.close();
					
				} catch (Exception e) {
					System.out.println(e.toString());
				}
				
				
				//추가작업 완료시
				System.out.println("\t\t\t\t----------------------------------");
				System.out.println("\t\t\t\tENTER키를 누르시면 일정표로 돌아갑니다.");
				String proceed = scanner.nextLine();
				
				if(proceed.equals("")) {
					Cls.clearScreen();
					System.out.println("\t\t\t\t\tLOADING.....");
					Thread.sleep(700);
					Cls.clearScreen();
					start();
					break;
				}
				
	//======================================================================================================		
				
			//테마 삭제하기	
			case "2" :
				System.out.println("\t\t----------------------------------");
				System.out.println("\t\t삭제하실 항목의 번호를 입력하세요.");
				System.out.println("\t\t[ ] 안의 번호를 입력하세요.");
				System.out.print("\t\t선택 : ");
				
				//Step 01. 삭제할 항목의 번호 입력
				int themeChoose = scanner.nextInt();
				scanner.skip("\r\n");
				if (dateIndex != 0) {
					themeChoose += 1;
				}
				
			//======================================================================
				
				//Step 02. 해당 날짜가 들어있는 라인 호출
				
				String[] temp = planTXT.get(dateIndex).split("■");
				
			//======================================================================
				
				//Step 03. 삭제할 테마를 지정
				String deleteItem = temp[themeChoose];
				
				String themeKeyword = deleteItem;
				
				Theme theme = new Theme();
				String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
				deleteItem = themeTXT[1];
				
				System.out.println("\t\t----------------------------------");
				System.out.printf("\t\t'%s'를 삭제하시겠습니까?\n", deleteItem);
				System.out.println("\t\tENTER키를 누르시면 삭제를 진행합니다.");
				String deleteProceed = scanner.nextLine();
				System.out.println("\t\tLOADING.....\n");
				
			//======================================================================
				
				//Step 04. 삭제 진행
				if (deleteProceed.equals("")) {
					
					//BufferedReader로 파일을 불러와서 BufferedWriter로 덮어쓴다
					String dummyTXT = "";
					String searchkey = temp[0] + "■" + temp[1] + "■";
					
					try {
						
						//1. 삭제하고자 하는 position의 바로 윗줄까지 이동하며 dummyTXT에 저장
						BufferedReader reader = new BufferedReader(new FileReader(Resource.TRAVEL_PATH));
						String line = null;
						while ((line = reader.readLine()) != null) {
							
							//2. 삭제해야 할 항목이 있는 라인에 도달하면 해당 항목을 빈칸으로 바꾸고 그 라인을 dummyTXT에 저장
							if (line.indexOf(searchkey) > -1) {
								String newTXT = planTXT.get(dateIndex).replace(themeKeyword + "■", "");
								dummyTXT += newTXT + "\r\n";
							} else {
								dummyTXT += line + "\r\n";
							}
							
						}
						reader.close();
	
						//3. 덮어쓰기
						BufferedWriter writer = new BufferedWriter(new FileWriter(Resource.TRAVEL_PATH));
						writer.write(dummyTXT);
						writer.close();
						
					} catch (Exception e) {
						System.out.println(e.toString());
					}
					

					System.out.println("\t\t삭제 완료.");
					System.out.println("\t\t----------------------------------");
					System.out.println("\t\tENTER키를 누르시면 일정표로 돌아갑니다.");
					deleteProceed = scanner.nextLine();
					
					if(deleteProceed.equals("")) {
						Cls.clearScreen();
						System.out.println("\t\t\t\t\tLOADING.....");
						Thread.sleep(700);
						Cls.clearScreen();
						start();
						break;
					}
					
				}
				
	//======================================================================================================	
				
			//일정표 선택 메뉴로 돌아가기
			case "*" :
				System.out.println("\n\t\t일정표 메뉴로 돌아갑니다.\n\t\t계속하시려면 ENTER키를 눌러주세요.");
				next = scanner.nextLine();
				if (next.equals("")) {
					Cls.clearScreen();
					System.out.println("\t\t\t\t\tLOADING.....");
					Thread.sleep(700);
					Cls.clearScreen();
					TravelPlan tPlan = new TravelPlan();
					tPlan.start();
					break;
				}
				
		}
		
	}//start 메소드

//---------------------------------------------------------------------------------------------------------------------------
	
	public String printDetailedSchedule() throws Exception {
		
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
		
	//====================================================================================
		
		//2. 회원의 여행 출발 날짜
		String[] memberPlan = planTXT.get(0).split("■");
		Calendar startDate = Calendar.getInstance();
		startDate.set(Integer.parseInt(memberPlan[1].substring(0, 4)), 
				Integer.parseInt(memberPlan[1].substring(4, 6)) - 1, 
				Integer.parseInt(memberPlan[1].substring(6)));
		
	//====================================================================================
		
		//3. 회원의 여행 돌아오는 날짜
		memberPlan = planTXT.get(planTXT.size() - 1).split("■");
		Calendar endDate = Calendar.getInstance();
		endDate.set(Integer.parseInt(memberPlan[1].substring(0, 4)), 
				Integer.parseInt(memberPlan[1].substring(4, 6)) - 1, 
				Integer.parseInt(memberPlan[1].substring(6)));
	
	//====================================================================================
		
		//4. 가는 교통편
		String[] memberTransport = planTXT.get(0).split("■");
		String keyword = memberTransport[memberTransport.length - 1]; //우선 식별자를 추출한 다음
		String departureInfo = "";
		
		String memberDeparture = "";
		
		//항공편의 경우
		if (keyword.substring(0, 2).equals("FG")) {

			try {
				
				BufferedReader reader = 
						new BufferedReader(new FileReader(Resource.FLIGHT_DEPARTURE_PATH));
				
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf(keyword) > -1) {
						departureInfo += line; //식별자를 통해 해당 교통편의 정보를 읽어온다
					}
				}
				reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			//마지막으로 일정표 상에 출력할 형식을 만든다
			String[] departureTemp = departureInfo.split("■");
			
			memberDeparture = String.format("\t\t001\t항공\t%s->제주(%s, %s)\t\t\t\t%,7d원"
					, departureTemp[1], departureTemp[0], departureTemp[3], Integer.parseInt(departureTemp[5]));
			
		} 
		
		//배편의 경우
		else if (keyword.substring(0, 2).equals("SG")) {
			
			try {
				
				BufferedReader reader = 
						new BufferedReader(new FileReader(Resource.SHIP_DEPARTURE_PATH));
				
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf(keyword) > -1) {
						departureInfo += line; //식별자를 통해 해당 교통편의 정보를 읽어온다
					}
				}
				reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			//마지막으로 일정표 상에 출력할 형식을 만든다
			String[] departureTemp = departureInfo.split("■");

			memberDeparture = String.format("\t\t001\t운항\t%s->제주(%s, %s)\t\t\t\t%,7d원"
					, departureTemp[1], departureTemp[0], departureTemp[3], Integer.parseInt(departureTemp[6]));
			
		}
				
	//===============================================================================================================
				
		//5. 돌아오는 교통편
		memberTransport = planTXT.get(planTXT.size()-1).split("■");
		keyword = memberTransport[memberTransport.length - 1]; //식별자
		String returnInfo = "";
		
		String memberReturn = "";
		
		//항공편의 경우
		if (keyword.substring(0, 2).equals("FC")) {
			
			try {
				
				BufferedReader reader = 
						new BufferedReader(new FileReader(Resource.FLIGHT_RETURN_PATH));
				
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf(keyword) > -1) {
						returnInfo += line; //식별자를 통해 해당 교통편의 정보를 읽어온다
					}
				}
				reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			//마지막으로 일정표 상에 출력할 형식을 만든다
			String[] returnTemp = returnInfo.split("■");

			memberReturn = String.format("항공\t%s->제주(%s, %s)\t\t\t\t%,7d원"
					, returnTemp[1], returnTemp[0], returnTemp[3], Integer.parseInt(returnTemp[5]));
			
		} 
		
		//배편의 경우
		else if (keyword.substring(0, 2).equals("SC")) {
			
			
			try {
				
				BufferedReader reader = 
						new BufferedReader(new FileReader(Resource.SHIP_RETURN_PATH));
				
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf(keyword) > -1) {
						returnInfo += line;
					}
				}
				reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			//일정표 상에 출력할 형식
			String[] returnTemp = returnInfo.split("■");

			memberReturn = String.format("해운\t제주->%s(%s, %s)\t\t\t\t%,7d원"
					, returnTemp[1], returnTemp[0], returnTemp[3], Integer.parseInt(returnTemp[6]));
			
		}

	//============================================================================================================================	
				
		//세부 일정표 출력
		System.out.println("\t\t--------------------------------------------------------------------------");
		System.out.print("\t\t██████╗ ███████╗████████╗ █████╗ ██╗██╗     ███████╗██████╗      \r\n" + 
				"\t\t██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██║██║     ██╔════╝██╔══██╗     \r\n" + 
				"\t\t██║  ██║█████╗     ██║   ███████║██║██║     █████╗  ██║  ██║     \r\n" + 
				"\t\t██║  ██║██╔══╝     ██║   ██╔══██║██║██║     ██╔══╝  ██║  ██║     \r\n" + 
				"\t\t██████╔╝███████╗   ██║   ██║  ██║██║███████╗███████╗██████╔╝     \r\n" + 
				"\t\t╚═════╝ ╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝╚══════╝╚══════╝╚═════╝      \r\n" + 
				"                                                                 \r\n" + 
				"\t\t███████╗ ██████╗██╗  ██╗███████╗██████╗ ██╗   ██╗██╗     ███████╗\r\n" + 
				"\t\t██╔════╝██╔════╝██║  ██║██╔════╝██╔══██╗██║   ██║██║     ██╔════╝\r\n" + 
				"\t\t███████╗██║     ███████║█████╗  ██║  ██║██║   ██║██║     █████╗  \r\n" + 
				"\t\t╚════██║██║     ██╔══██║██╔══╝  ██║  ██║██║   ██║██║     ██╔══╝  \r\n" + 
				"\t\t███████║╚██████╗██║  ██║███████╗██████╔╝╚██████╔╝███████╗███████╗\r\n" + 
				"\t\t╚══════╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═════╝  ╚═════╝ ╚══════╝╚══════╝\n");
		System.out.println("\t\t--------------------------------------------------------------------------");
		String header = String.format("\t\t\t\t\t\t[ 세부 일정표 ]\n\n\t\t\t\t\t             %s 님의 여행일정\n\t\t\t\t\t   %tF ~ %tF\n"
				, memberName, startDate, endDate);
		
		System.out.println(header);

		//여행 일수 만큼 반복
		for (int i=0; i<planTXT.size(); i++) {
			
			//삭제 모드로 갈 시에 필요하다!!
			dateIndex = i;
			int returnNumber = 0;
			
			System.out.println("\t\t===============================================================================");
			System.out.printf("\t\t(%d일차) %tF\n", i+1, startDate);
			System.out.println("\t\t===============================================================================");
			System.out.println("\t\t번호\t항목\t주요일정\t\t\t\t\t\t비용");
			
			//테마 출력
			String[] memberTheme = planTXT.get(i).split("■"); //1일차에 저장한 테마부터 차례로 불러옵니다
			
			//1일차
			if (i == 0) {
			
				//1일차의 경우 교통편 추가
				System.out.println("\t\t-------------------------------------------------------------------------------");
				System.out.println(memberDeparture);
				
				//테마 식별자는 2번지부터 시작 -> 마지막 교통편 식별자는 제외
				for (int j=2; j<=memberTheme.length - 2; j++) {
					
					String themeKeyword = memberTheme[j]; //테마 식별자
					
					//키워드가 들어있는 테마 리스트를 찾고 키워드에 해당하는 항목을 불러온다
					Theme theme = new Theme();
					String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
					String category = themeTXT[themeTXT.length-1];
					String detail = themeTXT[1];
					int price = Integer.parseInt(themeTXT[4]);
					String location = themeTXT[3];
					
					//travelExpense.add(price);
					
					System.out.println("\t\t-------------------------------------------------------------------------------");
					System.out.printf("\t\t%03d\t%s\t%-12s\t\t\t\t\t%,7d원\n\t\t\t\t%s\n", j, category, detail, price, location);
					
				}
					
			} 
			
			//마지막 날짜
			else if (i == planTXT.size() - 1) {

				for (int j=2; j<=memberTheme.length - 2; j++) {
					
					String themeKeyword = memberTheme[j];
					
					Theme theme = new Theme();
					String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
					String category = themeTXT[themeTXT.length-1];
					String detail = themeTXT[1];
					int price = Integer.parseInt(themeTXT[4]);
					String location = themeTXT[3];

					System.out.println("\t\t-------------------------------------------------------------------------------");
					System.out.printf("\t\t%03d\t%s\t%-12s\t\t\t\t\t%,7d원\n\t\t\t\t%s\n", j-1, category, detail, price, location);
					
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
					int price = Integer.parseInt(themeTXT[4]);
					String location = themeTXT[3];

					System.out.println("\t\t-------------------------------------------------------------------------------");
					System.out.printf("\t\t%03d\t%s\t%-12s\t\t\t\t\t%,7d원\n\t\t\t\t%s\n", j, category, detail, price, location);
					
				}
				
			}
			

			//마지막날의 경우에도 교통편 추가
			if (i == planTXT.size() - 1) {
				System.out.println("\t\t-------------------------------------------------------------------------------");
				System.out.println(String.format("\t\t%03d\t%s", returnNumber + 1, memberReturn));
			}
					
	//========================================================================================================================
			
			System.out.println("\t\t===============================================================================");
			
			
			System.out.println("\t\t[1] 추가하기");
			System.out.println("\t\t[2] 삭제하기");
			System.out.println();
			
			if (i == 0) {
				System.out.println("\t\t[0] 다음 일정 보기");
			} else if(i == planTXT.size()-1) {
				System.out.println("\t\t[9] 이전 일정 보기");
			} else {
				System.out.println("\t\t[9] 이전 일정 보기");
				System.out.println("\t\t[0] 다음 일정 보기");
			}
			
			System.out.println("\t\t[*] 일정표 선택 메뉴로 돌아가기");
			System.out.println("\t\t----------------------------------");
			System.out.println("\t\t[ ] 안의 번호를 입력하세요.");
			System.out.print("\t\t선택 : ");
			
			String select = scanner.nextLine();
			
			if (select.equals("0")) {
				
				System.out.println("\n\t\t다음 날짜의 일정 페이지로 이동합니다.\n\t\tENTER키를 눌러 진행해주세요.");
				String next = scanner.nextLine();
				if (next.equals("")) {
					Cls.clearScreen();
					System.out.println("\t\t\t\t\tLOADING.....");
					Thread.sleep(700);
					Cls.clearScreen();
					startDate.add(Calendar.DATE, 1);
					System.out.println("\t\t--------------------------------------------------------------------------");
					System.out.print("\t\t██████╗ ███████╗████████╗ █████╗ ██╗██╗     ███████╗██████╗      \r\n" + 
							"\t\t██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██║██║     ██╔════╝██╔══██╗     \r\n" + 
							"\t\t██║  ██║█████╗     ██║   ███████║██║██║     █████╗  ██║  ██║     \r\n" + 
							"\t\t██║  ██║██╔══╝     ██║   ██╔══██║██║██║     ██╔══╝  ██║  ██║     \r\n" + 
							"\t\t██████╔╝███████╗   ██║   ██║  ██║██║███████╗███████╗██████╔╝     \r\n" + 
							"\t\t╚═════╝ ╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝╚══════╝╚══════╝╚═════╝      \r\n" + 
							"                                                                 \r\n" + 
							"\t\t███████╗ ██████╗██╗  ██╗███████╗██████╗ ██╗   ██╗██╗     ███████╗\r\n" + 
							"\t\t██╔════╝██╔════╝██║  ██║██╔════╝██╔══██╗██║   ██║██║     ██╔════╝\r\n" + 
							"\t\t███████╗██║     ███████║█████╗  ██║  ██║██║   ██║██║     █████╗  \r\n" + 
							"\t\t╚════██║██║     ██╔══██║██╔══╝  ██║  ██║██║   ██║██║     ██╔══╝  \r\n" + 
							"\t\t███████║╚██████╗██║  ██║███████╗██████╔╝╚██████╔╝███████╗███████╗\r\n" + 
							"\t\t╚══════╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═════╝  ╚═════╝ ╚══════╝╚══════╝\n");
					System.out.println("\t\t--------------------------------------------------------------------------");
					System.out.println(header);
					continue;
				}
				
			} 
			
			else if (select.equals("9")) {
				
				System.out.println("\n\t\t이전 날짜의 일정 페이지로 이동합니다.\n\t\tENTER키를 눌러 진행해주세요.");
				String next = scanner.nextLine();
				if (next.equals("")) {
					Cls.clearScreen();
					System.out.println("\t\t\t\t\tLOADING.....");
					Thread.sleep(700);
					Cls.clearScreen();
					startDate.add(Calendar.DATE, -1);
					System.out.println("\t\t--------------------------------------------------------------------------");
					System.out.print("\t\t██████╗ ███████╗████████╗ █████╗ ██╗██╗     ███████╗██████╗      \r\n" + 
							"\t\t██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██║██║     ██╔════╝██╔══██╗     \r\n" + 
							"\t\t██║  ██║█████╗     ██║   ███████║██║██║     █████╗  ██║  ██║     \r\n" + 
							"\t\t██║  ██║██╔══╝     ██║   ██╔══██║██║██║     ██╔══╝  ██║  ██║     \r\n" + 
							"\t\t██████╔╝███████╗   ██║   ██║  ██║██║███████╗███████╗██████╔╝     \r\n" + 
							"\t\t╚═════╝ ╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝╚══════╝╚══════╝╚═════╝      \r\n" + 
							"                                                                 \r\n" + 
							"\t\t███████╗ ██████╗██╗  ██╗███████╗██████╗ ██╗   ██╗██╗     ███████╗\r\n" + 
							"\t\t██╔════╝██╔════╝██║  ██║██╔════╝██╔══██╗██║   ██║██║     ██╔════╝\r\n" + 
							"\t\t███████╗██║     ███████║█████╗  ██║  ██║██║   ██║██║     █████╗  \r\n" + 
							"\t\t╚════██║██║     ██╔══██║██╔══╝  ██║  ██║██║   ██║██║     ██╔══╝  \r\n" + 
							"\t\t███████║╚██████╗██║  ██║███████╗██████╔╝╚██████╔╝███████╗███████╗\r\n" + 
							"\t\t╚══════╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═════╝  ╚═════╝ ╚══════╝╚══════╝\n");
					System.out.println("\t\t--------------------------------------------------------------------------");
					System.out.println(header);
					i -= 2;
				}
				
			} 
			
			else {
				return select;
			}
					
		}//여행일수 for문 끝
		
		return null;
		
	}//printDetailedSchedule 메소드
	
}//DetailedSchedule 클래스




