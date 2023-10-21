<%--
  Created by IntelliJ IDEA.
  User:  陈鑫 兰文捷 徐鹏丰
  Date: 2023/7/24
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<html lang="cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>主界面</title>
    <link rel="stylesheet" href="bootstrap-3.4.1-dist/bootstrap-3.4.1-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="./iconfont/font_4215393_cv6a51352gj/iconfont.css">
    <link rel="stylesheet" href="http://at.alicdn.com/t/c/font_4144272_3vtq4renm6e.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="./js/jquery-validation-1.19.5/lib/jquery.js"></script>
    <script src="js/main.js"></script>
</head>
<body>
<video autoplay muted loop src="./mp4/video (360p).mp4"></video>
<header>
    <h1 id="currentUsN" style="display: none">${requestScope.currentUsername}</h1>
<%--    <h1 id="currentUsN" style="display: none">test</h1>--%>
    <div class="head">
        <h2>超市管理系统</h2>
        <div class="frame">
            <ul class="list">
                <li class="act home"><i class="iconfont icon-home"></i><span>首页</span></li>
                <li class="sendMessage"><i class="iconfont icon-envelope"></i><span>消息</span></li>
                <li class="receiveMessage"><i class="iconfont icon-comment"></i><span>收件箱</span></li>
                <li class="userInfo"><i class="iconfont icon-user"></i><span>用户信息</span></li>
            </ul>
        </div>
    </div>
</header>
<nav>
    <div class="nav">
        <button class="down"><i class="iconfont icon-user"></i><span>用户管理</span><i class="iconfont icon-a-xiala2"></i></button>
        <div class="a-container">
            <a class="a-user" href="#" style="font-size: 16px;"><span>用户信息</span></a>
            <a class="a-other"  href="#" style="font-size: 16px;"><span>其他信息</span></a>
        </div>
        <a href="#" class="b"><i class="iconfont icon-tubiaozhutu"></i><span>销售管理</span></a>
        <a href="#" class="d"><i class="iconfont icon-anquanyinsi"></i><span>货物细则</span></a>
        <a href="goodManage.jsp" class="c"><i class="iconfont icon-cangchucangku"></i><span>库存管理</span></a>
    </div>
