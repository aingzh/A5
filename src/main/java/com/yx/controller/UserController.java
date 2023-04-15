package com.yx.controller;

import com.github.pagehelper.PageInfo;
import com.yx.po.User;
import com.yx.service.UserService;
import com.yx.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

@Autowired
    private UserService userService;

    @GetMapping("/userIndex")
    public String userIndex(){
        return "user/userIndex";
    }

    @RequestMapping("/userAll")
    @ResponseBody
    public DataInfo queryUserAll(User user, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit){
        PageInfo<User> pageInfo = userService.queryUserAll(user,pageNum,limit);
        return DataInfo.ok("成功",pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加页面的跳转
     * @return
     */
    @GetMapping("/userAdd")
    public String userAdd(){
        return "user/userAdd";
    }

    /**
     * 添加提交
     * @param user
     * @return
     */
    @RequestMapping("/addUserSubmit")
    @ResponseBody
    public DataInfo addUserSubmit(User user){
        userService.addUserSubmit(user);
        return DataInfo.ok();
    }

    /**
     * 根据id查询
     */
    @GetMapping("/queryUserById")
    public String queryUserById(Integer id, Model model){
        model.addAttribute("id",id);
        return "user/updateUser";
    }

    /**
     * 修改提交
     */
    @RequestMapping("/updatePwdSubmit")
    @ResponseBody
    public DataInfo updatePwdSubmit(Integer id,String oldPwd,String newPwd){
        User user = userService.queryUserById(id);//根据id查询对象
        if (!oldPwd.equals(user.getPassword())){
            return DataInfo.fail("输入的旧密码错误");
        }else{
            user.setPassword(newPwd);
            userService.updateUserSubmit(user);//数据库修改
            return DataInfo.ok();
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/deleteUserByIds")
    @ResponseBody
    public DataInfo deleteUserByIds(String ids){
        List<String> list = Arrays.asList(ids.split(","));
        userService.deleteUserByIds(list);
        return DataInfo.ok();
    }



}
