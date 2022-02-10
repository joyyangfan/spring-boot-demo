package com.yf.mydemo.interfaces;

import com.yf.mydemo.infrastructure.resp.RespBody;
import com.yf.mydemo.infrastructure.resp.RespCode;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class TokenErrorController extends BasicErrorController {

    public TokenErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,ErrorAttributeOptions.of(ErrorAttributeOptions.Include.EXCEPTION,ErrorAttributeOptions.Include.MESSAGE,ErrorAttributeOptions.Include.STACK_TRACE, ErrorAttributeOptions.Include.BINDING_ERRORS));
        HttpStatus status = getStatus(request);
        return new ResponseEntity(RespBody.fail("验证失败"), status);
    }

}
