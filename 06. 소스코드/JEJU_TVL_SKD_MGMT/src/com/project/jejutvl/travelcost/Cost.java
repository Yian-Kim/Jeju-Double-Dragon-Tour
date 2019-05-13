package com.project.jejutvl.travelcost;

public class Cost {

	private String date;
	private String year;
	private String name;
	private String price;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Cost [date=" + date + ", year=" + year + ", name=" + name + ", price=" + price + "]";
	}

}
