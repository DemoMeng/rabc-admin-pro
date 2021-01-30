package com.hlw.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mqz
 * @description
 * @since 2020/5/6
 */
public class AddressUtils {

    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    public static String getRealAddressByIP(String ip)
    {
        String address = "XX XX";
        /**内网不查询*/
        if ("127.0.0.1".equals(ip)) {
            return "局域网";
        }
        String rspStr = null;
        try {
            rspStr = OkHttpUtils.post(IP_URL+"?ip=" + ip);
            if (StringUtils.isEmpty(rspStr)) {
                log.error("获取地理位置异常 {}", ip);
                return address;
            }
            JSONObject obj = null;
            try {
                JSONObject data = JSONObject.parseObject(rspStr).getJSONObject("data");
                String region = data.getString("region");
                String city = data.getString("city");
                address = region + " " + city;
            }
            catch (Exception e) {
                log.error("获取地理位置异常 {}", ip);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }

    public static void main(String[] args) {
        System.out.println(getAreaByIp("61.143.61.19"));
        System.out.println(getAreaWithGD("121.35.3.219"));
    }


    public static String getAreaByIp(String ip){
        String url = "http://ip.taobao.com/outGetIpInfo";
        String address = "XX XX";
        /**内网不查询*/
        if ("127.0.0.1".equals(ip)) {
            return "局域网";
        }
        Map<String,String> param = new HashMap<>();
        param.put("ip",ip);
        param.put("accessKey","alibaba-inc");
        String rspStr = null;
        try {
            rspStr = OkHttpUtils.formPost(url,param);
            if (StringUtils.isEmpty(rspStr)) {
                log.error("获取地理位置异常 {}", ip);
                return address;
            }
            try {
                JSONObject ret = JSONObject.parseObject(rspStr);
                if(ret.getInteger("code") == 0){
                    JSONObject data = ret.getJSONObject("data");
                    String region = data.getString("region");
                    String city = data.getString("city");
                    address = region + "省" + city+"市";
                }
            }
            catch (Exception e) {
                log.error("获取地理位置异常 {}", ip);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }


    public static String getAreaWithGD(String ip){
        String address = "地球村";
        if(StringUtils.isEmpty(ip)){
            return address+"（ip为空）";
        }
        if("127.0.0.1".equals(ip)||ip.contains("192.168")){
            address = "局域网";
            return address;
        }
        String url = "https://restapi.amap.com/v3/ip?ip="+ip+"&key=bb27903f7773fd3e3fe4c7756ce8c38d&output=JSON";
        try {
            String result = OkHttpUtils.get(url);
            JSONObject jb = JSONObject.parseObject(result);
            if("1".equals(jb.getString("status"))){
                System.out.println(jb.toJSONString());
                String province = jb.getString("province");
                String city = jb.getString("city");
                address = province+" "+city;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }






}
