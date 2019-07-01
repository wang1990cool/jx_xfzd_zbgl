package io.admin.modules.borrow.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-01 09:07:30
 */
@TableName("zb_jrsqmxb")
public class ZbJrsqmxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 申请单ID
	 */
	private String jrsqid;
	/**
	 * 
	 */
	private String zblbid;
	/**
	 * 装备类别
	 */
	private String zblbmc;
	/**
	 * 
	 */
	private String zbid;
	/**
	 * 装备名称
	 */
	private String zbmc;
	/**
	 * 装备数量
	 */
	private Integer zbsl;

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
	 * 设置：申请单ID
	 */
	public void setJrsqid(String jrsqid) {
		this.jrsqid = jrsqid;
	}
	/**
	 * 获取：申请单ID
	 */
	public String getJrsqid() {
		return jrsqid;
	}
	/**
	 * 设置：
	 */
	public void setZblbid(String zblbid) {
		this.zblbid = zblbid;
	}
	/**
	 * 获取：
	 */
	public String getZblbid() {
		return zblbid;
	}
	/**
	 * 设置：装备类别
	 */
	public void setZblbmc(String zblbmc) {
		this.zblbmc = zblbmc;
	}
	/**
	 * 获取：装备类别
	 */
	public String getZblbmc() {
		return zblbmc;
	}
	/**
	 * 设置：
	 */
	public void setZbid(String zbid) {
		this.zbid = zbid;
	}
	/**
	 * 获取：
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
	 * 设置：装备数量
	 */
	public void setZbsl(Integer zbsl) {
		this.zbsl = zbsl;
	}
	/**
	 * 获取：装备数量
	 */
	public Integer getZbsl() {
		return zbsl;
	}
}
