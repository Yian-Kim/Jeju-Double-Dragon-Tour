package com.project.jejutvl.travelplan;

import java.io.BufferedReader;
import java.io.FileReader;

import com.project.jejutvl.homescreen.*;

public class Theme {

	public String pathSetter(String themeKeyword) {
		
		if (themeKeyword.substring(0, 4).equals("ACTI")) { //액티비티
			
			String themeTXT = "";
			
			try {
				
				BufferedReader source_reader = 
						new BufferedReader(new FileReader(Resource.ACTI_PATH));
				
				String line = null;
				while ((line = source_reader.readLine()) != null) {
					if (line.indexOf(themeKeyword) > -1) {
						themeTXT += line;
					}
				}
				source_reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			return themeTXT + "■활동";
			
		} 
		
		else if (themeKeyword.substring(0, 4).equals("CAFE")) { //카페
			
			String themeTXT = "";
			
			try {
				
				BufferedReader source_reader = 
						new BufferedReader(new FileReader(Resource.CAFE_PATH));
				
				String line = null;
				while ((line = source_reader.readLine()) != null) {
					if (line.indexOf(themeKeyword) > -1) {
						themeTXT += line;
					}
				}
				source_reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			return themeTXT + "■카페";
			
		} 
		
		else if (themeKeyword.substring(0, 4).equals("FOOD")) { //맛집
			
			String themeTXT = "";
			
			try {
				
				BufferedReader source_reader = 
						new BufferedReader(new FileReader(Resource.FOOD_PATH));
				
				String line = null;
				while ((line = source_reader.readLine()) != null) {
					if (line.indexOf(themeKeyword) > -1) {
						themeTXT += line;
					}
				}
				source_reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			return themeTXT + "■맛집";
			
		} 
		
		else if (themeKeyword.substring(0, 4).equals("PLAC")) { //명소
			
			String themeTXT = "";
			
			try {
				
				BufferedReader source_reader = 
						new BufferedReader(new FileReader(Resource.PLACE_PATH));
				
				String line = null;
				while ((line = source_reader.readLine()) != null) {
					if (line.indexOf(themeKeyword) > -1) {
						themeTXT += line;
					}
				}
				source_reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			return themeTXT + "■명소";
			
		} 
		
		else if (themeKeyword.substring(0, 4).equals("STAY")) { //숙박
			
			String themeTXT = "";
			
			try {
				
				BufferedReader source_reader = 
						new BufferedReader(new FileReader(Resource.STAY_PATH));
				
				String line = null;
				while ((line = source_reader.readLine()) != null) {
					if (line.indexOf(themeKeyword) > -1) {
						themeTXT += line;
					}
				}
				source_reader.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			return themeTXT + "■숙박";
			
		} else { //어디에도 해당되지 않는 경우
			
			return null;
			
		}
		
	}//pathSetter
	
}//Theme
