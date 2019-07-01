package io.admin.modules.dic.xfzlb.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.dic.xfzlb.entity.ZdXfzlbxxbEntity;

import java.util.Map;

/**
 * 消防站类别信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-29 10:51:42
 */
public interface ZdXfzlbxxbService extends IService<ZdXfzlbxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

