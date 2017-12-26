var advInitTop = 0;
function inix(){
	advInitTop = document.getElementById("zxjgDiv").style.pixelTop;
}
function move(){
	document.getElementById("zxjgDiv").style.pixelTop = advInitTop+document.documentElement.scrollTop;
}
function showZXdiv(){
	var div = document.getElementById("zxjgDiv");
	div.style.top = (document.documentElement.scrollTop+(document.documentElement.clientHeight-div.offsetHeight)/2)+"px";
	div.style.left = 200;
// alert(div.style.top);
	document.getElementById("zxjgDiv").style.display="block";
// inix();
}
function closeDiv(){
	document.getElementById("zxts").innerHTML="";
	document.getElementById("zxjgDiv").style.display="none";
}
function sav(save){
	if(save=="save1"){
		save1();
		// document.getElementById("sav1").style.display="inline";
		// document.getElementById("sav2").style.display="none";
	}else{
		save2();
		// document.getElementById("sav2").style.display="inline";
		// document.getElementById("sav1").style.display="none";
	}
}

function chgKkd(val){
	if(val.indexOf("0")==0)
		val = val.substring(1);
	var kkd = document.getElementById("sourceCredit");
	
	if(parseInt(val)<6){
		kkd.options[1].selected = true;
	}else if(parseInt(val)<13){
		kkd.options[2].selected = true;
	}else if(parseInt(val)<14){
		kkd.options[3].selected = true;
	}else{
		kkd.options[4].selected = true;
	}
}
function vQq(strMobile) {
	var i,j,strTemp;
	strTemp="0123456789";
	if ( strMobile.length== 0){
		return false;
	}
	for (i=0;i<strMobile.length;i++)
	{
		j=strTemp.indexOf(strMobile.charAt(i));
		if (j==-1)
		{
			alert("QQ号码格式不正确");
			
			return false;
		}
	}
	return true;
	// verifyLength(strMobile,11,"qq号码");
}
function vMsn(strMobile){
	    if ((strMobile.value.indexOf("@")<0)||(strMobile.value.indexOf(":")!=-1))
	          {
	                  alert("MSN格式不正确");
	                  strMobile.focus();
	                  return false;
	          }else
	        	  return true;
}
function vEmailAddress2(strEmail) {
	var myReg = /^[_a-zA-Z0-9_-_._-]+@([_a-zA-Z0-9_-]+\.)+[a-zA-Z]{2,3}$/;
	if(strEmail.value!=""){
		if(!myReg.test(strEmail.value)){
			 alert("Email格式不正确，请重新输入");
					strEmail.focus();
					strEmail.select();
					return false;
		}else
			return true;
	}
}
function genBT(){
	var drugRelatType=document.jbxxForm.drugRelatType;
	var supplyDate=document.jbxxForm.supplyDate;
	var supplyPerson=document.jbxxForm.supplyPerson;
	var clueTitle=document.jbxxForm.clueTitle;
	var xm="";        // 姓名
	for(var i = 0;i<=count_sjr;i++){
		
		if(undefined!=document.getElementsByName("sjr.c_xm["+i+"]")[0]){
			if(""!=document.getElementsByName("sjr.c_xm["+i+"]")[0].value){
				xm=document.getElementsByName("sjr.c_xm["+i+"]")[0].value;
				break;
			}
		}
	}
	if(xm!="")
		clueTitle.value=supplyDate.value+"由"+supplyPerson.value+"提供的"+xm+"等人"+drugRelatType.options[drugRelatType.options.selectedIndex].text+"信息";
	else
		clueTitle.value=supplyDate.value+"由"+supplyPerson.value+"提供的"+drugRelatType.options[drugRelatType.options.selectedIndex].text+"信息";
}
function save2(){
		// alert("2");
		document.all.CXszt.value="";
		// alert("杩囬獙璇�);
		// return false;
		jbxxForm.submit();
}
	function save1(){
		document.all.CXszt.value=1;
		var fpr = document.getElementById("fpr");
		var fby = "";
		if("1"==fby){
			window.open('/clue/dwyh1.action?flag=xslr&csdm='+'340200000000&cxfw=cz','czxz1','height=350,width=630,top=40,left=200,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
			// window.open('/jsp/jdqbyhutil1.jsp?flag=xslr&csdm='+'340200000000'+'&cxfw=cz','czxz1','height=350,width=630,top=40,left=200,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
		}else{
			window.open('/util/JdqbYhUtil.jsp?flag=xslr&csdm='+'340200000000'+'&cxfw=cz','czxz','height=350,width=630,top=40,left=200,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
		}		
	}
	function jxcz(save){
				if(save=="save1"){
					// alert("1");
					save1();
				}else{
					// alert("2");
					save2();
				}
	}
	
	function zxyz(save){
		if(checkfm()){
		var str = "";
		for(i = 0;i<count_sjr;i++){

			var tab2 = document.getElementById("tab_sjr_"+i);
		
			if(tab2){
				var sfzhm = document.getElementById("sjr.c_sfzhm["+i+"]");
				var sfzzl = document.getElementById("sjr.c_sfzzl["+i+"]");
				if(""!=sfzzl.value){
					str+="1:"+sfzzl.value+":"+sfzhm.value+";";
				}
				var tab = document.getElementById("tab_sjr_zjhm_"+i);
				if(tab){
					var sjrzjlx = document.getElementsByName("sjr_zj_"+i+"_lx");
					var sjrzjhm = document.getElementsByName("sjr_zj_"+i+"_hm");
					for(j = 0;j<sjrzjlx.length;j++){
						str+="1:"+sjrzjlx[j].value+":"+sjrzjhm[j].value+";";
						// alert(sjrzjlx[j].value+":"+sjrzjhm[j].value);
					}
				}
				var tab3 =document.getElementById("tab_sjr_p_"+i);
				if(tab3){
					var sjrplx = document.getElementsByName("sjr_p_"+i+"_lx");
					var sjrpnr = document.getElementsByName("sjr_p_"+i+"_nr");
					for(n = 0;n<sjrplx.length;n++){
						str+="2:"+sjrplx[n].value+":"+sjrpnr[n].value+";";
						// alert(sjrplx[n].value+":"+sjrpnr[n].value);
					}
				}
				str = str.substring(0, str.length-1);
				str+="--"
				// alert(sfzzl.value);
				// alert(sfzhm.value);
				
			}
			
		}
		if(str!=""){
			str= str.substring(0, str.length-2);
		}
		
//		var params = $("#jbxxForm").serializeArray(); 
		jbxxForm.submit();
	}
	}
	
	function getYhmsg(values,bs,obj){
		var names=values.split("/");
		var name=names[1];
		var yhbs=names[0];
		var sfzh=names[2];
		document.getElementById("fpr_sp").style.display="";
		var fpr = document.getElementById("fpr");
		var fprxh = document.getElementById("fprxh");
		var fprsfzh = document.getElementById("fprsfzh");
		fpr.value=name;
		fprxh.value=yhbs;
		fprsfzh.value=sfzh;
		if(checkfm()){
			jbxxForm.submit();
		}
	}
	
	

/* 增加涉及人证件各类及号码 */
function addZjhm(tabid){
	var tab = document.getElementById("tab_sjr_zjhm_"+tabid);
	if(tab){
		
	}else{
		// 创建证件类型TAB
		var sjrtab = document.getElementById("tab_sjr_"+tabid);
		var row = sjrtab.insertRow(4);
		var cell = row.insertCell();
		cell.colSpan=4;
		cell.className='xsjbxx_3';
		cell.innerHTML="<table id='tab_sjr_zjhm_"+tabid+"' cellpadding=0 cellspacing=0 width=100% class='xsjbxx_1'>"
		+"<tr><td align=center class='xsjbxx_2' width=40%>证件类型</td><td width=50% class='xsjbxx_2' align=center>证件号码</td><td width=10% class='xsjbxx_2' align=center>操作</td></tr></table>";
		tab = document.getElementById("tab_sjr_zjhm_"+tabid);
	}
	// 创建证件号码内容
	var row = tab.insertRow();
	var cell1 = row.insertCell();
	var cell2 = row.insertCell();
	var cell3 = row.insertCell();
	
	cell1.setAttribute('width','40%');
	cell2.setAttribute('width','50%');
	cell3.setAttribute('width','10%');
	cell1.setAttribute('align','center');
	cell2.setAttribute('align','center');
	cell3.setAttribute('align','center');
	cell1.setAttribute('className','xsjbxx_3');
	cell2.setAttribute('className','xsjbxx_3');
	cell3.setAttribute('className','xsjbxx_3');
	
	cell1.innerHTML="<div id='select0'><select name='sjr_zj_"+tabid+"_lx' style='width:95%'><option value=''></option><option value='1'>居民身份证</option><option value='10'>回乡证</option><option value='11'>居留证件</option><option value='12'>其他</option><option value='13'>往来港澳通行证</option><option value='14'>大陆居民前往台湾通行证</option><option value='2'>组织机构代码证书</option><option value='3'>军官证</option><option value='4'>士兵证</option><option value='5'>军官离退休证</option><option value='6'>中国护照</option><option value='7'>外国护照</option><option value='8'>居民户口簿</option><option value='9'>旅行证</option></select>";
	cell2.innerHTML="<input type='text' name='sjr_zj_"+tabid+"_hm' style='width:95%'>";
	cell3.innerHTML="<input type='button' name='button_del_sjr_zj_"+tabid+"' class='Button' value='删除' style='width:70%' onClick=\"delSJR_ZJ('"+tabid+"',this)\"></div>";
}



// 删除人员证件信息
function delSJR_ZJ(idx_sjr,delbutton){
	var delb = document.getElementsByName("button_del_sjr_zj_"+idx_sjr);// 取所有人员属性删除按钮
    var tab = document.getElementById("tab_sjr_zjhm_"+idx_sjr);
	if(delb==null){
		return;
	}
	for(var i=0;i<delb.length;i++){// 遍历所有删除按钮
		if(delb[i]==delbutton){// 取到当前删除按钮位置，通过其所在表行索引进行删除
			tab.deleteRow(i+1);
		}
	}
}

var count_sjr_ext = 0;

/* 增加涉及人属性信息，包括电话、车牌号、QQ、MSN等 */
function addSJR_P(idx_sjr){
	var tab =document.getElementById("tab_sjr_p_"+idx_sjr);
	if(tab==null){
		alert('未找到人员信息');
		return;
	}
	var row = tab.insertRow();
	var cell = row.insertCell();// 类型
	cell.setAttribute('className','xsjbxx_3');
	cell.setAttribute('align','left');
	cell.innerHTML=
		"<input type=hidden name='sjr_p_"+idx_sjr+"_xh'/><div id='select0'>" +
				"<select name='suspectInfos["+idx_sjr+"].suspectExtInfos["+count_sjr_ext+"].type' onchange=\"\" >" +
						"<option value=''></option><option value='2002'>MSN</option>" +
						"<option value='2003'>EMAIL</option><option value='2004'>QQ</option>" +
						"<option value='2005'>车牌</option><option value='2007'>银行卡账号</option>" +
						"<option value='2009'>电话</option><option value='2099'>其他号码</option>" +
						"<option value='2100'>房产</option></select></div>";
	cell = row.insertCell();// 内容
	cell.setAttribute('className','xsjbxx_3');
	cell.setAttribute('align','left');
	// cell.setAttribute('width','100%');
	cell.innerHTML=
		"<div id='div_sjr_p_hm_"+idx_sjr+"' style='width:100%'>"
		+"<input type='hidden' name='sjr_p_"+idx_sjr+"_hmlx'>" +
				"<input type='text' name='suspectInfos["+idx_sjr+"].suspectExtInfos["+count_sjr_ext+"].number' style='width:95%'></div>";
	cell = row.insertCell();// 备注
	cell.setAttribute('className','xsjbxx_3');
	cell.setAttribute('align','left');
	cell.innerHTML=
		"<input type='text' name='suspectInfos["+idx_sjr+"].suspectExtInfos["+count_sjr_ext+"].mark' style='width:95%'>";
	cell = row.insertCell();// 操作
	cell.setAttribute('className','xsjbxx_3');
	cell.setAttribute('align','center');
	cell.innerHTML=
		"<input type=button class='Button' name='button_del_sjr_p_"+idx_sjr+"' value='删除' onClick=\"delSJR_P('"+idx_sjr+"',this)\">";
	
	count_sjr_ext++;
	document.all.count_sjr_ext.value=count_sjr_ext;
	
}

function chgSjrHmLx(obj,idx){

	var sel = document.getElementsByName("sjr_p_"+idx+"_lx");
	var div_p = document.getElementsByName("div_sjr_p_hm_"+idx);
	if(obj.value=='2005'){
		for(var i=0;i<sel.length;i++){
			if(sel[i]==obj){
				
				div_p[i].innerHTML="<div id='select0'><select style='width:40%' name='sjr_p_"+idx+"_hmlx'><option value=''></option><option value='1'>大型汽车</option><option value='10'>两轮摩托</option><option value='11'>三轮摩托</option><option value='12'>轻便摩托</option><option value='13'>其他</option><option value='2'>大客车</option><option value='3'>大货车</option><option value='4'>小型汽车</option><option value='5'>吉普型小客车</option><option value='6'>旅行型小客车</option><option value='7'>轿车型小客车</option><option value='8'>小货车</option><option value='9'>微型车</option></select><input type='text' name='sjr_p_"+idx+"_nr' style='width:35%'>"
				+"<input type='hidden' name='sjr_p_"+idx+"_khd' onclick='clearValue(this,\"请在此输入开户地\")' value='请在此输入开户地'><a href='javascript:getCLInfo("+idx+","+i+")'>车辆信息</a></div>"
			}
		}
	}else if(obj.value=='2100'){
		for(var i=0;i<sel.length;i++){
			if(sel[i]==obj){
				
				div_p[i].innerHTML="<div id='select0'><select style='width:50%' name='sjr_p_"+idx+"_hmlx'><option value=''></option><option value='3000'>住宅</option><option value='3001'>商铺</option></select><input type='text' name='sjr_p_"+idx+"_nr' style='width:45%'>"
				+"<input type='hidden' name='sjr_p_"+idx+"_khd' onclick='clearValue(this,\"请在此输入开户地\")' value='请在此输入开户地'></div>"
			}
		}
	}else if(obj.value=='2007'){
		for(var i=0;i<sel.length;i++){
			if(sel[i]==obj){
				div_p[i].innerHTML="<div id='select0'><select style='width:20%' name='sjr_p_"+idx+"_hmlx'><option value=''></option><option value='1'>存折</option><option value='2'>借计卡</option><option value='3'>信用卡</option></select>"
					+"<input type='text' name='sjr_p_"+idx+"_khd' onclick='clearValue(this,\"请在此输入开户地\")' value='输入开户地' style='width:35%'>"
					+"<input type='text' name='sjr_p_"+idx+"_nr' onclick='clearValue(this,\"请在此输入账号\")' value='输入账号' style='width:35%'></div>"
				
			}
		}
	}else if(obj.value=='2009'){
		for(var i=0;i<sel.length;i++){
			if(sel[i]==obj){
				div_p[i].innerHTML="<input type='hidden' name='sjr_p_"+idx+"_hmlx'><input type='text' name='sjr_p_"+idx+"_nr' style='width:55%'>"
				+"<input type='hidden' name='sjr_p_"+idx+"_khd' onclick='clearValue(this,\"请在此输入开户地\")' value='请在此输入开户地'><a href='javascript:getHMInfo("+idx+","+i+")'>号码归属地</a>&nbsp;<a href=\"javascript:void(0);\" onclick=\"jumpOut()\">话单导入</a>&nbsp;<a href=\"javascript:void(0);\" onclick=\"jumpOut(1)\">通讯录导入</a>"
				
			}
		}
	}else{
		for(var i=0;i<sel.length;i++){
			if(sel[i]==obj){
				div_p[i].innerHTML="<input type='hidden' name='sjr_p_"+idx+"_hmlx'><input type='text' name='sjr_p_"+idx+"_nr' style='width:95%'>"
				+"<input type='hidden' name='sjr_p_"+idx+"_khd' onclick='clearValue(this,\"请在此输入开户地\")' value='请在此输入开户地'>"
			}
		}
	}
	
}

function jumpOut(txl){
if(txl==1){
	var url = "/jsp/zygl/hddr/txxx_import.jsp?tab=JDQB_HDFX&dck=1&bs=2";
}else{
	var url = "/jsp/zygl/hddr/txxx_import.jsp?tab=JDQB_HDFX&dck=1";
}
	
	window.open(url,"","");
}

function clearValue(obj,checkvalue){
	if(obj.value==checkvalue){
		obj.value="";
	}
}
function delSJR(delbutton){
	var delb = document.getElementsByName("button_del_sjr");// 取所有人员属性删除按钮
    var tab = document.getElementById("tab_sjr");
	if(delb==null){
		return;
	}
	for(var i=0;i<delb.length;i++){// 遍历所有删除按钮
		if(delb[i]==delbutton){// 取到当前删除按钮位置，通过其所在表行索引进行删除
			tab.deleteRow(i);
			var delry = document.getElementById("del_ryidx").value;
			delry=delry+i+",";
		}
	}
	// 变换背景色
	chgSjrColor();
}
// 删除人员属性信息
function delSJR_P(idx_sjr,delbutton){
	var delb = document.getElementsByName("button_del_sjr_p_"+idx_sjr);// 取所有人员属性删除按钮
    var tab = document.getElementById("tab_sjr_p_"+idx_sjr);
	if(delb==null){
		return;
	}
	for(var i=0;i<delb.length;i++){// 遍历所有删除按钮
		if(delb[i]==delbutton){// 取到当前删除按钮位置，通过其所在表行索引进行删除
			tab.deleteRow(i+1);
		}
	}
}

function addXSFJ(){
	var tab = document.getElementById("tab_fj");
	var row = tab.insertRow();

	var cell = row.insertCell();
	cell.setAttribute("width","100%");
	cell.setAttribute("className","xsjbxx_3");
	cell.setAttribute("algin","left");
	cell.innerHTML="<table class='xsjbxx_1' width=100% cellpadding=0 cellspacing=0>"
		+"<tr><td width=40% class='xsjbxx_2' align=center><input type=file style='width:99%' name='c_fj' >"
		+"<td width=12% align=center class='xsjbxx_2'><input style='width:85%' type='button' name='button_del_fj'  value='删除' onClick='delXSFJ(this)'></td>"
		+"<td class='xsjbxx_2' align=left width=9%>备注</td>"
		+"<td width=20%><textarea name='fjbz' cols='12'></textarea></td>"
		+"</tr>"
		+"</table>";
}
function delXSFJ(delbutton){
	var delb = document.getElementsByName("button_del_fj");// 取所有附件删除按钮
    var tab = document.getElementById("tab_fj");
	if(delb==null){
		return;
	}
	for(var i=0;i<delb.length;i++){// 遍历所有删除按钮
		if(delb[i]==delbutton){// 取到当前删除按钮位置，通过其所在表行索引进行删除
			tab.deleteRow(i);
		}
	}
}
// 显示人员照片层
function showRYZP(idx_sjr){
	var div = document.getElementById("zp_div_"+idx_sjr);
	div.style.display = "block";
}
// 增加人员照片-显示
function showimg(idx_sjr,obj){
	var ryimg = document.getElementsByName("img_ry_"+idx_sjr);
	var zpfj = document.getElementsByName("c_zp_"+idx_sjr);
	for(var i=0;i<zpfj.length;i++){
		if(zpfj[i]==obj){
			ryimg[i].src=obj.value;
		}
	}
}
// 确定人员照片选择，隐藏照片层
function hiddenZP(idx_sjr){
	var ryimg = document.getElementsByName("img_ry_"+idx_sjr);
	var div = document.getElementById("zp_div_"+idx_sjr);
	
	var pic = document.getElementById("pic_"+idx_sjr);
	var ishas = false;
	// alert("11111111111111"+ryimg.length);
	for(var i=0;i<ryimg.length;i++){
		if(ryimg[i].src != 'http://qbyp.jd.ga//img/zwtp.jpg'){
	if(ryimg[i]!=null&&ryimg[i]!=undefined&&ryimg[i]!=''){
	// alert("进入if"+decodeURI(ryimg[i].src));
	var s = parseFloat(FileSize(decodeURI(ryimg[i].src.replace("file:///",""))));
				if(s>30){
					alert("上传照片不能大于30KB");
					return false;
				}
		}
			pic.src = ryimg[i].src;
			ishas = true;
			break;
	}
	}
	if(!ishas)
		pic.src = 'http://qbyp.jd.ga//img/zwtp.jpg';
	div.style.display = "none";
	return;
}
function DelAddZp(idx_sjr,obj){
	var delbutton = document.getElementsByName("delbutton_sjr_"+idx_sjr);
	var tab = document.getElementById("zptab_"+idx_sjr);
	for(var i=0;i<delbutton.length;i++){
		if(delbutton[i]==obj){
			if(i>2){
				tab.rows[0].cells[i-3].innerHTML=
					"		<img name='img_ry_"+idx_sjr+"' src='http://qbyp.jd.ga//img/zwtp.jpg' width=100 height=100>"
					+"		<br/><textarea cols='15' rows='2' name ='zpbz_"+count_sjr+"'></textarea>"
					+"		<BR><input type=file size='8' width='25' name='c_zp_"+idx_sjr+"' onChange='showimg("+idx_sjr+",this)'>"
					+"		<input type=button name='delbutton_sjr_"+idx_sjr+"' width='25' align='right' onClick='DelAddZp("+idx_sjr+",this)' value='删除照片'>"
		
			}else{
				tab.rows[0].cells[i].innerHTML=
					"		<img name='img_ry_"+idx_sjr+"' src='http://qbyp.jd.ga//img/zwtp.jpg' width=100 height=100>"
					+"		<br/><textarea cols='15' rows='2' name ='zpbz_"+count_sjr+"'></textarea>"
					+"		<BR><input type=file size='8' width='25' name='c_zp_"+idx_sjr+"' onChange='showimg("+idx_sjr+",this)'>"
					+"		<input type=button name='delbutton_sjr_"+idx_sjr+"' width='25' align='right' onClick='DelAddZp("+idx_sjr+",this)' value='删除照片'>"
		
			}
		}
	}
}

// 根据身份证号取出生日期、性别、户籍地
	// 根据身份证号自动生成年龄
function setAge(sfz,idx_sjr){
	
	var str=sfz.value;// 身份证编码
    var rq = str.substr(6,4) + "-" + str.substr(10,2) + "-" +str.substr(12,2);
    document.getElementById("sjr.d_cs["+idx_sjr+"]").value = rq;
}

// 根据身份证号码获取性别
function getSexFromID(sfzh,idx_sjr){
	var c_sex = "";
	if(sfzh.value.length==15){
		if(parseInt(sfzh.value.substring(14,15))%2!='0')
			c_sex = "1";
		else if(parseInt(sfzh.value.substring(14,15))%2=='0')
			c_sex = "2";
	    else
	        c_sex = "0";
	}else{
		if(parseInt(sfzh.value.substring(16,17))%2!='0')
			c_sex = "1";
		else if(parseInt(sfzh.value.substring(16,17))%2=='0')
			c_sex = "2";
		else
	        c_sex = "0";
	}
	var xb = document.getElementById("sjr.c_xb["+idx_sjr+"]");
	
	for(var i=0;i<xb.options.length;i++){
		if(xb.options[i].value==c_sex){
			xb.options[i].selected = true;
			break;
		}
	}

} 

function setXzqh(sfz,idx_sjr){
	// 身份证编码
    var str=sfz.value;
　　 var qh = str.substr(0,6);
　　 var action="http://qbyp.jd.ga/jsp/wdzm/wdgz/getjg.do?xzqh="+qh;
    $.post(action,{next:Math.random()},function(data){
		pageStr = data;
		document.getElementById("sjr.c_hjszd["+idx_sjr+"]").value = qh;
		document.getElementById("sjr.c_hjszd_["+idx_sjr+"]").value = data;
	});
}

function sfzzd(idx_sjr){
	// 取人员证件种类及证件号码
	var zjzl = document.getElementById("sjr.c_sfzzl["+idx_sjr+"]");
	var zjhm = document.getElementById("sjr.c_sfzhm["+idx_sjr+"]");
	// alert(zjzl.value);
	// alert(zjzl.options[zjzl.options.selectedIndex].value);
	if(zjzl.options[zjzl.options.selectedIndex].value=='1'){// 证件种类为身份证
//pengxiaolu 暂时		var i=isIdCardNo(zjhm.value,zjhm);// 验证身份证格式
//	    if(i==true&&zjhm.value!=null&&zjhm.value!=""){
//	        setAge(zjhm,idx_sjr);
//		    getSexFromID(zjhm,idx_sjr);
//		    setXzqh(zjhm,idx_sjr);
//		}
	}
}

/*---------------------------------------------------------------------*/
function getElementPos(obj)   
{   
    var left = 0;   
    var top = 0;   
  
    if(obj.x)   
    {   
        left= obj.x;   
        top = obj.y;   
    }else if(obj.offsetParent)   
    {      
        while(obj.offsetParent)   
        {    
            left += obj.offsetLeft;   
            top  += obj.offsetTop;   
            obj = obj.offsetParent;   
        }    
    }   
  
    return [left,top];// 封装在一个数组里
}   
function showDiv(objid_)   
{   
    var mydiv = document.getElementById("div_xzqh");   
    var obj = document.getElementById(objid_);
    var pos = getElementPos(obj);
    mydiv.style.display = "";   
    
    mydiv.style.left = pos[0];   
    mydiv.style.top = pos[1] + obj.offsetHeight+2;   
}

var xmlHttp;
var targetObject;
	// 当浏览器启动时创建对象
	function createXMLHttpRequest() {
		if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		}
	}
	// 定义请求函数(控件ID，显示控件ID)
	function getXzqhByPy(objid,objid_) {
		createXMLHttpRequest();
		// var url = "dicinfo.action?dm=xzqh&py=ln&ws=3";
		var mydiv = document.getElementById("div_xzqh");
		if(mydiv==null){
			mydiv = document.createElement("<div id=\'div_xzqh\' style=\'position:absolute; display:none; background:#FFFFFF; height:100;width:150;border: 1px solid #2D6C96;\' ></div>");
			document.body.appendChild(mydiv);
		}
		var obj = document.getElementById(objid_);
		var url="http://qbyp.jd.ga/jsp/xtgl/dicinfo.do?dm=xzqh&ws=5&py="+obj.value;
		xmlHttp.onreadystatechange = function(){return handleStateChange(objid,objid_)};	// 调用回调函数
		xmlHttp.open("GET", url, true);
		xmlHttp.send(null);
	}
	// 回调函数
	function handleStateChange(objid,objid_) {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var xmlDOM = xmlHttp.responseText;
				// 得到操作名称
				var str=xmlDOM.replace(/(^\s*)|(\s*$)/g, "");
				var mydiv = document.getElementById("div_xzqh");
				mydiv.innerHTML = "";
				var a_d = str.split(",");
				for(var i=0;i<a_d.length;i++){
					if(a_d[i].length>0){
						var idx = a_d[i].indexOf("|");
						if(idx!=-1){
							var str1 = a_d[i].substr(0,idx);
							var str2 = a_d[i].substr(idx+1);
							mydiv.innerHTML +="<div style=\"width:150px;height:19px;background:#FFFFFF;cursor:arrow\" onMouseover=\"this.style.background='#316AC5'\" onMouseout=\"this.style.background='#FFFFFF'\" onClick=setXzqhByPy('"+objid+"','"+objid_+"','"+str2+"','"+str1+"')>"+str2+"</div>";
							
						}
					}
				}
				
				mydiv.innerHTML += div_frame;
				showDiv(objid_);
			}
		}
	}
	
	function setXzqhByPy(objid,objid_,text,val){
		
		var o = document.getElementById(objid);
		var o_ = document.getElementById(objid_);
		o.value=val;
		o_.value=text;
		var mydiv = document.getElementById("div_xzqh");
		mydiv.innerHTML = "";
		mydiv.style.display="none";
	}
	function clearXzqhDiv(){
		/*
		 * var mydiv = document.getElementById("div_xzqh"); if(mydiv!=null){
		 * mydiv.innerHTML = ""; mydiv.style.display="none"; }
		 */
	}
