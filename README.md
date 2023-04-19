# generatorConifg.xml文件 #
mybatis generator会根据worker数据表字段创建po类、dao类以及mapper文件，配置好依赖关系。  
修改的有  
1. mysql-connector-java  
1）```classPathEntry``` 下```mysql-connector-java```的jar包位置 ```location```  
2）JDBC连接本地数据库的```connectionURL```，这里遇到时区问题，可以参考<https://blog.csdn.net/mqdxiaoxiao/article/details/108281845>，即在URL中加入```serverTimezone=Asia/Shanghai```  
3）JDBC ```driverClass```的版本问题，如果是6.0以上版本则是```com.mysql.cj.jdbc.Driver```
4）连接数据库的密码```password```和用户名```userId```
2. 项目路径  
```javaModelGenerator```、```sqlMapGenerator```、```javaClientGenerator```下本地项目路径```targetProject```  

# 编辑配置 #
编辑配置中添加maven运行项，配置命令```mybatis-generator:generate -e```,运行后mybatis会为worker表创建po类、dao类以及mapper文件  
参考<https://blog.csdn.net/Nishino_shou/article/details/81109740>  
但运行命令后po类、dao类以及mapper文件中其他的文件也会被mybatis自动刷新，刷新后就没有自定义添加的方法了，所以需要对这些软件包下的文件进行回滚变更  
生成worker相关的po类、dao类以及mapper文件需要根据需要添加自定义方法

# WorkerInfoMapper.xml #
在resource目录下的com.yx.dao，添加根据用户名和密码查询worker的方法  
```
  <select id="queryUserInfoByNameAndPassword" resultType="com.yx.po.WorkerInfo">
    select * from worker_info where username=#{username} and password=#{password}
  </select>
```  
添加根据工号和密码查询worker的方法  
```
  <select id="queryUserInfoByWorkerNumberAndPassword" resultType="com.yx.po.WorkerInfo">
    select * from worker_info where workerNumber=#{workerNumber} and password=#{password}
  </select>
```  

# WorkerInfoService #
在java目录下的com.yx.service，添加根据用户名和密码查询worker信息的方法
```
    WorkerInfo queryUserInfoByNameAndPassword(String username, String password);
```  
添加根据工号和密码查询worker信息的方法
```
    WorkerInfo queryUserInfoByWorkerNumberAndPassword(String WorkerNumber, String password);
```  

# WorkerInfoServiceImpl #
在java目录下的com.yx.service.impl，添加根据用户名和密码查询worker信息的方法
```
    public WorkerInfo queryUserInfoByNameAndPassword(String username, String password) {
        return workerInfoMapper.queryUserInfoByNameAndPassword(username, password);
    }
```  
添加根据工号和密码查询worker信息的方法
```
    public WorkerInfo queryUserInfoByWorkerNumberAndPassword(String workerNumber, String password) {
        return workerInfoMapper.queryUserInfoByWorkerNumberAndPassword(workerNumber, password);
    }
```  

# WorkerInfoMapper #
在java目录下的com.yx.dao，添加根据用户名和密码查询worker的方法
```
    WorkerInfo queryUserInfoByNameAndPassword(@Param("username") String username, @Param("password") String password);
```  
添加根据工号和密码查询worker的方法
```
    WorkerInfo queryUserInfoByWorkerNumberAndPassword(@Param("workerNumber") String workerNumber, @Param("password") String password);
```  

# WorkerInfo #
在java目录下的com.yx.po，没有添加新方法



# ReaderInfoMapper.xml #
在resource目录下的com.yx.dao，添加根据邮箱和密码查询读者信息的方法  
```
  <select id="queryUserInfoByEmailAndPassword" resultType="com.yx.po.ReaderInfo">
    select * from reader_info where email=#{email} and password=#{password}
  </select>
```  

# ReaderInfoService #
在java目录下的com.yx.service，添加根据邮箱和密码查询读者信息的方法
```
    ReaderInfo queryUserInfoByEmailAndPassword(String email, String password);
```  

# ReaderInfoServiceImpl #
在java目录下的com.yx.service.impl，添加根据邮箱和密码查询读者信息的方法
```
    public ReaderInfo queryUserInfoByEmailAndPassword(String email, String password) {
        return readerInfoMapper.queryUserInfoByEmailAndPassword(email, password);
    }
```  

# ReaderInfoMapper #
在java目录下的com.yx.dao，添加根据邮箱和密码查询读者的方法
```
    ReaderInfo queryUserInfoByEmailAndPassword(@Param("email") String email, @Param("password") String password);
```  

# LoginController #
图书馆工作人员登陆判断，增加选择用户类型为图书馆工作人员时，判断其用户名和密码正确并且未离职时则登陆成功，若其用户名和密码正确但已离职则返回信息“该图书馆管理人员已离职”  
也可以用工号和密码登陆同上。需要前端对图书馆工作人员输入的账户做判断，若输入用户名保存在username，若输入工号保存在workerNumber，
```
    else if (type.equals("3")){//来自图书馆工作人员信息表
        WorkerInfo workerInfo;
        if (workerNumber == null) {
            workerInfo = workerService.queryUserInfoByNameAndPassword(username, password);
            if (workerInfo == null) {
                model.addAttribute("msg", "用户名或密码错误");
                return "login";
            } else if (workerInfo.getStatus() == 0){
                model.addAttribute("msg", "该图书馆管理人员已离职");
                return "login";
            }
        } else {
            workerInfo = workerService.queryUserInfoByWorkerNumberAndPassword(workerNumber, password);
            if (workerInfo == null) {
                model.addAttribute("msg", "工号或密码错误");
                return "login";
            } else if (workerInfo.getStatus() == 0) {
                model.addAttribute("msg", "该图书馆管理人员已离职");
                return "login";
            }
        }
            session.setAttribute("user", workerInfo);
            session.setAttribute("type", "worker");
    }
```  
读者登陆判断，添加读者邮箱密码登陆，需要前端对读者输入的账户做判断，若输入用户名保存在username，若输入邮箱保存在email，没有删除读者的用户名登陆是为了不影响其他同学登陆读者系统测试
```
else if (type.equals("2")){//来自读者信息表
    ReaderInfo readerInfo;
    if (email == null) {
        readerInfo = readerService.queryUserInfoByNameAndPassword(username, password);
        if (readerInfo == null) {
             model.addAttribute("msg", "用户名或密码错误");
             return "login";
        }
    }else {
         readerInfo = readerService.queryUserInfoByEmailAndPassword(email, password);
         if (readerInfo == null) {
              model.addAttribute("msg", "邮箱或密码错误");
              return "login";
         }
    }
    session.setAttribute("user", readerInfo);
    session.setAttribute("type", "reader");
}
```

# login.jsp #
为了测试登陆，在选择用户类型中添加了图书馆工作人员的选项  
```<option value="3">图书馆工作人员</option>```