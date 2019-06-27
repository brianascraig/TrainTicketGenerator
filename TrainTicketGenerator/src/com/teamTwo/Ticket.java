package com.teamTwo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

public class Ticket {
    private static int counter = 100;
    private String pnr;
    private Date travelDate;
    private Train train;
    private TreeMap<Passenger, Integer> passengers;

    public Ticket(Date travelDate, Train train) {
        this.travelDate = travelDate;
        this.train = train;
        this.passengers = new TreeMap<Passenger, Integer>();
    }

    private String generatePNR() {
        StringBuilder pnr = new StringBuilder();

        pnr.append(this.train.getSource().substring(0,1));
        pnr.append(this.train.getDestination().substring(0,1) + "_");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        pnr.append(sdf.format(this.getTravelDate()) + "_");
        pnr.append(this.getCounter());

        this.setPnr(pnr.toString());
        this.counter++;

        return pnr.toString();
    }

    private double calcPassengerFare(Passenger psngrObj) {
        if (psngrObj.getAge() <= 12) {
            return train.getTicketPrice() * 0.5;
        } else if (psngrObj.getAge() >= 60) {
            return train.getTicketPrice() * 0.6;
        } else if (psngrObj.getGender() == 'F') {
            return train.getTicketPrice() * 0.25;
        }else{
            return train.getTicketPrice();
        }
    }

    public void addPassenger(String name, int age, char gender) {
        Passenger addPsngr = new Passenger(name, age, gender);//creating passenger object
        double fare = calcPassengerFare(addPsngr);//calculating fare
        passengers.put(addPsngr, Integer.valueOf((int) fare));//adding passenger to the tree map
    }

    private double calculateTotalTicketPrice() {
        double totalPrice = 0;

        for(Passenger psngr : passengers.keySet()) {
            Integer fare = (Integer) passengers.get(psngr);
            totalPrice += fare;
        }

        return totalPrice;
    }

    /**
     * PNR : -------------
     * Train no : -----
     * Train Name : -------------
     * From : -------------
     * To : -------------
     * Travel Date : dd/mm/yyyy
     * Passengers :
     * Name Age Gender Fare
     * ------------------- --- ------- x,xxx.xx
     * ------------------- --- ------- x,xxx.xx
     * ------------------- --- ------- x,xxx.xx
     * Total Price : xx,xxx.xx
     * @return
     */
    private StringBuilder generateTicket() {
        StringBuilder ticketStr = new StringBuilder();

        ticketStr.append("PNR : " + this.getPnr() + "\n");
        ticketStr.append("Train no : " + this.getTrain().getTrainNo() + "\n");
        ticketStr.append("Train Name : " + this.getTrain().getTrainName() + "\n");
        ticketStr.append("From : " + this.getTrain().getSource() + "\n");
        ticketStr.append("To : " + this.getTrain().getDestination() + "\n");
        ticketStr.append("Travel Date : " + this.getTravelDate() + "\n");
        ticketStr.append("Passengers : \n");
        ticketStr.append("Name              Age                 Gender          Fare\n");

        for(Passenger psngr : passengers.keySet()) {
            Integer fare = (Integer) passengers.get(psngr);

            ticketStr.append(psngr.getName() +"         " + psngr.getAge() + "              " + psngr.getGender() + "           " + fare + "\n");

        }

        ticketStr.append("Total Price: " + this.calculateTotalTicketPrice());

        return ticketStr;
    }

    public void writeTicket() {
        String fileName = generatePNR();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("C:\\swapna\\booking\\" + fileName + ".txt");
            fos.write(generateTicket().toString().getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public TreeMap<Passenger, Integer> getPassengers() {
        return passengers;
    }

    public void setPassengers(TreeMap<Passenger, Integer> passengers) {
        this.passengers = passengers;
    }
}
