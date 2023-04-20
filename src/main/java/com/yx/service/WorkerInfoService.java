package com.yx.service;

import com.github.pagehelper.PageInfo;
import com.yx.po.WorkerInfo;

import java.util.List;

public interface WorkerInfoService {

    /**
     * 查询所有记录
     */
    PageInfo<WorkerInfo> queryAllWorkerInfo(WorkerInfo workerInfo, Integer pageNum, Integer limit);

    /**
     * 添加
     */
    void addWorkerInfoSubmit(WorkerInfo workerInfo);

    /**
     * 查询（修改前先查询）
     */
    WorkerInfo queryWorkerInfoById(Integer id);

    /**
     * 修改提交
     */
    void updateWorkerInfoSubmit(WorkerInfo workerInfo);

    /**
     * 删除
     */
    void deleteWorkerInfoByIds(List<String> ids);


    /**
     * 根据用户名和密码查询用户信息
     */
    WorkerInfo queryUserInfoByNameAndPassword(String username, String password);

    /**
     * 根据工号和密码查询用户信息
     */
    WorkerInfo queryUserInfoByWorkerNumberAndPassword(String WorkerNumber, String password);
}
