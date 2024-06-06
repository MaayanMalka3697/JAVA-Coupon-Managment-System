package com.example.project3_coupons_management_system.Controllers;

import com.example.project3_coupons_management_system.Entityes.*;
import com.example.project3_coupons_management_system.JWT.AuthenticationRequest;
import com.example.project3_coupons_management_system.JWT.AuthenticationResponse;
import com.example.project3_coupons_management_system.JWT.JwtUtils;
import com.example.project3_coupons_management_system.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/Customer")

public class CustomerController extends ClientController{

    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtUtils jwtUtils;
    public CustomerController() {

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        customerService = (CustomerService)loginManager.login(authenticationRequest.getEmail(), authenticationRequest.getPassword(), ClientType.Customer);
        if(customerService != null) {
            String token = jwtUtils.generateToken(authenticationRequest, ClientType.Customer);
            System.out.println(token);
            return ResponseEntity.ok(new AuthenticationResponse(token));
        }
        else {
            return new ResponseEntity<String>("Invalid Email or Password...", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/purchaseCoupon")
    public void purchaseCoupon(@RequestBody Coupon coupon) throws Exception {
        customerService.PurchaseCoupon(coupon);
    }

    @GetMapping("/getCustomerCoupons")
    public ArrayList<Coupon> getCustomerCoupons() throws Exception {
        return customerService.GetCustomerCoupons();
    }

    @GetMapping("/getCustomerCouponsByCategory")
    public ArrayList<Coupon> getCustomerCoupons(@RequestParam("category") Category category) throws Exception {
        return customerService.GetCustomerCoupons(category);
    }

    @GetMapping("/getCustomerCouponsByPrice")
    public ArrayList<Coupon> getCustomerCoupons(@RequestParam("maxPrice") double maxPrice) throws Exception {
        return customerService.GetCustomerCoupons(maxPrice);
    }

    @GetMapping("/getCustomerDetails")
    public Customer getCustomerDetails() throws Exception {
        return customerService.GetCustomerDetails();
    }

    @GetMapping("/getAllCoupons")
    public ArrayList<Coupon> getAllCoupons() throws Exception {
        return customerService.GetAllCoupons();
    }



}
