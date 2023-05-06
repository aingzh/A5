package com.yx.controller;

import com.github.pagehelper.PageInfo;
import com.yx.po.ReaderInfo;
import com.yx.po.TypeInfo;
import com.yx.service.TypeInfoService;
import com.yx.utils.DataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller //声明后端控制器
@Api(tags = "类型管理--测试接口请先登录--根据读者cv，如未改干净请发挥你的聪明才智")
public class TypeInfoController {

    @Autowired
    private TypeInfoService typeInfoService;

    /**
     * 类型管理首页
     *
     * @return
     */
    @ApiOperation(value = "这个方法是用于页面跳转的，返回值是一个jsp", notes = "准备将页面跳转迁移至前端处理，此处可能废置")
    @GetMapping("/typeIndex")
    public String typeIndex() {
        return "type/typeIndex";
    }

    /**
     * 获取type数据信息，分页
     */
    @ApiOperation(value = "这个方法是用于模糊查询的，参考原前端代码，入参为url携带参数，返回值为工具类DataInfo，见下“model”，此处其data为typeInfo的list",
            notes = "1.目前后端代码支持入参 图书名 一者的任意查询，0/1参数皆可\n2.实际接口的入参是typeInfo和两个有默认值的分页参数，ssm没学到家，不知道是怎么实现接收处理url参数的")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = false, name = "name", value = "图书名")
    })
    @RequestMapping(value = "/typeAll", method = RequestMethod.GET)
    @ResponseBody       //@ResponseBody将java对象转为json格式的数据，表示该方法的返回结果直接写入 HTTP response body 中，一般在异步ajax获取数据时使用
    public DataInfo typeAll(String name, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit) {
        PageInfo<TypeInfo> pageInfo = typeInfoService.queryTypeInfoAll(name, pageNum, limit);
        return DataInfo.ok("success", pageInfo.getTotal(), pageInfo.getList());//总条数getTotal，数据封装成list,以便加载分页显示,由于加了ResponseBody,就会返回一个字符串
    }

    /**
     * 添加页面的跳转
     */
    @GetMapping("/typeAdd")
    @ApiOperation(value = "这个方法是用于页面跳转的，返回值是一个jsp", notes = "准备将页面跳转迁移至前端处理，此处可能废置")
    public String typeAdd() {
        return "type/typeAdd";
    }

    /**
     * 类型添加提交
     */
    @ApiOperation(value = "这个方法是用于添加员工的提交/入库，参考原前端代码，入参为post类请求体携带的json参数，返回值为工具类DataInfo，见下“model”，此处其data为空",
            notes = "1.目前后端代码支持入参为除id（这个数据库设置的自动增长）外的所有typeInfo信息\n2.实际接口的入参是typeInfo，ssm没学到家，不知道是怎么实现接收处理json参数的\n3.增添后的刷新请在前端调用typeAll接口")
    @PostMapping("/addTypeSubmit")
    @ResponseBody
    public DataInfo addTypeSubmit(@RequestBody TypeInfo info) {
        typeInfoService.addTypeSubmit(info);
        return DataInfo.ok();
    }

    /**
     * 类型根据id查询(修改)
     */
    @ApiOperation(value = "这个原方法进行了查询+页面跳转", notes = "不知道如何处理，下面类比readerAll提供了queryTypeInfoById1")
    @GetMapping("/queryTypeInfoById")
    public String queryTypeInfoById(Integer id, Model model) {
        TypeInfo info = typeInfoService.queryTypeInfoById(id);
        model.addAttribute("info", info);
        return "type/updateType";
    }
    @ApiOperation(value = "这个方法是用于根据id查询读者，参考原前端代码，入参为url携带参数，返回值为工具类DataInfo，见下“model”，此处其data为readerInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "id", value = "类型id"),
    })
    @RequestMapping(value = "/queryTypeInfoById1", method = RequestMethod.GET)
    @ResponseBody
    public DataInfo queryTypeInfoById1(Integer id) {
        TypeInfo typeInfo = typeInfoService.queryTypeInfoById(id);
        return DataInfo.ok(typeInfo);
    }

    /**
     * 修改提交功能
     */
    @ApiOperation(value = "这个方法是用于修改类型的提交/入库，参考原前端代码，入参为post类请求体携带的json参数，返回值为工具类DataInfo，见下“model”，此处其data为空",
            notes = "1.目前后端代码支持入参为除id（这个数据库设置的自动增长）外的所有typeInfo信息\n2.实际接口的入参是typeInfo，ssm没学到家，不知道是怎么实现接收处理json参数的\n3.修改后的刷新请在前端调用typeAll接口")
    @RequestMapping(value = "/updateTypeSubmit", method = RequestMethod.POST)
    @ResponseBody
    public DataInfo updateTypeSubmit(@RequestBody TypeInfo info) {
        typeInfoService.updateTypeSubmit(info);
        return DataInfo.ok();
    }

    /**
     * 类型删除
     */
    @ApiOperation(value = "这个方法是用于根据id删除类型，参考原前端代码，入参为url携带参数，返回值为工具类DataInfo，见下“model”，此处其data为空")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "ids", value = "待删除类型id列表，请以英文逗号分隔"),
    })
    @RequestMapping(value = "/deleteType", method = RequestMethod.GET)
    @ResponseBody
    public DataInfo deleteType(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        typeInfoService.deleteTypeByIds(list);
        return DataInfo.ok();
    }
}
