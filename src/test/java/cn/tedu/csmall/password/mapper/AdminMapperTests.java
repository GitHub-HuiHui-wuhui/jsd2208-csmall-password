package cn.tedu.csmall.password.mapper;

import cn.tedu.csmall.password.pojo.dto.AdminAddNewDTO;
import cn.tedu.csmall.password.pojo.entity.Admin;
import cn.tedu.csmall.password.pojo.vo.AdminStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class AdminMapperTests {
    @Autowired
    AdminMapper adminMapper;

    @Test
    void insert() {
        Admin admin = new Admin();
        admin.setUsername("jayzhou");
        admin.setPassword("123456");
        admin.setPhone("13800138001");
        admin.setEmail("jayzhou@baidu.com");

        log.debug("插入数据之前，参数：{}", admin);
        int row = adminMapper.insert(admin);
        log.debug("插入数据完成，受影响的行数：{}", row);
        log.debug("插入数据之后，参数：{}", admin);
    }

    @Test
    void insertBeach() {
        List<Admin> admins = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Admin admin = new Admin();
            admin.setUsername("test-admin-" + i);
            admins.add(admin);
        }

        int rows = adminMapper.insertBeach(admins);
        log.debug("批量插入完成，受影响的行数：{}", rows);
    }

    @Test
    void deleteById() {
        Long id = 10L;
        int rows = adminMapper.deleteById(id);
        log.debug("删除完成，受影响的行数：{}", rows);
    }

    @Test
    void deleteByIds() {
        Long[] ids = {9L, 8L, 11L};
        int rows = adminMapper.deleteByIds(ids);
        log.debug("批量删除完成，受影响的行数：{}", rows);
    }

    @Test
    void update() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setNickname("新-测试数据001");

        int rows = adminMapper.update(admin);
        log.debug("更新完成，受影响的行数：{}", rows);
    }

    @Test
    void count() {
        int count = adminMapper.count();
        log.debug("统计完成，表中的数据的数量：{}", count);
    }

    @Test
    void countByUsername() {
        String username = "jayzhou";
        int count = adminMapper.countByUsername(username);
        log.debug("根据用户名【{}】统计管理员账号的数量：{}", username, count);
    }

    @Test
    void countByPhone() {
        String phone = "13800138001";
        int count = adminMapper.contByPhone(phone);
        log.debug("根据电话【{}】统计管理员账号的数量：{}", phone, count);
    }

    @Test
    void countByEmail() {
        String email = "jayzhou@baidu.com";
        int count = adminMapper.countByEmail(email);
        log.debug("根据email【{}】统计管理员账号的数量：{}", email, count);
    }

    @Test
    void list() {
        List<?> list = adminMapper.list();
        log.debug("查询列表完成，结果中的数据的数量：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }

    @Test
    void getLoginInfoByUsername() {
        String username = "root";
        Object queryResult = adminMapper.getLoginInfoByUsername(username);
        log.debug("根据用户名【{}】查询数据详情完成，查询结果：{}", username, queryResult);
    }

    @Test
    void getStandById() {
        Long id = 2L;
        Object queryResult = adminMapper.getStandById(id);
        log.debug("根据id【{}】查询数据详情完成，查询结果：{}", id, queryResult);
    }
}
