package com.example.project3_coupons_management_system.Repositories;

import com.example.project3_coupons_management_system.Entityes.Company;
import com.example.project3_coupons_management_system.Entityes.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

    @Query("select id from Company where user.email = ?1 and user.password =?2")
    public Integer findByEmailAndPassword(String email, String password);

//      @Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM Company c WHERE c.name = ?1 Or c.email = ?2")
//      	public Boolean existsByNameOrEmail(String name, String email);

     public Boolean existsById(int companyID);

//        @Query("select c from Company c where c.user.email = ?1 and c.id <> ?2")
//        	public Company findCompanyByEmailAndId(String email, int id);

        @Query("select c from Coupon c where c.id = ?1")
        	public Set<Coupon> findCompanyCoupons(int companyID);



}
