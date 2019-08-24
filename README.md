<h2>J2EE校园报修系统</h2>
## 补充说明(2019.08.25更新)
* 为了弥补上面点Star的9位老铁，我专门对该项目进行了修改，但是整体框架不变，主要修复点罗列如下：
* 修改了编码utf-8显示中文问题
* 修改了路径jsp:forward 跳转失败问题
* 修改了页面JSTL之缺少 jstl1.2jar、standard.jar问题
* 修复了表名不一致问题
* 。。。

* 开发不足点：
写在前面:项目整体是前后端不分离的(前端jsp页面，后端原生servlet)，导致开发过程中重复性的语句较多，比如sql语句写在了dao层，区别于JPA框架的，不足点实在数不胜数。。。

# 简介
这是一个供小白简单入门J2EE的MVC模式实践学习的校园报修系统
系统展示：http://119.29.5.21:80/Fix/ （前端）   http://119.29.5.21:80/Fix/adminjsps/login.jsp (后台)

# 快速上手
## 1.运行环境
* IDE:Eclipse
* 数据库:MySql
* Tomcat:Tomcat 7.0
## 2.部署项目
### Eclipse部署
1. 新建一个Dynamic Web Project,取名为Fix
2. 复制项目目录下的src、WebContent文件夹内容
3. 粘帖进项目后，打开WebContent->WEB-INF->lib，将里面的所有包右击加进java运行环境中
4. 将项目中的sql 文件导进MySql 中，这里的数据库连接用户名为root，密码为password，新建的数据库名为：bx
5. 在浏览器中打开localhost:8080/Fix,即可访问用户页面
6. 用户名：ymx  密码：111
7. 打开localhost:8080/Fix/adminjsps/login.jsp，即可访问管理员页面
8. 超级管理员账户 用户名：sb@163.com 密码：sbadmin
9. 维修员 张师傅 账户 用户名：zhang@163.com 密码：zh  维修员 李师傅 账户 用户名：li@163.com 密码：li
### IDEA部署


# 目前实现的功能有：
1. 用户-管理员 登录及退出
2. 用户上传报修表单
3. 用户查看自己的报修表单
4. 用户修改自己的资料
5. 维修员查看未修复、已修复、暂缓修复、超时修复的表单信息，以及对相应表单进行提交完成，撤回已完成操作
5. 管理员查看未修复、已修复、暂缓修复、超时修复的表单信息，以及对相应信息进行修改删除操作
6. 管理员增删管理员、删除用户功能
7. 管理员与维修员通过页面留言墙进行交流功能
8. 管理员与维修员通过评价墙进行查看用户评价
9. 用户报修即时安排相应的师傅
10.对于超过24小时还未修复的表单，会自动归入超时表单

# 图片展示效果
* 用户登录页面
![用户登录](https://github.com/SK-Keith/Fix/blob/master/example/images/1.png)
* 报修页面
![用户报修](https://github.com/SK-Keith/Fix/blob/master/example/images/21.png)
* 用户查看报修单
![用户查看报单](https://github.com/SK-Keith/Fix/blob/master/example/images/22.png)
* 用户修改资料
![用户修改资料](https://github.com/SK-Keith/Fix/blob/master/example/images/4.png)
* 管理员登录页面

![管理员登录](https://github.com/SK-Keith/Fix/blob/master/example/images/5.png)
* 维修员首页
![管理员首页](https://github.com/SK-Keith/Fix/blob/master/example/images/6.png)
* 维修员查看未修复订单
![管理员未修复](https://github.com/SK-Keith/Fix/blob/master/example/images/7.png)
* 维修员修改资料
![管理员未修复](https://github.com/SK-Keith/Fix/blob/master/example/images/12.png)
* 管理员首页
![管理员未修复](https://github.com/SK-Keith/Fix/blob/master/example/images/13.png)
* 管理员查看未修复订单
![管理员未修复](https://github.com/SK-Keith/Fix/blob/master/example/images/14.png)
* 管理员进行用户管理
![管理员未修复](https://github.com/SK-Keith/Fix/blob/master/example/images/18.png)
* 管理员添加维修员
![管理员未修复](https://github.com/SK-Keith/Fix/blob/master/example/images/20.png)



