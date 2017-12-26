<%@page import="com.bocom.dto.UserRoleInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.bocom.dto.SessionUserInfo"%>
<%@page import="org.springframework.util.CollectionUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>应用商店</title>
    <META HTTP-EQUIV="content-type" CONTENT="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/sea-modules/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/font/iconfont.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/image/favicon1.ico" />
    <script src="${pageContext.request.contextPath}/sea-modules/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/sea-modules/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/sea-modules/layer/layer.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/module.js"></script>
    <!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/sea-modules/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath}/sea-modules/respond.min.js"></script>
    <![endif]-->
</head>

<body>	
    <div class="as-mainContainer" id="mainContainer">
            <div class="tab" role="tabpanel">
                <ul class="nav nav-tabs" role="tablist">
                    <li class="active" id="nav_rank"><a>应用排行榜</a></li>
                    <li id="nav_newapp"><a>新应用展示</a></li>
                    <li id="nav_myapp"><a>我的应用空间</a></li>
<!--                     <li id="nav_mystore"><a>我的收藏</a></li>
                    <li id="nav_myapply"><a>我的申请</a></li>
                    <li id="nav_myEva"><a>我的评价</a></li>  --> 
                    <li id="nav_myappro"><a>我的审批</a></li>
                </ul>

            
            </div>

        <div class="mainContainer" id="mainContainer">
            <iframe frameborder="0" class="layout-frame" src="rank.html" name="indexFrame" id="indexFrame"></iframe>
        </div>        
    </div>
</body>
<script type="text/javascript">
    var linkFlag = (window.location.href).split("#").length == 2 ? (window.location.href).split("#")[1] : "";
    var domFrame = document.getElementById("indexFrame");
    var linkObj = {};
	
    linkObj["rank"] = "${pageContext.request.contextPath}/manager/view/deploy/ranklist";
    linkObj["newapp"] = "${pageContext.request.contextPath}/manager/view/deploy/newapplist";  
    linkObj["myapp"] = "${pageContext.request.contextPath}/manager/view/deploy/myapplist";
    
/*     linkObj["mystore"] = "${pageContext.request.contextPath}/manager/view/deploy/storelist";      
    linkObj["myapply"] = "${pageContext.request.contextPath}/manager/view/deploy/applyList";
    linkObj["myEva"] = "${pageContext.request.contextPath}/manager/view/deploy/evaList"; */
    
    linkObj["myappro"] = "${pageContext.request.contextPath}/manager/view/deploy/approveList";
    jQuery(document).ready(function() {
        $(".nav>li").click(function() {
            var flag = $(this).attr("id").split("_")[1];
            window.location.href = window.location.href.split("#")[0] + "#" + flag;
            var _this = $(this).index();
            $(this).addClass('active').siblings().removeClass('active');
        });
        linkFlag = (window.location.href).split("#").length == 2 ? (window.location.href).split("#")[1] : "";
        domFrame.src = linkObj['rank'];
        changeLink(linkFlag);

    });

    function closeTopWin() {
        layer.close(winList[winList.length - 1]);
    }
    window.onhashchange = function() {
        linkFlag = (window.location.href).split("#").length == 2 ? (window.location.href).split("#")[1] : "";
        changeLink(linkFlag);
    }
    function changeLink(flag) {  
        var i = 0;
        for(var key in linkObj){
            if(flag == key){
                domFrame.src = linkObj[flag];
                 getNav(i)
            }
            if(flag == 'rank' || flag == 'newapp'){
            	$(".form-inline").show();
            }else{
            	$(".form-inline").hide();
            }            
             i++;
        }
    }
    function getNav(i) {
        $(".nav>li").eq(i).addClass('active').siblings().removeClass('active');
    }
</script>

</html>
