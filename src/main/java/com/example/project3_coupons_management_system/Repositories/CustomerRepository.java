package com.example.project3_coupons_management_system.Repositories;

import com.example.project3_coupons_management_system.Entityes.Coupon;
import com.example.project3_coupons_management_system.Entityes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    public Customer getCustomerByUser_Password(String password);
    @Query("select id from Customer where user.email = ?1 and user.password =?2")
    public  Integer findByEmailAndPassword(String email, String password);


    public boolean existsByEmail(String email);

    public Customer findById(int id);

    @Query("select c from Customer c where c.user.email = ?1 and c.id <> ?2")
    public Customer findCustomerByEmailAndId(String email , int id);

    @Query("SELECT coup FROM Coupon coup WHERE coup.id IN (SELECT coup.id FROM coup.customers cust WHERE cust.id = ?1 and coup.id = ?2)")
    public Coupon findCustomerCoupon(int custId, int cpnId);

    @Query("SELECT coup FROM Coupon coup WHERE coup.id Not IN (SELECT coup.id FROM coup.customers cust WHERE cust.id=?1) and coup.amount > 0 and coup.endDate > CURDATE() and coup.id > 0")
    public Set<Coupon> findCoupon(int cust_id);

}
