package com.chulai.service.Impl;

import com.alibaba.fastjson.JSON;
import com.chulai.domain.BaseResult;
import com.chulai.domain.User;
import com.chulai.repository.UserRepository;
import com.chulai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户的实现层
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public String editUser(User user) {
        BaseResult result=new BaseResult();
        result.setData(user);
        result.success();
        return JSON.toJSONString(result);
    }
}
