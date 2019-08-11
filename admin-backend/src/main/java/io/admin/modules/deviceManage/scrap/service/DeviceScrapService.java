package io.admin.modules.deviceManage.scrap.service;

import com.baomidou.mybatisplus.service.IService;
import io.admin.common.utils.PageUtils;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;

import java.util.List;
import java.util.Map;

public interface DeviceScrapService extends IService<ZbKcmxbEntity> {

    PageUtils queryPage(Map<String, Object> params);

}

