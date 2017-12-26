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
    <script src="${pageContext.request.contextPath}/sea-modules/laypage-v1.3/laypage/laypage.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/module.js"></script>
    <!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/sea-modules/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath}/sea-modules/respond.min.js"></script>
    <![endif]-->
</head>

<body>      
    <div class="">
        <div class="pull-container mart_20">
             <div class="row"  id="main_apps">
             </div>
             <div class="pageContainer layout-page" id="pageContainer"></div>
        </div>
    </div>
</body>
<script type="text/javascript">
var viewRecord =[];
var store = [];
var isFd = 3;
var idSr = 2;
var modeShow;//是否展示
//用于搜索数据编码的关键字，可搜索数据编码名称及表名
$(document).ready(function() {
     queryRank()    
});
function queryRank(curr){
      $.ajax({
        dataType: "json",
        cache: false,
        data:{
            pageNum: curr || 1, 
            userId: ${sessionUserInfo.userId},
            pageSize:18
            },
        url: "${pageContext.request.contextPath}/manager/rest/favorite/queryFavoriteList",
        success: function(res) {
            if (res.success) {
                $("#main_apps a").unbind("click");
                $(".imgdiv").find("a").unbind("click");
                $("#main_apps").empty();
                //  pageNum = res.page.pageNum; 
                store = res.page.list;
                var innerStr = "";
                if(res.page.list.length == 0){
                	innerStr = '';
                }
                var apple;
                for (var i = 0; i < res.page.list.length; i++) {
                   	if(res.page.list[i].logoWeb == null){
                   		res.page.list[i].logoWeb = "${pageContext.request.contextPath}/image/normal.png";
                	}
                   	if(res.page.list[i].logoWeb == ''){
                   		res.page.list[i].logoWeb = "${pageContext.request.contextPath}/image/normal.png";
                	}
                if(res.page.list[i].statusId == '0'){
                	apple = '申请中'
                  }else if(res.page.list[i].statusId == '1'){
                	  apple = ''
                  }else{
                	  apple = '申请'
                  }
                if (i < 5) {
           	
                    innerStr += "<div class='col-md-2 col-xs-3' id=" + res.page.list[i].id + "><div class='app'>" +
                    "   <div class='imgdiv'><div class='borimg'><img src='" + res.page.list[i].logoWeb + "' width='100' height='40'></div><a href='javascript:void(0)' id=" + res.page.list[i].id + "><i class='iconfont icon-icon2'></i>详情</a><p>" + "点击量:" + res.page.list[i].clickRate + "</p></div>" +
                    "   <div class='tac' title='"+res.page.list[i].appName+"'><h6>" + res.page.list[i].appName + "" +
                        "  </h6> <div class='appbtn'><a href='javascript:void(0)' class='marl_10'><i class='iconfont icon-quxiaoshoucang'></i>取消</a><a href='javascript:void(0)' class='appy'><i class='glyphicon glyphicon-edit'></i>"+res.page.list[i].statusId+"</a></div>" +
                        "</div></div></div>";      
                } else if (i == 5) {
                	
                    innerStr += "<div class='col-md-2 col-xs-3' id='more'><div class='app'>" +
                    "<img src='${pageContext.request.contextPath}/image/more.png'>"+
                        "   " +
                        "</div></div>";
                        
                } else if (i > 5) {
                	
                    innerStr += "<div class='col-md-2 col-xs-3 hid' id=" + res.page.list[i].id + "><div class='app'>" +
                    "   <div class='imgdiv'><div class='borimg'><img src='" + res.page.list[i].logoWeb + "' width='100' height='40'></div><a href='javascript:void(0)' id=" + res.page.list[i].id + "><i class='iconfont icon-icon2'></i>详情</a><p>" + "点击量:" + res.page.list[i].clickRate + "</p></div>" +
                    "   <div class='tac' title='"+res.page.list[i].appName+"'><h6>" + res.page.list[i].appName + "" +
                        "  </h6> <div class='appbtn'><a href='javascript:void(0)' class='marl_10'><i class='iconfont icon-quxiaoshoucang'></i>取消</a><a href='javascript:void(0)' class='appy'><i class='glyphicon glyphicon-edit'></i>"+res.page.list[i].statusId+"</a></div>" +
                        "</div></div></div>"; 
                } 
                
            }
            innerStr += "<div class='col-md-2 col-xs-3' id='moredown'><div class='app'>" +
            "   <img src='${pageContext.request.contextPath}/image/moredown.png'>" +
            "</div></div>";             

                $("#main_apps").append(innerStr);
                
                var dd = $("#main_apps").find('.appy').text(); 
                
                $("#main_apps .app").each(function(){
                	if($(this).find('.appy').text() == 1){
                		$(this).find('.appy').text("");
                	}else if($(this).find('.appy').text() == 0){
                		var dd = "<i class='glyphicon glyphicon-edit'></i>"+"申请中";
                		$(this).find('.appy').html(dd)
                	}else{
                		var dd = "<i class='glyphicon glyphicon-edit'></i>"+"申请";
                		$(this).find('.appy').html(dd);
                	}
                })
                $(window.parent.document).find("#indexFrameS").height($('#main_apps').height()+20);
                $(window).resize(function(){
                	$(window.parent.document).find("#indexFrameS").height($('#main_apps').height()+20);
                })
                selIndexList = [];
                
                $("#moredown").hide();
                if(modeShow == 1){
                	$("#main_apps").find('.hid').show();
                	$("#more").hide();
                	$("#moredown").show();
                }
                $("#more").click(function() {
                    $("#main_apps").find('.hid').show();
                    $(this).hide();
                    $("#moredown").show();
                    $(window.parent.document).find("#indexFrameS").height($('#main_apps').height());
                    modeShow = 1;
                })
                $("#moredown").click(function() {
                    $("#main_apps").find('.hid').hide();
                    $(this).hide();
                    $("#more").show();
                    $(window.parent.document).find("#indexFrameS").height($('#main_apps').height());
                    modeShow = 2;
                }) 
                
                $(".imgdiv").find("a").unbind("click")
                $(".imgdiv").find("a").bind("click", function() {  
                	var ww = $(this).parent().parent().parent().parent().attr('id');
                    var rr = $(this).parent().parent().parent().index();
                    var rffr = $(this).parent().siblings().find('h6').html();
                    var id = viewRecord.id;
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
                        	viewRecord = "";
                        	
                        }
                    });
                });                
                //绑定事件，父页面弹窗查看该数据编码的数据编码项
                $(".appbtn").find("a").unbind("click");
                $(".appbtn").find("a").bind("click", function() {
                    viewRecord = store[$(this).parent().parent().parent().parent().index()];
                    var id = viewRecord.id;                  
                    if ($(this).text() == "申请") {  
                        var layerId = window.parent.layer.open({
                            type: 2,
                            title: '申请审核',
                            area: ['600px', '400px'],
                            offset: 'auto',
                            content: ['${pageContext.request.contextPath}/manager/view/deploy/formapply' + '?' + id],
                            end: function() {
                                //window.parent.winList.pop();
                            	//queryRank()
                            	//parent.parent.indexFrameA.document.query();
                            	//parent.frames("indexFrameA").document.all.userName.value;
                            	//alert($(parent.parent.indexFrameA.document).contents().find("#mydiv1").html("aaa"));

                            
                            }
                        });
                    }else if($(this).text() == "取消"){
                        $.ajax({
                            dataType: "json",
                            type: "post",
                            cache: "false",
                            data: {
                                id: id,
                            },
                            url: "${pageContext.request.contextPath}/manager/rest/favorite/delFavorite",
                            success: function(res) {
                                if (res.success) {
                                	 window.parent.layer.alert("已取消收藏");
                                    queryRank();
                                } else {
                                	 window.parent.layer.alert("取消收藏失败");
                                }
                            },
                            error: function() {
                            	window.parent.layer.alert("取消收藏失败");
                            }
                        })                       
                    }
                });
                //显示分页
                laypage({
                         cont: 'pageContainer', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
                         pages: res.page.pages, //通过后台拿到的总页数
                         curr: curr || 1, //当前页
                         skin: '#428bca',
                         jump: function(obj, first) { //触发分页后的回调
                             if(!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                            	 queryRank(obj.curr);
                             }
                         }
                     }); 
            } else {
            	window.parent.layer.alert("获取数据失败"); 
            }


        },
        error:   function(jqXHR,  textStatus,  errorThrown) { 
            if (textStatus  !==  "error") { 
            	window.parent.layer.alert("获取数据失败"); 
            } 
        }
    });
}
</script>


</html>
