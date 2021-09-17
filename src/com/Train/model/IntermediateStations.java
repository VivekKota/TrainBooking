package com.Train.model;

public class IntermediateStations {

	private int id;
	private int trainNo;
	private String intermediateStation;

	public IntermediateStations() {
		super();
	}

	public IntermediateStations(int id, int trainNo, String intermediateStation) {
		super();
		this.id = id;
		this.trainNo = trainNo;
		this.intermediateStation = intermediateStation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public String getIntermediateStation() {
		return intermediateStation;
	}

	public void setIntermediateStation(String intermediateStation) {
		this.intermediateStation = intermediateStation;
	}

	@Override
	public String toString() {
		return "IntermediateStations [id=" + id + ", trainNo=" + trainNo + ", intermediateStation="
				+ intermediateStation + "]";
	}

}
