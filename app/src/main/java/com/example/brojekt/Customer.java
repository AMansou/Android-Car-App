package com.example.brojekt;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Customer {
    //public static ArrayList<Customer> customersArrayList=new ArrayList<Customer>();
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String country;
    private String City;
    private String Phone;
    private String Gender;
    private String cars;
    private String favorites;
    private boolean admin;
    public Customer() {

    }

    public Customer(String email, String firstName, String lastName, String password, String country, String city, String phone, String gender) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.country = country;
        this.admin = false;
        City = city;
        Phone = phone;
        Gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
    public void setCars(String cars){this.cars=cars;}
    public String getCars(){return cars;}
    public void setFavorites(String favorites){this.favorites=favorites;}
    public String getFavorites(){return favorites;}

    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", City='" + City + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Reserved Cars: '"+cars+'\''+
                ", Favorite Cars: '"+favorites+'\''+
                '}';
    }
}
