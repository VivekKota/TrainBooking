package com.Train.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Train.database.CancelledTicketDao;
import com.Train.database.DbConnection;
import com.Train.database.IntermediateDao;
import com.Train.database.PassengerDao;
import com.Train.database.TicketDao;
import com.Train.database.TrainDao;
import com.Train.database.UserDao;
import com.Train.model.CancelledTicket;
import com.Train.model.IntermediateStations;
import com.Train.model.Passenger;
import com.Train.model.Ticket;
import com.Train.model.Train;
import com.Train.model.User;

public class Main {

	public Train searchTrain(String source, String destination) {

		IntermediateDao intermediateDao = new IntermediateDao();

		int flag1 = 0, flag2 = 0;

		List<String> intermediates = new ArrayList<String>();
		List<Train> trains = new ArrayList<Train>();
		List<IntermediateStations> intermediateStations = new ArrayList<IntermediateStations>();

		TrainDao trainDao = new TrainDao();
		trains = trainDao.showAllTrains();

		for (int i = 0; i < trains.size(); i++) {

			intermediates.add(trains.get(i).getSource());

			intermediateStations = intermediateDao.getIntermediates(trains.get(i).getTrainNo());
			for (int z = 0; z < intermediateStations.size(); z++) {
				intermediates.add(intermediateStations.get(z).getIntermediateStation());
			}

			intermediates.add(trains.get(i).getDestination());

			for (int j = 0; j < intermediates.size() - 1; j++) {
				if (source.equalsIgnoreCase(intermediates.get(j))) {
					flag1 = 1;
					break;
				}
			}

			for (int k = 1; k < intermediates.size(); k++) {
				if (destination.equalsIgnoreCase(intermediates.get(k))) {
					flag2 = 1;
					break;
				}
			}
			if (flag1 == 1 && flag2 == 1) {
				return trains.get(i);

			} else {

				intermediates.clear();
				flag1 = 0;
				flag2 = 0;
				continue;
			}
		}
		return null;
	}

