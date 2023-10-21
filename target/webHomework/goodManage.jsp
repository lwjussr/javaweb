<%--
  Created by IntelliJ IDEA.
  User:徐鹏丰 陈鑫 兰文捷
  Date: 2023/8/20
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<head>
    <title>数据可视化系统</title>
    <link rel="stylesheet" href="./iconfont/font_4215393_cv6a51352gj/iconfont.css">
    <link rel="stylesheet" href="bootstrap-3.4.1-dist/bootstrap-3.4.1-dist/css/bootstrap.min.css">
    <script src="js/echarts.min.js"></script>
    <script src="./js/jquery-validation-1.19.5/lib/jquery.js"></script>
    <script src="js/goodManage.js"></script>
    <link rel="stylesheet" href="css/goodManage.css">
</head>
<body>
<div id="body" class="body">
    <div class="div4">
        <div id="header" class="header">
            <header>
                <h1>后台可视化管理系统</h1>
            </header>
        </div>
    </div>

    <!-- 这是左侧导航栏 -->
    <div id="nav-main" class="nav-main ">
        <div id="nav">
            <nav>
                <div class="accordion">
                    <ul id="accordion" class="accordion">
                        <li>
                            <div class="link"><i class="fa fa-users"></i>登陆管理<i class="fa fa-chevron-down"></i></div>
                            <ul class="submenu">
                                <div class="submenu-box">
                                    <li class="user"><a href="javascript:void(0);"
                                           onclick="showMainPage('main-user-info');">用户登录信息</a>
                                    </li>
                                </div>
                                <div class="submenu-box">
                                    <li class="ip"><a href="javascript:void(0);"
                                           onclick="showMainPage('main-other-info')">ip信息</a>
                                    </li>
                                </div>
                            </ul>
                        </li>

                        <li>
                            <div class="link"><i class="fa fa-chart-bar"></i>销售管理<i
                                    class="fa fa-chevron-down"></i>
                            </div>

                            <ul class="submenu">
                                <div class="submenu-box">
                                    <li class="saleExplore"><a href="javascript:void(0);"
                                           onclick="showMainPage('main-sales-query')">最近销售浏览</a>
                                    </li>
                                </div>

                                <div class="submenu-box">
                                    <li><a href="javascript:void(0);"
                                           onclick="showMainPage('main-sales-all')">总销售浏览</a>
                                    </li>
                                </div>

                            </ul>
                        </li>

                        <li>
                            <div class="link"><i class="fa fa-chart-bar"></i>利润分析<i
                                    class="fa fa-chevron-down"></i>
                            </div>

                            <ul class="submenu">
                                <div class="submenu-box">
                                    <li class="pr"><a href="javascript:void(0);"
                                                      onclick="showMainPage('main-profit-fiveday')">显示利润表</a>
                                    </li>
                                </div>
                            </ul>
                        </li>

                        <li>
                            <div class="link"><i class="fa fa-box"></i>库存管理<i class="fa fa-chevron-down"></i>
                            </div>
                            <ul class="submenu">
                                <div class="submenu-box">
                                    <li class="kucunShujv"><a href="javascript:void(0);"
                                           onclick="showMainPage('kucunShujv')">库存数据分析</a>
                                    </li>
                                </div>
                                <div class="submenu-box">
                                    <li><a href="javascript:void(0);"
                                           onclick="showMainPage('modifykucun')">库存数据修改</a>
                                    </li>
                                </div>
                            </ul>
                        </li>

                    </ul>
                </div>
            </nav>
        </div>


        <div id="main" class="main">
            <main>
                <div id="main-user-info" class="main-content main-user-info">
                    <div id="userLoginAmout" class="userLoginAmout"></div>
                    <div id="getLoginTimeAmount" class="getLoginTimeAmount"></div>
                </div>

                <div id="main-user-info02" class="main-content main-user-info02">

                </div>

                <div id="main-other-info" class="main-content main-other-info">
                    <div id="loginIp" class="loginIp"></div>
                    <div id="Ipaddress" class="Ipaddress"></div>
                </div>


                <div id="main-profit-fiveday" class=" main-content main-profit-fiveday">
                    <div id="profitAmount"></div>
                    <div class="profittable">
                        <table>
                            <tr>
                                <td>日期</td>
                                <td>利润</td>
                            </tr>
                                <sql:setDataSource var="profitDateSource" driver="com.mysql.cj.jdbc.Driver"
                                                   url="jdbc:mysql://81.70.187.168:3306/webHomework"
                                                   user="root" password="ncu111" />

                                <sql:query dataSource="${profitDateSource}" var="profitSet">
                                    SELECT  * FROM  profit order by sellTime;
                                </sql:query>

                                <c:forEach items="${profitSet.rows}" var="profit">
                                    <tr>
                                        <td>${profit.sellTime}</td>
                                        <td>${profit.profit}</td>
                                    </tr>
                                </c:forEach>
                        </table>
                    </div>
                </div>


                <div id="kucunShujv" class=" main-content kucunShujv">
                    <div id="goodAmount"></div>
                    <span><strong>搜索商品名称：</strong></span>
                    <select name="" id="goodSelect">
                        <sql:setDataSource var="goodInfoDateSource" driver="com.mysql.cj.jdbc.Driver"
                                           url="jdbc:mysql://81.70.187.168:3306/webHomework"
                                           user="root" password="ncu111" />

                        <sql:query dataSource="${goodInfoDateSource}" var="goodInfoSet">
                            SELECT  * FROM  goodInfo order by id;
                        </sql:query>
                        <c:forEach items="${goodInfoSet.rows}" var="goodInfor">
                        <option value=${goodInfor.goodName}>${goodInfor.goodName}</option>
                        </c:forEach>
                    </select>
                    <button class="btn1"><i class="glyphicon glyphicon-search"></i>开始搜索</button>
                    <div class="goodSaleDay" id="goodSaleDay"></div>
                </div>

                <div id="modifykucun" class=" main-content modifykucun">
                    <div class="s1"><i>搜索</i></div>
                    <div class="s2">
                        &nbsp;&nbsp;
                        <span><strong>搜索项目：</strong></span>
                        <select name="" id="saleSelect">
                            <option value="id">id</option>
                        </select>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <span><strong>搜索关键词：</strong></span>
                        <input type="text" class="searchInput">
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <button class="btn1"><i class="glyphicon glyphicon-search"></i>开始搜索</button>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <button class="btn2"><i class="glyphicon glyphicon-plus"></i>添加数据</button>
                    </div>

                    <div class="goodsinfo">
                        <table class="goodsinfotable" border="0">
                            <tr>
                                <th>ID</th>
                                <th>商品名称</th>
                                <th>生产商</th>
                                <th>进价</th>
                                <th>售价</th>
                                <th>数量</th>
                            </tr>
                            <sql:setDataSource var="goodInfoDateSource" driver="com.mysql.cj.jdbc.Driver"
                                               url="jdbc:mysql://81.70.187.168:3306/webHomework"
                                               user="root" password="ncu111" />

                            <sql:query dataSource="${goodInfoDateSource}" var="goodInfoSet">
                                SELECT  * FROM  goodInfo order by id;
                            </sql:query>

                            <c:forEach items="${goodInfoSet.rows}" var="goodInfor">
                                <tr class="goodTr">
                                    <td class="id">${goodInfor.id}</td>
                                    <td class="first">
                                        <input  class="first goodName" type="text" value=${goodInfor.goodName} disabled>
                                    </td>
                                    <td>
                                        <input class="manufacturer" type="text" value=${goodInfor.manufacturer} disabled>
                                    </td>
                                    <td>
                                        <input class="buyPrice" type="text" value=${goodInfor.buyPrice} disabled>
                                    </td>
                                    <td>
                                        <input class="sellPrice" type="text" value=${goodInfor.sellPrice} disabled>
                                    </td>
                                    <td>
                                        <input class="goodNum" type="text" value=${goodInfor.goodNum} disabled>
                                    </td>
                                    <td>
                                        <button class="modifyButton" style="height: 25px;width: 45px;">修改</button>
                                        <button class="deleteButton" style="height: 25px;width: 45px;">删除</button>
                                        <button class="sure" style="height: 25px;width: 90px;">确认修改</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                    <div class="edit-sale">
                        <table border="0">
                            <tr>
                                <th></th>
                                <th></th>
                            </tr>
                            <tr>
                                <td>ID：</td>
                                <td><input class="id" type="text"></td>
                            </tr>
                            <tr>
                                <td>商品名称：</td>
                                <td><input class="goodName" type="text"></td>
                            </tr>
                            <tr>
                                <td>生产商：</td>
                                <td><input class="manufacturer" type="text"></td>
                            </tr>
                            <tr>
                                <td>进价：</td>
                                <td><input class="buyPrice" type="text"></td>
                            </tr>
                            <tr>
                                <td>售价：</td>
                                <td><input class="sellPrice" type="text"></td>
                            </tr>
                            <tr>
                                <td>数量：</td>
                                <td><input class="goodNum" type="text"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><button class="ok" style="height: 25px;width: 45px;">确认</button></td>
                            </tr>
                        </table>
                    </div>
                </div>


                <div id="main-log-query" class=" main-content main-log-query">

                </div>

                <div id="main-sales-query" class=" main-content main-sales-query">
                    <div id="dateSale" class="dateSale"></div>
                    <div id="goodSale" class="goodSale"></div>
                </div>

                <div id="main-sales-all" class=" main-content main-sales-all">
                    <div class="main-sales-table">
                        <table  border="0">
                            <tr>
                                <th>ID</th>
                                <th>商品名称</th>
                                <th>生产商</th>
                                <th>数量变动</th>
                                <th>变动日期</th>
                            </tr>
                            <sql:setDataSource var="goodRecordDateSource" driver="com.mysql.cj.jdbc.Driver"
                                               url="jdbc:mysql://81.70.187.168:3306/webHomework"
                                               user="root" password="ncu111" />

                            <sql:query dataSource="${goodRecordDateSource}" var="goodRecordSet">
                                SELECT  * FROM  goodRecord order by changeTime;
                            </sql:query>

                            <c:forEach items="${goodRecordSet.rows}" var="goodRecord">
                                <tr>
                                    <td>${goodRecord.id}</td>
                                    <td>${goodRecord.goodName}</td>
                                    <td>${goodRecord.manufacturer}</td>
                                    <td>${goodRecord.changeNum}</td>
                                    <td>${goodRecord.changeTime}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </main>
        </div>
    </div>
