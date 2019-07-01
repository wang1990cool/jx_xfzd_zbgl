package io.admin.modules.dic.bmgl.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.dic.bmgl.entity.ZdBmmcxxbEntity;

import java.util.Map;

/**
 * 部门信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-30 21:54:12
 */
public interface ZdBmmcxxbService extends IService<ZdBmmcxxbEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

