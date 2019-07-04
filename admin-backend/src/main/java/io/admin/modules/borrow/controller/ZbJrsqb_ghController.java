package io.admin.modules.borrow.controller;

import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.modules.borrow.entity.JyJczbbEntity;
import io.admin.modules.borrow.entity.JyJczbmxbEntity;
import io.admin.modules.borrow.entity.ZbJrsqbEntity;
import io.admin.modules.borrow.entity.ZbJrsqmxbEntity;
import io.admin.modules.borrow.service.JyJczbbService;
import io.admin.modules.borrow.service.JyJczbmxbService;
import io.admin.modules.borrow.service.ZbJrsqbService;
import io.admin.modules.borrow.service.ZbJrsqmxbService;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.kcgl.kcmx.service.ZbKcmxbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("borrow/ghJrsq")
public class ZbJrsqb_ghController {

    @Autowired
    private ZbJrsqbService jrsqbService;

    @Autowired
    private JyJczbmxbService jyJczbmxbService;

    @Autowired
    private ZbKcmxbService zbKcmxbService;

    @Autowired
    private JyJczbbService jyJczbbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("borrow:gh:jrsq:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = jrsqbService.queryPageByZtm(params, new String[]{"4","5"});
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

    @RequestMapping("/return")
    public R returnZb(@RequestBody Map<String, Object> params){
        String jrsqid = (String)params.get("jrsqid");
        String zbbm = (String)params.get("zbbm");

        List<JyJczbmxbEntity> jczbmxList = jyJczbmxbService.selectByMap(params);

        if (jczbmxList.size() == 0) {
            return R.error("装备不存在");
        }else {
            JyJczbmxbEntity jyJczbmxbEntity = jczbmxList.get(0);
            if ("已归还".equals(jyJczbmxbEntity.getZt())) {
                return R.error("装备已归还");
            }else {
                jczbmxList.get(0).setZt("已归还");
                jyJczbmxbService.updateById(jczbmxList.get(0));

                ZbKcmxbEntity kcmx = zbKcmxbService.selectByzbbm(zbbm);

                kcmx.setZtm(1);
                kcmx.setZtxs("在库");
                kcmx.setXssbmid(kcmx.getSsbmid());
                kcmx.setXssbmmc(kcmx.getSsbmmc());
                zbKcmxbService.updateById(kcmx);
            }
        }

        List<JyJczbbEntity> jczbs = jyJczbbService.selectByJrsqid(jrsqid);
        List<JyJczbmxbEntity> Jczbmxs = jyJczbmxbService.selectByJrsqidAndZt(jrsqid, new String[]{"已归还"});

        int count = 0;
        for (JyJczbbEntity jczb :  jczbs) {
            count += jczb.getSjsl();
        }

        if (count == Jczbmxs.size()){
            ZbJrsqbEntity jrsqbEntity = jrsqbService.selectByJrsqid(jrsqid);
            jrsqbEntity.setZtm(5);
            jrsqbEntity.setZt("全部归还");
            jrsqbService.updateById(jrsqbEntity);
        }

        return R.ok();
    }


}