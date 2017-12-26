<%@page import="com.bocom.dto.UserRoleInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.bocom.dto.SessionUserInfo"%>
<%@page import="org.springframework.util.CollectionUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
<title>应用商店</title>
<META HTTP-EQUIV="content-type" CONTENT="text/html; charset=UTF-8">
<link
	href="${pageContext.request.contextPath}/sea-modules/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/font/iconfont.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath}/sea-modules/jquery/jquery-1.9.1.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/sea-modules/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/sea-modules/layer/layer.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/module.js"></script>
<script src="${pageContext.request.contextPath}/sea-modules/tool/base.js" type="text/javascript"></script>
<!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/sea-modules/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath}/sea-modules/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="layout-main"
		style="position: absolute; top: 20px; left: 20px; right: 20px; bottom: 20px;">
		<form class="form-horizontal evaform" role="form" id="infoForm">
			<div class="form-group">
				<label for="appName" class="col-xs-2 control-label">系统名称:</label>
				<div class="col-xs-9">
					<input class="form-control" id="appName" name='appName' len="50" readonly='readonly'>
				</div>
			</div>	
			<div class="form-group">
				<label for="" class="col-xs-2 control-label">评分:</label>
				<div class="col-xs-9">
					<div id="evalu">
					    
						<i class="iconfont icon-shoucang"></i><i
							class="iconfont icon-shoucang"></i><i
							class="iconfont icon-shoucang"></i><i
							class="iconfont icon-shoucang"></i><i
							class="iconfont icon-shoucang"></i>
							<input class="adddtiona"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="isPublic" class="col-xs-2 control-label">设置限制:</label>
				<div class="col-xs-9">
					<select class="form-control" id="isPublic">
						<option value='1'>公开</option>
						<option value='0'>非公开</option>
					</select>
				</div>
			</div>							
			<div class="form-group">
				<label for="appJudge" class="col-xs-2 control-label">评语:</label>
                <div class="col-xs-9 add_fin">
                	<div class="comment" style='max-height:177px;height:auto'>
                		<h3></h3>
                		<div id='commentbox'>                		
                		</div>
                	</div>           
                </div> 
			</div>

			<div class="clearfix"></div>

			<div class="form-group mart_20">
				<div class="col-xs-offset-2 col-xs-10">
					<a type="button" class="btn btn-primary" id="submit">提交</a>
				</div>
			</div>
			<div class='addtion' style='display:none'>
				<div class="form-group">
					<label for="appJudgeadd" class="col-xs-2 control-label">追加评语:</label>
					<div class="col-xs-9">
						<textarea class="form-control" id="appJudgeadd" name='appJudgeadd' len="50"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-xs-2 control-label">追加评分:</label>
					<div class="col-xs-9">
						<div id="evalu2">
							<i class="iconfont icon-shoucang"></i><i
								class="iconfont icon-shoucang"></i><i
								class="iconfont icon-shoucang"></i><i
								class="iconfont icon-shoucang"></i><i
								class="iconfont icon-shoucang"></i>
								<input class="adddtiona2"/>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
				<div class="form-group mart_20">
					<div class="col-xs-offset-2 col-xs-10">
						<a type="button" class="btn btn-primary" id="submitadd">提交</a>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>

