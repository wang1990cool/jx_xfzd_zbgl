package io.admin.modules.borrow.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.borrow.entity.ZbJrsqbEntity;

import java.util.Map;

/**
 * 装备借入申请表
 *
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-01 09:07:30
 */
public interface ZbJrsqbService extends IService<ZbJrsqbEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageSh(Map<String, Object> params);

    void deleteByJrsqIds(String[] jrsqIds);


}

