package com.example.brojekt;

public class Car {
    private String make;
    private String model;
    private int year;
    private int distance;
    private int price;
    private boolean accidents;
    private boolean offers;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Car(String make, String model, int year, int distance, int price, boolean accidents,
               boolean offers){
        this.make = make;
        this.model = model;
        this.year = year;
        this.distance = distance;
        this.price = price;
        this.accidents = accidents;
        this.offers = offers;
    }

    public Car(){
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAccidents() {
        return accidents;
    }

    public void setAccidents(boolean accidents) {
        this.accidents = accidents;
    }

    public boolean isOffers() {
        return offers;
    }

    public void setOffers(boolean offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", distance=" + distance +
                ", price=" + price +
                ", accidents=" + accidents +
                ", offers=" + offers +
                '}';
    }
}
