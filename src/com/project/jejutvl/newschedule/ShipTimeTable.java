package com.project.jejutvl.newschedule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.jejutvl.homescreen.Cls;
import com.project.jejutvl.homescreen.Resource;

public class ShipTimeTable {
	
	private static Scanner scan = new Scanner(System.in);
	
	// =================================== 배편 스케줄
	
	public void shipTimeTable(Boolean flag, String city) throws Exception {

		ArrayList<String> list = new ArrayList<String>();

		if (city.equals("1")) { city = "부산"; } 
		else if (city.equals("2")) { city = "여수"; } 
		else if (city.equals("3")) { city = "완도"; } 
		else if (city.equals("4")) { city = "목포"; }
		else if (city.equals("5")) { city = "해남"; }
		else if (city.equals("6")) { city = "녹동"; }

		System.out.println("\t\t\t                    [새로운 일정 작성]\n");
		System.out.println("\t\t\t-------------------------------------------------------");
		
		if (flag == true) {
			goShip(flag, city, list);
		} else if (flag == false) {
			comeShip(flag, city, list);		
		}
	
	} // Method : shipTimeTable

	// =================================== 오는 날 배편
	
	private void comeShip(Boolean flag, String city, ArrayList<String> list) throws Exception {
	
		System.out.printf("\t\t\t2단계)\n\t\t\t교통편 입력 - 배편\n\t\t\t오는날(%s)\n\t\t\t(제주 -> %s)\n\n", Resource.getEndDate(), city);
		System.out.println("\t\t\t[번호]      [운항사]      [출발시간]      [도착시간]      [가격]");
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resource.SHIP_RETURN_PATH));
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
		
			String company = temp[2]; // 운항사
			String departure = temp[3]; // 출발시간
			String arrival = temp[4]; // 도착시간
			String cost = temp[5]; // 가격

			System.out.printf("\t\t\t[%02d]\t     %-6s \t %5s         %-5s       %-5s\n", i + 1, Resource.getReturnCode(), company,
					departure, arrival, cost);
		}
	

		System.out.println("\n\t\t\t-----------------------------------------------------------------");
		System.out.println("\t\t\t[ ] 안의 번호를 입력하세요");
		System.out.print("\t\t\t선택 : ");

		String select = scan.nextLine();

		if (Integer.parseInt(select) > 0 && Integer.parseInt(select) <= list.size()) {
			String[] temp = list.get(Integer.parseInt(select) - 1).split("■");
		
			Resource.setReturnCode(temp[0]);
			Resource.save.add(2, Resource.getReturnCode());
			System.out.printf("\n\t\t\t%s 배편을 선택하셨습니다.\n", Resource.getReturnCode());
		}
		System.out.println("\t\t\t다음 단계로 이동합니다.");
		System.out.println("\t\t\t계속하시려면 ENTER키를 눌러주세요.");
		

		String yes = scan.nextLine();

		if (yes.equals("")) {
			Cls.clearScreen();
			TravelDay sd = new TravelDay();
			sd.start();

		}else {
			System.out.println("\t\t\t<ERROR>\n\t\t\t잘못된 입력입니다.");
		}
	}

	// =================================== 가는 날 배편
	
	private void goShip(Boolean flag, String city, ArrayList<String> list) throws Exception {
		System.out.printf("\t\t\t2단계)\n\t\t\t교통편 입력 - 배편\n\t\t\t가는날(%s)\n\t\t\t(%s -> 제주)\n\n", Resource.getStartDate(), city);
		System.out.println("\t\t\t[번호]      [운항사]      [출발시간]      [도착시간]      [가격]");
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resource.SHIP_DEPARTURE_PATH));
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
			
			String company = temp[2]; // 운항사
			String departure = temp[3]; // 출발시간
			String arrival = temp[4]; // 도착시간
			String cost = temp[5]; // 가격

			
				System.out.printf("\t\t\t[%02d]\t     %-6s \t %5s         %-5s       %-5s\n", i + 1, Resource.getDepartureCode(), company,
						departure, arrival, cost);
			

		}

		System.out.println("\n\t\t\t-----------------------------------------------------------------");
		System.out.println("\t\t\t[ ] 안의 번호를 입력하세요");
		System.out.print("\t\t\t선택 : ");

		String select = scan.nextLine();

		if (Integer.parseInt(select) > 0 && Integer.parseInt(select) <= list.size()) {
			String[] temp = list.get(Integer.parseInt(select) - 1).split("■");
			
				Resource.setDepartureCode(temp[0]);
				Resource.save.add(1, Resource.getDepartureCode());
				System.out.printf("\n\t\t\t%s 배편을 선택하셨습니다.\n", Resource.getDepartureCode());
			
			
				System.out.println("\t\t\t돌아오는 날의 교통편 선택으로 이동합니다.");
				System.out.println("\t\t\t계속하시려면 ENTER키를 눌러주세요.");
		}

			String yes = scan.nextLine();

			if (yes.equals("")) {
				Cls.clearScreen();
				
					flag = false;
					Transport ts = new Transport();
					ts.start(flag); // 오는날 교통편 선택 화면으로 이동합니다.
				
			} else {

			System.out.println("\t\t\t<ERROR>\n\t\t\t잘못된 입력입니다.");

		}
	}
}
