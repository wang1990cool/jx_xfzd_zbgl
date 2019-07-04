package io.admin.modules.zbgl.rypb.controller;

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
@RequestMapping("zbgl/rypbmxb")
public class RypbmxbController {
    @Autowired
    private ZbKcmxbService zbKcmxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("zbgl:rypbmxb:list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("ztxs","在人");
        PageUtils page = zbKcmxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("zbgl:rypbmxb:info")
    public R info(@PathVariable("id") Long id){
			ZbKcmxbEntity zbKcmxb = zbKcmxbService.selectById(id);

        return R.ok().put("zbkcmxb", zbKcmxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("zbgl:rypbmxb:save")
    public R save(@RequestBody ZbKcmxbEntity zbKcmxb){
			zbKcmxbService.insert(zbKcmxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("zbgl:rypbmxb:update")
    public R update(@RequestParam Map<String, Object> params){
        String zbbm=(String)params.get("zbbm");
        String rybh=(String)params.get("rybh");
        String ryxm=(String)params.get("ryxm");
        ZbKcmxbEntity zbKcmxb=zbKcmxbService.selectByzbbm(zbbm);
        zbKcmxb.setZtm(3);
        zbKcmxb.setZtxs("在人");
        zbKcmxb.setZt(rybh+ryxm);
        zbKcmxbService.updateById(zbKcmxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("zbgl:rypbmxb:delete")
    public R delete(@RequestBody Long[] ids){
        zbKcmxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/giveback")
    @RequiresPermissions("zbgl:rypbmxb:giveback")
    public R giveback(@RequestBody Long[] ids){

        for (int i =0;i<ids.length;i++){
            Long id=ids[i];
            ZbKcmxbEntity zbKcmxb=zbKcmxbService.selectById(id);
            zbKcmxb.setZtm(1);
            zbKcmxb.setZtxs("在库");
            zbKcmxb.setZt(zbKcmxb.getXssbmmc());
            zbKcmxbService.updateById(zbKcmxb);
        }
        return R.ok();
    }

}