var div_frame = "<iframe style=\"position:absolute; visibility:inherit; top:0px; left:0px; width:150px; height:100px; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)'\"></iframe>";
// 人员表格变色
function chgSjrColor(){
	var idx = -1;
	for(var i=0;i<=count_sjr;i++){
		var tab = document.getElementById("tab_sjr_"+i);
		
		if(tab)
			idx ++;
		if(idx%2==0){
			if(tab){
				var r = tab.rows.length;
				for(var j=0;j<r;j++){
					var c = tab.rows.item(j).cells.length;
					for(var m=0;m<c;m++){
						if(tab.rows.item(j).cells.item(m).className=="xsjbxx_2" 
							||tab.rows.item(j).cells.item(m).className=="xsjbxx_2_")
							tab.rows.item(j).cells.item(m).setAttribute("className","xsjbxx_2_");
					}
				}
				
			}
			var tab_hm = document.getElementById("tab_sjr_p_"+i);
			
			if(tab_hm){
				var r = tab_hm.rows.length;
				for(var j=0;j<r;j++){
					var c = tab_hm.rows.item(j).cells.length;
					for(var m=0;m<c;m++){
						if(tab_hm.rows.item(j).cells.item(m).className=="xsjbxx_2"
							||tab_hm.rows.item(j).cells.item(m).className=="xsjbxx_2_")
							tab_hm.rows.item(j).cells.item(m).setAttribute("className","xsjbxx_2_");
					}
				}
				
			}
			var tab_ysa = document.getElementById("tab_sjr_ysa_"+i);
			if(tab_ysa){
				var r = tab_ysa.rows.length;
				for(var j=0;j<r;j++){
					var c = tab_ysa.rows.item(j).cells.length;
					for(var m=0;m<c;m++){
						if(tab_ysa.rows.item(j).cells.item(m).className=="xsjbxx_2"
							||tab_ysa.rows.item(j).cells.item(m).className=="xsjbxx_2_")
							tab_ysa.rows.item(j).cells.item(m).setAttribute("className","xsjbxx_2_");
					}
				}
				
			}
		}else if(idx%2==1){
			if(tab){
				var r = tab.rows.length;
				for(var j=0;j<r;j++){
					var c = tab.rows.item(j).cells.length;
					for(var m=0;m<c;m++){
						if(tab.rows.item(j).cells.item(m).className=="xsjbxx_2" 
							||tab.rows.item(j).cells.item(m).className=="xsjbxx_2_")
							tab.rows.item(j).cells.item(m).setAttribute("className","xsjbxx_2");
					}
				}
				
			}
			var tab_hm = document.getElementById("tab_sjr_p_"+i);
			
			if(tab_hm){
				var r = tab_hm.rows.length;
				for(var j=0;j<r;j++){
					var c = tab_hm.rows.item(j).cells.length;
					for(var m=0;m<c;m++){
						if(tab_hm.rows.item(j).cells.item(m).className=="xsjbxx_2"
							||tab_hm.rows.item(j).cells.item(m).className=="xsjbxx_2_")
							tab_hm.rows.item(j).cells.item(m).setAttribute("className","xsjbxx_2");
					}
				}
				
			}
			var tab_ysa = document.getElementById("tab_sjr_ysa_"+i);
			if(tab_ysa){
				var r = tab_ysa.rows.length;
				for(var j=0;j<r;j++){
					var c = tab_ysa.rows.item(j).cells.length;
					for(var m=0;m<c;m++){
						if(tab_ysa.rows.item(j).cells.item(m).className=="xsjbxx_2"
							||tab_ysa.rows.item(j).cells.item(m).className=="xsjbxx_2_")
							tab_ysa.rows.item(j).cells.item(m).setAttribute("className","xsjbxx_2");
					}
				}
				
			}
		}
	}
}
function getCKInfo(idx){
	sfzh = document.getElementsByName("sjr.c_sfzhm["+idx+"]");
	if(sfzh[0].value == null || sfzh[0].value == ""){
		alert("请输入身份证号!");
	}else{
		window.open("/util/showCKInfo.jsp?target="+idx+"&sfzh="+sfzh[0].value,"","");
	}
}

