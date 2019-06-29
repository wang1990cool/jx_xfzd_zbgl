package io.admin.modules.dic.zblb.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.dic.zblb.entity.ZdZblbxxbEntity;

import java.util.List;
import java.util.Map;

/**
 * 装备类别信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-29 08:05:52
 */
public interface ZdZblbxxbService extends IService<ZdZblbxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
    String getXlh();

}

