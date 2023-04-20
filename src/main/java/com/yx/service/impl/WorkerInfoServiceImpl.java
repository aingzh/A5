package com.yx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yx.dao.WorkerInfoMapper;
import com.yx.po.WorkerInfo;
import com.yx.service.WorkerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("WorkerInfoService")
public class WorkerInfoServiceImpl implements WorkerInfoService{

    @Autowired
    private WorkerInfoMapper workerInfoMapper;

    @Override
    public PageInfo<WorkerInfo> queryAllWorkerInfo(WorkerInfo workerInfo, Integer pageNum, Integer limit) {
        PageHelper.startPage(pageNum,limit);
        List<WorkerInfo> workerInfoList =  workerInfoMapper.queryAllWorkerInfo(workerInfo);
        return new PageInfo<>(workerInfoList);
    }

    @Override
    public void addWorkerInfoSubmit(WorkerInfo workerInfo) {
        workerInfoMapper.insert(workerInfo);
    }

    @Override
    public WorkerInfo queryWorkerInfoById(Integer id) {
        return workerInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateWorkerInfoSubmit(WorkerInfo workerInfo) {
        workerInfoMapper.updateByPrimaryKey(workerInfo);
    }

    @Override
    public void deleteWorkerInfoByIds(List<String> ids) {
        for (String id : ids){
            workerInfoMapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }

    @Override
    public WorkerInfo queryUserInfoByNameAndPassword(String username, String password) {
        return workerInfoMapper.queryUserInfoByNameAndPassword(username, password);
    }

    @Override
    public WorkerInfo queryUserInfoByWorkerNumberAndPassword(String workerNumber, String password) {
        return workerInfoMapper.queryUserInfoByWorkerNumberAndPassword(workerNumber, password);
    }
}
