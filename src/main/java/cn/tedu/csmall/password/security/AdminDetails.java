package cn.tedu.csmall.password.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode
@ToString(callSuper = true)
public class AdminDetails extends User {


    /**
     * 管理员ID
     */
    private Long id;

    public AdminDetails(Long id, String username, String password,
                        boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled,
                true, true, true, authorities);
        this.id = id;
    }
}
