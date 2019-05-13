package com.project.jejutvl.newschedule;

import java.util.Scanner;

import com.project.jejutvl.homescreen.Cls;
import com.project.jejutvl.homescreen.Resource;

// 2단계) 교통편 선택
class Transport { 

	private static Scanner scanner;
	
	static {
		scanner = new Scanner(System.in);
	}

//--------------------------------------------------------------------------------------------------------------	
	
	public void start(Boolean flag) throws Exception {

		String select = printTransport(flag); // 교통편 선택 화면 출력

		transport(flag, select); // 가는편 or 오는편, 교통편 처리

	}

//--------------------------------------------------------------------------------------------------------------

	// TODO : 교통편 선택 (1 : 항공편, 2: 배편)
	public void transport(Boolean flag, String select) throws Exception {

		if (select.equals("1") || select.equals("2")) {
			Cls.clearScreen();
			
			// TODO : 도시 선택 
			selectCity(flag, select);
			
		} else {
			System.out.println("\t\t\t\t<ERROR>\n\t\t\t\t잘못된 입력입니다.");
		}

	} // Method : transport

//--------------------------------------------------------------------------------------------------------------

	// TODO : 도시 선택
	public void selectCity(Boolean flag, String ride) throws Exception {

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
		System.out.println("\t\t\t\t         [새로운 일정 작성]\n");
		System.out.println("\t\t\t\t--------------------------------------");

		// 항공편을 선택했을 경우
		if (ride.equals("1")) {
			flightCity(flag);
		}

		// 배편을 선택했을 경우
		else if (ride.equals("2")) {
			shipCity(flag);
		}

	}// Method : selectCity

//--------------------------------------------------------------------------------------------------------------	
	
	// TODO : 항공편 도시 선택
	private void flightCity(Boolean flag) throws Exception {

		if (flag == true) {
			System.out.printf("\t\t\t\t2단계)\n\t\t\t\t교통편 입력 - 항공편\n\t\t\t\t가는날(%s)\n\t\t\t\t출발 도시 선택\n\n",
					Resource.getStartDate());
		} else if (flag == false) {
			System.out.printf("\t\t\t\t2단계)\n\t\t\t\t교통편 입력 - 항공편\n\t\t\t\t오는날(%s)\n\t\t\t\t도착 도시 선택\n\n",
					Resource.getEndDate());
		}

		System.out.println("\t\t\t\t[1] 인천         [6]무안");
		System.out.println("\t\t\t\t[2] 김포         [7]울산");
		System.out.println("\t\t\t\t[3] 부산         [8]광주");
		System.out.println("\t\t\t\t[4] 대구         [9]군산");
		System.out.println("\t\t\t\t[5] 청주         [10]여수");
		System.out.println("\n\t\t\t\t--------------------------------------");
		System.out.println("\t\t\t\t[ ] 안의 번호를 입력하세요");
		System.out.print("\t\t\t\t선택 : ");

		String city = scanner.nextLine();

		if (city.equals("1") || city.equals("2") || city.equals("3") || city.equals("4") || city.equals("5")
				|| city.equals("6") || city.equals("7") || city.equals("8") || city.equals("9") || city.equals("10")) {
			
			Cls.clearScreen();
			
			// TODO : 항공편 스케줄
			FlightTimeTable flightT = new FlightTimeTable();
			flightT.flightTimeTable(flag, city);
			
		} else {
			
			System.out.println("\t\t\t\t<ERROR>\n\t\t\t\t잘못된 입력입니다.");
			
		}

	} // Method : flightCity

//--------------------------------------------------------------------------------------------------------------
	
	// TODO : 배편 도시 선택
	public void shipCity(Boolean flag) throws Exception {
		if (flag == true) {
			System.out.printf("\t\t\t\t2단계)\n\t\t\t\t교통편 입력 - 배편\n\t\t\t\t가는날(%s)\n\t\t\t\t출발 도시 선택\n\n",
					Resource.getStartDate());
		} else if (flag == false) {
			System.out.printf("\t\t\t\t2단계)\n\t\t\t\t교통편 입력 - 배편\n\t\t\t\t오는날(%s)\n\t\t\t\t도착 도시 선택\n\n",
					Resource.getEndDate());
		}
		System.out.println("\t\t\t\t[1] 부산         [4]목포");
		System.out.println("\t\t\t\t[2] 여수         [5]해남");
		System.out.println("\t\t\t\t[3] 완도         [6]고흥");
		System.out.println("\n\t\t\t\t--------------------------------------");
		System.out.println("\t\t\t\t[ ] 안의 번호를 입력하세요");
		System.out.print("\t\t\t\t선택 : ");

		String city = scanner.nextLine();

		if (city.equals("1") || city.equals("2") || city.equals("3") || city.equals("4") || city.equals("5")
				|| city.equals("6")) {
			
			Cls.clearScreen();
			
			// TODO : 배편 스케줄
			ShipTimeTable shipT = new ShipTimeTable();
			shipT.shipTimeTable(flag, city);
			
		} else {
			
			System.out.println("\t\t\t\t<ERROR>\n\t\t\t\t잘못된 입력입니다.");
			
		}

	} // Method : shipCity

//--------------------------------------------------------------------------------------------------------------
	
	// TODO : 교통편 선택 -> 항공편 or 배편
	public String printTransport(Boolean flag) {

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
		System.out.println("\t\t\t\t         [새로운 일정 작성]\n");
		System.out.println("\t\t\t\t--------------------------------------");

		if (flag == true) { // 최초 실행시에는 무조건 가는날로 분기가 설정됩니다.
			System.out.printf("\t\t\t\t2단계)\n\t\t\t\t교통편 입력\n\t\t\t\t가는날(%s)\n\n", Resource.getStartDate());
		} else if (flag == false) {
			System.out.printf("\t\t\t\t2단계)\n\t\t\t\t교통편 입력\n\t\t\t\t오는날(%s)\n\n", Resource.getEndDate());
		}

		System.out.println("\t\t\t\t[1] 항공편");
		System.out.println("\t\t\t\t[2] 배편");
		System.out.println("\n\t\t\t\t--------------------------------------");
		System.out.println("\t\t\t\t[ ] 안의 번호를 입력하세요");
		System.out.print("\t\t\t\t선택 : ");

		String select = scanner.nextLine();

		return select;
		
	} // Method : printTransport

} // Class : Transport



