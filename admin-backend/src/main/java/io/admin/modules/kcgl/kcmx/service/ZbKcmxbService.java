package io.admin.modules.kcgl.kcmx.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;

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
}

