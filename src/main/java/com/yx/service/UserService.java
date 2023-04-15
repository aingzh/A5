package com.yx.service;

import com.github.pagehelper.PageInfo;
import com.yx.po.User;

import java.util.List;
public interface UserService {
/**
     * 查询所有用户（分页）
     */
    PageInfo<User> queryUserAll(User user, Integer pageNum, Integer limit);

    /**
     * 添加提交
     */
    void addUserSubmit(User user);

    /**
     * 根据id查询（修改）
     */
    User queryUserById(Integer id);

    /**
     * 修改提交
     */
    void updateUserSubmit(User user);

    /**
     * 删除
     */
    void deleteUserByIds(List<String> ids);

    /**
     * 根据用户名和密码查询用户信息
     */
    User queryUserByNameAndPassword(String username,String password);
}
