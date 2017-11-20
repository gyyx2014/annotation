package com.lyz.annotation;

import com.yeepay.g3.utils.common.CheckUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.Errors;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 自定义校验器规则抽象实现
 *
 * @author yongzhi.liu
 * @version V1.0.0
 * @date 17/11/16 10:24
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
public abstract class AbstractCustomerValidatorRule implements CustomerValidatorRule {

    public void valid(Annotation annotation, Object target, final Field field, final Errors errors) throws Exception {
        preHandle(annotation, target, field, errors);
        PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(target.getClass(), field.getName());
        Method method = propertyDescriptor.getReadMethod();
        Object property = method.invoke(target);
        validProperty(annotation, property, new PostHandler() {
            public void postHandle(String errorCode, String message) {
                errors.rejectValue(field.getName(), errorCode, message);
            }
        });
    }

    /**
     * 验证属性
     */
    public abstract void validProperty(Annotation annotation, Object object, PostHandler postHandler);


    /**
     * 预处理
     */
    private void preHandle(Annotation annotation, Object object, Field field, Errors errors) {
        CheckUtils.notEmpty(annotation, "annotation");
        CheckUtils.notEmpty(object, "object");
        CheckUtils.notEmpty(field, "field");
        CheckUtils.notEmpty(errors, "errors");
    }

    public static interface PostHandler {
        void postHandle(String errorCode, String message);
    }
}
