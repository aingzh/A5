package com.yx.service;

import com.github.pagehelper.PageInfo;
import com.yx.po.LendList;

import java.util.List;

public interface LendListService {

    //分页查询
    PageInfo<LendList> queryLendListAll(LendList lendList, int page, int limit);

    //添加借阅记录
    void addLendListSubmit(LendList lendList);


    /**
     * 删除
     */
    void deleteLendListById(List<String> ids, List<String> bookIds);

    /**
     * 还书
     */
    void updateLendListSubmit(List<String> ids, List<String> bookIds);

    /**
     * 异常还书
     */
    void backBook(LendList lendList);

    /**
     * 时间线查询
     */
    List<LendList> queryLookBookList(Integer rid, Integer bid);

    /**
     * 统计将超时或已超时的借书记录
     * @param rid
     * @return
     */
    Integer countWillExpireLend(Integer rid);

    /**
     * 查询超期图书
     */
    List<LendList> queryOverdueList();

    /**
     * 查询读者借阅图书
     */
    List<LendList> queryListByReader(Integer rid);

}
