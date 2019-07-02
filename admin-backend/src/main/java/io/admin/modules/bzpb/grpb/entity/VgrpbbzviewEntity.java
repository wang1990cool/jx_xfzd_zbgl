package io.admin.modules.bzpb.grpb.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * VIEW
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 22:35:45
 */
@TableName("vgrpbbzview")
public class VgrpbbzviewEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 装备类别名称
	 */
	@TableId
	private String zblbmc;
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
	private Long zbsl;
	/**
	 * 
	 */
	private String bmmc;
	/**
	 * 
	 */
	private Long rysl;
	/**
	 * 
	 */
	private Long id;
	/**
	 * 
	 */
	private Double bzsl;

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
	public void setRysl(Long rysl) {
		this.rysl = rysl;
	}
	/**
	 * 获取：
	 */
	public Long getRysl() {
		return rysl;
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
	/**
	 * 设置：
	 */
	public void setBzsl(Double bzsl) {
		this.bzsl = bzsl;
	}
	/**
	 * 获取：
	 */
	public Double getBzsl() {
		return bzsl;
	}
}
