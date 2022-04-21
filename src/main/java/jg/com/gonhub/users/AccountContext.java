package jg.com.gonhub.users;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
@Getter
public class AccountContext extends User {
    private final jg.com.gonhub.users.User user;

    public AccountContext(jg.com.gonhub.users.User user ,Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }

}
