package com.bridgelabz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CabInvoiceGenerator {
    public static final double NORMAL_RIDE_COST_PER_KILOMETER = 10;
    public static final double NORMAL_RIDE_COST_PER_MINUTE = 1;
    public static final double NORMAL_RIDE_MINIMUM_FARE = 5;
    public static final double PREMIUM_RIDE_COST_PER_KILOMETER = 15;
    public static final double PREMIUM_RIDE_COST_PER_MINUTE = 2;
    public static final double PREMIUM_RIDE_MINIMUM_FARE = 20;
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
                System.out.print("Enter ride type (Normal/Premium): ");
                String rideType = input.nextLine();
                addRide(userId, distance, time, rideType);
            } else if (choice == 2) {
                System.out.print("Enter user id: ");
                String userId = input.nextLine();
                ArrayList<Ride> rides = new ArrayList<>();
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

    public static void addRide(String userId, double distance, double time, String rideType){
        if(rideRepository.containsKey(userId)){
            rideRepository.get(userId).add(new Ride(distance,time,rideType));
        }
        else{
            ArrayList<Ride> rides = new ArrayList<>();
            rides.add(new Ride(distance,time,rideType));
            rideRepository.put(userId,rides);
        }
    }

    public static double calculateFare(ArrayList<Ride> rides) {
        double fare = 0;
        for (Ride ride : rides) {
            if(ride.rideType.equalsIgnoreCase("Normal")){
                fare += ride.distance * NORMAL_RIDE_COST_PER_KILOMETER + ride.time * NORMAL_RIDE_COST_PER_MINUTE;
                fare = fare < NORMAL_RIDE_MINIMUM_FARE * rides.size() ? NORMAL_RIDE_MINIMUM_FARE * rides.size() : fare;
            }else if(ride.rideType.equalsIgnoreCase("Premium")){
                fare += ride.distance * PREMIUM_RIDE_COST_PER_KILOMETER + ride.time * PREMIUM_RIDE_COST_PER_MINUTE;
                fare = fare < PREMIUM_RIDE_MINIMUM_FARE * rides.size() ? PREMIUM_RIDE_MINIMUM_FARE * rides.size() : fare;
            }
        }
        return fare;
    }

    static class Ride {
        public double distance;
        public double time;
        public String rideType;

        public Ride(double distance, double time, String rideType) {
            this.distance = distance;
            this.time = time;
            this.rideType = rideType;
        }
    }
}


