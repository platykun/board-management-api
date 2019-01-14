package boardmanagement.api.demo.security.service;

import boardmanagement.api.demo.common.bean.entity.UserEntityBean;
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
    LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(loginService.countById(username)!= 1){
            throw new UsernameNotFoundException(username);
        }

        UserEntityBean loginUser = loginService.findByUserId(username).get();
        String loginUserPassword= loginService.findPasswordByUserId(username).getPassword();
        int authority = loginUser.getAuthority();

        if(ADMIN.ordinal() == authority){
            Collection<GrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority("ADMIN"));
            authorityList.add(new SimpleGrantedAuthority("USER"));
            return new User(username, loginUserPassword, authorityList);
//            return User.withUsername(username)
//                    .password(ENCRYPTED_PASSWORD)
//                    .authorities(new SimpleGrantedAuthority("ROLE_USER")) // ユーザの権限
////                    .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
//                    .build();
        } else {
            Collection<GrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority("USER"));
            return new User(username, loginUserPassword, authorityList);
//            return User.withUsername(username)
//                    .password(ENCRYPTED_PASSWORD)
//                    .authorities(new SimpleGrantedAuthority("ROLE_USER")) // ユーザの権限
//                    .build();
        }


    }
}
