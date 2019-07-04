package io.admin.modules.borrow.vo;

import io.admin.modules.borrow.entity.JyJczbbEntity;
import io.admin.modules.borrow.entity.ZbJrsqbEntity;
import io.admin.modules.borrow.entity.ZbJrsqmxbEntity;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	 * 装备类别编码
	 */
	private String zblbid;

	/**
	 * 装备类别
	 */
	private String zblbmc;

	/**
	 * 装备编号
	 */
	private String zbid;
	/**
	 * 装备名称
	 */
	private String zbmc;

	private Long sqbmid;

	private String sqbmmc;
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

	// 若领用单中有无数据
	public static List<JyJczbVo> createJczbVos( ZbJrsqbEntity jrsq, List<ZbJrsqmxbEntity> jrsqmxbEntityList,
											   List<List<ZbKcmxbEntity>> kcmxs) {
		List<JyJczbVo> jyJczbVoList = new ArrayList<JyJczbVo>();
		for (int i = 0; i < jrsqmxbEntityList.size();i++){
			JyJczbVo jyJczbVo = JyJczbVo.createJczbVo(null,jrsq,jrsqmxbEntityList.get(i),kcmxs.get(i));
			jyJczbVoList.add(jyJczbVo);
		}
		return jyJczbVoList;
	}

	// 若领用单中有数据
	public static List<JyJczbVo> updateJczbVos(List<JyJczbbEntity> jyJczbbEntityList,ZbJrsqbEntity jrsq,
											   List<ZbJrsqmxbEntity> jrsqmxbEntityList, List<List<ZbKcmxbEntity>> kcmxs) {
		List<JyJczbVo> jyJczbVoList = new ArrayList<JyJczbVo>();
		for (int i = 0; i < jrsqmxbEntityList.size();i++){
			JyJczbVo jyJczbVo = JyJczbVo.createJczbVo(jyJczbbEntityList.get(i), jrsq,jrsqmxbEntityList.get(i), kcmxs.get(i));
			jyJczbVoList.add(jyJczbVo);
		}
		return jyJczbVoList;
	}

	public static JyJczbVo createJczbVo(JyJczbbEntity jyJczbbEntity, ZbJrsqbEntity jrsq ,ZbJrsqmxbEntity jrsqmxEntity, List<ZbKcmxbEntity> kcmxbEntities) {
		JyJczbVo jyJczbVo = new JyJczbVo();
		jyJczbVo.setJrsqid(jrsqmxEntity.getJrsqid());

		jyJczbVo.setZblbid(jrsqmxEntity.getZblbid());
		jyJczbVo.setZblbmc(jrsqmxEntity.getZblbmc());

		jyJczbVo.setZbid(jrsqmxEntity.getZbid());
		jyJczbVo.setZbmc(jrsqmxEntity.getZbmc());

		jyJczbVo.setSqbmid(jrsq.getSqbmid());
		jyJczbVo.setSqbmmc(jrsq.getSqbmmc());

		jyJczbVo.setSqsl(jrsqmxEntity.getZbsl());
		if (jyJczbbEntity == null) {
			jyJczbVo.setSjsl(0);
		}else {
			jyJczbVo.setSjsl(jyJczbbEntity.getSjsl());
			jyJczbVo.setId(jyJczbbEntity.getId());
		}

		if (kcmxbEntities.size() > 0) {
			jyJczbVo.setKcsl(kcmxbEntities.size());
		}
		return jyJczbVo;
	}



}
