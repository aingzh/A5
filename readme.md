# 游客注册
## 新增头像设置支持
在 `ReaderInfo` 类中添加 `avatara`属性
```java
    private String avatar;
    public String getAvatar() {
        return avatar;
        }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
        }
```
## 添加注册功能
`addReaderSubmit` 方法接受一个 `ReaderInfo` 对象作为请求体
```java
    @RequestMapping("/addReaderSubmit")
    @ResponseBody
    public DataInfo addReaderSubmit(@RequestBody ReaderInfo readerInfo) {
        readerInfo.setPassword("123456");//设置默认密码
        readerInfoService.addReaderInfoSubmit(readerInfo);
        return DataInfo.ok();
        }
```
注意，由于 `ReaderInfo` 类中密码的定义为
```java
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
```
如果未提供密码，则将使用默认密码"123456"


# 超级用户增删改用户信息
## 添加用户信息
同游客注册，但为了添加时能够自行设置密码而运用三目表达式
```java
    @RequestMapping("/addReaderSubmit")
    @ResponseBody
    public DataInfo addReaderSubmit(@RequestBody ReaderInfo readerInfo) {
        readerInfo.setPassword(readerInfo.getPassword() != null ? readerInfo.getPassword() : "123456");//设置默认密码
        readerInfoService.addReaderInfoSubmit(readerInfo);
        return DataInfo.ok();
    }
```
## 更新用户信息
先根据ID匹配用户数据再跳转至修改页面
```java
    @GetMapping("/queryReaderInfoById")
    public String queryReaderInfoById(Integer id, Model model) {
        ReaderInfo readerInfo = readerInfoService.queryReaderInfoById(id);
        model.addAttribute("info", readerInfo);
        return "reader/updateReader";
    }
```
然后提交修改
```java
    @RequestMapping("/updateReaderSubmit")
    @ResponseBody
    public DataInfo updateReaderSubmit(@RequestBody ReaderInfo readerInfo) {
        readerInfoService.updateReaderInfoSubmit(readerInfo);
        return DataInfo.ok();
    }
```
## 删除用户信息
```java
    @RequestMapping("/deleteReader")
    @ResponseBody
    public DataInfo deleteReader(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        readerInfoService.deleteReaderInfoByIds(list);
        return DataInfo.ok();
    }
```


# 简单测试新增头像修改功能的可行性
## 前言
由于本人不太熟悉前端只能进行简单的测试：p

## 前端部分
首先在 `updateReader.jsp` 中添加头像URL传输
```html
    <div class="layui-form-item">
        <label class="layui-form-label required">头像</label>
        <div class="layui-input-block">
            <input type="text" name="avatar" value="${info.avatar}" autocomplete="off" class="layui-input">
        </div>
    </div>
```
然后在系统中进入账户信息修改页面，在头像栏输入 `example`，显示异常500，检查发现前端数据已正确传出
![222.png](https://s2.loli.net/2023/04/16/nXJPHqsWfubI8LF.png)
## 后端部分
依次检查后端代码，发现 `ReaderInfoMapper.xml` 中缺少 `avatar` 列，需进行添加
```xml
    <result column="avatar" jdbcType="VARCHAR" property="avatar"/>
    
   
    <sql id="Base_Column_List">
        avatar
    </sql>
    
  
    <insert id="insert" parameterType="com.yx.po.ReaderInfo">
        insert into reader_info (avatar
        )
        values (#{avatar,jdbcType=VARCHAR}
        )
    </insert>

   
    <trim prefix="(" suffix=")" suffixOverrides=",">
    <if test="avatar != null">
        avatar,
    </if>
    </trim>
   
   
    <set>
    <if test="avatar != null">
        avatar = #{avatar,jdbcType=VARCHAR},
    </if>
    </set>

    <update id="updateByPrimaryKey" parameterType="com.yx.po.ReaderInfo">
    update reader_info
    set avatar = #{avatar,jdbcType=VARCHAR}
    where id = #{id,jdbcType=INTEGER}
    </update>
```
数据库 `library.sql` 中也加入 `avatar` 列
```sql
    CREATE TABLE `reader_info`  (
                                 `avatar`       varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
                                  PRIMARY KEY (`id`) USING BTREE
    ) ENGINE = InnoDB
```
测试数据分别都输入一个空字段预防报错
```sql
    INSERT INTO `reader_info`
    VALUES (1, 'zhangsan', '12345', '彭于晏', '男', '2001-04-01', '江西南昌', '13767134834', 'yu123@163.com',
            '2021-04-02 13:18:59', '8120116041', '');
    INSERT INTO `reader_info`
    VALUES (2, 'mary', '12345', '陈恋', '女', '2004-04-01', '湖北武汉', '15270839599', 'yx123@163.com',
            '2021-03-06 07:57:56', '8120116044', '');
    INSERT INTO `reader_info`
    VALUES (3, 'cindy', '12345', '辛帝', '女', '2010-04-04', '北京海淀', '13834141133', 'zs1314@163.com',
            '2021-04-04 13:36:42', '8120116042', '');
```
系统上头像栏再输入 `example` 进行测试，无报错且提示修改成功，测试完成
![111.png](https://s2.loli.net/2023/04/16/vVrAfMpNQt1BIuJ.png)





