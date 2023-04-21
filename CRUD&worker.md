### 一、版本变更

#### 1.0 

* 进行了增删改查功能的完善，未能解决图书增加的日期录入失败的问题
* 进行了读者管理功能的添加

* 时间：2023年4月21日 15:13:21
* 署名：A5-后端-陆忠明



### 二、增删改查（CRUD）功能

#### （一）概述

1. 增删改查功能整体设计文件结构一致，不同功能单元的增删改查功能又高度相似，故本开发文档以reader表的增删改查为例
2. 服务调用是自顶向下的，但功能依赖是自底向上的，故本开发文档将自底向上进行说明



#### （二）以reader为例的CRUD

##### 1.代码结构及功能说明

* `pojo/model/domain`：三者是一个意思，作为数据传输媒介的 java 类，需要提供与数据表相对应的私有成员变量及其操作接口（setter&getter 方法），结构简单，后不再赘述

* `dao` 层（数据层）：使用 Mybatis 提供的 “Mapper 代理开发 + 配置文件” 方式，包含两个部分：

   * Mapper 接口：描述提供的接口（包含入参，返回值），写好服务层需要的方法即可
   * `Mapper.xml`：在 resources 文件夹下，需要与上述接口保持同名，路径保持对应，这里写于 Mapper 接口中方法对应的实现逻辑（不是具体实现，这只是配置文件，向 Mybatis 说明要如何实现）和字段映射（用于解决 `pojo` 成员变量名与数据库表字段名不一致问题，比如典型的下划线命名法与驼峰命名法 ）

* service 层：使用 `@Service` 注解为 Bean，交由 Spring IoC 管理（引用对象成员使用 `@Autowired` 进行自动装配），包含服务接口与对应实现，调用 `dao` 层接口实现服务功能

* controller 层：==与前端进行交互的最上层，URL，入参与返回值皆在这里==，`@RequestMapping` 注解描述前端请求的 url（相对路径），`@ResponseBody` 注解返回值响应体

   

##### 2.增加功能（Insert / add）

1. `pojo` ：`ReaderInfo`

2. `dao` 层： `ReaderInfoMapper.insert(ReaderInfo record)` 方法，主要是配置文件部分，定义实现逻辑。插入功能，使用动态 SQL 与静态 SQL 类似，一个是值为 null，一个是空，选用静态 `SQL`

   ```
   <insert id="insert" parameterType="com.yx.po.BookInfo">
     insert into book_info (id, name, author, publish, isbn, introduction,
       language, price, publish_date, type_id, status)
     values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, 
     #{author,jdbcType=VARCHAR}, #{publish,jdbcType=VARCHAR},
     #{isbn,jdbcType=VARCHAR}, #{introduction,jdbcType=VARCHAR},
     #{language,jdbcType=VARCHAR}, #{price,jdbcType=DOUBLE},
     #{publishDate,jdbcType=DATE}, 
     #{typeId,jdbcType=INTEGER}, #{status,jdbcType=INTEGER})
   </insert>
   ```

   说明：

   *  `</insert>` 标签指定为 insert 语句；

   * id 与 Mapper 方法名对应；

   * `parameterType` 入参类型，可省略，Mybatis 默认支持 参数罗列/Map/pojo 三种入参类型；

   * 后 #{} 为参数占位符（防 SQL 注入），其名称与入参中对应名称保持一致。

     

3. `service` 层：直接调用 `dao` 层方法

   ```
   public void addReaderInfoSubmit(ReaderInfo readerInfo) {
       readerInfoMapper.insert(readerInfo);
   }
   ```

   

4. `controller` 层：直接调用 `service` 层方法完成插入服务，调用工具类进行操作成功信息的返回

   ```
   @RequestMapping("/addReaderSubmit") //URL
   @ResponseBody
   public DataInfo addReaderSubmit(@RequestBody ReaderInfo readerInfo) {
       readerInfoService.addReaderInfoSubmit(readerInfo);
       return DataInfo.ok(); //调用工具类进行返回值封装，json格式字符串，插入功能，无data信息
   }
   ```



##### 3.删除功能（delete）

1. `pojo` ：`ReaderInfo`

