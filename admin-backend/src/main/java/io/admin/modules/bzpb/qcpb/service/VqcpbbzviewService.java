package io.admin.modules.bzpb.qcpb.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.bzpb.qcpb.entity.VqcpbbzviewEntity;

import java.util.Map;

/**
 * VIEW
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 14:39:01
 */
public interface VqcpbbzviewService extends IService<VqcpbbzviewEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

