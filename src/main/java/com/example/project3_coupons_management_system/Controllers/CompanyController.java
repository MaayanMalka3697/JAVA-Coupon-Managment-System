package com.example.project3_coupons_management_system.Controllers;

import com.example.project3_coupons_management_system.Entityes.ClientType;
import com.example.project3_coupons_management_system.Entityes.Company;
import com.example.project3_coupons_management_system.Entityes.User;
import com.example.project3_coupons_management_system.JWT.AuthenticationRequest;
import com.example.project3_coupons_management_system.JWT.AuthenticationResponse;
import com.example.project3_coupons_management_system.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Company")

public class CompanyController extends ClientController {

    @Autowired
    private CompanyService companyService;


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

}
