package io.admin.modules.borrow.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 装备借入申请表
 * 
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-01 09:07:30
 */
@TableName("jy_jrsqb")
public class ZbJrsqbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@TableId
	private Long id;

	private String jrsqid;

	private String sqmc;
	
	private Long sqbmid;
	
	private String sqbmmc;
	
	private Long jcbmid;
	
	private String jcbmmc;
	
	private Integer ztm;
	
	private String zt;
	
	private String ghrq;
	
	private String bz;
	
	private Long createUserId;

	private String createUserName;

	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJrsqid() {
		return jrsqid;
	}

	public void setJrsqid(String jrsqid) {
		this.jrsqid = jrsqid;
	}

	public String getSqmc() {
		return sqmc;
	}

	public void setSqmc(String sqmc) {
		this.sqmc = sqmc;
	}

	public Long getSqbmid() {
		return sqbmid;
	}

	public void setSqbmid(Long sqbmid) {
		this.sqbmid = sqbmid;
	}

	public String getSqbmmc() {
		return sqbmmc;
	}

	public void setSqbmmc(String sqbmmc) {
		this.sqbmmc = sqbmmc;
	}

	public Long getJcbmid() {
		return jcbmid;
	}

	public void setJcbmid(Long jcbmid) {
		this.jcbmid = jcbmid;
	}

	public String getJcbmmc() {
		return jcbmmc;
	}

	public void setJcbmmc(String jcbmmc) {
		this.jcbmmc = jcbmmc;
	}

	public Integer getZtm() {
		return ztm;
	}

	public void setZtm(Integer ztm) {
		this.ztm = ztm;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getGhrq() {
		return ghrq;
	}

	public void setGhrq(String ghrq) {
		this.ghrq = ghrq;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
}
