package com.Train.model;

public class CancelledTicket {

	private int trainNo;
	private String seatNo;

	public CancelledTicket() {
		super();
	}

	public CancelledTicket(int trainNo, String seatNo) {
		super();
		this.trainNo = trainNo;
		this.seatNo = seatNo;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	@Override
	public String toString() {
		return "CancelledTicket [trainNo=" + trainNo + ", seatNo=" + seatNo + "]";
	}

}
