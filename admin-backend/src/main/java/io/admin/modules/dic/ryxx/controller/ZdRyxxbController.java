package io.admin.modules.dic.ryxx.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.dic.ryxx.entity.ZdRyxxbEntity;
import io.admin.modules.dic.ryxx.service.ZdRyxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 人员信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-30 22:50:14
 */
@RestController
@RequestMapping("dic/zdryxxb")
public class ZdRyxxbController {
    @Autowired
    private ZdRyxxbService zdRyxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dic:zdryxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdRyxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("dic:zdryxxb:info")
    public R info(@PathVariable("id") Long id){
			ZdRyxxbEntity zdRyxxb = zdRyxxbService.selectById(id);

        return R.ok().put("zdryxxb", zdRyxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dic:zdryxxb:save")
    public R save(@RequestBody ZdRyxxbEntity zdRyxxb){
			zdRyxxbService.insert(zdRyxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dic:zdryxxb:update")
    public R update(@RequestBody ZdRyxxbEntity zdRyxxb){
			zdRyxxbService.updateById(zdRyxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dic:zdryxxb:delete")
    public R delete(@RequestBody Long[] ids){
			zdRyxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
