package com.project.jejutvl.newschedule;

import java.util.Random;

/**
 * 
 * @author 엄현철
 * 회원 더미 데이터를 만들기 위한 코드입니다
 *
 */

public class member_dummy {

	public static void main(String[] args) {
		
		member();		
		//plan();
		
	}//main
	
//-------------------------------------------------------------------------------------	
	
	private static void member() {
		
		//100명의 회원 데이터를 만듭니다
		int count = 100; 
		Random rnd = new Random();	
		
		String[] id1 = {"김", "나", "다", "라", "마", "바", "사", "아", "자", "차", "카"};
		String[] id2 = {"김", "나", "다", "라", "마", "바", "사", "아", "자", "차", "카"};
		String[] id3 = {"김", "나", "다", "라", "마", "바", "사", "아", "자", "차", "카"};
		
		String[] psw = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
		String[] birth = {"2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990"};	
		String[] gender = {"남", "여 "};

		
	}//member

//-------------------------------------------------------------------------------------	
	
	private static void plan() {
		
		int count = 100; 
		
		//여행 정보
		String[] id = new String[count];
		String[] date = new String[count];
		
		String[] transport = new String[count];	
		
		String[] theme1 = new String[count];
		String[] theme2 = new String[count];
		String[] theme3 = new String[count];
		String[] theme4 = new String[count];
		String[] theme5 = new String[count];
		
	}//plan

}//member_travel_plan
