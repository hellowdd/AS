
package com.bocom.domain;

import java.util.Date;
import java.util.UUID;

import com.bocom.enums.DelFlagEnum;

/**  
 * ClassName:BaseDomain <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO 基础的实体 <br/>  
 * Date:     2017年4月19日 下午1:16:52 <br/>  
 * @author   win  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
public abstract class BaseDomain {
	
	protected String id;//主键
	
    protected String createBy;//创建人

    protected Date createDate;//创建时间

    protected String updateBy;//更新人

    protected Date updateDate;//更新时间
   
    protected String delFlag;//删除标识
    
    public BaseDomain(){
    	this.delFlag = DelFlagEnum.NORMAL_FLAG.getKeyCode();
    }

	public String getCreateBy() {
	
		return createBy;
	}

	public void setCreateBy(String createBy) {
	
		this.createBy = createBy;
	}

	public Date getCreateDate() {
	
		return createDate;
	}

	public void setCreateDate(Date createDate) {
	
		this.createDate = createDate;
	}

	public String getUpdateBy() {
	
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
	
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
	
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
	
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
	
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
	
		this.delFlag = delFlag;
	}
	
	public String getId() {
	
		return id;
	}

	public void setId(String id) {
	
		this.id = id;
	}

	protected void preInsert(){
		setId(UUID.randomUUID().toString().replaceAll("-", ""));
		setCreateBy("2");
		setCreateDate(new Date());
		setUpdateBy(this.getCreateBy());
		setUpdateDate(this.getCreateDate());
	}
    
	protected void preUpdate(){
		setUpdateBy("2");
		setUpdateDate(new Date());
	}
}
  