</nav>
<section>
    <div class="user">
        <div class="up">
            <div class="s1"><i>搜索</i></div>

            <div class="s2">
                &nbsp;&nbsp;
                <span><strong>搜索项目：</strong></span>
                <select name="" id="userSearchType">
                    <option value="userInfoUsN">用户名</option>
                    <option value="userInfoRN">真实姓名</option>
                    <option value="userInfoT">电话号码</option>
                </select>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <span><strong>搜索关键词：</strong></span>
                <input type="text" class="searchInput">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <button class="btn1"><i class="glyphicon glyphicon-search"></i>开始搜索</button>
                &nbsp;&nbsp;&nbsp;&nbsp;

            </div>

            <div class="user-data">
                <table class="userInforTable" border="0">
                    <tr>
                        <th>用户名</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>联系电话</th>
                        <th>操作</th>
                    </tr>

                    <sql:setDataSource var="usInforDateSource" driver="com.mysql.cj.jdbc.Driver"
                                       url="jdbc:mysql://81.70.187.168:3306/webHomework"
                                       user="root" password="ncu111" />

                    <sql:query dataSource="${usInforDateSource}" var="userInforSet">
                        SELECT  * FROM  userInformation;
                    </sql:query>

                        <c:forEach items="${userInforSet.rows}" var="userInfor">
                         <tr class="userInfoTr">
                                <td class="userInfoUsN">${userInfor.userName}</td>
                                <td class="userInfoRN">${userInfor.name}</td>
                                <td class="userInfoG"> ${userInfor.gender}</td>
                                <td class="userInfoT">${userInfor.telephone}</td>
                                <td>
                                    <button class="modifyButton" style="margin-right: 15px;height: 25px;width: 45px;">修改</button>
                                    <button class="deleteButton" style="height: 25px;width: 45px;">删除</button>
                                </td>
                        </tr>
                        </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <div class="other">
        <div class="other-data">
            <table border="0">
                <tr>
                    <th>用户名</th>
                    <th>登录时间</th>
                    <th>登录ip地址</th>
                    <th>ip归属地</th>
                </tr>

                <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                                   url="jdbc:mysql://81.70.187.168:3306/webHomework"
                                   user="root" password="ncu111" />

                <sql:query dataSource="${dataSource}" var="loginRecordList">
                    SELECT  * FROM  loginRecord;
                </sql:query>

                <c:forEach items="${loginRecordList.rows}" var="loginRecord">
                    <tr class="loginTr">
                        <td class="userName">${loginRecord.userName}</td>
                        <td class="loginTime">${loginRecord.loginTime}</td>
                        <td class="loginIp">${loginRecord.loginIP}</td>
                        <td class="address">${loginRecord.address}</td>
                        <td>
                        <button class="deleteButton" style="height: 25px;width: 45px;">删除</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <div class="sale">
        <div class="s1"><i>搜索</i></div>
        <div class="s2">
            &nbsp;&nbsp;
            <span><strong>搜索项目：</strong></span>
            <select name="" id="saleSelect">
                <option value="saleId">id</option>
                <option value="saleName">商品名</option>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <span><strong>搜索关键词：</strong></span>
            <input type="text" class="searchInput">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button class="btn1"><i class="glyphicon glyphicon-search"></i>开始搜索</button>
            &nbsp;&nbsp;&nbsp;&nbsp;
        </div>

        <div class="saledata">

            <table class="saletable" border="0">
                <tr>
                    <th>ID</th>
                    <th>商品名称</th>
                    <th>单价/元</th>
                    <th>库存</th>
                    <th style="padding-left: 25px;">数量</th>
                </tr>
                <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                                   url="jdbc:mysql://81.70.187.168:3306/webHomework"
                                   user="root" password="ncu111" />

                <sql:query dataSource="${dataSource}" var="goodInfoList">
                    SELECT  * FROM  goodInfo ORDER BY id ASC;
                </sql:query>

                <c:forEach items="${goodInfoList.rows}" var="goodInfo">
                    <tr class="saleTr">
                        <td class="saleId">${goodInfo.id}</td>
                        <td class="saleName">${goodInfo.goodName}</td>
                        <td class="price">${goodInfo.sellPrice}</td>
                        <td class="inventory">${goodInfo.goodNum}</td>
                        <td>
                            <i class="iconfont icon-minus"></i>
                            <input class="number" type="text"  min="0" value="0" disabled style="background-color: white;">
                            <i class="iconfont icon-add"></i>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <div class="settlement">
                <div class="ww">
                    <div id="calculate">结算</div>
                    <div class="price-sum">
                        总价：￥<span class="sum-price">0</span>
                    </div>
                </div>

                <div class="submit">
                    <button class="pay">支付</button>
                </div>
            </div>
        </div>
    </div>

    <div class="goods-rule">
        <div class="other-data">
            <table border="0">
                <tr>
                    <th>id</th>
                    <th>商品名</th>
                    <th>生产厂家</th>
                    <th>变化数目</th>
                    <th>时间</th>
                </tr>

                <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                                   url="jdbc:mysql://81.70.187.168:3306/webHomework"
                                   user="root" password="ncu111" />

                <sql:query dataSource="${dataSource}" var="goodRecordList">
                    SELECT  * FROM  goodRecord;
                </sql:query>

                <c:forEach items="${goodRecordList.rows}" var="goodRecord">
                    <tr class="goodReTr">
                        <td class="id">${goodRecord.id}</td>
                        <td class="goodName">${goodRecord.goodName}</td>
                        <td class="manufacturer">${goodRecord.manufacturer}</td>
                        <td class="changeNum">${goodRecord.changeNum}</td>
                        <td class="changeTime">${goodRecord.changeTime}</td>
                        <td>
                            <button class="deleteButton" style="height: 25px;width: 45px;">删除</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <div class="popupWindow popWinHidden">
        <div class="popBox">
            <div class="userInfoBox">
                <div class="closeButton">&#10005;</div>
                <table class="userInforTable" border="0">
                    <tr>
                        <th>&nbsp&nbsp</th>
                        <th>&nbsp&nbsp</th>
                    </tr>
                    <tr>
                        <td>用户名：</td>
                        <td class="userInfoUN"><input type="text" style="color: white" disabled></td>
                    </tr>
                    <tr>
                        <td>姓名：</td>
                        <td class="userInfoRealNa"><input type="text" style="color: white" disabled></td>
                    </tr>
                    <tr>
                        <td>性别：</td>
                        <td class="userInfoSex"><input type="text" style="color: white" disabled></td>
                    </tr>
                    <tr>
                        <td>联系电话：</td>
                        <td class="userInfoPhone"><input type="text" style="color: white" disabled></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <button class="edit" style="height: 25px;width: 45px;">编辑</button>
                            <button class="confirmMod" style="height: 25px;width: 90px;">确认修改</button>
                            <button class="editpsw" style="width: 90px;height: 25px;">修改密码</button>
                        </td>
                    </tr>
                </table>
                <table class="pswtable" border="0">
                    <tr>
                        <th></th>
                        <th></th>
                    </tr>
                    <tr>
                        <td>旧密码：</td>
                        <td class="oldPW"><input type="text" style="color: white" ></td>
                    </tr>
                    <tr>
                        <td>新密码：</td>
                        <td class="newPW"><input type="text" style="color: white" ></td>
                    </tr>
                    <tr>
                        <td nowrap="nowrap">确认新密码：</td>
                        <td class="reNewPW"><input type="text" style="color: white" ></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <button class="submit" style="height: 25px;width: 45px;">确认</button>
                        </td>
                    </tr>
                </table>
            </div>

            <div class="sendMessageBox">
                <div class="closeButton">&#10005;</div>
                <div  style="left: 28px;
                    top: 70px;
                    color: white;
                    position: absolute;
                    color: rgb(30, 212, 203);">
                    <label for="recipient">选择收件人：</label>
                    <select id="recipient" name="recipient"></select>
                </div>

                <textarea id="message" name="message" rows="10" cols="80" style=
                        "resize: none;
                    position: absolute;
                    left: 25px;
                    top: 125px;
                    background-color: rgb(255, 250, 240);
                    border-radius: 10px;
                    font-size: 15px;"></textarea>

                <button  id="submitButton" style="position: absolute;
                    right: 80px;
                    bottom:150px ;
                    background: rgb(30, 212, 203);
                    width: 160px;
                    padding: 4px 0;
                    font-weight: bolder;
                    border-radius: 8px;
                    ">发送消息</button>
            </div>

            <div class="receiveMessBox" id="messageContainer">
                <div class="closeButton">&#10005;</div>
            </div>
        </div>
    </div>
