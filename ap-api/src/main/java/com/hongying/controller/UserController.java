package com.hongying.controller;

import com.hongying.service.request.LoginRequest;
import com.hongying.response.BaseResponse;
import com.hongying.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "ap/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping(value = "/login")
    public BaseResponse<Boolean> queryProperty(@RequestBody LoginRequest loginRequest) {
        userService.login(loginRequest);
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
