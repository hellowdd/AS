//定义好的正则表达式
var regObj = {
	//应用id，由A-Z的大写字母组成，或附加0-9的数字组合（数字组合不是必须）。 
	appID:/^[A-Z]{1,}[0-9]{0,}$/,
	//这个很长的是年份的正则表达式
	date:/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/,
	//中文名称，50个汉字
	ch:/^[\u4e00-\u9fa5]+$/,
	//中文全拼，最对128字符，中间用“-”分割
	zhspell:/^([a-z]{1,6}-?){0,}[a-z]{1,6}$/,
	upletter:/^[A-Z]+$/,
	version:/^v([\d]{1,6}\.+){0,}\d{1,7}$/,
	varchar:/^[\u4e00-\u9fa5A-Za-z0-9 ]+$/,
	engAndNum:/^[A-Za-z0-9]+$/,
	number:/^[0-9]{1,}$/,
	ip:/^(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)$/,
	// port:/^\d{1,4}$|^([1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/,
	port:/^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/,
	//只能输入数字字母下划线并且不能以下划线结尾，不能为全数字（既然不能为数字开头了，肯定不是能全数字）
	tab:/^[a-zA-Z_][a-zA-Z0-9_]{0,}$/,
	//中文，字母，数字组成的字符串，不要求三者同时出现
	chznun:/^[a-zA-Z0-9\u4e00-\u9fa5]+$/
     
};

//绑定事件，失去焦点检验
function autoCheckForm(id){
	var inputs = $("#"+id).find("input");
	var textareas = $("#"+id).find("textarea");
	var select = $("#"+id).find("select");
	inputs.on("blur",function(){
		$(this).val($.trim($(this).val()));
		checkItem($(this));
	});
	textareas.on("blur",function(){
		checkItem($(this));
	});
	select.on("blur",function(){
		checkItem($(this));
	});
}

//提交时全部检验
var canSubmit = true;
function checkForm(id){
	canSubmit = true;
	var inputs = $("#"+id).find("input");
	var textareas = $("#"+id).find("textarea");
	var select = $("#"+id).find("select");
	for(var i=0;i<inputs.length;i++){
		checkItem(inputs.eq(i));
	}
	for(var i=0;i<textareas.length;i++){
		checkItem(textareas.eq(i));
	}
	for(var i=0;i<select.length;i++){
		checkItem(select.eq(i));
	}
}

function checkItem(jqObj){
	if(jqObj.attr("len")){
		var len = parseInt(jqObj.attr("len"));
		if(jqObj.attr("requried")&&jqObj.val()==""){
			if("blank" != jqObj.attr("errortype")){
				jqObj.attr("errortype","blank");
				jqObj.parent().addClass("has-error");
				jqObj.tooltip("destroy");
				jqObj.tooltip({
					title:"该项问必填项",
					animation:false,
					placement:"bottom"
				});
				jqObj.tooltip("show");
			}
			canSubmit = false;
			return;
		}
		if(jqObj.val().replace(/[\u4e00-\u9fa5]/g,"aa").length>len){
			if("outLen" != jqObj.attr("errortype")){
				jqObj.attr("errortype","outLen");
				jqObj.parent().addClass("has-error");
				jqObj.tooltip("destroy");
				jqObj.tooltip({
					title:"长度超过限制,最多"+len+"个字符",
					animation:false,
					placement:"bottom"
				});
				jqObj.tooltip("show");
			}
			canSubmit = false;
			return;
		}
	}
	if(jqObj.attr("reg")){
		if(jqObj.val()!=""&&jqObj.val().match(regObj[jqObj.attr("reg")])==null){
			if("regError" != jqObj.attr("errortype")){
				jqObj.attr("errortype","regError");
				jqObj.parent().addClass("has-error");
				jqObj.tooltip("destroy");
				jqObj.tooltip({
					title:jqObj.attr("tip"),
					animation:false,
					placement:"bottom"
				});
				jqObj.tooltip("show");
			}
			canSubmit = false;
			return;
		}
	}
	jqObj.attr("errortype","");
	jqObj.parent().removeClass("has-error");
	jqObj.tooltip("destroy");
}
