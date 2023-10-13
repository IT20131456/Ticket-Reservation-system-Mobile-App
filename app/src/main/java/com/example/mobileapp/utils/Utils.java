package com.example.mobileapp.utils;

import android.icu.text.SimpleDateFormat;

import com.example.mobileapp.data.model.Reservation;
import com.example.mobileapp.data.model.TrainSchedule;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Utility class containing various helper methods.
 */
public class Utils {

    /**
     * Get the position of a string in a list.
     */
    public static int getPosition(List<String> stringList, String from) {
        return stringList.indexOf(from);
    }

    /**
     * Validate reservation data and return an error message if invalid.
     */
    public static String validateData(Reservation newReservation, TrainSchedule selectedSchedule) {
        if (newReservation.getNumber_of_tickets() > 4) {
            return "Maximum of 4 tickets are allowed for a NIC";
        } else if (getPosition(selectedSchedule.getIntermediate_stops(), newReservation.getFrom()) >
                getPosition(selectedSchedule.getIntermediate_stops(), newReservation.getTo())) {
            return "Please select a valid starting and ending station";
        } else if (newReservation.getNumber_of_tickets() < 1) {
            return "Please provide the ticket count";
        }
        return "";
    }

    /**
     * Calculate the total cost based on the selected class and the number of tickets.
     */
    public static int getTotal(int selectedClass, int noOfTickets) {
        if (selectedClass == 1) {
            return 1000 * noOfTickets;
        } else if (selectedClass == 2) {
            return 200 * noOfTickets;
        } else {
            return 30 * noOfTickets;
        }
    }

    /**
     * Convert a ticket class string to its corresponding numeric value.
     */
    public static int getTrainClassAsNumber(String toString) {
        if (toString.equals("First-Class")) {
            return 1;
        } else if (toString.equals("Second-Class")) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * Convert a numeric ticket class value to its string representation.
     */
    public static String getTicketClassAsString(Integer ticketClass) {
        if (ticketClass == 1){
            return "First-Class";
        } else if (ticketClass == 2) {
            return "Second-Class";
        } else {
            return "Third-Class";
        }
    }

    /**
     * Generate a random number between 1 and 10,000.
     */
    public static String getRandomNumber() {
        double randomDouble = Math.random();
        int min = 1;
        int max = 10000;
        int randomNumber = (int) (randomDouble * (max - min + 1)) + min;
        return Integer.toString(randomNumber);
    }

    /**
     * Check if a date is at least 5 days in the future.
     */
    public static boolean isValidUpdate(String resDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date currentDate = new Date();
        Date reservationDate = null; // Initialize to null

        try {
            reservationDate = sdf.parse(resDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false; // Parsing failed, so it's not a valid date
        }

        if (reservationDate != null) {
            Calendar currentCalendar = Calendar.getInstance();
            Calendar reservationCalendar = Calendar.getInstance();

            currentCalendar.setTime(currentDate);
            reservationCalendar.setTime(reservationDate);

            long daysDifference = (reservationCalendar.getTimeInMillis() - currentCalendar.getTimeInMillis()) / (1000 * 60 * 60 * 24);

            return daysDifference >= 5;
        }

        return false; // Parsing resulted in null date
    }

    /**
     * Check if a date is in the past.
     */
    public static boolean isDateInPast(String dateToCheck) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date currentDate = new Date();

        try {
            Date date = sdf.parse(dateToCheck);
            return date.before(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false; // Consider it as not in the past if parsing fails
        }
    }
}
