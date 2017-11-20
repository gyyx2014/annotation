package com.lyz.annotation.date;

import com.lyz.annotation.CustomerValidator;

import java.lang.annotation.*;

/**
 * 日期字符串规则
 *
 * @author yongzhi.liu
 * @version V1.0.0
 * @date 17/11/16 10:17
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@CustomerValidator
public @interface DateString {
    String pattern() default "yyyy-MM-dd HH:mm:ss";

    String errorCode() default "No Date";

    String message() default "must be date pattern";
}
