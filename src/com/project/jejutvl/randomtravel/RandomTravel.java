package com.project.jejutvl.randomtravel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

public class RandomTravel {
	
//	public static Scanner scan = new Scanner(System.in);
	public static String[] condition = new String[3]; // 조건을 저장해놓은 배열 (최소나이, 최대나이, 성별 순)
	public static ArrayList<String> idMatched = new ArrayList<String>(); // 조건에 맞는 ID를 저장해놓은 배열
	public static ArrayList<String> allMatched = new ArrayList<String>(); // 모든 조건이 맞는 회원의 ID를 저장해놓은 배열	
	public static ArrayList<String> matchedMemberSchedule = new ArrayList<String>(); // 입력 조건 맞는 아이디와 일치하는 회원들의 전체 일정 배열
	public static ArrayList<String> dateMatched = new ArrayList<String>(); // 입력조건+날짜 같은 기존회원 배열
	public static ArrayList<Information> thisMemberTotalInfo = new ArrayList<Information>(); // 로그인한 회원의 정보+일정을 저장해둔 배열
	public static ArrayList<Information> finalMatching = new ArrayList<Information>(); // 최종 매칭 가능한 회원의 정보+일정을 저장해둔 배열
	
	public static ArrayList<String> matchingList = new ArrayList<String>();
//	public static ArrayList<String> choiceList = new ArrayList<String>();
	public static HashMap<String, String> choiceList = new HashMap<String, String>();
	
	
	/**
	 * 랜덤 여행 메인 화면 메소드
	 * @throws InterruptedException 
	 */
	public static void start() throws InterruptedException {
						
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.println("\t\t[  랜  덤  여  행     ]\t\t");
		System.out.println();
		System.out.println("[1] 매칭 상대 찾기");
		System.out.println("[2] 매칭 상대 프로필 보기");
		System.out.println("[*] 홈 화면으로 돌아가기");
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("[ ] 안의 번호를 입력해주세요.");
		System.out.print("입력 : ");
		String select = scan.nextLine();
		
		
		switch (select) {
		
			case "1" :
				// 매칭 상대 찾기 
				printFindPartner(); // 매칭 조건 입력 화면
				break;
				
			case "2" :
				// 매칭 상대 프로필 보기
				printMatchingProfile();
				break;
				
			case "*" :
				// 홈 화면으로 돌아가기
				break;
				
			default :
				System.out.println("잘못 입력하셨습니다. [ ] 안의 번호만 입력해주세요.");
				System.out.println();
				again();
				start();
				break;
		}
		
	}
	
	
	
	
	/** 매칭 0
	 * 현재 로그인한 회원의 정보 + 일정을 배열에 저장하는 메소드
	 */
	public static void thisMember() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(Path.fakeInfo));		
			String line = null;
			
			
			while((line = reader.readLine()) != null) {
			
				String[] thisMember = line.split("■");
				
				Information thisMemberInfo = new Information();
								
				thisMemberInfo.setId(thisMember[0]);
				thisMemberInfo.setDate(thisMember[1]);
				
				String day ="";
				for (int i=2; i<thisMember.length; i++) {
					day += thisMember[i] + "■";
				}
				thisMemberInfo.setDaySchedule(day);				
				
				thisMemberTotalInfo.add(thisMemberInfo);

			}
			
			BufferedReader reader2 = new BufferedReader(new FileReader(Path.info));	
			String line2 = null;

			while((line2 = reader2.readLine()) != null) {
				
				for (int i=0; i<thisMemberTotalInfo.size(); i++) {
					
					if (line2.contains(thisMemberTotalInfo.get(i).getId())) {
						
						String[] thisPrivate = line2.split("■");
						
						Information info = thisMemberTotalInfo.get(i);
						info.setBirthYear(thisPrivate[2]);
						info.setGender(thisPrivate[3]);
						info.setName(thisPrivate[4]);
						
						thisMemberTotalInfo.set(i, info);
						
					}						
				}
			}
						
			reader.close();
			reader2.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
	}//thisMember()
	
	
	
	
	
	
	
	/** 매칭1
	 * 매칭 조건 입력 화면 출력 메소드
	 * @throws InterruptedException 
	 */
	public static void printFindPartner() throws InterruptedException {
		
		System.out.println();
		System.out.println(" 조건 입력 화면 "); // TODO 꾸미기
		
		printFindPartnerAge(); // 나이 조건 설정 화면
		
	}//printFindPartner
	
	
	//TODO 나이입력조건 변경
	/** 매칭 1-1
	 * 매칭 조건 중 나이를 입력하는 화면을 출력하는 메소드
	 * @throws InterruptedException 
	 */
	public static void printFindPartnerAge() throws InterruptedException {
				
		Scanner scan = new Scanner(System.in);
		
		// 만나이로 입력받을 것
		System.out.println("매칭을 원하는 나이를 '만 나이'로 입력해주세요.");
		System.out.println();
		
		System.out.print("최소 나이 : ");
		String minAge = scan.nextLine();
		
		System.out.print("최대 나이 : ");
		String maxAge = scan.nextLine();
		
		System.out.println();
		
		
		
		// String으로 입력받은 최소/최대 나이가 무관(#)일 경우, 최소나이는 0, 최대 나이는 1000으로 설정 후 진행
		if (minAge.equals("#")) {
			minAge = "0";
		}
		
		if (maxAge.equals("#")) {
			maxAge = "1000";
		}
		
	
		
		boolean b = false;
		
		
		for (int i=0; i<minAge.length(); i++) {
			
			if ((int)minAge.charAt(i) > (int)'9' || (int)minAge.charAt(i) < (int)'0' ) {
				
				b = true;
				break;
			}	
		}	
		
		
		for (int i=0; i<maxAge.length(); i++) {
			
			if ((int)maxAge.charAt(i) > (int)'9' || (int)maxAge.charAt(i) < (int)'0' ) {
				
				b = true;
				break;
			}	
			
		}

		
		
		if (b) {
			
			System.out.println("나이는 '숫자'로만 입력해주세요.");
			System.out.println();
			again();
			System.out.println();
			printFindPartnerAge(); // 재귀
			
			
		} else if (Integer.parseInt(minAge) > Integer.parseInt(maxAge)) {
			
			System.out.println("최소나이는 최대나이를 넘을 수 없습니다.");
			System.out.println();
			again();
			System.out.println();
			System.out.println();
			printFindPartnerAge(); // 재귀
		
		} else if (Integer.parseInt(minAge) > 15 
				&& Integer.parseInt(minAge) < 100
				&& Integer.parseInt(maxAge) > 15 
				&& Integer.parseInt(maxAge) < 100) {

			condition[0] = minAge;
			condition[1] = maxAge;
			
			printFindPartnerGender(); // 모든 조건이 통과하면 성별 입력으로 이동
			
		} else {
			
			System.out.println("나이 입력이 올바르지 않습니다.");
			System.out.println();
			again();
			System.out.println();
			System.out.println();
			printFindPartnerAge(); // 재귀
			
		}	
		
	}//printFindPartnerAge()
	
	
	
	/** 매칭 1-2
	 * 매칭 조건 중 성별을 입력하는 화면을 출력하는 메소드
	 * @throws InterruptedException 
	 */
	public static void printFindPartnerGender() throws InterruptedException {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("원하는 성별을 선택해주세요.");
		System.out.println("남자:1, 여자:2, 무관:#");
		System.out.print("입력  : ");
		String gender = scan.nextLine();
		
		
		if (gender.equals("1") || gender.equals("2") || gender.equals("#")) {
			condition[2] = gender;
			
			printConditionComplete(); // 조건 입력 완료 메시지 출력
			
			
		} else {
			System.out.println("성별 입력이 올바르지 않습니다.");
			again();
			System.out.println();
			printFindPartnerGender(); // 재귀
		}
			
	}//printFindPartnerGender()
	
	
	
	/** 매칭 1-3
	 * 조건 입력 완료 메시지 출력 메소드
	 * @throws InterruptedException
	 */
	private static void printConditionComplete() throws InterruptedException {

		System.out.println("입력 완료!");
		System.out.println("매칭 가능한 상대를 검색합니다.");
		
		Thread.sleep(500);
		System.out.println("완벽한 파트너를 찾는 중");
		Thread.sleep(1500);
		
		thisMember(); // 현재 로그인한 회원의 정보를 저장
		checkCondition(condition); // 입력된 조건을 변수로 넣어 조건에 맞는 기존 회원 아이디를 반환받음
		checkSchedule(idMatched); 
		matchingAvailableList(); // 모든 조건 만족하는 회원 저장
		printMatchingList(); // 매칭 가능한 회원 목록 출력
		
	}
	
	
	
	/** 매칭 2-1
	 * 매칭 조건에 맞는 회원을 검색하고 아이디를 반환해주는 메소드
	 * @param condition 조건이 들어있는 배열 ([0]:최소나이, [1]:최대나이, [2]:성별)
	 * @return 회원의 아이디 배열
	 */
	public static ArrayList<String> checkCondition(String[] condition) {
		
		if (condition[2].equals("1")) {
			condition[2] = "남";
		} else if (condition[2].equals("2")) {
			condition[2] = "여";
		}
		
		Calendar now = Calendar.getInstance();		

		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(Path.info));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] info = line.split("■");
				
				// 만 나이로 계산
				int minBirthYear = now.get(Calendar.YEAR) - Integer.parseInt(condition[0]);
				int maxBirthYear = now.get(Calendar.YEAR) - Integer.parseInt(condition[1]);
				
				// 나이 검사
				if (minBirthYear >= Integer.parseInt(info[2]) 
						&& maxBirthYear <= Integer.parseInt(info[2])) {
					
					// 성별 검사
					if (condition[2].equals(info[3]) || condition[2].equals("#")) {
						idMatched.add(info[0]);
					}	
				}
			}
			
			reader.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
				
		return idMatched;
	}
	
	
	/** 2-2
	 * 매칭을 요청한 회원의 조건에 일치하는 기존회원 중, 일정까지 맞는 회원을 찾는 메소드
	 * @param idMatched 설정한 조건에 일치하는 회원의 아이디 목록
	 */
	public static void checkSchedule(ArrayList<String> idMatched) {
		
		// 기존 회원 일정 파일을 읽어서, 회원이 설정한 조건에 맞는 사람들의 일정만 matchedMemberSchedule 배열에 저장
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(Path.schedule));		

			String line = null;
			
			while((line = reader.readLine()) != null) {
				
				for (String id : idMatched) {
					
					if (line.contains(id)) {
						
						matchedMemberSchedule.add(line);
					}
				}
			}
			
			reader.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	
		
		// 조건 일치 일정표 -> 날짜 일치 검사
		for (String scheduleInfo : matchedMemberSchedule) {
			
			String[] findDate = scheduleInfo.split("■");
			String date = findDate[1];
			
			try {
				
				BufferedReader reader = new BufferedReader(new FileReader(Path.fakeInfo));		

				String line = null;
				
				while((line = reader.readLine()) != null) {
				
					String[] thisMember = line.split("■");
					String thisMemberDate = thisMember[1];
					
					if (thisMemberDate.equals(date)) {
						dateMatched.add(scheduleInfo); // 맞는 날짜 스케줄 전체를 저장
					}
					 
				
				}
				
				reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
	
		}
		
		
		// 조건 일치 + 날짜 일치 -> 일별 2개이상 중복 체크
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(Path.fakeInfo));		

			String line = null;
						
			while((line = reader.readLine()) != null) {
				
				String[] thisMember = line.split("■"); // TODO 이름 바꿔야함				

				for (String schedule : dateMatched) {

					String[] matchedMember = schedule.split("■");
									
					int count = 0;
				
					for (int j=2; j<matchedMember.length; j++) {
						
						for(int i=2; i<thisMember.length; i++) {
							
							if (thisMember[i].equals(matchedMember[j])) {
								
								count++;
							}

						}
						
						if (count >= 2) {
							
							allMatched.add(schedule);
							break;
							
						}					
					}
				}
			}				
			
			reader.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}//checkSchedule()
	
	
	
	
	/** 매칭 2-3
	 * Information 클래스를 이용해서 매칭 가능한 회원을 설정해두는 메소드
	 */
	public static void matchingAvailableList() {
		
		System.out.println();
		System.out.println("\t[       매칭 가능 목록       ]\t");
		System.out.println();
		System.out.println();
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(Path.info));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
									
				String[] memberInfo = line.split("■");
				
				for (String s : allMatched) {
					
					String[] matchedInfo = s.split("■");
					
					if (memberInfo[0].contains(matchedInfo[0])) {
						
						Information matchingInfo = new Information();
						matchingInfo.setId(memberInfo[0]);
						matchingInfo.setBirthYear(memberInfo[2]);
						matchingInfo.setGender(memberInfo[3]);
						matchingInfo.setName(memberInfo[4]);
						matchingInfo.setDate(matchedInfo[1]);
						
						String daySchedule = "";
						for(int i=2; i<matchedInfo.length; i++) {
						
							daySchedule += matchedInfo[i]+ "■";
						}
						
						matchingInfo.setDaySchedule(daySchedule);
						
						finalMatching.add(matchingInfo);
					}
				}
			}
			
			reader.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}//matchingAvailableList()
	
	
	
	/** 2-4
	 * 매칭 가능 목록 출력 메소드
	 * @throws InterruptedException 
	 */
	public static void printMatchingList() throws InterruptedException {
		
		String[] thisDay = null;
		String[] matchedDay = null;
		

		String total = "";
		
		for (Information info2 : finalMatching) {
			
			matchedDay = info2.getDaySchedule().split("■");

			for (Information info1 : thisMemberTotalInfo) {
				
				thisDay = info1.getDaySchedule().split("■");
				
				
				if (info1.getDate().equals(info2.getDate())) {

					total = String.format("%s■%s■%s■%s■"
										, info2.getId()
										, info2.getBirthYear()
										, info2.getGender()
										, info2.getDate());

					
					// 공통테마 구해서 누적 //TODO 공통인지 전체인지 확인
					for(int i=0; i<thisDay.length; i++) {
						
						total += String.format((changeThemeKor(thisDay[i]) +" "));
						
					}
					
					matchingList.add(total);
					break;	
				}
			}	
		}
		
		System.out.println("=====================================================================");
		System.out.println("번호\t아이디\t\t생년\t성별\t날짜\t\t공통테마");
		System.out.println("=====================================================================");
		
		matchingList.add(matchingList.size(), "");
		
		int num = 1;
		
		for (int i=0; i<matchingList.size()-1; i++) {
			
			if (i == 0 && num == 1) {
				
				String[] listLine1 = matchingList.get(i).split("■");
				System.out.printf("[%d]\t%s\t%s\t%s\t%s\t%s\n"
									, num
									, listLine1[0]
									, listLine1[1]
									, listLine1[2]
									, listLine1[3]
									, listLine1[4]);
				choiceList.put(num+"", listLine1[0]);
				num++;
				
				
			} else if (i > 0) {
			
			String[] listLine1 = matchingList.get(i).split("■");
			String[] listLine2 = matchingList.get(i-1).split("■");


			if (num != 1 && listLine1[0].contains(listLine2[0])) {
				
				System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n"
						, "    "
						, "         "
						, "    "
						, "    "
						, listLine1[3]
						, listLine1[4]);			
				
			} else {
				
				System.out.printf("[%d]\t%s\t%s\t%s\t%s\t%s\n"
									, num
									, listLine1[0]
									, listLine1[1]
									, listLine1[2]
									, listLine1[3]
									, listLine1[4]);
				choiceList.put(num+"", listLine1[0]);
				num++;			
			}
			
			}
		}
	
		System.out.println();
		printChoice(); // 선택 입력창 출력
		
	}//matchingListLine()
	
	

	
	/** 매칭 2-5
	 * 매칭 상대 선택 입력창 출력 메소드
	 * @throws InterruptedException 
	 */
	private static void printChoice() throws InterruptedException {
		
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.println("[*] 매칭 홈 화면으로 돌아가기");
		System.out.println();
		System.out.println("매칭을 원하는 상대의 [ ] 안의 번호를 입력하세요.");
		System.out.print("선택 : ");
		
		String select = scan.nextLine();
		
		
		if (select.equals("*")) {
			// TODO 홈화면으로 가기
			start();
		}
		
	
		if (choiceList.containsKey(select)) {
			
			System.out.println();
			System.out.printf("'%s'님을 선택하셨습니다.\n", choiceList.get(select));
			System.out.println();
			System.out.println("선택하신 회원님과 매칭을 진행하시겠습니까? ( Y / N )");
			System.out.print("입력 : ");
			
			String input = scan.nextLine();
			
				if (input.equals("Y") || input.equals("y")) {
					
					System.out.println();
					System.out.printf("'%s'님과 매칭되었습니다.", choiceList.get(select));
					System.out.println();
					

					try {
						
						BufferedWriter writer = new BufferedWriter(new FileWriter(Path.matching, true));
						
						writer.write(thisMemberTotalInfo.get(0).getId() + "■" + choiceList.get(select));
						
						writer.close();
						
					} catch (Exception e) {
						System.out.println(e.toString());
					}
					
					
					
				} else if (input.equals("N") || input.equals("n")) {
					
					again();
					printChoice();
		
				} else {
					
					System.out.println("잘못 입력하셨습니다.");
					again();
					printChoice(); //재귀
					
				}
	
		} else {
			
			System.out.println("잘못 입력하셨습니다.");
			again();
			printChoice(); //재귀
		}
		
		
		printMenu2();

		
	}//printChoice

	
	/** 매칭 3
	 * 매칭 완료 후 메뉴 출력 화면 메소드
	 * @throws InterruptedException 
	 */
	private static void printMenu2() throws InterruptedException {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("-----------------------");
		System.out.println("[0] 매칭 일정표 보기");
		System.out.println("[#] 상대 프로필 보기");
		System.out.println("-----------------------");
		System.out.println("[ ] 안의 번호를 입력해주세요.");
		System.out.print("선택 : ");
		String select = scan.nextLine();
		
		switch (select) {
		
		case "0" :
			// 매칭 일정표 보기
			break;
			
		case "#" :
			// 상대 프로필 보기
			printMatchingProfile();
			break;
			
		default :
			System.out.println("잘못 입력하셨습니다.");
			System.out.println();
			pause();
			System.out.println();
			printMenu2();
			break;
		}
		
	}//printMenu2()

	
	/** 프로필 1
	 * 매칭된 상대의 프로필을 출력하는 메소드
	 * @throws InterruptedException 
	 */
	public static void printMatchingProfile() throws InterruptedException {
		
		String matchingMember = "";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(Path.matching));
			
			String line = null;
			
			
			while ((line = reader.readLine()) != null) {
				
				if (line.contains(Path.currentMemberId)) {
					
					String[] s = line.split("■");
					matchingMember = s[1];
					
				}
				
			}
			
			reader.close();
			
			
			try {
				
				BufferedReader reader2 = new BufferedReader(new FileReader(Path.info));
				
				while ((line = reader2.readLine()) != null) {
					
					if (line.contains(matchingMember)) {
					
						
						String[] profile = line.split("■");
						
						System.out.println();
						
						System.out.println("=====================================");
						System.out.println("\t매칭 상대 프로필\t");
						System.out.println("=====================================");
						System.out.println();
						
						
						System.out.printf("아이디 : %s\n", profile[0]);
						System.out.printf("이름 : %s\n", profile[4]);		
						System.out.printf("생년 : %s\n", profile[2]);
						System.out.printf("성별 : %s\n", profile[3]);

					}
										
				}
				
				reader2.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		printProfileMenu();
			
		
	}//printMatchingProfile()
	
	
	/**
	 * 매칭 프로필 출력 후 메뉴 출력 메소드
	 * @throws InterruptedException 
	 */
	public static void printProfileMenu() throws InterruptedException {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("=============================");
		System.out.println("[1] 매칭 일정표 보기");
		System.out.println("[2] 메시지 보내기");
		System.out.println();
		System.out.println("[*] 홈 화면으로 돌아가기");
		System.out.println("-----------------------------");
		System.out.println("[ ] 안의 번호를 입력하세요.");
		System.out.print("선택 : ");
		
		String select = scan.nextLine();
		
		switch (select) {
		
			case "1" :
				// 매칭 일정표 보기
				break;
			
			case "2" :
				// 메시지 보내기
				break;
			
			case "*" :
				// 홈 화면으로 돌아가기				
				start();
				break;
		
			default :
				System.out.println("잘못 입력하셨습니다.");
				again();
				printProfileMenu();		
				break;
		}
		
		
		
	}//printProfileMenu()
	
	
	
	/**
	 * 일정 코드를 읽어서 테마를 한글로 변환해주는 메소드
	 * @param theme 테마코드
	 * @return
	 */
	public static String changeThemeKor(String theme) {
		
		
		if (theme.contains("CAFE")) {
			return "카페";
		} else if (theme.contains("FOOD")) {
			return "맛집";
		} else if (theme.contains("PLACE")) {
			return "명소";
		} else if (theme.contains("ACTI")) {
			return "액티";
		} else if (theme.contains("STAY")) {
			return "숙소";
		} else {
			return "뭔가 잘못됐따";//TODO 바꾸기
		}		
		
	}//changeThemeKor()
	

	
	private static void again() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("다시 입력하시려면 ENTER를 입력하세요.");
		scan.nextLine();
		
	}//again()
	
	
	private static void pause() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("계속 하시려면 ENTER를 입력하세요.");
		scan.nextLine();
		
	}
}
