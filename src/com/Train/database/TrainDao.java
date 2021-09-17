package com.Train.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Train.model.Train;

public class TrainDao {

	TicketDao ticketDao = new TicketDao();

	public void addTrain(Train train) {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("insert into train values(?,?,?,?,?)");
			pst.setInt(1, train.getTrainNo());
			pst.setString(2, train.getTrainName());
			pst.setString(3, train.getSource());
			pst.setString(4, train.getDestination());
			//pst.setString(5, train.getIntermediate());
			pst.setInt(5, train.getNoOfSeatsAvailable());

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			con.commit();

		} catch (Exception e) {

			System.out.println("Error in adding train to the database");
			e.printStackTrace();
		}

	}

	public List<Train> showAllTrains() {

		try {

			Connection con = DbConnection.connect();
			Statement st = con.createStatement();
			String sql = "select * from train";
			ResultSet rs = st.executeQuery(sql);

			List<Train> trains = new ArrayList<Train>();
			while (rs.next()) {
				int trainNo = rs.getInt("trainNo");
				String trainName = rs.getString("trainName");
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				//String intermediate = rs.getString("intermediate");
				int noOfSeatsAvailable = rs.getInt("noOfSeatsAvailable");

				Train train = new Train(trainNo, trainName, source, destination,noOfSeatsAvailable);
				trains.add(train);
			}
			return trains;

		} catch (Exception e) {

			System.out.println("Error in showing trains");
			e.printStackTrace();
		}
		return null;
	}

	public void updateTrain(int updatedSeatsAvailable, int trainNo) {

		try {
			Connection con = DbConnection.connect();

			PreparedStatement pst = con.prepareStatement("update train set noOfSeatsAvailable= ? where trainNo = ?");
			pst.setInt(1, updatedSeatsAvailable);
			pst.setInt(2, trainNo);

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			con.commit();

		} catch (Exception e) {
			System.out.println("Error in updating train seats after booking done by user");
			e.printStackTrace();
		}

	}

	public Train findTrainByNo(int trainNo) {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("Select * from train where trainNo = ? ");
			pst.setInt(1, trainNo);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				int no = rs.getInt("trainNo");
				String name = rs.getString("trainName");
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				//String intermediate = rs.getString("intermediate");
				int noOfSeatsAvailable = rs.getInt("noOfseatsAvailabe");

				Train train = new Train(no, name, source, destination,noOfSeatsAvailable);

				return train;
			}

		} catch (Exception e) {

			System.out.println("Error in finding trains");
			e.printStackTrace();
		}
		return null;

	}

}