2. `dao` 层： `deleteByPrimaryKey(Integer id);` 方法，主要是配置文件部分，定义实现逻辑。删除功能，一般依据传入主键进行删除即可

   ```
   <delete id="deleteByPrimaryKey" parameterType="java.lang.Integer">
       delete from reader_info
       where id = #{id,jdbcType=INTEGER}
   </delete>
   ```

   说明：

   *  `</delete>` 标签指定为 delete 语句；

   * id 与 Mapper 方法名对应；

   * `parameterType` 入参类型，可省略，Mybatis 默认支持 参数罗列/Map/pojo 三种入参类型；

   * 后 #{} 为参数占位符（防 SQL 注入），其名称与入参中对应名称保持一致

     

3. `service` 层：直接调用 `dao` 层方法，由于支持批量删除，所以循环调用

   ```
   public void deleteReaderInfoByIds(List<String> ids) {
       for (String id : ids) {
           readerInfoMapper.deleteByPrimaryKey(Integer.parseInt(id));
       }
   }
   ```

   

4. `controller` 层：直接调用 `service` 层方法完成批量删除服务，调用工具类进行操作成功信息的返回

   ```
   @RequestMapping("/deleteReader") //URL
   @ResponseBody
   public DataInfo deleteReader(String ids) {
       List<String> list = Arrays.asList(ids.split(","));
       readerInfoService.deleteReaderInfoByIds(list);
       return DataInfo.ok(); //调用工具类进行返回值封装，json格式字符串，删除功能，无data信息
   }
   ```



##### 4.修改功能（update）

1. `pojo` ：`ReaderInfo`

2. `dao` 层： `updateByPrimaryKeySelective(ReaderInfo record);` 方法，主要是配置文件部分，定义实现逻辑。修改功能，一般使用动态 SQL，以防输入信息不足导致不被期望的将原有值置为 null 的情况（**认为不输入表示不进行修改，而不是修改为 null**）

   ```
   <update id="updateByPrimaryKeySelective" parameterType="com.yx.po.ReaderInfo">
   	update reader_info
       <set>
           <if test="username != null and username != ''">
               username = #{username,jdbcType=VARCHAR},
           </if>
           <if test="password != null and password != ''">
               password = #{password,jdbcType=VARCHAR},
           </if>
           <if test="realName != null and realName != ''">
               realName = #{realName,jdbcType=VARCHAR},
           </if>
           <if test="sex != null and sex != ''">
               sex = #{sex,jdbcType=VARCHAR},
           </if>
           <if test="birthday != null">
               birthday = #{birthday,jdbcType=DATE},
           </if>
           <if test="address != null and address != ''">
               address = #{address,jdbcType=VARCHAR},
           </if>
           <if test="tel != null and tel != ''">
               tel = #{tel,jdbcType=VARCHAR},
           </if>
           <if test="email != null and email != ''">
               email = #{email,jdbcType=VARCHAR},
           </if>
           <if test="registerDate != null">
               registerDate = #{registerDate,jdbcType=TIMESTAMP},
           </if>
           <if test="readerNumber != null and readerNumber != ''">
               readerNumber = #{readerNumber,jdbcType=VARCHAR},
           </if>
           <if test="avatar != null and avatar != ''">
               avatar = #{avatar,jdbcType=VARCHAR}
           </if>
       </set>
       where id = #{id,jdbcType=INTEGER};
   </update>
   ```

   说明：

   * `</update>` 标签指定为 update语句；

   * id 与 Mapper 方法名对应；

   * `parameterType` 入参类型，可省略，Mybatis 默认支持 参数罗列/Map/pojo 三种入参类型；

   * 后 #{} 为参数占位符（防 SQL 注入），其名称与入参中对应名称保持一致；

   * 动态 SQL 使用 MyBatis 提供的 `</set>` 智能标签，采用 if 逻辑，可自动删除多余的语句和“,”，当一个 if 都未成立时，会自动删除 SQL 语句中的 “set”（当然，这会造成 SQL 语法出错，暂不处理）；

   * 注：**这里 if 标签增加了非空串的判定，意为空串输入表示不做变动，而不是变动为空串**

     

3. `service` 层：直接调用 `dao` 层方法

   ```
   public void updateReaderInfoSubmit(ReaderInfo readerInfo) {
       readerInfoMapper.updateByPrimaryKeySelective(readerInfo);
   }
   ```

   

4. `controller` 层：直接调用 `service` 层方法完成修改服务，调用工具类进行操作成功信息的返回

   ```
   @RequestMapping("/updateReaderSubmit") //URL
   @ResponseBody
   public DataInfo updateReaderSubmit(@RequestBody ReaderInfo readerInfo) {
       readerInfoService.updateReaderInfoSubmit(readerInfo);
       return DataInfo.ok(); //调用工具类进行返回值封装，json格式字符串，修改功能，无data信息
   }
   ```



