package io.admin.modules.remind.controller;

import io.admin.common.enums.KcztEnum;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.common.utils.ShiroUtils;
import io.admin.modules.borrow.controller.ZplPrinter;
import io.admin.modules.dic.zbmc.entity.ZdZbmcxxbEntity;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.kcgl.kcmx.service.ZbKcmxbService;
import io.admin.modules.remind.entity.ViewRemindScrapEntity;
import io.admin.modules.remind.service.RemindScrapService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static io.admin.common.utils.ShiroUtils.getUserEntity;

@RestController
@RequestMapping("remind/scrap")
public class RemindScrapController {


    @Autowired
    private RemindScrapService remindScrapService;

    @Autowired
    private ZbKcmxbService zbKcmxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("remind:scrap:list")
    public R list(@RequestParam Map<String, Object> params){
        if (!"admin".equals(ShiroUtils.getUserEntity().getUsername())) {
            params.put("ssbmmc", ShiroUtils.getUserEntity().getUnit());
        }

        PageUtils page = remindScrapService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 删除
     */
    @RequestMapping("/scrap")
//    @RequiresPermissions("remind:scrap:scrap")
    public R delete(@RequestBody Long[] ids){
        for (Long id : ids) {
            ZbKcmxbEntity zbKcmxbEntity = zbKcmxbService.selectById(id);
            zbKcmxbEntity.setZtm(KcztEnum.BF.getCode());
            zbKcmxbEntity.setZtxs(KcztEnum.BF.getName());
            zbKcmxbService.updateById(zbKcmxbEntity);
        }
        return R.ok();
    }

}