</html>
<script type="text/javascript">
var selEva = "";
var idadd;
var viewRecord;
$(document).ready(function() {
	//var viewRecord =window.parent.frames["indexFrame"].viewRecord;
	var id = window.location.href.split("?").slice(-1);
	if(parent.isFd == 2){
		viewRecord =parent.viewRecord
	}else{
		viewRecord =window.parent.frames["indexFrameN"].viewRecord
	}
	//console.log(viewRecord)
	$("#appName").val(viewRecord.appName)
	

    var reh = {
           	appId:viewRecord.appId,
        	appVersion:viewRecord.appVersion||viewRecord.version
        }
	console.log(reh)
        $.ajax({
            async: false,
            cache: false,
            dataType: "json",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(reh),
            url: "${pageContext.request.contextPath}/manager/rest/appJudge/getAppJudgeInfo",
            success: function(res) {
            	console.log(res)
                if (res.success) {
    			     $("#appJudge").val(res.data.appJudge);
    			     $("#appJudgeadd").val(res.data.appJudgeAdd);
                     for(var i = 0;i <res.data.appScore/20;i++){
                   		$("#evalu").children('i').eq(i).addClass('red2');
                   	 } 
                     for(var i = 0;i <res.data.appScoreAdd/20;i++){
                    		$("#evalu2").children('i').eq(i).addClass('red2');
                    	 }
                   	 $("#isPublic").val(res.data.isPublic);
                   	 idadd = res.data.id;
                   	 var innerStr = "";
                   	$("#commentbox").empty();
                     for (var i = 0; i < res.data.appJudgeInfo.length; i++) {
                    	 res.data.appJudgeInfo[i].createTime = res.data.appJudgeInfo[i].createTime?dateFormathms(new Date(res.data.appJudgeInfo[i].createTime)):"";
                         innerStr += "<div class='pull-left commentment'>"+"<p class='wordwrap'>"+res.data.appJudgeInfo[i].judgeContent+"<p>"+
                         "<h6><span>"+res.data.appJudgeInfo[i].createTime+"</span></h6>"+
                         "</div>";
                  } 
                  $("#commentbox").append(innerStr);
                  $("#add_fin").hide();
                  	 if(res.data.appJudge == null){
                   		 $(".addtion").show();
                   		 $("#add_fin").show();
                   		 $("#appJudge").hide();
                   		 $("#submit").parent().parent().hide();
                   		 $("#appJudge").attr("readonly","readonly");
                   		 $("#isPublic").attr("readonly","readonly");
                   		 $(".adddtiona").show();
                   		 $(".adddtiona").attr("readonly","readonly");
                   		 $("#isPublic").attr("disabled","disabled");                 		 
                   	 }
                }
            },
            error:   function(jqXHR,  textStatus,  errorThrown) { 
                if (textStatus  !==  "error") { 
                    window.parent.layer.alert("提交失败！"); 
                } 
            }
        });
//
    $("#evalu").find('i').click(function(){
    	$("#evalu").find('i').removeClass('red2')
    	var index = $(this).index();
    	for(var i = 0;i<=index;i++){
    		$("#evalu").find('i').eq(i).addClass('red2');
    		selEva = 20*(i+1);
    	}
    	
    })
    $("#evalu2").find('i').click(function(){
    	$("#evalu2").find('i').removeClass('red2')
    	var index = $(this).index();
    	for(var i = 0;i<=index;i++){
    		$("#evalu2").find('i').eq(i).addClass('red2');
    		selEva = 20*(i+1);
    	}
    	
    }) 
    //追加
    $("#submitadd").click(function() {
        var rej = {
			    id:idadd,
			    appScore:selEva,
			    appJudgeAdd:$("#appJudgeadd").val(),
			    isPublic:$("#isPublic").val()		        
            }
         if($("#appJudgeadd").val() == ""){
        	window.parent.layer.alert("请填写追加评语！");
        		return false;
        }else if($("#appJudgeadd").val().length >=1000){
         	window.parent.layer.alert("字数不能超过1000！");
					return false;
		    }
        if(selEva == ""){
        	window.parent.layer.alert("请进行星级评价！");
        		return false;
        }
        $.ajax({
            async: false,
            cache: false,
            dataType: "json",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(rej),
            url: "${pageContext.request.contextPath}/manager/rest/appJudge/appendAppJudge",
            success: function(res) {
                if (res.success) {
    			    window.parent.layer.alert("提交成功!",function(i){
    			    	window.parent.frames["indexFrameN"].queryRankA();
    			    	window.parent.frames["indexFrameE"].query();
    					window.parent.layer.closeAll();	
    					 
    					//top.document.location.reload();
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
