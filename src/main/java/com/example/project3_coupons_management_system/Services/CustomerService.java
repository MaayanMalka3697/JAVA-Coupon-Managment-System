package com.example.project3_coupons_management_system.Services;

import com.example.project3_coupons_management_system.Entityes.Coupon;
import com.example.project3_coupons_management_system.Entityes.User;
import com.example.project3_coupons_management_system.Repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService extends ClientService{

    private int customerID;

    public CustomerService() {

    }
    @Override
    public boolean Login(String email, String password) throws Exception {
        if(email != null && password != null) {
            Integer id = CustomerRepository.findByEmailAndPassword(email,password);
            if(id != null) {
                customerID = id;
            }

            //Brining the specific id from customers table to initiate the customerID data type
            if(customerID > 0 && id != null) {
                System.out.println("Welcome Customer!");
                return true;
            }
            else {
                throw new Exception("Invalid email or password");
            }
        } else {
            throw new Exception("Cannot login with empty details");
        }
    }
    public void PurchaseCoupon(Coupon coupon)
    {
        //TODO: רכישת קופון
    }

    @Override
    public boolean Login(User user) throws Exception {
        String email= user.getEmail();
        String password= user.getEmail();
        if(email != null && password != null) {
            Integer id = CustomerRepository.findByEmailAndPassword(email,password);
            if(id != null) {
                customerID = id;
            }

            //Brining the specific id from customers table to initiate the customerID data type
            if(customerID > 0 && id != null) {
                System.out.println("Welcome Customer!");
                return true;
            }
            else {
                throw new Exception("Invalid email or password");
            }
        } else {
            throw new Exception("Cannot login with empty details");
        }
    }
public ArrayList<Coupon> GetCustomerCoupons()
{
    return CustomerRepository.

}

//TODO  : ALL THE OTHER FUNCTIONS...!



}
