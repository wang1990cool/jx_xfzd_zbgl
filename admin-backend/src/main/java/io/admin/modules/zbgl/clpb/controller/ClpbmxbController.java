package io.admin.modules.zbgl.clpb.controller;

import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.kcgl.kcmx.service.ZbKcmxbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 装备库存明细信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-01 11:13:25
 */
@RestController
@RequestMapping("zbgl/clpbmxb")
public class ClpbmxbController {
    @Autowired
    private ZbKcmxbService zbKcmxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("zbgl:clpbmxb:list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("ztxs","在车");
        PageUtils page = zbKcmxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("zbgl:clpbmxb:info")
    public R info(@PathVariable("id") Long id){
			ZbKcmxbEntity zbKcmxb = zbKcmxbService.selectById(id);

        return R.ok().put("zbkcmxb", zbKcmxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("zbgl:clpbmxb:save")
    public R save(@RequestBody ZbKcmxbEntity zbKcmxb){
			zbKcmxbService.insert(zbKcmxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("zbgl:clpbmxb:update")
    public R update(@RequestParam Map<String, Object> params){
        String zbbm=(String)params.get("zbbm");
        String cph=(String)params.get("cph");
        ZbKcmxbEntity zbKcmxb=zbKcmxbService.selectByzbbm(zbbm);
        zbKcmxb.setZtm(2);
        zbKcmxb.setZtxs("在车");
        zbKcmxb.setZt(cph);
        zbKcmxbService.updateById(zbKcmxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("zbgl:clpbmxb:delete")
    public R delete(@RequestBody Long[] ids){
			zbKcmxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
