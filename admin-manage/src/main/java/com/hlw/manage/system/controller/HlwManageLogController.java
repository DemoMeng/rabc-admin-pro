package com.hlw.manage.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlw.common.model.dto.system.LogListDto;
import com.hlw.common.model.dto.system.UserListDto;
import com.hlw.common.model.vo.system.LogListVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.core.annotations.RequirePermission;
import com.hlw.manage.core.annotations.TargetSource;
import com.hlw.manage.system.service.IHlwManageLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mqz
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/system/log")
@Api(tags = "日志管理控制入口")
public class HlwManageLogController {

    @Autowired
    private IHlwManageLogService logService;

    @PostMapping("/getSearch")
    @ApiOperation(value = "获取筛选条件")
    @TargetSource()
    @RequirePermission(permission = "system:log:list")
    public ResponseBean userList(){
        return logService.getSearch();
    }


    @PostMapping("/list")
    @ApiOperation(value = "日志列表")
    @TargetSource()
    @RequirePermission(permission = "system:log:list")
    public ResponseBean<IPage<LogListVo>> userList(@RequestBody LogListDto dto){
        return logService.getAll(dto);
    }





}
