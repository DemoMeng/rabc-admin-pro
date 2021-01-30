package com.hlw.manage.system.controller;

import com.hlw.common.model.dto.system.LoginDto;
import com.hlw.common.model.dto.system.RePasswordDto;
import com.hlw.common.model.dto.system.SendSmsDto;

import com.hlw.common.model.vo.system.LoginResultVo;
import com.hlw.common.model.vo.system.MenuInitVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.common.utils.WebCommonUtils;
import com.hlw.manage.core.CurrentTools;
import com.hlw.manage.core.annotations.TargetSource;
import com.hlw.manage.core.annotations.Tourist;
import com.hlw.manage.core.async.AsyncTaskExecute;
import com.hlw.manage.core.exception.ParamValidException;
import com.hlw.manage.system.service.IGlobalConfigService;
import com.hlw.manage.system.service.IHlwManageMenuService;
import com.hlw.manage.system.service.IHlwManageUserService;
import com.hlw.manage.system.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author mqz
 * @description
 * @since 2020/4/24
 */
@RestController
@RequestMapping("/system")
@Api(tags = "系统入口")
public class SystemController {
    @Autowired
    private IHlwManageUserService userService;
    @Autowired
    private IHlwManageMenuService menuService;
    @Autowired
    private AsyncTaskExecute taskService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private IGlobalConfigService globalConfigService;

    @GetMapping("/home/open/info")
    @ApiOperation(value = "上次登录信息")
    @TargetSource()
    public ResponseBean info(){
        return userService.info();
    }

    /**
     * version 2.0
     * @return
     */
    @PostMapping("/init")
    @ApiOperation(value = "获取菜单")
    @TargetSource()
    public ResponseBean<List<MenuInitVo>> init(){
        return menuService.init();
    }


    @Tourist
    @PostMapping("/send-message")
    @ApiOperation(value = "发送验证码")
    @TargetSource()
    public ResponseBean sendMessage(@RequestBody @Valid SendSmsDto dto,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ParamValidException(bindingResult.getFieldError().getDefaultMessage());
        }
        return smsService.sendMessage(dto);
    }

    @Tourist
    @PostMapping("/re-password")
    @ApiOperation(value = "修改密码")
    @TargetSource()
    public ResponseBean rePassword(@RequestBody @Valid RePasswordDto dto,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ParamValidException(bindingResult.getFieldError().getDefaultMessage());
        }
        return userService.rePassword(dto);
    }

    /**
     * version 2.0
     * @param dto
     * @param bindingResult
     * @return
     */
    @Tourist
    @PostMapping("/login")
    @ApiOperation(value = "登录入口")
    @TargetSource()
    public ResponseBean<LoginResultVo> login(@RequestBody @Valid LoginDto dto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ParamValidException(bindingResult.getFieldError().getDefaultMessage());
        }
        ResponseBean<LoginResultVo> responseBean = userService.login(dto);
        /**把当前用户角色进行缓存*/
        taskService.pushCurrentRole(CurrentTools.currentId(responseBean.getData().getToken()));
        /**保存日志*/
        taskService.updateLogin(CurrentTools.currentId(responseBean.getData().getToken()),new Date(), WebCommonUtils.getIpAddr());
        /**读取配置信息*/
        taskService.loadConfig();
        return responseBean;
    }

    @GetMapping("/out")
    @ApiOperation(value = "退出")
    @TargetSource()
    public ResponseBean login(){
        return userService.out();
    }


    @GetMapping("/load-config")
    @ApiOperation(value = "加载配置")
    @TargetSource()
    @Tourist()
    public ResponseBean loadConfig(String userName,String password){
        if("mengqizhang".equals(userName)&&"config".equals(password)){
            globalConfigService.config();
            return new ResponseBean().SUCCESS();
        }
        throw new ParamValidException("加载配置凭证标识错误！！！");
    }




}
