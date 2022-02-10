package com.yf.mydemo.infrastructure.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yf.mydemo.infrastructure.resp.RespBody;
import com.yf.mydemo.infrastructure.resp.RespCode;
import lombok.var;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("text/javascript;charset=utf-8");
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        var respBody =RespBody.build(RespCode.UNAUTHORIZED,"认证失败...");
        ObjectMapper mapper = new ObjectMapper();
        httpServletResponse.getWriter().print(mapper.writeValueAsString(respBody));
    }
}
