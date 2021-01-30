package com.hlw.manage.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlw.common.model.dto.system.RoleCheckDto;
import com.hlw.common.model.dto.system.RoleListDto;
import com.hlw.common.model.dto.system.RoleMenuDto;
import com.hlw.common.model.vo.system.CurrentRoleVo;
import com.hlw.common.model.vo.system.CurrentVo;
import com.hlw.common.model.vo.system.RoleEditVo;
import com.hlw.common.model.vo.system.RoleVo;
import com.hlw.common.response.ResponseBean;
import com.hlw.manage.core.annotations.Logs;
import com.hlw.manage.core.annotations.SuperAdmin;
import com.hlw.manage.core.annotations.TargetSource;
import com.hlw.manage.core.async.AsyncTaskExecute;
import com.hlw.manage.system.service.IHlwManageRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author mqz
 * @since 2020-04-26
 */
@RestController
@RequestMapping("/system/role")
@Api(tags = "角色控制入口")
public class HlwManageRoleController {

    @Autowired
    private IHlwManageRoleService roleService;
    @Autowired
    private AsyncTaskExecute taskService;


    /**
     * version 2.0
     * @return
     */
    @GetMapping("/currentRole")
    @ApiOperation(value = "当前登录用户所属角色集合")
    @TargetSource()
    public ResponseBean<List<CurrentRoleVo>> current(){
        return roleService.current();
    }

    @ApiOperation(value = "角色列表")
    @PostMapping("/list")
    @TargetSource()
    @SuperAdmin()
    public ResponseBean<IPage<RoleVo>> list(@RequestBody RoleListDto dto){
        return roleService.getAll(dto);
    }


    @ApiOperation(value = "角色编辑回显")
    @GetMapping("/echo")
    @TargetSource()
    @SuperAdmin()
    public ResponseBean<RoleEditVo> echo(@ApiParam(name = "id",value = "角色id") Long id){
        //return roleService.echo(id);
        return roleService.echoBetter(id);
    }

    @ApiOperation(value = "角色删除")
    @GetMapping("/remove")
    @TargetSource()
    @Logs()
    @SuperAdmin()
    public ResponseBean remove(@RequestParam @ApiParam(name = "id",value = "角色id") Long id){
        ResponseBean responseBean = roleService.removeRole(id);
        /**清除用户菜单缓存*/
        taskService.removeRoleMenu();
        taskService.deleteSaveRole();
        taskService.deleteChildMenu();
        return responseBean;
    }

    @ApiOperation(value = "检查编号和名称是否重复")
    @PostMapping("/check")
    @TargetSource()
    @SuperAdmin()
    public ResponseBean list(@RequestBody RoleCheckDto dto){
        return roleService.check(dto);
    }


    @ApiOperation(value = "角色操作保存")
    @PostMapping("/save")
    @TargetSource()
    @Logs()
    @SuperAdmin()
    public ResponseBean save(@RequestBody RoleMenuDto dto){
        ResponseBean response = roleService.saveRole(dto);
        /**清除用户菜单缓存*/
        taskService.removeRoleMenu();
        taskService.deleteSaveRole();
        taskService.deleteChildMenu();
        return response;
    }



//    @ApiOperation(value = "角色菜单操作回显")
//    @PostMapping("/roleMenuEcho")
//    @TargetSource()
//    @Logs()
//    public ResponseBean roleMenuEcho(@ApiParam(name = "roleId", value = "角色id") Long roleId){
//        return roleService.roleMenuEcho(roleId);
//    }


//    @ApiOperation(value = "角色菜单操作保存")
//    @PostMapping("/roleMenuSave")
//    @TargetSource()
//    @Logs()
//    public ResponseBean roleMenuSave(@RequestBody RoleMenuDto dto){
//        return roleService.roleMenuSave(dto);
//    }

}
