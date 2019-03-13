package com.project.jejutvl.homescreen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.project.jejutvl.newschedule.TravelDate;
import com.project.jejutvl.travelplan.TravelPlan;

public class IDSkdCheck {

	Scanner scan = new Scanner(System.in);

	public void idSkdCheck() throws Exception {
		// TODO : 회원가입 후, 그 회원 이름으로 파일 하나가 만들어질 것임
		// TODO : 그 파일에 아무런 내용이 없으면 새로운 일정을 만들라는 메시지가 나와야 한다

		// STEP 01. 일정더미.dat 파일에서 데이터 읽어오기
		String tempTXT = "";

		try {
			BufferedReader source_reader = new BufferedReader(new FileReader(Resource.TRAVEL_PATH));
			String line = null;
			while ((line = source_reader.readLine()) != null) {
				if (line.indexOf(Resource.getMemberID()) > -1) { // 해당ID가 포함된 라인이 발견되면
					Resource.planTXT.add(line); // 해당 라인을 txt에 추가
					tempTXT += line + "\r\n";
				}
			}
			source_reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		// STEP 02. 회원정보.dat 파일에서 데이터 읽어오기

		try {
			BufferedReader source_reader = new BufferedReader(new FileReader(Resource.MEMBER_PATH));
			String line = null;
			while ((line = source_reader.readLine()) != null) {
				if (line.indexOf(Resource.getMemberID()) > -1) { // 해당ID가 포함된 라인이 발견되면
					Resource.memberTXT += line; // 해당 라인을 txt에 추가
				}
			}
			source_reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		// STEP 03. 해당 아이디로 만든 일정표가 있는지 없는지 체크
		if (tempTXT.indexOf(Resource.getMemberID()) > -1) {

			System.out.println("\n\t\t\t\t여행일정표 메뉴로 이동합니다.\n\t\t\t\tENTER키를 눌러 진행해주세요.");
			String next = scan.nextLine();

			Cls.clearScreen();
			System.out.println("\t\t\t\t\tLOADING.....");

			Thread.sleep(1000);
			Cls.clearScreen();

			// 일정표 메뉴로 이동
			TravelPlan tp = new TravelPlan();
			tp.start();

		} else { // 만약에 해당 ID가 없을 경우

			System.out.println("\n\t\t\t\t일정표가 없습니다.\n\t\t\t\t새로운 일정을 만들어주세요.");
			System.out.println("\t\t\t\tENTER키를 눌러 진행해주세요.");
			String next = scan.nextLine();

			Cls.clearScreen();
//			System.out.println("\t\t\t\t\tLOADING.....");
//			Thread.sleep(1000);
//			NamePot.clearScreen();

			// 새 일정표 작성 화면으로 이동
			TravelDate td = new TravelDate();
			td.start();
		}

	} // Method : idSkdCheck

}
