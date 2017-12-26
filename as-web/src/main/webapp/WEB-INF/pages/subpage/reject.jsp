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
    <div class="layout-main" style="position: absolute;top: 20px;left: 20px;right: 20px;bottom: 20px;">
        <form class="form-horizontal" role="form" id="infoForm">
        <div class="form-group">
            <label for="" class="col-xs-2 control-label">驳回理由:</label>
            <div class="col-xs-9">
                <textarea class="form-control" id="reason"></textarea>
            </div>
        </div>
        <div class="form-group mart_20">
            <div class="col-xs-offset-2 col-xs-10">
                <a type="button" class="btn btn-primary" id="reject">提交</a>
            </div>
        </div>
        </form>
    </div>
</body>

</html>
<script type="text/javascript">
var id = window.location.href.split("?").slice(-1);
//encodeURI
$("#reject").click(function() {
    var rej = {
        id: id.toString(),
        isAgree:2,
        userId:${sessionUserInfo.userId},
        orgCode:"",
        statusRemark:$("#reason").val()
        }
    $.ajax({
        async: false,
        cache: false,
        dataType: "json",
        data: rej,
        url: "${pageContext.request.contextPath}" + "/manager/rest/audit/auditApply",
        success: function(res) {
            if (res.success) {
			    window.parent.layer.alert("驳回成功!",function(i){
					window.parent.layer.closeAll();							
				}); 
            } else {
                window.parent.layer.alert("驳回失败！");
            }
        },
        error:   function(jqXHR,  textStatus,  errorThrown) { 
            if (textStatus  !==  "error") { 
                window.parent.layer.alert("驳回失败！"); 
            } 
        }
    });
})
</script>

