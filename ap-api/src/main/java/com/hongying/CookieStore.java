package com.hongying;

import com.hongying.utils.MD5;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CookieStore {
    private Map<String, Long> userCookie = new ConcurrentHashMap<>();

    public void writeCookie(Long userId) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        String cookieValue = MD5.MD5Encode(System.currentTimeMillis() + "" + userId);
        Cookie cookie = new Cookie("user", cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(360000);
        response.addCookie(cookie);
        userCookie.put(cookieValue,userId);
    }
}
