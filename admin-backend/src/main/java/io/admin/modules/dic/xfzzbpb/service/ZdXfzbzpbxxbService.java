package io.admin.modules.dic.xfzzbpb.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.dic.xfzzbpb.entity.ZdXfzbzpbxxbEntity;

import java.util.Map;

/**
 * 消防站标准配备信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-29 11:10:15
 */
public interface ZdXfzbzpbxxbService extends IService<ZdXfzbzpbxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

