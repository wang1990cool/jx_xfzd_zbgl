package io.admin.modules.deviceManage.train.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.enums.KcztEnum;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;
import io.admin.modules.deviceManage.train.dao.DeviceTrainDao;
import io.admin.modules.deviceManage.train.service.DeviceTrainService;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("deviceTrainServiceImpl")
public class DeviceTrainServiceImpl extends ServiceImpl<DeviceTrainDao, ZbKcmxbEntity> implements DeviceTrainService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String ztxs = KcztEnum.XN.getName();
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
