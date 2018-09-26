package boardmanagement.api.demo.security.service;

import boardmanagement.api.demo.manage.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static boardmanagement.api.demo.common.constants.AuthType.ADMIN;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    // TODO: パスワードを復号化して取得する仕組みが備わっていないため、修正が必要。
    private static String ENCRYPTED_PASSWORD = "$2a$10$5DF/j5hHnbeHyh85/0Bdzu1HV1KyJKZRt2GhpsfzQ8387A/9duSuq"; // "password"を暗号化した値

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(userService.countByUserName(username)!= 1){
            throw new UsernameNotFoundException(username);
        }

        int authority = userService.findByUserName(username).get().getAuthority();

        if(ADMIN.ordinal() == authority){
            Collection<GrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority("ADMIN"));
            authorityList.add(new SimpleGrantedAuthority("USER"));
            return new User(username, ENCRYPTED_PASSWORD, authorityList);
//            return User.withUsername(username)
//                    .password(ENCRYPTED_PASSWORD)
//                    .authorities(new SimpleGrantedAuthority("ROLE_USER")) // ユーザの権限
////                    .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
//                    .build();
        } else {
            Collection<GrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority("USER"));
            return new User(username, ENCRYPTED_PASSWORD, authorityList);
//            return User.withUsername(username)
//                    .password(ENCRYPTED_PASSWORD)
//                    .authorities(new SimpleGrantedAuthority("ROLE_USER")) // ユーザの権限
//                    .build();
        }


    }
}
