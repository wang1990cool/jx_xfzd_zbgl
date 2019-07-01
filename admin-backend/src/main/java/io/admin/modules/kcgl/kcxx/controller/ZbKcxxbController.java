package io.admin.modules.kcgl.kcxx.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.kcgl.kcmx.service.ZbKcmxbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.kcgl.kcxx.entity.ZbKcxxbEntity;
import io.admin.modules.kcgl.kcxx.service.ZbKcxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 库存信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-01 11:17:05
 */
@RestController
@RequestMapping("kcgl/zbkcxxb")
public class ZbKcxxbController {
    @Autowired
    private ZbKcxxbService zbKcxxbService;
    @Autowired
    private ZbKcmxbService zbKcmxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("kcgl:zbkcxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zbKcxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("kcgl:zbkcxxb:info")
    public R info(@PathVariable("id") Integer id){
			ZbKcxxbEntity zbKcxxb = zbKcxxbService.selectById(id);

        return R.ok().put("zbKcxxb", zbKcxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("kcgl:zbkcxxb:save")
    public R save(@RequestBody ZbKcxxbEntity zbKcxxb){
            int rksl=zbKcxxb.getZbsl();
            for(int i=0 ;i<rksl;i++){
                ZbKcmxbEntity zbKcmxb=new ZbKcmxbEntity();
                zbKcmxb.setZbbm(zbKcmxbService.getXlh(zbKcxxb.getZbid()));
                zbKcmxb.setZbid(zbKcxxb.getZbid());
                zbKcmxb.setZbmc(zbKcxxb.getZbmc());
                zbKcmxb.setSsbmid(zbKcxxb.getSsbmid());
                zbKcmxb.setSsbmmc(zbKcxxb.getSsbmmc());
                zbKcmxb.setZtm(1);
                zbKcmxb.setZtxs("在库");
                zbKcmxb.setZt(zbKcxxb.getSsbmmc());
                zbKcmxb.setXssbmid(zbKcxxb.getSsbmid());
                zbKcmxb.setXssbmmc(zbKcxxb.getSsbmmc());
                zbKcmxb.setScrq(zbKcxxb.getScrq());
                zbKcmxb.setCreateTime(new Date());
                zbKcmxbService.insert(zbKcmxb);
            }
            ZbKcxxbEntity zbKcxx=zbKcxxbService.selectByzbid(zbKcxxb.getZbid(),zbKcxxb.getSsbmmc());
            if(zbKcxx==null){
                zbKcxxbService.insert(zbKcxxb);
            }else{
                zbKcxx.setZbsl(zbKcxx.getZbsl()+zbKcxxb.getZbsl());
                zbKcxxbService.updateById(zbKcxx);
            }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("kcgl:zbkcxxb:update")
    public R update(@RequestBody ZbKcxxbEntity zbKcxxb){
			zbKcxxbService.updateById(zbKcxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("kcgl:zbkcxxb:delete")
    public R delete(@RequestBody Integer[] ids){
			zbKcxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
