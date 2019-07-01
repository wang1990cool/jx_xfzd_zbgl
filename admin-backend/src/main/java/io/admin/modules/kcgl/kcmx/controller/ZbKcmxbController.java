package io.admin.modules.kcgl.kcmx.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.kcgl.kcmx.service.ZbKcmxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 装备库存明细信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-01 11:13:25
 */
@RestController
@RequestMapping("kcgl/zbkcmxb")
public class ZbKcmxbController {
    @Autowired
    private ZbKcmxbService zbKcmxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("kcgl:zbkcmxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zbKcmxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("kcgl:zbkcmxb:info")
    public R info(@PathVariable("id") Long id){
			ZbKcmxbEntity zbKcmxb = zbKcmxbService.selectById(id);

        return R.ok().put("zbkcmxb", zbKcmxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("kcgl:zbkcmxb:save")
    public R save(@RequestBody ZbKcmxbEntity zbKcmxb){
			zbKcmxbService.insert(zbKcmxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("kcgl:zbkcmxb:update")
    public R update(@RequestBody ZbKcmxbEntity zbKcmxb){
			zbKcmxbService.updateById(zbKcmxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("kcgl:zbkcmxb:delete")
    public R delete(@RequestBody Long[] ids){
			zbKcmxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
