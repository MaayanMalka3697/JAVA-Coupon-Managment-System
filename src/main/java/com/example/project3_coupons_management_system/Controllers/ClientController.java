package com.example.project3_coupons_management_system.Controllers;

import com.example.project3_coupons_management_system.JWT.AuthenticationRequest;
import com.example.project3_coupons_management_system.Login.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public abstract class ClientController {

    @Autowired
    protected LoginManager loginManager;
    public abstract ResponseEntity<?> login(AuthenticationRequest authenticationRequest) throws Exception;

}
