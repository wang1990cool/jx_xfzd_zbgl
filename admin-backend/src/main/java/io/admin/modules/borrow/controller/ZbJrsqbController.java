package io.admin.modules.borrow.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.common.utils.*;
import io.admin.modules.borrow.entity.ZbJrsqmxbEntity;
import io.admin.modules.borrow.service.ZbJrsqmxbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.borrow.entity.ZbJrsqbEntity;
import io.admin.modules.borrow.service.ZbJrsqbService;


/**
 * 装备借入申请表
 *
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-01 09:07:30
 */
@RestController
@RequestMapping("borrow/jrsq")
public class ZbJrsqbController {

    @Autowired
    private ZbJrsqbService jrsqbService;
    @Autowired
    private ZbJrsqmxbService jrsqmxService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("borrow:jrsq:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = jrsqbService.queryPage(params);

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

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("borrow:jrsq:save")
    public R save(@RequestBody Map<String, Object> models){
        ZbJrsqbEntity jrsqEntity = JsonUtils.toBean(models.get("jrsq"), ZbJrsqbEntity.class);
        Map<String,Object> jrsqmxMap =JsonUtils.toHashMap(models.get("jrsqmx"));
        List<ZbJrsqmxbEntity> insertRecords = JsonUtils.toList(jrsqmxMap.get("insertRecords"),ZbJrsqmxbEntity.class);
        List<ZbJrsqmxbEntity> updateRecords = JsonUtils.toList(jrsqmxMap.get("updateRecords"),ZbJrsqmxbEntity.class);

        String jrsqId = this.getJrsqId();

        // 状态赋值
        jrsqEntity.setZtm(1);
        jrsqEntity.setZt("草稿状态");
        // 申请单编号赋值
        jrsqEntity.setJrsqid(jrsqId);

        // 创建人和创建时间赋值
        jrsqEntity.setCreateUserId(ShiroUtils.getUserId());
        jrsqEntity.setCreateTime(new Date());

        // 申请部门赋值
        jrsqEntity.setSqbmid(ShiroUtils.getUserEntity().getUnitId());
        jrsqEntity.setSqbmmc(ShiroUtils.getUserEntity().getUnit());

        if (insertRecords.size() > 0 ) {
            for (ZbJrsqmxbEntity zbJrsqmxbEntity : insertRecords) {
                zbJrsqmxbEntity.setJrsqid(jrsqId);
            }
            jrsqmxService.insertBatch(insertRecords);
        }

        if (updateRecords.size() > 0){
            for (ZbJrsqmxbEntity jrmx : updateRecords){
                if (jrmx.getId() != null){
                    jrsqmxService.updateById(jrmx);
                }else {
                    jrsqmxService.insert(jrmx);
                }
            }
        }

        jrsqbService.insert(jrsqEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody Map<String, Object> models){
        ZbJrsqbEntity jrsqEntity = JsonUtils.toBean(models.get("jrsq"), ZbJrsqbEntity.class);
        Map<String,Object> jrsqmxMap =JsonUtils.toHashMap(models.get("jrsqmx"));

        jrsqbService.updateById(jrsqEntity);

        List<ZbJrsqmxbEntity> insertRecords = JsonUtils.toList(jrsqmxMap.get("insertRecords"),ZbJrsqmxbEntity.class);
        List<ZbJrsqmxbEntity> updateRecords = JsonUtils.toList(jrsqmxMap.get("updateRecords"),ZbJrsqmxbEntity.class);
        List<ZbJrsqmxbEntity> removeRecords = JsonUtils.toList(jrsqmxMap.get("removeRecords"),ZbJrsqmxbEntity.class);

        if (insertRecords.size() > 0) {
            for (ZbJrsqmxbEntity jrmx : insertRecords){
                jrmx.setJrsqid(jrsqEntity.getJrsqid());
                jrsqmxService.insert(jrmx);
            }
        }

        if (updateRecords.size() > 0){
            for (ZbJrsqmxbEntity jrmx : updateRecords){
                if (jrmx.getId() != null){
                    jrsqmxService.updateById(jrmx);
                }else {
                    jrsqmxService.insert(jrmx);
                }
            }
        }

        if (removeRecords.size() > 0){
            for (ZbJrsqmxbEntity jrmx : removeRecords){
                jrsqmxService.deleteById(jrmx.getId());
            }
        }

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("borrow:jrsq:delete")
    public R delete(@RequestBody String[] jrsqIds){

        jrsqbService.deleteByJrsqIds(jrsqIds);
        jrsqmxService.deleteByJrsqIds(jrsqIds);

        return R.ok();
    }

    @RequestMapping("/submit")
    @RequiresPermissions("borrow:jrsq:submit")
    public R submit(@RequestBody String id) {
        ZbJrsqbEntity jrsqbEntity = jrsqbService.selectById(id);
        jrsqbEntity.setZtm(2);
        jrsqbEntity.setZt("已提交");
        jrsqbService.updateById(jrsqbEntity);
        return R.ok();
    }

    @RequestMapping("/withdraw")
    @RequiresPermissions("borrow:jrsq:withdraw")
    public R withdraw(@RequestBody String id) {
        ZbJrsqbEntity jrsqbEntity = jrsqbService.selectById(id);
        jrsqbEntity.setZtm(1);
        jrsqbEntity.setZt("草稿状态");
        jrsqbService.updateById(jrsqbEntity);
        return R.ok();
    }


    public String getJrsqId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        List<ZbJrsqbEntity> list = jrsqbService.selectList(
                new EntityWrapper<ZbJrsqbEntity>().
                        like("jrsqid", "JY" + date)
        );

        String [] array = new String[list.size()];
        if(list.size() > 0){
            for(int i = 0;i < list.size(); i++){
                array[i] = list.get(i).getJrsqid();
            }
        }
        return "JY" + date + "_" + SerialNumUtils.createSerialNum(array, 3);
    }

}