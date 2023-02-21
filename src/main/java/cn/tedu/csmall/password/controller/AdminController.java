package cn.tedu.csmall.password.controller;

import cn.tedu.csmall.password.pojo.dto.AdminAddNewDTO;
import cn.tedu.csmall.password.pojo.dto.AdminLoginDTO;
import cn.tedu.csmall.password.pojo.vo.AdminListItemVO;
import cn.tedu.csmall.password.security.AdminDetails;
import cn.tedu.csmall.password.security.LoginPrincipal;
import cn.tedu.csmall.password.service.AdminService;
import cn.tedu.csmall.password.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * 处理管理员相关请求的控制器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/admins")
@Api(tags = "1. 管理员管理模块")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //    public AdminController(AdminService adminService) {
    //        log.debug("创建控制器对象：AdminController，构造方法传入参数：{}",adminService);
    //        this.adminService = adminService;
    //    }

    // http://localhost:9081/admins/login
    @ApiOperation("管理员登录")
    @ApiOperationSupport(order = 50)
    @PostMapping("/login")
    public JsonResult login(AdminLoginDTO adminLoginDTO) {
        log.debug("开始处理【管理员登录】的请求，参数：{}", adminLoginDTO);

        // ↓↓↓↓↓↓↓ 获取调用方法的返回结果，即JWT数据
        String jwt = adminService.login(adminLoginDTO);

        //                   ↓↓↓ 将JWT封装到响应对象中
        return JsonResult.ok(jwt);
    }

    @ApiOperation("添加管理员")
    @ApiOperationSupport(order = 100)
    @PreAuthorize("hasAnyAuthority('/ams/admin/add-new')")
    @PostMapping("/add-new")
    public JsonResult addNew(@Valid AdminAddNewDTO adminAddNewDTO) {
        log.debug("开始处理【添加管理员】的请求，参数：{}", adminAddNewDTO);
        adminService.addNew(adminAddNewDTO);
        return JsonResult.ok();
    }

    @ApiOperation("根据id删除管理员")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParam(name = "id", value = "管理员ID", required = true, type = "Long")
    @PreAuthorize("hasAuthority('/ams/admin/delete')") // 新增
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult delete(@PathVariable Long id,
                             @ApiIgnore @AuthenticationPrincipal LoginPrincipal adminDetails) {
        log.debug("开始处理【根据id删除删除管理员】的请求，参数：{}", id);
        log.debug("当事人：{}", adminDetails);
        log.debug("当事人的ID：{}", adminDetails.getId()); // 获取扩展的属性
        log.debug("当事人的用户名：{}", adminDetails.getUsername());
        adminService.delete(id);
        return JsonResult.ok();
    }

    // http://localhost:9081/admins/9527/enable
    @ApiOperation("启用管理员")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParam(name = "id", value = "管理员ID", required = true, type = "Long")
    @PreAuthorize("hasAuthority('/ams/admin/update')")
    @PostMapping("/{id:[0-9]+}/enable")
    public JsonResult setEnable(@PathVariable Long id) {
        log.debug("开始处理【启用管理员】的请求，参数：{}", id);
        adminService.setEnable(id);
        return JsonResult.ok();
    }

    // http://localhost:9081/admins/9527/disable
    @ApiOperation("禁用管理员")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParam(name = "id", value = "管理员ID", required = true, type = "Long")
    @PreAuthorize("hasAuthority('/ams/admin/update')")
    @PostMapping("/{id:[0-9]+}/disable")
    public JsonResult setDisable(@PathVariable Long id) {
        log.debug("开始处理【禁用管理员】的请求，参数：{}", id);
        adminService.setDisable(id);
        return JsonResult.ok();
    }

    // http://localhost:9081/admins/
    @ApiOperation("查询管理员列表")
    @ApiOperationSupport(order = 420)
    @PreAuthorize("hasAuthority('/ams/admin/read')")
    @GetMapping("")
    public JsonResult list() {
        log.debug("开始处理【查询管理员列表】的请求，参数：无");
        List<AdminListItemVO> list = adminService.list();
        return JsonResult.ok(list);
    }
}
