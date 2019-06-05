package com.hongying.controller;

import com.hongying.CookieStore;
import com.hongying.enums.ErrorCodeEnum;
import com.hongying.response.BaseResponse;
import com.hongying.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.hongying.service.request.LoginRequest;

@RestController
@RequestMapping(value = "ap/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CookieStore cookieStore;

    @ResponseBody
    @PostMapping(value = "/login")
    public BaseResponse<Boolean> queryProperty(@RequestBody LoginRequest loginRequest) {
        if(StringUtils.isEmpty(loginRequest.getName()) || StringUtils.isEmpty(loginRequest.getPassword())){
            return BaseResponse.buildFailedResponse(ErrorCodeEnum.PARAM_ERROR);
        }
        Long userId = userService.login(loginRequest);
        cookieStore.writeCookie(userId);
        return BaseResponse.buildSuccessResponse(true);
    }

    @ResponseBody
    @GetMapping(value = "/create")
    public BaseResponse<Boolean> createTestUser(@RequestParam("pwd") String pwd){
        if(!pwd.equals("hongying.duan")){
            return BaseResponse.buildFailedResponse("hello word");
        }
        return BaseResponse.buildSuccessResponse(userService.initData());
    }
}
