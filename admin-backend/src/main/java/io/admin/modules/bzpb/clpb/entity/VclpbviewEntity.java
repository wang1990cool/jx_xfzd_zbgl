package io.admin.modules.bzpb.clpb.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * VIEW
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 23:53:20
 */
@TableName("vclpbview")
public class VclpbviewEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String bmmc;
	/**
	 * 消防站类别编码
	 */
	private Long xfzlbid;
	/**
	 * 消防站类别
	 */
	private String xfzlbmc;
	/**
	 * 最小配备
	 */
	private Integer zbslmin;
	/**
	 * 最大配备
	 */
	private Integer zbslmax;
	/**
	 * 
	 */
	private Long clsl;

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
	 * 设置：消防站类别编码
	 */
	public void setXfzlbid(Long xfzlbid) {
		this.xfzlbid = xfzlbid;
	}
	/**
	 * 获取：消防站类别编码
	 */
	public Long getXfzlbid() {
		return xfzlbid;
	}
	/**
	 * 设置：消防站类别
	 */
	public void setXfzlbmc(String xfzlbmc) {
		this.xfzlbmc = xfzlbmc;
	}
	/**
	 * 获取：消防站类别
	 */
	public String getXfzlbmc() {
		return xfzlbmc;
	}
	/**
	 * 设置：最小配备
	 */
	public void setZbslmin(Integer zbslmin) {
		this.zbslmin = zbslmin;
	}
	/**
	 * 获取：最小配备
	 */
	public Integer getZbslmin() {
		return zbslmin;
	}
	/**
	 * 设置：最大配备
	 */
	public void setZbslmax(Integer zbslmax) {
		this.zbslmax = zbslmax;
	}
	/**
	 * 获取：最大配备
	 */
	public Integer getZbslmax() {
		return zbslmax;
	}
	/**
	 * 设置：
	 */
	public void setClsl(Long clsl) {
		this.clsl = clsl;
	}
	/**
	 * 获取：
	 */
	public Long getClsl() {
		return clsl;
	}
}
