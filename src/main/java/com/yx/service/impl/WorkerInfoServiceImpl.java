package com.yx.service.impl;

import com.yx.dao.WorkerInfoMapper;
import com.yx.po.WorkerInfo;
import com.yx.service.WorkerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("WorkerInfoService")
public class WorkerInfoServiceImpl implements WorkerInfoService{

    @Autowired
    private WorkerInfoMapper workerInfoMapper;

    @Override
    public WorkerInfo queryUserInfoByNameAndPassword(String username, String password) {
        return workerInfoMapper.queryUserInfoByNameAndPassword(username, password);
    }

    @Override
    public WorkerInfo queryUserInfoByWorkerNumberAndPassword(String workerNumber, String password) {
        return workerInfoMapper.queryUserInfoByWorkerNumberAndPassword(workerNumber, password);
    }
}
