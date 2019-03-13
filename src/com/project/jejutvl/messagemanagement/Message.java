package com.project.jejutvl.messagemanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Message {

	// private static int num = 1;

	private static UI ui;
	private static Scanner scan;
	private static ArrayList<Message> list;

	static {
		ui = new UI();
		scan = new Scanner(System.in);
		list = new ArrayList<Message>();

	}

	public void start() throws IOException {

		messageLoad(); // >>>>>>>>>>>>>>>>> 저장된 메시지 로딩 메소드

		boolean loop = true;

		ui.start(); // >>>>>>>>>>>>>>>>>>>>> UiMessageManagement 클래스 시작

		while (loop) {

			// 메뉴 > 선택 > 실행
			ui.menu();

			switch (scan.nextLine()) {
			case "1":
				list(); // >>>>>>>>>>>>>>> 받은 메시지함 메소드
				break;
			case "2":
				add(); // >>>>>>>>>>>>>>> 메시지 작성하는 메소드
				save(); // >>>>>>>>>>>>>>> 매시지 파일에 저장하는 메소드
				break;
			case "*":
				System.out.println("홈 화면으로 돌아갑니다.");
				// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Menu.printHome
			default:
				loop = false;
				break;

			}
		}
	}

	private static void save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.sendmessage));

			ui.title(UI.ADD);

			for (Message message : list) {

				writer.write(String.format("%s■%s■%s\r\n", message.getId(), message.getDate(), message.getContent()));

			}
			writer.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	private static void list() throws IOException {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(Path.receivemessage));

			System.out.println("==================================================");
			System.out.println("		받은 메시지함 		");
			System.out.println("==================================================");
			System.out.println("번호  보낸 사람\t날짜\t\t내용\t\t");
			System.out.println("==================================================");

			String line = null;

			int num = 1;

			while ((line = reader.readLine()) != null) {

				String[] temp = line.split("■");

				System.out.printf("%02d\t%s\t%s\t%s\n", num, temp[0], temp[1], temp[2]); // >>>> 받은 메시지 읽어오기
				num++;

				System.out.println("--------------------------------------------------");
			}
			reader.close();

			System.out.println("전문을 보시려면 [ ]안의 번호를 입력해주세요.");
			System.out.println();
			System.out.println("[0] 메시지보내기");
			System.out.println("[*] 홈 화면으로 돌아가기");
			System.out.println("-----------------------");
			System.out.print("선택 : ");

			String select = scan.nextLine();

			if (select.equals("0")) {
				add(); // >>>>>>>>>>>>>>>>>>>>>> 메시지 작성 메소드 넘어가기
				save();
			} else if (select.equals("*")) {
				System.out.println("홈 화면으로 돌아갑니다.");// Menu.printHome() 추가할 것

			} else {

				int index = Integer.parseInt(select);

				BufferedReader reader2 = new BufferedReader(new FileReader(Path.receivemessage));
				line = "";
				num = 1;

				System.out.println();
				while ((line = reader2.readLine()) != null) {

					if (num == index) {

						receivedMessage(line);

						break;
					}
					num++;
				}
				reader2.close();
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	private static void receivedMessage(String line) throws IOException {

		System.out.println("==========================");
		System.out.printf("\t메시지(%d/%d)\t\n", 1, list.size());
		System.out.println("==========================");

		String[] temp = line.split("■");

		System.out.println("보낸 사람 : " + temp[0]);

		System.out.println("날짜 : " + temp[1]);

		String[] temp1 = temp[2].split("\\."); // >>>>>>>>>>>>>>>>>>>>>>>>>>>식별자(.) 단위로 메시지 내용 개행

		System.out.println("내용 : ");

		for (String messagecontent : temp1) {
			System.out.println(messagecontent + ".");
		}

		System.out.println("--------------------------");
		System.out.println("[0] 답장하기");
		System.out.println("[*] 받은 메시지함으로 돌아가기");
		System.out.println("--------------------------");
		System.out.print("선택 : ");
		String select = scan.nextLine();

		if (select.equals("0")) {
			reply(); // >>>>>>>>>>>>>>>>>>>>>>>>>> 받은 메시지 답장 메소드

		} else if (select.equals("*")) {
			list();

		} else {
			System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
			scan.nextLine();

		}
	}

	private static void reply() throws IOException {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(Path.receivemessage));

			String line = null;

			while ((line = reader.readLine()) != null) {

				String[] temp = line.split("■");

				System.out.println("--------------------------");

				System.out.println("받는 사람 : " + temp[0]);
				System.out.println("내용 : ");
				String content = scan.nextLine();

				System.out.println("--------------------------");
				System.out.println("저장 되었습니다. \n받은 메시지함으로 돌아갑니다.");
				content = scan.nextLine();
				list(); // >>>>>>>>>>>>>>>>> 받은 메시지함 이동

			}
			reader.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void add() throws IOException {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.sendmessage));

			ui.title(UI.ADD);

			System.out.println("받는 사람 : ");
			String id = scan.nextLine();

			System.out.println("내용 : ");
			String content = scan.nextLine();

			Calendar now = Calendar.getInstance();
			String date = String.format("%tF", now);

			Message message = new Message();
			message.setId(id);
			message.setDate(date);
			message.setContent(content);

			list.add(message);

			ui.pause(UI.ADD);

			writer.close();
			ui.start();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void messageLoad() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(Path.receivemessage));

			String line = null;

			while ((line = reader.readLine()) != null) {

				String[] temp = line.split("■");

				Message message = new Message();
				message.setId(temp[0]);
				message.setDate(temp[1]);
				message.setContent(temp[2]);

				list.add(message);// 메시지 목록에 추가하기

			}
			reader.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
}
