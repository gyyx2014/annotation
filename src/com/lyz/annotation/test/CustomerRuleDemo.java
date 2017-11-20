package com.lyz.annotation.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;

/**
 * @author yongzhi.liu
 * @version V1.0.0
 * @date 17/11/16 11:27
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-application.xml")
public class CustomerRuleDemo {

    @Autowired
    private Validator customerValidatorFactory;

    @Test
    public void dateStrTest() {
        DemoDTO demoDTO = new DemoDTO();
        demoDTO.setDate("2015 11 16");
        BindException bindException = new BindException(demoDTO, "target");
        customerValidatorFactory.validate(demoDTO, bindException);
        System.out.println(bindException.getFieldErrors());
    }
}
