package io.admin.modules.bzpb.qcpb.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.bzpb.qcpb.entity.VqcpbbzviewEntity;
import io.admin.modules.bzpb.qcpb.service.VqcpbbzviewService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * VIEW
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 14:39:01
 */
@RestController
@RequestMapping("bzpb/vqcpbbzview")
public class VqcpbbzviewController {
    @Autowired
    private VqcpbbzviewService vqcpbbzviewService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bzpb:vqcpbbzview:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = vqcpbbzviewService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{zblbmc}")
    @RequiresPermissions("bzpb:vqcpbbzview:info")
    public R info(@PathVariable("zblbmc") String zblbmc){
			VqcpbbzviewEntity vqcpbbzview = vqcpbbzviewService.selectById(zblbmc);

        return R.ok().put("vqcpbbzview", vqcpbbzview);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bzpb:vqcpbbzview:save")
    public R save(@RequestBody VqcpbbzviewEntity vqcpbbzview){
			vqcpbbzviewService.insert(vqcpbbzview);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bzpb:vqcpbbzview:update")
    public R update(@RequestBody VqcpbbzviewEntity vqcpbbzview){
			vqcpbbzviewService.updateById(vqcpbbzview);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bzpb:vqcpbbzview:delete")
    public R delete(@RequestBody String[] zblbmcs){
			vqcpbbzviewService.deleteBatchIds(Arrays.asList(zblbmcs));

        return R.ok();
    }



}
