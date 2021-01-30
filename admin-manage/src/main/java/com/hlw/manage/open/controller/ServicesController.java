package com.hlw.manage.open.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlw.common.model.dto.open.ServicesEditDto;
import com.hlw.common.model.dto.open.ServicesListDto;
import com.hlw.common.model.dto.open.ServicesSaveDto;
import com.hlw.common.model.vo.open.ServicesListVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.core.annotations.RequirePermission;
import com.hlw.manage.core.annotations.TargetSource;
import com.hlw.manage.core.exception.ParamValidException;
import com.hlw.manage.open.service.ITServicesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 服务表 前端控制器
 * </p>
 *
 * @author mqz
 * @since 2020-07-14
 */
@RestController
@RequestMapping("/open/services")
@Api(tags = "API管理")
public class ServicesController {

    @Autowired
    private ITServicesService servicesService;


    @PostMapping("/on-sale/list")
    @ApiOperation(value = "列表")
    @TargetSource(value = "open")
    @RequirePermission(permission = "open:api:on-sale:list")
    public ResponseBean<IPage<ServicesListVo>> list(@RequestBody ServicesListDto dto){
        return servicesService.getList(dto);
    }

    @PostMapping("/for-sale/edit")
    @ApiOperation(value = "编辑")
    @TargetSource(value = "open")
    @RequirePermission(permission = "open:api:for-sale:edit")
    public ResponseBean edit(@RequestBody @Valid ServicesEditDto dto, BindingResult result){
        if(result.hasErrors()){
            throw new ParamValidException(result.getFieldError().getDefaultMessage());
        }
        return servicesService.edit(dto);
    }

    @GetMapping("/for-sale/remove")
    @ApiOperation(value = "删除")
    @TargetSource(value = "open")
    @RequirePermission(permission = "open:api:for-sale:remove")
    public ResponseBean remove(@RequestParam @ApiParam(name = "id",value = "id") Integer id){
        return servicesService.removeServices(id);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    @TargetSource(value = "open")
    @RequirePermission(permission = "open:api:for-sale:add")
    public ResponseBean save(@RequestBody @Valid ServicesSaveDto dto, BindingResult result){
        if(result.hasErrors()){
            throw new ParamValidException(result.getFieldError().getDefaultMessage());
        }
        return servicesService.saveServices(dto);
    }
}