package com.Train.model;

public class Passenger {

	private int passengerId;
	private int pnr;
	private String passengerName;
	private int age;
	private String gender;
	private String seatNo;
	private String bookingStatus;
	private int trainNo;

	public Passenger() {
		super();
	}

	public Passenger(int passengerId, int pnr, String passengerName, int age, String gender, String seatNo,
			String bookingStatus, int trainNo) {
		super();
		this.passengerId = passengerId;
		this.pnr = pnr;
		this.passengerName = passengerName;
		this.age = age;
		this.gender = gender;
		this.seatNo = seatNo;
		this.bookingStatus = bookingStatus;
		this.trainNo = trainNo;
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public int getPnr() {
		return pnr;
	}

	public void setPnr(int pnr) {
		this.pnr = pnr;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", pnr=" + pnr + ", passengerName=" + passengerName + ", age="
				+ age + ", gender=" + gender + ", seatNo=" + seatNo + ", bookingStatus=" + bookingStatus + ", trainNo="
				+ trainNo + "]";
	}

	
	
}
