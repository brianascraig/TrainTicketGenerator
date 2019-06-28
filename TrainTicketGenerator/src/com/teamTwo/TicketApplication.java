package com.teamTwo;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static java.lang.System.exit;

public class TicketApplication {
	public static void main(String[] args) {
		Scanner scr = new Scanner(System.in);

		try {
//// Enter train number needs to be pn top
			// if you enter a date before current date there needs to be an excpetion message
			System.out.println("Enter travel Date");
			String dateStr = scr.nextLine();
			System.out.println("Entered Date is " + dateStr);

			System.out.println("Enter the Train number");
			int trainNo = scr.nextInt();
			Train trn = TrainDAO.findTrain(trainNo);
			if (trn == null) {
				System.out.println("train with given train number does not exist");
				exit(0);
			}


			String pattern = "dd/MM/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Date travelDate = simpleDateFormat.parse(dateStr);
			//TODO
			Ticket ticket = new Ticket(travelDate, trn);//creating ticket object here with train and date

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


		} catch (ParseException | SQLException e) {
			e.printStackTrace();
		}


	}
}
