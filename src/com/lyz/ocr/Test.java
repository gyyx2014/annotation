package com.lyz.ocr;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Files;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by DOOM on 2017/7/3.
 */
public class Test {
    public static void main(String[] args) throws Exception {
//		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        List<String> list = Files.readLines(new File("/Users/yp-tc-m-2886/Downloads/data.txt"), Charset.forName("UTF-8"));
        String string = String.join("", list);
        JSONObject jsonObject = sortWorkOrderFromEngine(string, "create_time", -1);
//		Files.wr


//		String url = "http://192.168.30.98:8080/workorder/restAppWorkOrderService/queryPersonWorkOrderCount";
//		String jsonStr = "{\"person_id\":\"RY1503538063730\",\"position\":\"Android\",\"project_id\":\"Pj1301020001\",\"user_id\":\"RY1503538063730\"}";
//		for(int i=0; i<100; i++){
//			System.out.println(httpPostRequest(url, jsonStr));
//		}

//		String string = "20171012";
//		System.out.println(string.compareTo("20171013"));
//		String time = "2017" + "1016" + "16" + "30" + "00";
//		Calendar calendar = DateUtil.getBeforeTime(time, CommonConst.data_format_save, 1);
//		String cronTime = "0 " + calendar.get(Calendar.MINUTE) + " " + calendar.get(Calendar.HOUR_OF_DAY) + " " + calendar.get(Calendar.DAY_OF_MONTH) + " " + (calendar.get(Calendar.MONTH) + 1) + " ?";
//		System.out.println(cronTime);
//		JSONObject param = new JSONObject();
//		param.put("user_id", "gsc");
//		param.put("from_start_time", "20161016205501");
//		param.put("from_end_time", "20171016205500");
//		param.put("bury_point_type", "bp_app_obj_sel");
//		httpPostRequest("http://localhost:8080/saas-manage/restBuryPointService/exportBuryPoint", param.toJSONString(), "E:/test.xlsx");
//        downLoadFromUrl("http://localhost:8080/saas-manage/restBuryPointService/exportBuryPoint",  
//                    "test.xlsx","E:/");  

//		JSONObject createTime = new JSONObject();
//		createTime.put("cycle", "w");
//		createTime.put("time_day", "1");
//		createTime.put("time_hour", "00");
//		createTime.put("time_minute", "00");
//		System.out.println(getCronTime(createTime, 2));

//		System.out.println(getRemindType(null));


//		String str1 = "";
//		String str2 = "";
//		System.out.println(str1.compareTo(str2));
//		System.out.println(str2.compareTo(str1));
//		System.out.println("----------");
//		str1 = "20171025150400";
//		str2 = "20171025150400";
//		System.out.println(str2.compareTo(str1));
//		System.out.println(str1.compareTo(str2));
//		System.out.println("----------");
//		str1 = "20171026150400";
//		str2 = "20171025150400";
//		System.out.println(str2.compareTo(str1));
//		System.out.println(str1.compareTo(str2));
//		System.out.println("----------");
//		
//		
//		JSONArray array = new JSONArray();
//		JSONObject object1 = new JSONObject();
//		object1.put("time", "");
//		array.add(object1);
//		
//		JSONObject object2 = new JSONObject();
//		object2.put("time", "");
//		array.add(object2);
//		
//		JSONObject object3 = new JSONObject();
//		object3.put("time", "");
//		array.add(object3);
//		
//		JSONObject object4 = new JSONObject();
//		object4.put("time", "20171028203300");
//		array.add(object4);
//		
//		JSONObject object5 = new JSONObject();
//		object5.put("time", "");
//		array.add(object5);
//		
//		JSONObject object6 = new JSONObject();
//		object6.put("time", "");
//		array.add(object6);
//		
//		System.out.println(sortWorkOrderFromEngine(array, "time", CommonConst.asc).toJSONString());
//		System.out.println("-----------------------------------------------------------------------------");

//		String[] arr =
//            {"", "", "", "", "", "", "", "3", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
//                    "", "", "", "1", "1", "", "", "1", "", "1", "", "", "", "", "1", "", "", "1", "", "", "", "2", "1", "", "", "", "2", "30", "", "3"};
//	    sortWorkOrderFromEngine(Arrays.asList(arr), null, 1);
//	    System.out.println(arr.length);
    }

