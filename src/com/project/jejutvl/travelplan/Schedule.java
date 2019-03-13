package com.project.jejutvl.travelplan;

import java.util.Scanner;

class Schedule {
	
	public static Scanner scan;
	private static String name = "홍길동";
	
	static {
		scan = new Scanner(System.in);
	}
	
	// !] 날짜는 나중에 수정
	private static String startDate = "2019-03-06";
	private static String finishDate = "2019-03-09";
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		Schedule.name = name;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		Schedule.startDate = startDate;
	}
	
	public String getFinishDate() {
		return finishDate;
	}
	
	public void setFinishDate(String finishDate) {
		Schedule.finishDate = finishDate;
	}

	
}
