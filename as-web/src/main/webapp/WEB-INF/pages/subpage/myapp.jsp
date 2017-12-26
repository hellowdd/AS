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
    <title>应用商店</title>
    <META HTTP-EQUIV="content-type" CONTENT="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/sea-modules/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/font/iconfont.css" rel="stylesheet" type="text/css" />
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
	
    <div class="as-content">
    	<div class='slideshow'>
    		<h4 class="bg-primary"><i class="glyphicon glyphicon-th-list"></i>我的应用 <span class="glyphicon glyphicon-chevron-up pull-right"></span></h4>
	        <div class="pull-container mart_20">
	<!--            <div class="row" id="main_app">
	            </div>  -->
	             <iframe frameborder="0" class="layout-frame" src="${pageContext.request.contextPath}/manager/view/deploy/myapplist1" name="indexFrameN" id="indexFrameN"></iframe>
	        </div>
    	</div>
    	<div class='slideshow'>
    		<h4 class="bg-primary myStore"><i class="glyphicon glyphicon-th-list"></i>我的收藏  <span class="glyphicon glyphicon-chevron-down pull-right"></span></h4>
	        <div class="pull-container">
	            <iframe frameborder="0" class="layout-frame" src="" name="indexFrameS" id="indexFrameS"></iframe>
	        </div>
    	</div>
    	<div class='slideshow'>
    		<h4 class="bg-primary"><i class="glyphicon glyphicon-th-list"></i>我的申请 <span class="glyphicon glyphicon-chevron-down pull-right"></span></h4>
	        <div class="pull-container mart_20">
  				<iframe frameborder="0" class="layout-frame" src="" name="indexFrameA" id="indexFrameA"></iframe>
	        </div>
    	</div>
    	<div class='slideshow'>
    		<h4 class="bg-primary"><i class="glyphicon glyphicon-th-list"></i>我的评价 <span class="glyphicon glyphicon-chevron-down pull-right"></span></h4>
	        <div class="pull-container mart_20">
  				<iframe frameborder="0" class="layout-frame" src="" name="indexFrameE" id="indexFrameE"></iframe>
	        </div>
    	</div>    	
    </div>

