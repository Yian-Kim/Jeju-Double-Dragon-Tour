package com.project.jejutvl.travelcost;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.project.jejutvl.homescreen.Cls;
import com.project.jejutvl.homescreen.HomeScreen;
import com.project.jejutvl.homescreen.Resource;
import com.project.jejutvl.travelplan.DetailedSchedule;

public class TravelCost {

	private static Scanner scan;
	//private static ArrayList<Cost> list;

	static {

		scan = new Scanner(System.in);
		//list = new ArrayList<Cost>();
		
	}

//---------------------------------------------------------------------------------------------------------------	

	public static void main(String[] args) throws IOException {

		try {

			try {
				BufferedReader reader = new BufferedReader(new FileReader(Resource.MEMBER_PATH));
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf(Resource.getMemberID()) > -1) { // 해당ID가 포함된 라인이 발견되면
						Resource.setMemberTXT(line); // 해당 라인을 txt에 추가
					}
				}
				reader.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			// 회원의 정보가 저장되어있는 리스트
			String[] memberData = Resource.getMemberTXT().split("■");
			String memberName = memberData[4];

	         System.out.println("\t\t-----------------------------------------------------------------");
	         System.out.print("\t\t\t████████╗██████╗  █████╗ ██╗   ██╗███████╗██╗     \r\n" + 
	         		"\t\t\t╚══██╔══╝██╔══██╗██╔══██╗██║   ██║██╔════╝██║     \r\n" + 
	         		"\t\t\t   ██║   ██████╔╝███████║██║   ██║█████╗  ██║     \r\n" + 
	         		"\t\t\t   ██║   ██╔══██╗██╔══██║╚██╗ ██╔╝██╔══╝  ██║     \r\n" + 
	         		"\t\t\t   ██║   ██║  ██║██║  ██║ ╚████╔╝ ███████╗███████╗\r\n" + 
	         		"\t\t\t   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝  ╚═══╝  ╚══════╝╚══════╝\r\n" + 
	         		"                                                  \r\n" + 
	         		"\t\t\t ██████╗ ██████╗ ███████╗████████╗                \r\n" + 
	         		"\t\t\t██╔════╝██╔═══██╗██╔════╝╚══██╔══╝                \r\n" + 
	         		"\t\t\t██║     ██║   ██║███████╗   ██║                   \r\n" + 
	         		"\t\t\t██║     ██║   ██║╚════██║   ██║                   \r\n" + 
	         		"\t\t\t╚██████╗╚██████╔╝███████║   ██║                   \r\n" + 
	         		"\t\t\t ╚═════╝ ╚═════╝ ╚══════╝   ╚═╝                   \r\n");
	         System.out.println("\t\t-----------------------------------------------------------------");
	         System.out.println("\t\t\t[여행경비]");
			System.out.println();
			System.out.printf("\t\t%s 회원님의 예상비용", memberName);
			System.out.println();
			System.out.println("\t\t=================================================================");
			System.out.println();
			System.out.println("\t\t번호\t(일차)\t날짜\t\t\t\t예상비용");
			System.out.println();
			System.out.println("\t\t================================================================");

			int num = 1;
			int total = 0;

			BufferedReader reader3 = new BufferedReader(new FileReader(Resource.PRICE_PATH));

			String line = null;
			while ((line = reader3.readLine()) != null) {

				String[] s = line.split("■");
				System.out.printf("\t\t[%d]\t%s\t%s\t\t\t  %,d\n", num, s[0], s[1], Integer.parseInt(s[3]));
				num++;
				total += Integer.parseInt(s[3]);
				System.out.println("\t\t------------------------------------------------------------------");
			}

			System.out.printf("\t\t\t\t\t총계 : %,d원\n", total);
			System.out.println("\t\t------------------------------------------------------------------");
			System.out.println();
			System.out.println("\t\t[#] 세부 일정 확인하기");
			System.out.println("\t\t[*] 메인 메뉴로 돌아가기");
			System.out.println("\t\t------------------------------------------------------------------");
			System.out.println("\t\t[ ]안의 번호를 입력하세요.");

			boolean loop = true;
			loop: while (loop) {

				System.out.print("\t\t선택 : ");
				String input = scan.nextLine();

				if (input.equals("#") || input.equals("*")) {
					
					if (input.equals("#")) {
						
						System.out.println("\n\t\t\t\t'세부 일정표'를 선택하셨습니다.\n\t\t\t\t계속하시려면 ENTER키를 눌러주세요.");
						
						String next = scan.nextLine();
						
						if (next.equals("")) {
							
							Cls.clearScreen();
							System.out.println("\t\t\t\t\tLOADING.....");
							Thread.sleep(700);
							Cls.clearScreen();
							
							
							DetailedSchedule detailedSKD = new DetailedSchedule();
							detailedSKD.start();
							break;

						}
						
					}

					if (input.equals("*")) {
						
						System.out.println("\n\t\t\t\t메인 메뉴로 돌아갑니다.\n\t\t\t\t계속하시려면 ENTER키를 눌러주세요.");
						String next = scan.nextLine();
						if (next.equals("")) {
							Cls.clearScreen();
							System.out.println("\t\t\t\tLOADING.....");
							Thread.sleep(700);
							Cls.clearScreen();
							HomeScreen.start();
							break;
						}

					}

				} else {
					
					System.out.println("\t\t# 또는 * 을 입력해주세요.");
					continue loop;

				}

			} // While loop

			reader3.close();

		} catch (Exception e) {
			
			System.out.println(e.toString());
			
		}

	} // main

//---------------------------------------------------------------------------------------------------------------

} // Class : TravelCost



