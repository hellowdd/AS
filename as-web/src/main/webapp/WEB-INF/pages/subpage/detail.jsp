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
    <script src="${pageContext.request.contextPath}/sea-modules/tool/base.js" type="text/javascript"></script>
    <!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/sea-modules/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath}/sea-modules/respond.min.js"></script>
    <![endif]-->
</head>

<body>
    <div class="layout-main" style="position: absolute;top: 20px;left: 20px;right: 20px;bottom: 20px;">
        <form class="form-horizontal padd_3" role="form" id="infoForm">
        	<div class='fotm_fd'>
        	     <div class="form-group">
	                <div class="col-xs-12">
	                     <p><img id="logoApp"/></p>  
	                </div>
	            </div>
	            <div class="form-group">
	                <div class="col-xs-12">
	                	<span>版本号:</span><p id="version" style='display: inline-block'></p>         
	                </div>
	            </div>              
	            <div class="form-group">
	                <div class="col-xs-12">
	                	<div id="evalu">
	                		<i class="iconfont icon-shoucang"></i><i class="iconfont icon-shoucang"></i><i class="iconfont icon-shoucang"></i><i class="iconfont icon-shoucang"></i><i class="iconfont icon-shoucang"></i>
	                	</div>              
	                </div>
	            </div>
        	</div>
        	<div class='frd_re'>             
	            <div class="form-group">
	                <div class="col-xs-12">
	                   <p id="appDesc" class='miacht'></p>               
	                </div>
	            </div>
        	</div>			
             <div class="form-group">
                <div class="col-xs-12">
                	<div class="comment">
                		<h3></h3>
                		<div id='commentbox'>                		
                		</div>
                	</div>           
                </div>
            </div>                                  
            <div class="clearfix"></div>
        </form>
    </div>
</body>

</html>
<script type="text/javascript">
var selIndexList = [];
var viewRecord = "";
$(document).ready(function() {
	if(parent.isFd == 2){
		viewRecord =parent.viewRecord
	}else{	
		viewRecord =window.parent.frames["indexFrameS"].viewRecord;
		if(viewRecord == ""){
			viewRecord =window.parent.frames["indexFrameN"].viewRecord;
		}
	}
	console.log(viewRecord)
	
    $("#appName").html(viewRecord.appName);
    $("#companyName").html(viewRecord.appOrgName);
    $("#version").html(viewRecord.version||viewRecord.appVersion);
    $("#appDesc").html(viewRecord.appDesc)
     if(viewRecord.logoApp){
        if(viewRecord.logoApp == null || viewRecord.logoApp == ""){
        	$("#logoApp").attr('src','${pageContext.request.contextPath}/image/normal.png');
        }else{
        	$("#logoApp").attr('src',viewRecord.logoApp);
        } 	
    }else if(viewRecord.logoWeb){
    	if(viewRecord.logoWeb == null || viewRecord.logoWeb == ""){    	
        	$("#logoApp").attr('src','${pageContext.request.contextPath}/image/normal.png');
        }else{
        	$("#logoApp").attr('src',viewRecord.logoWeb);
        } 	
    }   
    $(".fotm_fd").find('img').width();
    if($(".fotm_fd").find('img').height()>$(".fotm_fd").find('img').width()){
    	$(".fotm_fd").find('img').css({
    		"width":"auto",
    		'min-height':" 105px"
    	});
    }
    query()
    function query(curr){
        var reg = {
               	appId:viewRecord.appId,
            	appVersion:viewRecord.appVersion||viewRecord.version
            }
            $.ajax({
                async: false,
                cache: false,
                dataType: "json",
                type:"post",
                contentType: "application/json",
                data: JSON.stringify(reg),
                url: "${pageContext.request.contextPath}/manager/rest/appJudge/getAppJudgeInfoList",
                success: function(res) {
                    if (res.success) {
                    	console.log(res)
                    	if(res.page.list.length != 0){
                            $(".commentment").empty();
                        	$(".comment h3").html(res.page.list.length+"人评价过");
                        	if(res.app_score_avg == ""||res.app_score_avg == null){
                        		$("#evalu").empty()
                        		$("#evalu").html('暂无评价')
                        	}else{
                            	for(var i = 0;i < res.app_score_avg/20;i++){                            		
                            		$("#evalu").children('i').eq(i).addClass('red2');
                            	} 	
                        	}
                            
                            var innerStr = "";
                            for (var i = 0; i < res.page.list.length; i++) {
                            	innerStr += "<div class='addchage'><img src='${pageContext.request.contextPath}/image/per.png' class='pull-left'/>"+
                                "<div class='pull-left addchangeright'><h4>"+res.page.list[i].createByname+"</h4><h6><strong id='"+res.page.list[i].appScore+"'><i class='iconfont icon-shoucang'></i>"+
                                "<i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i>"+
                                "</strong></h6>";
                            	for(var j = 0;j< res.page.list[i].appJudgeInfo.length;j++){
                            		res.page.list[i].appJudgeInfo[j].createTime =res.page.list[i].appJudgeInfo[j].createTime?dateFormathms(new Date(res.page.list[i].appJudgeInfo[j].createTime)):"";
                                    innerStr += "<div class='pull-left commentment'>"+"<p class='wordwrap'>"+res.page.list[i].appJudgeInfo[j].judgeContent+"</p>"+
                                    "<h6><span>"+res.page.list[i].appJudgeInfo[j].createTime+"</span></h6>"+
                                   "</div>";
                                    
                            	}
                            	innerStr += "</div></div>"
               /*                     innerStr += "<img src='${pageContext.request.contextPath}/image/per.png' class='pull-left'/><div class='pull-left commentment'><h4>"+res.page.list[i].createByname+"</h4>"+
                                   "<p>"+res.page.list[i].appJudge+"<p>"+
                                   "<h6><span>"+res.page.list[i].createDate+"</span><strong id='"+res.page.list[i].appScore+"'><i class='iconfont icon-shoucang'></i>"+
                                   "<i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i>"+
                                   "</strong></h6>"+
	                                   "<div class=''>"+
		                                   "<span class='assw'>追加评论:</span><p class='dgrfdg'>"+res.page.list[i].appJudgeAdd+"<p>"+
		                                   "<h6><span>"+res.page.list[i].judgeAddTime+"</span><strong id='"+res.page.list[i].appScoreAdd+"'><i class='iconfont icon-shoucang'></i>"+
		                                   "<i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i>"+
		                                   "</strong></h6>"+                                    
	                                   "</div>"+
                                   "</div>"; */
                            } 
                            $("#commentbox").append(innerStr);
                            $("#commentbox").find('strong').each(function(){
                            	var score = $(this).attr('id');
                            	for(var i = 0;i <score/20;i++){
                            		$(this).children('i').eq(i).addClass('red2');
                            	}       
                            	
                            })
                            $("#commentbox").find('.dgrfdg').each(function(){
                            	var ctec = $(this).html();
                            	if(ctec == null || ctec == 'null'){
                            		$(this).parent().hide();
                            	}
                            })       
                    	}

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
      
})
</script>
