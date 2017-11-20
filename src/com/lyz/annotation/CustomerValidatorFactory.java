package com.lyz.annotation;

import com.yeepay.g3.utils.common.CheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yongzhi.liu
 * @version V1.0.0
 * @date 17/11/16 11:17
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
@Component
public class CustomerValidatorFactory implements Validator {

    @Autowired
    private CustomerValidatorConfig customerValidatorConfig;

    public boolean supports(Class<?> aClass) {
        return true;
    }

    public void validate(Object target, Errors errors) {
        CheckUtils.notEmpty(target, "target");
        CheckUtils.notEmpty(errors, "errors");
        List<Field> fieldList = getFields(target.getClass());
        for (Field field : fieldList) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().getAnnotation(CustomerValidator.class) != null) {
                    try {
                        CustomerValidatorRule customerValidatorRule = customerValidatorConfig.findRule(annotation);
                        if (customerValidatorRule != null) {
                            customerValidatorRule.valid(annotation, target, field, errors);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 获取class的fields。
     *
     * @param clazz bean所在的class
     * @return
     */
    private List<Field> getFields(Class<? extends Object> clazz) {
        // 声明Field数组
        List<Field> fields = new ArrayList<Field>();
        // 如果class类型不为空
        while (clazz != null) {
            // 添加属性到属性数组
            Collections.addAll(fields, clazz.getDeclaredFields());
            clazz = clazz.getSuperclass();
        }
        return fields;
    }
}
