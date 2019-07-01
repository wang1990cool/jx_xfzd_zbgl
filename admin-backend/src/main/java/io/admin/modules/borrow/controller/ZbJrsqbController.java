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

import io.admin.modules.borrow.entity.ZbJrsqbEntity;
import io.admin.modules.borrow.service.ZbJrsqbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 装备借入申请表
 *
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-01 09:07:30
 */
@RestController
@RequestMapping("borrow/zbjrsqb")
public class ZbJrsqbController {
    @Autowired
    private ZbJrsqbService zbJrsqbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("borrow:zbjrsqb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zbJrsqbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("borrow:zbjrsqb:info")
    public R info(@PathVariable("id") Long id){
		ZbJrsqbEntity zbJrsqb = zbJrsqbService.selectById(id);

        return R.ok().put("zbJrsqb", zbJrsqb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("borrow:zbjrsqb:save")
    public R save(@RequestBody ZbJrsqbEntity zbJrsqb){
		zbJrsqbService.insert(zbJrsqb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("borrow:zbjrsqb:update")
    public R update(@RequestBody ZbJrsqbEntity zbJrsqb){
		zbJrsqbService.updateById(zbJrsqb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("borrow:zbjrsqb:delete")
    public R delete(@RequestBody Long[] ids){
		zbJrsqbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