##### 5.查询功能（select / query）

1. `pojo` ：`ReaderInfo`

2. `dao` 层：

   *  `List<ReaderInfo> queryAllReaderInfo(ReaderInfo readerInfo);` 和 `ReaderInfo selectByPrimaryKey(Integer id);`，查询功能一般提供简单查询（使用主键查询的那个）与条件查询（动态适应查询条件）两种，其他查询根据具体需求而定
   * 主要是配置文件部分，定义实现逻辑

   ```
   <select id="queryAllReaderInfo" resultType="com.yx.po.ReaderInfo" parameterType="com.yx.po.ReaderInfo">
       select * from reader_info
       <where>
           <!--读者图书卡号-->
           <if test="readerNumber!=null">
               and readerNumber like '%${readerNumber}%'
           </if>
           <!--用户名-->
           <if test="username!=null">
               and username like '%${username}%'
           </if>
           <!--电话号-->
           <if test="tel!=null">
               and tel like '%${tel}%'
           </if>
       </where>
   </select>
   ```

   ```
   <select id="selectByPrimaryKey" parameterType="java.lang.Integer" resultMap="BaseResultMap">
       select
       <include refid="Base_Column_List"/>
       from reader_info
       where id = #{id,jdbcType=INTEGER}
   </select>
   ```

   说明：

   * `</select>` 标签指定为 select语句；

   * id 与 Mapper 方法名对应；

   * `parameterType` 入参类型，可省略，Mybatis 默认支持 参数罗列/Map/pojo 三种入参类型；

   * 由于是查询语句，有返回值，通过 `resultMap` 指定（若不需要字段映射也可以用 `resultType`）；

   * `<include>` 为 SQL 片段导入，简单理解为字符串替换即可；

   * like 模糊查询需要使用 % 通配符处理一下

   * 后 #{} 为参数占位符（防 SQL 注入），其名称与入参中对应名称保持一致；

   * 动态 SQL 使用 MyBatis 提供的 `</where>` 智能标签，采用 if 逻辑，可自动删除多余的语句和“and”，当一个 if 都未成立时，会自动删除 SQL 语句中的 “where”，此时即为查询所有

     

3. `service` 层：直接调用 `dao` 层方法，剩余两个参数是分页显示相关信息

   ```
   public PageInfo<ReaderInfo> queryAllReaderInfo(ReaderInfo readerInfo, Integer pageNum, Integer limit) {
       PageHelper.startPage(pageNum, limit);
       List<ReaderInfo> readerInfoList = readerInfoMapper.queryAllReaderInfo(readerInfo);
       return new PageInfo<>(readerInfoList);
   }
   ```

   ```
   public ReaderInfo queryReaderInfoById(Integer id) {
       return readerInfoMapper.selectByPrimaryKey(id);
   }
   ```

   

4. `controller` 层：直接调用 `service` 层方法完成查询服务，调用工具类进行操作成功信息的返回

   ```
   @RequestMapping("/readerAll")
   @ResponseBody
   public DataInfo queryReaderAll(ReaderInfo readerInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit) {
       PageInfo<ReaderInfo> pageInfo = readerInfoService.queryAllReaderInfo(readerInfo, pageNum, limit);
       return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
   }
   ```

   下面这个是监听修改时前端调用，所以是一个页面跳转的形式

   ```
   @GetMapping("/queryReaderInfoById")
   public String queryReaderInfoById(Integer id, Model model) {
       ReaderInfo readerInfo = readerInfoService.queryReaderInfoById(id);
       model.addAttribute("info", readerInfo);
       return "reader/updateReader";
   }
   ```



### 三、员工操作

#### （一）概述

1. 后端部分与 reader 几乎一致，主要涉及到前端部分的改动，主要是 `init.json` 文件的变动
2. 这个过程中遇到了一些问题，变动不生效，下面是解决问题中的记录



#### （二）问题记录

**问题描述：添加了工作人员的如下信息，但在登录后的左侧边栏没有成功出现对应员工管理选项卡**

