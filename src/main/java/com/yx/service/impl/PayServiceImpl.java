package com.yx.service.impl;

import com.alipay.api.AlipayApiException;
import com.yx.po.Order;
import com.yx.service.PayService;
import com.yx.utils.alipay.config.Alipay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private Alipay alipay;

    @Override
    public String aliPay(Order order) throws AlipayApiException {
        return alipay.pay(order);
    }
}


