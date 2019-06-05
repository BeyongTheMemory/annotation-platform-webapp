package com.hongying.service;
import com.hongying.service.request.LoginRequest;


public interface UserService {
    Long login(LoginRequest loginRequest);

    Boolean initData();
}
