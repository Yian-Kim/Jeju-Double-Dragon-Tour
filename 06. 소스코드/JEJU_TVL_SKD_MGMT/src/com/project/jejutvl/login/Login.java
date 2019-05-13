package com.project.jejutvl.login;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.project.jejutvl.homescreen.Cls;
import com.project.jejutvl.homescreen.HomeScreen;
import com.project.jejutvl.homescreen.Resource;

//로그인 회원가입 화면
public class Login {
	
	private static Scanner scan;
	private static ArrayList<Member> list;

	//초기화
	static {
		scan = new Scanner(System.in);
		list = new ArrayList<Member>();
	}

//---------------------------------------------------------------------------------------------------------------------------	
	
	//TODO : Main
	public static void main(String[] args) throws IOException {

		// initialize
		dummy(); // 더미 파일 읽어오기 (회원목록)

		boolean loop = true; // 1 or 2 = true 그외 false - > 다시선택

		try {

			while (loop) {
				
				System.out.println("\n\t\t==========================================================================\n");
				System.out.println("\t\t--------------------------------------------------------------------------\n\n");
				System.out.print("\t██████╗  ██████╗ ██╗   ██╗██████╗ ██╗     ███████╗                                         \r\n" + 
						"\t██╔══██╗██╔═══██╗██║   ██║██╔══██╗██║     ██╔════╝                                         \r\n" + 
						"\t██║  ██║██║   ██║██║   ██║██████╔╝██║     █████╗                                           \r\n" + 
						"\t██║  ██║██║   ██║██║   ██║██╔══██╗██║     ██╔══╝                                           \r\n" + 
						"\t██████╔╝╚██████╔╝╚██████╔╝██████╔╝███████╗███████╗                                         \r\n" + 
						"\t╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝ ╚══════╝╚══════╝                                         \r\n" + 
						"                                                                                           \r\n" + 
						"\t██████╗ ██████╗  █████╗  ██████╗  ██████╗ ███╗   ██╗    ████████╗ ██████╗ ██╗   ██╗██████╗ \r\n" + 
						"\t██╔══██╗██╔══██╗██╔══██╗██╔════╝ ██╔═══██╗████╗  ██║    ╚══██╔══╝██╔═══██╗██║   ██║██╔══██╗\r\n" + 
						"\t██║  ██║██████╔╝███████║██║  ███╗██║   ██║██╔██╗ ██║       ██║   ██║   ██║██║   ██║██████╔╝\r\n" + 
						"\t██║  ██║██╔══██╗██╔══██║██║   ██║██║   ██║██║╚██╗██║       ██║   ██║   ██║██║   ██║██╔══██╗\r\n" + 
						"\t██████╔╝██║  ██║██║  ██║╚██████╔╝╚██████╔╝██║ ╚████║       ██║   ╚██████╔╝╚██████╔╝██║  ██║\r\n" + 
						"\t╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝       ╚═╝    ╚═════╝  ╚═════╝ ╚═╝  ╚═╝\r\n" + 
						"                                                                                           \r\n");
				System.out.println("");
				System.out.println("\t\t\tJEJU TRAVEL MANAGEMENT PROGRAM | Since 2019.03.13.");
				System.out.println("\t\t--------------------------------------------------------------------------");
				System.out.println("\t\t==========================================================================\n");
				System.out.println("\t\t\t\t[ 로그인 ]");
				System.out.println();
				System.out.println("\t\t\t\t----------------------------");
				System.out.println();
				System.out.println("\t\t\t\t  [1] 로그인");
				System.out.println("\t\t\t\t  [2] 회원가입");
				System.out.println("\n\t\t\t\t  [0] 프로그램 종료");
				System.out.println("\n\t\t\t\t----------------------------");
				System.out.println("\t\t\t\t[ ] 안의 번호를 입력하세요");
				System.out.print("\t\t\t\t선택(번호) : ");
				String input = scan.nextLine();
				// (메뉴 출력 -> 항목 선택 -> 기능 실행)

				System.out.println("");
				System.out.println("");

				if (input.equals("1")) {
					Cls.clearScreen();
					login(); // 로그인 메소드

				} else if (input.equals("2")) {
					Cls.clearScreen();
					add(); // 회원가입 메소드

				} else {

					System.out.println("1번과 2번을 선택해 주세요");
					scan.nextLine();
					Cls.clearScreen();
					System.out.println("\t\t\t\t\tLOADING.....");
					Thread.sleep(700);
					Cls.clearScreen();
					continue;

				}
				
			} // While문 끝

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	} // Method : main

//-----------------------------------------------------------------------------------------------------------------------------

	//TODO : 로그인 1번 선택시 화면
	private static void login() throws Exception {

		System.out.println("\n\n\n\n\n\n\n\t\t---------------------------------------------------------------------\n\n");
		System.out.println();
		System.out.print("\t\t\t\t██╗      ██████╗  ██████╗ ██╗███╗   ██╗\r\n" + 
				"\t\t\t\t██║     ██╔═══██╗██╔════╝ ██║████╗  ██║\r\n" + 
				"\t\t\t\t██║     ██║   ██║██║  ███╗██║██╔██╗ ██║\r\n" + 
				"\t\t\t\t██║     ██║   ██║██║   ██║██║██║╚██╗██║\r\n" + 
				"\t\t\t\t███████╗╚██████╔╝╚██████╔╝██║██║ ╚████║\r\n" + 
				"\t\t\t\t╚══════╝ ╚═════╝  ╚═════╝ ╚═╝╚═╝  ╚═══╝\r\n");
		System.out.println();
		System.out.println("\t\t---------------------------------------------------------------------\n\n");
		System.out.println("\n\n\n");
		System.out.println("\t\t\t\t[         로그인         ]");
		System.out.println("\n\t\t\t\t--------------------------\n");
		System.out.print("\t\t\t\t아이디 : ");
		String ID = scan.nextLine().trim();

		System.out.print("\t\t\t\t비밀번호 : ");
		String password = scan.nextLine().trim();
		System.out.println("\n\t\t\t\t--------------------------");

		boolean pass = false;

		@SuppressWarnings("unused")
		int whois = -1;

		for (int i = 0; i < list.size(); ++i) {

			if (list.get(i).getID().equals(ID) && list.get(i).getPassword().equals(password)) {
				pass = true;
				whois = i;
			}
		}

		if (pass) {
			System.out.println("\t\t\t\t로그인 하였습니다.");
			scan.nextLine();
			Cls.clearScreen();
			System.out.println("\t\t\t\t\tLOADING.....");
			Thread.sleep(700);
			Cls.clearScreen();
			
			// TODO : 로그인 한 ID를 setMemberID로 세팅한다
			Resource.setMemberID(ID);
			HomeScreen.start();
			

		} else {
			System.out.println("\t\t\t\t아이디 또는 비밀번호를 다시 입력해주세요.");
			scan.nextLine();
			Cls.clearScreen();
			login(); // 다시 로그인 화면으로
		}
		
	} // Method : login
	
//-----------------------------------------------------------------------------------------------------------------------------	
	
	//TODO : 회원가입 메소드
	private static void add() throws IOException, InterruptedException {
		System.out.println();
		System.out.println("---------------------------------------------------------------------");
		System.out.println();
		System.out.print("\t\t\t███████╗██╗ ██████╗ ███╗   ██╗    ██╗   ██╗██████╗ \r\n" + 
				"\t\t\t██╔════╝██║██╔════╝ ████╗  ██║    ██║   ██║██╔══██╗\r\n" + 
				"\t\t\t███████╗██║██║  ███╗██╔██╗ ██║    ██║   ██║██████╔╝\r\n" + 
				"\t\t\t╚════██║██║██║   ██║██║╚██╗██║    ██║   ██║██╔═══╝ \r\n" + 
				"\t\t\t███████║██║╚██████╔╝██║ ╚████║    ╚██████╔╝██║     \r\n" + 
				"\t\t\t╚══════╝╚═╝ ╚═════╝ ╚═╝  ╚═══╝     ╚═════╝ ╚═╝     \r\n");
		System.out.println();
		System.out.println("---------------------------------------------------------------------");
		System.out.println();

		System.out.println("아이디는 영문자5 + 숫자4를 입력해주세요.");
		System.out.println();
		System.out.println("비밀번호는 영문자3 + 숫자3를 입력해주세요.");
		System.out.println();
		System.out.println("이름은 한글로만 입력해주세요.");
		System.out.println();
		System.out.println("생년월일은 숫자4자리로 입력해 주세요");
		System.out.println();
		System.out.println("성별(남/여)중에 선택하여 입력해 주세요.");
		System.out.println();

		System.out.println("-----------------------------------------");

		System.out.println("회원가입 진행하실려면 ENTER키를 누르세요.");
		scan.nextLine();

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(Resource.MEMBER_PATH, true));

			System.out.println();
			System.out.println("\t\t--------------------------------------------------------------------------");
			System.out.println();
			System.out.print("\t\t\t███████╗██╗ ██████╗ ███╗   ██╗    ██╗   ██╗██████╗ \r\n" + 
					"\t\t\t██╔════╝██║██╔════╝ ████╗  ██║    ██║   ██║██╔══██╗\r\n" + 
					"\t\t\t███████╗██║██║  ███╗██╔██╗ ██║    ██║   ██║██████╔╝\r\n" + 
					"\t\t\t╚════██║██║██║   ██║██║╚██╗██║    ██║   ██║██╔═══╝ \r\n" + 
					"\t\t\t███████║██║╚██████╔╝██║ ╚████║    ╚██████╔╝██║     \r\n" + 
					"\t\t\t╚══════╝╚═╝ ╚═════╝ ╚═╝  ╚═══╝     ╚═════╝ ╚═╝     \r\n");
			System.out.println();
			System.out.println("\t\t--------------------------------------------------------------------------");
			System.out.println();
			
			String ID = "";// 무한루프이므로 ID를 초기화시켜준다.
			loop: while (true) {
				// System.out.println("아이디는 영문자5 + 숫자4를 입력해주세요.");
				System.out.print("아이디 : ");
				ID = scan.nextLine().trim();

				for (int i = 0; i < list.size(); ++i) {
					if (ID.equals(list.get(i).getID())) {
						System.out.println("ID가 중복되었습니다.");
						continue loop;
					}
				}

				if (Pattern.matches("^[ㄱ-ㅎ가-힣]*$", ID)) {
					System.out.println("영어 소문자와 숫자로 9자리 입력해주세요\n");
					continue loop;

				}
				if (Pattern.matches("^[a-z0-9]*$", ID)) {
					if (ID.length() == 9) {
						break;

					}

				}
				System.out.println("영어 소문자와 숫자로 9자리 입력해주세요");
				continue loop;

			} // 아이디 중복 검사

			String password = "";// 무한루프이므로 password를 초기화시켜준다.
			loop: while (true) {
				// System.out.println("비밀번호는 영문자3 + 숫자3를 입력해주세요.");
				System.out.print("비밀번호 : ");
				password = scan.nextLine().trim();

				if (Pattern.matches("^[a-z0-9]*$", password)) {
					if (password.length() == 6) {

						System.out.println("비밀번호를 다시 입력해 주세요.");
						System.out.print("비밀번호 : ");
						String password2 = scan.nextLine();

						if (Pattern.matches("^[a-z0-9]*$", password2)) {
							if (password2.length() == 6) {
								if (password.equals(password) == password2.equals(password2)) {
									break;

								}

							}

						}
						System.out.println("재입력한 비밀번호가 맞지 않습니다. 재입력해주세요.");

					}

				}
				System.out.println("패스워드는 영어소문자와 숫자로 6자리 입력해주세요.");
				continue loop;

			} // 패스워드 6자리 검사

			String name = "";// 무한루프이므로 name을 초기화시켜준다.
			while (true) {
				// System.out.println("이름은 한글로 입력해주세요.");
				System.out.print("이름 : ");
				name = scan.nextLine().trim();

				if (Pattern.matches("^[가-힣]+$", name)) {
					break;

				}
				System.out.println("이름은 한글로만 입력해주세요.");

			} // 이름 한글 검사

			String year = "";
			while (true) {
				// System.out.println("년도는 숫자4자리로 입력해주세요.");
				System.out.print("태어난 년도(숫자) : ");
				year = scan.nextLine().trim();

				if (Pattern.matches("^[0-9]*$", year)) {
					if (year.length() == 4) {
						break;
					}
				}
				System.out.println("태어난 년도(숫자) 4자리로 입력해주세요.");
			} // 년도 검사

			String gender = "";// 무한루프이므로 gender을 초기화시켜준다
			while (true) {
				// System.out.println("성별은 남 / 여로 입력해주세요");
				System.out.print("성별(남/여) : ");
				gender = scan.nextLine().trim();
				if (gender.equals("남") || gender.equals("여")) {
					break;
				}
				System.out.println("남/여 중 하나를 입력해주세요.");
			} // 남/여 유효성 검사

			String tel = "";// 무한루프이므로 tell을 초기화 시켜준다.
			while (true) {
				System.out.println("전화번호는 000-0000-0000 로 입력해주세요.");
				System.out.print("연락처(-포함): ");
				tel = scan.nextLine().trim();

				String[] all = tel.split("-");

				if (all.length == 3 && all[0].length() == 3 && all[1].length() == 4 && all[2].length() == 4) {
					break;// all = 전체 전화번호를 - 기준으로 3등분하여 배열에 담는다. 그후 0 - 3자리 , 1 - 4자리, 2 - 4자리를 검사
				}
				System.out.println("전화번호는 000-0000-0000 형식입니다");
			} // 전화번호 유효성 검사

			// memory
			Member m = new Member();
			m.setID(ID);
			m.setPassword(password);
			m.setYear(year);
			m.setGender(gender);
			m.setName(name);
			list.add(m);
			
			// file I/O
			writer.write(String.format("%s■%s■%s■%s■%s\r\n", m.getID(), m.getPassword(), m.getYear(), m.getGender(), m.getName()));
			

			writer.close();
			System.out.println();
			System.out.println("-----------------------------------------");
			Thread.sleep(700);
			System.out.println("가입이 완료되었습니다.");
			System.out.println();
			System.out.println("-----------------------------------------");
			Thread.sleep(500);

			pause();

		} catch (Exception e) {

			System.out.println(e.toString());
		}

	} //Method : add
	
