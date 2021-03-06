package boardmanagement.api.demo.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static boardmanagement.api.demo.security.config.SecurityConstants.*;

/**
 * 認証フィルタ.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 暗号鍵
     */
    private static String secretKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private MappingJackson2HttpMessageConverter httpMessageConverter;

    public JWTAuthenticationFilter(
            AuthenticationManager authenticationManager,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter,
            String secretKey
    ) {
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.httpMessageConverter = mappingJackson2HttpMessageConverter;
        this.secretKey = secretKey;

        // ログイン用のpathを変更する
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(LOGIN_URL, "POST"));

        // ログイン用のID/PWのパラメータ名を変更する
        setUsernameParameter(LOGIN_ID);
        setPasswordParameter(PASSWORD);

    }

    // 認証の処理
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            // requestパラメータからユーザ情報を読み取る
            UserForm userForm = new ObjectMapper().readValue(req.getInputStream(), UserForm.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userForm.getLoginId(),
                            userForm.getPass(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    // 認証に成功した場合の処理
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        // ログイン情報と権限情報を取得してトークンに格納する.
        StringBuffer authorities = new StringBuffer();
        List<String> authList = new ArrayList<>();
        auth.getAuthorities().forEach(a -> {
            authorities.append(",");
            authorities.append(a.toString());
            authList.add(a.toString());
        });

        // TODO: リクエストヘッダーにトークンを埋め込まない場合、この処理は不要のため削除すること.
        Date expire = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        String token = Jwts.builder()
                .setSubject(((User)auth.getPrincipal()).getUsername() + authorities.toString())
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        res.addHeader(HEADER_EXPIRE, expire.toString());


        HttpOutputMessage outputMessage = new ServletServerHttpResponse(res);
        httpMessageConverter.write(new AuthenticationResult(TOKEN_PREFIX + token, authList), MediaType.APPLICATION_JSON_UTF8, outputMessage);

        // ここでレスポンスを組み立てると個別のパラメータを返せるがFilterの責務の範囲内で実施しなければならない
        // auth.getPrincipal()で取得できるUserDetailsは自分で作ったEntityクラスにもできるのでカスタム属性は追加可能
    }

    @lombok.Value
    public static class AuthenticationResult {
        private final String token;
        private final List<String> authList;
    }



}