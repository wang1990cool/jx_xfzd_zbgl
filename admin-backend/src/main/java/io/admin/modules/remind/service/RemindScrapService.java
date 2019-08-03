
package io.admin.modules.remind.service;


import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.remind.entity.ViewRemindScrapEntity;

import java.util.Map;


public interface RemindScrapService extends IService<ViewRemindScrapEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
