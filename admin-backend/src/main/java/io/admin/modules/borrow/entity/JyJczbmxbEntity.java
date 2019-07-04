package io.admin.modules.borrow.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 装备名称信息表
 * 
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-03 09:03:51
 */
@TableName("jy_jczbmxb")
public class JyJczbmxbEntity implements Serializable {
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
	private String zbbm;
	/**
	 * 装备名称
	 */
	private String zbmc;
	/**
	 * 状态
	 */
	private String zt;

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
}
