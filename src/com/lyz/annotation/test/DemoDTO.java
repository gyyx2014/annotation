package com.lyz.annotation.test;

import com.lyz.annotation.date.DateString;

/**
 * @author yongzhi.liu
 * @version V1.0.0
 * @date 17/11/16 11:31
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
public class DemoDTO {

    @DateString
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
