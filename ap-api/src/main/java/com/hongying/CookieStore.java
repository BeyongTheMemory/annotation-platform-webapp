package com.hongying;

import com.hongying.enums.ErrorCodeEnum;
import com.hongying.exceptions.ApException;
import com.hongying.utils.MD5;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CookieStore {
    private final String COOKIE_NAME = "user";
    private Map<String, Long> userCookie = new ConcurrentHashMap<>();

    public void writeCookie(Long userId) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        String cookieValue = MD5.MD5Encode(System.currentTimeMillis() + "" + userId);
        Cookie cookie = new Cookie(COOKIE_NAME, cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(360000);
        response.addCookie(cookie);
        userCookie.put(cookieValue,userId);
    }

    public Long getUserId(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(COOKIE_NAME)){
                    Long userId = userCookie.get(cookie.getValue());
                    if(userId == null || userId < 1){
                        throw new ApException(ErrorCodeEnum.LOGIN_ERROR);
                    }
                    return userId;
                }
            }
        }
        throw new ApException(ErrorCodeEnum.LOGIN_ERROR);
    }
}
