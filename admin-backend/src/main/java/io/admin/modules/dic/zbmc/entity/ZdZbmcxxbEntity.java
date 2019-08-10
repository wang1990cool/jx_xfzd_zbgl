package io.admin.modules.dic.zbmc.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 装备名称信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-29 09:45:56
 */
@TableName("zd_zbmcxxb")
public class ZdZbmcxxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 类别编码
	 */
	private String zblbid;
	/**
	 * 类别名称
	 */
	private String zblbmc;
	/**
	 * 装备编码
	 */
	private String zbid;
	/**
	 * 装备名称
	 */
	private String zbmc;
	/**
	 * 报废周期 -1：不保养，单位为年
	 */
	private Integer bfzq;
	/**
	 * 保养周期 -1：不保养，单位为月
	 */
	private Integer byzq;
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
	 * 装备照片
	 */
	private byte[] zbzp;

	/**
	 * 证书照片存放名称
	 */
	private String zppath;

	public String getZbjs() {
		return zbjs;
	}

	public void setZbjs(String zbjs) {
		this.zbjs = zbjs;
	}

	private String zbjs;

	public String getZppath() {
		return zppath;
	}

	public void setZppath(String zppath) {
		this.zppath = zppath;
	}

	public byte[] getZbzp() {
		return zbzp;
	}

	public void setZbzp(byte[] zbzp) {
		this.zbzp = zbzp;
	}

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
	 * 设置：类别编码
	 */
	public void setZblbid(String zblbid) {
		this.zblbid = zblbid;
	}
	/**
	 * 获取：类别编码
	 */
	public String getZblbid() {
		return zblbid;
	}
	/**
	 * 设置：类别名称
	 */
	public void setZblbmc(String zblbmc) {
		this.zblbmc = zblbmc;
	}
	/**
	 * 获取：类别名称
	 */
	public String getZblbmc() {
		return zblbmc;
	}
	/**
	 * 设置：装备编码
	 */
	public void setZbid(String zbid) {
		this.zbid = zbid;
	}
	/**
	 * 获取：装备编码
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
	 * 设置：报废周期 -1：不保养，单位为年
	 */
	public void setBfzq(Integer bfzq) {
		this.bfzq = bfzq;
	}
	/**
	 * 获取：报废周期 -1：不保养，单位为年
	 */
	public Integer getBfzq() {
		return bfzq;
	}
	/**
	 * 设置：保养周期 -1：不保养，单位为月
	 */
	public void setByzq(Integer byzq) {
		this.byzq = byzq;
	}
	/**
	 * 获取：保养周期 -1：不保养，单位为月
	 */
	public Integer getByzq() {
		return byzq;
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