	public static void main(String[] args) {

		Main main = new Main();
		Scanner sc = new Scanner(System.in);

		try {

			User user = new User();
			Train train = new Train();
			Ticket ticket = new Ticket();
			Passenger passenger = new Passenger();

			UserDao userDao = new UserDao();
			TrainDao trainDao = new TrainDao();
			TicketDao ticketDao = new TicketDao();
			PassengerDao passengerDao = new PassengerDao();
			IntermediateDao intermediateDao = new IntermediateDao();
			CancelledTicketDao cancelledTicketDao = new CancelledTicketDao();

			List<String> intermediates = new ArrayList<String>();
			List<Train> trains = new ArrayList<Train>();
			List<Ticket> tickets = new ArrayList<Ticket>();
			List<Passenger> passengers = new ArrayList<Passenger>();
			List<CancelledTicket> cancelledticket = new ArrayList<CancelledTicket>();
			List<IntermediateStations> intermediateStations = new ArrayList<IntermediateStations>();

			String source, destination, trainName, intermediateStation, userName, gender, seatNo, email, phone,
					bookingStatus, exit;
			int trainNo, noOfSeatsAvailable, age, userId, updatedSeatsAvailable;

			boolean done = false;
			while (!done) {

				System.out.println("1. Add train");
				System.out.println("2. Show all Trains");
				System.out.println("3. Add a user");
				System.out.println("4. Book a ticket");
				System.out.println("5. cancel a ticket");
				System.out.println("6. Print Occupancy chart");
				System.out.println("7. Update passengers");

				System.out.println("8. Exit");

				int choice = 0;
				System.out.println("Enter your choice");
				choice = sc.nextInt();
				switch (choice) {

				case 1:

					System.out.println("Enter Train Number");
					trainNo = sc.nextInt();
					System.out.println("Enter Train Name");
					trainName = sc.next();
					System.out.println("Enter Source");
					source = sc.next();
					System.out.println("Enter Destination");
					destination = sc.next();

					System.out.println("Enter intermediate stations and type exit at last");
					while (!sc.hasNext("exit")) {

						intermediateStation = sc.next();
						intermediateDao.addIntermediateStation(trainNo, intermediateStation);
					}

					exit = sc.next();
					System.out.println("Enter No of Seats Available");
					noOfSeatsAvailable = sc.nextInt();
					trainDao.addTrain(new Train(trainNo, trainName, source, destination, noOfSeatsAvailable));

					break;

				case 2:

					trains = trainDao.showAllTrains();
					for (int i = 0; i < trains.size(); i++) {
						System.out.println(trains.get(i));
					}
					break;

				case 3:

					System.out.println("Enter UserName");
					userName = sc.next();
					System.out.println("Enter Gender");
					gender = sc.next();
					System.out.println("Enter age");
					age = sc.nextInt();
					System.out.println("Enter Email");
					email = sc.next();
					System.out.println("Enter Phone");
					phone = sc.next();
					userDao.addUser(userName, gender, age, email, phone);

					break;

				case 4:

					System.out.println("Enter User Id");
					userId = sc.nextInt();
					user = userDao.findUser(userId);
					if (user != null) {

						System.out.println("Enter Source");
						source = sc.next();
						System.out.println("Enter Destination");
						destination = sc.next();
						train = main.searchTrain(source, destination);
						int flag3 = 0;

						if (train != null) {

							System.out.println("Enter no of passengers");
							int noOfPassengers = sc.nextInt();

							ticketDao.bookTicket(source, destination, train.getTrainNo(), noOfPassengers, userId);
							int pnr = ticketDao.findPnr();

							int seats = train.getNoOfSeatsAvailable();
							for (int k = 0; k < noOfPassengers; k++) {
								System.out.println("Enter  passenger Name");
								userName = sc.next();
								System.out.println("Enter  passenger age");
								age = sc.nextInt();
								System.out.println("Enter  passenger gender");
								gender = sc.next();
								if (seats > 0) {
									seatNo = Integer.toString(seats);
									bookingStatus = "Booked";
									seats--;

								} else {
									seatNo = "WL" + Integer.toString(Math.abs(seats - 1));
									bookingStatus = "WaitingList";
									seats--;

								}

								passengerDao.addPassenger(pnr, userName, age, gender, seatNo, bookingStatus,
										train.getTrainNo());
							}
							updatedSeatsAvailable = seats;

							trainDao.updateTrain(updatedSeatsAvailable, train.getTrainNo());

							System.out.println("Ticket Info");
							System.out.println("From :" + source);
							System.out.println("To : " + destination);
							System.out.println("Train Number : " + train.getTrainNo());
							passengers = passengerDao.findPassengers(pnr);
							for (int i = 0; i < passengers.size(); i++) {
								System.out.println("Passenger :" + passengers.get(i).getPassengerName()
										+ "                Seat No: " + passengers.get(i).getSeatNo());
							}

							flag3 = 1;

						}

						if (flag3 == 0) {

							int flag1 = 0, flag2 = 0;
							System.out.println("No Direct Trains are Avaialable Checking for Intermediate Trains");

							Train train1 = new Train();
							Train train2 = new Train();
							trains = trainDao.showAllTrains();

							for (int i = 0; i < trains.size(); i++) {

								intermediates.add(trains.get(i).getSource());
								intermediateStations = intermediateDao.getIntermediates(trains.get(i).getTrainNo());
								for (int z = 0; z < intermediateStations.size(); z++) {
									intermediates.add(intermediateStations.get(z).getIntermediateStation());
								}
								intermediates.add(trains.get(i).getDestination());

								for (int j = 0; j < intermediates.size(); j++) {
									if (source.equalsIgnoreCase(intermediates.get(j))) {
										train1 = trains.get(i);
										flag1 = 1;
										break;
									}
								}

								for (int k = 0; k < intermediates.size(); k++) {
									if (destination.equalsIgnoreCase(intermediates.get(k))) {
										train2 = trains.get(i);
										flag2 = 1;
										break;
									}
								}
								intermediates.clear();
							}

							if (flag1 == 1 && flag2 == 1) {

								List<String> train1Stations = new ArrayList<String>();
								List<String> train2Stations = new ArrayList<String>();

								train1Stations.add(train1.getSource());
								intermediateStations = intermediateDao.getIntermediates(train1.getTrainNo());
								for (int z = 0; z < intermediateStations.size(); z++) {
									train1Stations.add(intermediateStations.get(z).getIntermediateStation());
								}
								train1Stations.add(train1.getDestination());

								train2Stations.add(train2.getSource());
								intermediateStations = intermediateDao.getIntermediates(train2.getTrainNo());
								for (int z = 0; z < intermediateStations.size(); z++) {
									train2Stations.add(intermediateStations.get(z).getIntermediateStation());
								}
								train2Stations.add(train2.getDestination());

								ArrayList<String> commonStations = new ArrayList<String>(train1Stations);
								commonStations.retainAll(train2Stations);

								train1 = main.searchTrain(source, commonStations.get(0));
								train2 = main.searchTrain(commonStations.get(0), destination);

								if (train1 != null && train2 != null) {

									System.out.println("Intermediate trains are available");
									System.out.println("Enter no of passengers");
									int noOfPassengers = sc.nextInt();

									ticketDao.bookTicket(source, commonStations.get(0), train1.getTrainNo(),
											noOfPassengers, userId);
									int pnr1 = ticketDao.findPnr();

									ticketDao.bookTicket(commonStations.get(0), destination, train2.getTrainNo(),
											noOfPassengers, userId);
									int pnr2 = ticketDao.findPnr();

									int seats1 = train1.getNoOfSeatsAvailable();
									int seats2 = train2.getNoOfSeatsAvailable();

									for (int k = 0; k < noOfPassengers; k++) {
										System.out.println("Enter  passenger Name");
										userName = sc.next();
										System.out.println("Enter  passenger age");
										age = sc.nextInt();
										System.out.println("Enter  passenger gender");
										gender = sc.next();
										if (seats1 > 0) {
											seatNo = Integer.toString(seats1);
											bookingStatus = "Booked";
											seats1--;

										} else {
											seatNo = "WL" + Integer.toString(Math.abs(seats1 - 1));
											bookingStatus = "WaitingList";
											seats1--;

										}

										passengerDao.addPassenger(pnr1, userName, age, gender, seatNo, bookingStatus,
												train1.getTrainNo());
										if (seats2 > 0) {
											seatNo = Integer.toString(seats2);
											bookingStatus = "Booked";
											seats2--;

										} else {
											seatNo = "WL" + Integer.toString(Math.abs(seats2 - 1));
											bookingStatus = "WaitingList";
											seats2--;
										}

										passengerDao.addPassenger(pnr2, userName, age, gender, seatNo, bookingStatus,
												train2.getTrainNo());

									}

									trainDao.updateTrain(seats1, train1.getTrainNo());
									trainDao.updateTrain(seats2, train2.getTrainNo());

									System.out.println("Ticket Info");
									System.out.println("From :" + source);
									System.out.println("To : " + destination);
									System.out.println("Train Number : " + train1.getTrainNo());
									passengers = passengerDao.findPassengers(pnr1);
									for (int i = 0; i < passengers.size(); i++) {
										System.out.println("Passenger :" + passengers.get(i).getPassengerName()
												+ "                Seat No: " + passengers.get(i).getSeatNo());
									}
									System.out.println();
									System.out.println("Train Number : " + train2.getTrainNo());
									passengers = passengerDao.findPassengers(pnr2);
									for (int i = 0; i < passengers.size(); i++) {
										System.out.println("Passenger :" + passengers.get(i).getPassengerName()
												+ "                Seat No: " + passengers.get(i).getSeatNo());
									}

								} else {
									System.out.println("No intermediate stations are available");
								}

							} else {
								System.out.println("No Trains are avialble between the stations");
							}

						}

					}

					else {
						System.out.println("To book a ticket a registered User is required");
					}

					break;

				case 5:

					System.out.println("Enter PNR Number");
					int pnr = sc.nextInt();
					ticket = ticketDao.findTicket(pnr);
					if (ticket != null) {

						passengers = passengerDao.findPassengers(pnr);
						for (int i = 0; i < passengers.size(); i++) {
							System.out.println(passengers.get(i));
						}

						System.out.println("Enter passengerId to cancel the ticket respectively and enter exit at last");

						int cancelledTickets = 0;
						while (!sc.hasNext("exit")) {
							String seat = null;
							System.out.println("Enter passengerId");
							int id = sc.nextInt();
							for (int i = 0; i < passengers.size(); i++) {
								if (id == passengers.get(i).getPassengerId()) {
									seat = passengers.get(i).getSeatNo();
									break;
								} else {
									continue;
								}
							}
							bookingStatus = "WaitingList";
							passenger = passengerDao.checkWaitingListPassenger(ticket.getTrainNo(), bookingStatus);
							if (passenger != null) {
								bookingStatus = "Booked";
								passengerDao.updatePassenger(passenger.getPassengerId(), seat, bookingStatus);
							} else {
								cancelledTicketDao.cancelledTickets(ticket.getTrainNo(), seat);
							}
							passengerDao.removePassenger(id);
							cancelledTickets++;

						}
						exit = sc.next();
						int noOfPassengersremain = ticket.getNoOfPassengers() - cancelledTickets;
						if (noOfPassengersremain == 0) {
							ticketDao.removeTicket(pnr);
							System.out.println("Ticket removed");
						} else {
							ticketDao.updateTicket(pnr, noOfPassengersremain);
							System.out.println("Ticket updated");
						}

					} else {
						System.out.println("Entered Pnr is incorrect ");
					}

					break;

				case 6:

					System.out.println("Enter train No");
					trainNo = sc.nextInt();
					tickets = ticketDao.printOccupancyChart(trainNo);
					System.out.println("Occupancy Chart for Train Number : " + trainNo);
					for (int i = 0; i < tickets.size(); i++) {
						System.out.println();
						System.out.println("Source : " + tickets.get(i).getSource() + "  Destination :"
								+ tickets.get(i).getDestination());
						passengers = passengerDao.findPassengers(tickets.get(i).getPnr());
						for (int j = 0; j < passengers.size(); j++) {
							System.out.println("Passenger :" + passengers.get(j).getPassengerName()
									+ "                Seat No: " + passengers.get(j).getSeatNo());
						}
					}
					break;

				case 7:

					System.out.println("Enter train No");
					trainNo = sc.nextInt();
					cancelledticket = cancelledTicketDao.findCancelledTickets(trainNo);
					for (int i = 0; i < cancelledticket.size(); i++) {
						bookingStatus = "WaitingList";
						passenger = passengerDao.checkWaitingListPassenger(ticket.getTrainNo(), bookingStatus);
						if (passenger != null) {
							bookingStatus = "Booked";
							passengerDao.updatePassenger(passenger.getPassengerId(), cancelledticket.get(i).getSeatNo(),
									bookingStatus);
						} else {
							break;
						}
					}

					break;

				case 8:
					done = true;
					System.out.println("Bye");
					break;

				}
			}

		} catch (

		Exception e) {

			System.out.println("Error in main block");
			e.printStackTrace();

		} finally {
			sc.close();
			DbConnection.close();
		}

	}

}
