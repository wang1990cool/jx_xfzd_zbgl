package io.admin.modules.dic.clxx.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.dic.clxx.entity.ZdClxxbEntity;

import java.util.List;
import java.util.Map;

/**
 * 库存信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-01 20:09:18
 */
public interface ZdClxxbService extends IService<ZdClxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<ZdClxxbEntity> selectByssbmid(String ssbmid);
}

