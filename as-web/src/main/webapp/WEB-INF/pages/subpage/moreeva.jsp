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
      <script src="${pageContext.request.contextPath}/sea-modules/viewRecordpond.min.js"></script>
    <![endif]-->
</head>

<body>
    <div class="layout-main" style="position: absolute;top: 20px;left: 20px;right: 20px;bottom: 20px;">
        <form class="form-horizontal padd_3" role="form" id="infoForm">
<!--         	<div class='fotm_fd'>
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
        	</div> -->			
             <div class="form-group">
                <div class="col-xs-12">
                	<div class="comment" style='height:380px'>
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
	viewRecord =window.parent.frames["indexFrameE"].viewEva;
    $(".commentment").empty();
   	$(".comment h3").html();
      	for(var i = 0;i < viewRecord.appScore/20;i++){                            		
      		$("#evalu").children('i').eq(i).addClass('red2');
      	} 	
       var innerStr = "";
       	innerStr += "<div class='addchage'><img src='${pageContext.request.contextPath}/image/per.png' class='pull-left'/>"+
           "<div class='pull-left addchangeright'><h4>"+viewRecord.createByname+"</h4><h6><strong id='"+viewRecord.appScore+"'><i class='iconfont icon-shoucang'></i>"+
           "<i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i><i class='iconfont icon-shoucang'></i>"+
           "</strong></h6>";
       	for(var j = 0;j< viewRecord.appJudgeInfo.length;j++){
       		viewRecord.appJudgeInfo[j].createTime =viewRecord.appJudgeInfo[j].createTime?dateFormathms(new Date(viewRecord.appJudgeInfo[j].createTime)):"";
               innerStr += "<div class='pull-left commentment'>"+"<p class='wordwrap'>"+viewRecord.appJudgeInfo[j].judgeContent+"</p>"+
               "<h6><span>"+viewRecord.appJudgeInfo[j].createTime+"</span></h6>"+
              "</div>";
               
       	}
       	innerStr += "</div></div>"
       $("#commentbox").append(innerStr);
       $("#commentbox").find('strong').each(function(){
       	var score = $(this).attr('id');
       	for(var i = 0;i <score/20;i++){
       		$(this).children('i').eq(i).addClass('red2');
       	}       
       	
       })      
});
                  
  
                    		
</script>
