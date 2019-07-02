package io.admin.modules.dic.xfzclpb.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.dic.xfzclpb.entity.ZdXfzclpbxxbEntity;

import java.util.Map;

/**
 * 消防站车辆配备信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 23:20:09
 */
public interface ZdXfzclpbxxbService extends IService<ZdXfzclpbxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

