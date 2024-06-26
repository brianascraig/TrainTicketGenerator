package com.teamTwo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Scanner;

import static java.lang.System.exit;

class InvalidDateException extends Exception {
	String message;
	public InvalidDateException(String msg)
	{
		super(msg);
		message = msg;
	}
	public String toString()
	{
		return "Invalid Date Exception "+message;
	}
}

class getTicketInfo{
	Scanner scr = new Scanner(System.in);
	Calendar currDate = Calendar.getInstance();
	LocalDate travelDate;
	Train trn;
//	SwingCalendar sc = new SwingCalendar();
	Ticket ticket ;//creating ticket object here with train and date

	public void getTrainNo() {
		try {
			System.out.println("Enter the Train number");
			int trainNo = scr.nextInt();
			trn = TrainDAO.findTrain(trainNo);
			if (trn == null) {
				System.out.println("train with given train number does not exist");
				exit(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getTravelDate() throws InvalidDateException{

		try {
			System.out.println("Enter travel Date:");
			System.out.println("Enter the month");
			int month = scr.nextInt();
			System.out.println("Enter the day");
			int day = scr.nextInt();
			System.out.println("Enter the year");
			int year = scr.nextInt();
			String dateStr = day + "-" + month + "-" + year;
			System.out.println("Entered Date is " + dateStr);
			LocalDate now = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
			//String formattedDate = now.format(formatter);
			travelDate = LocalDate.parse(dateStr, formatter);
			if (travelDate.isBefore(now)) {
				throw new InvalidDateException("Date entered is before the current date ....");
			}

			ticket = new Ticket(travelDate, trn);
		} catch (DateTimeParseException e) {
			throw new InvalidDateException("Not a Valid Date");
		}

	}

	public void getPassengrs() {
//        System.out.println("Travel date is before current date ");
		System.out.println("Enter Number of Passengers");
		int psngrNo = scr.nextInt();

		for (int i = 0; i < psngrNo; i++) {
			Scanner psngrScanner = new Scanner(System.in);
			System.out.println("Enter Passenger Name");
			String name = psngrScanner.nextLine();

			System.out.println("Enter age");
			int age = psngrScanner.nextInt();

			System.out.println("Enter Gender");
			char gender = psngrScanner.next().charAt(0);
			ticket.addPassenger(name, age, gender);//adding passenger to the ticket object
		}
		ticket.writeTicket();
		//TODO
		System.out.println("Ticket Booked with PNR : " + ticket.getPnr());
	}
}

public class TicketApplication {

	public static void main(String[] args) throws InvalidDateException {
	getTicketInfo gti = new getTicketInfo();
		try {
			gti.getTrainNo();
			gti.getTravelDate();
			gti.getPassengrs();
		} catch (InvalidDateException e) {
			System.out.println(e.getMessage());
		}


//
//// Enter train number needs to be pn top
			// if you enter a date before current date there needs to be an excpetion message




			//TODO



		}


	}

