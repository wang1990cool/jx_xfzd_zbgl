package io.admin.modules.borrow.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 装备借入申请表
 * 
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-01 09:07:30
 */
@Data
@TableName("jy_jrsqb")
public class ZbJrsqbEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@TableId
	private Long id;

	private String jrsqid;

	private String sqmc;
	
	private Long sqbmid;
	
	private String sqbmmc;
	
	private Long jcbmid;
	
	private String jcbmmc;
	
	private Integer ztm;
	
	private String zt;
	
	private String ghrq;
	
	private String bz;
	
	private Long createUserId;

	private String createUserName;

	private Date createTime;


}
