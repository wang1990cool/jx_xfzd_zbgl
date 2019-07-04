package io.admin.modules.borrow.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.borrow.entity.JyJczbmxbEntity;

import java.util.List;
import java.util.Map;

/**
 * 装备名称信息表
 *
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-03 09:03:51
 */
public interface JyJczbmxbService extends IService<JyJczbmxbEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<JyJczbmxbEntity> selectByJrsqidAndZt(String jrsqid, String[] zt);
}

