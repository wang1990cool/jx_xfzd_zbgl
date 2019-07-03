package io.admin.modules.bzpb.grpb.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.bzpb.grpb.entity.VgrpbbzviewEntity;

import java.util.Map;

/**
 * VIEW
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 22:35:45
 */
public interface VgrpbbzviewService extends IService<VgrpbbzviewEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

