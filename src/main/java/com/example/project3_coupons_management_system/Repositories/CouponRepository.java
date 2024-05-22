package com.example.project3_coupons_management_system.Repositories;

import com.example.project3_coupons_management_system.Entityes.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
}
