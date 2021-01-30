package com.hlw.manage.system.controller;

import com.alibaba.fastjson.JSON;
import com.hlw.common.response.ResponseBean;
import com.hlw.common.utils.oss.OssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author mqz
 * @description
 * @since 2020/5/12
 */
@RestController
@RequestMapping("/system/file")
@Api(tags = "文件上传相关入口")
public class FileController {

    //TODO 可以将文件部分抽离出一个模块

    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public ResponseBean uploadCompanyImg(@RequestParam("file") MultipartFile file){
        return new ResponseBean().SUCCESS(OssUtil.uploadImage(file));
    }


}
