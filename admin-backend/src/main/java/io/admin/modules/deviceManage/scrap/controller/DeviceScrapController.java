package io.admin.modules.deviceManage.scrap.controller;

import io.admin.common.annotation.SysLog;
import io.admin.common.enums.KcztEnum;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.common.validator.group.AddGroup;
import io.admin.modules.borrow.controller.ZplPrinter;
import io.admin.modules.deviceManage.scrap.service.DeviceScrapService;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.kcgl.kcmx.service.ZbKcmxbService;
import io.admin.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static io.admin.common.utils.ShiroUtils.getUserEntity;


@RestController
@RequestMapping("device/scrap")
public class DeviceScrapController {
    @Autowired
    private DeviceScrapService deviceScrapService;

    @Autowired
    private ZbKcmxbService zbKcmxbService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("device:scrap:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = deviceScrapService.queryPage(params);

        return R.ok().put("page", page);
    }

    @PostMapping("/scrap")
    public R scrap(@RequestBody Long[] ids){

        for (Long id : ids) {
            ZbKcmxbEntity mx = zbKcmxbService.selectById(id);
            mx.setZtm(KcztEnum.BF.getCode());
            mx.setZtxs(KcztEnum.BF.getName());
            zbKcmxbService.updateById(mx);
        }

        return R.ok();
    }



}
