package io.admin.modules.borrow.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.borrow.entity.JyJczbbEntity;

import java.util.Map;

/**
 * 
 *
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-03 09:03:51
 */
public interface JyJczbbService extends IService<JyJczbbEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

