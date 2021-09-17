package com.Train.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Train.model.CancelledTicket;

public class CancelledTicketDao {

	public void cancelledTickets(int trainNo, String seatNo) {

		try {
			Connection con = DbConnection.connect();

			PreparedStatement pst = con.prepareStatement("insert into cancelledTickets(trainNo, seatNo) values(?,?)");
			pst.setInt(1, trainNo);
			pst.setString(2, seatNo);

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			con.commit();

		} catch (Exception e) {
			System.out.println("Error in adding cancelled tickets");
			e.printStackTrace();
		}

	}

	public List<CancelledTicket> findCancelledTickets(int trainNo) {

		try {
			Connection con = DbConnection.connect();

			PreparedStatement pst = con.prepareStatement("Select * from cancelledtickets where trainNo = ?");
			pst.setInt(1, trainNo);

			ResultSet rs = pst.executeQuery();

			List<CancelledTicket> cancelledTickets = new ArrayList<CancelledTicket>();

			while (rs.next()) {
				String seatNo = rs.getString("seatNo");

				CancelledTicket ticket = new CancelledTicket(trainNo, seatNo);
				cancelledTickets.add(ticket);
			}
			return cancelledTickets;

		} catch (Exception e) {
			System.out.println("Error in printing Occupancy Chart");
			e.printStackTrace();
		}
		return null;

	}

}
