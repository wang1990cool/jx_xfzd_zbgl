package io.admin.modules.borrow.vo;

import io.admin.modules.borrow.entity.ZbJrsqbEntity;
import io.admin.modules.borrow.entity.ZbJrsqmxbEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-03 09:13:50
 */

@Data
public class JyJczbVo implements Serializable {
	private static final long serialVersionUID = 1L;


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

	/**
	 * 库存数量
	 */
	private int kcsl;


	public static void createJczbVo(ZbJrsqbEntity jrsqEntity, ZbJrsqmxbEntity jrsqmxEntity) {
		JyJczbVo jyJczbVo = new JyJczbVo();
		jyJczbVo.setJrsqid(jrsqEntity.getJrsqid());
		jyJczbVo.setZbid(jrsqmxEntity.getZbid());
		jyJczbVo.setZbmc(jrsqmxEntity.getZbmc());
		jyJczbVo.setSqsl(jrsqmxEntity.getZbsl());
		jyJczbVo.setSjsl(0);
		jyJczbVo.setKcsl(0);

	}


}
