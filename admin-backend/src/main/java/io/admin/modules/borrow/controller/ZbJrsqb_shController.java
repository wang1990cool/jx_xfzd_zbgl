package io.admin.modules.borrow.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.common.utils.*;
import io.admin.modules.borrow.entity.ZbJrsqbEntity;
import io.admin.modules.borrow.entity.ZbJrsqmxbEntity;
import io.admin.modules.borrow.service.ZbJrsqbService;
import io.admin.modules.borrow.service.ZbJrsqmxbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("borrow/shJrsq")
public class ZbJrsqb_shController {

    @Autowired
    private ZbJrsqbService jrsqbService;
    @Autowired
    private ZbJrsqmxbService jrsqmxService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("borrow:sh:jrsq:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = jrsqbService.queryPageSh(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ZbJrsqbEntity jrsqb = jrsqbService.selectById(id);
        return R.ok().put("jrsqb", jrsqb);
    }

    @RequestMapping("/audit")
    public R audit(@RequestBody Map<String, Object> models) {
        Integer id = (Integer) models.get("id");
        String cz = (String) models.get("cz");

        ZbJrsqbEntity jrsqb = jrsqbService.selectById(id);

        if ("agree".equals(cz)) {
            jrsqb.setZtm(3);
            jrsqb.setZt("审核通过");
            jrsqbService.updateById(jrsqb);
        }else {
            jrsqb.setZtm(9);
            jrsqb.setZt("审核不通过");
            jrsqbService.updateById(jrsqb);
        }

        return R.ok();
    }

}