//-----------------------------------------------------------------------------------------------------------------------------

	//TODO : 메인화면 돌아가는 메소드
	private static void pause() {

		System.out.println("메인화면으로 돌아가시려면 ENTER키를 누르세요.");
		scan.nextLine();
		Cls.clearScreen();
		System.out.println("\t\t\t\t\tLOADING.....");
		Cls.clearScreen();
		
	} //Method : Pause
	
//-----------------------------------------------------------------------------------------------------------------------------
	
	//TODO : 기존 회원 목록 읽어오기
	private static void dummy() throws IOException {
		// 더미 파일 읽어오기
		File file = new File(Resource.MEMBER_PATH);

		@SuppressWarnings("resource")
		FileReader fileReader = new FileReader(file);

		int pop;

		StringBuilder sb = new StringBuilder();

		while ((pop = fileReader.read()) != -1) {

			if (pop > 60000) // UTF-8 변경시 char 값이 6만 이상으로 들어갈시에..
				continue;

			if (pop == 13) { // 13은 엔터(아스키코드값)
				String word = sb.toString();

				word = word.trim();

				String[] mem = word.split("■");

				Member member = new Member();

				member.setID(mem[0]);
				member.setPassword(mem[1]);
				member.setYear(mem[2]);
				member.setGender(mem[3]);
				member.setName(mem[4]);
				list.add(member);

				sb = new StringBuilder();

			} else {

				sb.append((char) pop);
			}
			
		} // While문 끝

	} // Method : dummy
	
}// Class : Login



