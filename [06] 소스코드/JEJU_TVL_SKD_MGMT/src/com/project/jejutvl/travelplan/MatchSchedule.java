package com.project.jejutvl.travelplan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.jejutvl.homescreen.*;

//매칭 일정표 클래스
public class MatchSchedule {

	private static String goStop = "go";
	
	private static Scanner scanner;

	static {
		scanner = new Scanner(System.in);
	}

//--------------------------------------------------

	public void start() throws Exception {

		String select = printMatchSchedule();

		switch (select) {

		case "0": // 세부 일정표 보기
			System.out.println("\n\t세부 일정표를 조회합니다.\n\t계속하시려면 ENTER키를 눌러주세요.");
			String next = scanner.nextLine();
			if (next.equals("")) {
				Cls.clearScreen();
				System.out.println("\t\t\t\t\tLOADING.....");
				Thread.sleep(700);
				Cls.clearScreen();
				DetailedSchedule detailedSKD = new DetailedSchedule();
				detailedSKD.start();
				break;
			}

			// ===================================================================================================

		case "#": // 일정표 메뉴로 돌아가기
			System.out.println("\n\t일정표 메뉴로 돌아갑니다.\n\t계속하시려면 ENTER키를 눌러주세요.");
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

			// ===================================================================================================

		case "*": // 메인메뉴로 돌아가기
			System.out.println("\n\t메인 메뉴로 돌아갑니다.\n\t계속하시려면 ENTER키를 눌러주세요.");
			next = scanner.nextLine();
			if (next.equals("")) {
				Cls.clearScreen();
				System.out.println("\t\t\t\t\tLOADING.....");
				Thread.sleep(700);
				Cls.clearScreen();
				HomeScreen.start();
				break;
			}

		}// switch문의 끝

	}// start 메소드

//-------------------------------------------------------------------------------------------------------------

