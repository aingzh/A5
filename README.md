# 开发环境指南

## 安装VSCode
https://code.visualstudio.com/

## 安装 Nodejs

（也可以使用 docker，不使用 docker 的话用以下步骤）

安装 Nodejs： https://nodejs.org/en/

安装以后用以下命令进行测试：

```
node -v
```

正常情况下应该显示 nodejs 的版本号

之后安装 cnpm（可选，如果你的网络环境访问国外网站没有问题可以直接使用 npm）

```
npm install -g cnpm --registry=https://registry.npmmirror.com
```

Linux 下可能需要用 root 执行：

```
sudo npm install -g cnpm --registry=https://registry.npmmirror.com
```


## 开发语言

如果可以，请尽量使用Typescript进行开发，程序中也可以使用Javascript。

使用Typescript时，普通文件后缀名为.ts。使用Javascript时，普通文件名后缀为.js。

在.vue文件中，如果希望使用Typescript，可以在script标签中加上lang="ts"

```
<script lang="ts">
 //...在这里写Typescript

</script>

```

## 设置前端web模块

安装模块（cnpm 或者 npm 选择一种即可）

```
cnpm i
```

之后可以尝试启动

```
npm run serve
```

## 相关文档

* Markdown: Markdown是写文档的语法。可以参照 [Markdown语法指南](https://www.jianshu.com/p/1e402922ee32/)。Markdown文件后缀名是.md
* Vue: 我们使用的Vue 3。如果要学习，需要注意版本，Vue官方文档： [中文](https://v3.cn.vuejs.org/)， [英文](https://vuejs.org/)
* Element Plus: https://element-plus.gitee.io/zh-CN/
