package com.project.jejutvl.newschedule;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.project.jejutvl.homescreen.Resource;

class SaveInfo {
	/*
	 * TODO : save 리스트에 넣어놓은 정보를 dat파일에 저장하는 메소드입니다.
	 * save 리스트에 저장한 데이터 목록
	 * 0. ID
	 * 1. 가는 교통편
	 * 2. 돌아오는 교통편
	 * 3. 테마 (memberTheme hashMap을 한꺼번에 불러옵니다)
	 */
	
	public void saveInfo(String dateSelect) {
		
		//TODO : 여기서 또 새롭게 start 캘린더를 선언합니다. 굳이 이렇게까지 하는 이유는...
		//start에 들어있는 날짜와 saveStart 혹은 saveEnd에 들어있는 날짜를 서로 비교할 때 오류가 발생하기 때문입니다.
		
		//EX. 2019-03-10 과 2019-03-10을 비교하는데, 두 날짜가 다르다고 함...???
		
		//이 오류때문에 정적 변수였던 start 캘린더를 일반 변수로 바꿨고, 메소드마다 새롭게 날짜를 설정하도록 했습니다.
		//(현재 여기 이 메소드에서 또 start 캘린더를 선언하지 않으면 자꾸 오류가...)
		//왜 오류가 나는지는 모르겠음...그래도 어찌저찌해서 고치기는 했음...
		
		Calendar start = Calendar.getInstance();
		
		String[] temp = Resource.getStartDate().split("-");
		int year = Integer.parseInt(temp[0]);
		int month = Integer.parseInt(temp[1]);
		int date = Integer.parseInt(temp[2]);
		
		start.set(year, month-1, date + Integer.parseInt(dateSelect) - 1);
		
	//===============================================================================
		
		Calendar saveStart = Calendar.getInstance();
		Calendar saveEnd = Calendar.getInstance();

		String[] tempStart = Resource.getStartDate().split("-");
		int startYear = Integer.parseInt(tempStart[0]);
		int startMonth = Integer.parseInt(tempStart[1]);
		int startDate = Integer.parseInt(tempStart[2]);
		
		String[] tempEnd = Resource.getEndDate().split("-");
		int endYear = Integer.parseInt(tempEnd[0]);
		int endMonth = Integer.parseInt(tempEnd[1]);
		int endDate = Integer.parseInt(tempEnd[2]);
		
		saveStart.set(startYear, startMonth-1, startDate);
		saveEnd.set(endYear, endMonth-1, endDate);
		
	//===============================================================================	
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
	//===============================================================================		
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(Resource.TRAVEL_PATH, true));
			
			if (start.compareTo(saveStart) == 0) {

				String tempDate = format.format(start.getTime());
										  //ID■날짜■테마■교통편
				writer.write(String.format("%s■%s■%s■%s\n", Resource.save.get(0), tempDate,  Resource.save.get(3),  Resource.save.get(1)));
				
			} else if (start.compareTo(saveEnd) == 0){
				
				String tempDate = format.format(start.getTime());
				writer.write(String.format("%s■%s■%s■%s\n", Resource.save.get(0), tempDate,  Resource.save.get(3),  Resource.save.get(2)));
				
			} else {
				
				String tempDate = format.format(start.getTime());
				writer.write(String.format("%s■%s■%s\n", Resource.save.get(0), tempDate,  Resource.save.get(3)));
				
			}

			writer.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}//saveInfo 메소드
	
}
