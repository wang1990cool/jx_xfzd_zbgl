package io.admin.modules.deviceManage.scrap.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.enums.KcztEnum;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;
import io.admin.modules.deviceManage.scrap.dao.DeviceScrapDao;
import io.admin.modules.deviceManage.scrap.service.DeviceScrapService;
import io.admin.modules.kcgl.kcmx.dao.ZbKcmxbDao;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.kcgl.kcmx.service.ZbKcmxbService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("deviceScrapServiceImpl")
public class DeviceScrapServiceImpl extends ServiceImpl<DeviceScrapDao, ZbKcmxbEntity> implements DeviceScrapService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String ztxs = KcztEnum.BF.getName();
        String ssbmmc = (String)params.get("ssbmmc");
        String zbmc = (String)params.get("zbmc");

        Page<ZbKcmxbEntity> page = this.selectPage(
                new Query<ZbKcmxbEntity>(params).getPage(),
                new EntityWrapper<ZbKcmxbEntity>().
//                like(StringUtils.isNotBlank(ssbmmc), "ssbmmc", ssbmmc).
                like(StringUtils.isNotBlank(zbmc), "zbmc", zbmc).
                eq(StringUtils.isNotBlank(ztxs), "ztxs", ztxs)

        );

        return new PageUtils(page);
    }

}
