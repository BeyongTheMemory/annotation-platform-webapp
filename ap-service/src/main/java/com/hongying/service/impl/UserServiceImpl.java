package com.hongying.service.impl;

import com.hongying.repository.domain.User;
import com.hongying.repository.mapper.UserDAO;
import com.hongying.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongying.service.request.LoginRequest;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public Long login(LoginRequest loginRequest) {
        return 1L;
    }

    @Override
    public Boolean initData() {
        User record = new User();
        record.setName("1");
        record.setPassword("1");
        userDAO.insertSelective(record);
        return true;
    }
}
