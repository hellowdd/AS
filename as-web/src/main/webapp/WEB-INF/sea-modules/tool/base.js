//将null转化为"",同时可截取字符串长度
function strFormat(str,len){
	if(str){
		if(len&&str.length>10){
			return str.substring(0,len)+"...";
		}else{
			return str;
		}		
	}else{
		return "";
	}
}
//判断是否是字母及下划线组成，首字母不得为下划线
function checkCode(str){
	if(""==str){
		return false;
	}
	for(var i=0;i<str.length;i++){ 
		var c = str.charAt(i);
		if(i==0){
			if((c=="_")&&(c<"a"||c>"z")&&(c<"A"||c>"Z")){
				return false;
			}
		}else{
			if((c!="_")&&(c<"a"||c>"z")&&(c<"A"||c>"Z")){
				return false;
			}
		}
	}
	return true;
}

function getLen(val) {
	var len = 0;
    for (var i = 0; i < val.length; i++) {
    	var patt = new RegExp(/[^\x00-\xff]/ig);
    	var a = val[i];
    	if (patt.test(a)){
    		len += 2;
    	}
    	else{
    		len += 1;
    	}
	}
	return len;
}

function fillForm(josn,formId){
	var inps = $("#"+formId).find("input");
	inps.each(function(i){
		inps.eq(i).val(josn[this.id]);
	});
	var textareas = $("#"+formId).find("textarea");
	textareas.each(function(i){
		textareas.eq(i).val(josn[this.id]);
	});
	var select = $("#"+formId).find("select");
	select.each(function(i){
		select.eq(i).val(josn[this.id]);
	});	
}

function dateFormat(date){
	var year = date.getFullYear();
	var month = (date.getMonth()+1)<10?"0"+(date.getMonth()+1):(date.getMonth()+1);
	var day = date.getDate()<10?"0"+date.getDate():date.getDate();
	return year +"-"+month+"-"+day;
}
function dateFormathms(date){
	var year = date.getFullYear();
	var month = (date.getMonth()+1)<10?"0"+(date.getMonth()+1):(date.getMonth()+1);
	var day = date.getDate()<10?"0"+date.getDate():date.getDate();
	var hour = date.getHours()<10?"0"+date.getHours():date.getHours();
	var min = (date.getMinutes())<10?"0"+(date.getMinutes()):(date.getMinutes());

	var second = (date.getSeconds())<10?"0"+(date.getSeconds()):(date.getSeconds());
	return year +"-"+month+"-"+day+" "+hour+":"+min+":"+second;
}
//格式化日期，返回日期格式
function strToDate(str){
	var list = str.split("-");
	return new Date(list[0],parseInt(list[1])-1,list[2]);
}
