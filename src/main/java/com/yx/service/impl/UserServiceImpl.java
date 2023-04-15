package com.yx.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yx.dao.UserMapper;
import com.yx.po.User;
import com.yx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
@Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> queryUserAll(User user, Integer pageNum, Integer limit) {
        PageHelper.startPage(pageNum,limit);
        List<User> userList = userMapper.queryUserInfoAll(user);
        return new PageInfo<>(userList) ;
    }

    @Override
    public void addUserSubmit(User user) {
        userMapper.insert(user);
    }

    @Override
    public User queryUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateUserSubmit(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteUserByIds(List<String> ids) {
        for (String id : ids){
            userMapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }

    @Override
    public User queryUserByNameAndPassword(String username, String password) {
        return userMapper.queryUserByNameAndPassword(username,password);
    }
}


