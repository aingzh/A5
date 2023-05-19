package com.yx.controller;

import com.alipay.api.AlipayApiException;
import com.yx.po.Order;
import com.yx.service.PayService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {
    @Autowired
    private PayService aliPayService;
    /**
     * 支付宝支付 api
     *
     * @param outTradeNo
     * @param subject
     * @param totalAmount
     * @param description
     * @return
     * @throws AlipayApiException
     */
    @PostMapping(value = "/order/alipay")
    @ApiOperation(value="支付宝支付 api",httpMethod ="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "outTradeNo",value = "商户订单号",paramType = "query",dataType="String"),
            @ApiImplicitParam(name = "subject",value = "订单名称",paramType = "query",dataType="String"),
            @ApiImplicitParam(name = "totalAmount",value = "付款金额",paramType = "query",dataType="String"),
            @ApiImplicitParam(name = "description",value = "商品描述",paramType = "query",dataType="String",required = false)
    })
    public String alipay(String outTradeNo, String subject,
                         String totalAmount, String description) throws AlipayApiException {
        Order order = new Order();
        order.setOut_trade_no(outTradeNo);
        order.setSubject(subject);
        order.setTotal_amount(totalAmount);
        order.setDescription(description);
        System.out.println(order);
        return aliPayService.aliPay(order);
    }
}


