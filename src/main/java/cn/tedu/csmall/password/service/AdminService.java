package cn.tedu.csmall.password.service;

import cn.tedu.csmall.password.pojo.dto.AdminAddNewDTO;
import cn.tedu.csmall.password.pojo.dto.AdminLoginDTO;
import cn.tedu.csmall.password.pojo.vo.AdminListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 处理管理员数据的业务接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Transactional
public interface AdminService {

    /**
     * 管理员登录
     *
     * @param adminLoginDTO 封装了登录参数的对象
     * @return 管理员登录成功后将得到的JWT
     */
    String login(AdminLoginDTO adminLoginDTO);

    /**
     * 添加管理员
     *
     * @param adminAddNewDTO 管理员数据
     */
    //@Transactional //事务性的，即具有“要么全部成功，要么全部失败”的特性。
    void addNew(AdminAddNewDTO adminAddNewDTO);

    /**
     * 删除管理员
     *
     * @param id 管理员id
     */
    void delete(Long id);

    /**
     * 启用管理员
     *
     * @param id 管理员id
     */
    void setEnable(Long id);

    /**
     * 禁用管理员
     *
     * @param id 管理员id
     */
    void setDisable(Long id);

    /**
     * 查询管理员数据列表
     *
     * @return 管理员数据列表
     */
    List<AdminListItemVO> list();

}
