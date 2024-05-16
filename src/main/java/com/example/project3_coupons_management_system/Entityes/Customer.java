package com.example.project3_coupons_management_system.Entityes;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name="Customer")
public class Customer {

    @Id
    @GeneratedValue
    int id;
    String firstName;
    String lastName;
    @Embedded
    User user;

    @OneToMany
    ArrayList<Coupon> coupons;

    public Customer() {
    }

    public Customer(String firstName, String lastName, User user, ArrayList<Coupon> coupons) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.coupons = coupons;
    }

    public Customer(String firstName, String lastName, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
    }

    public Customer(String firstName, String lastName,String email,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user=new User(email, password);
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user=new User();
    }

    public int getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(ArrayList<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", user=" + user +
                ", coupons=" + coupons +
                '}';
    }
}
