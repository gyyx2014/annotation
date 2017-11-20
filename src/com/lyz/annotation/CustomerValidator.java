package com.lyz.annotation;

import java.lang.annotation.*;

/**
 * 自定义校验器注解
 *
 * @author yongzhi.liu
 * @version V1.0.0
 * @date 17/11/16 10:16
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
@Documented
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomerValidator {
}
