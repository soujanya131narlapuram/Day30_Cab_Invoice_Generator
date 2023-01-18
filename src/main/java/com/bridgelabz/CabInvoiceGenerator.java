package com.bridgelabz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CabInvoiceGenerator {
    public static final double COST_PER_KILOMETER = 10;
    public static final double COST_PER_MINUTE = 1;
    public static final double MINIMUM_FARE = 5;
    public static HashMap<String, ArrayList<Ride>> rideRepository = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add a ride for user");
            System.out.println("2. Generate Invoice");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();
            if (choice == 1) {
                System.out.print("Enter user id: ");
                String userId = input.nextLine();
                System.out.print("Enter distance in kilometers: ");
                double distance = input.nextDouble();
                System.out.print("Enter time in minutes: ");
                double time = input.nextDouble();
                addRide(userId, distance, time);
            } else if (choice == 2) {
                System.out.print("Enter user id: ");
                String userId = input.nextLine();
                ArrayList<Ride> rides =new ArrayList<>();
                if (rideRepository.containsKey(userId)){
                    rides = rideRepository.get(userId);
                }
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

    public static void addRide(String userId, double distance, double time){
        if(rideRepository.containsKey(userId)){
            rideRepository.get(userId).add(new Ride(distance,time));
        }
        else{
            ArrayList<Ride> rides = new ArrayList<>();
            rides.add(new Ride(distance,time));
            rideRepository.put(userId,rides);
        }
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
