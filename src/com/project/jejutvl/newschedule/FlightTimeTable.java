package com.project.jejutvl.newschedule;

import com.project.jejutvl.homescreen.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightTimeTable {

	Scanner scan = new Scanner(System.in);
	
	// =================================== 항공편 스케줄

	public void flightTimeTable(Boolean flag, String city) throws Exception {

		ArrayList<String> list = new ArrayList<String>();

		if (city.equals("1")) { city = "인천"; } 
		else if (city.equals("2")) { city = "김포"; } 
		else if (city.equals("3")) { city = "부산"; } 
		else if (city.equals("4")) { city = "대구"; }
		else if (city.equals("5")) { city = "청주"; }
		else if (city.equals("6")) { city = "무안"; }
		else if (city.equals("7")) { city = "울산"; }
		else if (city.equals("8")) { city = "광주"; }
		else if (city.equals("9")) { city = "군산"; } 
		else if (city.equals("10")) { city = "여수"; }

		System.out.println("\t\t\t                       [새로운 일정 작성]\n");
		System.out.println("\t\t\t-----------------------------------------------------------------");
		
		
		if (flag == true) {
			goFlight(flag, city, list); // 가는 날 비행편
		} else if (flag == false) {
			comeFlight(flag, city, list); // 오는 날 비행편
		}

	} // Method : flightTimeTable
	
	// =================================== 오는 날 비행편

	private void comeFlight(Boolean flag, String city, ArrayList<String> list) throws Exception {
		
		System.out.printf("\t\t\t2단계)\n\t\t\t교통편 입력 - 항공편\n\t\t\t오는날(%s)\n\t\t\t(제주 -> %s)\n\n", Resource.getEndDate(), city);
		System.out.println("\t\t\t[번호]      [항공사]      [출발시간]      [도착시간]      [가격]");
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resource.FLIGHT_RETURN_PATH));
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				String dataCity = temp[1];
				if (dataCity.equals(city)) {
					list.add(line);
				}
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		for (int i = 0; i < list.size(); i++) {

			String[] temp = list.get(i).split("■");
			Resource.setReturnCode(temp[0]); // 돌아오는 날의 교통편 일련번호
		
			String company = temp[2]; // 항공사
			String departure = temp[3]; // 출발시간
			String arrival = temp[4]; // 도착시간
			String cost = temp[5]; // 가격

			System.out.printf("\t\t\t[%02d]\t     %-6s \t %5s         %-5s       %-5s\n", i + 1,
						Resource.getReturnCode(), company, departure, arrival, cost);

		}

		System.out.println("\n\t\t\t-----------------------------------------------------------------");
		System.out.println("\t\t\t[ ] 안의 번호를 입력하세요");
		System.out.print("\t\t\t선택 : ");

		String select = scan.nextLine();
		
		if (Integer.parseInt(select) > 0 && Integer.parseInt(select) <= list.size()) {
			String[] temp = list.get(Integer.parseInt(select) - 1).split("■");
			
			Resource.setReturnCode(temp[0]);
			Resource.save.add(2, Resource.getReturnCode());
			System.out.printf("\n\t\t\t%s 항공편을 선택하셨습니다.\n", Resource.getReturnCode());
			
		}
		
		System.out.println("\t\t\t다음 단계로 이동합니다.");
		System.out.println("\t\t\t계속하시려면 ENTER키를 눌러주세요.");
		

		String yes = scan.nextLine();

		if (yes.equals("")) {
			Cls.clearScreen();
			TravelDay sd = new TravelDay();
			sd.start(); // 날짜 선택 메소드로 이동
				
		} else {
			System.out.println("\t\t\t<ERROR>\n\t\t\t잘못된 입력입니다.");
		}
		
	} // Method : comeFlight

	// =================================== 가는 날 비행편

	private void goFlight(Boolean flag, String city, ArrayList<String> list) throws Exception {
		
		System.out.printf("\t\t\t2단계)\n\t\t\t교통편 입력 - 항공편\n\t\t\t가는날(%s)\n\t\t\t(%s -> 제주)\n\n", Resource.getStartDate(), city);
		System.out.println("\t\t\t[번호]      [항공사]      [출발시간]      [도착시간]      [가격]");
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resource.FLIGHT_DEPARTURE_PATH));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				String dataCity = temp[1];
				if (dataCity.equals(city)) {
					list.add(line);
				}
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		for (int i = 0; i < list.size(); i++) {

			String[] temp = list.get(i).split("■");
			Resource.setDepartureCode(temp[0]); // 가는날의 교통편 일련번호

			String company = temp[2]; // 항공사
			String departure = temp[3]; // 출발시간
			String arrival = temp[4]; // 도착시간
			String cost = temp[5]; // 가격
			System.out.printf("\t\t\t[%02d]\t     %-6s \t %5s         %-5s       %-5s\n", i + 1
					, Resource.getDepartureCode(), company, departure, arrival, cost);
		}

		System.out.println("\n\t\t\t-----------------------------------------------------------------");
		System.out.println("\t\t\t[ ] 안의 번호를 입력하세요");
		System.out.print("\t\t\t선택 : ");

		String select = scan.nextLine();
		
		if (Integer.parseInt(select) > 0 && Integer.parseInt(select) <= list.size()) {
			String[] temp = list.get(Integer.parseInt(select) - 1).split("■");

			Resource.setDepartureCode(temp[0]);
			Resource.save.add(1, Resource.getDepartureCode());
			System.out.printf("\n\t\t\t%s 항공편을 선택하셨습니다.\n", Resource.getDepartureCode());
			System.out.println("\t\t\t돌아오는 날의 교통편 선택으로 이동합니다.");
			System.out.println("\t\t\t계속하시려면 ENTER키를 눌러주세요.");

			String next = scan.nextLine();

			Cls.clearScreen();

			flag = false;
			Transport ts = new Transport();
			ts.start(flag); // 오는날 교통편 선택 화면으로 이동합니다.


		} else {
			System.out.println("\t\t\t<ERROR>\n\t\t\t잘못된 입력입니다.");
		}
		
	} // Method : goFlight
}
