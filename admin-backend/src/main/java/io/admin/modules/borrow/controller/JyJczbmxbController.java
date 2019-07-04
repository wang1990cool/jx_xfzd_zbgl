package io.admin.modules.borrow.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.borrow.entity.JyJczbmxbEntity;
import io.admin.modules.borrow.service.JyJczbmxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 装备名称信息表
 *
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-03 09:03:51
 */
@RestController
@RequestMapping("borrow/jyjczbmxb")
public class JyJczbmxbController {
    @Autowired
    private JyJczbmxbService jyJczbmxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("borrow:jyjczbmxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = jyJczbmxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("borrow:jyjczbmxb:info")
    public R info(@PathVariable("id") Long id){
		JyJczbmxbEntity jyJczbmxb = jyJczbmxbService.selectById(id);

        return R.ok().put("jyJczbmxb", jyJczbmxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("borrow:jyjczbmxb:save")
    public R save(@RequestBody JyJczbmxbEntity jyJczbmxb){
		jyJczbmxbService.insert(jyJczbmxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("borrow:jyjczbmxb:update")
    public R update(@RequestBody JyJczbmxbEntity jyJczbmxb){
		jyJczbmxbService.updateById(jyJczbmxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("borrow:jyjczbmxb:delete")
    public R delete(@RequestBody Long[] ids){
		jyJczbmxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
