package boardmanagement.api.demo.security.config;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static boardmanagement.api.demo.security.config.SecurityConstants.HEADER_STRING;
import static boardmanagement.api.demo.security.config.SecurityConstants.TOKEN_PREFIX;

/**
 * 認可フィルタ.
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    /**
     * 暗号鍵.
     */
    private String secretKey;

    private AuthenticationManager authenticationManager;

    public JWTAuthorizationFilter(
            AuthenticationManager authenticationManager, String secretKey) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        // AuthorizationヘッダのBearer Prefixである場合
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);




        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String[] subjects = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject().split(",");

            if(subjects.length < 2) return null;

            String principal = subjects[0];

            ArrayList<SimpleGrantedAuthority> permitList = new ArrayList<>();
            for(int i = 1; i<subjects.length; i++){
                permitList.add(new SimpleGrantedAuthority(subjects[i]));
            }

            return new UsernamePasswordAuthenticationToken(principal, null, permitList);
        }
        return null;
    }
}