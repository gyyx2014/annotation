package com.lyz.ocr;

import com.yeepay.g3.ocr.engine.CaptchaOCR;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author yongzhi.liu
 * @version V1.0.0
 * @date 17/10/15 20:56
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
public class OCRTest {
    public static void main(String[] args) throws Exception {
        File file = new File("/Users/yp-tc-m-2886/Downloads/test-1.jpg");
        InputStream input = new FileInputStream(file);
        byte[] byt = new byte[input.available()];
        input.read(byt);
        String answer = CaptchaOCR.recognize(byt);
        System.out.println(answer);
    }

}
