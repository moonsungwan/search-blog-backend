package com.task.bank.global.security;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.task.bank.global.message.MessageCode;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=utf-8");
        
        HashMap<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code", "9999");
        resultMap.put("message", MessageCode.INVALID_AUTH_TOKEN.getMessage());
        resultMap.put("errorCode", HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject resultJson = new JSONObject(resultMap);
        
        PrintWriter printWriter = response.getWriter();
        printWriter.print(resultJson);
    }
    
}