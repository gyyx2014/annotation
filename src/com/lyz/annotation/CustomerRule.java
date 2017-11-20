package com.lyz.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义规则注解
 *
 * @author yongzhi.liu
 * @version V1.0.0
 * @date 17/11/16 10:45
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface CustomerRule {
}
