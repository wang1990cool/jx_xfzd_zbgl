package io.admin.modules.deviceManage.train.controller;

import io.admin.common.enums.KcztEnum;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.modules.deviceManage.train.service.DeviceTrainService;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.kcgl.kcmx.service.ZbKcmxbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("device/train")
public class DeviceTrainController {
    @Autowired
    private DeviceTrainService deviceTrainService;

    @Autowired
    private ZbKcmxbService zbKcmxbService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("device:train:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = deviceTrainService.queryPage(params);

        return R.ok().put("page", page);
    }

    @PostMapping("/train")
    public R train(@RequestBody Long[] ids){

        for (Long id : ids) {
            ZbKcmxbEntity mx = zbKcmxbService.selectById(id);
            mx.setZtm(KcztEnum.XN.getCode());
            mx.setZtxs(KcztEnum.XN.getName());
            zbKcmxbService.updateById(mx);
        }

        return R.ok();
    }



}
