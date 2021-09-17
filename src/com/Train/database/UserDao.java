package com.Train.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Train.model.User;

public class UserDao {

	public void addUser(String userName, String gender, int age, String email, String phoneNo) {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("insert into user(userName,gender,age, email, phoneNo) values(?,?,?,?,?)");
			pst.setString(1, userName);
			pst.setString(2, gender);
			pst.setInt(3, age);
			pst.setString(4,email);
			pst.setString(5, phoneNo);
			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			con.commit();

		} catch (Exception e) {

			System.out.println("Error in adding user to the database");
			e.printStackTrace();
		}

	}

	public User findUser(int userId) {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("Select * from user where userId = ? ");
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("userId");
				String userName = rs.getString("userName");
				String gender = rs.getString("gender");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String phoneNo = rs.getString("phoneNo");

				User user = new User(id, userName, gender, age, email, phoneNo);
				return user;
			}

		} catch (Exception e) {

			System.out.println("Error in finding user");
			e.printStackTrace();
		}
		return null;

	}


}
