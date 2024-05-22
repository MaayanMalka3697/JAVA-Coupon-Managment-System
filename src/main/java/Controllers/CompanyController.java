package Controllers;

import com.example.project3_coupons_management_system.Entityes.Company;
import com.example.project3_coupons_management_system.Entityes.User;
import com.example.project3_coupons_management_system.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user)
    {
        if (companyService.Login(user))
            return ResponseEntity.ok(HttpStatus.OK);
        else {
            return new ResponseEntity<String>("UNAUTHORIZED. Invalid Email or Password", HttpStatus.UNAUTHORIZED);
        }
    }





}
