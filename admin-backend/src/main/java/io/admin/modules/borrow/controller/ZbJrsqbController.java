package io.admin.modules.borrow.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.common.utils.*;
import io.admin.modules.borrow.entity.ZbJrsqmxbEntity;
import io.admin.modules.borrow.service.ZbJrsqmxbService;
import io.admin.modules.sys.entity.SysUserEntity;
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
    @RequiresPermissions("borrow:jrsq:info")
    public R info(@PathVariable("id") Long id){
		ZbJrsqbEntity Jrsqb = jrsqbService.selectById(id);

        return R.ok().put("Jrsqb", Jrsqb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("borrow:jrsq:save")
    public R save(@RequestBody Map<String, Object> models){
        ZbJrsqbEntity jrsqEntity = JsonUtils.toBean(models.get("jrsq"), ZbJrsqbEntity.class);
        List<ZbJrsqmxbEntity> jrsqmxEntityList =
                JsonUtils.toList(models.get("jrsqmx"),ZbJrsqmxbEntity.class);

        String jrsqId = this.getJrsqId();

        System.out.println(jrsqId);

        jrsqEntity.setJrsqid(jrsqId);
        jrsqEntity.setsq
        jrsqEntity.setCreateUserId(ShiroUtils.getUserId());
        jrsqEntity.setCreateTime(new Date());

        if (jrsqmxEntityList.size() > 0 ) {
            for (ZbJrsqmxbEntity zbJrsqmxbEntity : jrsqmxEntityList) {
                zbJrsqmxbEntity.setJrsqid(jrsqId);
            }
        }

        jrsqbService.insert(jrsqEntity);
        jrsqmxService.insertBatch(jrsqmxEntityList);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("borrow:jrsq:update")
    public R update(@RequestBody ZbJrsqbEntity Jrsqb){
        jrsqbService.updateById(Jrsqb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("borrow:jrsq:delete")
    public R delete(@RequestBody Long[] ids){
        jrsqbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    public String getJrsqId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        List<ZbJrsqbEntity> list = jrsqbService.selectList(
                new EntityWrapper<ZbJrsqbEntity>().
                        like("jrsqid", date)
        );

        String [] array = new String[list.size()];
        if(list.size() > 0){
            for(int i = 0;i < list.size(); i++){
                array[i] = list.get(i).getJrsqid();
            }
        }
        return date + "_" + SerialNumUtils.createSerialNum(array, 3);
    }

}