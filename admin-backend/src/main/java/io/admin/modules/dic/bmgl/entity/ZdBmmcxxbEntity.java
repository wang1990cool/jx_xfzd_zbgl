package io.admin.modules.dic.bmgl.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-30 21:54:12
 */
@TableName("zd_bmmcxxb")
public class ZdBmmcxxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long pid;
	/**
	 * 
	 */
	private String bmmc;
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
	private String bz;

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
	public void setPid(Long pid) {
		this.pid = pid;
	}
	/**
	 * 获取：
	 */
	public Long getPid() {
		return pid;
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
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * 获取：
	 */
	public String getBz() {
		return bz;
	}
}
