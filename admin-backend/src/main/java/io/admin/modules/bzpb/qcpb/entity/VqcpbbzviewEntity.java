package io.admin.modules.bzpb.qcpb.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * VIEW
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 14:39:01
 */
@TableName("vqcpbbzview")
public class VqcpbbzviewEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String zblbmc;
	/**
	 * 
	 */
	private String zbmc;
	/**
	 * 
	 */
	private Integer zbslmin;
	/**
	 * 
	 */
	private Integer zbslmax;
	/**
	 * 
	 */
	private Integer bfzbsl;
	/**
	 * 
	 */
	private String bmmc;
	/**
	 * 
	 */
	private Long zbsl;
	/**
	 * 
	 */
	private Long id;

	/**
	 * 设置：
	 */
	public void setZblbmc(String zblbmc) {
		this.zblbmc = zblbmc;
	}
	/**
	 * 获取：
	 */
	public String getZblbmc() {
		return zblbmc;
	}
	/**
	 * 设置：
	 */
	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
	}
	/**
	 * 获取：
	 */
	public String getZbmc() {
		return zbmc;
	}
	/**
	 * 设置：
	 */
	public void setZbslmin(Integer zbslmin) {
		this.zbslmin = zbslmin;
	}
	/**
	 * 获取：
	 */
	public Integer getZbslmin() {
		return zbslmin;
	}
	/**
	 * 设置：
	 */
	public void setZbslmax(Integer zbslmax) {
		this.zbslmax = zbslmax;
	}
	/**
	 * 获取：
	 */
	public Integer getZbslmax() {
		return zbslmax;
	}
	/**
	 * 设置：
	 */
	public void setBfzbsl(Integer bfzbsl) {
		this.bfzbsl = bfzbsl;
	}
	/**
	 * 获取：
	 */
	public Integer getBfzbsl() {
		return bfzbsl;
	}
	/**
	 * 设置：
	 */
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	/**
	 * 获取：
	 */
	public String getBmmc() {
		return bmmc;
	}
	/**
	 * 设置：
	 */
	public void setZbsl(Long zbsl) {
		this.zbsl = zbsl;
	}
	/**
	 * 获取：
	 */
	public Long getZbsl() {
		return zbsl;
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
}
