package io.admin.modules.dic.xfzzbpb.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 消防站标准配备信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-29 11:10:15
 */
@TableName("zd_xfzbzpbxxb")
public class ZdXfzbzpbxxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long xfzlbid;
	/**
	 * 
	 */
	private String xfzlbmc;
	/**
	 * 
	 */
	private String zblbbh;
	/**
	 * 
	 */
	private String zblbmc;
	/**
	 * 
	 */
	private String zbmcbh;
	/**
	 * 
	 */
	private String zbmc;
	/**
	 * 
	 */
	private Integer zbslmax;
	/**
	 * 
	 */
	private Integer zbslmin;

	private Integer bfzbsl;

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
	public void setXfzlbid(Long xfzlbid) {
		this.xfzlbid = xfzlbid;
	}
	/**
	 * 获取：
	 */
	public Long getXfzlbid() {
		return xfzlbid;
	}
	/**
	 * 设置：
	 */
	public void setXfzlbmc(String xfzlbmc) {
		this.xfzlbmc = xfzlbmc;
	}
	/**
	 * 获取：
	 */
	public String getXfzlbmc() {
		return xfzlbmc;
	}
	/**
	 * 设置：
	 */
	public void setZblbbh(String zblbbh) {
		this.zblbbh = zblbbh;
	}
	/**
	 * 获取：
	 */
	public String getZblbbh() {
		return zblbbh;
	}
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
	public void setZbmcbh(String zbmcbh) {
		this.zbmcbh = zbmcbh;
	}
	/**
	 * 获取：
	 */
	public String getZbmcbh() {
		return zbmcbh;
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
	public void setZbslmin(Integer zbslmin) {
		this.zbslmin = zbslmin;
	}
	/**
	 * 获取：
	 */
	public Integer getZbslmin() {
		return zbslmin;
	}

	public Integer getBfzbsl() {
		return bfzbsl;
	}

	public void setBfzbsl(Integer bfzbsl) {
		this.bfzbsl = bfzbsl;
	}
}
