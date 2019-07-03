package io.admin.modules.bzpb.grpb.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.bzpb.grpb.entity.VgrpbbzviewEntity;
import io.admin.modules.bzpb.grpb.service.VgrpbbzviewService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * VIEW
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 22:35:45
 */
@RestController
@RequestMapping("bzpb/vgrpbbzview")
public class VgrpbbzviewController {
    @Autowired
    private VgrpbbzviewService vgrpbbzviewService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bzpb:vgrpbbzview:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = vgrpbbzviewService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{zblbmc}")
    @RequiresPermissions("bzpb:vgrpbbzview:info")
    public R info(@PathVariable("zblbmc") String zblbmc){
			VgrpbbzviewEntity vgrpbbzview = vgrpbbzviewService.selectById(zblbmc);

        return R.ok().put("vgrpbbzview", vgrpbbzview);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bzpb:vgrpbbzview:save")
    public R save(@RequestBody VgrpbbzviewEntity vgrpbbzview){
			vgrpbbzviewService.insert(vgrpbbzview);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bzpb:vgrpbbzview:update")
    public R update(@RequestBody VgrpbbzviewEntity vgrpbbzview){
			vgrpbbzviewService.updateById(vgrpbbzview);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bzpb:vgrpbbzview:delete")
    public R delete(@RequestBody String[] zblbmcs){
			vgrpbbzviewService.deleteBatchIds(Arrays.asList(zblbmcs));

        return R.ok();
    }

}
