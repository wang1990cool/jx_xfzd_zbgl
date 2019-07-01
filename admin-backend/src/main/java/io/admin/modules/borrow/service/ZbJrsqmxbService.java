package io.admin.modules.borrow.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.borrow.entity.ZbJrsqmxbEntity;

import java.util.Map;

/**
 * 
 *
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-01 09:07:30
 */
public interface ZbJrsqmxbService extends IService<ZbJrsqmxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

