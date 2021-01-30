package com.hlw.manage.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlw.common.model.dto.system.MenuListDto;
import com.hlw.common.model.dto.system.MenuSaveDto;
import com.hlw.common.model.vo.system.MenuChildListVo;
import com.hlw.common.model.vo.system.MenuEditVo;
import com.hlw.common.model.vo.system.MenuListVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.core.annotations.Logs;
import com.hlw.manage.core.annotations.SuperAdmin;
import com.hlw.manage.core.annotations.TargetSource;
import com.hlw.manage.core.exception.ParamValidException;
import com.hlw.manage.system.service.IHlwManageMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author mqz
 * @since 2020-04-26
 */
@RestController
@RequestMapping("/system/menu")
@Api(tags = "菜单控制入口")
public class HlwManageMenuController {

    @Autowired
    private IHlwManageMenuService menuService;

    @PostMapping("/list")
    @ApiOperation(value = "菜单列表")
    @TargetSource()
    @SuperAdmin()
    public ResponseBean<IPage<MenuListVo>> list(@RequestBody @Valid MenuListDto dto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ParamValidException(bindingResult.getFieldError().getDefaultMessage());
        }
        return menuService.getAllList(dto);
    }

    @GetMapping("/parentMenu")
    @ApiOperation(value = "父级菜单列表")
    @TargetSource()
    @SuperAdmin()
    public ResponseBean<List<MenuListVo>> parentMenuList(@RequestParam @ApiParam(value = "当前级别归属：一级菜单：1，二级菜单:2 ,三级菜单:3 ,tab 链 4 ",name = "type") Integer type){
        return menuService.parentMenuList(type);
    }

    @GetMapping("/remove")
    @ApiOperation(value = "删除")
    @TargetSource()
    @SuperAdmin()
    public ResponseBean remove(@RequestParam @ApiParam(name = "id",value = "菜单id",required = true) Long id){
        return menuService.removeMenu(id);
    }

    @GetMapping("/editEcho")
    @ApiOperation(value = "菜单编辑回显")
    @TargetSource()
    @SuperAdmin()
    public ResponseBean<MenuEditVo> editEcho(@RequestParam @ApiParam(name = "id",value = "菜单id") Long id){
        return menuService.editEcho(id);
    }

    @Logs()
    @PostMapping("/save")
    @TargetSource()
    @SuperAdmin()
    @ApiOperation(value = "菜单保存  新增目录 parentId 为 0 ，新增菜单 parentId 为父级菜单id")
    public ResponseBean save(@Valid @RequestBody MenuSaveDto dto,BindingResult result){
        if(result.hasErrors()){
            throw new ParamValidException(result.getFieldError().getDefaultMessage());
        }
        return menuService.saveMenu(dto);
    }


    @GetMapping("/child-list")
    @ApiOperation(value = "子列表集")
    @TargetSource()
    @SuperAdmin()
    public ResponseBean<List<MenuChildListVo>> getChild(@RequestParam @ApiParam(name = "id",value = "id") Long id){
        return menuService.getChild(id);
    }




}
