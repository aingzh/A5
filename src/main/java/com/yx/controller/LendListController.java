package com.yx.controller;

import com.github.pagehelper.PageInfo;
import com.yx.po.Admin;
import com.yx.po.BookInfo;
import com.yx.po.LendList;
import com.yx.po.ReaderInfo;
import com.yx.service.BookInfoService;
import com.yx.service.LendListService;
import com.yx.service.ReaderInfoService;
import com.yx.utils.Constants;
import com.yx.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Controller
public class LendListController {
    @Autowired
    private LendListService lendListService;
    @Autowired
    private ReaderInfoService readerService;

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.lend.notify.from}")
    private String form;

    @Value("${mail.lend.notify.subject}")
    private String subject;

    @Value("${mail.lend.notify.content}")
    private String content;

    @GetMapping("/lendListIndex")
    public String lendListIndex() {
        return "lend/lendListIndex";
    }

    @GetMapping("/lendListIndexOfReader")
    public String lendListIndexOfReader(){
        return "lend/lendListIndexOfReader";
    }

    /**
     * 分页查询所有借阅记录
     * 1 request获取
     * 2、参数绑定
     * 3、对象绑定
     */
    @ResponseBody
    @RequestMapping("/lendListAll")
    public DataInfo lendListAll(Integer type, String readerNumber, String name, Integer status,
                                @RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "15")Integer limit){
        LendList info=new LendList();
        info.setBackType(type);
        //创建读者对象
        ReaderInfo reader=new ReaderInfo();
        reader.setReaderNumber(readerNumber);
        //把以上对象交给info
        info.setReaderInfo(reader);
        //图书对象
        BookInfo book=new BookInfo();
        book.setName(name);
        book.setStatus(status);
        info.setBookInfo(book);
        //分页查询所有的记录信息
        PageInfo pageInfo=lendListService.queryLendListAll(info,page,limit);
        return DataInfo.ok("ok",pageInfo.getTotal(),pageInfo.getList());
    }

    @ResponseBody
    @RequestMapping("/lendListAllOfReader")
    public DataInfo lendListAllOfReader(HttpSession session, Integer backType, String name, Integer status,
                                        @RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "15")Integer limit){
        ReaderInfo readerInfo = (ReaderInfo) session.getAttribute("user");
        return lendListAll(backType, readerInfo.getReaderNumber(), name, status, page, limit);
    }

    /**
     * 添加跳转
     */
    @GetMapping("/addLendList")
    public String addLendList() {
        return "lend/addLendList";
    }


    /**
     * 添加跳转
     */
    @GetMapping("/addLendListOfReader")
    public String addLendListOfReader(){
        return "lend/addLendListOfReader";
    }

    /**
     * 借书信息提交
     * 1判断借阅号码是否存在
     * 2、可借的数据是否大于等于当前的借书量
     * 3、添加借书记录，同时改变书的状态信息
     * cardnumber:借书号码
     * ids：字符串 书id的集合
     */
    @ResponseBody
    @RequestMapping("/addLend")
    public DataInfo addLend(String readerNumber,String ids){
        //获取图书id的集合
        List<String> list= Arrays.asList(ids.split(","));
        if(list.size() > Constants.LEND_MAX || list.isEmpty() ) {
            return DataInfo.fail(String.format("只能借1~%s本书", Constants.LEND_MAX));
        }

        //判断卡号是否存在
        ReaderInfo reader=new ReaderInfo();
        reader.setReaderNumber(readerNumber);
        PageInfo<ReaderInfo> pageInfo=readerService.queryAllReaderInfo(reader,1,1);
        if(pageInfo.getList().size()==0){
            return DataInfo.fail("卡号信息不存在");
        } else{
            ReaderInfo readerCard2=pageInfo.getList().get(0);
            List<LendList> lendLists = lendListService.queryListByReader(readerCard2.getId());
            if(list.size() > (Constants.LEND_MAX - lendLists.size())) {
                return DataInfo.fail(String.format("只能再借%s本书", (Constants.LEND_MAX - lendLists.size())));
            } else {
                //可借书
                for(String bid:list) {
                    LendList lendList = new LendList();
                    lendList.setReaderId(readerCard2.getId());//读者id
                    lendList.setBookId(Integer.valueOf(bid));//书的id
                    lendList.setLendDate(new Date());
                    lendListService.addLendListSubmit(lendList);
                    //更变书的状态
                    BookInfo info = bookInfoService.queryBookInfoById(Integer.valueOf(bid));
                    //设置书的状态
                    info.setStatus(1);
                    bookInfoService.updateBookSubmit(info);
                }
            }
        }

        return DataInfo.ok();
    }

    @ResponseBody
    @RequestMapping("/addLendOfReader")
    public DataInfo addLendOfReader(HttpSession session, String ids){
        ReaderInfo readerInfo = (ReaderInfo) session.getAttribute("user");
        return addLend(readerInfo.getId() + "", ids);
    }

    /**
     * 删除借阅记录
     */
    @ResponseBody
    @RequestMapping("/deleteLendListByIds")
    public DataInfo deleteLendListByIds(String ids, String bookIds) {
        List list = Arrays.asList(ids.split(","));//借阅记录的id
        List blist = Arrays.asList(bookIds.split(","));//图书信息的id

        lendListService.deleteLendListById(list, blist);
        return DataInfo.ok();
    }

    /**
     * 还书功能
     */
    @ResponseBody
    @RequestMapping("/backLendListByIds")
    public DataInfo backLendListByIds(String ids, String bookIds) {
        List<String> list = Arrays.asList(ids.split(","));//借阅记录的id
        List<String> blist = Arrays.asList(bookIds.split(","));//图书信息的id
        lendListService.updateLendListSubmit(list, blist);
        return DataInfo.ok();
    }

    /**
     * 页面跳转 异常还书
     */
    @GetMapping("/excBackBook")
    public String excBackBook(HttpServletRequest request, Model model) {
        //获取借阅记录id
        String id = request.getParameter("id");
        String bId = request.getParameter("bookId");
        model.addAttribute("id", id);
        model.addAttribute("bid", bId);
        return "lend/excBackBook";
    }

    /**
     * 异常还书
     */
    @ResponseBody
    @RequestMapping("/updateLendInfoSubmit")
    public DataInfo updateLendInfoSubmit(LendList lendList) {
        lendListService.backBook(lendList);
        return DataInfo.ok();
    }

    /**
     * 查阅时间线
     */
    @RequestMapping("/queryLookBookList")
    public String queryLookBookList(String flag, Integer id, Model model) {
        List<LendList> list = null;
        if (flag.equals("book")) {
            list = lendListService.queryLookBookList(null, id);
        } else {
            list = lendListService.queryLookBookList(id, null);
        }
        model.addAttribute("info", list);
        return "lend/lookBookList";
    }

    @RequestMapping("/queryLookBookList2")
    public String queryLookBookList(HttpServletRequest request, Model model) {
        ReaderInfo readerInfo = (ReaderInfo) request.getSession().getAttribute("user");
        List<LendList> list = list = lendListService.queryLookBookList(readerInfo.getId(), null);
        model.addAttribute("info", list);
        return "lend/lookBookList";
    }

    @RequestMapping("/noticeBack")
    @ResponseBody
    public DataInfo noticeBack(HttpSession session) {
        final Object user = session.getAttribute("user");
        if (user instanceof Admin) {
            return DataInfo.ok();
        }

        final Integer c = lendListService.countWillExpireLend(((ReaderInfo) user).getId());
        if (c > 0) {
            return DataInfo.fail("已借的书籍中有" + c + "已经过期");
        }
        else if (c <= 0 && c > -7){
            return DataInfo.fail("已借的书籍中有" + c + "本即将在7天内过期");
        }
        else {
            return DataInfo.ok();
        }
    }

    /**
     * 提醒归还图书
     * @param request
     * @param model
     *
     * @return
     */
    @RequestMapping("/notifyBackLend")
    @ResponseBody
    public DataInfo notifyBackLend(HttpServletRequest request,Model model){
        List<LendList> list = lendListService.queryOverdueList();
        new Thread() {
            @Override
            public void run() {
                for (LendList lendList : list) {
                    ReaderInfo readerInfo = lendList.getReaderInfo();
                    if(readerInfo != null) {
                        String to = readerInfo.getEmail();
                        if(to != null) {
                            SimpleMailMessage email = new SimpleMailMessage();
                            email.setFrom(form);
                            email.setTo(to);
                            email.setSubject(subject);
                            email.setText(content);

                            try {
                                javaMailSender.send(email);
                                System.out.println(to);
                                // System.out.println(lendList.getBookInfo().getName());
                            } catch (MailException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }.start();
        return DataInfo.ok("成功", null);
    }

    /**
     public static void main(String[] args) {
     Properties properties = new Properties();
     properties.setProperty("mail.smtp.auth", "true");
     JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
     javaMailSender.setHost("smtp.163.com");
     javaMailSender.setPort(25);
     javaMailSender.setDefaultEncoding("UTF-8");
     javaMailSender.setUsername("overdue_reminder1@163.com");
     javaMailSender.setPassword("YGATFFMLURWTTSAO");
     javaMailSender.setJavaMailProperties(properties);

     SimpleMailMessage email = new SimpleMailMessage();
     email.setFrom("overdue_reminder1@163.com");
     email.setTo("overdue_reminder1@163.com");
     email.setSubject("Test");
     email.setText("Test");

     // sends the e-mail
     javaMailSender.send(email);
     }
     */

}
