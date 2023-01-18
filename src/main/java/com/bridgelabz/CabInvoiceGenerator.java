package com.bridgelabz;

import java.util.ArrayList;
import java.util.Scanner;

public class CabInvoiceGenerator {
    public static final double COST_PER_KILOMETER = 10;
    public static final double COST_PER_MINUTE = 1;
    public static final double MINIMUM_FARE = 5;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Ride> rides = new ArrayList<>();
        while (true) {
            System.out.println("1. Add Ride");
            System.out.println("2. Generate Invoice");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();
            if (choice == 1) {
                System.out.print("Enter distance in kilometers: ");
                double distance = input.nextDouble();
                System.out.print("Enter time in minutes: ");
                double time = input.nextDouble();
                rides.add(new Ride(distance, time));
            } else if (choice == 2) {
                double fare = calculateFare(rides);
                System.out.println("Total number of rides: " + rides.size());
                System.out.println("Total fare for the rides: " + fare);
                System.out.println("Average fare per ride: " + fare/rides.size());
            } else if (choice == 3) {
                break;
            }
        }
        input.close();
    }

    public static double calculateFare(ArrayList<Ride> rides) {
        double fare = 0;
        for (Ride ride : rides) {
            fare += ride.distance * COST_PER_KILOMETER + ride.time * COST_PER_MINUTE;
        }
        return fare < MINIMUM_FARE * rides.size() ? MINIMUM_FARE * rides.size() : fare;
    }

    static class Ride {
        public double distance;
        public double time;

        public Ride(double distance, double time) {
            this.distance = distance;
            this.time = time;
        }
    }
}
