package com.demo.myannotation;

import com.demo.tools.DESUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;



@ControllerAdvice
public class EncryptResponseAdvice implements ResponseBodyAdvice {

    private final static Logger logger = LoggerFactory.getLogger(EncryptResponseAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        logger.info("now" + methodParameter.getMethod());
        if (methodParameter.getMethod().isAnnotationPresent(EncryptResponse.class)){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                logger.info("加密数据为: {}", objectMapper.writeValueAsString(o));
                String string = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
                return DESUtil.encrypt(string);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
