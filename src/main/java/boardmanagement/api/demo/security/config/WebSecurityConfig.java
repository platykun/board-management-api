package boardmanagement.api.demo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static boardmanagement.api.demo.security.config.SecurityConstants.LOGIN_URL;
import static boardmanagement.api.demo.security.config.SecurityConstants.SIGNUP_URL;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), bCryptPasswordEncoder(), mappingJackson2HttpMessageConverter))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .authorizeRequests()
                .mvcMatchers("/user/**")
                .hasAnyAuthority("USER")
                .mvcMatchers("/admin/**")
                .hasAnyAuthority("ADMIN")
                .antMatchers("/**", SIGNUP_URL, LOGIN_URL).permitAll()
                .anyRequest().authenticated()
                .and().logout()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // xssProtectionを設定しない.セキュリティ強度を弱める設定のため、設定せずに済むのであれば設定しない
//         http.headers().xssProtection().block(false);

        // TODO: 思考停止で設定を入れる。要否は要検討
        // https://stackoverflow.com/questions/36968963/how-to-configure-cors-in-a-spring-boot-spring-security-application/37610988#37610988
//        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        http.cors().configurationSource(corsConfigurationSource());

    }


    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * CORS設定
     *
     * @return CORS設定
     */
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.addAllowedOrigin("http://localhost:8080");
        corsConfiguration.addAllowedOrigin("https://condescending-montalcini-4af215.netlify.com");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsConfigurationSource;
    }

}