package com.project.jejutvl.randomtravel;

public class Information {
	
	public String id;
	public String birthYear;
	public String gender;
	public String name;
	public String date;
	public String daySchedule;
	


	@Override
	public String toString() {
		return "Information [id=" + id + ", birthYear=" + birthYear + ", gender=" + gender
				+ ", name=" + name + ", date=" + date + ", daySchedule=" + daySchedule + "]";
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getBirthYear() {
		return birthYear;
	}



	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getDaySchedule() {
		return daySchedule;
	}



	public void setDaySchedule(String daySchedule) {
		this.daySchedule = daySchedule;
	}
	
	

	


}
