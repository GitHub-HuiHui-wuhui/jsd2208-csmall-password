package cn.tedu.csmall.password.service;

import cn.tedu.csmall.password.ex.ServiceException;
import cn.tedu.csmall.password.pojo.dto.AdminAddNewDTO;
import cn.tedu.csmall.password.pojo.dto.AdminLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class AdminServiceTests {

    @Autowired
    AdminService adminService;

    @Test
    void login() {
        AdminLoginDTO adminLoginDTO = new AdminLoginDTO();
        adminLoginDTO.setUsername("liucangsong");
        adminLoginDTO.setPassword("123456");

        try {
            String jwt = adminService.login(adminLoginDTO);
            log.debug("登录成功，JWT：{}", jwt);
        } catch (Throwable e) {
            // 由于不确定Spring Security会抛出什么类型的异常
            // 所以，捕获的是Throwable
            // 并且，在处理时，应该打印信息，以了解什么情况下会出现哪种异常
            e.printStackTrace();
        }
    }

    @Test
    void addNew() {
        AdminAddNewDTO admin = new AdminAddNewDTO();
        admin.setUsername("管理员100");
        admin.setPassword("123456");
        admin.setPhone("13900139100");
        admin.setEmail("13900139100@baidu.com");
        admin.setRoleIds(new Long[]{4L, 5L, 6L});

        try {
            adminService.addNew(admin);
            log.debug("添加数据完成！");
        } catch (ServiceException e) {
            log.debug("添加数据失败！名称已经被占用！");
        }
    }

    @Test
    void setEnable() {
        Long id = 1L;
        try {
            adminService.setEnable(id);
            log.debug("启用管理员完成！");
        } catch (ServiceException e) {
            log.debug("启用管理员失败！具体原因请参见日志！");
        }
    }

    @Test
    void setDisable() {
        Long id = 1L;

        try {
            adminService.setDisable(id);
            log.debug("禁用管理员完成！");
        } catch (ServiceException e) {
            log.debug("禁用管理员失败！具体原因请参见日志！");
        }
    }

    @Test
    void delete() {
        Long id = 7L;

        try {
            adminService.delete(id);
            log.debug("删除管理员完成！");
        } catch (ServiceException e) {
            log.debug("删除管理员失败！具体原因请参见日志！");
        }
    }

    @Test
    void list() {
        List<?> list = adminService.list();
        log.debug("查询列表完成，列表中的数据的数量：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }


}
