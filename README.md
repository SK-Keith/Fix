# 简介
这是一个供小白简单入门J2EE的MVC模式实践学习的校园报修系统

# 快速上手
## 1.运行环境
* IDE:Eclipse
* 数据库:MySql
* Tomcat:Tomcat 7.0
## 2.部署项目
* 1. 新建一个Dynamic Web Project,取名为Fix
* 2. 复制项目目录下的src、WebContent文件夹内容
* 3. 粘帖进项目后，打开WebContent->WEB-INF->lib，将里面的所有包右击加进java运行环境中
* 4. 将项目中的sql 文件导进MySql 中，这里的数据库连接用户名为root，密码为password，新建的数据库名为：bx
* 5. 在浏览器中打开localhost:8080/Fix,即可访问用户页面
* 6. 用户名：HZU  密码：111
* 7. 打开localhost:8080/Fix/adminjsps/login.jsp，即可访问管理员页面
* 8. 超级管理员账户 用户名：sb@163.com 密码：sbadmin
* 9. 维修员 张师傅 账户 用户名：zhang@163.com 密码：zh
* 10.维修员 李师傅 账户 用户名：li@163.com 密码：li

# 目前实现的功能有：
* 1. 用户-管理员 登录及退出
* 2. 用户上传报修表单
* 3. 用户查看自己的报修表单
* 4. 用户修改自己的资料
* 5. 维修员查看未修复、已修复、暂缓修复、超时修复的表单信息，以及对相应表单进行提交完成，撤回已完成操作
* 5. 管理员查看未修复、已修复、暂缓修复、超时修复的表单信息，以及对相应信息进行修改删除操作
* 6. 管理员增删管理员、删除用户功能
* 7. 管理员与维修员通过页面留言墙进行交流功能
* 8. 管理员与维修员通过评价墙进行查看用户评价

# 图片展示效果
用户登录页面
[![用户登录]](https://github.com/SK-Keith/Fix/blob/master/example/images/1.png)
[用户登录]:https://github.com/SK-Keith/Fix/blob/master/example/images/1.png






