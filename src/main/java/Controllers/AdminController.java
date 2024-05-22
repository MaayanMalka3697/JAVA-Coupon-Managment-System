package Controllers;

import com.example.project3_coupons_management_system.Entityes.User;
import com.example.project3_coupons_management_system.Services.AdminService;
import com.example.project3_coupons_management_system.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService AdminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user)
    {
        if (AdminService.Login(user))
            return ResponseEntity.ok(HttpStatus.OK);
        else {
            return new ResponseEntity<String>("UNAUTHORIZED. Invalid Email or Password", HttpStatus.UNAUTHORIZED);
        }
    }



}
