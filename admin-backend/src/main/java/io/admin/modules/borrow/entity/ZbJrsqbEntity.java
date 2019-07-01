package io.admin.modules.borrow.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 装备借入申请表
 * 
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-01 09:07:30
 */
@TableName("zb_jrsqb")
public class ZbJrsqbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String sqmc;
	/**
	 * 
	 */
	private Long sqbmid;
	/**
	 * 
	 */
	private String sqbmmc;
	/**
	 * 
	 */
	private Long jcbmid;
	/**
	 * 
	 */
	private String jcbmmc;
	/**
	 * 
	 */
	private Integer ztm;
	/**
	 * 
	 */
	private String zt;
	/**
	 * 
	 */
	private Date ghrq;
	/**
	 * 
	 */
	private String bz;
	/**
	 * 
	 */
	private Long createUserId;
	/**
	 * 
	 */
	private Date createTime;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setSqmc(String sqmc) {
		this.sqmc = sqmc;
	}
	/**
	 * 获取：
	 */
	public String getSqmc() {
		return sqmc;
	}
	/**
	 * 设置：
	 */
	public void setSqbmid(Long sqbmid) {
		this.sqbmid = sqbmid;
	}
	/**
	 * 获取：
	 */
	public Long getSqbmid() {
		return sqbmid;
	}
	/**
	 * 设置：
	 */
	public void setSqbmmc(String sqbmmc) {
		this.sqbmmc = sqbmmc;
	}
	/**
	 * 获取：
	 */
	public String getSqbmmc() {
		return sqbmmc;
	}
	/**
	 * 设置：
	 */
	public void setJcbmid(Long jcbmid) {
		this.jcbmid = jcbmid;
	}
	/**
	 * 获取：
	 */
	public Long getJcbmid() {
		return jcbmid;
	}
	/**
	 * 设置：
	 */
	public void setJcbmmc(String jcbmmc) {
		this.jcbmmc = jcbmmc;
	}
	/**
	 * 获取：
	 */
	public String getJcbmmc() {
		return jcbmmc;
	}
	/**
	 * 设置：
	 */
	public void setZtm(Integer ztm) {
		this.ztm = ztm;
	}
	/**
	 * 获取：
	 */
	public Integer getZtm() {
		return ztm;
	}
	/**
	 * 设置：
	 */
	public void setZt(String zt) {
		this.zt = zt;
	}
	/**
	 * 获取：
	 */
	public String getZt() {
		return zt;
	}
	/**
	 * 设置：
	 */
	public void setGhrq(Date ghrq) {
		this.ghrq = ghrq;
	}
	/**
	 * 获取：
	 */
	public Date getGhrq() {
		return ghrq;
	}
	/**
	 * 设置：
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * 获取：
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * 设置：
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
