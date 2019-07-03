package io.admin.modules.dic.xfzclpb.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.dic.xfzclpb.entity.ZdXfzclpbxxbEntity;
import io.admin.modules.dic.xfzclpb.service.ZdXfzclpbxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 消防站车辆配备信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 23:20:09
 */
@RestController
@RequestMapping("dic/zdxfzclpbxxb")
public class ZdXfzclpbxxbController {
    @Autowired
    private ZdXfzclpbxxbService zdXfzclpbxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dic:zdxfzclpbxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdXfzclpbxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("dic:zdxfzclpbxxb:info")
    public R info(@PathVariable("id") Long id){
			ZdXfzclpbxxbEntity zdXfzclpbxxb = zdXfzclpbxxbService.selectById(id);

        return R.ok().put("zdxfzclpbxxb", zdXfzclpbxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dic:zdxfzclpbxxb:save")
    public R save(@RequestBody ZdXfzclpbxxbEntity zdXfzclpbxxb){
			zdXfzclpbxxbService.insert(zdXfzclpbxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dic:zdxfzclpbxxb:update")
    public R update(@RequestBody ZdXfzclpbxxbEntity zdXfzclpbxxb){
			zdXfzclpbxxbService.updateById(zdXfzclpbxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dic:zdxfzclpbxxb:delete")
    public R delete(@RequestBody Long[] ids){
			zdXfzclpbxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
