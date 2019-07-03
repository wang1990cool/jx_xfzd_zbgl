package io.admin.modules.dic.xfzgrpb.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.dic.xfzgrpb.entity.ZdXfzgrpbxxbEntity;
import io.admin.modules.dic.xfzgrpb.service.ZdXfzgrpbxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 消防站个人配备信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 17:00:35
 */
@RestController
@RequestMapping("dic/zdxfzgrpbxxb")
public class ZdXfzgrpbxxbController {
    @Autowired
    private ZdXfzgrpbxxbService zdXfzgrpbxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dic:zdxfzgrpbxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdXfzgrpbxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("dic:zdxfzgrpbxxb:info")
    public R info(@PathVariable("id") Long id){
			ZdXfzgrpbxxbEntity zdXfzgrpbxxb = zdXfzgrpbxxbService.selectById(id);

        return R.ok().put("zdxfzgrpbxxb", zdXfzgrpbxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dic:zdxfzgrpbxxb:save")
    public R save(@RequestBody ZdXfzgrpbxxbEntity zdXfzgrpbxxb){
			zdXfzgrpbxxbService.insert(zdXfzgrpbxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dic:zdxfzgrpbxxb:update")
    public R update(@RequestBody ZdXfzgrpbxxbEntity zdXfzgrpbxxb){
			zdXfzgrpbxxbService.updateById(zdXfzgrpbxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dic:zdxfzgrpbxxb:delete")
    public R delete(@RequestBody Long[] ids){
			zdXfzgrpbxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
