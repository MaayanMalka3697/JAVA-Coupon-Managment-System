package com.example.project3_coupons_management_system.Controllers;

import com.example.project3_coupons_management_system.Entityes.Company;
import com.example.project3_coupons_management_system.Entityes.Customer;
import com.example.project3_coupons_management_system.Entityes.User;
import com.example.project3_coupons_management_system.Services.AdminService;
import com.example.project3_coupons_management_system.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {



    @Autowired
    private AdminService adminService;


    public AdminController() {
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user)
    {
        if (adminService.Login(user))
            return ResponseEntity.ok(HttpStatus.OK);
        else {
            return new ResponseEntity<String>("UNAUTHORIZED. Invalid Email or Password", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/addCompany")
    public void addCompany(@RequestBody Company company) throws Exception {
        adminService.AddNewCompany(company);
    }
    @PutMapping("/updateCompany")
    public void updateCompany(@RequestBody Company company) throws Exception {
        adminService.UpdateCompany(company);
    }

    @DeleteMapping("/deleteCompany/{id}")
    public void deleteCompany(@PathVariable("id") int companyID) throws Exception {
        adminService.DeleteCompany(companyID);
    }

    @GetMapping("/getAllCompanies")
    public List<Company> getAllCompanies() throws Exception {
        return adminService.GetAllCompanies();
    }

    @GetMapping("/getOneCompany/{id}")
    public Company getOneCompany(@PathVariable("id") int companyID) throws Exception {
        return adminService.GetCompanyByID(companyID);
    }

    @PostMapping("/addCustomer")
    public void addCustomer(@RequestBody Customer customer) throws Exception {
        adminService.AddNewCustomer(customer);
    }

    @PutMapping("/updateCustomer")
    public void updateCustomer(@RequestBody Customer customer) throws Exception {
        adminService.UpdateCustomer(customer);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public void deleteCustomer(@PathVariable("id") int customerID) throws Exception {
        adminService.DeleteCustomer(customerID);
    }

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers() throws Exception {
        return adminService.GetAllCustomers();
    }

    @GetMapping("/getOneCustomer")
    public Customer getOneCustomer(@PathVariable("id") int customerID) throws Exception {
        return adminService.GetCustomerByID(customerID);
    }













}
