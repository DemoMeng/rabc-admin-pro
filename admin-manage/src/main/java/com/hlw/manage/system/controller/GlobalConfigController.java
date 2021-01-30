package com.hlw.manage.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlw.common.model.dto.system.GlobalConfigEditDto;
import com.hlw.common.model.dto.system.MenuListDto;
import com.hlw.common.model.vo.system.GlobalConfigListVo;
import com.hlw.common.model.vo.system.MenuListVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.core.annotations.SuperAdmin;
import com.hlw.manage.core.annotations.TargetSource;
import com.hlw.manage.core.exception.ParamValidException;
import com.hlw.manage.system.entity.GlobalConfig;
import com.hlw.manage.system.service.IGlobalConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mqz
 * @since 2020-07-10
 */
@RestController
@RequestMapping("/system/global-config")
@Api(tags = "系统设置（接口赠送审核设置）")
public class GlobalConfigController {

    @Autowired
    private IGlobalConfigService globalConfigService;

    @PostMapping("/list")
    @ApiOperation(value = "列表")
    @TargetSource()
    @SuperAdmin()
    public ResponseBean<List<GlobalConfigListVo>> list(){
        return globalConfigService.getAllList();
    }

    @PostMapping("/touch")
    @ApiOperation(value = "编辑")
    @TargetSource()
    @SuperAdmin()
    public ResponseBean<List<GlobalConfigListVo>> edit(@RequestBody @Valid List<GlobalConfigEditDto> gc,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ParamValidException(bindingResult.getFieldError().getDefaultMessage());
        }
        return globalConfigService.edit(gc);
    }

}
