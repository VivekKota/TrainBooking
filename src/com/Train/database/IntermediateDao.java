package com.Train.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Train.model.IntermediateStations;

public class IntermediateDao {

	public void addIntermediateStation(int trainNo, String intermediateStation) {
		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("insert into intermediateStations(trainNo,intermediateStation) values(?,?)");
			pst.setInt(1, trainNo);
			pst.setString(2, intermediateStation);

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			con.commit();

		} catch (Exception e) {

			System.out.println("Error in adding intermediate station to the database");
			e.printStackTrace();
		}

	}

	public List<IntermediateStations> getIntermediates(int trainNo) {
		try {
			
			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("Select * from intermediateStations where trainNo = ? order by id ");
			pst.setInt(1, trainNo);
			ResultSet rs = pst.executeQuery();


			List<IntermediateStations> intermediateStations = new ArrayList<IntermediateStations>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String intermediateStation = rs.getString("intermediateStation");

				IntermediateStations intermediates = new IntermediateStations(id, trainNo, intermediateStation);
				intermediateStations.add(intermediates);
			}
			return intermediateStations;

		} catch (Exception e) {

			System.out.println("Error in getting intermediateStations");
			e.printStackTrace();
		}

		return null;
	}

}
