package cn.tedu.csmall.password.security;

import cn.tedu.csmall.password.mapper.AdminMapper;
import cn.tedu.csmall.password.pojo.vo.AdminLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("Spring Security调用了loadUserByUsername()方法，参数：{}", s);


        AdminLoginInfoVO loginInfoVO = adminMapper.getLoginInfoByUsername(s);
        log.debug("从数据库中查询用户名【{}】匹配的信息，结果：{}", s, loginInfoVO);

        if (loginInfoVO == null) {
            return null; // 暂时
        }

        // 创建权限列表
        // AdminDetails的构造方法要求是Collection<? extends GrantedAuthority>类型的
        // 在Mapper查询结果中的权限是List<String>类型的，所以需要遍历再创建得到所需的权限列表
        List<String> permissions = loginInfoVO.getPermissions();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
            authorities.add(authority);
        }

        // 创建AdminDetails类型的对象
        // 此类型是基于User类型扩展的，可以有自定义属性，例如id
        AdminDetails adminDetails = new AdminDetails(
                loginInfoVO.getId(), loginInfoVO.getUsername(),
                loginInfoVO.getPassword(), loginInfoVO.getEnable() == 1,
                authorities
        );

        //        UserDetails userDetails = User.builder()
        //                .username(loginInfoVO.getUsername())
        //                .password(loginInfoVO.getPassword())
        //                .disabled(loginInfoVO.getEnable() == 0)
        //                .accountLocked(false) // 账号是否已锁定
        //                .accountExpired(false) // 账号是否过期
        //                .credentialsExpired(false) // 凭证是否过期
        //                .authorities(loginInfoVO.getPermissions().toArray(new String[]{})) // 权限
        //                .build();
        log.debug("即将向Spring Security返回UserDetails对象：{}", adminDetails);
        return adminDetails;
        //        // 假设可用的用户名/密码是 root/123456
        //        if ("root".equals(s)) {
        //            UserDetails userDetails = User.builder()
        //                    .username("root")
        //                    .password("$2a$10$g7d7g8/W38S/KKhBscPXW.C52f3yy2edGRFv0X4mkeEXEKQ6cszb2")
        //                    .disabled(false) // 账号是否禁用
        //                    .accountLocked(false) // 账号是否已锁定
        //                    .accountExpired(false) // 账号是否过期
        //                    .credentialsExpired(false) // 凭证是否过期
        //                    .authorities("这是一个山寨的临时权限，也不知道有什么用") // 权限
        //                    .build();
        //            return userDetails;
        //        }
        //        // 如果用户名不存在，暂时返回null
        //        return null;
    }
}
