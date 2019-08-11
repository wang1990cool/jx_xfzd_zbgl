package io.admin.modules.kcgl.kcxx.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 库存信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-01 11:17:05
 */
@TableName("zb_kcxxb")
public class ZbKcxxbVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 装备编号
	 */
	private String zbid;
	/**
	 * 装备名称
	 */
	private String zbmc;
	/**
	 * 所属部门编号
	 */
	private Long ssbmid;
	/**
	 * 所属部门名称
	 */
	private String ssbmmc;
	/**
	 * 生产日期
	 */
	@TableField(exist = false)
	private String scrq;

	/**
	 * 装备数量
	 */
	private Integer zbsl;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：装备编号
	 */
	public void setZbid(String zbid) {
		this.zbid = zbid;
	}
	/**
	 * 获取：装备编号
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
	 * 设置：所属部门编号
	 */
	public void setSsbmid(Long ssbmid) {
		this.ssbmid = ssbmid;
	}
	/**
	 * 获取：所属部门编号
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

	public String getScrq() {
		return scrq;
	}

	public void setScrq(String scrq) {
		this.scrq = scrq;
	}
}
