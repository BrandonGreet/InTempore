package com.application.users.intempore;


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
        this.address = data[3];
        this.city = data[4];
        this.zipCode = data[6];
        this.phone = data[8];
        this.website = data[9];
        this.hours = data[10];
        this.operationSeason = data[11];
        this.months = getMonths();
    }


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
        months.put("Novembe", 11);
        months.put("December", 12);
        return months;
    }

    public boolean isInSeason() {
        if (this.operationSeason.equals("Year-round")) {
            return true;
        }
        String[] opperationArr = this.operationSeason.split("-");
        String[] begin = opperationArr[0].split(" ");
        String[] end = opperationArr[1].split(" ");
        int start_month = this.months.get(begin[0]);
        int start_day = Integer.parseInt(begin[1]);
        int end_month = this.months.get(end[0]);
        int end_day = Integer.parseInt(end[1]);
        if (this.currMonth > start_month && this.currMonth < end_month) {
            return true;
        } else if (this.currMonth == start_month) {
            return this.currDay > start_day;
        } else if (this.currMonth == end_month) {
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

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
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
