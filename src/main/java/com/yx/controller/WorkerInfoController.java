package com.yx.controller;

import com.github.pagehelper.PageInfo;
import com.yx.po.WorkerInfo;
import com.yx.po.WorkerInfo;
import com.yx.service.WorkerInfoService;
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
@Api(tags = "员工管理--测试接口请先登录--根据读者cv，如未改干净请发挥你的聪明才智")
public class WorkerInfoController {

    @Autowired
    private WorkerInfoService workerInfoService;

    /*@Autowired
    private AdminService adminService;*/

    /**
     * 跳转员工管理页面
     */
    @GetMapping("/workerIndex")
    @ApiOperation(value = "这个方法是用于页面跳转的，返回值是一个jsp", notes = "准备将页面跳转迁移至前端处理，此处可能废置")
    public String workerIndex(){
        return "worker/workerIndex";
    }

    /**
     * 查询所有数据
     */
    @ApiOperation(value = "这个方法是用于模糊查询的，参考原前端代码，入参为url携带参数，返回值为工具类DataInfo，见下“model”，此处其data为workerInfo的list",
            notes = "1.目前后端代码支持入参 员工工号+用户名+电话号 三者的任意查询，0/1/2/3参数皆可\n2.实际接口的入参是workerInfo和两个有默认值的分页参数，ssm没学到家，不知道是怎么实现接收处理url参数的")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = false, name = "workerNumber", value = "员工工号"),
            @ApiImplicitParam(paramType = "query", required = false, name = "username", value = "用户名"),
            @ApiImplicitParam(paramType = "query", required = false, name = "tel", value = "电话号")
    })
    @RequestMapping(value = "/workerAll", method = RequestMethod.GET)
    @ResponseBody
    public DataInfo queryWorkerAll(WorkerInfo workerInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit){
        PageInfo<WorkerInfo> pageInfo = workerInfoService.queryAllWorkerInfo(workerInfo,pageNum,limit);
        return DataInfo.ok("success",pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加页面跳转
     */
    @RequestMapping(value = "/workerAdd", method = RequestMethod.GET)
    @ApiOperation(value = "这个方法是用于页面跳转的，返回值是一个jsp", notes = "准备将页面跳转迁移至前端处理，此处可能废置")
    public String workerAdd(){
        return "worker/workerAdd";
    }

    /**
     * 添加页面提交
     */
    @ApiOperation(value = "这个方法是用于添加员工的提交/入库，参考原前端代码，入参为post类请求体携带的json参数，返回值为工具类DataInfo，见下“model”，此处其data为空",
            notes = "1.目前后端代码支持入参为除id（这个数据库设置的自动增长）外的所有WorkerInfo信息\n2.实际接口的入参是workerInfo，ssm没学到家，不知道是怎么实现接收处理json参数的\n3.增添后的刷新请在前端调用workerAll接口")
    /*不需要画蛇添足，这个写法不对，对象式参数直接向下面一样 @RequestBody 即可
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", required = true, name = "workerInfo1", value = "测试", dataType = "com.yx.po.WorkerInfo")
    })*/
    @RequestMapping(value = "/addWorkerSubmit", method = RequestMethod.POST)
    @ResponseBody
    public DataInfo addWorkerSubmit(@RequestBody WorkerInfo workerInfo){
        workerInfo.setPassword("123456");//设置默认密码
        workerInfoService.addWorkerInfoSubmit(workerInfo);
        return DataInfo.ok();
    }

    /**
     * 根据id查询数据再跳转到修改页面
     */
    @ApiOperation(value = "这个原方法进行了查询+页面跳转", notes = "不知道如何处理，下面类比workerAll提供了queryWorkerInfoById1")
    @GetMapping("/queryWorkerInfoById")
    public String queryWorkerInfoById(Integer id, Model model){
        WorkerInfo workerInfo = workerInfoService.queryWorkerInfoById(id);
        model.addAttribute("info",workerInfo);
        return "worker/updateWorker";
    }

    @ApiOperation(value = "这个方法是用于根据id查询员工，参考原前端代码，入参为url携带参数，返回值为工具类DataInfo，见下“model”，此处其data为workerInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "id", value = "员工id"),
    })
    @RequestMapping(value = "/queryWorkerInfoById1", method = RequestMethod.GET)
    @ResponseBody
    public DataInfo queryWorkerInfoById1(Integer id) {
        WorkerInfo workerInfo = workerInfoService.queryWorkerInfoById(id);
        return DataInfo.ok(workerInfo);
    }

    /**
     * 修改提交
     */
    @ApiOperation(value = "这个方法是用于修改员工的提交/入库，参考原前端代码，入参为post类请求体携带的json参数，返回值为工具类DataInfo，见下“model”，此处其data为空",
            notes = "1.目前后端代码支持入参为除id（这个数据库设置的自动增长）外的所有WorkerInfo信息\n2.实际接口的入参是workerInfo，ssm没学到家，不知道是怎么实现接收处理json参数的\n3.修改后的刷新请在前端调用workerAll接口")
    @RequestMapping(value = "/updateWorkerSubmit", method = RequestMethod.POST)
    @ResponseBody
    public DataInfo updateWorkerSubmit(@RequestBody WorkerInfo workerInfo){
        workerInfoService.updateWorkerInfoSubmit(workerInfo);
        return DataInfo.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "这个方法是用于根据id删除员工，参考原前端代码，入参为url携带参数，返回值为工具类DataInfo，见下“model”，此处其data为空")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "ids", value = "待删除员工id列表，请以英文逗号分隔"),
    })
    @RequestMapping(value = "/deleteWorker", method = RequestMethod.GET)
    @ResponseBody
    public DataInfo deleteWorker(String ids){
        List<String> list= Arrays.asList(ids.split(","));
        workerInfoService.deleteWorkerInfoByIds(list);
        return DataInfo.ok();
    }

    /**
     * 修改提交（右上角修改密码）
     */
    /*@RequestMapping("/updatePwdSubmit3")
    @ResponseBody
    public DataInfo updatePwdSubmit(HttpServletRequest request, String oldPwd, String newPwd){
        HttpSession session = request.getSession();
        if(session.getAttribute("type")=="admin"){
            //管理员
            Admin admin = (Admin)session.getAttribute("user");
            Admin admin1 = (Admin)adminService.queryAdminById(admin.getId());
            if (!oldPwd.equals(admin1.getPassword())){
                return DataInfo.fail("The old password entered is incorrect");
            }else{
                admin1.setPassword(newPwd);
                adminService.updateAdminSubmit(admin1);//数据库修改
            }
        }else{
            //员工
            WorkerInfo workerInfo = (WorkerInfo) session.getAttribute("user");
            WorkerInfo workerInfo1 = workerInfoService.queryWorkerInfoById(workerInfo.getId());//根据id查询对象
            if (!oldPwd.equals(workerInfo1.getPassword())){
                return DataInfo.fail("The old password entered is incorrect");
            }else{
                workerInfo1.setPassword(newPwd);
                workerInfoService.updateWorkerInfoSubmit(workerInfo1);//数据库修改
            }
        }
        return DataInfo.ok();
    }*/
}
