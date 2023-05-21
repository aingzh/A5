package com.yx.controller;

import com.github.pagehelper.PageInfo;
import com.yx.po.BookInfo;
import com.yx.po.TypeInfo;
import com.yx.service.BookInfoService;
import com.yx.service.TypeInfoService;
import com.yx.utils.DataInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@ApiOperation(value="BookInfo接口")
public class BookInfoController {

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private TypeInfoService typeInfoService;

    /**
     * 图书管理首页
     * @return
     */
    @GetMapping("/bookIndex")
    @ApiOperation(value="图书管理首页",httpMethod ="GET")
    public String bookIndex(){
        return "book/bookIndex";
    }

    /**
     * 图书借阅首页
     * @return
     */
    @GetMapping("/bookIndexOfReader")
    @ApiOperation(value="图书借阅首页",httpMethod ="GET")
    public String bookIndexOfReader(){
        return "book/bookIndexOfReader";
    }

    /**
     * 可按条件分页查询book信息，封装成json
     * @param bookInfo
     * @param pageNum
     * @param limit
     * @return
     */
    /*原来的，避免出问题*/
    @RequestMapping("/bookAll")
    @ResponseBody       //@ResponseBody将java对象转为json格式的数据，表示该方法的返回结果直接写入 HTTP response body 中，一般在异步ajax获取数据时使用
    @ApiOperation(value="分页查询",httpMethod ="GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "BookInfo",value = "查询条件",paramType = "body",dataType="BookInfo",required = false),
            @ApiImplicitParam(name = "pageNum",value = "页数",defaultValue = "1",paramType = "query",dataType="Integer",required = false),
            @ApiImplicitParam(name = "limit",value = "每页显示条数",defaultValue = "15",paramType = "query",dataType="Integer",required = false)
    })
    public DataInfo bookAll(BookInfo bookInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit){
        PageInfo<BookInfo> pageInfo = bookInfoService.queryBookInfoAll(bookInfo,pageNum,limit);
        return DataInfo.ok("success",pageInfo.getTotal(),pageInfo.getList());//总条数getTotal，数据封装成list,以便加载分页显示,由于加了ResponseBody,就会返回一个字符串
    }

    /*用来图书管理的*/
    @RequestMapping("/bookAll2")
    @ResponseBody       //@ResponseBody将java对象转为json格式的数据，表示该方法的返回结果直接写入 HTTP response body 中，一般在异步ajax获取数据时使用
    @ApiOperation(value="分页查询",httpMethod ="GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "BookInfo",value = "查询条件",paramType = "body",dataType="BookInfo",required = false),
            @ApiImplicitParam(name = "pageNum",value = "页数",defaultValue = "1",paramType = "query",dataType="Integer",required = false),
            @ApiImplicitParam(name = "limit",value = "每页显示条数",defaultValue = "15",paramType = "query",dataType="Integer",required = false)
    })
    public DataInfo bookAll2(BookInfo bookInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit){
        PageInfo<BookInfo> pageInfo = bookInfoService.queryBookInfoAll2(bookInfo,pageNum,limit);
        return DataInfo.ok("success",pageInfo.getTotal(),pageInfo.getList());//总条数getTotal，数据封装成list,以便加载分页显示,由于加了ResponseBody,就会返回一个字符串
    }

    /*用来借书的*/
    @RequestMapping("/bookAll3")
    @ResponseBody       //@ResponseBody将java对象转为json格式的数据，表示该方法的返回结果直接写入 HTTP response body 中，一般在异步ajax获取数据时使用
    @ApiOperation(value="分页查询",httpMethod ="GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "BookInfo",value = "查询条件",paramType = "body",dataType="BookInfo",required = false),
            @ApiImplicitParam(name = "pageNum",value = "页数",defaultValue = "1",paramType = "query",dataType="Integer",required = false),
            @ApiImplicitParam(name = "limit",value = "每页显示条数",defaultValue = "15",paramType = "query",dataType="Integer",required = false)
    })
    public DataInfo bookAll3(BookInfo bookInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit){
        PageInfo<BookInfo> pageInfo = bookInfoService.queryBookInfoAll3(bookInfo,pageNum,limit);
        return DataInfo.ok("success",pageInfo.getTotal(),pageInfo.getList());//总条数getTotal，数据封装成list,以便加载分页显示,由于加了ResponseBody,就会返回一个字符串
    }

    /**
     * 添加页面的跳转
     */
    @GetMapping("/bookAdd")
    @ApiOperation(value="添加页面的跳转",httpMethod ="GET")
    public String bookAdd(){
        return "book/bookAdd";
    }

    /**
     * 类型添加提交
     */
    @RequestMapping("/addBookSubmit")
    @ResponseBody
    @ApiOperation(value="类型添加提交",httpMethod ="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "BookInfo",value = "更改信息",paramType = "body",dataType="BookInfo",required = false),
    })
    public DataInfo addBookSubmit(BookInfo info){
        bookInfoService.addBookSubmit(info);
        return DataInfo.ok();
    }

    /**
     * 类型根据id查询(修改)
     */
    @GetMapping("/queryBookInfoById")
    @ApiOperation(value="类型根据id查询(修改)",httpMethod ="GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "类型id",dataType="Integer",required = false),
    })
    public String queryTypeInfoById(Integer id, Model model) {
        BookInfo bookInfo = bookInfoService.queryBookInfoById(id);
        model.addAttribute("info", bookInfo);
        return "book/updateBook";
    }

    /**
     * 修改提交功能
     */

    @RequestMapping("/updateBookSubmit")
    @ResponseBody
    @ApiOperation(value="修改提交功能",httpMethod ="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "BookInfo",value = "修改信息",paramType = "body",dataType="BookInfo",required = true),
    })
    public DataInfo updateBookSubmit(@RequestBody BookInfo info){
        bookInfoService.updateBookSubmit(info);
        return DataInfo.ok();
    }
    /**
     * 类型删除
     */

    @RequestMapping("/deleteBook")
    @ResponseBody
    @ApiOperation(value="类型删除",httpMethod ="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value = "类型ids",paramType = "body",dataType="String",required = false),
    })
    public DataInfo deleteBook(String ids){
        List<String> list= Arrays.asList(ids.split(","));
        bookInfoService.deleteBookByIds(list);
        return DataInfo.ok();
    }

    @RequestMapping("/findAllList")
    @ResponseBody
    @ApiOperation(value="显示所有类型",httpMethod ="GET")
    public List<TypeInfo> findAll(){
        PageInfo<TypeInfo> pageInfo = typeInfoService.queryTypeInfoAll(null,1,100);
        List<TypeInfo> lists = pageInfo.getList();
        return lists;
    }
}