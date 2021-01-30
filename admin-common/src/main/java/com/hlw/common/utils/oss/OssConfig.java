package com.hlw.common.utils.oss;


/**
 * @author zwd
 * @since 2019-06-14
 **/
public class OssConfig {

    public final static String AccessKeyId = "XXXXX";

    public final static String AccessKeySecret = "XXXXX";

    public final static String EndPoint = "http://oss-cn-hangzhou.aliyuncs.com";

    public final static String BucketName = "XXXX";

    public final static String baseurl = "XXXX";

    public static String getAccessKeyId() {
        return AccessKeyId;
    }

    public static String getAccessKeySecret() {
        return AccessKeySecret;
    }

    public static String getEndPoint() {
        return EndPoint;
    }

    public static String getBucketName() {
        return BucketName;
    }

    public static String getBaseurl() {
        return baseurl;
    }
}
