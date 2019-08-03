package io.admin.modules.borrow.controller;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.common.enums.KcztEnum;
import io.admin.common.utils.JsonUtils;
import io.admin.common.utils.ShiroUtils;
import io.admin.modules.borrow.entity.JyJczbbEntity;
import io.admin.modules.borrow.entity.ZbJrsqbEntity;
import io.admin.modules.borrow.entity.ZbJrsqmxbEntity;
import io.admin.modules.borrow.service.JyJczbbService;
import io.admin.modules.borrow.service.ZbJrsqbService;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.kcgl.kcmx.service.ZbKcmxbService;
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
@RequestMapping("borrow/jczbmx")
public class JyJczbmxbController {
    @Autowired
    private JyJczbmxbService jyJczbmxbService;

    @Autowired
    private JyJczbbService jyJczbbService;

    @Autowired
    private ZbKcmxbService zbKcmxbService;

    @Autowired
    private ZbJrsqbService jrsqbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<JyJczbmxbEntity> list = jyJczbmxbService.selectByJrsqidAndZt(
                (String) params.get("jrsqid"), new String[] {"借用"});
        return R.ok().put("data", list);
    }

    @RequestMapping("/returnList")
    public R returnList(@RequestParam Map<String, Object> params){
        List<JyJczbmxbEntity> list = jyJczbmxbService.selectByJrsqidAndZt(
                (String) params.get("jrsqid"), new String[] {"借用","已归还"});
        return R.ok().put("data", list);
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

    @RequestMapping("/geZbmxByZbbm")
    public R geZbmxByZbbm(@RequestParam Map<String, Object> params){
        List<ZbKcmxbEntity> kcmxs = zbKcmxbService.selectByMap(params);

        if (kcmxs.size() == 0) {
            return R.error("该装备不存在");
        } else {
            if (!"在库".equals(kcmxs.get(0).getZtxs())){
                return R.error("该装备不在库");
            }else {
                return R.ok().put("data" , kcmxs.get(0));
            }
        }
    }

    @RequestMapping("/addKcmx")
    public R addKcmx(@RequestBody Map<String, Object> models){
        String jrsqid = (String)models.get("jrsqid");

        Map<String,Object> jrsqmxMap =JsonUtils.toHashMap(models.get("jrsqmx"));
        List<JyJczbmxbEntity> insertRecords = JsonUtils.toList(jrsqmxMap.get("insertRecords"),JyJczbmxbEntity.class);
        List<JyJczbmxbEntity> removeRecords = JsonUtils.toList(jrsqmxMap.get("removeRecords"),JyJczbmxbEntity.class);

        Map map1 = new HashMap();
        map1.put("jrsqid", jrsqid);

        List<ZbJrsqbEntity> jrsqbs = jrsqbService.selectByMap(map1);
        ZbJrsqbEntity jrsqb = jrsqbs.get(0);

        if (insertRecords.size() > 0 ) {
            jyJczbmxbService.insertBatch(insertRecords);

            for (JyJczbmxbEntity jcmx : insertRecords){
                String zbbm = jcmx.getZbbm();
                Map map = new HashMap();
                map.put("zbbm", zbbm);

                List<ZbKcmxbEntity> kcmxList = zbKcmxbService.selectByMap(map);
                ZbKcmxbEntity kcmx = kcmxList.get(0);
                kcmx.setZtm(KcztEnum.JC.getCode());
                kcmx.setZtxs(KcztEnum.JC.getName());
                kcmx.setXssbmid( jrsqb.getJcbmid());
                kcmx.setXssbmmc( jrsqb.getJcbmmc());

                zbKcmxbService.updateById(kcmx);
            }
        }

        if (removeRecords.size() > 0){
            for (JyJczbmxbEntity jcmx : removeRecords){
               String zbbm = jcmx.getZbbm();
               Map map = new HashMap();
               map.put("zbbm", zbbm);
               jyJczbmxbService.deleteByMap(map);

                List<ZbKcmxbEntity> kcmxList = zbKcmxbService.selectByMap(map);
                ZbKcmxbEntity kcmx = kcmxList.get(0);
                kcmx.setZt("1");
                kcmx.setZtxs("在库");
                kcmx.setXssbmid( kcmx.getSsbmid());
                kcmx.setXssbmmc( kcmx.getSsbmmc());

                zbKcmxbService.updateById(kcmx);
            }
        }

        List<JyJczbbEntity> jyJczbbEntities = jyJczbbService.selectByMap(map1);
        this.updateSjsl(jyJczbbEntities);

        List<ZbJrsqbEntity> jrsqbEntities  = jrsqbService.selectList(
                new EntityWrapper<ZbJrsqbEntity>().
                        eq("jrsqid", jrsqid)
        );
        jrsqbEntities.get(0).setZtm(4);
        jrsqbEntities.get(0).setZt("已领用");
        jrsqbService.updateById(jrsqbEntities.get(0));

        return R.ok();
    }

    public void updateSjsl(List<JyJczbbEntity> jyJczbbEntities) {
        for (JyJczbbEntity jczb : jyJczbbEntities){
            List<JyJczbmxbEntity> list = jyJczbmxbService.selectList(
                    new EntityWrapper<JyJczbmxbEntity>().
                            eq("jrsqid", jczb.getJrsqid()).
                            eq("zbid", jczb.getZbid())
            );
            if (list.size() > 0) {
                jczb.setSjsl(list.size());
            }else {
                jczb.setSjsl(0);
            }
            jyJczbbService.updateById(jczb);
        }
    }

}