function getCLInfo(idx,m){// idx:当前涉及人序号，m为号码在所有附属信息中的序号
	var hm ;// 定义号码
	var hms = document.getElementsByName("sjr_p_"+idx+"_nr");// 取涉及人所有号码
	hm = hms[m].value;// 根据序号取选择的号码
	// if(hm.length<7)
		// alert("只能查询手机号码归属地");
	// else
	window.open("/util/showCLInfo.jsp?target1="+idx+"&target2="+m+"&hphm="+hm,"","");
}

function getHMInfo(idx,m){// idx:当前涉及人序号，m为号码在所有附属信息中的序号
	var hm ;// 定义号码
	var hms = document.getElementsByName("sjr_p_"+idx+"_nr");// 取涉及人所有号码
	hm = hms[m].value;// 根据序号取选择的号码
	if(hm.length<7)
		alert("只能查询手机号码归属地");
	else
	window.open("/util/showHMInfo.jsp?target1="+idx+"&target2="+m+"&hm="+hm,"","");
}
function setValue(result, index){
	strs = result.split("|");
	(document.getElementsByName("sjr.c_xm["+index+"]"))[0].value = (strs[0].split(":"))[1];
	csrq = (strs[13].split(":"))[1];
	csrq = csrq.substring(0,4) + "-" + csrq.substring(4,6) + "-" + csrq.substring(6,8);
	(document.getElementsByName("sjr.d_cs["+index+"]"))[0].value = csrq;
	(document.getElementsByName("sjr.c_sjjzdxz["+index+"]"))[0].value = (strs[11].split(":"))[1];
	(document.getElementsByName("sjr.c_hjszd_["+index+"]"))[0].value = (strs[7].split(":"))[1];
	xb = (document.getElementsByName("sjr.c_xb["+index+"]"))[0].options;
	for(i = 0; i < xb.length; i++){
		if(xb[i].text == (strs[2].split(":"))[1]){
			xb[i].selected = 'selected';
			break;
		}
	}
	mz = (document.getElementsByName("sjr.c_mz["+index+"]"))[0].options;
	for(i = 0; i < mz.length; i++){
		if(mz[i].text == (strs[3].split(":"))[1]){
			mz[i].selected = 'selected';
			break;
		}
	}
	sfzzl = (document.getElementsByName("sjr.c_sfzzl["+index+"]"))[0].options;
	for(i = 0; i < sfzzl.length; i++){
		if(sfzzl[i].text == "居民身份证"){
			sfzzl[i].selected = 'selected';
			break;
		}
	}
}
