package io.admin.modules.dic.zblb.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 装备类别信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-29 08:05:52
 */
@TableName("zd_zblbxxb")
public class ZdZblbxxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 类别编号
	 */
	private String lbid;
	/**
	 * 类别名称
	 */
	private String lbmc;
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
	 * 设置：类别编号
	 */
	public void setLbid(String lbid) {
		this.lbid = lbid;
	}
	/**
	 * 获取：类别编号
	 */
	public String getLbid() {
		return lbid;
	}
	/**
	 * 设置：类别名称
	 */
	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}
	/**
	 * 获取：类别名称
	 */
	public String getLbmc() {
		return lbmc;
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
