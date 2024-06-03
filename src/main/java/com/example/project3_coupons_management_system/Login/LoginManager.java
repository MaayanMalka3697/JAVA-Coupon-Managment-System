package com.example.project3_coupons_management_system.Login;

import com.example.project3_coupons_management_system.Entityes.ClientType;
import com.example.project3_coupons_management_system.Entityes.User;
import com.example.project3_coupons_management_system.Services.AdminService;
import com.example.project3_coupons_management_system.Services.ClientService;
import com.example.project3_coupons_management_system.Services.CompanyService;
import com.example.project3_coupons_management_system.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.example.project3_coupons_management_system.Entityes.ClientType.*;

@Component
@Scope("singleton")
public class LoginManager {
    @Autowired
    AdminService adminService;

    @Autowired
    CompanyService companyService;

    @Autowired
    CustomerService customerService;

    private LoginManager() {

    }

    public ClientService login(String email , String password, ClientType clientType) throws Exception {
        boolean boolLogin = false;
        ClientService clientService = null;
        switch (clientType) {
            case Administrator:
                clientService = adminService;
                boolLogin = adminService.Login(new User(email, password));
                break;
            case Company:
                clientService = companyService;
                boolLogin = companyService.Login(new User(email, password));
                break;
            case Customer:
                clientService = customerService;
                boolLogin = customerService.Login(new User(email, password));
                break;
        }

        if(!boolLogin)
            return null;
        else
            return clientService;
    }
}
