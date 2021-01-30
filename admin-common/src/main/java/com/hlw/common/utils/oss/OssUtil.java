package com.hlw.common.utils.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

/**
 * @author zwd
 * @since 2020-07-01
 **/
public class OssUtil {

    private OSSClient ossClient = null;

    public static OSSClient initClient() {
        return new OSSClient(OssConfig.EndPoint, OssConfig.AccessKeyId, OssConfig.AccessKeySecret);
    }

    public static String uploadImage(File file) {
        String fileName = null;
        OSSClient ossClient = initClient();
        try {
            String originalFilename = file.getName();
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            fileName = "images/" + new Date().getTime() + fileType;
            ossClient.putObject(OssConfig.BucketName, fileName, new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

        ossClient.shutdown();
        return OssConfig.baseurl + fileName;
    }


    public static String uploadImage(MultipartFile file) {
        String fileName = null;
        OSSClient ossClient = initClient();
        try {
            String originalFilename = file.getOriginalFilename();
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            fileName = "images/" + new Date().getTime() + fileType;
            ossClient.putObject(OssConfig.BucketName, fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

        ossClient.shutdown();
        return OssConfig.baseurl + fileName;
    }

    public static String uploadSeal(MultipartFile file) {
        String fileName = null;
        OSSClient ossClient = initClient();
        try {
            String originalFilename = file.getOriginalFilename();
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            fileName = "seal/" + System.currentTimeMillis() + fileType;
            ossClient.putObject(OssConfig.getBucketName(), fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

        ossClient.shutdown();
        return OssConfig.getBaseurl() + fileName;

    }


    public static String uploadPdfByStream(InputStream inputStream, String fileName) {
        OSSClient ossClient = initClient();
        fileName = "pdf/" + fileName;
        ossClient.putObject(OssConfig.getBucketName(), fileName, inputStream);
        ossClient.shutdown();
        return OssConfig.getBaseurl() + fileName;
    }


    public static String uploadDoc(MultipartFile file) {
        String fileName = null;
        OSSClient ossClient = initClient();
        try {
            String originalFilename = file.getOriginalFilename();
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            fileName = "doc/" + new Date().getTime() + fileType;
            ossClient.putObject(OssConfig.getBucketName(), fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

        return OssConfig.getBaseurl() + fileName;

    }

    public static String uploadSealByStream(InputStream inputStream, String fileName) {
        OSSClient ossClient = initClient();
        fileName = "seal/" + fileName;
        ossClient.putObject(OssConfig.getBucketName(), fileName, inputStream);
        ossClient.shutdown();
        return OssConfig.getBaseurl() + fileName;
    }

    public static void downloadFile(File file, String key) {
        OSSClient ossClient = initClient();

        try {
            ossClient.getObject(new GetObjectRequest(OssConfig.getBucketName(), key), file);
        } catch (ClientException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }

    public static String downloadPdf(String pdfpath) {
        String imgUrl = pdfpath.split("com/")[1];
        String localimgUrl = System.getProperty("user.dir") + "/" + new Date().getTime() + ".pdf";
        File imglocalUrl = new File(localimgUrl);
        OssUtil.downloadFile(imglocalUrl, imgUrl);
        return localimgUrl;
    }

    public static String downloadImg(String imgpath) {
        String imgUrl = imgpath.split("com/")[1];
        String localimgUrl = System.getProperty("user.dir") + "/" + new Date().getTime() + ".png";
        File imglocalUrl = new File(localimgUrl);
        OssUtil.downloadFile(imglocalUrl, imgUrl);
        return localimgUrl;
    }

    public static String downloadImgHxy(String imgpath) {
        String imgUrl = imgpath.split("com/")[1];
        String localimgUrl = "/tmp/image/" + System.currentTimeMillis() + ".png";
        //String localimgUrl = System.getProperty("user.dir") + "/" + new Date().getTime() + ".png";
        File imglocalUrl = new File(localimgUrl);
        OssUtil.downloadFile(imglocalUrl, imgUrl);
        return localimgUrl;
    }

    public static String downloadPdfHxy(String pdfpath) {
        String imgUrl = pdfpath.split("com/")[1];
      //  String localPdfUrl = "/tmp/pdf/" + new Date().getTime() + ".pdf";
        String localPdfUrl = "E:\\java\\signonline4.0\\tmp\\pdf\\" + new Date().getTime() + ".pdf";
        File imglocalUrl = new File(localPdfUrl);
        OssUtil.downloadFile(imglocalUrl, imgUrl);
        return localPdfUrl;
    }


    /**
     * zhangjing
     * 从OSS固定地址下载Excel
     *
     * @return
     */
    public static String downloadExcel() {
        String fileName = "excel/template.xlsx";//OSS存储路径
        String localExcelPath = "/tmp/excel/template.xlsx";//客户端本地存储路径
        File file = new File(localExcelPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();//新建文件夹
        }
        OssUtil.downloadFile(file, fileName);
        return localExcelPath;
    }

    /**
     * 流上传到OSS
     *
     * @param key   路径
     * @param bytes byte数组
     */
    public static void uploadSeal(String key, byte[] bytes) {
        OSSClient ossClient = initClient();
        try {
            ossClient.putObject(OssConfig.getBucketName(), key, new ByteArrayInputStream(bytes));
        } catch (ClientException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
            ossClient = null;
        }
    }

    public static void upload(File file, String key) {
        OSSClient ossClient = initClient();
        try {
            ossClient.putObject(OssConfig.getBucketName(), key, file);
        } catch (ClientException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }

    public static String uploadImage(byte[] bytes) {
        String fileName = null;
        OSSClient ossClient = initClient();
        try {
            fileName = "images/" + new Date().getTime() + ".png";
            ossClient.putObject(OssConfig.BucketName, fileName, new ByteArrayInputStream(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

        ossClient.shutdown();
        return OssConfig.baseurl + fileName;


    }

    public static String uploadPdf(byte[] bytes) {
        String fileName = null;
        OSSClient ossClient = initClient();
        try {
            fileName = "pdf/" + new Date().getTime() + ".pdf";
            ossClient.putObject(OssConfig.BucketName, fileName, new ByteArrayInputStream(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

        ossClient.shutdown();
        return OssConfig.baseurl + fileName;
    }

    public static void delete(String url) {
        OSSClient ossClient = initClient();

        String path = url.split("com/")[1];
        ossClient.deleteObject(OssConfig.BucketName, path);
    }

}
