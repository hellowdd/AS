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
        <div class="evacont">
            <table class="table table-hover table-fixed">
                <thead>
                    <tr>
                        <th>应用名称</th>
                        <th>星级评价</th>
                        <th>最新评语</th>
                        <th>操作</th>
      <!--                   <th>追加评语</th> -->
                    </tr>
                </thead>
                <tbody id="mainTable">                   
                </tbody>
            </table>
            <div class="pageContainer layout-page" id="pageContainer"></div>
        </div>
</body>
<script type="text/javascript">
//用于搜索数据编码的关键字，可搜索数据编码名称及表名
var searchParam = "";
var viewEva;
//判断是否是ie8或ie9
$(document).ready(function() {
    query()  
})

function query(curr) {
	var rej ={
	        pageNum: curr || 1, 
	        pageSize:12
	       };
    $.ajax({
        dataType: "json",
        cache: false,
        type:"post",
        contentType: "application/json",
        data: JSON.stringify(rej),
        url: "${pageContext.request.contextPath}/manager/rest/appJudge/getMyAppJudgeInfoList",
        success: function(res) {
            if (res.success) {
                $("#mainTable a").unbind("click");
                $("#mainTable").empty();
                //  pageNum = res.page.pageNum; 
                store = res.page.list;
                var innerStr = "";              
                for (var i = 0; i < res.page.list.length; i++) {
                    var trCls = "";
                    var judgeContent = "";
                    if (i % 2 == 0) {
                        trCls = "bg-tr-even";
                    }
                    if(res.page.list[i].appJudgeInfo.length>0){
                    	judgeContent = res.page.list[i].appJudgeInfo[0].judgeContent;
                    }
                    innerStr += "<tr class='" + trCls + "' id=" + res.page.list[i].id + ">" +
                        "   <td title='" + res.page.list[i].appName + "'>" + res.page.list[i].appName + "</td>" +
                        "   <td class='col9'><strong id='" + res.page.list[i].appScore+ "' ><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i></strong></td>" +
                        "   <td title='" + judgeContent+ "' class='ellipse'>" + judgeContent + "</td>" + 
                        "   <td><a href='javascript:void(0)' class='marl_10'>更多评价</a</td>" + 
             /*            "   <td class='col9'><strong id='" + res.page.list[i].appScoreAdd+ "' ><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i></strong></td>" +
                        "   <td title='" + res.page.list[i].appJudgeAdd+ "' class='ellipse'>" + res.page.list[i].appJudgeAdd + "</td>" + */
                        "</tr>";
                }
                $("#mainTable").append(innerStr);
                $(window).resize(function(){
                    $(window.parent.document).find("#indexFrameE").height($('.evacont').height()+30);
                })
                $("#mainTable").find('strong').each(function(){
                	var score = $(this).attr('id');
                	if(score == 'null'){
                		$(this).empty();
                	}else{
                    	for(var i = 0;i <score/20;i++){
                    		$(this).children('i').eq(i).addClass('red2');
                    	} 	
                	}                  
                })
				$("#mainTable").find('td').each(function(){
                	if($(this).html() == 'null'){
                		$(this).empty();
                	}                 
                })	
                $("#mainTable").find("a").bind("click", function() {                 	
                	var ww = $(this).parent().parent().attr('id');
                    var rr = $(this).parent().parent().index(); 
                    viewEva = store[rr];
                    var name = store[rr].appName
                    var layerId = window.parent.layer.open({
                        type: 2,
                    	title: name+"的更多评价",
                        area: ['650px', '500px'],
                        offset: 'auto',
                        content: ['${pageContext.request.contextPath}/manager/view/deploy/moreeva'],
                        end: function() {
                        	viewEva = "";
                        }
                    });
                }); 
                //显示分页
              laypage({
                       cont: 'pageContainer', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
                       pages: res.page.pages, //通过后台拿到的总页数
                       curr: curr || 1, //当前页
                       skin: '#428bca',
                       jump: function(obj, first) { //触发分页后的回调
                           if(!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                               query(obj.curr);
                           }
                       }
                   }); 
            } else {
            	window.parent.layer.alert("获取数据失败！");
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
