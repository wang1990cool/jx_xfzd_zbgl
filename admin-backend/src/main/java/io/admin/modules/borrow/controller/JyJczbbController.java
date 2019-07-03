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

import io.admin.modules.borrow.entity.JyJczbbEntity;
import io.admin.modules.borrow.service.JyJczbbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 
 *
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-03 09:03:51
 */
@RestController
@RequestMapping("borrow/jyjczbb")
public class JyJczbbController {
    @Autowired
    private JyJczbbService jyJczbbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("borrow:jyjczbb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = jyJczbbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("borrow:jyjczbb:info")
    public R info(@PathVariable("id") Long id){
		JyJczbbEntity jyJczbb = jyJczbbService.selectById(id);

        return R.ok().put("jyJczbb", jyJczbb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("borrow:jyjczbb:save")
    public R save(@RequestBody JyJczbbEntity jyJczbb){
		jyJczbbService.insert(jyJczbb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("borrow:jyjczbb:update")
    public R update(@RequestBody JyJczbbEntity jyJczbb){
		jyJczbbService.updateById(jyJczbb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("borrow:jyjczbb:delete")
    public R delete(@RequestBody Long[] ids){
		jyJczbbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
