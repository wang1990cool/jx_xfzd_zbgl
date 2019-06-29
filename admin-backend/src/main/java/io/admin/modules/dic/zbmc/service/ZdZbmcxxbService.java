package io.admin.modules.dic.zbmc.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.dic.zbmc.entity.ZdZbmcxxbEntity;

import java.util.List;
import java.util.Map;

/**
 * 装备名称信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-29 09:45:56
 */
public interface ZdZbmcxxbService extends IService<ZdZbmcxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
    String getXlh(String zblbid);
    List<ZdZbmcxxbEntity> selectByZblbid(String zblbid);
}

