package com.project.jejutvl.homescreen;

import java.util.ArrayList;
import java.util.HashMap;

public class Resource {
	
	// 항공편 리스트
	public final static String FLIGHT_DEPARTURE_PATH;
	public final static String FLIGHT_RETURN_PATH;
	
	// 배편 리스트
	public final static String SHIP_DEPARTURE_PATH;
	public final static String SHIP_RETURN_PATH;
	
	// 회원 정보 리스트
	public final static String MEMBER_PATH;
	
	// 일정 더미 리스트
	public final static String TRAVEL_PATH;
	
	// 테마
	public final static String CAFE_PATH;
	public final static String FOOD_PATH;
	public final static String PLACE_PATH;
	public final static String STAY_PATH;
	public final static String ACTI_PATH;
	
	// 비용 관리 리스트
	public final static String PRICE_PATH;	
	
	//매칭 상대 정보 리스트
	public static final String MATCH_PATH;
	
	//메시지 파일 저장 경로
	public static final String TOTAL_MESSAGE;
	
	static {
		FLIGHT_DEPARTURE_PATH = ".\\data\\교통편, 테마\\교통_항공편_가는날.dat";
		FLIGHT_RETURN_PATH = ".\\data\\교통편, 테마\\교통_항공편_오는날.dat";
		
		SHIP_DEPARTURE_PATH = ".\\data\\교통편, 테마\\교통_배편_가는날.dat";
		SHIP_RETURN_PATH = ".\\data\\교통편, 테마\\교통_배편_오는날.dat";
		
		MEMBER_PATH = ".\\data\\더미\\회원정보.dat";
		TRAVEL_PATH = ".\\data\\더미\\일정더미.dat";
		
		CAFE_PATH = ".\\data\\교통편, 테마\\테마_카페_리스트.dat";
		FOOD_PATH = ".\\data\\교통편, 테마\\테마_맛집_리스트.dat";
		PLACE_PATH = ".\\data\\교통편, 테마\\테마_명소_리스트.dat";
		STAY_PATH = ".\\data\\교통편, 테마\\테마_숙박_리스트.dat";
		ACTI_PATH = ".\\data\\교통편, 테마\\테마_액티비티_리스트.dat";
		
		PRICE_PATH = ".\\data\\price.dat";
		
		MATCH_PATH = ".\\data\\매칭완료.txt";
		
		TOTAL_MESSAGE = ".\\data\\totalmessage.dat";
	}
		
//-----------------------------------------------------------------------------------------------------
	
	//회원의 ID
	public static String memberID = "";
	
	//매칭된 상대방의 ID
	public static String matchID = "";
	
	//회원정보dat 파일에서 해당 ID로 검색된 회원의 데이터를 담기 위한 변수
	private static String memberTXT = "";	
	
	// 일정더미.dat 파일에 해당 ID로 저장된 일정을 담기 위한 변수
	public static ArrayList<String> planTXT = new ArrayList<String>();

	

	public static String getMemberID() {
		return memberID;
	}

	public static void setMemberID(String memberID) {
		Resource.memberID = memberID;
	}

	public static String getMatchID() {
		return matchID;
	}

	public static void setMatchID(String matchID) {
		Resource.matchID = matchID;
	}	
	
	public static String getMemberTXT() {
		return memberTXT;
	}

	public static void setMemberTXT(String memberTXT) {
		Resource.memberTXT = memberTXT;
	}
	
//-----------------------------------------------------------------------------------------------------	

	//프로그램 진행 상에 필요한 정적 데이터들
	public static String startDate = ""; //출발 날짜
	public static String endDate = ""; //돌아올 날짜
	
	public static String departureCode = ""; // 출발 교통편의 일련번호
	public static String returnCode = ""; // 돌아올 교통편의 일련번호
		
	public static long interval = 0; //총 몇 박 몇 일인지 카운트하기 위한 변수
	public static int dateCount = 0; //하루 일정을 저장할 때 마다 1씩 카운트 -> interval과 값이 동일해지면 최종 저장 -> 메인메뉴로 복귀
	
	public static String dateSelect = ""; //사용자가 선택한 날짜(1일차, 2일차, ...)
	public static String themePath = ""; //사용자가 선택한 테마의 dat파일 경로
	
	public static HashMap<Integer, String> memberTheme; //날짜별로 선택한 테마들
	public static ArrayList<String> save; //실제로 dat파일에 저장할 데이터들
	
	static {
		memberTheme = new HashMap<Integer, String>();
		save = new ArrayList<String>();
	}
	
	
	
	public static String getStartDate() {
		return startDate;
	}

	public static void setStartDate(String startDate) {
		Resource.startDate = startDate;
	}

	public static String getEndDate() {
		return endDate;
	}

	public static void setEndDate(String endDate) {
		Resource.endDate = endDate;
	}

	public static String getDepartureCode() {
		return departureCode;
	}

	public static void setDepartureCode(String departureCode) {
		Resource.departureCode = departureCode;
	}

	public static String getReturnCode() {
		return returnCode;
	}

	public static void setReturnCode(String returnCode) {
		Resource.returnCode = returnCode;
	}

	public static long getInterval() {
		return interval;
	}

	public static void setInterval(long interval) {
		Resource.interval = interval;
	}

	public static int getDateCount() {
		return dateCount;
	}

	public static void setDateCount(int dateCount) {
		Resource.dateCount = dateCount;
	}

	public static String getDateSelect() {
		return dateSelect;
	}

	public static void setDateSelect(String dateSelect) {
		Resource.dateSelect = dateSelect;
	}

	public static String getThemePath() {
		return themePath;
	}

	public static void setThemePath(String themePath) {
		Resource.themePath = themePath;
	}	

	public static HashMap<Integer, String> getMemberTheme() {
		return memberTheme;
	}

	public static void setMemberTheme(HashMap<Integer, String> memberTheme) {
		Resource.memberTheme = memberTheme;
	}

	public static ArrayList<String> getSave() {
		return save;
	}

	public static void setSave(ArrayList<String> save) {
		Resource.save = save;
	}

}
