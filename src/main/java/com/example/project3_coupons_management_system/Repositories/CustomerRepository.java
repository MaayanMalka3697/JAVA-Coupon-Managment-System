package com.example.project3_coupons_management_system.Repositories;

import com.example.project3_coupons_management_system.Entityes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    public Customer getCustomerByUser_Password(String password);


}
