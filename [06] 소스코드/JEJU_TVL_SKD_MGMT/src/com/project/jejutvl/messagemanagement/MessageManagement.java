package com.project.jejutvl.messagemanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.jejutvl.homescreen.Cls;
import com.project.jejutvl.homescreen.HomeScreen;
import com.project.jejutvl.homescreen.Resource;

public class MessageManagement {

	private static UiMessageManagement ui;
	private static Scanner scan;
	private static ArrayList<Message> list;
	
	static {
		ui = new UiMessageManagement();
		scan = new Scanner(System.in);
		list = new ArrayList<Message>();

	}

//----------------------------------------------------------------------------------------------------------	
	
	//TODO : main으로 되어있는 메소드를 전부 다 start로 바꿔야 해요 (우선 여기 클래스에서만)
	public static void main(String[] args) throws IOException, Exception {

		String temp = "";
		
		try {
			BufferedReader source_reader = new BufferedReader(new FileReader(Resource.MATCH_PATH));
			String line = null;
			while ((line = source_reader.readLine()) != null) {
				if (line.indexOf(Resource.getMemberID()) > -1) {
					temp = line;
				}
			}
			source_reader.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		String[] names = temp.split("■");
		Resource.setMemberID(names[0]);
		Resource.setMatchID(names[1]);
	
	//=====================================================================================	
		
		messageLoad(); // 저장된 메시지 로딩 메소드

		boolean loop = true;

		ui.start(); // UiMessageManagement 클래스 시작

		while (loop) {

			// 메뉴 > 선택 > 실행
			ui.menu();

			switch (scan.nextLine()) {
			case "1":
				
				Cls.clearScreen();
				System.out.println("\t\t\t\t\tLOADING.....");
				Thread.sleep(700);
				Cls.clearScreen();
				list(); // 받은 메시지함 메소드
				break;
				
			case "2":
				
				Cls.clearScreen();
				System.out.println("\t\t\t\t\tLOADING.....");
				Thread.sleep(700);
				Cls.clearScreen();
				add(); // 메시지 작성하는 메소드
				save(); // 메시지 파일에 저장하는 메소드
				break;
				
			case "*":
				
				System.out.println("\n\t\t\t\t메인 메뉴로 돌아갑니다.\n\t\t\t\t계속하시려면 ENTER키를 눌러주세요.");
				
				String next = scan.nextLine();
				
				if (next.equals("")) {
					Cls.clearScreen();
					System.out.println("\t\t\t\t\tLOADING.....");
					Thread.sleep(700);
					Cls.clearScreen();
					HomeScreen.start();
					break;
				}
				
			default:
				loop = false;
				break;

			}
			
		}
		
	}//main

//----------------------------------------------------------------------------------------------------------	
	
	private static void save() {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(Resource.TOTAL_MESSAGE));

			for (Message message : list) {

				writer.write(String.format("%s■%s■%s■%s\r\n"
						, Resource.getMatchID(), Resource.getMemberID(), message.getDate(), message.getContent()));

			}
			writer.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	private static void list() throws IOException {

		System.out.println("\n\t\t--------------------------------------------------------------------------");
		System.out.print("\t\t\t███╗   ███╗███████╗███████╗███████╗ █████╗  ██████╗ ███████╗\r\n" + 
				"\t\t\t████╗ ████║██╔════╝██╔════╝██╔════╝██╔══██╗██╔════╝ ██╔════╝\r\n" + 
				"\t\t\t██╔████╔██║█████╗  ███████╗███████╗███████║██║  ███╗█████╗  \r\n" + 
				"\t\t\t██║╚██╔╝██║██╔══╝  ╚════██║╚════██║██╔══██║██║   ██║██╔══╝  \r\n" + 
				"\t\t\t██║ ╚═╝ ██║███████╗███████║███████║██║  ██║╚██████╔╝███████\r\n");
		System.out.println();
		System.out.println("\t\t--------------------------------------------------------------------------");
		System.out.println("\t\t\t\t==================================================");
		System.out.println("\t\t\t\t		받은 메시지함 		");
		System.out.println("\t\t\t\t==================================================");
		System.out.println("\t\t\t\t번호\t보낸 사람\t\t날짜\t\t     내용\t\t");
		System.out.println("\t\t\t\t==================================================");
		
		try {
		
			ArrayList<String> all = new ArrayList<String>();

			BufferedReader reader = new BufferedReader(new FileReader(Resource.TOTAL_MESSAGE));
			
			String line = null;
			
			int lineNum = 1;
			
			while ((line = reader.readLine()) != null) {
				
				if (line.contains(Resource.getMatchID())) {
					
					all.add(lineNum + "■" + line);
					lineNum++;
					
				}
			}				
			
			for (String s : all) {
				
				String[] temp = s.split("■");
				
				System.out.printf("\t\t\t\t[%s]\t%s\t%s\t%.10s\t\n", temp[0], temp[1], temp[3], temp[4]);
				System.out.println("\t\t\t\t--------------------------------------------------");
				
			}
	
			System.out.println("\t\t\t\t전문을 보시려면 [ ]안의 번호를 입력해주세요.");
			System.out.println();
			System.out.println("\t\t\t\t[0] 메시지보내기");
			System.out.println("\t\t\t\t[*] 메시지 메뉴로 돌아가기");
			System.out.println("\t\t\t\t--------------------------------------------------");
			System.out.print("\t\t\t\t선택 : ");

			String select = scan.nextLine();		
				
			if ((int)select.charAt(0) >= (int)'9' || (int)select.charAt(0) < (int)'0') {
				
				if (select.equals("*")) {
					
					System.out.println("\n\t\t\t\t메시지 메뉴로 돌아갑니다.\n\t\t\t\t계속하시려면 ENTER키를 눌러주세요.");
					
					String next = scan.nextLine();
					
					if (next.equals("")) {
						Cls.clearScreen();
						System.out.println("\t\t\t\t\tLOADING.....");
						Thread.sleep(700);
						Cls.clearScreen();
						main(null);
						
					}
					
				} else {
					
					System.out.println("\t\t\t\t잘못된 입력하셨습니다.");
					again();
					list();
				}
					
			} else {
			
			if (Integer.parseInt(select) <= all.size() || select.equals("0") || select.equals("*")) {
			
				if (select.equals("0")) {
					
					add(); // >>>>>>>>>>>>>>>>>>>>>> 메시지 작성 메소드 넘어가기
					save();
					
				} else if (select.equals("*")) {
					
					System.out.println("\n\t\t\t\t메인 메뉴로 돌아갑니다.\n\t\t\t\t계속하시려면 ENTER키를 눌러주세요.");
					
					String next = scan.nextLine();
					
					if (next.equals("")) {
						Cls.clearScreen();
						System.out.println("\t\t\t\t\tLOADING.....");
						Thread.sleep(700);
						Cls.clearScreen();
						HomeScreen.start();
					}
					
				} else {
					
					receivedMessage(select, all);
				}
			
			} else {
				
				System.out.println("\t\t\t\t잘못 입력하셨습니다. 다시 입력해 주세요.");
				scan.nextLine();
			}
			}
			reader.close();
					
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

//----------------------------------------------------------------------------------------------------------	
	
	private static void receivedMessage(String x, ArrayList<String> all) throws IOException, Exception { //>>>> TODO String x 추가 

		System.out.println("\t\t\t\t=============================");
		System.out.printf("\t\t\t\t\t메시지(%d/%d)\t\n", Integer.parseInt(x), list.size()); //>>>>> TODO 1 -> Integer.parseInt(x) 수정했음
		System.out.println("\t\t\t\t=============================");
		
		for (String s : all) {
			
			String[] temp = s.split("■");
			
			if (temp[0].equals(x)) {
				
			
				System.out.println("\t\t\t\t보낸 사람 : " + temp[1]);
				
				System.out.println("\t\t\t\t날짜 : " + temp[3]);
				
				System.out.print("\t\t\t\t내용 : ");	
	      
		 String Mread = "";
	      
	      //int index = 0;

	      for (int i=0; i <temp[4].length(); i= i+20) {
	         
	    	 if (temp[4].length() < 20) {
	    		 
	    		 Mread = temp[4].substring(i);
	    		 break;
	    		 
	    	 } else if (i+20 > temp[4].length()) {
	            
	            Mread += temp[4].substring(i);
	            break;
	            
	         } else {
	      
	            Mread += temp[4].substring(i, i+20) + "\r\n";
	         }      
	      }         
	      
	      System.out.printf("\t\t\t\t" + Mread);
	      System.out.println();
			}
		}	
		
			System.out.println("\t\t\t\t-----------------------------");
			//System.out.println("\t\t\t\t[0] 답장하기");
			System.out.println("\t\t\t\t[*] 받은 메시지함으로 돌아가기");
			System.out.println("\t\t\t\t-----------------------------");
			System.out.print("\t\t\t\t선택 : ");
			String select = scan.nextLine();

			if (select.equals("*")) {
				list();
				//	reply(all.get(Integer.parseInt(x))); // 받은 메시지 답장 메소드

			} else {
				
				System.out.println("\t\t\t\t잘못 입력하셨습니다. ");
				System.out.println("\t\t\t\tENTER를 누르시면 메시지함으로 돌아갑니다.");
				scan.nextLine();
				Cls.clearScreen();
				Thread.sleep(700);
				System.out.println("\t\t\t\t\tLOADING.....");
				Cls.clearScreen();
				again();
				receivedMessage(select, all);

			} 
				
				
			
	}
	
//----------------------------------------------------------------------------------------------------------
	
	@SuppressWarnings("unused")
	private static void reply(String line) throws IOException {

		try {
		
		BufferedReader reader = new BufferedReader(new FileReader(Resource.TOTAL_MESSAGE));

			String[] temp = line.split("■");

			System.out.println("-----------------------------");
			
			System.out.println("\t\t\t\t받는 사람 : " + temp[1]);
			System.out.print("\t\t\t\t내용 : " );
			String content = scan.nextLine();
			
			if (content.equals("")) {
				
				System.out.println("\t\t\t\t-----------------------------");
				System.out.println("\t\t\t\t저장 되었습니다.\n\t\t\t\tENTER를 누르시면 메시지함으로 돌아갑니다.");
				content = scan.nextLine();
				Cls.clearScreen();
				System.out.println("\t\t\t\t\tLOADING.....");
				Cls.clearScreen();
				Thread.sleep(700);
				list(); // 받은 메시지함 이동
				
			}

		reader.close();
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

//----------------------------------------------------------------------------------------------------------
	
	private static void add() throws IOException { // >>>>>>>>>>>>>>>>> 메시지 작성 메소드
	
		try {
			
		System.out.println("\n\n\t\t\t\t[     메시지 작성            ]");
			
		BufferedWriter writer = new BufferedWriter(new FileWriter(Resource.TOTAL_MESSAGE));

		System.out.printf("\t\t\t\t받는 사람 : %s\n", Resource.getMatchID());
		Thread.sleep(500);
		String receiverId = Resource.getMatchID();
		
		String senderId = Resource.getMemberID();

		System.out.print("\t\t\t\t내용 : ");
		String content = scan.nextLine();

		Calendar now = Calendar.getInstance();
		String date = String.format("%tF", now);

		Message message = new Message();
		message.setReceiverId(receiverId);
		message.setSenderId(senderId);
		message.setDate(date);
		message.setContent(content);

		list.add(message);

		System.out.print("\t\t\t\t저장 되었습니다.\n\t\t\t\tENTER를 누르시면 메시지함으로 돌아갑니다.");
		scan.nextLine();
		Cls.clearScreen();
		Thread.sleep(700);
		System.out.println("\t\t\t\t\tLOADING.....");
		Cls.clearScreen();
		
		writer.close();
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

//----------------------------------------------------------------------------------------------------------
	
	private static void messageLoad() { // 받은 메시지 읽어오는 메소드

		try {

			BufferedReader reader = new BufferedReader(new FileReader(Resource.TOTAL_MESSAGE));

			String line = null;

			while ((line = reader.readLine()) != null) {

				String[] temp = line.split("■");

				Message message = new Message();
				message.setReceiverId(temp[0]);
				message.setSenderId(temp[1]);
				message.setDate(temp[2]);
				message.setContent(temp[3]);

				list.add(message);// 메시지 목록에 추가하기

			}
			reader.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
	
//----------------------------------------------------------------------------------------------------------
	
	private static void again() {
		
		System.out.println("\t\t\t\t다시 입력하시려면 ENTER를 입력하세요.");
		scan.nextLine();
		
	}
}


