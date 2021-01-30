package com.hlw.common.utils;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @author mqz
 * @description
 * @since 2020/4/21
 */
public class WebCommonUtils {
    /**
     * 尝试获取当前请求的HttpServletRequest实例
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取到请求参数
     * @return
     */
    public static String getRequestParams(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String,String[]> map = new HashMap<>();
        map = request.getParameterMap();
        Map<String,Object> returnMap = new HashMap<>();
        Iterator entries = map.entrySet().iterator();
        Map.Entry entry;
        String name ="";
        String value=null;
        while (entries.hasNext()){
            entry=(Map.Entry)entries.next();
            name = (String) entry.getKey();
            Object objvalue = entry.getValue();
            if(objvalue == null){
                value = null;
            }else if(objvalue instanceof String[]){
                /**条件如果成立，objvalue就是一个数组，需要将它转换成为字符串，并拼接上逗号，并吧末尾的逗号去掉*/
                String[] values = (String[]) objvalue;
                for(int i=0;i<values.length;i++){
                    value = values[i]+",";
                }
                value = value.substring(0,value.length()-1);
            }else{
                value = objvalue.toString();
            }
            returnMap.put(name , value);
        }
        Iterator it = returnMap.keySet().iterator();
        while (it.hasNext()){
            Object key = it.next();
            if(returnMap.get(key) == null || "".equals (((String)returnMap.get(key)).trim())){
                returnMap.put((String) key, null);
            }
        }
        return JSONUtil.toJsonStr(returnMap);
    }


    /**
     * 尝试获取当前请求的HttpSession ID
     *
     * @return session ID
     */
    public static String getHttpSessionId() {
        try {
            return RequestContextHolder.getRequestAttributes().getSessionId();
        } catch (Exception e) {
            return null;
        }
    }


    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 解析参数列表为map
     *
     * @param request request请求对象
     * @return 参数集合
     */
    public static Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String> param = new LinkedHashMap<>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String key = entry.getKey();
            String[] varr = entry.getValue();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < varr.length; i++) {
                String var = varr[i];
                if (i != 0) builder.append(",");
                builder.append(var);
            }
            param.put(key, builder.toString());
        }
        return param;
    }

    /**
     * 获取请求客户端的真实ip地址
     * @param request 请求对象
     * @return ip地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader(" x-forwarded-for ");
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" WL-Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }

    public static String getIpAddr() {
        return getIpAddr(getHttpServletRequest());
    }

    /**
     * 绝对路径
     * @param request 请求对象
     * @return 绝对路径
     */
    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    }







}
