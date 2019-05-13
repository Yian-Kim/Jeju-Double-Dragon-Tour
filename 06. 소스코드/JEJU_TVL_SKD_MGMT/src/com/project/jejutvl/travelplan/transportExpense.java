package com.project.jejutvl.travelplan;

import java.io.BufferedReader;
import java.io.FileReader;

import com.project.jejutvl.homescreen.Resource;

public class transportExpense {
	
	public int pathSetter(String transportKeyword) {
		
		if (transportKeyword.substring(0, 2).equals("FG")) {
			
			String returnInfo = "";
			
			try {
				
				BufferedReader reader = 
						new BufferedReader(new FileReader(Resource.FLIGHT_DEPARTURE_PATH));
				
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf(transportKeyword) > -1) {
						returnInfo += line;
					}
				}
				reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			String[] TXT = returnInfo.split("■");
			
			int Expense = Integer.parseInt(TXT[5]);
			
			return Expense;
			
		} 
		
		
		else if (transportKeyword.substring(0, 2).equals("SG")) {
			
			String returnInfo = "";
			
			try {
				
				BufferedReader reader = 
						new BufferedReader(new FileReader(Resource.SHIP_DEPARTURE_PATH));
				
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf(transportKeyword) > -1) {
						returnInfo += line;
					}
				}
				reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			String[] TXT = returnInfo.split("■");
			
			int Expense = Integer.parseInt(TXT[6]);
			
			return Expense;
			
		} 
		
		
		else if (transportKeyword.substring(0, 2).equals("FC")) {
			
			String returnInfo = "";
			
			try {
				
				BufferedReader reader = 
						new BufferedReader(new FileReader(Resource.FLIGHT_RETURN_PATH));
				
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf(transportKeyword) > -1) {
						returnInfo += line;
					}
				}
				reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			String[] TXT = returnInfo.split("■");
			
			int Expense = Integer.parseInt(TXT[5]);
			
			return Expense;
			
		} 
		
		
		else if (transportKeyword.substring(0, 2).equals("SC")) {
			
			String returnInfo = "";
			
			try {
				
				BufferedReader reader = 
						new BufferedReader(new FileReader(Resource.SHIP_RETURN_PATH));
				
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf(transportKeyword) > -1) {
						returnInfo += line;
					}
				}
				reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			String[] TXT = returnInfo.split("■");
			
			int Expense = Integer.parseInt(TXT[6]);
			
			return Expense;
			
		} 
		
		
		else {
			
			return 0;
			
		}

	} // Method : PathSetter

} // Class : transportExpense