1. 后端：数据库 + `pojo` + 数据层 + 服务层 + 控制层
2. 前端：`init.json` + WEB-INF/pages/worker/*.jsp



**最终结果：后面都可以不看，成果是：改动源码时改动不生效如何处理**

1. 最快：删除编译生成的 target 文件夹，再重新运行即可

2. （==**慎用**==）上一步没有用，在确保不是数据库出问题的基础上，进行如下操作：

   把项目换一个路径（记得更改`generatorConfig.xml`里的路径），删除`.idea/` 文件夹（关闭项目后再删，确保删干净），重新部署一遍Tomcat，然后重新编译运行即可

3. 还没有用的话（应急处理）：直接换一个文件，copy过来改个名字



##### 第一次尝试

**1.在 `index.jsp` 中**

1. 使用 `init.json` 初始化 options，以其为参数调用 `miniAdmin.render(options)`



**2.在 `miniAdmin.js` 中**

1. 在 `$.getJSON` 回调中调用了 `miniMenu.render()`，其 `menuList` 参数为 `data.menuInfo`
2. data 即为 `init.json` 中的信息，对的上，且是 `json` 格式的使用方式



**3.在 `miniMenu.js` 中**

1. 25 行分支，选择调用 `renderMultiModule()` 还是 `renderSingleModule()`
2. 两者都是调用的 `renderLeftMenu()` 进行渲染 45&142行
3. `renderLeftMenu()` -> 遍历左侧菜单列表，使用`laytpl`模板引擎渲染左侧菜单项的HTML代码并返回（其内调用`renderChildrenMenu()`遍历处理子菜单项）



**4.总结：**

1. 到这就调用结束了，这几个全是遍历渲染格式化，半点没动数据
2. 唯一输入数据的地方就是 jsp 里调用的 `init.json`，**所以为啥改了没用啊？？？** -> 问题可能不再这里



**5.第三次尝试未果回来**

1. 又尝试了删除 target 文件夹重新编译，改的内容依旧没有生效，==感觉被写死了，并不是从 `init.json `读取的信息==
2. 我把读入这个 `json` 的代码给删了，结果报错了，交换的话，读的确实是另一个文件的信息，那么就是确实读了，不过读的位置不是我改的位置
3. 是这个绝对路径的问题`${pageContext.request.contextPath}`，导致我更改的不是他使用的，至于发生这个问题的原因是：**我把这个最初版的项目和另一个版本的项目放在了一个路径，导致了路径混乱**
4. 我把项目换了一个路径，删除了`.idea/` 文件夹，重新部署了一遍Tomcat，改了`generatorConfig.xml`里的路径，然后重新编译运行
5. **改动生效了，成功**



##### 第二次尝试

**1.服务层&控制层**

1. 发现 `ReaderInfoServiceImpl` 的部分方法被使用次数更多
2. 一个个对比后发现一方面是控制层借书时调用，暂不考虑员工的借书问题
3. 另一方面是控制层修改密码的调用，这个不影响，后续能跑起来了可以考虑添加



**2.总结**

1. 在后端代码中发现了一些可完善的功能，但与问题不相关



##### 第三次尝试

**1.跑起来以后 `f12` 观察前后端交互**

1. 发现点击左侧边栏以后，返回一个 index，其内容为对应的 jsp 文件，于是考虑对比几个 jsp 的区别
2. 对比后发现除了因为字段不同导致的不同处理外并无其他区别，也就是说这发送 jsp 文件的响应控制不在 jsp 文件之中



**2.寻找 jsp 文件是谁控制发到前端的 -> 左侧边栏选项卡的点击事件是谁监听的**

1. 发现是控制层做的响应，传输对应 jsp 文件，那么问题来了，控制层代码注册到 Web 服务器 Tomcat 中，是前端一个事件触发了控制层代码的响应，寻找这个事件是谁监听处理的（应该就是左侧边栏选项卡的点击事件）
2. 首先，不会在几个 jsp 文件里，因为没有监听侧边栏点击的，由于鼠标停在左侧边栏选项卡上时左下角显示`javascript:;`字样，应该是触发了一个 js 文件
3. 尝试修改 `miniMenu.js` 第57行的 `javascript:;`，改了以后发现运行时左下角确实变了，而且无法识别而爆红，对比了一下少了一个 http://localhost:8080/library_system_master_war_exploded/lib/layui-v2.5.5/css/modules/layer/default/loading-0.gif ，没啥用
4. 然后查了一下，获得如下结论：`href=‘javascript:;’`代表的是发生动作时执行一段`javascript`代码，但是这个代码是空的，所以什么也不执行（用它的原因：`<a>` 标签中必须提供 `href` 属性，但是这个就出现了一个问题，如果我不想让他跳转，那么这个时候`href`应该怎么赋值。）



**3.依旧没找到事件监听在哪里，终止，回到第一次尝试，问题已解决，只是因为操作问题出的 bug**



