package com.project.jejutvl.travelplan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.project.jejutvl.homescreen.Cls;
import com.project.jejutvl.homescreen.HomeScreen;
//import com.project.jejutvl.messagemanagement.UI;
import com.project.jejutvl.homescreen.Resource;
import com.project.jejutvl.randomtravel.RandomTravel;

//일정표 선택하는 클래스
public class TravelPlan {
	
	private static Scanner scanner;
	//private static UI ui;
	
	static {
		//ui = new UI();
		scanner = new Scanner(System.in);
	}
	
//------------------------------------------------------------	
	
	// 여행일정표 메뉴 진입
	public void start() throws Exception {
		
		//TODO : printTravelPlan에서 리턴 받은 값에 따라 출력될 메뉴가 결정됩니다.
		String select = printTravelPlan();
		//ENTER키를 입력받으면 다음 화면으로 이동하게 합니다.
		String next = "";
		
		switch(select) {
		
			case "1" : // 총 일정표
				System.out.println("\n\t\t\t\t'총 일정표'를 선택하셨습니다.\n\t\t\t\t계속하시려면 ENTER키를 눌러주세요.");
				
				//ENTER키 입력
				next = scanner.nextLine();
				
				if (next.equals("")) {
					Cls.clearScreen();
					System.out.println("\t\t\t\t\tLOADING.....");
					Thread.sleep(700);
					Cls.clearScreen();
					TotalSchedule totalSKD = new TotalSchedule();
					totalSKD.start();
					break;
					// new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // 화면 지우기
				}

		//======================================================================================================
				
			case "2" : // 날짜별 세부 일정표
				System.out.println("\n\t\t\t\t'세부 일정표'를 선택하셨습니다.\n\t\t\t\t계속하시려면 ENTER키를 눌러주세요.");
				
				next = scanner.nextLine();
				
				if (next.equals("")) {
					Cls.clearScreen();
					System.out.println("\t\t\t\t\tLOADING.....");
					Thread.sleep(700);
					Cls.clearScreen();
					DetailedSchedule detailedSKD = new DetailedSchedule();
					detailedSKD.start();
					break;
				}
				
		//======================================================================================================
				
			case "3" : // 매칭 일정표
				
				String temp = "";
				
				try {
					BufferedReader source_reader = new BufferedReader(new FileReader(Resource.MATCH_PATH));
					String line = null;
					while ((line = source_reader.readLine()) != null) {
						if (line.indexOf(Resource.getMemberID()) > -1) {
							temp = line;
						}
					}
					source_reader.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
				
				if (!Resource.getMemberID().isEmpty()) {
					
					if (!Resource.getMatchID().isEmpty()) {
						
						String[] couple = temp.split("■");
						String opponent = couple[1];
						Resource.setMatchID(opponent);
						
						System.out.println("\n\t\t\t\t'매칭 일정표'를 선택하셨습니다.\n\t\t\t\t계속하시려면 ENTER키를 눌러주세요.");
						
						next = scanner.nextLine();
						
						if (next.equals("")) {
							Cls.clearScreen();
							System.out.println("\t\t\t\t\tLOADING.....");
							Thread.sleep(700);
							Cls.clearScreen();
							MatchSchedule matchSKD = new MatchSchedule();
							matchSKD.start();
							break;
						}
						
					} else {
						
						System.out.println("\n\t\t\t\t회원님과 매칭 된 상대가 없습니다\n\t\t\t\t랜덤 매칭 메뉴를 이용해주세요.");
						System.out.println("\t\t\t\t계속하시려면 ENTER키를 눌러주세요.");
						next = scanner.nextLine();
						if (next.equals("")) {
							Cls.clearScreen();
							System.out.println("\t\t\t\t\tLOADING.....");
							Thread.sleep(700);
							Cls.clearScreen();
							RandomTravel.start();
							break;
						}
						
					}
			
				}
				
		//======================================================================================================
				
			case "*" : // 메인 화면으로 돌아가기
				System.out.println("\n\t\t\t\t메인 메뉴로 돌아갑니다.\n\t\t\t\t계속하시려면 ENTER키를 눌러주세요.");
				
				next = scanner.nextLine();
				
				if (next.equals("")) {
					Cls.clearScreen();
					System.out.println("\t\t\t\t\tLOADING.....");
					Thread.sleep(700);
					Cls.clearScreen();
					HomeScreen.start();
					break;
				}
				
		}//switch문 끝
		
	}//start메소드

//----------------------------------------------------------------------------------------------------------
	
	// 여행일정표 메뉴 출력
	public static String printTravelPlan() {
		
		System.out.println();
		System.out.println("\t\t--------------------------------------------------------------------------");
		System.out.print("\t\t██╗   ██╗██╗███████╗██╗    ██╗                                   \r\n" + 
				"\t\t██║   ██║██║██╔════╝██║    ██║                                   \r\n" + 
				"\t\t██║   ██║██║█████╗  ██║ █╗ ██║                                   \r\n" + 
				"\t\t╚██╗ ██╔╝██║██╔══╝  ██║███╗██║                                   \r\n" + 
				"\t\t ╚████╔╝ ██║███████╗╚███╔███╔╝                                   \r\n" + 
				"\t\t  ╚═══╝  ╚═╝╚══════╝ ╚══╝╚══╝                                    \r\n" + 
				"                                                                 \r\n" + 
				"\t\t███████╗ ██████╗██╗  ██╗███████╗██████╗ ██╗   ██╗██╗     ███████╗\r\n" + 
				"\t\t██╔════╝██╔════╝██║  ██║██╔════╝██╔══██╗██║   ██║██║     ██╔════╝\r\n" + 
				"\t\t███████╗██║     ███████║█████╗  ██║  ██║██║   ██║██║     █████╗  \r\n" + 
				"\t\t╚════██║██║     ██╔══██║██╔══╝  ██║  ██║██║   ██║██║     ██╔══╝  \r\n" + 
				"\t\t███████║╚██████╗██║  ██║███████╗██████╔╝╚██████╔╝███████╗███████╗\r\n" + 
				"\t\t╚══════╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═════╝  ╚═════╝ ╚══════╝╚══════╝\n");
		System.out.println("\t\t--------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t    [일 정 표   선 택]\n");
		System.out.println("\t\t\t\t--------------------------------------\n");
		System.out.println("\t\t\t\t[1] 총 일정표");
		System.out.println("\t\t\t\t[2] 날짜별 세부 일정표");
		System.out.println("\t\t\t\t[3] 매칭 일정표\n");
		System.out.println("\t\t\t\t[*] 메인화면으로 돌아가기\n");
		System.out.println("\t\t\t\t--------------------------------------");
		System.out.println("\t\t\t\t[ ] 안의 번호를 입력하세요.");
		System.out.print("\t\t\t\t선택 : ");
		
		String select = scanner.nextLine();
		return select;
		
	}
}




