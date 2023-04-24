package com.yx.controller;

import com.yx.codeutil.IVerifyCodeGen;
import com.yx.codeutil.SimpleCharVerifyCodeGenImpl;
import com.yx.codeutil.VerifyCode;
import com.yx.po.Admin;
import com.yx.po.ReaderInfo;
import com.yx.po.WorkerInfo;
import com.yx.service.AdminService;
import com.yx.service.ReaderInfoService;
import com.yx.service.WorkerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ReaderInfoService readerService;
    @Autowired
    private WorkerInfoService workerService;

    /**
     * 登录页面的转发
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 获取验证码方法
     *
     * @param request
     * @param response
     */
    @RequestMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            System.out.println("Exception handling");
        }
    }

    /**
     * 登录验证
     */
    @RequestMapping("/loginIn")
    public String loginIn(HttpServletRequest request, Model model) {

        //获取用户名与密码
        String input = request.getParameter("input");
        String email = null;
        String workerNumber = null;
        String username = null;
        String password = request.getParameter("password");
        String code = request.getParameter("captcha");
        String type = request.getParameter("type");

        //判断验证码是否正确（验证码已经放入session）
        HttpSession session = request.getSession();
        String realCode = (String) session.getAttribute("VerifyCode");
        if (!realCode.toLowerCase().equals(code.toLowerCase())) {
            model.addAttribute("msg", "The captcha is incorrect");
            return "login";
        }

        // 类型判断
        // 使用正则表达式验证输入是否为邮箱、工号、用户名
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[a-z]+$";
        String workerNumberRegex = "^2000920\\d{4}$";
        if (input.matches(emailRegex)) {
            email = input;
        } else if (input.matches(workerNumberRegex)) {
            workerNumber = input;
        } else {
            username = input;
        }
        //验证码正确则判断用户名和密码
        if (type.equals("1")) {//管理员信息
            //工号和密码是否正确
            Admin admin = adminService.queryUserByNameAndPassword(username, password);
            if (admin == null) {//该用户不存在
                model.addAttribute("msg", "The work number or password is incorrect");
                return "login";
            }
            session.setAttribute("user", admin);
            session.setAttribute("type", "admin");
        } else if (type.equals("2")) {//来自读者信息表
            ReaderInfo readerInfo;
            if (email == null) {
                readerInfo = readerService.queryUserInfoByNameAndPassword(username, password);
                if (readerInfo == null) {
                    model.addAttribute("msg", "The username or password is incorrect");
                    return "login";
                }
            } else {
                readerInfo = readerService.queryUserInfoByEmailAndPassword(email, password);
                if (readerInfo == null) {
                    model.addAttribute("msg", "The email or password is incorrect");
                    return "login";
                }
            }
            session.setAttribute("user", readerInfo);
            session.setAttribute("type", "reader");
        } else if (type.equals("3")) {//来自图书馆工作人员信息表
            WorkerInfo workerInfo;
            if (workerNumber == null) {
                workerInfo = workerService.queryUserInfoByNameAndPassword(username, password);
                if (workerInfo == null) {
                    model.addAttribute("msg", "The username or password is incorrect");
                    return "login";
                } else if (workerInfo.getStatus() == 0) {
                    model.addAttribute("msg", "The library staff has resignated");
                    return "login";
                }
            } else {
                workerInfo = workerService.queryUserInfoByWorkerNumberAndPassword(workerNumber, password);
                if (workerInfo == null) {
                    model.addAttribute("msg", "The work number or password is incorrect");
                    return "login";
                } else if (workerInfo.getStatus() == 0) {
                    model.addAttribute("msg", "The library staff has resignated");
                    return "login";
                }
            }
            session.setAttribute("user", workerInfo);
            session.setAttribute("type", "worker");
        }

        return "redirect:/index";

    }

    /**
     * 退出功能
     */
    @GetMapping("loginOut")
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();//注销
        return "/login";
    }

}
