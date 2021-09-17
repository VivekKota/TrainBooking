package com.Train.model;

public class User {

	private int userId;
	private String userName;
	private String gender;
	private int age;
	private String email;
	private String phoneNo;

	public User() {
		super();
	}

	public User(int userId, String userName, String gender, int age, String email, String phoneNo) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phoneNo = phoneNo;
	}



	public int getUserid() {
		return userId;
	}

	public void setUserid(int userid) {
		this.userId = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", gender=" + gender + ", age=" + age + ", email="
				+ email + ", phoneNo=" + phoneNo + "]";
	}

}
