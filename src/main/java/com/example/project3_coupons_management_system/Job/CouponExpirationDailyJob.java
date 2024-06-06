package com.example.project3_coupons_management_system.Job;

import com.example.project3_coupons_management_system.Entityes.Coupon;
import com.example.project3_coupons_management_system.Repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
@Scope("singleton")
public class CouponExpirationDailyJob implements Runnable{
    @Autowired
    private CouponRepository couponsRepo;

    private boolean quit;

    public CouponExpirationDailyJob() {

    }

    @Override
    public void run() {
        while(!quit) {
            for (Coupon cpn : couponsRepo.findByEndDateBefore(new Date(Calendar.getInstance().getTime().getTime()))) {
                couponsRepo.delete(cpn);
            }
            try {
                Thread.sleep(864000000);
            } catch (InterruptedException e) {
                System.out.println(("Exception in CouponExpirationDailyJob - " + e.getMessage()));
            }
        }
    }

    public void stop() {
        quit = true;
    }

}
