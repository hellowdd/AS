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
    <div class="as-content">
        <div class="pull-container">
            <form class="form-inline text-right marb_20 mart_100">
                <input type="text" class="form-control" id="submitValue" placeholder="请输入应用名称" />
                <button type="button" class="btn btn-primary" href="javascript:void(0)" id="queryapp" role="button"><span class="glyphicon glyphicon-search"></span></button>
            </form>
            <div class="row" id="main_app">
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
var viewRecord = [];
var store = [];
var store2 = [];
var isFd = 2;
//用于搜索的关键字
var searchParam = "";
var isIe89 = (navigator.appName == "Microsoft Internet Explorer" && (navigator.appVersion.match(/8./i) == "8." || navigator.appVersion.match(/9./i) == "9.")) ? true : false;
//判断是否是ie8或ie9
$(document).ready(function() {
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
    queryRank()

    
});

function queryRank() {
	var reg = {
	  startTime:"",
	  endTime:"",
   	  commparam:searchParam
     }
    $.ajax({
        dataType: "json",
        type: "POST",
        cache: false,
        contentType: "application/json",
        data: JSON.stringify(reg),
        url: "${pageContext.request.contextPath}/api/queryOrgAppInfo",
        success: function(res) {
        	console.log(res)
            if (res.success) {
                $("#main_app a").unbind("click");
                $("#main_app").empty();
                //  pageNum = res.page.pageNum; 
                store = res.data;     
                var innerStr = "";
                for (var i = 0; i < res.data.length; i++) {
                	innerStr += "<div class='cateory' ><i class='glyphicon glyphicon-th-list'></i><span>"+res.data[i].bizCategoryName+"</span></div><div class='row' id='"+i+"'>";
                	for (var j = 0; j < res.data[i].list.length; j++) {
                    	if(res.data[i].list[j].logoApp == null){
                    		res.data[i].list[j].logoApp = '${pageContext.request.contextPath}/image/normal.png';
                    	}
                    	if(res.data[i].list[j].logoApp == ''){
                    		res.data[i].list[j].logoApp = '${pageContext.request.contextPath}/image/normal.png';
                    	}  
                    	
                        if (j < 5) {
                            innerStr += "<div class='col-md-2 col-xs-3' id=" + res.data[i].list[j].id + "><div class='app'>" +
                            "   <div class='imgdiv'><div class='borimg'><img src='" + res.data[i].list[j].logoApp + "' width='100' height='40'></div></a><p style='margin-top: 32px;'>" + "点击量:" + res.data[i].list[j].click_rate + "</p></div>" +
                            "   <div class='tac' title='"+res.data[i].list[j].appName+"'><h6>" + res.data[i].list[j].appName + "" +
                                "  </h6> <div class='appbtn'><a href='javascript:void(0)' class='marl_10'><i class='iconfont icon-icon2' style='font-size: 17px;vertical-align: middle;'></i>查看评价</a></div>" +
                                "</div></div></div>"; 
                        }  else if (j == 5) {
                            innerStr += "<div class='col-md-2 col-xs-3 more' id='more'><div class='app'>" +
                            "<img src='${pageContext.request.contextPath}/image/more.png'>"+
                                "   " +
                                "</div></div>";
                        } else if (j > 5) {
                            innerStr += "<div class='col-md-2 col-xs-3 hid' id=" + res.data[i].list[j].id + "><div class='app'>" +
                            "   <div class='imgdiv'><div class='borimg'><img src='" + res.data[i].list[j].logoApp + "' width='100' height='40'></div></a><p style='margin-top: 32px;'>" + "点击量:" + res.data[i].list[j].click_rate + "</p></div>" +
                            "   <div class='tac' title='"+res.data[i].list[j].appName+"'><h6>" + res.data[i].list[j].appName + "" +
                                "  </h6> <div class='appbtn'><a href='javascript:void(0)' class='marl_10'><i class='iconfont icon-icon2' style='font-size: 17px;vertical-align: middle;'></i>查看评价</a></div>" +
                                "</div></div></div>"; 
                                
                        }                      	         
                                
                	}
                    innerStr += "<div class='col-md-2 col-xs-3 moredown' id='moredown'><div class='app'>" +
                    "   <img src='${pageContext.request.contextPath}/image/moredown.png'>" +
                    "</div></div>";
                	innerStr +="</div>"
                }
         		
                $("#main_app").append(innerStr);
               
                selIndexList = [];
                $(".moredown").hide();
                $(".more").click(function() {
                	$(this).siblings('.hid').show();
                    $(this).hide();
                    $(this).siblings('.moredown').show();
                })
                $(".moredown").click(function() {
                	$(this).siblings('.hid').hide();
                    $(this).hide();
                    $(this).siblings('.more').show();
                })
                //绑定事件，父页面弹窗查看该数据编码的数据编码项
/*                 $(".imgdiv").find("a").bind("click", function() {  
                	 var ww = $(this).parent().parent().parent().parent().attr('id');
                    var rr = $(this).parent().parent().parent().index();
                    var rffr = $(this).parent().siblings().find('h6').html();
                    var id = viewRecord.id;
                    viewRecord = store[ww].list[rr];
                    var layerId = layer.open({
                        type: 2,
                    	title: rffr+"详情",
                        area: ['600px', '550px'],
                        offset: 'auto',
                        content: ['${pageContext.request.contextPath}/manager/view/deploy/detail'+ '?' + id],
                        end: function() {
                        }
                    });
                    
                });  */
                $(".appbtn").find("a").bind("click", function() {                   
                    var ww = $(this).parent().parent().parent().parent().parent().attr('id');
                    var rr = $(this).parent().parent().parent().parent().index();
                    var rffr = $(this).parent().siblings().html();
                    viewRecord = store[ww].list[rr];
                    var id = viewRecord.id;                  
                     if($(this).text() == "查看评价"){
                        var layerId = layer.open({
                            type: 2,
                        	title: rffr+"的评价信息",
                            area: ['600px', '550px'],
                            offset: 'auto',
                            content: ['${pageContext.request.contextPath}/manager/view/deploy/detail'+ '?' + id],
                            end: function() {
                            }
                        });
                    }
                });
            } else {
                layer.alert(res.message);
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
