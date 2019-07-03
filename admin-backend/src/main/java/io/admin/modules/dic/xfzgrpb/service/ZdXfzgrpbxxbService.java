package io.admin.modules.dic.xfzgrpb.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.dic.xfzgrpb.entity.ZdXfzgrpbxxbEntity;

import java.util.Map;

/**
 * 消防站个人配备信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 17:00:35
 */
public interface ZdXfzgrpbxxbService extends IService<ZdXfzgrpbxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

