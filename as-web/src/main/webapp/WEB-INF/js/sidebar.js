/**
 * 侧边栏
 */

var Sidebar = function () {  
	return {
	    goTo: function (id,url,data) {
	    	if(url.indexOf("?")==-1){
	    		url = url + "?v="+(new Date()).getTime();
	    	}else{
	    		url = url + "&v="+(new Date()).getTime();
	    	}
	    	$("#"+id).load(url,data,function(response,status,xhr){
	    	});
	    }		
	}
}();