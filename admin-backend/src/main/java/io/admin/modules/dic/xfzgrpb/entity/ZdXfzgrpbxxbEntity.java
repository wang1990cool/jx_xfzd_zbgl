package io.admin.modules.dic.xfzgrpb.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 消防站个人配备信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 17:00:35
 */
@TableName("zd_xfzgrpbxxb")
public class ZdXfzgrpbxxbEntity implements Serializable {
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
	 * 消防站类别
	 */
	private String xfzlbmc;
	/**
	 * 装备类别编号
	 */
	private String zblbbh;
	/**
	 * 装备类别名称
	 */
	private String zblbmc;
	/**
	 * 
	 */
	private String zbmcbh;
	/**
	 * 装备名称
	 */
	private String zbmc;
	/**
	 * 标准配备数量
	 */
	private Integer bzpbsl;
	/**
	 * 标准备份比
	 */
	private Double bzbfb;
	/**
	 * 
	 */
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
	 * 设置：装备类别编号
	 */
	public void setZblbbh(String zblbbh) {
		this.zblbbh = zblbbh;
	}
	/**
	 * 获取：装备类别编号
	 */
	public String getZblbbh() {
		return zblbbh;
	}
	/**
	 * 设置：装备类别名称
	 */
	public void setZblbmc(String zblbmc) {
		this.zblbmc = zblbmc;
	}
	/**
	 * 获取：装备类别名称
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
	 * 设置：标准配备数量
	 */
	public void setBzpbsl(Integer bzpbsl) {
		this.bzpbsl = bzpbsl;
	}
	/**
	 * 获取：标准配备数量
	 */
	public Integer getBzpbsl() {
		return bzpbsl;
	}
	/**
	 * 设置：标准备份比
	 */
	public void setBzbfb(Double bzbfb) {
		this.bzbfb = bzbfb;
	}
	/**
	 * 获取：标准备份比
	 */
	public Double getBzbfb() {
		return bzbfb;
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
}
