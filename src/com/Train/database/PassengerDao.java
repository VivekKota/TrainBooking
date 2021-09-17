package com.Train.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Train.model.Passenger;

public class PassengerDao {

	public void addPassenger(int pnr, String passengerName, int age, String gender, String seatNo, String bookingStatus,
			int trainNo) {

		try {
			Connection con = DbConnection.connect();

			PreparedStatement pst = con.prepareStatement(
					"insert into passenger(pnr, passengerName, age, gender, seatNo,bookingStatus, trainNo ) values(?,?,?,?,?,?,?)");
			pst.setInt(1, pnr);
			pst.setString(2, passengerName);
			pst.setInt(3, age);
			pst.setString(4, gender);
			pst.setString(5, seatNo);
			pst.setString(6, bookingStatus);
			pst.setInt(7, trainNo);

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			con.commit();

		} catch (Exception e) {
			System.out.println("Error in adding tickets");
			e.printStackTrace();
		}
	}

	public List<Passenger> findPassengers(int pnr) {
		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("Select * from passenger where pnr = ? ");
			pst.setInt(1, pnr);
			ResultSet rs = pst.executeQuery();

			List<Passenger> passengers = new ArrayList<Passenger>();
			while (rs.next()) {

				int id = rs.getInt(1);
				String passengerName = rs.getString("passengerName");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String seatNo = rs.getString("seatNo");
				String bookingStatus = rs.getString("bookingStatus");
				int trainNo = rs.getInt("trainNo");

				Passenger passenger = new Passenger(id, pnr, passengerName, age, gender, seatNo, bookingStatus,
						trainNo);
				passengers.add(passenger);
			}
			return passengers;

		} catch (Exception e) {

			System.out.println("Error in finding passengers with pnr ");
			e.printStackTrace();
		}
		return null;
	}

	public void removePassenger(int id) {

		try {
			Connection con = DbConnection.connect();

			PreparedStatement pst = con.prepareStatement("delete from passenger where passengerId =? ");
			pst.setInt(1, id);

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			con.commit();

		} catch (Exception e) {
			System.out.println("Error in deleting passenger");
			e.printStackTrace();
		}

	}

	public Passenger checkWaitingListPassenger(int trainNo, String bookingStatus) {

		try {
			Connection con = DbConnection.connect();

			PreparedStatement pst = con.prepareStatement(
					"Select * from passenger where trainNo = ? and bookingStatus = ? order by passengerId asc limit 1 ");
			pst.setInt(1, trainNo);
			pst.setString(2, bookingStatus);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				int id = rs.getInt(1);
				int pnr = rs.getInt("pnr");
				String passengerName = rs.getString("passengerName");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String seatNo = rs.getString("seatNo");

				Passenger passenger = new Passenger(id, pnr, passengerName, age, gender, seatNo, bookingStatus,
						trainNo);
				return passenger;
			}

		} catch (Exception e) {
			System.out.println("Error in deleting passenger");
			e.printStackTrace();
		}

		return null;
	}

	public void updatePassenger(int passengerId, String seatNo, String bookingStatus) {

		try {
			Connection con = DbConnection.connect();

			PreparedStatement pst = con
					.prepareStatement("update passenger set  seatNo = ? , bookingStatus = ? where passengerId =? ");
			pst.setString(1, seatNo);
			pst.setString(2, bookingStatus);
			pst.setInt(3, passengerId);

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			con.commit();

		} catch (Exception e) {
			System.out.println("Error in updating passenger details after cancelling users");
			e.printStackTrace();
		}
	}

}
