package com.yx.service;

import com.yx.po.WorkerInfo;

public interface WorkerInfoService {
    /**
     * 根据用户名和密码查询用户信息
     */
    WorkerInfo queryUserInfoByNameAndPassword(String username, String password);

    /**
     * 根据工号和密码查询用户信息
     */
    WorkerInfo queryUserInfoByWorkerNumberAndPassword(String WorkerNumber, String password);
}
