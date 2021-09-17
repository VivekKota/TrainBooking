package com.Train.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Train.model.Ticket;

public class TicketDao {

	public void bookTicket(String source, String destination, int trainNo, int noOfPassengers, int bookedBy) {

		try {
			Connection con = DbConnection.connect();

			PreparedStatement pst = con.prepareStatement(
					"insert into ticket(source,destination, trainNo, noOfPassengers, bookedBy) values(?,?,?,?,?)");
			pst.setString(1, source);
			pst.setString(2, destination);
			pst.setInt(3, trainNo);
			pst.setInt(4, noOfPassengers);
			pst.setInt(5, bookedBy);

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			con.commit();

		} catch (Exception e) {
			System.out.println("Error in booking tickets");
			e.printStackTrace();
		}
	}
	
	public int findPnr()
	{
		try {
			Connection con = DbConnection.connect();

			PreparedStatement pst = con.prepareStatement("Select * from ticket order by pnr desc limit 1");
			ResultSet rs = pst.executeQuery();

			int pnr = 0 ;
			while (rs.next()) {

			    pnr = rs.getInt(1);
			}
			return pnr;

		} catch (Exception e) {
			System.out.println("Error in booking tickets");
			e.printStackTrace();
		}
		return 0;

	}

	public List<Ticket> printOccupancyChart(int trainNo) {

		try {
			Connection con = DbConnection.connect();

			PreparedStatement pst = con.prepareStatement("Select * from ticket where trainNo = ?");
			pst.setInt(1, trainNo);

			ResultSet rs = pst.executeQuery();

			List<Ticket> tickets = new ArrayList<Ticket>();
			while (rs.next()) {
				int pnr = rs.getInt(1);
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				int noOfPassengers = rs.getInt("noOfPassengers");
				int bookedBy = rs.getInt("bookedBy");

				Ticket ticket = new Ticket(pnr, source, destination, trainNo, noOfPassengers, bookedBy);
				tickets.add(ticket);
			}
			return tickets;

		} catch (Exception e) {
			System.out.println("Error in printing Occupancy Chart");
			e.printStackTrace();
		}
		return null;

	}

	public Ticket findTicket(int pnr) {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("Select * from ticket where pnr = ? ");
			pst.setInt(1, pnr);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				int id = rs.getInt(1);
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				int trainNo = rs.getInt("trainNo");
				int noOfPassengers = rs.getInt("noOfPassengers");
				int bookedBy = rs.getInt("bookedBy");

				Ticket ticket = new Ticket(id, source, destination, trainNo, noOfPassengers , bookedBy);
				return ticket;
			}

		} catch (Exception e) {

			System.out.println("Error in finding ticket with pnr ");
			e.printStackTrace();
		}
		return null;

	}

	public void updateTicket(int pnr, int noOfPassengersremain ) {

		try {
			Connection con = DbConnection.connect();

			PreparedStatement pst = con.prepareStatement("update ticket set  noOfPassengers = ? where pnr =? ");
			pst.setInt(1, noOfPassengersremain);
			pst.setInt(2, pnr);

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			con.commit();

		} catch (Exception e) {
			System.out.println("Error in updating ticket after cancelling users");
			e.printStackTrace();
		}

	}

	public void removeTicket(int pnr) {
		
		try {
			Connection con = DbConnection.connect();

			PreparedStatement pst = con.prepareStatement("delete from ticket where pnr =? ");
			pst.setInt(1, pnr);

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			con.commit();

		} catch (Exception e) {
			System.out.println("Error in deleting ticket");
			e.printStackTrace();
		}

	}
		

}
