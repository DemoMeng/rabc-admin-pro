package com.hlw.manage.manage.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlw.common.model.dto.manage.*;
import com.hlw.common.model.vo.manage.CompanyListVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.core.annotations.RequirePermission;
import com.hlw.manage.core.annotations.TargetSource;
import com.hlw.manage.core.exception.ParamValidException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mqz
 * @since 2020-05-09
 */
@RestController
@RequestMapping("/manage/customer/company")
@Api(tags = "企业用户")
public class CompanyProdController {

    @PostMapping("/prod-list")
    @TargetSource(value = "manage")
    @ApiOperation(value = "专业版用户列表")
    @RequirePermission(permission = "manage:user:company:prod:list")
    public ResponseBean<IPage<CompanyListVo>> prodList(@Valid @RequestBody CompanyListDto dto,BindingResult result){
        if(result.hasErrors()){
            throw new ParamValidException(result.getFieldError().getDefaultMessage());
        }
        return new ResponseBean().SUCCESS();
    }

    @PostMapping("/prod-save")
    @TargetSource(value = "manage")
    @ApiOperation(value = "创建账号")
    @RequirePermission(permission = "manage:user:company:prod:add")
    public ResponseBean save(@RequestBody @Valid CompanySaveDto dto, BindingResult result){
        if(result.hasErrors()){
            throw new ParamValidException(result.getFieldError().getDefaultMessage());
        }
        //TODO Your Service To Implement The Detail
        return new ResponseBean().SUCCESS();
    }











}
