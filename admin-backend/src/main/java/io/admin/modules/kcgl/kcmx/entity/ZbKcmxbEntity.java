package io.admin.modules.kcgl.kcmx.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 装备库存明细信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-01 11:13:25
 */
@TableName("zb_kcmxb")
public class ZbKcmxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 装备编码
	 */
	private String zbbm;
	/**
	 * 装备名称编号
	 */
	private String zbid;
	/**
	 * 装备名称
	 */
	private String zbmc;
	/**
	 * 所属部门id
	 */
	private Long ssbmid;
	/**
	 * 所属部门名称
	 */
	private String ssbmmc;
	/**
	 * 状态吗
	 */
	private Integer ztm;
	/**
	 * 状态
	 */
	private String zt;
	/**
	 * 现所属部门编码
	 */
	private Long xssbmid;
	/**
	 * 现所属部门名称
	 */
	private String xssbmmc;
	/**
	 * 报废日期
	 */
	private Date bfrq;
	/**
	 * 上次保养日期
	 */
	private Date byrq;
	/**
	 * 生产日期
	 */
	private String scrq;
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
	 * 设置：装备编码
	 */
	public void setZbbm(String zbbm) {
		this.zbbm = zbbm;
	}
	/**
	 * 获取：装备编码
	 */
	public String getZbbm() {
		return zbbm;
	}
	/**
	 * 设置：装备名称编号
	 */
	public void setZbid(String zbid) {
		this.zbid = zbid;
	}
	/**
	 * 获取：装备名称编号
	 */
	public String getZbid() {
		return zbid;
	}
	/**
	 * 设置：装备名称
	 */
	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
	}
	/**
	 * 获取：装备名称
	 */
	public String getZbmc() {
		return zbmc;
	}
	/**
	 * 设置：所属部门id
	 */
	public void setSsbmid(Long ssbmid) {
		this.ssbmid = ssbmid;
	}
	/**
	 * 获取：所属部门id
	 */
	public Long getSsbmid() {
		return ssbmid;
	}
	/**
	 * 设置：所属部门名称
	 */
	public void setSsbmmc(String ssbmmc) {
		this.ssbmmc = ssbmmc;
	}
	/**
	 * 获取：所属部门名称
	 */
	public String getSsbmmc() {
		return ssbmmc;
	}
	/**
	 * 设置：状态吗
	 */
	public void setZtm(Integer ztm) {
		this.ztm = ztm;
	}
	/**
	 * 获取：状态吗
	 */
	public Integer getZtm() {
		return ztm;
	}
	/**
	 * 设置：状态
	 */
	public void setZt(String zt) {
		this.zt = zt;
	}
	/**
	 * 获取：状态
	 */
	public String getZt() {
		return zt;
	}
	/**
	 * 设置：现所属部门编码
	 */
	public void setXssbmid(Long xssbmid) {
		this.xssbmid = xssbmid;
	}
	/**
	 * 获取：现所属部门编码
	 */
	public Long getXssbmid() {
		return xssbmid;
	}
	/**
	 * 设置：现所属部门名称
	 */
	public void setXssbmmc(String xssbmmc) {
		this.xssbmmc = xssbmmc;
	}
	/**
	 * 获取：现所属部门名称
	 */
	public String getXssbmmc() {
		return xssbmmc;
	}
	/**
	 * 设置：报废日期
	 */
	public void setBfrq(Date bfrq) {
		this.bfrq = bfrq;
	}
	/**
	 * 获取：报废日期
	 */
	public Date getBfrq() {
		return bfrq;
	}
	/**
	 * 设置：上次保养日期
	 */
	public void setByrq(Date byrq) {
		this.byrq = byrq;
	}
	/**
	 * 获取：上次保养日期
	 */
	public Date getByrq() {
		return byrq;
	}
	/**
	 * 设置：生产日期
	 */
	public void setScrq(String scrq) {
		this.scrq = scrq;
	}
	/**
	 * 获取：生产日期
	 */
	public String getScrq() {
		return scrq;
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
