package com.yx.controller;

import com.github.pagehelper.PageInfo;
import com.yx.po.Admin;
import com.yx.po.ReaderInfo;
import com.yx.service.AdminService;
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

@Controller
@Api(tags = "管理员管理--测试接口请先登录--根据读者cv，如未改干净请发挥你的聪明才智")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/adminIndex")
    @ApiOperation(value = "这个方法是用于页面跳转的，返回值是一个jsp", notes = "准备将页面跳转迁移至前端处理，此处可能废置")
    public String adminIndex() {
        return "admin/adminIndex";
    }

    @ApiOperation(value = "这个方法是用于模糊查询的，参考原前端代码，入参为url携带参数，返回值为工具类DataInfo，见下“model”，此处其data为adminInfo的list",
            notes = "1.目前后端代码支持入参 用户名+管理权限 两者的任意查询，0/1/2参数皆可\n2.实际接口的入参是admin和两个有默认值的分页参数，ssm没学到家，不知道是怎么实现接收处理url参数的")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = false, name = "username", value = "用户名"),
            @ApiImplicitParam(paramType = "query", required = false, name = "adminType", value = "管理权限，0代表普通管理员，1代表超级管理员")
    })
    @RequestMapping(value = "/adminAll", method = RequestMethod.GET)
    @ResponseBody
    public DataInfo queryAdminAll(Admin admin, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit) {
        PageInfo<Admin> pageInfo = adminService.queryAdminAll(admin, pageNum, limit);
        return DataInfo.ok("success", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 添加页面的跳转
     *
     * @return
     */
    @GetMapping("/adminAdd")
    @ApiOperation(value = "这个方法是用于页面跳转的，返回值是一个jsp", notes = "准备将页面跳转迁移至前端处理，此处可能废置")
    public String adminAdd() {
        return "admin/adminAdd";
    }

    /**
     * 添加提交
     *
     * @param admin
     * @return
     */
    @ApiOperation(value = "这个方法是用于添加管理员的提交/入库，参考原前端代码，入参为post类请求体携带的json参数，返回值为工具类DataInfo，见下“model”，此处其data为空",
            notes = "1.目前后端代码支持入参为除id（这个数据库设置的自动增长）外的所有ReaderInfo信息\n2.实际接口的入参是admin，ssm没学到家，不知道是怎么实现接收处理json参数的\n3.增添后的刷新请在前端调用readerAll接口")
    @RequestMapping(value = "/addAdminSubmit", method = RequestMethod.POST)
    @ResponseBody
    public DataInfo addBookSubmit(@RequestBody Admin admin) {
        adminService.addAdminSubmit(admin);
        return DataInfo.ok();
    }

    /**
     * 根据id查询
     */
    @ApiOperation(value = "这个原方法进行了查询+页面跳转", notes = "不知道如何处理，下面类比adminAll提供了queryAdminById1")
    @GetMapping("/queryAdminById")
    public String queryAdminById(Integer id, Model model) {
        model.addAttribute("id", id);
        return "admin/updateAdmin";
    }
    @ApiOperation(value = "这个方法是用于根据id查询读者，参考原前端代码，入参为url携带参数，返回值为工具类DataInfo，见下“model”，此处其data为admin")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "id", value = "管理员id"),
    })
    @RequestMapping(value = "/queryAdminById1", method = RequestMethod.GET)
    @ResponseBody
    public DataInfo queryAdminById1(Integer id) {
        Admin admin = adminService.queryAdminById(id);
        return DataInfo.ok(admin);
    }

    /**
     * 修改提交
     */
    /*@ApiOperation(value = "这个方法是用于修改管理员密码的提交/入库，参考原前端代码，入参为post类请求体携带的json参数，返回值为工具类DataInfo，见下“model”，此处其data为空",
            notes = "1.目前后端代码支持入参为旧密码和新密码\n2.修改后的刷新请在前端调用adminAll接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", required = true, name = "id", value = "管理员id"),
            @ApiImplicitParam(paramType = "body", required = true, name = "oldPwd", value = "旧密码"),
            @ApiImplicitParam(paramType = "body", required = true, name = "newPwd", value = "新密码")
    })*/
    @ApiOperation(value = "原先的Post方式参数列表式入参改不好，改成GET不就密码直接在URL了吗，不太好，下面改成对象处理updatePwdSubmit1")
    @RequestMapping(value = "/updatePwdSubmit", method = RequestMethod.POST)
    @ResponseBody
    public DataInfo updatePwdSubmit(Integer id, String oldPwd, String newPwd) {
        Admin admin = adminService.queryAdminById(id);//根据id查询对象
        if (!oldPwd.equals(admin.getPassword())) {
            return DataInfo.fail("The old password entered is incorrect");
        } else {
            admin.setPassword(newPwd);
            adminService.updateAdminSubmit(admin);//数据库修改
            return DataInfo.ok();
        }
    }

    @ApiOperation(value = "这个方法是用于修改管理员密码的提交/入库，参考原前端代码，入参为post类请求体携带的json参数，返回值为工具类DataInfo，见下“model”，此处其data为空",
            notes = "1.目前后端代码支持入参为旧密码和新密码\n2.修改后的刷新请在前端调用adminAll接口\n3.请前端自行调用查询与更新接口完成更新操作，源代码是把页面跳转写在后端了")
    @RequestMapping(value = "/updatePwdSubmit1", method = RequestMethod.POST)
    @ResponseBody
    public DataInfo updatePwdSubmit1(/*Integer id, String oldPwd, String newPwd*/@RequestBody Admin admin) {
        /*Admin admin = adminService.queryAdminById(id);//根据id查询对象
        if (!oldPwd.equals(admin.getPassword())) {
            return DataInfo.fail("The old password entered is incorrect");
        } else {
            admin.setPassword(newPwd);
            adminService.updateAdminSubmit(admin);//数据库修改
            return DataInfo.ok();
        }*/
        adminService.updateAdminSubmit(admin);//数据库修改
        return DataInfo.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "这个方法是用于根据id删除管理员，参考原前端代码，入参为url携带参数，返回值为工具类DataInfo，见下“model”，此处其data为空")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "ids", value = "待删除管理员id列表，请以英文逗号分隔"),
    })
    @RequestMapping(value = "/deleteAdminByIds", method = RequestMethod.GET)
    @ResponseBody
    public DataInfo deleteAdminByIds(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        adminService.deleteAdminByIds(list);
        return DataInfo.ok();
    }

}
