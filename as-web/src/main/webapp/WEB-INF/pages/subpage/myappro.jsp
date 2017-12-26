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
    <div class="as-content">
<!--              <form class="form-inline text-right marb_20 mart_100">
                <span class="control-label" for="submitValue">应用名称:</span>
                <input type="text" class="form-control" id="submitValue" placeholder="为空查询所有" />
                <button type="button" class="btn btn-primary" href="javascript:void(0)" id="queryapp" role="button">查询</button>
            </form>  -->  
        <table class="table table-hover table-fixed">
            <thead>
                <tr>
                    <th>应用名称</th>
                    <th>应用版本</th>
                    <th>申请人员</th>
                    <th>申请角色</th>
                    <th>申请时间</th>
                    <th>申请理由</th>
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
     //ie8和ie9下的placeholder兼容
    if(isIe89) {
        $("#submitValue").addClass("placeholer-ie");
        $("#submitValue").val($("#submitValue").attr("placeholder"));
        $("#submitValue").on("focus", function() {
            if($("#submitValue").val() == $("#submitValue").attr("placeholder")) {
                $("#submitValue").val("");
            }
            $("#submitValue").removeClass("placeholer-ie");
        });
        $("#submitValue").on("blur", function() {
            if($("#submitValue").val() == "") {
                $("#submitValue").val($("#submitValue").attr("placeholder"));
                $("#submitValue").addClass("placeholer-ie");
            }
        });
    }
    $("#queryapp").on("click", function() {
        if(isIe89 && $("#submitValue").val() == $("#submitValue").attr("placeholder")) {
            searchParam = "";
        } else {
            searchParam = $("#submitValue").val();
        }
        queryRank();
    });   
})

function query(curr) {
    $.ajax({
        dataType: "json",
        //type: "POST",
        cache: false,
        data:{
            pageNum: curr || 1, 
            statusCode: 0,
            pageSize:12,
            },
         url: "${pageContext.request.contextPath}/manager/rest/apply/applyList",
         success: function(res) {
            if (res.success) {
                $("#mainTable a").unbind("click");
                $("#mainTable").empty();
                 //pageNum = res.page.pageNum; 
                store = res.page.list;
                var innerStr = "";
                for (var i = 0; i < res.page.list.length; i++) {
                    var trCls = "";
                    if (i % 2 == 0) {
                        trCls = "bg-tr-even";
                    }	
                    innerStr += "<tr class='" + trCls + "' id=" + res.page.list[i].id + ">" +
                        /*  "   <td>" + ((res.page.pageNum-1)*20+i+1) + "</td>" + */
                        "   <td class='ellips' title='" + res.page.list[i].appName + "'>" + res.page.list[i].appName + "</td>" +
                        "   <td class='ellips' title='" + res.page.list[i].appVersion + "'>" + res.page.list[i].appVersion + "</td>" +
                        "   <td class='ellips' title='" + res.page.list[i].createByname + "'>" + res.page.list[i].createByname + "</td>" +
                        "   <td class='ellips' title='" + res.page.list[i].roleIds + "'>" + res.page.list[i].roleIds + "</td>" +
                        "   <td class='ellips' title='" + res.page.list[i].createDate + "'>" + res.page.list[i].createDate + "</td>" +
                        "   <td title='" + res.page.list[i].remark+ "' class='ellipse'>" + res.page.list[i].remark + "</td>" +
                        "   <td><a href='javascript:void(0)' class='btn btn-primary marl_10' title='同意'><i class='iconfont icon-shenpi'></i></a><a href='javascript:void(0)' class='btn btn-danger' title='驳回'><i class='iconfont icon-bohui1'></i></a></td>" +
                        "</tr>";
                }
                $("#mainTable").append(innerStr);
                selIndexList = [];
                //绑定事件，父页面弹窗查看该数据编码的数据编码项
                $("#mainTable").find("a").bind("click", function() {
                    viewRecord = store[$(this).parent().parent().index()];
                    var id = viewRecord.id;
                    if ($(this).attr('title') == "同意") {
                        $.ajax({
                            dataType: "json",
                            type: "post",
                            cache: "false",
                            data: {
                                id: id,
                                isAgree:1,
                                userId:${sessionUserInfo.userId},orgCode:'',
                                statusRemark:""
                            },
                            url: "${pageContext.request.contextPath}/manager/rest/audit/auditApply",
                            success: function(res) {
                                if (res.success) {
                                   layer.alert("审批成功");
                                    query();
                                } else {
                                    layer.alert("审批失败");
                                }
                            },
                            error: function() {
                                layer.alert("审批失败");
                            }
                        })
                    } else if ($(this).attr('title') == "驳回") {
                         var layerId = layer.open({
                                type: 2,
                                title:'填写驳回理由',
                                area: ['600px', '300px'],
                                offset: 'auto',
                                content: ['${pageContext.request.contextPath}/manager/view/deploy/reject'+'?'+id],
                                end: function() {
                                    query();
                                }
                            });
                            // window.parent.winList.push(layerId);

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
                layer.alert("获取数据失败！");
            }


        },
        error:   function(jqXHR,  textStatus,  errorThrown) { 
            if (textStatus  !==  "error") { 
				layer.alert("获取数据失败！"); 
            } 
        }
    });
}
</script>

</html>