</div>

<script>
    // 让子菜单能够缓慢拉出
    const menuItems = document.querySelectorAll('.link');
    menuItems.forEach(item => {
        item.addEventListener('click', () => {
            const submenu = item.nextElementSibling;
            if (submenu.classList.contains('active')) {
                submenu.classList.remove('active', 'show');
            } else {
                submenu.classList.add('active');
                setTimeout(() => submenu.classList.add('show'), 10);
            }
        });
    });

    // 在点击子菜单选项时切换到对应主要内容页面
    function showMainPage(mainpageId) {
        // 隐藏所有主要内容界面子页面内容
        var pageContents = document.querySelectorAll('.main-content');
        for (var i = 0; i < pageContents.length; i++) {
            pageContents[i].style.display = 'none';
        }

        // 显示选中主要内容的页面内容
        var selectedPage = document.getElementById(mainpageId);
        selectedPage.style.display = 'block';
    };

    window.onload = function() {
        var defaultTab = document.getElementById("main-user-info");
        var allTabs = document.getElementsByClassName("main-content");

        for (var i = 0; i < allTabs.length; i++) {
            if (allTabs[i] === defaultTab) {
                allTabs[i].style.display = "block";
            } else {
                allTabs[i].style.display = "none";
            }
        }
    };

</script>



</body>
</html>

