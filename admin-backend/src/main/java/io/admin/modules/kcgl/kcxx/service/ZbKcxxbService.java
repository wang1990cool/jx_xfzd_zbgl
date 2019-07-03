package io.admin.modules.kcgl.kcxx.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.kcgl.kcxx.entity.ZbKcxxbEntity;

import java.util.Map;

/**
 * 库存信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-01 11:17:05
 */
public interface ZbKcxxbService extends IService<ZbKcxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
    ZbKcxxbEntity selectByzbid(String zbid,String ssbmmc);
}