    /**
     * 排序
     *
     * @param queryResult
     * @param field       排序的字段
     * @param order       -1:倒序  1:正序
     * @return
     */
    public static JSONObject sortWorkOrderFromEngine(String queryResult, final String field, final int order) {
        JSONObject resultJson = JSONObject.parseObject(queryResult);
        if ("success".equals(resultJson.getString("Result"))) {
            JSONArray array = resultJson.getJSONArray("Content");
            Collections.sort(array, new Comparator<Object>() {
                public int compare(Object str1, Object str2) {
                    JSONObject obj1 = JSONObject.parseObject(str1.toString());
                    JSONObject obj2 = JSONObject.parseObject(str2.toString());
                    String publishTime1 = obj1.getJSONObject("work_order").getJSONObject("wo_body").getString(field);
                    String publishTime2 = obj2.getJSONObject("work_order").getJSONObject("wo_body").getString(field);
                    System.out.println(publishTime1 + "-----" + publishTime2);
                    if (isNull(publishTime1) && isNull(publishTime2)) {
                        return 0;
                    } else if (isNull(publishTime1)) {
                        return order * -1;
                    } else if (isNull(publishTime2)) {
                        return order;
                    } else {
                        return publishTime1.compareTo(publishTime2) * order;
                    }

                }
            });
        }
        return resultJson;
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
            public int compare(String str1, String str2) {
                if (!isNull(str1.toString(), str2.toString())) {
                    int result = str1.compareTo(str2) * order;
                    return result;
                }
                return order * -1;
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

    /**
     * 排序
     *
     * @param field 排序的字段
     * @param order -1:倒序  1:正序
     * @return
     */
    public static JSONArray sortWorkOrderFromEngine(JSONArray array, final String field, final int order) {
        Collections.sort(array, new Comparator<Object>() {
            public int compare(Object str1, Object str2) {
                JSONObject obj1 = JSONObject.parseObject(str1.toString());
                JSONObject obj2 = JSONObject.parseObject(str2.toString());
                String publishTime1 = obj1.getString(field);
                String publishTime2 = obj2.getString(field);
                return publishTime1.compareTo(publishTime2) * order;
            }
        });
        return array;
    }

//	/**
//	 * 功能描述：http请求，Json类型参数
//	 *
//	 * @param url
//	 * @param jsonStr
//	 * @return
//	 * @throws Exception
//	 * @创建者 wanghailong
//	 * @邮箱 wanghailong@persagy.com 修改描述
//	 */
//	public static String httpPostRequest(String url, String jsonStr) throws Exception {
//		String respContent = null;
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		HttpPost httpPost = new HttpPost(url);
//		httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
//		httpPost.setEntity(new StringEntity(jsonStr, "utf-8"));
//		// 执行请求
//		respContent = executeHttpRequest(httpclient, httpPost);
//		return respContent;
//	}
//
//	/**
//	 * 执行http请求
//	 * @param httpClient
//	 * @param httpRequest
//	 * @return
//	 */
//	private static String executeHttpRequest(CloseableHttpClient httpClient, HttpUriRequest httpRequest){
//		CloseableHttpResponse response;
//		String respContent = null;
//		try {
//			response = httpClient.execute(httpRequest);
//			respContent = EntityUtils.toString(response.getEntity(), "utf-8");
//		} catch (Exception e) {
//		}finally {
//			if(respContent == null || !respContent.startsWith("{")){
//				respContent = ToolsUtil.errorJsonMsg("请求异常" + respContent);
//			}
//		}
//		return respContent;
//	}
//
//	public static String getRemindType(String planEndTime){
//		String remindType = "1";
//		if(!isNull(planEndTime)){
//
//			String currentDate = DateUtil.getNowTimeStr();
//			String afterMonthDate = DateUtil.getAfterDate(0, 1, 0, 0);
//
//			long endDay = Long.valueOf(planEndTime) / 1000000;
//			long currentDay = Long.valueOf(currentDate) / 1000000;
//			long afterMonthDay = Long.valueOf(afterMonthDate);
//			if(endDay <= afterMonthDay){
//				remindType = "2";
//				if(endDay <= currentDay){
//					remindType = "3";
//				}
//			}
//
//		}
//		return remindType;
//	}
//
//	public static String getCronTime(JSONObject createTime, int aheadHour) throws Exception{
//		String cycle = createTime.getString("cycle");
//		String cronTime = null;
//
//		int timeDay = createTime.getIntValue("time_day");
//		int timeHour = createTime.getIntValue("time_hour");
//		int timeMinute = createTime.getIntValue("time_minute");
//
//		if("y".equals(cycle)){
//			int overDays = ((timeHour - aheadHour + 24) / 24 -1 ) * -1;
//			timeHour = (timeHour - aheadHour + 24) % 24;
//			String cronMonth, cronDay;
//			if(overDays > 0){
//				if(timeDay % 100 <= overDays){
//					timeDay = timeDay / 100;
//					cronMonth = (timeDay -1 + 12) % 12 + "";
//					if("0".equals(cronMonth)){
//						cronMonth = "12";
//					}
//					if(timeDay % 100 == overDays){
//						cronDay = "L";
//					}else{
//						cronDay = (overDays - timeDay% 100) + "L";
//					}
//				}else{
//					cronMonth = timeDay / 100 + "";
//					cronDay = (timeDay % 100 -1) + "";
//				}
//			}else{
//				cronMonth = timeDay / 100 + "";
//				cronDay = timeDay % 100 + "";
//			}
//			cronTime = "0 " + timeMinute + " " + timeHour + " " + cronDay + " " + cronMonth + " ?";
//		}else if("m".equals(cycle)){
//			int overDays = ((timeHour - aheadHour + 24) / 24 -1 ) * -1;
//			timeHour = (timeHour - aheadHour + 24) % 24;
//			if(timeHour < 0){
//				timeHour = timeHour + 24;
//			}
//			String cronDay;
//			if(overDays > 0){
//				if(timeDay <= overDays){
//					if(timeDay == overDays){
//						cronDay = "L";
//					}else{
//						cronDay = (overDays - timeDay) + "L";
//					}
//				}else{
//					cronDay = "" + (timeDay-overDays);
//				}
//			}else{
//				cronDay = "" + timeDay;
//			}
//			cronTime = "0 " + timeMinute + " " + timeHour + " " + cronDay + " * ?";
//		}else if("w".equals(cycle)){
//			//overDays 提前发布天数
//			int overDays = ((timeHour - aheadHour + 24) / 24 -1 ) * -1;
//			timeHour = (timeHour - aheadHour) % 24;
//			timeDay = (timeDay + 1 - overDays) % 7;
//			if(timeDay < 0){
//				timeDay = timeDay + 7;
//			}
//			if(timeHour < 0){
//				timeHour = timeHour + 24;
//			}
//			cronTime = "0 " + timeMinute + " " + timeHour + " ? * " + timeDay;
//		}else if("d".equals(cycle)){
//			timeHour = (timeHour - aheadHour + 24) % 24;
//			cronTime = "0 " + timeMinute + " " + timeHour + " * * ?";
//		}
//		return cronTime;
//	}
//
//	/**
//    *
//    * 功能描述：http请求，Json类型参数
//    *
//    * @param url
//    * @param inputStream
//    * @return
//    * @throws Exception
//    * @创建者 wanghailong
//    * @邮箱 wanghailong@persagy.com 修改描述
//    */
//   public static void httpPostRequest(String url, String jsonStr, String fileName) throws Exception {
//	// 生成一个httpclient对象
//	    CloseableHttpClient httpclient = HttpClients.createDefault();
//	    HttpPost httpPost = new HttpPost(url);
//		httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
//		httpPost.setEntity(new StringEntity(jsonStr, "utf-8"));
//	    HttpResponse response = httpclient.execute(httpPost);
//	    HttpEntity entity = response.getEntity();
//	    InputStream in = entity.getContent();
//	    File file = new File(fileName);
//	    try {
//	        FileOutputStream fout = new FileOutputStream(file);
//	        int l = -1;
//	        byte[] tmp = new byte[1024];
//	        while ((l = in.read(tmp)) != -1) {
//	            fout.write(tmp, 0, l);
//	            // 注意这里如果用OutputStream.write(buff)的话，图片会失真，大家可以试试
//	        }
//	        fout.flush();
//	        fout.close();
//	    } finally {
//	        // 关闭低层流。
//	        in.close();
//	    }
//	    httpclient.close();
//   }
//
//   /**
//    * 将返回结果转化为String
//    *
//    * @param entity
//    * @return
//    * @throws Exception
//    */
//   private static String getRespString(HttpEntity entity) throws Exception {
//       if (entity == null) {
//           return null;
//       }
//       InputStream is = entity.getContent();
//       StringBuffer strBuf = new StringBuffer();
//       byte[] buffer = new byte[4096];
//       int r = 0;
//       while ((r = is.read(buffer)) > 0) {
//           strBuf.append(new String(buffer, 0, r, "UTF-8"));
//       }
//       return strBuf.toString();
//   }
//
//	public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
//        URL url = new URL(urlStr);
//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//                //设置超时间为3秒
//        conn.setConnectTimeout(3*1000);
//        //防止屏蔽程序抓取而返回403错误
//        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//
//        //得到输入流
//        InputStream inputStream = conn.getInputStream();
//        //获取自己数组
//        byte[] getData = readInputStream(inputStream);
//
//        //文件保存位置
//        File saveDir = new File(savePath);
//        if(!saveDir.exists()){
//            saveDir.mkdir();
//        }
//        File file = new File(saveDir+File.separator+fileName);
//        FileOutputStream fos = new FileOutputStream(file);
//        fos.write(getData);
//        if(fos!=null){
//            fos.close();
//        }
//        if(inputStream!=null){
//            inputStream.close();
//        }
//
//
//        System.out.println("info:"+url+" download success");
//
//    }
//

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
//
//    public static void sortWoPlan(JSONObject woPlan){
//		JSONArray array = woPlan.getJSONArray("freq_times");
//		if(array != null){
//    		Collections.sort(array, new Comparator<Object>(){
//    			public int compare(Object str1, Object str2){
//    				JSONObject obj1 = JSONObject.parseObject(str1.toString());
//    				JSONObject obj2 = JSONObject.parseObject(str2.toString());
//    				JSONObject startTime1 = obj1.getJSONObject("start_time");
//    				JSONObject startTime2 = obj2.getJSONObject("start_time");
//    				int timeDay1 = startTime1.getIntValue("time_day");
//    				int timeDay2 = startTime2.getIntValue("time_day");
//    				if(timeDay1 < timeDay2){
//    					return -1;
//    				}else if(timeDay1 == timeDay2){
//    					int timeHour1 = startTime1.getIntValue("time_hour");
//    					int timeHour2 = startTime2.getIntValue("time_hour");
//    					if(timeHour1 < timeHour2){
//    						return -1;
//    					}else if(timeHour1 == timeHour2){
//    						int timeMinute1 = startTime1.getIntValue("time_minute");
//    						int timeMinute2 = startTime2.getIntValue("time_minute");
//    						if(timeMinute1 < timeMinute2){
//    							return -1;
//    						}else if(timeMinute1 == timeMinute2){
//    							return 0;
//    						}else{
//    							return 1;
//    						}
//    					}else{
//    						return 1;
//    					}
//    				}else{
//    					return -1;
//    				}
//    			}
//    		});
//    	}
//	}

//    /**
//     * 排序
//     * @param queryResult
//     * @param field  排序的字段
//     * @param order      -1:倒序  1:正序
//     * @return
//     */
//    public static JSONObject sortByField(String queryResult, String field, int order){
//        JSONObject resultJson = JSONObject.parseObject(queryResult);
//        JSONArray contentArray = resultJson.getJSONArray("Content");
//        if(contentArray != null){
//            Collections.sort(contentArray, new Comparator<Object>(){
//                public int compare(Object str1, Object str2){
//                    JSONObject obj1 = JSONObject.parseObject(str1.toString());
//                    JSONObject obj2 = JSONObject.parseObject(str2.toString());
//                    if(obj1.getLongValue(field) > obj2.getLongValue(field)){
//                        return order;
//                    }
//                    if(obj1.getLongValue(field) == obj2.getLongValue(field)){
//                        return 0;
//                    }
//                    return -1 * order;
//                }
//            });
//        }
//        resultJson.put("Content", contentArray);
//        return resultJson;
//    }
//
//    /**
//     * 排序
//     * @param queryResult
//     * @param field  排序的字段
//     * @param order      -1:倒序  1:正序
//     * @return
//     */
//    public static JSONObject sortByField(String queryResult, String preField, String field, int order){
//        JSONObject resultJson = JSONObject.parseObject(queryResult);
//        JSONArray contentArray = resultJson.getJSONArray("Content");
//        if(contentArray != null){
//            Collections.sort(contentArray, new Comparator<Object>(){
//                public int compare(Object str1, Object str2){
//                    JSONObject obj1 = JSONObject.parseObject(str1.toString());
//                    JSONObject obj2 = JSONObject.parseObject(str2.toString());
//                    if(obj1.getJSONObject(preField).getLongValue(field) > obj2.getJSONObject(preField).getLongValue(field)){
//                        return order;
//                    }
//                    if(obj1.getJSONObject(preField).getLongValue(field) == obj2.getJSONObject(preField).getLongValue(field)){
//                        return 0;
//                    }
//                    return -1 * order;
//                }
//            });
//        }
//        resultJson.put("Content", contentArray);
//        return resultJson;
//    }

}