</body>
<script type="text/javascript">
var viewRecord = [];
var store = [];
//用于搜索数据编码的关键字，可搜索数据编码名称及表名
var searchParam = "";
var linkObj = {};
var isFd = 1;
linkObj["myapp"] = "${pageContext.request.contextPath}/manager/view/deploy/myapplist";
linkObj["mystore"] = "${pageContext.request.contextPath}/manager/view/deploy/storelist";      
linkObj["myapply"] = "${pageContext.request.contextPath}/manager/view/deploy/applyList";
linkObj["myEva"] = "${pageContext.request.contextPath}/manager/view/deploy/evaList";
//判断是否是ie8或ie9
var isIe89 = (navigator.appName == "Microsoft Internet Explorer" && (navigator.appVersion.match(/8./i) == "8." || navigator.appVersion.match(/9./i) == "9.")) ? true : false;
$(document).ready(function() {
    //queryRankA()
    //收藏

    $(".bg-primary").siblings().hide();
    $(".bg-primary").eq(0).siblings().show();
/*     $(".bg-primary").children('span').removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
    $(".bg-primary").eq(0).siblings().children('span').children('span').addClass("glyphicon-chevron-up").removeClass("glyphicon-chevron-down"); */
    
    $(".bg-primary").click(function(){
    	if($(this).siblings().css('display')== 'none'){
    		$(this).siblings().slideDown();
    		$(this).children('span').addClass("glyphicon-chevron-up").removeClass("glyphicon-chevron-down");
    		
    	}else{
    		$(this).siblings().slideUp();
    		$(this).children('span').removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
    	}
    })
    var domFrameS = document.getElementById("indexFrameS");
    	domFrameS.src= linkObj['mystore'];
    var domFrameA = document.getElementById("indexFrameA");
    	domFrameA.src= linkObj['myapply'];
    var domFrameE = document.getElementById("indexFrameE");
    	domFrameE.src= linkObj['myEva'];

    $("#more").click(function() {
        $("#main_app").find('.hid').show();
        $(this).parent().parent().hide();
    })

});
function queryRankA() {
    $.ajax({
        dataType: "json",
        cache: false,
        url: "${pageContext.request.contextPath}/manager/rest/myApp/getMyApp",
        success: function(res) {
            if (res.success) {
                $("#main_app a").unbind("click");
                $("#main_app").empty();
                //  pageNum = res.page.pageNum; 
                store = res.data;
                var innerStr = "";
                for (var i = 0; i < res.data.length; i++) {    
                   	if(res.data[i].logoApp == null){
                		res.data[i].logoApp = '${pageContext.request.contextPath}/image/normal.png';
                	}
                	if(res.data[i].logoApp == ''){
                		res.data[i].logoApp = '${pageContext.request.contextPath}/image/normal.png';
                	}
                	if( res.data[i].appScore == 'null' || res.data[i].appScore == null){
                        if (i < 5) {
                            innerStr += "<div class='col-md-2 col-xs-3' id=" + res.data[i].id + "><div class='app'>" +
                            "   <div class='imgdiv'><div class='borimg'><img src='" + res.data[i].logoApp + "' width='100' height='40'></div><a href='javascript:void(0)' id=" + res.data[i].id + "><i class='iconfont icon-icon2'></i>详情</a><p>" + "点击量:" + res.data[i].clikeRate + "</p></div>" +
                            "   <div class='tac' title='"+res.data[i].appName+"'><h6>" + res.data[i].appName + "" +
                            "  </h6> <h6>(" + res.data[i].roleName + 
                            "  )</h6><div class='appbtn mar_16'><a href='javascript:void(0)' class='marl_10'><i class='glyphicon glyphicon-edit'></i>评价</a></div>" + 
                            /*  "  </h6> <div class='appbtn'><a href='javascript:void(0)' class='marl_10'></a></div>" + */
                                "</div></div></div>";
                        } else if (i == 5) {
                            innerStr += "<div class='col-md-2 col-xs-3' id='more'><div class='app'>" +
                            "<img src='${pageContext.request.contextPath}/image/more.png'>"+
                                "   " +
                                "</div></div>";
                        } else if (i > 5) {
                            innerStr += "<div class='col-md-2 col-xs-3 hid' id=" + res.data[i].id + "><div class='app'>" +
                            "   <div class='imgdiv'><div class='borimg'><img src='" + res.data[i].logoApp + "' width='100' height='40'></div><a href='javascript:void(0)' id=" + res.data[i].id + "><i class='iconfont icon-icon2'></i>详情</a><p>" + "点击量:" + res.data[i].clikeRate + "</p></div>" +
                            "   <div class='tac' title='"+res.data[i].appName+"'><h6>" + res.data[i].appName + 
                            "  </h6> <h6>(" + res.data[i].roleName + 
                            "  )</h6><div class='appbtn mar_16'><a href='javascript:void(0)' class='marl_10'><i class='glyphicon glyphicon-edit'></i>评价</a></div>" + 
                            /*  "  </h6> <div class='appbtn'><a href='javascript:void(0)' class='marl_10'></a></div>" + */
                                "</div></div></div>";
                        }                		
                	}else if(res.data[i].appScoreAdd == 'null' || res.data[i].appScoreAdd == null){
                        if (i < 5) {
                            innerStr += "<div class='col-md-2 col-xs-3' id=" + res.data[i].id + "><div class='app'>" +
                            "   <div class='imgdiv'><div class='borimg'><img src='" + res.data[i].logoApp + "'></div><a href='javascript:void(0)' id=" + res.data[i].id + "><i class='iconfont icon-icon2'></i>详情</a><p>" + "点击量:" + res.data[i].clikeRate + "</p></div>" +
                            "   <div class='tac' title='"+res.data[i].appName+"'><h6>" + res.data[i].appName + "" +
                            "  </h6> <h6>(" + res.data[i].roleName + 
                            "  )</h6><div class='appbtn mar_16'><a href='javascript:void(0)' class='marl_10'><i class='glyphicon glyphicon-edit'></i>追加评价</a></div>" + 
                            /*  "  </h6> <div class='appbtn'><a href='javascript:void(0)' class='marl_10'></a></div>" + */
                                "</div></div></div>";
                        } else if (i == 5) {
                            innerStr += "<div class='col-md-2 col-xs-3' id='more'><div class='app'>" +
                            "<img src='${pageContext.request.contextPath}/image/more.png'>"+
                                "   " +
                                "</div></div>";
                        } else if (i > 5) {
                            innerStr += "<div class='col-md-2 col-xs-3 hid' id=" + res.data[i].id + "><div class='app'>" +
                            "   <div class='imgdiv'><div class='borimg'><img src='" + res.data[i].logoApp + "'></div><a href='javascript:void(0)' id=" + res.data[i].id + "><i class='iconfont icon-icon2'></i>详情</a><p>" + "点击量:" + res.data[i].clikeRate + "</p></div>" +
                            "   <div class='tac' title='"+res.data[i].appName+"'><h6>" + res.data[i].appName + 
                            "  </h6> <h6>(" + res.data[i].roleName + 
                            "  )</h6><div class='appbtn mar_16'><a href='javascript:void(0)' class='marl_10'><i class='glyphicon glyphicon-edit'></i>追加评价</a></div>" + 
                            /*  "  </h6> <div class='appbtn'><a href='javascript:void(0)' class='marl_10'></a></div>" + */
                                "</div></div></div>";
                        }
                	}else{
                        if (i < 5) {
                            innerStr += "<div class='col-md-2 col-xs-3' id=" + res.data[i].id + "><div class='app'>" +
                            "   <div class='imgdiv'><div class='borimg'><img src='" + res.data[i].logoApp + "'></div><a href='javascript:void(0)' id=" + res.data[i].id + "><i class='iconfont icon-icon2'></i>详情</a><p>" + "点击量:" + res.data[i].clikeRate + "</p></div>" +
                            "   <div class='tac' title='"+res.data[i].appName+"'><h6>" + res.data[i].appName + "" +
                            "  </h6> <h6>(" + res.data[i].roleName + 
                            "  )</h6><div class='appbtn mar_16'><a href='javascript:void(0)' class='marl_10'><i class='glyphicon glyphicon-edit'></i>已追加评价</a></div>" + 
                            /*  "  </h6> <div class='appbtn'><a href='javascript:void(0)' class='marl_10'></a></div>" + */
                                "</div></div></div>";
                        } else if (i == 5) {
                            innerStr += "<div class='col-md-2 col-xs-3' id='more'><div class='app'>" +
                            "<img src='${pageContext.request.contextPath}/image/more.png'>"+
                                "   " +
                                "</div></div>";
                        } else if (i > 5) {
                            innerStr += "<div class='col-md-2 col-xs-3 hid' id=" + res.data[i].id + "><div class='app'>" +
                            "   <div class='imgdiv'><div class='borimg'><img src='" + res.data[i].logoApp + "'></div><a href='javascript:void(0)' id=" + res.data[i].id + "><i class='iconfont icon-icon2'></i>详情</a><p>" + "点击量:" + res.data[i].clikeRate + "</p></div>" +
                            "   <div class='tac' title='"+res.data[i].appName+"'><h6>" + res.data[i].appName + 
                            "  </h6> <h6>(" + res.data[i].roleName + 
                            "  )</h6><div class='appbtn mar_16'><a href='javascript:void(0)' class='marl_10'><i class='glyphicon glyphicon-edit'></i>已追加评价</a></div>" + 
                            /*  "  </h6> <div class='appbtn'><a href='javascript:void(0)' class='marl_10'></a></div>" + */
                                "</div></div></div>";
                        }                		
                	}

                }
                innerStr += "<div class='col-md-2 col-xs-3' id='moredown'><div class='app'>" +
                    "   <img src='${pageContext.request.contextPath}/image/moredown.png'>" +
                    "</div></div>";
                $("#main_app").append(innerStr);

                selIndexList = [];
                $("#moredown").hide();
                $("#more").click(function() {
                    $("#main_app").find('.hid').show();
                    $(this).hide();
                    $("#moredown").show();
                })
                $("#moredown").click(function() {
                        $("#main_app").find('.hid').hide();
                        $(this).hide();
                        $("#more").show();
                    })
                //绑定事件，父页面弹窗查看该数据编码的数据编码项
                $(".imgdiv").find("a").bind("click", function() {  
                	var ww = $(this).parent().parent().parent().parent().attr('id');
                    var rr = $(this).parent().parent().parent().index();
                    var id = viewRecord.id;
                    var rffr = $(this).parent().siblings().find('h6').html();
                    viewRecord = store[rr];
                    var layerId = layer.open({
                        type: 2,
                    	title: rffr+"详情",
                        area: ['600px', '550px'],
                        offset: 'auto',
                        content: ['${pageContext.request.contextPath}/manager/view/deploy/detail'+ '?' + id],
                        end: function() {
                            //window.parent.winList.pop();
                        	//queryRank();
                        	//window.location.reload();
                        	viewRecord = "";
                        }
                    });
                });                     
                $(".appbtn").find("a").bind("click", function() {
                    viewRecord = store[$(this).parent().parent().parent().parent().index()];
                    var id = viewRecord.id;
                   // var userName = ${sessionUserInfo.userName};
                    var reg = {
                        	favoriteId:"1",
                        	appId:viewRecord.appId,
                        	appName:viewRecord.appName,
                        	appVersion:viewRecord.appVersion,
                        	logoWeb:viewRecord.logoApp,
                        	remark:"",
                        	createByname:"${sessionUserInfo.userName}",
                        	createBy:${sessionUserInfo.userId}
                        };
                    if ($(this).text() == "评价" || $(this).text() == "追加评价") {
                        var layerId =layer.open({
                            type: 2,
                            title: '评价',
                            area: ['600px', '450px'],
                            offset: 'auto',
                            content: ['${pageContext.request.contextPath}/manager/view/deploy/evaluate' + '?' + id],
                            end: function() {
                                //window.parent.winList.pop();
                            	//queryRankA();
                            	//window.location.reload();
                            }
                        });
                    }else if($(this).text() == "已追加评价"){
	                    var layerId = layer.open({
	                        type: 2,
	                        title: '查看评价',
	                        area: ['600px', '450px'],
	                        offset: 'auto',
	                        content: ['${pageContext.request.contextPath}/manager/view/deploy/evaluate' + '?' + id],
	                        end: function() {
	                        }
	                    });                   	
                    }
                    
                    
                });
            } else {
                window.parent.layer.alert(res.message);
            }
        },
        error:   function(jqXHR,  textStatus,  errorThrown) { 
            if (textStatus  !==  "error") { 
                window.parent.layer.alert("获取数据失败！"); 
            } 
        }
    });
}

</script>


</html>
