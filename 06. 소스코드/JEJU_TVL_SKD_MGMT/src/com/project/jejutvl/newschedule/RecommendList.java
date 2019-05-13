package com.project.jejutvl.newschedule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.jejutvl.homescreen.Cls;
import com.project.jejutvl.homescreen.Resource;
import com.project.jejutvl.travelplan.DetailedSchedule;

class RecommendList {

	private static Scanner scan = new Scanner(System.in);

//----------------------------------------------------------------------------------------------------------------	
	
	// TODO : 테마별 추천리스트 조회 화면
	public void start(String theme, String day) throws Exception {

		ArrayList<String> list = new ArrayList<String>();
		String themeList = "";
		int lineCount = 0;

		if (theme.equals("1")) {
			themeList = "카페";
			Resource.setThemePath(Resource.CAFE_PATH);
		} else if (theme.equals("2")) {
			themeList = "맛집";
			Resource.setThemePath(Resource.FOOD_PATH);
		} else if (theme.equals("3")) {
			themeList = "숙박";
			Resource.setThemePath(Resource.STAY_PATH);
		} else if (theme.equals("4")) {
			themeList = "명소";
			Resource.setThemePath(Resource.PLACE_PATH);
		} else if (theme.equals("5")) {
			themeList = "액티비티";
			Resource.setThemePath(Resource.ACTI_PATH);
		}

		System.out.println("\t\t\t\t[새로운 일정 작성]\n");
		System.out.printf("\t\t\t\t테마 - %s/추천리스트\n\n", themeList);
		System.out.println("\t\t\t\t==================================================");
		
		try {

			BufferedReader source_reader = new BufferedReader(new FileReader(Resource.getThemePath()));
			String line = null;
			while ((line = source_reader.readLine()) != null) {
				list.add(line); // 출력할 리스트에 그 라인을 추가
			}
			source_reader.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		for (int i = 0; i < list.size(); i++) {

			lineCount++;

			String[] temp = list.get(i).split("■");
			String name = temp[1]; // 장소명
			String info = temp[2]; // 설명
			String place = temp[3]; // 장소
			String cost = temp[4]; // 비용
			String rate = temp[5]; // 평가

			if (Integer.parseInt(rate) == 1) {
				rate = "★☆☆☆☆";
			} else if (Integer.parseInt(rate) == 2) {
				rate = "★★☆☆☆";
			} else if (Integer.parseInt(rate) == 3) {
				rate = "★★★☆☆";
			} else if (Integer.parseInt(rate) == 4) {
				rate = "★★★★☆";
			} else if (Integer.parseInt(rate) == 5) {
				rate = "★★★★★";
			}

			System.out.printf("\t\t\t\t[%d] %s\n", i + 1, name);
			System.out.println("\t\t\t\t - 설명 : " + info);
			System.out.println("\t\t\t\t - 주소 : " + place);
			System.out.println("\t\t\t\t - 비용 : " + cost);
			System.out.println("\t\t\t\t - 평가  : " + rate);

			if (lineCount == 5 || lineCount == 10 ||
				lineCount == 20 || lineCount == 20 || 
				lineCount == 30 || lineCount == 40 || 
				lineCount == 50 || lineCount == list.size()) {

				System.out.println("\t\t\t\t==================================================");
				System.out.printf("\t\t\t\t[0] 더보기(%d/%d)\n", lineCount, list.size());
				System.out.println("\t\t\t\t[#] 검색하기");
				System.out.println("\t\t\t\t[*] 테마선택으로 돌아가기");
				System.out.println("\t\t\t\t==================================================");
				System.out.println("\t\t\t\t[ ] 안의 번호를 입력하세요.");
				System.out.print("\t\t\t\t선택(택 1) : ");

				String choice = scan.nextLine();

				if (choice.equals("0")) {

					//더보기
					Cls.clearScreen();
					System.out.println("\t\t\t\t[새로운 일정 작성]\n");
					System.out.printf("\t\t\t\t테마 - %s/추천리스트\n\n", themeList);

				} else if (choice.equals("*")) {

					// TODO : 테마선택으로 돌아가기
					Cls.clearScreen();
			        TravelTheme tTheme = new TravelTheme();
			        tTheme.start(day);
					break;

				} else if (choice.equals("#")) {

					// TODO : 검색화면으로 이동하기
					Cls.clearScreen();
					search(themeList, theme, day);
					break;

				} else {

					// 선택한 테마를 일정표에 추가하기
					temp = list.get(Integer.parseInt(choice) - 1).split("■");
					String themeCode = temp[0];
					name = temp[1];

					
					// TODO : 세부일정 모드에서 추가모드로 들어왔을 때와, 새로운 일정을 만들 때와의 분기를 설정
					if (DetailedSchedule.flag == true) {

						DetailedSchedule.addItem = themeCode;
						System.out.printf("\t\t\t\t선택하신 '%s'이(가) 일정표에 추가되었습니다.\n", name);
						System.out.println("\t\t\t\tENTER키를 누르시면 세부일정 화면으로 돌아갑니다.");
						String yes = scan.nextLine();

						if (yes.equals("")) {
							break;
						}

					} else {

						int selectDate = Integer.parseInt(day);
						
						// 선택한 항목을 추가
						if (!Resource.getMemberTheme().containsKey(selectDate - 1)) {
							Resource.getMemberTheme().put(selectDate - 1, themeCode);
						} else {
							Resource.getMemberTheme().put(selectDate - 1,
									Resource.getMemberTheme().get(selectDate - 1) + "■" + themeCode);
						}
						System.out.printf("\t\t\t\t선택하신 '%s'이(가) 일정표에 추가되었습니다.\n", name);
						System.out.println("\t\t\t\tENTER키를 누르시면 테마 선택 화면으로 돌아갑니다.");
						String next = scan.nextLine();
						
						if (next.equals("")) {
							
							// TODO : 테마 선택 화면으로 이동
				            Cls.clearScreen();
				            TravelTheme tTheme = new TravelTheme();
				            tTheme.start(day);
				            break;
				            
						}

					}

				}

			} else {
				System.out.println("\t\t\t\t--------------------------------------------------");
			}

		} // for문 끝

	}// recommend 메소드

//----------------------------------------------------------------------------------------------------------------
	
	// TODO : 검색 화면입니다.
	public void search(String themeList, String theme, String day) throws Exception {

		int count = 0;

		ArrayList<String> list = new ArrayList<String>();

		
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
		System.out.printf("\t\t\t\t테마 - %s 리스트 검색", themeList);
		System.out.println("\n\t\t\t\t검색하실 키워드를 입력하세요");
		System.out.println("\t\t\t\t==================================================");
		System.out.print("\t\t\t\t검색 : ");

		String keyword = scan.nextLine();

		try {

			BufferedReader source_reader = new BufferedReader(new FileReader(Resource.getThemePath()));

			String line = null;
			while ((line = source_reader.readLine()) != null) {
				if (line.indexOf(keyword) > -1) { // 라인을 한 줄 불러올 때 마다 검색어가 포함되어있으면
					list.add(line); // 출력할 리스트에 그 라인을 추가
					count++; // 검색결과 1개씩 증가
				}
			}
			source_reader.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		if (count != 0) {

			Cls.clearScreen();

			
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
			System.out.println("\t\t\t\t테마 - 카페 리스트 검색");
			System.out.printf("\n\t\t\t\t'%s' 검색결과 : %d개\n", keyword, count);
			System.out.println("\t\t\t\t==================================================");

			for (int i = 0; i < list.size(); i++) {

				String[] temp = list.get(i).split("■");
				String name = temp[1]; // 장소명
				String info = temp[2]; // 설명
				String place = temp[3]; // 장소
				String cost = temp[4]; // 비용
				String rate = temp[5]; // 평가

				if (Integer.parseInt(rate) == 1) {
					rate = "★☆☆☆☆";
				} else if (Integer.parseInt(rate) == 2) {
					rate = "★★☆☆☆";
				} else if (Integer.parseInt(rate) == 3) {
					rate = "★★★☆☆";
				} else if (Integer.parseInt(rate) == 4) {
					rate = "★★★★☆";
				} else if (Integer.parseInt(rate) == 5) {
					rate = "★★★★★";
				}

				System.out.printf("\t\t\t\t[%d] %s\n", i + 1, name);
				System.out.println("\t\t\t\t - 설명 : " + info);
				System.out.println("\t\t\t\t - 주소 : " + place);
				System.out.println("\t\t\t\t - 비용 : " + cost);
				System.out.println("\t\t\t\t - 평가  : " + rate);
				if (i < list.size() - 1) {
					System.out.println("\t\t\t\t--------------------------------------------------");
				} else {
					System.out.println("\t\t\t\t==================================================");
					System.out.println("\t\t\t\t[0] 추천리스트로 돌아가기");
					System.out.println("\t\t\t\t[#] 다시 검색하기");
					System.out.println("\t\t\t\t==================================================");
					System.out.println("\t\t\t\t[ ] 안의 번호를 입력하세요.");
					System.out.print("\t\t\t\t선택(택 1) : ");

					String choice = scan.nextLine();

					if (choice.equals("0")) {

						Cls.clearScreen();
						start(theme, day);
						break;

					} else if (choice.equals("#")) {

						Cls.clearScreen();
						search(themeList, theme, day);
						break;

					}

					// 선택한 테마를 일정표에 추가하기
					temp = list.get(Integer.parseInt(choice) - 1).split("■");
					String themeCode = temp[0];
					name = temp[1];

					// TODO : 검색모드도 동일하다. 세부일정에서 추가모드로 왔을때와 새로운 일정 추가 때의 분기 설정
					if (DetailedSchedule.flag == true) {

						DetailedSchedule.addItem = themeCode;
						System.out.printf("\t\t\t\t선택하신 '%s'이(가) 일정표에 추가되었습니다.\n", name);
						System.out.println("\t\t\t\tENTER키를 누르시면 세부일정 화면으로 돌아갑니다.");
						String yes = scan.nextLine();

						if (yes.equals("")) {
							break;
						}

					} else {

						int selectDate = Integer.parseInt(day);
						
						// 선택한 항목을 추가합니다.
						if (!Resource.getMemberTheme().containsKey(selectDate - 1)) {
							Resource.getMemberTheme().put(selectDate - 1, themeCode);
						} else {
							Resource.getMemberTheme().put(selectDate - 1,
									Resource.getMemberTheme().get(selectDate - 1) + "■" + themeCode);
						}
						System.out.printf("\t\t\t\t선택하신 '%s'이(가) 일정표에 추가되었습니다.\n", name);
						System.out.println("\t\t\t\tENTER키를 누르시면 테마 선택 화면으로 돌아갑니다.");
						String yes = scan.nextLine();

						if (yes.equals("")) {
							
							// TODO : HashMap 안에 선택한 테마들을 넣어둔 상태 -> 테마 선택 화면으로 이동
							Cls.clearScreen();
							TravelTheme tTheme = new TravelTheme();
							tTheme.start(day);
							break;
							
						}
					}
					// TODO : 여기까지 바뀜

				}

			} // for문 끝

		} else {

			System.out.println("\t\t\t\t--------------------------------------------------");
			System.out.println("\t\t\t\t입력하신 검색어에 해당하는 항목을 찾을 수 없습니다.");
			System.out.println("\t\t\t\t[0] 추천리스트로 돌아가기");
			System.out.println("\t\t\t\t[#] 다시 검색하기");
			System.out.print("\t\t\t\t입력 : ");

			String choice = scan.nextLine();

			if (choice.equals("0")) {
				Cls.clearScreen();
				start(theme, day);
			} else if (choice.equals("#")) {
				Cls.clearScreen();
				search(themeList, theme, day);
			}

		}

	}// search메소드

}// Class : RecommendList



