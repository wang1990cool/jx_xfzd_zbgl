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
 * @date 2019-07-03 09:03:51
 */
@TableName("jy_jczbb")
public class JyJczbbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 申请单id
	 */
	private Long jrsqid;
	/**
	 * 装备编号
	 */
	private String zbid;
	/**
	 * 装备名称
	 */
	private String zbmc;
	/**
	 * 申请数量
	 */
	private int sqsl;
	/**
	 * 实际数量
	 */
	private int sjsl;

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
	 * 设置：申请单id
	 */
	public void setJrsqid(Long jrsqid) {
		this.jrsqid = jrsqid;
	}
	/**
	 * 获取：申请单id
	 */
	public Long getJrsqid() {
		return jrsqid;
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
	 * 设置：申请数量
	 */
	public void setSqsl(Integer sqsl) {
		this.sqsl = sqsl;
	}
	/**
	 * 获取：申请数量
	 */
	public Integer getSqsl() {
		return sqsl;
	}
	/**
	 * 设置：实际数量
	 */
	public void setSjsl(int sjsl) {
		this.sjsl = sjsl;
	}
	/**
	 * 获取：实际数量
	 */
	public int getSjsl() {
		return sjsl;
	}
}
