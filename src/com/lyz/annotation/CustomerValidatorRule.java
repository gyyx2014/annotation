package com.lyz.annotation;

import org.springframework.validation.Errors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 自定义校验器规则接口
 *
 * @author yongzhi.liu
 * @version V1.0.0
 * @date 17/11/16 10:22
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
public interface CustomerValidatorRule {
    /**
     * 判断是否支持该注解
     */
    boolean support(Annotation annotation);

    /**
     * 校验处理
     *
     * @param annotation 注解
     * @param target     对象
     * @param field      属性
     */
    void valid(Annotation annotation, Object target, final Field field, final Errors errors) throws Exception;
}
