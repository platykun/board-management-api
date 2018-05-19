package boardmanagement.api.demo.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static List<String> usernameList = Arrays.asList("user", "admin");
    private static String ENCRYPTED_PASSWORD = "$2a$10$5DF/j5hHnbeHyh85/0Bdzu1HV1KyJKZRt2GhpsfzQ8387A/9duSuq"; // "password"を暗号化した値

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 本来ならここでDBなどからユーザを検索することになるが、サンプルのためリストに含まれるかで判定している
        if(!usernameList.contains(username)){
            throw new UsernameNotFoundException(username);
        }

        if("admin".equals(username)){
            Collection<GrantedAuthority> authorityList = new ArrayList<>();
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
