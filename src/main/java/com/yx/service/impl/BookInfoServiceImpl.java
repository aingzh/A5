package com.yx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yx.dao.BookInfoMapper;
import com.yx.po.BookInfo;
import com.yx.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("bookInfoService")
public class BookInfoServiceImpl implements BookInfoService {

    @Autowired
    private BookInfoMapper bookInfoMapper;

    /*原来的，避免出问题*/
    @Override
    public PageInfo<BookInfo> queryBookInfoAll(BookInfo bookInfo, Integer pageNum, Integer limit) {
        PageHelper.startPage(pageNum, limit);
        List<BookInfo> bookInfoList = bookInfoMapper.queryBookInfoAll(bookInfo);
        Collections.reverse(bookInfoList); //逆序一下，这样新加的在上面
        return new PageInfo<>(bookInfoList);
    }
    /*用来图书管理的*/
    @Override
    public PageInfo<BookInfo> queryBookInfoAll2(BookInfo bookInfo, Integer pageNum, Integer limit) {
        PageHelper.startPage(pageNum, limit);
        List<BookInfo> bookInfoList = bookInfoMapper.queryBookInfoAll2(bookInfo);
        Collections.reverse(bookInfoList); //逆序一下，这样新加的在上面
        /*不会联表的我只能使用笨方法了*/
        for(BookInfo i : bookInfoList){
            i.setBorrowCounts(bookInfoMapper.queryBorrowCountsByIsbnInt(i.getIsbn()));
        }
        return new PageInfo<>(bookInfoList);
    }
    /*用来借书的*/
    @Override
    public PageInfo<BookInfo> queryBookInfoAll3(BookInfo bookInfo, Integer pageNum, Integer limit) {
        PageHelper.startPage(pageNum, limit);
        List<BookInfo> bookInfoList = bookInfoMapper.queryBookInfoAll(bookInfo);
        bookInfoList.removeIf(a -> a.getStatus() == 1); //去掉在借的书
        return new PageInfo<>(bookInfoList);
    }

    /*笨方法批量图书入库*/
    @Override
    public void addBookSubmit(BookInfo bookInfo) {
        //bookInfoMapper.insert(bookInfo);
        int maxBookId = bookInfoMapper.queryMaxBookId();
        for(int i = bookInfo.getCounts(); i > 0; i--){
            bookInfo.setId(++maxBookId);
            bookInfoMapper.insertSelective(bookInfo);
        }
    }

    @Override
    public BookInfo queryBookInfoById(Integer id) {
        return bookInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateBookSubmit(BookInfo info) {
        bookInfoMapper.updateByPrimaryKeySelective(info);
    }

    @Override
    public void deleteBookByIds(List<String> ids) {
        for (String id : ids) {
            bookInfoMapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
    }

    @Override
    public List<BookInfo> getBookCountByType() {
        return bookInfoMapper.getBookCountByType();
    }
}
