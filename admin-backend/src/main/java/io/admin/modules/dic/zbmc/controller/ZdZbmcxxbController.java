package io.admin.modules.dic.zbmc.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.dic.zbmc.entity.ZdZbmcxxbEntity;
import io.admin.modules.dic.zbmc.service.ZdZbmcxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;

import static io.admin.common.utils.ShiroUtils.getUserEntity;


/**
 * 装备名称信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-29 09:45:56
 */
@RestController
@RequestMapping("dic/zdzbmcxxb")
public class ZdZbmcxxbController {
    @Autowired
    private ZdZbmcxxbService zdZbmcxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dic:zdzbmcxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdZbmcxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("dic:zdzbmcxxb:info")
    public R info(@PathVariable("id") Long id){
			ZdZbmcxxbEntity zdZbmcxxb = zdZbmcxxbService.selectById(id);

        return R.ok().put("zdzbmcxxb", zdZbmcxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dic:zdzbmcxxb:save")
    public R save(@RequestBody ZdZbmcxxbEntity zdZbmcxxb){
        zdZbmcxxb.setCreateUserId(getUserEntity().getUserId());
        zdZbmcxxb.setCreateTime(new Date());
        zdZbmcxxb.setZbid(zdZbmcxxbService.getXlh(zdZbmcxxb.getZblbid()));
		zdZbmcxxbService.insert(zdZbmcxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dic:zdzbmcxxb:update")
    public R update(@RequestBody ZdZbmcxxbEntity zdZbmcxxb){
			zdZbmcxxbService.updateById(zdZbmcxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dic:zdzbmcxxb:delete")
    public R delete(@RequestBody Long[] ids){
			zdZbmcxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
