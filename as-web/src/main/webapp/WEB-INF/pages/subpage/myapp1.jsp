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
    <div class="">
        <div class="pull-container mart_20">
             <div class="row"  id="main_app">
             </div>
             <div class="pageContainer layout-page" id="pageContainer"></div>
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
var idSr = 1;
var modeShow;//是否展示
//判断是否是ie8或ie9
var isIe89 = (navigator.appName == "Microsoft Internet Explorer" && (navigator.appVersion.match(/8./i) == "8." || navigator.appVersion.match(/9./i) == "9.")) ? true : false;
$(document).ready(function() {
    queryRankA()


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
                store = res.data;
                var innerStr = "";
                for (var i = 0; i < res.data.length; i++) {    
                   	if(res.data[i].logoApp == null){
                		res.data[i].logoApp = '${pageContext.request.contextPath}/image/normal.png';
                	}
                	if(res.data[i].logoApp == ''){
                		res.data[i].logoApp = '${pageContext.request.contextPath}/image/normal.png';
                	}
                	var isSEva;
                	if( res.data[i].appScore == 'null' || res.data[i].appScore == null){
                		isSEva = '评价'
                	}else{
                		isSEva = '追加评价'
                	}
                    if (i < 5) {
                        innerStr += "<div class='col-md-2 col-xs-3' id=" + res.data[i].id + "><div class='app'>" +
                        "   <div class='imgdiv'><div class='borimg'><img src='" + res.data[i].logoApp + "' width='100' height='40'></div><a href='javascript:void(0)' id=" + res.data[i].id + "><i class='iconfont icon-icon2'></i>详情</a><p>" + "点击量:" + res.data[i].clikeRate + "</p></div>" +
                        "   <div class='tac' title='"+res.data[i].appName+"'><h6>" + res.data[i].appName + "" +
                        "  </h6> <h6>(" + res.data[i].roleName + 
                        "  )</h6><div class='appbtn mar_16'><a href='javascript:void(0)' class='marl_10'><i class='glyphicon glyphicon-edit'></i>"+isSEva+"</a></div>" + 
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
                        "  )</h6><div class='appbtn mar_16'><a href='javascript:void(0)' class='marl_10'><i class='glyphicon glyphicon-edit'></i>"+isSEva+"</a></div>" +                           
                            "</div></div></div>";
                    }                		
            	}

                innerStr += "<div class='col-md-2 col-xs-3' id='moredown'><div class='app'>" +
                    "   <img src='${pageContext.request.contextPath}/image/moredown.png'>" +
                    "</div></div>";
                $("#main_app").append(innerStr);
                
                $(window.parent.document).find("#indexFrameN").height($('#main_app').height()+40);
                $(window).resize(function(){
                	$(window.parent.document).find("#indexFrameN").height($('#main_app').height()+40);
                })
                
                selIndexList = [];
                $("#moredown").hide();
                
                if(modeShow == 1){
                	$("#main_app").find('.hid').show();
                	$("#more").hide();
                	$("#moredown").show();
                }
                
                $("#more").click(function() {
                    $("#main_app").find('.hid').show();
                    $(this).hide();
                    $("#moredown").show();
                    $(window.parent.document).find("#indexFrameN").height($('#main_app').height());
                    modeShow = 1;
                })
                
                $("#moredown").click(function() {
                        $("#main_app").find('.hid').hide();
                        $(this).hide();
                        $("#more").show();
                        $(window.parent.document).find("#indexFrameN").height($('#main_app').height());
                        modeShow = 2;
                    })
                //绑定事件，父页面弹窗查看该数据编码的数据编码项
                $(".imgdiv").find("a").bind("click", function() {  
                	var ww = $(this).parent().parent().parent().parent().attr('id');
                    var rr = $(this).parent().parent().parent().index();
                    var id = viewRecord.id;
                    var rffr = $(this).parent().siblings().find('h6').html();
                    viewRecord = store[rr];
                    var layerId = window.parent.layer.open({
                        type: 2,
                    	title: rffr+"详情",
                        area: ['650px', '600px'],
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
                    if ($(this).text() == "评价") {
                        var layerId =window.parent.layer.open({
                            type: 2,
                            title: '评价',
                            area: ['700px', '420px'],
                            offset: 'auto',
                            content: ['${pageContext.request.contextPath}/manager/view/deploy/evaluate' + '?' + id],
                            end: function() {
                            }
                        });
                    }else if($(this).text() == "追加评价"){
	                    var layerId = window.parent.layer.open({
	                        type: 2,
	                        title: '追加评价',
	                        area: ['700px', '580px'],
	                        offset: 'auto',
	                        content: ['${pageContext.request.contextPath}/manager/view/deploy/evaluateadd' + '?' + id],
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
