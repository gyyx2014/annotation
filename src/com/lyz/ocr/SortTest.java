package com.lyz.ocr;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author yongzhi.liu
 * @version V1.0.0
 * @date 17/10/25 15:06
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
public class SortTest {
    public static void main(String[] args) throws Exception {
        String[] array =
//                {"0", "0", "0", "3", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
//                 "1", "0", "0", "1", "0", "1", "1", "0", "0", "1", "2", "1", "0", "0", "0", "2", "3"};
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "3",
                        "0", "0", "0", "0", "0", "0", "0", "0", "1", "1", "1", "1", "1", "1", "2", "2", "3"};
        sortWorkOrderFromEngine(Arrays.asList(array), null, 1);
        System.out.println(array.length);
    }

    /**
     * 排序
     *
     * @param field 排序的字段
     * @param order -1:倒序  1:正序
     * @return
     */
    public static void sortWorkOrderFromEngine(List array, String field, final int order) {
        Collections.sort(array, new Comparator<String>() {
            int compareCount = 0;

            public int compare(String str1, String str2) {
                compareCount++;
                int result = -1 * order;
                if (!str1.equals("0") || !str2.equals("0")) {
                    result = str1.compareTo(str2) * order;
                }
                if (str1.equals("0") && str2.equals("0")) {
                    return 0;
                }
                System.out.println(str1 + "   " + str2 + "   result:" + result + "  compareCount:" + compareCount);
                return result;
            }
        });
    }

    public static boolean isNull(String... params) {
        for (String param : params) {
            if (param == null || "".equals(param)) {
                return true;
            }
        }
        return false;
    }

//    public int compare(Object str1, Object str2){
//        JSONObject obj1 = JSONObject.parseObject(str1.toString());
//        JSONObject obj2 = JSONObject.parseObject(str2.toString());
//        String publishTime1 = obj1.getJSONObject("work_order").getJSONObject("wo_body").getString(field);
//        String publishTime2 = obj2.getJSONObject("work_order").getJSONObject("wo_body").getString(field);
//        if(!StringUtil.isNull(publishTime1, publishTime2))
//            return publishTime1.compareTo(publishTime2) * order;
//        return order * -1;
//    }

}
