package com.lyz.annotation;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定易验证器配置
 * 通过实现ApplicationContextAware接口，从上下文中自动加载处理器
 *
 * @author yongzhi.liu
 * @version V1.0.0
 * @date 17/11/16 10:54
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
public class CustomerValidatorConfig implements ApplicationContextAware {

    private Map<Annotation, CustomerValidatorRule> customerValidatorRuleMap = new ConcurrentHashMap<Annotation, CustomerValidatorRule>();
<<<<<<< HEAD

=======
>>>>>>> f67f84d31b8863820ef0931bbaa25d0204fc6727
    private Map<String, Object> customerValidationRules = null;


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        customerValidationRules = applicationContext.getBeansWithAnnotation(CustomerRule.class);
    }

    private CustomerValidatorRule findFormMap(Annotation annotation) {
        Iterator<String> iterator = customerValidationRules.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            CustomerValidatorRule customerValidatorRule = (CustomerValidatorRule) customerValidationRules.get(key);
            if (key != null && customerValidatorRule.support(annotation)) {
                return customerValidatorRule;
            }
        }
        return null;
    }

    public CustomerValidatorRule findRule(Annotation annotation) {
        if (!customerValidatorRuleMap.containsKey(annotation)) {
            CustomerValidatorRule newCustomerValidatorRule = findFormMap(annotation);
            if (newCustomerValidatorRule != null) {
                customerValidatorRuleMap.put(annotation, newCustomerValidatorRule);
            }
        }
        return customerValidatorRuleMap.get(annotation);
    }
}
