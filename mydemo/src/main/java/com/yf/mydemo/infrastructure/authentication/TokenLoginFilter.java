package com.yf.mydemo.infrastructure.authentication;

import com.yf.mydemo.infrastructure.resp.RespBody;
import com.yf.mydemo.infrastructure.resp.ResponseUtil;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    public TokenLoginFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Map<String, String[]> map = request.getParameterMap();
            String username = map.get("username")[0];
            String password = map.get("password")[0];
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserDetails user = (UserDetails)authResult.getPrincipal();
        var roles = authResult.getAuthorities().stream().map(o->o.getAuthority()).toArray(String[]::new);
        var roleStr = String.join(",",roles);
        String token = JwtTokenUtil.createToken(user.getUsername(),roleStr);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("user",user);
        map.put("loginName",user.getUsername());
        ResponseUtil.out(response, RespBody.ok(map));
    }
}
