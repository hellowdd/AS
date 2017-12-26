function checkfm() {
	var clueSource = document.jbxxForm.clueSource;
	var drugRelatType=document.jbxxForm.drugRelatType;
	var supplyDate=document.jbxxForm.supplyDate;
	var clueContent = document.jbxxForm.clueContent;
	var supplyPerson=document.jbxxForm.supplyPerson;
	var clueTitle=document.jbxxForm.clueTitle;
	
	if (clueTitle.value.trim() != "") {
		if (!verifyLength(clueTitle.value.trim(), 200, "线索标题")) {
			return false;
		}
	}

	if (supplyPerson.value.trim() != "") {
		if (!verifyLength(supplyPerson.value.trim(), 60, "提供者")) {
			return false;
		}
	}
	if (supplyDate.value == "") {
		alert("提供日期不允许为空");
		supplyDate.focus();
		return false;
	}

	if(drugRelatType.value==""){
	   		alert("涉毒类型不允许为空");
	   		drugRelatType.focus();
	   		return false;
	   }
	
	if (clueSource.value == "") {
		alert("线索来源不允许为空！");
		clueSource.focus();
		return false;
	}
	if (supplyPerson.value.trim() == "") {
		alert("提供者信息不允许为空");
		supplyPerson.focus();
		return false;
	}
	if (clueContent.value.trim() == "") {
		alert("线索内容不允许为空");
		clueContent.focus();
		return false;
	} else {
		if (!verifyLength(clueContent.value.trim(), 2000, "线索内容")) {
			return false;
		}
	}

	// 检验人员属性信息
	var isSjrxx = false;
//	var count_sjr=document.getElementById("count_sjr_num").value;
	for (var i = 0; i < count_sjr; i++) {
		var tab2 = document.getElementById("tab_sjr_" + i);
		var zl_lx = document.getElementsByName("sjr_p_" + i + "_lx");
		var zl_hmlx = document.getElementsByName("sjr_p_" + i + "_hmlx");
		var zl_nr = document.getElementsByName("sjr_p_" + i + "_nr");
		
		var isXX = false;
		if (tab2) {
			isSjrxx = true;
			var sjrxm = document.getElementById("sjr.c_xm[" + i + "]");
			var sjrch = document.getElementById("sjr.c_ch[" + i + "]");
			var sjrhjdxz = document.getElementById("sjr.c_hjszdxz[" + i + "]");
			var sjrjzdxz = document.getElementById("sjr.c_sjjzdxz[" + i + "]");
			var sjrtmtz = document.getElementById("sjr.c_tbtmtz[" + i + "]");
			var sjrfwcylx = document.getElementById("sjr.c_fwcylx[" + i + "]");
			var sjrfwdy = document.getElementById("sjr.c_fwdy[" + i + "]");
			var sjrzjhm = document.getElementById("sjr.c_sfzhm[" + i + "]");
			var sjr_p_lx = document.getElementsByName("sjr_p_" + i + "_lx");
			var sjr_p_nr = document.getElementsByName("sjr_p_" + i + "_nr");
			var index;
			for (index = 0; index < sjr_p_lx.length; index++) {
				if ((sjrzjhm == null || sjrzjhm.value.trim() == "")
						&& (sjr_p_lx[index] == null || sjr_p_lx[index].value.trim() == "")
						&& (sjr_p_nr[index] == null || sjr_p_nr[index].value.trim() == "")) {
					alert("请输入身份证号或完成附属信息");
					return false;
				}
			}
			if (sjrxm != null && sjrxm.value.trim() != "") {
				if (!verifyLength(sjrxm.value.trim(), 30, "涉及人姓名"))
					return false;
			}
			if (sjrch != null && sjrch.value.trim() != "") {
				if (!verifyLength(sjrch.value.trim(), 128, "涉及人绰号"))
					return false;
			}
			if (sjrhjdxz != null && sjrhjdxz.value.trim() != "") {
				if (!verifyLength(sjrhjdxz.value.trim(), 128, "涉及人户籍地详址"))
					return false;
			}
			if (sjrhjdxz != null && sjrhjdxz.value.trim() != "") {
				if (!verifyLength(sjrhjdxz.value.trim(), 128, "涉及人户籍地详址"))
					return false;
			}
			if (sjrjzdxz != null && sjrjzdxz.value.trim() != "") {
				if (!verifyLength(sjrjzdxz.value.trim(), 128, "涉及人居住地详址"))
					return false;
			}

			if (sjrfwcylx != null && sjrfwcylx.value.trim() != "") {

				if (sjrfwcylx.value == "1") {// 购买
					if (sjrfwdy.value == "") {
						alert("请选择涉及人房屋抵押情况");
						return false;
					}
				}
			}

			if (sjrtmtz != null && sjrtmtz.value.trim() != "") {
				if (!verifyLength(sjrtmtz.value.trim(), 256, "涉及人体貌特征"))
					return false;
			}

			var zjzl = document.getElementById("sjr.c_sfzzl[" + i + "]").value;

			var zjhm = document.getElementById("sjr.c_sfzhm[" + i + "]").value;
			
			if (zjzl.trim() != "" || zjhm.trim() != "") {
				if ((zjzl != null && zjzl != "") && zjhm.trim().length == 0) {
					alert("请填写证件号码");
					return false;
				}
				if ((zjzl == null || zjzl == "") && zjhm.trim().length != 0) {
					alert("请填写证件类型");
					return false;
				}
//	pengxiaolu 暂时			if (zjzl != null && zjzl != '0') {
//					if (zjzl == '1') {
//						if (!isIdCardNo(zjhm, this)) {
//							return false;
//						}
//					}
//				}
				isXX = true;
			}

			if (zl_lx != null && zl_lx.length > 0) {
				for ( var j = 0; j < zl_lx.length; j++) {

					// 验证号码类型lx是否填写
					if (zl_lx[j].value.length == 0) {
						alert('人员附属信息类型不能为空');
						zl_lx[j].focus();
						return false;
					}
					if (zl_bz[j].value.trim().length != 0) {
						if (!verifyLength(zl_bz[j].value.trim(), 1000, "相关号码备注"))
							return false;
					}
					// 验证号码子类型是否填写
					if (zl_lx[j].value == '2005') {
						if (zl_hmlx[j].value.length == 0) {
							alert('人员附属信息中的号牌种类不能为空');
							zl_hmlx[j].focus();
							return false;
						}
					}
					// 验证号码内容是否填写
					if (zl_nr[j].value.length == 0) {
						alert('人员附属信息中号码内容不能为空');
						zl_nr[j].focus();
						return false;
					} else {
						if (!verifyLength(zl_nr[j].value.trim(), 30, "相关号码内容"))
							return false;
					}
					// 验证号码内容是否符合格式
					if (zl_lx[j].value == '2003') {// EMAIL

						if (vEmailAddress2(zl_nr[j])) {
						} else {
							zl_nr[j].focus();
							return false;
						}
					} else if (zl_lx[j].value == '2004') {// QQ
					} else if (zl_lx[j].value == '2005') {// 车牌号

					} else if (zl_lx[j].value == '2006') {// MSN
						if (vMsn(zl_nr[j])) {

						} else {
							return false;
						}
					} else if (zl_lx[j].value == '2007') {// 银行卡

					} else if (zl_lx[j].value == '2009') {// 电话

					}
				}
			} else {
//				if ((sjrxm == null || sjrxm.value.trim() == "")
//						&& (sjrch == null || sjrch.value.trim() == "")
//						&& (sjrhjdxz == null || sjrhjdxz.value.trim() == "")
//						&& (sjrjzdxz == null || sjrjzdxz.value.trim() == "")
//						&& (sjrtmtz == null || sjrtmtz.value.trim() == "")
//						&& (fs == null || fs.value.trim() == "")
//						&& (sjrhjd == null || sjrhjd.value.trim() == "")
//						&& (sjrjzd == null || sjrjzd.value.trim() == "")
//						&& (zjzl == null || zjzl == "")
//						&& (zjhm.trim().length == 0)
//						&& (gj == null || gj.value.trim() == "")
//						&& (mz == null || mz.value.trim() == "")
//						&& (cs == null || cs.value.trim() == "")
//						&& (xb == null || xb.value.trim() == "")) {
//					alert("请填写人员相关信息");
//					return false;
//				}
			}
		}
//		if (isXX == false && zl_lx.length == 0) {
//			alert("请填写涉及人证件号码或附属信息");
//			return false;
//		}
	}
	if (isSjrxx == false) {
		if (!confirm("您的线索中没有相关涉及人信息，是否继续操作？")) {
			return false;
		}
	}

	return true;
}