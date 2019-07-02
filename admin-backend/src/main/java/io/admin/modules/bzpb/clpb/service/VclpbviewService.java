package io.admin.modules.bzpb.clpb.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.bzpb.clpb.entity.VclpbviewEntity;

import java.util.Map;

/**
 * VIEW
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 23:53:20
 */
public interface VclpbviewService extends IService<VclpbviewEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

