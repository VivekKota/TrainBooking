package com.Train.model;

public class Ticket {

	private int pnr;
	private String source;
	private String destination;
	private int trainNo;
	private int noOfPassengers;
	private int bookedBy;

	public Ticket() {
		super();
	}

	public Ticket(int pnr, String source, String destination, int trainNo, int noOfPassengers, int bookedBy) {
		super();
		this.pnr = pnr;
		this.source = source;
		this.destination = destination;
		this.trainNo = trainNo;
		this.noOfPassengers = noOfPassengers;
		this.bookedBy = bookedBy;
	}

	public int getPnr() {
		return pnr;
	}

	public void setPnr(int pnr) {
		this.pnr = pnr;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public int getBookedBy() {
		return bookedBy;
	}

	public void setBookedBy(int bookedBy) {
		this.bookedBy = bookedBy;
	}

	@Override
	public String toString() {
		return "Ticket [pnr=" + pnr + ", source=" + source + ", destination=" + destination + ", trainNo=" + trainNo
				+ ", noOfPassengers=" + noOfPassengers + ", bookedBy=" + bookedBy + "]";
	}


	
}
