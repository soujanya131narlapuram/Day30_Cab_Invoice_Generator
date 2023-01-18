package com.bridgelabz;

import java.util.Scanner;

public class CabInvoiceGenerator {
    public static final double COST_PER_KILOMETER = 10;
    public static final double COST_PER_MINUTE = 1;
    public static final double MINIMUM_FARE = 5;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter distance in kilometers: ");
        double distance = input.nextDouble();
        System.out.print("Enter time in minutes: ");
        double time = input.nextDouble();
        double fare = calculateFare(distance, time);
        System.out.println("Total fare for the journey is: " + fare);
        input.close();
    }

    public static double calculateFare(double distance, double time) {
        double fare = distance * COST_PER_KILOMETER + time * COST_PER_MINUTE;
        return fare < MINIMUM_FARE ? MINIMUM_FARE : fare;
    }
}

