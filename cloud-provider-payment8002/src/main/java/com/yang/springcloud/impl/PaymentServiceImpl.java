package com.yang.springcloud.impl;

import com.yang.springcloud.dao.PaymentDao;
import com.yang.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.springcloud.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return  paymentDao.create(payment);

    }

    public Payment getPaymentById(@Param("id") Long id){
        return paymentDao.getPaymentById(id);
    }

}
