package com.bocom.dto.resp;

import java.util.List;
import java.util.Map;

public class DataDto {
	private String BizCategoryName;
	private List<AppInfo> list;

	public String getBizCategoryName() {
		return BizCategoryName;
	}

	public void setBizCategoryName(String bizCategoryName) {
		BizCategoryName = bizCategoryName;
	}

	public List<AppInfo> getList() {
		return list;
	}

	public void setList(List<AppInfo> list) {
		this.list = list;
	}
}
