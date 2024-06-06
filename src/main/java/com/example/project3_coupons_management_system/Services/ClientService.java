package com.example.project3_coupons_management_system.Services;

import com.example.project3_coupons_management_system.Repositories.CompanyRepository;
import com.example.project3_coupons_management_system.Repositories.CouponRepository;
import com.example.project3_coupons_management_system.Repositories.CustomerRepository;
import com.example.project3_coupons_management_system.Entityes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class  ClientService {
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected CompanyRepository companyRepository;

    public abstract boolean Login(String email, String password) throws Exception;

    public abstract boolean Login(User user) throws Exception;

}
