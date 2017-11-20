package com.lyz.annotation.date;

import com.lyz.annotation.AbstractCustomerValidatorRule;
import com.lyz.annotation.CustomerRule;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义时间校验器规则
 *
 * @author yongzhi.liu
 * @version V1.0.0
 * @date 17/11/16 10:37
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
@CustomerRule
public class DateValidatorRule extends AbstractCustomerValidatorRule {

    @Override
    public void validProperty(Annotation annotation, Object object, PostHandler postHandler) {
        DateString dateString = (DateString) annotation;
        if (parse(dateString.pattern(), (String) object) == null) {
            postHandler.postHandle(dateString.errorCode(), dateString.message());
        }
    }

    public boolean support(Annotation annotation) {
        return annotation instanceof DateString;
    }

    private Date parse(String pattern, String property) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(property);
        } catch (ParseException e) {
            //do noting
        }
        return null;
    }

}
