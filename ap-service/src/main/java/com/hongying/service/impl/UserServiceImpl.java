package com.hongying.service.impl;

import com.hongying.exceptions.ApException;
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
        User user = userDAO.selectByName(loginRequest.getName());
        if(user == null){
            throw new ApException("user not exist");
        }
        if(!user.getPassword().equals(loginRequest.getPassword())){
            throw new ApException("invalid password");
        }
        return user.getId();
    }

    @Override
    public Boolean initData() {
        User record = new User();
        for(int i = 2;i<100;i++){
            record.setName(i+"");
            record.setPassword(i+"");
            userDAO.insertSelective(record);
        }
        return true;
    }
}
