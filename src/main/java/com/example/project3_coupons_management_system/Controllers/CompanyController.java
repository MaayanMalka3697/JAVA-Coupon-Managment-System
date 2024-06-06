package com.example.project3_coupons_management_system.Controllers;

import com.example.project3_coupons_management_system.Entityes.*;
import com.example.project3_coupons_management_system.JWT.AuthenticationRequest;
import com.example.project3_coupons_management_system.JWT.AuthenticationResponse;
import com.example.project3_coupons_management_system.JWT.JwtUtils;
import com.example.project3_coupons_management_system.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/Company")

public class CompanyController extends ClientController {

    //@Autowired
    private CompanyService companyService;
    @Autowired
    private JwtUtils jwtUtils;

    public CompanyController() {

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        companyService = (CompanyService)loginManager.login(authenticationRequest.getEmail(), authenticationRequest.getPassword(), ClientType.Company);
        if(companyService != null) {
            String token = jwtUtils.generateToken(authenticationRequest, ClientType.Company);
            System.out.println(token);
            return ResponseEntity.ok(new AuthenticationResponse(token));
        } else {
            return new ResponseEntity<String>("Invalid Email or Password...", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/addCoupon")
    public void addCoupon(@RequestBody Coupon coupon) throws Exception {
        companyService.AddCoupon(coupon);
    }

    @PutMapping("/updateCoupon")
    public void updateCoupon(@RequestBody Coupon coupon) throws Exception {
        companyService.UpdateCoupon(coupon);
    }

    @DeleteMapping("/deleteCoupon/{id}")
    public void deleteCoupon(@PathVariable("id") int couponID) throws Exception {
        companyService.DeleteCoupon(couponID);
    }

    @GetMapping("/getCompanyCoupons")
    public ArrayList<Coupon> getCompanyCoupons() throws Exception {
        return companyService.GetCompanyCoupons();
    }

    @GetMapping("/getCompanyCouponsByCategory")
    public ArrayList<Coupon> getCompanyCoupons(@RequestParam("category") Category category) throws Exception {
        return companyService.GetCompanyCoupons(category);
    }

    @GetMapping("/getCompanyCouponsByPrice")
    public ArrayList<Coupon> getCompanyCoupons(@RequestParam("maxPrice") double maxPrice) throws Exception {
        return companyService.GetCompanyCoupons(maxPrice);
    }

    @GetMapping("/getCompanyDetails")
    public Company getCompanyDetails() throws Exception {
        return companyService.getCompanyDetails();
    }

}
