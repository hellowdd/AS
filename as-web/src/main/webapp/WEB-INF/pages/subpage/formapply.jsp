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
                <label for="" class="col-xs-2 control-label">应用名称:</label>
                <div class="col-xs-9">
                    <input type="" name="" class="form-control" readonly="readonly" id="applyname" />
                </div>
            </div>
            <div class="form-group">
                <label for="" class="col-xs-2 control-label">申请角色:</label>
                <div class="col-xs-9">
                	<div id="maincheck"></div>
                	
<!--                     <select class="form-control" id="role">
                        <option>管理员</option>
                        <option>12</option>
                        <option>12</option>
                    </select> -->
                </div>
            </div>
            <div class="form-group">
                <label for="" class="col-xs-2 control-label">申请理由:</label>
                <div class="col-xs-9">
                    <textarea class="form-control" id="reason"></textarea>
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="form-group mart_20">
                <div class="col-xs-offset-2 col-xs-10">
                    <a type="button" class="btn btn-primary" id="submit">提交</a>
                </div>
            </div>
        </form>
    </div>
</body>

</html>
<script type="text/javascript">
var selIndexList = [];
var viewRecord
$(document).ready(function() {
	if(parent.isFd == 2){
		viewRecord =parent.viewRecord
	}else{
		viewRecord =window.parent.frames["indexFrameS"].viewRecord
	}	
	var id = window.location.href.split("?").slice(-1);
	//var id = window.parent.viewdata;
    $("#applyname").val(viewRecord.appName);
    query()
    //角色
    function query(curr){
        $.ajax({
            async: false,
            cache: false,
            dataType: "json",
            data:{
            	version: viewRecord.appVersion||viewRecord.version,
                appId: viewRecord.appId
                   },
             url: "${pageContext.request.contextPath}/manager/rest/myApp/getAppAllRole",
            success: function(res) {
                if (res.success) {
                	$("#maincheck").empty();
                	var inner = "";
                	for (var i = 0; i < res.data.length; i++) {
                		inner += "<input type='checkbox' name='' id='"+res.data[i].roleCode+"'/><span class='marl_10'>"+res.data[i].roleName+"</span>"
                	}
                    $("#maincheck").append(inner);
                    $("#maincheck").find("input").bind("change", function () {
                        var id = $(this).attr("id");
                        var pname = $(this).next().html();
                        if ($(this).prop("checked")) {
                            selIndexList.push(id+':'+pname);
                        } else {
                            for (var i = 0; i < selIndexList.length; i++) {
                                if (selIndexList[i][0] == id) {
                                    selIndexList.splice(i, 1);
                                    break;
                                }
                            }
                        }
                    });
                } else {
                    window.parent.layer.alert("获取角色失败！");
                }
            },
            error:   function(jqXHR,  textStatus,  errorThrown) { 
                if (textStatus  !==  "error") { 
                    window.parent.layer.alert("获取角色失败！"); 
                } 
            }
        });     	
    }	
      
    $("#submit").click(function() {
        var reason = $("#reason").val();
        var role = $("#role").val();
        var rej = {
				statusId:"0",
			    appId:viewRecord.appId,
			    appName:viewRecord.appName,
			    appVersion:viewRecord.appVersion||viewRecord.version,
			    remark:$("#reason").val(),
			    roleIds:selIndexList.toString(),
			    statusRemark:"",
			    createByname:'${sessionUserInfo.userName}',
			    createBy:${sessionUserInfo.userId}
            }
        if(selIndexList == ""){
        	window.parent.layer.alert("请选择申请角色！");
        		return false;
        }
        $.ajax({
            async: false,
            cache: false,
            dataType: "json",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(rej),
            url: "${pageContext.request.contextPath}/manager/rest/apply/insertApply",
            success: function(res) {
                if (res.success) {
    			    window.parent.layer.alert("提交成功!",function(i){
    			    	if(parent.isFd == 2){
    			    		
    			    	}else{
    			    		window.parent.frames["indexFrameA"].query();
    			    		window.parent.frames["indexFrameS"].queryRank();
    			    	}
    			        
    					window.parent.layer.closeAll();	   					
    				});                    
                } else {
                	if(res.data){
        			    window.parent.layer.alert(res.data,function(i){
        					window.parent.layer.closeAll();							
        				});                		
                	}else{
                		window.parent.layer.alert("提交失败！");
                	}
                    
                }
            },
            error:   function(jqXHR,  textStatus,  errorThrown) { 
                if (textStatus  !==  "error") { 
                    window.parent.layer.alert("提交失败！"); 
                } 
            }
        });
    })
})
</script>
