package com.project.jejutvl.homescreen;

import java.util.Scanner;

public class Cls {
	
	private static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
	}
	
	public static void clearScreen() {
		for (int i = 0; i < 50; i++) {
			System.out.println("");
		}
		// new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	} // clearSceen -> 추후 cls로 바꿀 예정
}
