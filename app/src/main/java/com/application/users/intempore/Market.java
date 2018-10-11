package com.application.users.intempore;

/**
 * Class outlines a market
 * read database into a collection of markets
 * getters for fields that users will need to see: address, distance, name, etc.
 * methods to calculate distance from user and check if the market is currently operating
 *
 * TODO: create methods to get users location and date and update the variables associated with date and location
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Market {
    private String county;
    private String name;
    private String address;
    private String city;
    private String zipCode;
    private String phone;
    private String website;
    private String hours;
    private String operationSeason;

    private double longitude;
    private double latitude;

    private String contact;
    private boolean inSeason;

    private double userLat;
    private double userLong;
    private int currMonth;
    private int currDay;

    private HashMap<String, Integer> months;

    private ArrayList<Market> markets;

    public Market(String[] data) {
        this.county = data[0];
        this.name = data[1];
        // index 3 is street address, 4 is city, 5 is state, 6 is zip code
        this.address = data[3] + ", " + data[4] + ", " + data[5] + ", " + data[6];
        this.phone = data[8];
        this.website = data[9];
        this.hours = data[10];
        this.operationSeason = data[11];
        this.months = getMonths();
    }

    /**
     * creates dictionary for easily translating string months into numbers
     * @return string to number month dictionary
     */
    private HashMap<String, Integer> getMonths() {
        HashMap<String, Integer> months = new HashMap<>();
        months.put("January", 1);
        months.put("February", 2);
        months.put("March", 3);
        months.put("April", 4);
        months.put("May", 5);
        months.put("June", 6);
        months.put("July", 7);
        months.put("August", 8);
        months.put("September", 9);
        months.put("October", 10);
        months.put("November", 11);
        months.put("December", 12);
        return months;
    }

    /**
     * checks if the given market is currently operating
     * @return true if in season, false otherwise
     */
    public boolean isInSeason() {
        if (this.operationSeason.equals("Year-round")) {
            return true;
        }
        // puts operation season in array form ["January 1", "August 3"]
        String[] opperationArr = this.operationSeason.split("-");

        // gets a start array and end array in form ["January", "1"] and ["August", "3"]
        String[] begin = opperationArr[0].split(" ");
        String[] end = opperationArr[1].split(" ");

        // start and end month determined with the months hash map, ex: month.get("January") returns 1
        int start_month = this.months.get(begin[0]);
        // start/end day will always be a number in the database, so parseint is used
        int start_day = Integer.parseInt(begin[1]);
        int end_month = this.months.get(end[0]);
        int end_day = Integer.parseInt(end[1]);

        // if the current month is between the start and end it is open
        if (this.currMonth > start_month && this.currMonth < end_month) {
            return true;
        }
        // checks if the day is passed the opening day when the market opened in the current month
        else if (this.currMonth == start_month) {
            return this.currDay > start_day;
        }
        // checks if the day is before the end day if the market closes in the current month
        else if (this.currMonth == end_month) {
            return this.currDay < end_day;
        } return false;
    }

    /**
     * get distance from user to market
     * @return double distance
     */
    public double getDistane() {
        return Math.sqrt(Math.pow((this.latitude - this.userLat), 2) +
                Math.pow((this.longitude - this.userLong), 2));
    }

    public String getCounty() {
        return county;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getHours() {
        return hours;
    }

    public String getContact() {
        return contact;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }
}
