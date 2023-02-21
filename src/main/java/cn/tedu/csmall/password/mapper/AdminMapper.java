package cn.tedu.csmall.password.mapper;

import cn.tedu.csmall.password.pojo.entity.Admin;
import cn.tedu.csmall.password.pojo.vo.AdminListItemVO;
import cn.tedu.csmall.password.pojo.vo.AdminLoginInfoVO;
import cn.tedu.csmall.password.pojo.vo.AdminStandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理管理员数据的Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface AdminMapper {

    /**
     * 插入管理员数据
     *
     * @param admin 管理员数据
     * @return 受影响的行数
     */
    int insert(Admin admin);

    /**
     * 批量插入管理员数据
     *
     * @param adminList 若干个管理员数据的集合
     * @return 受影响的行数
     */
    int insertBeach(List<Admin> adminList);

    /**
     * 根据管理员id删除管理员数据
     *
     * @param id 管理员id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据管理员id批量删除管理员数据
     *
     * @param ids 批量管理员id
     * @return 受影响的行数
     */
    int deleteByIds(Long[] ids);

    /**
     * 根据管理员id修改管理员的数据
     *
     * @param admin 封装了管理员id和新的数据的对象
     * @return 受影响的行数
     */
    int update(Admin admin);

    /**
     * 统计管理员数据的数量
     *
     * @return 管理员数据数量
     */
    int count();

    /**
     * 根据用户名统计管理员数据的数量
     *
     * @param username 用户名
     * @return 匹配用户名的管理员数据的数据
     */
    int countByUsername(String username);

    /**
     * 根据电话统计管理员数据的数量
     *
     * @param phone 电话号码
     * @return 匹配电话的管理员数据的数据
     */
    int contByPhone(String phone);

    /**
     * 根据email统计管理员数据的数量
     *
     * @param email email
     * @return 匹配email的管理员数据的数据
     */
    int countByEmail(String email);

    /**
     * 查询管理员数据列表
     *
     * @return 管理员数据列表
     */
    List<AdminListItemVO> list();

    /**
     * 根据管理员用户名查询管理登录信息
     *
     * @param username 用户名
     * @return 匹配的登录信息，如果没有匹配的数据，则返回null
     */
    AdminLoginInfoVO getLoginInfoByUsername(String username);

    /**
     * 根据管理员id查询管理员数据详情
     *
     * @param id 管理员id
     * @return 匹配的管理员数据详情，如果没有匹配的数据，则返回null
     */
    AdminStandardVO getStandById(Long id);
}
