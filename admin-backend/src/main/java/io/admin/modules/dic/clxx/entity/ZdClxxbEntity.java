package io.admin.modules.dic.clxx.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 库存信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-01 20:09:18
 */
@TableName("zd_clxxb")
public class ZdClxxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 所属部门编号
	 */
	private Long ssbmid;
	/**
	 * 所属部门名称
	 */
	private String ssbmmc;
	/**
	 * 上次保养日期
	 */
	private String byrq;
	/**
	 * 车牌号
	 */
	private String cph;

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
	 * 设置：上次保养日期
	 */
	public void setByrq(String byrq) {
		this.byrq = byrq;
	}
	/**
	 * 获取：上次保养日期
	 */
	public String getByrq() {
		return byrq;
	}
	/**
	 * 设置：车牌号
	 */
	public void setCph(String cph) {
		this.cph = cph;
	}
	/**
	 * 获取：车牌号
	 */
	public String getCph() {
		return cph;
	}
}
