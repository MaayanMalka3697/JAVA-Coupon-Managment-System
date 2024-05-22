package com.example.project3_coupons_management_system.Services;

import com.example.project3_coupons_management_system.Entityes.Coupon;
import com.example.project3_coupons_management_system.Entityes.User;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends ClientService{


    @Override
    public boolean Login(User user) {
        User u =  customerRepository.getCustomerByUser_Password(user.getPassword()).getUser();
        return  (u.getEmail().equals(user.getEmail()));
    }

    public void PurchaseCoupon(Coupon coupon)
    {
        //TODO: רכישת קופון
    }
//TODO  : ALL THE OTHER FUNCTIONS...!



}
