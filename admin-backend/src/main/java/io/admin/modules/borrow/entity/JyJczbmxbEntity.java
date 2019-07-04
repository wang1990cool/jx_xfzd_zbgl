package io.admin.modules.borrow.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 装备名称信息表
 * 
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-03 09:03:51
 */

@Data
@TableName("jy_jczbmxb")
public class JyJczbmxbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 类别编码
	 */
	private String zblbid;
	/**
	 * 类别名称
	 */
	private String zblbmc;

	private String zbid;
	/**
	 * 装备编码
	 */
	private String zbbm;
	/**
	 * 装备名称
	 */
	private String zbmc;
	/**
	 * 状态
	 */
	private String zt;

	private String jrsqid;

}
