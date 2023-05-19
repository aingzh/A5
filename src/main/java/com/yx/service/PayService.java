package com.yx.service;

import com.alipay.api.AlipayApiException;
import com.yx.po.Order;

public interface PayService {
    /**
     * 支付宝支付接口
     * @param order
     * @return
     * @throws AlipayApiException
     */
    String aliPay(Order order) throws AlipayApiException;
}
