package com.bocom.dto.req;

public class AppPageParamDto {
	private String appCategory;// 应用技术类别
	private String bizCategory;// 应用业务类别
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private String commparam;// 公司名称和应用名称模糊查询
	private String companyId;// 公司Id
	private String appIdGroup;// 筛选最新版本app
	private int pageSize;
	private int pageNum;

	public AppPageParamDto(String appCategory, String bizCategory,
			String startTime, String endTime, String commparam,
			String companyId, String appIdGroup) {
		super();
		this.appCategory = appCategory;
		this.bizCategory = bizCategory;
		this.startTime = startTime;
		this.endTime = endTime;
		this.commparam = commparam;
		this.companyId = companyId;
		this.appIdGroup = appIdGroup;
	}

	public AppPageParamDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAppCategory() {
		return appCategory;
	}

	public void setAppCategory(String appCategory) {
		this.appCategory = appCategory;
	}

	public String getBizCategory() {
		return bizCategory;
	}

	public void setBizCategory(String bizCategory) {
		this.bizCategory = bizCategory;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCommparam() {
		return commparam;
	}

	public void setCommparam(String commparam) {
		this.commparam = commparam;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getAppIdGroup() {
		return appIdGroup;
	}

	public void setAppIdGroup(String appIdGroup) {
		this.appIdGroup = appIdGroup;
	}

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(int pageNum)
    {
        this.pageNum = pageNum;
    }

}
