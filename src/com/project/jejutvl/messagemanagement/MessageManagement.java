package com.project.jejutvl.messagemanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MessageManagement {

	public static void main(String[] args) throws IOException {

		start();

	}

	private static void start() throws IOException {

		Scanner scan = new Scanner(System.in);

		try {
			System.out.println("[	메시지관리		]");
			System.out.println();
			System.out.println("*************************");
			System.out.println();
			System.out.println("[1] 받은 메시지함");
			System.out.println("[2] 메시지 보내기");
			System.out.println();
			System.out.println("[*] 메인화면으로 돌아가기");
			System.out.println();
			System.out.println("*************************");
			System.out.println("[ ]안의 번호를 입력하세요.");
			System.out.print("선택 : ");

			String select = scan.nextLine();

			if (select.equals("1")) {
				printMessageManagement();
				// break;

			} else if (select.equals("2")) {
				sendMessage();
				// break;

			} else if (select.equals("*")) {
				// menu.printHomeMenu();
			} else {

			}
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");

		} catch (Exception e) {
			e.toString();
		}

	}

	private static void printMessageManagement() throws IOException {

		Scanner scan = new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new FileReader(Path.sendmessage));

		System.out.println("==================================================");
		System.out.println("		받은 메시지함 		");
		System.out.println("==================================================");
		System.out.println("번호  보낸 사람	날짜   내용			 ");
		System.out.println("==================================================");

		String line = null;

		int i = 0;

		while ((line = reader.readLine()) != null) {
			i++;
			String[] temp = line.split("■");

			System.out.printf("%s\t%s\t%s\t%s\n", i, temp[0], temp[1], temp[2]);

			System.out.println("----------------------------------------");
		}

		System.out.println("전문을 보시려면 [ ]안의 번호를 입력해주세요.");
		System.out.println();
		System.out.println("[0] 메시지보내기");
		System.out.println("[*] 홈 화면으로 돌아가기");
		System.out.println("-----------------------");
		System.out.print("선택 : ");

		String select = scan.nextLine();
		if (select.equals("0")) {
			sendMessage();

		} else if (select.equals("i")) {// array

			System.out.println("*************************");
			System.out.printf("	메시지(%d/%d)	\n", 1, 2); // getter, setter
			System.out.println("*************************");
			System.out.println("보낸 사람 : "); // 보낸사람 데이터 파일 불러오는지 확인
			// BufferedReader reader = new BufferedReader(new
			// FileReader("C:\\kbj\\com.project.jejutvl.messagemanagement\\src\\com\\project\\jejutvl\\messagemanagement\\id.dat"));

			System.out.println("내용 : ");
			BufferedReader reader2 = new BufferedReader(new FileReader(Path.sendmessage));
			String data = "";
			System.out.println();
			System.out.println();
			System.out.println("-------------------------");
			System.out.println("[0] 답장하기");
			System.out.println("[*] 받은 메시지함으로 돌아가기 ");

			System.out.println("-------------------------");
			System.out.print("선택 : ");

			select = scan.nextLine();
			if (select.equals("0")) {
				sendMessage();
			} else if (select.equals("*")) {
				printMessageManagement();
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
			}

		} else {
			System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");

		}
	}

	private static void sendMessage() throws IOException {

		Scanner scan = new Scanner(System.in);
		BufferedWriter writer = new BufferedWriter(new FileWriter(Path.sendmessage));

		System.out.println("*************************");
		System.out.println("	메시지 보내기	");
		System.out.println("*************************");
		System.out.print("받는 사람 : ");
		String reciever = scan.nextLine();

		System.out.print("내용(최대 50자) : ");

		String content = scan.nextLine(); // 매칭상대에게 보낼 메시지 내용 입력
		writer.write(content); // 입력 메시지 파일로 저장
		writer.close();

		BufferedReader reader = new BufferedReader(new FileReader(Path.sendmessage));
		String data = "";

		// while ((data = reader.readLine()) !=null) {
		// System.out.println(data); // 파일에 저장된 메시지 출력
		// }

		reader.close();
		System.out.println("-------------------------");
		System.out.println("[#] 메시지보내기");
		System.out.println("[*] 홈화면으로 돌아가기");
		System.out.println("-------------------------");
		System.out.print("선택 : ");
		String select = scan.nextLine();
		if (select.equals("#")) {
			System.out.println("메시지 전송이 완료되었습니다.");
		} else if (select.equals("*")) {
			// menu.printHomeMenu();
		} else {
			System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
		}
		System.out.println("-------------------------");

		System.out.println("ENTER 키를 누르시면 홈화면으로 돌아갑니다.");

	}

}
