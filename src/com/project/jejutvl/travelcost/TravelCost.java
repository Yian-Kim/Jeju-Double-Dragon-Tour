package com.project.jejutvl.travelcost;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TravelCost {

	private static Scanner scan;
	private static ArrayList<Cost> list;

	static {

		scan = new Scanner(System.in);
		list = new ArrayList<Cost>();
	}

	public static void main(String[] args) throws IOException {

		try {

			BufferedReader reader2 = new BufferedReader(
					new FileReader("D:\\Class\\JAVA\\jejuTvlMgmt\\dat\\price.txt"));

			String line = null;

			line = reader2.readLine();

			String[] array1 = line.split("■");

			System.out.println("\t\t\t[여행경비]");
			System.out.println();
			System.out.printf("\t\t%s 회원님의 예상비용", array1[2]);
			System.out.println();
			System.out.println("=================================================================");
			System.out.println();
			System.out.println("번호\t(일차)\t날짜\t\t\t\t예상비용");
			System.out.println();
			System.out.println("================================================================");

			int num = 1;
			int total = 0;
			
			reader2.close();
			
			BufferedReader reader3 = new BufferedReader(
					new FileReader("D:\\Class\\JAVA\\jejuTvlMgmt\\dat\\price.txt"));

			while ((line = reader3.readLine()) != null) {

				String[] s = line.split("■");
				System.out.printf("[%d]\t%s\t%s\t\t\t  %,d\n", num, s[0], s[1], Integer.parseInt(s[3]));
				num++;
				total += Integer.parseInt(s[3]);
				System.out.println("------------------------------------------------------------------");
			}

			System.out.printf("\t\t\t\t\t총계 : %,d원\n", total);
			System.out.println("------------------------------------------------------------------");
			System.out.println();
			System.out.println("[#] 세부 일정 확인하기");
			System.out.println("[*] 메뉴선택 화면으로 돌아가기");
			System.out.println("------------------------------------------------------------------");
			Thread.sleep(700);
			System.out.println("[ ]안의 번호를 입력하세요.");

			boolean loop = true;
			loop: while (loop) {

				System.out.println("선택 : ");
				String input = scan.nextLine();

				if (input.equals("#") || input.equals("*")) {
					if (input.equals("#")) {
						m1(); //일차선택 메소드로
						break;
					}

					if (input.equals("*")) {
						//메인화면 연결하기
						System.out.println("메인화면으로 돌아갑니다.");
						break;

					}

				} else {
					System.out.println("# 또는 * 을 입력해주세요.");
					continue loop;

				}

			}

			reader3.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}// main

	/**
	 * 일차 선택으로 가는 메소드
	 * @throws InterruptedException 
	 */
	private static void m1() throws InterruptedException {
		
		loop: while(true) {			
		
		System.out.println("일차 선택(번호)");
		String input2 = scan.nextLine();

		if (Pattern.matches("^[0-9]*$", input2)) {
			
			if (input2.equals("1")) {
				Thread.sleep(700);
				System.out.println("ENTER키를 누르시면 1일차 세부 일정표로 이동합니다.");
				scan.nextLine();
				break;

				// 1일차 일정으로

			} else if (input2.equals("2")) {
				Thread.sleep(700);
				System.out.println("ENTER키를 누르시면 2일차 세부 일정표로 이동합니다.");
				scan.nextLine();
				break;
				
				// 2일차 일정으로
				
			} else if (input2.equals("3")) {
				Thread.sleep(700);
				System.out.println("ENTER키를 누르시면 3일차 세부 일정표로 이동합니다.");
				scan.nextLine();
				break;
				
				// 3일차 일정으로
				
			} else if (input2.equals("4")) {
				Thread.sleep(700);
				System.out.println("ENTER키를 누르시면 4일차 세부 일정표로 이동합니다.");
				scan.nextLine();
				break;
				
				// 4일차 일정으로
				
			} else if (input2.equals("5")) {				
				Thread.sleep(700);
				System.out.println("ENTER키를 누르시면 4일차 세부 일정표로 이동합니다.");
				scan.nextLine();
				break;
				
				// 5일차 일정으로

			}

		} else {
			System.out.println("돌아가실 일차를 숫자로 입력해주세요.");
			continue loop;
		}
		
		}

	}// m1
}// class
