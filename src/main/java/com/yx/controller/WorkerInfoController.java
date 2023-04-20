package com.yx.controller;

import com.github.pagehelper.PageInfo;
import com.yx.po.WorkerInfo;
import com.yx.service.WorkerInfoService;
import com.yx.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class WorkerInfoController {

    @Autowired
    private WorkerInfoService workerInfoService;

    /*@Autowired
    private AdminService adminService;*/

    /**
     * 跳转读者管理页面
     */
    @GetMapping("/workerIndex")
    public String workerIndex(){
        return "worker/workerIndex";
    }

    /**
     * 查询所有数据
     */
    @RequestMapping("/workerAll")
    @ResponseBody
    public DataInfo queryWorkerAll(WorkerInfo workerInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit){
        PageInfo<WorkerInfo> pageInfo = workerInfoService.queryAllWorkerInfo(workerInfo,pageNum,limit);
        return DataInfo.ok("成功",pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加页面跳转
     */
    @RequestMapping("/workerAdd")
    public String workerAdd(){
        return "worker/workerAdd";
    }

    /**
     * 添加页面提交
     */
    @RequestMapping("/addWorkerSubmit")
    @ResponseBody
    public DataInfo addWorkerSubmit(@RequestBody WorkerInfo workerInfo){
        workerInfo.setPassword("123456");//设置默认密码
        workerInfoService.addWorkerInfoSubmit(workerInfo);
        return DataInfo.ok();
    }

    /**
     * 根据id查询数据再跳转到修改页面
     */
    @GetMapping("/queryWorkerInfoById")
    public String queryWorkerInfoById(Integer id, Model model){
        WorkerInfo workerInfo = workerInfoService.queryWorkerInfoById(id);
        model.addAttribute("info",workerInfo);
        return "worker/updateWorker";
    }

    /**
     * 修改提交
     */
    @RequestMapping("/updateWorkerSubmit")
    @ResponseBody
    public DataInfo updateWorkerSubmit(@RequestBody WorkerInfo workerInfo){
        workerInfoService.updateWorkerInfoSubmit(workerInfo);
        return DataInfo.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/deleteWorker")
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
                return DataInfo.fail("输入的旧密码错误");
            }else{
                admin1.setPassword(newPwd);
                adminService.updateAdminSubmit(admin1);//数据库修改
            }
        }else{
            //读者
            WorkerInfo workerInfo = (WorkerInfo) session.getAttribute("user");
            WorkerInfo workerInfo1 = workerInfoService.queryWorkerInfoById(workerInfo.getId());//根据id查询对象
            if (!oldPwd.equals(workerInfo1.getPassword())){
                return DataInfo.fail("输入的旧密码错误");
            }else{
                workerInfo1.setPassword(newPwd);
                workerInfoService.updateWorkerInfoSubmit(workerInfo1);//数据库修改
            }
        }
        return DataInfo.ok();
    }*/
}
