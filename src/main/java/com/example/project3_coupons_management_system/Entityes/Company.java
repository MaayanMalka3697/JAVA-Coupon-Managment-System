package com.example.project3_coupons_management_system.Entityes;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "Company")
public class Company {
    @GeneratedValue
    @Id
    int id;
    String name;
    @ Embedded
    User user;
    @OneToMany
    ArrayList<Coupon> coupons;

    public Company() {
    }

    public Company(String name, User user, ArrayList<Coupon> coupons) {
        this.name = name;
        this.user = user;
        this.coupons = coupons;
    }

    public Company(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Company(String name, String email, String password) {

        this.name=name;
        this.user=new User(email,password);

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", user=" + user +
                ", coupons=" + coupons +
                '}';
    }
}