	public String printMatchSchedule() {

		ArrayList<String> planTXT = new ArrayList<String>();

		try {
			BufferedReader source_reader = new BufferedReader(new FileReader(Resource.TRAVEL_PATH));
			String line = null;
			while ((line = source_reader.readLine()) != null) {
				if (line.indexOf(Resource.getMemberID()) > -1) { // 해당ID가 포함된 라인이 발견되면
					planTXT.add(line); // 해당 라인을 txt에 추가
				}
			}
			source_reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		ArrayList<String> matchPlanTXT = new ArrayList<String>();

		try {
			BufferedReader source_reader = new BufferedReader(new FileReader(Resource.TRAVEL_PATH));
			String line = null;
			while ((line = source_reader.readLine()) != null) {
				if (line.indexOf(Resource.getMatchID()) > -1) { // 해당ID가 포함된 라인이 발견되면
					matchPlanTXT.add(line); // 해당 라인을 txt에 추가
				}
			}
			source_reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		// =================================================================================

		// 2-1. 회원의 이름
		String memberTXT = "";
		try {
			BufferedReader source_reader = new BufferedReader(new FileReader(Resource.MEMBER_PATH));
			String line = null;
			while ((line = source_reader.readLine()) != null) {
				if (line.indexOf(Resource.getMemberID()) > -1) { // 해당ID가 포함된 라인이 발견되면
					memberTXT += line; // 해당 라인을 txt에 추가
				}
			}
			source_reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		String[] memberData = memberTXT.split("■");
		String memberName = memberData[4];

		// 2-2. 매칭 상대의 이름
		String matchTXT = "";
		try {
			BufferedReader source_reader = new BufferedReader(new FileReader(Resource.MEMBER_PATH));
			String line = null;
			while ((line = source_reader.readLine()) != null) {
				if (line.indexOf(Resource.getMatchID()) > -1) { // 해당ID가 포함된 라인이 발견되면
					matchTXT += line; // 해당 라인을 txt에 추가
				}
			}
			source_reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		String[] matchData = matchTXT.split("■");
		String matchName = matchData[4];

		// =================================================================================

		// 3-1. 회원의 여행 출발 날짜
		String[] memberPlan = planTXT.get(0).split("■");
		Calendar startDate = Calendar.getInstance();
		startDate.set(Integer.parseInt(memberPlan[1].substring(0, 4)),
				Integer.parseInt(memberPlan[1].substring(4, 6)) - 1, Integer.parseInt(memberPlan[1].substring(6)));
		
		// 3-2. 매칭 상대의 여행 출발 날짜
		String[] matchPlan = matchPlanTXT.get(0).split("■");
		Calendar matchStartDate = Calendar.getInstance();
		matchStartDate.set(Integer.parseInt(matchPlan[1].substring(0, 4)),
				Integer.parseInt(matchPlan[1].substring(4, 6)) - 1, Integer.parseInt(matchPlan[1].substring(6)));
	
		// =================================================================================

		// 4-1. 회원의 여행 돌아오는 날짜
		memberPlan = planTXT.get(planTXT.size() - 1).split("■");
		Calendar endDate = Calendar.getInstance();
		endDate.set(Integer.parseInt(memberPlan[1].substring(0, 4)),
				Integer.parseInt(memberPlan[1].substring(4, 6)) - 1, Integer.parseInt(memberPlan[1].substring(6)));
		
		// 4-2. 매칭 상대의 여행 돌아오는 날짜
		matchPlan = matchPlanTXT.get(matchPlanTXT.size() - 1).split("■");
		Calendar matchEndDate = Calendar.getInstance();
		matchEndDate.set(Integer.parseInt(matchPlan[1].substring(0, 4)),
				Integer.parseInt(matchPlan[1].substring(4, 6)) - 1, Integer.parseInt(matchPlan[1].substring(6)));
		
		// ==================================================================================================================

		// 일정표 출력
		System.out.println("\t\t--------------------------------------------------------------------------");
		System.out.print("\t\t███╗   ███╗ █████╗ ████████╗ ██████╗██╗  ██╗██╗███╗   ██╗ ██████╗ \r\n" + 
				"\t\t████╗ ████║██╔══██╗╚══██╔══╝██╔════╝██║  ██║██║████╗  ██║██╔════╝ \r\n" + 
				"\t\t██╔████╔██║███████║   ██║   ██║     ███████║██║██╔██╗ ██║██║  ███╗\r\n" + 
				"\t\t██║╚██╔╝██║██╔══██║   ██║   ██║     ██╔══██║██║██║╚██╗██║██║   ██║\r\n" + 
				"\t\t██║ ╚═╝ ██║██║  ██║   ██║   ╚██████╗██║  ██║██║██║ ╚████║╚██████╔╝\r\n" + 
				"\t\t╚═╝     ╚═╝╚═╝  ╚═╝   ╚═╝    ╚═════╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝ ╚═════╝ \r\n" + 
				"                                                                  \r\n" + 
				"\t\t███████╗ ██████╗██╗  ██╗███████╗██████╗ ██╗   ██╗██╗     ███████╗ \r\n" + 
				"\t\t██╔════╝██╔════╝██║  ██║██╔════╝██╔══██╗██║   ██║██║     ██╔════╝ \r\n" + 
				"\t\t███████╗██║     ███████║█████╗  ██║  ██║██║   ██║██║     █████╗   \r\n" + 
				"\t\t╚════██║██║     ██╔══██║██╔══╝  ██║  ██║██║   ██║██║     ██╔══╝   \r\n" + 
				"\t\t███████║╚██████╗██║  ██║███████╗██████╔╝╚██████╔╝███████╗███████╗ \r\n" + 
				"\t\t╚══════╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═════╝  ╚═════╝ ╚══════╝╚══════╝ \n");
		System.out.println("\t\t--------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t   [매칭 상대 일정표]\n");
		System.out.printf("\t\t\t\t\t%s님과 %s님의 매칭된 일정입니다.\n\n", memberName, matchName);
		System.out.println("\t=========================================\t=========================================");
		System.out.printf("\t\t  [%s 회원님의 여행일정]\t\t\t\t  [%s 회원님의 여행일정]\n\t\t %tF ~ %tF\t\t\t %tF ~ %tF\n", memberName,
				matchName, startDate, endDate, matchStartDate, matchEndDate);
		System.out.println("\t=========================================\t=========================================");

		int compareIndex = 0;

		// 여행 일수 만큼 반복
		for (int i = 0; i < planTXT.size(); i++) {

			// 둘 간에 공통되는 사항이 있는지 체크
			boolean flag = true;
			
			ArrayList<String> sameTheme = new ArrayList<String>(); // 서로간에 공통되는 테마들
			
		//======================================================================
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String myDateTemp = format.format(startDate.getTime());
			String matchDateTemp = format.format(matchStartDate.getTime());
			
			int myDate = Integer.parseInt(myDateTemp);
			int matchDate = Integer.parseInt(matchDateTemp);
		//======================================================================

			if (myDate < matchDate) {
				
				System.out.printf("\t(%d일차) %tF\n", i + 1, startDate);
				System.out.println("\t-----------------------------------------");
				System.out.println("\t번호\t항목\t주요일정");
				System.out.println("\t-----------------------------------------");

				// 테마 출력
				String[] memberTheme = planTXT.get(i).split("■"); // 1일차에 저장한 테마부터 차례로 불러옵니다

				if (i == 0) {

					// 테마 식별자는 2번지부터 시작 -> 마지막 교통편 식별자는 제외
					for (int j = 2; j < memberTheme.length - 1; j++) {

						String themeKeyword = memberTheme[j]; // 테마 식별자

						// 키워드가 들어있는 테마 리스트를 찾고 키워드에 해당하는 항목을 불러온다
						Theme theme = new Theme();
						String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
						String category = themeTXT[themeTXT.length - 1];
						String detail = themeTXT[1];

						System.out.printf("\t%03d\t%s\t%s\n", j - 1, category, detail);

					}

				} else if (i == planTXT.size() - 1) {

					for (int j = 2; j < memberTheme.length - 1; j++) {

						String themeKeyword = memberTheme[j];

						Theme theme = new Theme();
						String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
						String category = themeTXT[themeTXT.length - 1];
						String detail = themeTXT[1];

						System.out.printf("\t%03d\t%s\t%s\n", j - 1, category, detail);

					}

				} else {

					// 교통편이 저장되어 있지 않은 날
					for (int j = 1; j < memberTheme.length - 1; j++) {

						String themeKeyword = memberTheme[j + 1];

						Theme theme = new Theme();
						String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
						String category = themeTXT[themeTXT.length - 1];
						String detail = themeTXT[1];

						System.out.printf("\t%03d\t%s\t%s\n", j, category, detail);

					}

				}

				System.out.println("\t=========================================");

				startDate.add(Calendar.DATE, 1);
				
			} else {

				while (flag == true) {

					String[] myCompare = planTXT.get(i).split("■");				
					String[] matchCompare = matchPlanTXT.get(compareIndex).split("■");

					if (myCompare[1].equals(matchCompare[1])) {

						// 비교 시작
						for (int j = 2; j < myCompare.length - 1; j++) {
							for (int k = 2; k < matchCompare.length - 1; k++) {

								String myKeyword = myCompare[j]; // 나의 테마 식별자
								String matchKeyword = matchCompare[k]; // 매칭 상대의 테마 식별자
								if (myKeyword.equals(matchKeyword)) {
									sameTheme.add(matchKeyword);
								}

							} // k for
						} // j for

						flag = false;

					} else {
						
						matchStartDate.add(Calendar.DATE, 1);
						compareIndex++;

						if (compareIndex >= matchPlanTXT.size()) {
							
							for (int m=i; m < planTXT.size(); m++) {
								
								System.out.printf("\t(%d일차) %tF\n", i + 1, startDate);
								System.out.println("\t-----------------------------------------");
								System.out.println("\t번호\t항목\t주요일정");
								System.out.println("\t-----------------------------------------");

								// 테마 출력
								String[] memberTheme = planTXT.get(m).split("■"); // 1일차에 저장한 테마부터 차례로 불러옵니다

								if (m == 0) {

									// 테마 식별자는 2번지부터 시작 -> 마지막 교통편 식별자는 제외
									for (int j = 2; j < memberTheme.length - 1; j++) {

										String themeKeyword = memberTheme[j]; // 테마 식별자

										// 키워드가 들어있는 테마 리스트를 찾고 키워드에 해당하는 항목을 불러온다
										Theme theme = new Theme();
										String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
										String category = themeTXT[themeTXT.length - 1];
										String detail = themeTXT[1];

										System.out.printf("\t%03d\t%s\t%s\n", j - 1, category, detail);

									}

								} else if (m == planTXT.size() - 1) {

									for (int j = 2; j < memberTheme.length - 1; j++) {

										String themeKeyword = memberTheme[j];

										Theme theme = new Theme();
										String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
										String category = themeTXT[themeTXT.length - 1];
										String detail = themeTXT[1];

										System.out.printf("\t%03d\t%s\t%s\n", j - 1, category, detail);

									}

								} else {

									// 교통편이 저장되어 있지 않은 날
									for (int j = 1; j < memberTheme.length - 1; j++) {

										String themeKeyword = memberTheme[j + 1];

										Theme theme = new Theme();
										String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
										String category = themeTXT[themeTXT.length - 1];
										String detail = themeTXT[1];

										System.out.printf("\t%03d\t%s\t%s\n", j, category, detail);

									}

								}

								System.out.println("\t=========================================");
								startDate.add(Calendar.DATE, 1);
								
							}

							flag = false;
							goStop = "stop";
							break;
							
						} // if (compareIndex >= matchPlanTXT.size())
						
					}

				} // while문 끝

				if (goStop.equals("stop")) {
					break;
				}
				
		//=================================================================================================================
		
				if (!sameTheme.isEmpty()) {

					System.out.printf("\t(%d일차) %tF\t\t\t\t(%d일차) %tF\n", i + 1, startDate, compareIndex + 1,
							matchStartDate);
					System.out.println(
							"\t-----------------------------------------\t-----------------------------------------");
					System.out.println("\t번호\t항목\t주요일정\t\t\t\t번호\t항목\t주요일정");
					System.out.println(
							"\t-----------------------------------------\t-----------------------------------------");

					startDate.add(Calendar.DATE, 1);

					String matchDetail = "";

					// 테마 출력
					String[] memberTheme = planTXT.get(i).split("■"); // 1일차에 저장한 테마부터 차례로 불러옵니다

					// 첫째 날 (교통편 포함)
					if (i == 0) {

						// 테마 식별자는 2번지부터 시작 -> 마지막 교통편 식별자는 제외
						for (int j = 2; j < memberTheme.length - 1; j++) {

							boolean matchFlag = false;
							String themeKeyword = memberTheme[j]; // 테마 식별자

							// 내 키워드
							Theme theme = new Theme();
							String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
							String myCategory = themeTXT[themeTXT.length - 1];
							String myDetail = themeTXT[1];

							System.out.printf("\t%03d\t%s\t%-8s", j - 1, myCategory, myDetail);

							for (int k = 0; k < sameTheme.size(); k++) {

								// 매칭 상대 키워드
								themeTXT = theme.pathSetter(sameTheme.get(k)).split("■");
								String matchCategory = themeTXT[themeTXT.length - 1];
								matchDetail = themeTXT[1];

								if (themeKeyword.equals(sameTheme.get(k))) {

									System.out.printf("%4s", "★");
									System.out.printf("\t\t\t%03d\t%s\t%8s", k + 1, matchCategory, matchDetail);
									System.out.printf("%s\n", "★");
									matchFlag = true;
									break;

								} else {
									continue;
								}

							} // k for

							if (matchFlag == false) {
								System.out.println();
							}

						} // j for

					}

					// 마지막 날 (교통편 포함)
					else if (i == planTXT.size() - 1) {

						for (int j = 2; j < memberTheme.length - 1; j++) {

							boolean matchFlag = false;
							String themeKeyword = memberTheme[j];

							Theme theme = new Theme();
							String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
							String myCategory = themeTXT[themeTXT.length - 1];
							String myDetail = themeTXT[1];

							System.out.printf("\t%03d\t%s\t%-8s", j - 1, myCategory, myDetail);

							for (int k = 0; k < sameTheme.size(); k++) {

								// 매칭 상대 키워드
								themeTXT = theme.pathSetter(sameTheme.get(k)).split("■");
								String matchCategory = themeTXT[themeTXT.length - 1];
								matchDetail = themeTXT[1];

								if (themeKeyword.equals(sameTheme.get(k))) {

									System.out.printf("%4s", "★");
									System.out.printf("\t\t\t%03d\t%s\t%s ★\n", k + 1, matchCategory, matchDetail);
									matchFlag = true;
									break;

								} else {
									continue;
								}

							} // k for

							if (matchFlag == false) {
								System.out.println();
							}

						}

					}

					// 그 외 (교통편 없는 중간 날짜)
					else {

						for (int j = 1; j < memberTheme.length - 1; j++) {

							boolean matchFlag = false;
							String themeKeyword = memberTheme[j + 1];

							Theme theme = new Theme();
							String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
							String myCategory = themeTXT[themeTXT.length - 1];
							String myDetail = themeTXT[1];

							System.out.printf("\t%03d\t%s\t%-8s", j, myCategory, myDetail);

							for (int k = 0; k < sameTheme.size(); k++) {

								// 매칭 상대 키워드
								themeTXT = theme.pathSetter(sameTheme.get(k)).split("■");
								String matchCategory = themeTXT[themeTXT.length - 1];
								matchDetail = themeTXT[1];

								if (themeKeyword.equals(sameTheme.get(k))) {

									System.out.printf("%4s", "★");
									System.out.printf("\t\t\t%03d\t%s\t%s ★\n", k + 1, matchCategory, matchDetail);
									matchFlag = true;
									break;

								} else {
									continue;
								}

							} // k for

							if (matchFlag == false) {
								System.out.println();
							}

						}

					}

					if (!matchDetail.isEmpty()) {
						System.out.println(
								"\t=========================================\t=========================================");
					} else {
						System.out.println("\t=========================================");
					}

				} else { // 공통사항이 없을 경우

					System.out.printf("\t(%d일차) %tF\n", i + 1, startDate);
					System.out.println("\t-----------------------------------------");
					System.out.println("\t번호\t항목\t주요일정");
					System.out.println("\t-----------------------------------------");

					startDate.add(Calendar.DATE, 1);

					// 테마 출력
					String[] memberTheme = planTXT.get(i).split("■"); // 1일차에 저장한 테마부터 차례로 불러옵니다

					if (i == 0) {

						// 테마 식별자는 2번지부터 시작 -> 마지막 교통편 식별자는 제외
						for (int j = 2; j < memberTheme.length - 1; j++) {

							String themeKeyword = memberTheme[j]; // 테마 식별자

							// 키워드가 들어있는 테마 리스트를 찾고 키워드에 해당하는 항목을 불러온다
							Theme theme = new Theme();
							String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
							String category = themeTXT[themeTXT.length - 1];
							String detail = themeTXT[1];

							System.out.printf("\t%03d\t%s\t%s\n", j - 1, category, detail);

						}

					} else if (i == planTXT.size() - 1) {

						for (int j = 2; j < memberTheme.length - 1; j++) {

							String themeKeyword = memberTheme[j];

							Theme theme = new Theme();
							String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
							String category = themeTXT[themeTXT.length - 1];
							String detail = themeTXT[1];

							System.out.printf("\t%03d\t%s\t%s\n", j - 1, category, detail);

						}

					} else {

						// 교통편이 저장되어 있지 않은 날
						for (int j = 1; j < memberTheme.length - 1; j++) {

							String themeKeyword = memberTheme[j + 1];

							Theme theme = new Theme();
							String[] themeTXT = theme.pathSetter(themeKeyword).split("■");
							String category = themeTXT[themeTXT.length - 1];
							String detail = themeTXT[1];

							System.out.printf("\t%03d\t%s\t%s\n", j, category, detail);

						}

					}

					System.out.println("\t=========================================");

				}

			} // else문 끝

		} // 여행 일수 for문

		// ==================================================================================================================

		System.out.println("\n\t[0] 세부 일정표 보기");
		System.out.println("\t[#] 일정표 메뉴로 돌아가기");
		System.out.println("\t[*] 메인메뉴로 돌아가기");
		System.out.println("\t---------------------------------------------------------------------------------------");
		System.out.println("\t[ ] 안의 번호를 입력하세요");
		System.out.print("\t선택 : ");

		String select = scanner.nextLine();
		return select;

	}// printMatchSchedule 메소드

}// MatchSchedule 클래스