</section>
</body>

<script>

    //将相关的元素选中
    const lis = document.querySelectorAll('.list li');
    lis.forEach(li => {
        //将选中的元素添加监视器
        li.addEventListener('click' ,function (){
            lis.forEach(item => {
                //如果选中则将选中的元素加为act类当中 其余元素全部从act类中移除
                item.classList.remove('act');
                this.classList.add('act');
            })
        })
    })


    const spanList = document.querySelectorAll('.nav .a-container span');
    var downButton = document.querySelector('.down'); // 获取下拉按钮
    var container = document.querySelector('.a-container'); // 获取内容容器
    var isOpen = false; // 记录下拉栏状态，默认为关闭

    // 初始状态下隐藏下拉栏
    container.style.display = 'none';

    downButton.addEventListener('click', function() {

        if (!isOpen) {
            // 展开下拉栏
            setTimeout(function () {
                spanList.forEach(item => {
                    //如果选中则将选中的元素加为act类当中
                    item.classList.add('act');
                })
                container.style.display = 'block';
                container.style.height = '0';
                container.offsetHeight;
                container.style.height = container.scrollHeight + 'px'; // 使用scrollHeight获取实际高度
                isOpen = true;
            }, 10);
        } else {
            setTimeout(function () {
                container.style.height = '0px';
                container.style.display = 'none';
            }, 10);
            spanList.forEach(item => {
                //如果选中则将选中的元素加为act类当中
                item.classList.remove('act');
            })
            isOpen = false;
        }
    });

    var increment = document.getElementsByClassName("icon-add");
    var price = document.getElementsByClassName("price");
    for (var i = 0; i < increment.length; i++) {
        //为a标签添加index属性，用于记录下标
        increment[i].index = i;
        price[i].index = i;
        //点击+数量增加的功能函数
        increment[i].onclick = function () {
            var flag = this.index;
            //获取当前按钮对应的数量框
            var q = document.getElementsByClassName("number")[flag];
            var kun_cun =  parseInt(document.getElementsByClassName("inventory")[flag].textContent);
            if (kun_cun > 0) {
                var newKC = --kun_cun;
                document.getElementsByClassName("inventory")[flag].textContent = newKC;

                var newvalue = parseInt(q.value) + 1;

                q.setAttribute('value', newvalue);
                var m = parseFloat(price[flag].innerHTML);
                var sum = m + parseFloat(document.getElementsByClassName("sum-price")[0].innerHTML);
                document.getElementsByClassName("sum-price")[0].innerHTML = sum.toFixed(2);;
            }
        }
    }

    //获取所有-按钮
    var increment = document.getElementsByClassName("icon-minus");
    var price = document.getElementsByClassName("price");
    for (var i = 0; i < increment.length; i++) {
        //为a标签添加index属性，用于记录下标
        increment[i].index = i;
        price[i].index = i;
        //点击-数量增加的功能函数
        increment[i].onclick = function () {
            var flag = this.index;

            //获取当前按钮对应的数量框
            var q = document.getElementsByClassName("number")[flag];
            var kun_cun =  parseInt(document.getElementsByClassName("inventory")[flag].textContent);
            var newKC = ++kun_cun;
            var num = parseInt(q.value);

            if (num > 0) {
                var newvalue = parseInt(q.value) - 1;
                var m = parseFloat(price[flag].innerHTML);
                var sum = parseFloat(document.getElementsByClassName("sum-price")[0].innerHTML) - m;
                document.getElementsByClassName("sum-price")[0].innerHTML = sum.toFixed(2);;
                q.setAttribute('value', newvalue);
                document.getElementsByClassName("inventory")[flag].textContent = newKC;
            }
        }
    }
</script>
</html>
