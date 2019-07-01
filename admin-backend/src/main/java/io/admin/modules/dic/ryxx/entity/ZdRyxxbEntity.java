package io.admin.modules.dic.ryxx.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 人员信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-30 22:50:14
 */
@TableName("zd_ryxxb")
public class ZdRyxxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String rybh;
	/**
	 * 
	 */
	private String ryxm;
	/**
	 * 
	 */
	private Long ssbmid;
	/**
	 * 
	 */
	private String ssbmmc;
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
	public void setRybh(String rybh) {
		this.rybh = rybh;
	}
	/**
	 * 获取：
	 */
	public String getRybh() {
		return rybh;
	}
	/**
	 * 设置：
	 */
	public void setRyxm(String ryxm) {
		this.ryxm = ryxm;
	}
	/**
	 * 获取：
	 */
	public String getRyxm() {
		return ryxm;
	}
	/**
	 * 设置：
	 */
	public void setSsbmid(Long ssbmid) {
		this.ssbmid = ssbmid;
	}
	/**
	 * 获取：
	 */
	public Long getSsbmid() {
		return ssbmid;
	}
	/**
	 * 设置：
	 */
	public void setSsbmmc(String ssbmmc) {
		this.ssbmmc = ssbmmc;
	}
	/**
	 * 获取：
	 */
	public String getSsbmmc() {
		return ssbmmc;
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
