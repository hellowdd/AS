/**
 * 命名规则： 人员控件为"sjr."+字段名+"["+索引+"]"命名方式，如人员姓名:"sjr.c_xm[0]"；
 * 人员属性信息以'sjr_p_'+人员信息索引+字段名，如资料类型:"sjr_p_1_lx"
 */
var count_sjr = 0;

function addSJR(){
	var tab = document.getElementById("tab_sjr");
	var row = tab.insertRow();
	var count_sjr2 = count_sjr+1;
	var cell = row.insertCell();
	cell.setAttribute("width","100%");
	cell.setAttribute("className","xsjbxx_3");
	cell.setAttribute("algin","left");
	cell.innerHTML="<fieldset class='st'><legend>涉及人员"+count_sjr2+"</legend>"
		+"<table id='tab_sjr_"+count_sjr+"' class='xsjbxx_1' width=100% cellpadding=0 cellspacing=0>"
		+"<tr><td class='xsjbxx_2' colspan=6><input type=hidden name='issjr_"+count_sjr+"' value='1'>" +
				"<a href=\"javascript:getCKInfo("+count_sjr+")\">信息补全</a>" +
						"<input type=button name='button_del_sjr' class='Button' value='删除' onClick='delSJR(this)'></td></tr>"
		+"<tr><td align=left width=12% class='xsjbxx_2'>姓名</td><td width=25% align=left class='xsjbxx_3'><input type='text' name='suspectInfos["+count_sjr+"].name' id='sjr.c_xm["+count_sjr+"]'  style='width:95%' value='' onchange='genBT()'></td>"
		+"<td align=left width=12% class='xsjbxx_2'>绰号&nbsp;</td><td width=30% align=left class='xsjbxx_3'><input type='text' name='suspectInfos["+count_sjr+"].nickname' id='sjr.c_ch["+count_sjr+"]' style='width:95%' value=''></td>"
		+"<td width=20% rowspan=7 style='border-bottom-width: 1px;border-bottom-style: solid;border-bottom-color: #94CDF0;'><img id='pic_"+count_sjr+"' src='http://qbyp.jd.ga//img/zwtp.jpg' style='width:110px;height:100px;' onclick='showRYZP(" + count_sjr + ")' /></td>"
		+"</tr>"
		
		+"<tr><td align=left width=10% class='xsjbxx_2'>证件类型</td><td align=left class='xsjbxx_3'><div id='select0'><select name='sjr.c_sfzzl["+count_sjr+"]' id='sjr.c_sfzzl["+count_sjr+"]' style='width:95%'><option value=''></option><option value='1'>居民身份证</option><option value='10'>回乡证</option>" +
				"<option value='11'>居留证件</option><option value='12'>其他</option><option value='13'>往来港澳通行证</option><option value='14'>大陆居民前往台湾通行证</option><option value='2'>组织机构代码证书</option><option value='3'>军官证</option><option value='4'>士兵证</option><option value='5'>军官离退休证</option>" +
				"<option value='6'>中国护照</option><option value='7'>外国护照</option><option value='8'>居民户口簿</option><option value='9'>旅行证</option></select></div></td>"
		+"<td align=left width=10% class='xsjbxx_2'>证件号码</td>"
		+"<td align=left class='xsjbxx_3'><input type='text' id='sjr.c_sfzhm["+count_sjr+"]' name='sjr.c_sfzhm["+count_sjr+"]' style='width:85%' value='' onfocusout=''>"
		+"<img title='点击添加多个号码' src='http://qbyp.jd.ga/images/add_1.gif' style='cursor:hand' onclick='addZjhm("+count_sjr+")'/></td>"
		+"</tr>"
		
		+"<tr><td align=left width=10% class='xsjbxx_2'>性别</td><td align=left class='xsjbxx_3'><div id='select0'><select name='suspectInfos["+count_sjr+"].gender' id='sjr.c_xb["+count_sjr+"]' style='width:95%'><option value=''></option><option value='1'>男</option><option value='2'>女</option></select></div></td>"
		+"<td align=left width=10% class='xsjbxx_2'>出生日期</td><td align=left class='xsjbxx_3'><input type='text' name='suspectInfos["+count_sjr+"].birthday' id='sjr.d_cs["+count_sjr+"]' style='width:95%' value='' onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})\" class='Wdate'></td>"
		+"</tr>"
		+"<tr><td align=left width=10% class='xsjbxx_2'>民族</td><td align=left class='xsjbxx_3'><div id='select0'><select name='suspectInfos["+count_sjr+"].nation' id='sjr.c_mz["+count_sjr+"]' style='width:95%'><option value=''></option><option value='03'> 回族</option><option value='01'> 汉族</option>" +
				"<option value='06'> 苗族</option><option value='97'>其他</option><option value='9A'>外籍人员</option><option value='05'> 维吾尔族</option><option value='07'> 彝族</option><option value='08'> 壮族</option><option value='04'> 藏族</option></select></div></td>"
		+"<td align=left width=10% class='xsjbxx_2'>国籍</td><td align=left class='xsjbxx_3'><div id='select0'><select name='suspectInfos["+count_sjr+"].nationality' id='sjr.c_gj["+count_sjr+"]' style='width:95%'><option value=''></option><option value='020'>安道尔</option><option value='040'>奥地利</option>" +
				"<option value='008'>阿尔巴尼亚</option><option value='012'>阿尔及利亚</option><option value='372'>爱尔兰</option><option value='004'>阿富汗</option><option value='024'>安哥拉</option><option value='032'>阿根廷</option><option value='818'>埃及</option><option value='784'>阿拉伯联合酋长国</option><option value='446'>澳门</option>" +
				"<option value='512'>阿曼</option><option value='230'>埃塞俄比亚</option><option value='028'>安提瓜</option><option value='052'>巴巴多斯</option><option value='598'>巴布亚新几内亚</option><option value='072'>博茨瓦纳</option><option value='064'>不丹</option><option value='352'>冰岛</option><option value='630'>波多黎各</option>" +
				"<option value='142'>白俄罗斯</option><option value='044'>巴哈马</option><option value='100'>保加利亚</option><option value='586'>巴基斯坦</option><option value='048'>巴林</option><option value='616'>波兰</option><option value='108'>布隆迪</option><option value='600'>巴拉圭</option><option value='056'>比利时</option>" +
				"<option value='374'>巴勒斯坦</option><option value='068'>玻利维亚</option><option value='084'>伯利兹</option><option value='060'>百慕大</option><option value='204'>贝宁</option><option value='590'>巴拿马</option><option value='074'>布维岛</option><option value='076'>巴西</option><option value='226'>赤道几内亚</option>" +
				"<option value='408'>朝鲜</option><option value='626'>东帝汶</option><option value='768'>多哥</option><option value='278'>德国</option><option value='208'>丹麦</option><option value='212'>多米尼加</option><option value='214'>多米尼加共和国</option><option value='218'>厄瓜多尔</option><option value='810'>俄罗斯</option>" +
				"<option value='336'>梵蒂冈</option><option value='132'>佛得角</option><option value='250'>法国</option><option value='242'>斐济</option><option value='238'>福克兰群岛(马尔维纳斯)</option><option value='246'>芬兰</option><option value='234'>法罗</option><option value='608'>菲律宾</option>" +
				"<option value='258'>法属波利尼西亚</option><option value='254'>法属圭亚那</option><option value='192'>古巴</option><option value='270'>冈比亚</option><option value='316'>关岛</option><option value='312'>瓜德罗普</option><option value='178'>刚果</option><option value='999'>国籍不明</option><option value='170'>哥伦比亚</option>" +
				"<option value='304'>格陵兰</option><option value='308'>格林纳达</option><option value='188'>哥斯达黎加</option><option value='328'>圭亚那</option><option value='332'>海地</option><option value='334'>赫德岛</option><option value='340'>洪都拉斯</option><option value='410'>韩国</option><option value='528'>荷兰</option>" +
				"<option value='225'>哈萨克斯坦</option><option value='716'>津巴布韦</option><option value='262'>吉布提</option><option value='417'>吉尔吉斯斯坦</option><option value='200'>捷克斯洛伐克</option><option value='296'>基里巴斯</option><option value='288'>加纳</option><option value='124'>加拿大</option>" +
				"<option value='324'>几内亚</option><option value='624'>几内亚比绍</option><option value='266'>加蓬</option><option value='146'>柬埔寨</option><option value='128'>坎顿和恩德贝里群岛</option><option value='166'>可可(基林)群岛</option><option value='184'>库克群岛</option><option value='174'>科摩罗</option>" +
				"<option value='120'>喀麦隆</option><option value='136'>开曼群岛</option><option value='404'>肯尼亚</option><option value='634'>卡塔尔</option><option value='414'>科威特</option><option value='422'>黎巴嫩</option><option value='434'>利比亚</option><option value='642'>罗马尼亚</option><option value='638'>留尼汪</option>" +
				"<option value='442'>卢森堡</option><option value='426'>莱索托</option><option value='418'>老挝</option><option value='646'>卢旺达</option><option value='438'>列支敦士登</option><option value='104'>缅甸</option><option value='216'>毛德地</option><option value='450'>马达加斯加</option><option value='462'>马尔代夫</option>" +
				"<option value='470'>马耳他</option><option value='840'>美国</option><option value='496'>蒙古</option><option value='050'>孟加拉</option><option value='466'>马里</option><option value='604'>秘鲁</option><option value='504'>摩洛哥</option><option value='480'>毛里求斯</option><option value='478'>毛里塔尼亚</option>" +
				"<option value='454'>马拉维</option><option value='458'>马来西亚</option><option value='492'>摩纳哥</option><option value='508'>莫桑比亚</option><option value='016'>美属萨摩亚</option><option value='849'>美属太平洋群岛</option><option value='474'>马提尼克</option><option value='500'>蒙特塞拉特</option><option value='484'>墨西哥</option>" +
				"<option value='720'>民主也门</option><option value='570'>纽埃岛</option><option value='524'>尼泊尔</option><option value='710'>南非(阿札尼亚)</option><option value='010'>南极洲</option><option value='520'>瑙鲁</option><option value='516'>纳米比亚</option><option value='566'>尼日利亚</option><option value='890'>南斯拉夫</option>" +
				"<option value='578'>挪威</option><option value='612'>皮特凯恩岛</option><option value='620'>葡萄牙</option><option value='392'>日本</option><option value='752'>瑞典</option><option value='756'>瑞士</option><option value='736'>苏丹</option><option value='162'>圣诞岛</option><option value='678'>圣多美和普林西比</option>" +
				"<option value='222'>萨尔瓦多</option><option value='654'>圣赫勒拿</option><option value='658'>圣基茨－尼维斯－安圭</option><option value='694'>塞拉利昂</option><option value='144'>斯里兰卡</option><option value='090'>所罗门群岛</option><option value='740'>苏里南</option><option value='662'>圣卢西亚</option><option value='706'>索马里</option>" +
				"<option value='674'>圣马力诺</option><option value='686'>塞内加尔</option><option value='666'>圣皮埃尔和密克隆</option><option value='196'>塞浦路斯</option><option value='690'>塞舌尔</option><option value='682'>沙特阿拉伯</option><option value='744'>斯瓦巴德群岛</option><option value='854'>上沃尔特</option><option value='748'>斯威士兰</option>" +
				"<option value='670'>圣文森特和格林纳丁斯</option><option value='792'>土耳其</option><option value='764'>泰国</option><option value='776'>汤加</option><option value='227'>塔吉克斯坦</option><option value='772'>托克劳</option><option value='224'>土库曼斯坦</option><option value='796'>特克斯和凯科斯群岛</option><option value='780'>特立尼达和多巴哥</option>" +
				"<option value='788'>突尼斯</option><option value='582'>太平洋群岛</option><option value='834'>坦桑尼亚</option><option value='798'>图瓦卢</option><option value='320'>危地马拉</option><option value='800'>乌干达</option><option value='872'>威克岛</option><option value='804'>乌克兰</option><option value='096'>文莱</option><option value='858'>乌拉圭</option>" +
				"<option value='876'>瓦利斯和富图纳群岛</option><option value='862'>委内瑞拉</option><option value='223'>乌兹别克斯坦</option><option value='724'>西班牙</option><option value='702'>新加坡</option><option value='300'>希腊</option><option value='760'>叙利亚</option><option value='732'>西撒哈拉</option><option value='882'>西萨摩亚</option><option value='554'>新西兰</option>" +
				"<option value='384'>象牙海岸</option><option value='348'>匈牙利</option><option value='356'>印度</option><option value='400'>约旦</option><option value='380'>意大利</option><option value='360'>印度尼西亚</option><option value='826'>英国</option><option value='396'>约翰斯顿岛</option><option value='364'>伊朗</option><option value='368'>伊拉克</option>" +
				"<option value='886'>也门</option><option value='388'>牙买加</option><option value='704'>越南</option><option value='376'>以色列</option><option value='092'>英属维尔京群岛</option><option value='086'>英属印度洋领土</option><option value='292'>直布罗陀</option><option value='894'>赞比亚</option><option value='148'>乍得</option><option value='140'>中非</option>" +
				"<option value='156'>中国</option><option value='152'>智利</option><option value='488'>中途岛</option><option value='180'>扎伊尔</option></select></div></td>"
		+"</tr>"
		
		+"<tr><td align=left width=10% class='xsjbxx_2'>户籍地</td>"
		+"<td width=23% align=left class='xsjbxx_3'>"
		+"<input type=text name='suspectInfos["+count_sjr+"].household'  id='sjr.c_hjszd_["+count_sjr+"]' style='width:75%' value='' onfocusout='clearXzqhDiv()' onkeyup=\"\">"
		+"&nbsp;<u style='cursor:hand' onclick=\"\" >选择</u>"
		+"<input type=hidden name='sjr.c_hjszd["+count_sjr+"]' id='sjr.c_hjszd["+count_sjr+"]' value=''></td>"
		+"<td align=left width=10% class='xsjbxx_2'>户籍地详址</td>"
		+"<td align=left class='xsjbxx_3'><input type=text name='suspectInfos["+count_sjr+"].householdAddr' id='sjr.c_hjszdxz["+count_sjr+"]' style='width:95%' value=''></td>"
		+"</tr>"
		
		+"<tr><td align=left width=10% class='xsjbxx_2'>居住地</td>"
		+"<td align=left class='xsjbxx_3'>"
		+"<input type=text name='suspectInfos["+count_sjr+"].residence' id='sjr.c_sjjzd_["+count_sjr+"]' style='width:75%' value='' onfocusout='clearXzqhDiv()' onkeyup=\"\">"
		+"&nbsp;<u style='cursor:hand' onclick=\"\" >选择</u>"
		+"<input type=hidden name='sjr.c_sjjzd["+count_sjr+"]' id='sjr.c_sjjzd["+count_sjr+"]' value=''></td>"
		+"<td align=left width=10% class='xsjbxx_2'>居住地详址</td>"
		+"<td align=left class='xsjbxx_3'><input type=text name='suspectInfos["+count_sjr+"].residenceAddr' id='sjr.c_sjjzdxz["+count_sjr+"]' style='width:95%' value=''></td>"
		+"</tr>"
		+"<tr>"
		+"<td align=left class='xsjbxx_2'>工作单位</td>"
		+"<td align=left class='xsjbxx_3'>"
		+"	<div id='select0'>"
		+"		<input type=text name='suspectInfos["+count_sjr+"].workUnit' id='sjr.c_gzdw["+count_sjr+"]' style='width:95%' value=''></input>"
		+"	</div>"
		+"</td>"
		+"<td align=left class='xsjbxx_2'>活动区域</td>"
		+"<td align=left class='xsjbxx_3' >"
		+"	<input type=text name='suspectInfos["+count_sjr+"].activityArea' id='sjr.c_hdqy["+count_sjr+"]' style='width:95%' value=''></input>"
		+"</td>"
		+"</tr>"
		+"<td align=left class='xsjbxx_2'>体貌特征</td>"
		+"<td align=left class='xsjbxx_3' colspan=5><textarea style='width:99%' name='suspectInfos["+count_sjr+"].physicalFeature' id='sjr.c_tbtmtz["+count_sjr+"]'></textarea></td></tr>"
		
		+"<tr><td align=left class='xsjbxx_2' colspan=6>"
		+"<table width=100% id='tab_sjr_ysa_"+count_sjr+"' cellpadding=0 cellspacing=0><tr>"
		+"<td align=left class='xsjbxx_2' width=12%>研判结果 </td>"
		+"<td align=left class='xsjbxx_3' width=23%>"
		+"<div id='select0'><select name='suspectInfos["+count_sjr+"].judgeResult' id='sjr.c_ypjg["+count_sjr+"]' style='width:95%'><option value=''></option><option value='1'>嫌疑</option><option value='2'>待定</option><option value='3'>排除</option></select></div></td>"
		+"<td align=left class='xsjbxx_2' width=10%>涉毒类型 </td>"
		+"<td align=left class='xsjbxx_3' width=23%>"
		+"<div id='select0'><select name='suspectInfos["+count_sjr+"].drugType' id='sjr.c_sdlx["+count_sjr+"]' style='width:95%'><option value=''></option><option value='1'>贩毒</option><option value='10'>测试</option><option value='2'>吸毒</option><option value='3'>运输</option><option value='4'>走私</option>" +
				"<option value='5'>制造</option><option value='6'>持有</option><option value='7'>容留</option><option value='8'>教引</option><option value='9'>关系</option></select></div></td>"
		+"<td align=left class='xsjbxx_2' width=10%>案件角色 </td>"
		+"<td align=left class='xsjbxx_3'>"
		+"<div id='select0'><select name='suspectInfos["+count_sjr+"].caseRole' id='sjr.c_ajjs["+count_sjr+"]' style='width:95%'><option value=''></option><option value='1'>主犯</option><option value='2'>从犯</option><option value='3'>相关人员</option></select></div>"
		+"</td></tr></table></td></tr>"
		+"<tr><td align=left class='xsjbxx_2'>备注</td>"
		+"<td align=left class='xsjbxx_3' colspan=5><textarea style='width:95%' name='suspectInfos["+count_sjr+"].mark' id='sjr.c_bz["+count_sjr+"]'></textarea></td></tr>"
		+"<tr><td colspan='6' align='right'><a href=\"javascript:addSJR_P('"+count_sjr+"')\" >增加附属信息</a></td></tr>"
		+"<tr><td width=100% colspan='6'><table id='tab_sjr_p_"+count_sjr+"' cellpadding=0 cellspacing=0 width=100% class='xsjbxx_1'><tr>"
		+"<td width=15% class='xsjbxx_2' align=center>号码类别</td><td width=50% class='xsjbxx_2' align=center>号码</td><td width=30% class='xsjbxx_2' align=center>备注</td><td width=10% align=center class='xsjbxx_2'>操作</td></tr>"
		+"</table></td></tr>"
		+"</table></fieldset><br>";
	count_sjr++;
	document.all.count_sjr.value=count_sjr;
	row.scrollIntoView(true);
	// 变换背景色
	chgSjrColor();
}