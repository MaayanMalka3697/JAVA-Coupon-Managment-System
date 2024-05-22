package com.example.project3_coupons_management_system.Services;

import com.example.project3_coupons_management_system.Entityes.Company;
import com.example.project3_coupons_management_system.Entityes.Customer;
import com.example.project3_coupons_management_system.Entityes.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService extends ClientService{

    @Override
    public boolean Login(User user) {
        return (user.getEmail().equals("admin@admin.com") && user.getPassword().equals("123456"));
    }

    public void AddNewCustomer(Customer c)
    {
        //TODO: להוסיף בדיקות תקינות
        customerRepository.findAll().add(c);
    }

    public Customer UpdateCustomer(Customer c)
    {
        customerRepository.save(c);
        return c;
    }

    public void DeleteCustomer(Customer c)
    {
        customerRepository.delete(c);
    }
    public List<Customer> GetAllCustomers()
    {
        return customerRepository.findAll();
    }

    public Customer GetByPassword(String password)
    {
       return customerRepository.getCustomerByUser_Password(password);
    }

    //**********************************************************
    //Company
    public void AddNewCompany(Company c)
    {
        //TODO: להוסיף בדיקות תקינות
        companyRepository.findAll().add(c);
    }

    public Company UpdateCompany(Company c)
    {
        companyRepository.save(c);
        return c;
    }

    public void DeleteCompany(Company c)
    {
        companyRepository.delete(c);
    }
    public List<Company> GetAllCompanies()
    {
        return companyRepository.findAll();
    }













}
