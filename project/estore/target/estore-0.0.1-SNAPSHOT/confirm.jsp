<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>结算页面</title>
    <link rel="stylesheet" href="css/common.css"/>
    <link rel="stylesheet" href="css/icons.css"/>
    <link rel="stylesheet" href="css/table.css"/>
    <link rel="stylesheet" href="css/orderSure.css"/>
    <script type="text/javascript">

        function showAdres(sp) {
            var pa = document.getElementById("newAdres");
            console.log(pa.style['display']);
            if (pa.style['display'] == "none") {
                pa.style['display'] = 'block';
                sp.innerHTML = "取消新增地址";
            } else {
                pa.style['display'] = 'none';
                sp.innerHTML = "新增收货地址";
            }
        }
    </script>
</head>
<body>
<!--顶部-->
<div class="top">
    <div class="top_center">
        <ul class="top_bars">
            <%--根据是否登录 选择显示不一样的状态--%>
            <c:choose>
                <%--当从session可以获取到user时 表示用户已经登录--%>
                <c:when test="${!empty sessionScope.user}">
                    <li><a href="#">${sessionScope.user.name}</a>|</li>
                    <li><a href="logout">退出</a>|</li>
                </c:when>
                <c:otherwise>
                    <li><a href="login.jsp" style="color: red;">请登录</a>|</li>
                </c:otherwise>
            </c:choose>
            <li><a href="#">我的订单<span class="jt_down"></span></a>|</li>
            <li><a href="#">关注杰普<span class="jt_down"></span></a>|</li>
            <li><a href="#">网站导航<span class="jt_down"></span></a></li>
        </ul>
    </div>
</div>
<!--头部-->
<div class="header3">
    <a href="index.jsp"><img src="images/logo.png" class="oneImg"></a>


    <div class="h3_right">
        <img src="images/play_03.png" alt="">
    </div>

</div>
<p class="orderButtom">填写并核对订单信息</p>
<!--中间复杂部分-->
<div class="content">
    <div class="contentCenter">

        <form action="orderList" name="orderForm">

            <div class="centerTop">
                <b style="font-size:20px;">收货人信息</b>

                <b style="float: right;cursor: pointer;" onclick="showAdres(this);"
                   ondblclick="hideAdres(this);">新增收货地址</b>
                <ul class="adres">
                    <c:forEach items="${requestScope.addressList}" var="address">
                        <li>
                            <input type="radio" name="shipAddId" value="" style="width:50px">
                                ${address.receiveName}&nbsp;&nbsp;&nbsp;${address.phone}&nbsp;&nbsp;&nbsp;${address.address}
                        </li>
                    </c:forEach>
                </ul>

                <p id="newAdres" style="font-size:15px;display: none;">
                    收件人姓名：<input type="text" name="receiveName"><br/><br/>
                    收件人地址：<input type="text" name="address"><br/><br/>
                    收件人电话：<input type="text" name="phone">
                </p>
            </div>


            <p class="singleP"><b>送货清单</b><span><a href="shopCart.html">返回修改购物车</a></span></p>
            <div class="bigDiv">
                <div class="twoDiv">
                    <b>商家：briup自营</b>
                    <c:forEach items="${sessionScope.shopCar.orderLines}" var="orderLine">
                        <ul class="oneUL">
                            <li>
                                <img src="${orderLine.book.image}" class="pic">
                                <p>${orderLine.book.category.name}&nbsp;&nbsp;${orderLine.book.name}&nbsp;&nbsp;</p>
                                <p><font>¥${orderLine.book.price}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×${orderLine.num}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有货
                                </p>
                                <p><img src="images/sureLogo_14.png" alt=""><span>七天无理由退换货</span></p>

                            </li>
                            <li>
                                <pre>【赠品】  高级定制干花书签  随机  ×1</pre>
                            </li>
                        </ul>
                    </c:forEach>
                </div>
            </div>

            <div class="money">
                <span><font>${sessionScope.shopCar.size}</font>件商品，总商品金额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥${sessionScope.shopCar.cost}</span>
                <span><img src="images/sureLogo_18.png" alt="">运费：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥0.00</span>

                <span>应付总额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥${sessionScope.shopCar.cost}</span>
            </div>
            <div class="submit">
                <span>应付金额：<font>¥${sessionScope.shopCar.cost}</font><input type="image" src="images/21_03.png"></span>
            </div>
        </form>
    </div>

</div>
<!--脚部-->
<div class="footer3">
    <div class="f3_top">
        <div class="f3_center">
            <div class="ts1">品目繁多 愉悦购物</div>
            <div class="ts2">正品保障 天天低价</div>
            <div class="ts3">极速物流 特色定制</div>
            <div class="ts4">优质服务 以客为尊</div>
        </div>
    </div>
    <div class="f3_middle">
        <ul class="f3_center">
            <li class="f3_mi_li01">
                <h1>购物指南</h1>
                <p>常见问题</p>
                <p>找回密码</p>
                <p>会员介绍</p>
                <p>购物演示</p>
            </li>
            <li class="f3_mi_li01">
                <h1>配送方式</h1>
                <p>常见问题</p>
                <p>找回密码</p>
                <p>会员介绍</p>
                <p>购物演示</p>
            </li>
            <li class="f3_mi_li01">
                <h1>支付方式</h1>
                <p>常见问题</p>
                <p>找回密码</p>
                <p>会员介绍</p>
                <p>购物演示</p>
            </li>
            <li class="f3_mi_li01">
                <h1>售后服务</h1>
                <p>常见问题</p>
                <p>找回密码</p>
                <p>会员介绍</p>
                <p>购物演示</p>
            </li>
            <li class="f3_mi_li01">
                <h1>服务保障</h1>
                <p>常见问题</p>
                <p>找回密码</p>
                <p>会员介绍</p>
                <p>购物演示</p>
            </li>
            <li class="f3_mi_li06">
                <h1>客服中心</h1>
                <img src="images/qrcode_jprj.jpg" width="80px" height="80px">
                <p>抢红包、疑问咨询、优惠活动</p>
            </li>
        </ul>
    </div>
    <div class="f3_bottom">
        <p class="f3_links">
            <a href="#">关于我们</a>|
            <a href="#">联系我们</a>|
            <a href="#">友情链接</a>|
            <a href="#">供货商入驻</a>
        </p>
        <p>沪ICP备14033591号-8 杰普软件briup.com版权所有 杰普软件科技有限公司 </p>
        <img src="images/police.png">
    </div>
</div>
</body>
</html>