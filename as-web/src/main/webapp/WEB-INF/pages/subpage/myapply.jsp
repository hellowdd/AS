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
        <div class="applycont">
            <table class="table table-hover table-fixed">
                <thead>
                    <tr>
                        <th>应用名称</th>
                        <th>申请时间</th>
                        <th>申请人</th>
                        <th>审核情况</th>
                        <th>驳回理由</th>
                        <th>操作</th>
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
//判断是否是ie8或ie9
var isIe89 = (navigator.appName == "Microsoft Internet Explorer" && (navigator.appVersion.match(/8./i) == "8." || navigator.appVersion.match(/9./i) == "9.")) ? true : false;
$(document).ready(function() {
    query()    
})

function query(curr) {
    $.ajax({
        dataType: "json",
        cache: false,
        data:{
           pageNum: curr || 1, 
           userId: ${sessionUserInfo.userId},
           pageSize:12
             },
        url: "${pageContext.request.contextPath}/manager/rest/apply/applyList",
        success: function(res) {
            if (res.success) {
                $("#mainTable a").unbind("click");
                $("#mainTable").empty();
                //  pageNum = res.page.pageNum; 
                store = res.page.list;
                var innerStr = "";
                for (var i = 0; i < res.page.list.length; i++) {
                    var trCls = "";
                    if (i % 2 == 0) {
                        trCls = "bg-tr-even";
                    }
                    var statusId;
                    if(res.page.list[i].statusId == 0){
                    	statusId = "待审核"
                    }else if(res.page.list[i].statusId == 1){
                    	statusId = "申请通过"
                    }else{
                    	statusId = "申请驳回"
                    } 
                    innerStr += "<tr class='" + trCls + "' id=" + res.page.list[i].id + ">" +
                        /*  "   <td>" + ((res.page.pageNum-1)*20+i+1) + "</td>" + */
                        "   <td title='" + res.page.list[i].appName + "'>" + res.page.list[i].appName + "</td>" +
                        "   <td title='" + res.page.list[i].createDate+ "'>" + res.page.list[i].createDate + "</td>" +
                        "   <td title='" + res.page.list[i].createByname+ "'>" + res.page.list[i].createByname + "</td>" +
                        "   <td><span>" + statusId + "</span></td>" +                    
                        "   <td title='" + res.page.list[i].statusRemark+ "' class='ellipse'>" + res.page.list[i].statusRemark + "</td>" +
                        "   <td><a href='javascript:void(0)' class='btn btn-danger' title='删除'><i class='iconfont icon-iconfontcolor23'></i></a></td>" +
                        "</tr>";
                }
                $("#mainTable").append(innerStr);
                $(window.parent.document).find("#indexFrameA").height($('.applycont').height()+20);
                $(window).resize(function(){
                	  $(window.parent.document).find("#indexFrameA").height($('.applycont').height()+20);
                })
                $("#mainTable").find('tr').each(function(){
                    if($(this).children().eq(3).text() == '申请通过'){
                        $(this).children().eq(3).addClass('green');
                    }else if($(this).children().eq(3).text() == '待审核'){
                        $(this).children().eq(3).addClass('org');
                    }else{
                        $(this).children().eq(3).addClass('red');
                    }                   
                })

                selIndexList = [];

                //绑定事件，父页面弹窗查看该数据编码的数据编码项
                $("#mainTable").find("a").bind("click", function() {
                    viewRecord = store[$(this).parent().parent().index()];
                    var id = viewRecord.id;
                    if ($(this).attr('title') == "删除") {
                        $.ajax({
                            dataType: "json",
                            type: "post",
                            cache: "false",
                            data: {
                                id: id,
                            },
                            url: "${pageContext.request.contextPath}/manager/rest/apply/delApply",
                            success: function(res) {
                                if (res.success) {
                                	window.parent.layer.alert("删除成功");
                                    query();
                                    window.parent.frames["indexFrameS"].queryRank();
                                } else {
                                	window.parent.layer.alert("删除失败");
                                }
                            },
                            error: function() {
                            	window.parent.layer.alert("删除失败");
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
