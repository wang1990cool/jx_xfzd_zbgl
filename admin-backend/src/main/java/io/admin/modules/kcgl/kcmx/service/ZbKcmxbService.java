package io.admin.modules.kcgl.kcmx.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;

import java.util.List;
import java.util.Map;

/**
 * 装备库存明细信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-01 11:13:25
 */
public interface ZbKcmxbService extends IService<ZbKcmxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
    String getXlh(String zbid);//根据装备名称id得到流水号
    List<ZbKcmxbEntity> selectList(String ssbmid, int ztm);//根据部门编码及状态码得到装备明细，用于在库在车在人
//    List<ZbKcmxbEntity> selectList(String zbid);
    List<ZbKcmxbEntity> selectList(String zbid,int ztm,String ssbmid);//根据装备id，部门编码及状态码得到装备明细，用于在库在车在人
    ZbKcmxbEntity selectByzbbm(String zbbm);

}

