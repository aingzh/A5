package com.yx.controller;

import com.github.pagehelper.PageInfo;
import com.yx.po.Admin;
import com.yx.po.BookInfo;
import com.yx.po.ReaderInfo;
import com.yx.po.TypeInfo;
import com.yx.service.AdminService;
import com.yx.service.ReaderInfoService;
import com.yx.utils.DataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@Api(tags = "读者管理--测试接口请先登录")
public class ReaderInfoController {

    @Autowired
    private ReaderInfoService readerInfoService;

    @Autowired
    private AdminService adminService;

    /**
     * 跳转读者管理页面
     */
    @GetMapping("/readerIndex")
    @ApiOperation(value = "这个方法是用于页面跳转的，返回值是一个jsp", notes = "准备将页面跳转迁移至前端处理，此处可能废置")
    public String readerIndex() {
        return "reader/readerIndex";
    }

    /**
     * 查询所有数据
     */
    /*@RequestMapping("/readerAll")*/

    @ApiOperation(value = "这个方法是用于模糊查询的，参考原前端代码，入参为url携带参数，返回值为工具类DataInfo，见下“model”，此处其data为readerInfo的list",
            notes = "1.目前后端代码支持入参 读者图书卡号+用户名+电话号 三者的任意查询，0/1/2/3参数皆可\n2.实际接口的入参是readerInfo和两个有默认值的分页参数，ssm没学到家，不知道是怎么实现接收处理url参数的")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = false, name = "readerNumber", value = "读者图书卡号"),
            @ApiImplicitParam(paramType = "query", required = false, name = "username", value = "用户名"),
            @ApiImplicitParam(paramType = "query", required = false, name = "tel", value = "电话号")
    })
    @RequestMapping(value = "/readerAll", method = RequestMethod.GET)
    @ResponseBody
    public DataInfo queryReaderAll(ReaderInfo readerInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit) {
        PageInfo<ReaderInfo> pageInfo = readerInfoService.queryAllReaderInfo(readerInfo, pageNum, limit);
        return DataInfo.ok("success", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 添加页面跳转
     */
    @RequestMapping(value = "/readerAdd", method = RequestMethod.GET)
    @ApiOperation(value = "这个方法是用于页面跳转的，返回值是一个jsp", notes = "准备将页面跳转迁移至前端处理，此处可能废置")
    public String readerAdd() {
        return "reader/readerAdd";
    }

    /**
     * 添加页面提交
     */
    @ApiOperation(value = "这个方法是用于添加员工的提交/入库，参考原前端代码，入参为post类请求体携带的json参数，返回值为工具类DataInfo，见下“model”，此处其data为空",
            notes = "1.目前后端代码支持入参为除id（这个数据库设置的自动增长）外的所有ReaderInfo信息\n2.实际接口的入参是readerInfo，ssm没学到家，不知道是怎么实现接收处理json参数的\n3.增添后的刷新请在前端调用readerAll接口")
    /*不需要画蛇添足，这个写法不对，对象式参数直接向下面一样 @RequestBody 即可
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", required = true, name = "readerInfo1", value = "测试", dataType = "com.yx.po.ReaderInfo")
    })*/
    @RequestMapping(value = "/addReaderSubmit", method = RequestMethod.POST)
    @ResponseBody
    public DataInfo addReaderSubmit(@RequestBody ReaderInfo readerInfo) {
        readerInfo.setPassword(readerInfo.getPassword() != null ? readerInfo.getPassword() : "123456");//设置默认密码
        readerInfoService.addReaderInfoSubmit(readerInfo);
        return DataInfo.ok();
    }

    /**
     * 根据id查询数据再跳转到修改页面
     */
    @ApiOperation(value = "这个原方法进行了查询+页面跳转", notes = "不知道如何处理，下面类比readerAll提供了queryReaderInfoById1")
    @GetMapping("/queryReaderInfoById")
    public String queryReaderInfoById(Integer id, Model model) {
        ReaderInfo readerInfo = readerInfoService.queryReaderInfoById(id);
        model.addAttribute("info", readerInfo);
        return "reader/updateReader";
    }
    @ApiOperation(value = "这个方法是用于根据id查询读者，参考原前端代码，入参为url携带参数，返回值为工具类DataInfo，见下“model”，此处其data为readerInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "id", value = "读者id"),
    })
    @RequestMapping(value = "/queryReaderInfoById1", method = RequestMethod.GET)
    @ResponseBody
    public DataInfo queryReaderInfoById1(Integer id) {
        ReaderInfo readerInfo = readerInfoService.queryReaderInfoById(id);
        return DataInfo.ok(readerInfo);
    }

    /**
     * 修改提交
     */
    @ApiOperation(value = "这个方法是用于修改读者的提交/入库，参考原前端代码，入参为post类请求体携带的json参数，返回值为工具类DataInfo，见下“model”，此处其data为空",
            notes = "1.目前后端代码支持入参为除id（这个数据库设置的自动增长）外的所有ReaderInfo信息\n2.实际接口的入参是readerInfo，ssm没学到家，不知道是怎么实现接收处理json参数的\n3.修改后的刷新请在前端调用readerAll接口")
    @RequestMapping(value = "/updateReaderSubmit", method = RequestMethod.POST)
    @ResponseBody
    public DataInfo updateReaderSubmit(@RequestBody ReaderInfo readerInfo) {
        readerInfoService.updateReaderInfoSubmit(readerInfo);
        return DataInfo.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "这个方法是用于根据id删除读者，参考原前端代码，入参为url携带参数，返回值为工具类DataInfo，见下“model”，此处其data为空")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "ids", value = "待删除读者id列表，请以英文逗号分隔"),
    })
    @RequestMapping(value = "/deleteReader", method = RequestMethod.GET)
    @ResponseBody
    public DataInfo deleteReader(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        readerInfoService.deleteReaderInfoByIds(list);
        return DataInfo.ok();
    }

    /**
     * 修改提交（右上角修改密码）
     */
    @ApiOperation(value = "这个方法是用于读者在右上角修改密码的，暂不做处理，dd，有负责密码修改的同学吗？")
    @RequestMapping(value = "/updatePwdSubmit2", method = RequestMethod.POST)
    @ResponseBody
    public DataInfo updatePwdSubmit(HttpServletRequest request, String oldPwd, String newPwd) {
        HttpSession session = request.getSession();
        if (session.getAttribute("type") == "admin") {
            //管理员
            Admin admin = (Admin) session.getAttribute("user");
            Admin admin1 = (Admin) adminService.queryAdminById(admin.getId());
            if (!oldPwd.equals(admin1.getPassword())) {
                return DataInfo.fail("The old password entered is incorrect");
            } else {
                admin1.setPassword(newPwd);
                adminService.updateAdminSubmit(admin1);//数据库修改
            }
        } else {
            //读者
            ReaderInfo readerInfo = (ReaderInfo) session.getAttribute("user");
            ReaderInfo readerInfo1 = readerInfoService.queryReaderInfoById(readerInfo.getId());//根据id查询对象
            if (!oldPwd.equals(readerInfo1.getPassword())) {
                return DataInfo.fail("The old password entered is incorrect");
            } else {
                readerInfo1.setPassword(newPwd);
                readerInfoService.updateReaderInfoSubmit(readerInfo1);//数据库修改
            }
        }
        return DataInfo.ok();
    }
}
