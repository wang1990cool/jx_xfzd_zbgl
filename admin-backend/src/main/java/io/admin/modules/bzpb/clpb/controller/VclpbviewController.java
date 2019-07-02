package io.admin.modules.bzpb.clpb.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.bzpb.clpb.entity.VclpbviewEntity;
import io.admin.modules.bzpb.clpb.service.VclpbviewService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * VIEW
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 23:53:20
 */
@RestController
@RequestMapping("bzpb/vclpbview")
public class VclpbviewController {
    @Autowired
    private VclpbviewService vclpbviewService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bzpb:vclpbview:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = vclpbviewService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("bzpb:vclpbview:info")
    public R info(@PathVariable("id") Long id){
			VclpbviewEntity vclpbview = vclpbviewService.selectById(id);

        return R.ok().put("vclpbview", vclpbview);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bzpb:vclpbview:save")
    public R save(@RequestBody VclpbviewEntity vclpbview){
			vclpbviewService.insert(vclpbview);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bzpb:vclpbview:update")
    public R update(@RequestBody VclpbviewEntity vclpbview){
			vclpbviewService.updateById(vclpbview);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bzpb:vclpbview:delete")
    public R delete(@RequestBody Long[] ids){
			vclpbviewService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
