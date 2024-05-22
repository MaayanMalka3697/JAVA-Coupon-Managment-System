package com.example.project3_coupons_management_system.Services;

import com.example.project3_coupons_management_system.Entityes.Company;
import com.example.project3_coupons_management_system.Entityes.Coupon;
import com.example.project3_coupons_management_system.Entityes.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompanyService extends ClientService{


    @Override
    public boolean Login(User user) {
     User u =  customerRepository.getCustomerByUser_Password(user.getPassword()).getUser();
     return  (u.getEmail().equals(user.getEmail()));
    }

    public Coupon AddCoupon(Coupon coupon)
    {
        couponRepository.save(coupon);
        return coupon;
    }

    public void UpdateCoupon(Coupon coupon)
    {
        couponRepository.save(coupon);
    }

    public void DeleteCoupon(Coupon coupon)
    {
        couponRepository.delete(coupon);
    }

    //*****************************************************
    //Company

    public ArrayList<Coupon> GetCompanyCoupons(Company company)
    {
       return companyRepository.findById(company.getId()).get().getCoupons();
    }

//     public ArrayList<Coupon> GetCompanyCoupons(Category category)
//    {
//        //?
//    }

    //GetCompanyCoupons(double price){}
    //GetCompanyDetails()
}
