package io.admin.modules.borrow.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-03 09:03:51
 */
@Data
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
	private String jrsqid;
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

	private String zblbid;

	private String zblbmc;

	private Long sqbmid;

	private String sqbmmc;

	private Date createTime;

	private String createUserId;

}
