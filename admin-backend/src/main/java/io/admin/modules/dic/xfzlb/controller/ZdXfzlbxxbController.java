package io.admin.modules.dic.xfzlb.controller;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.dic.xfzlb.entity.ZdXfzlbxxbEntity;
import io.admin.modules.dic.xfzlb.service.ZdXfzlbxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 消防站类别信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-29 10:51:42
 */
@RestController
@RequestMapping("dic/zdxfzlbxxb")
public class ZdXfzlbxxbController {
    @Autowired
    private ZdXfzlbxxbService zdXfzlbxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dic:zdxfzlbxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdXfzlbxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("dic:zdxfzlbxxb:info")
    public R info(@PathVariable("id") Long id){
			ZdXfzlbxxbEntity zdXfzlbxxb = zdXfzlbxxbService.selectById(id);

        return R.ok().put("zdxfzlbxxb", zdXfzlbxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dic:zdxfzlbxxb:save")
    public R save(@RequestBody ZdXfzlbxxbEntity zdXfzlbxxb){
			zdXfzlbxxbService.insert(zdXfzlbxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dic:zdxfzlbxxb:update")
    public R update(@RequestBody ZdXfzlbxxbEntity zdXfzlbxxb){
			zdXfzlbxxbService.updateById(zdXfzlbxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dic:zdxfzlbxxb:delete")
    public R delete(@RequestBody Long[] ids){
			zdXfzlbxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/select")
    public R selectList(){
        List<ZdXfzlbxxbEntity> xfzlbList= zdXfzlbxxbService.selectList(new EntityWrapper<ZdXfzlbxxbEntity>());
        ArrayList arrays = new ArrayList();
        for (int i=0;i<xfzlbList.size();i++){
            Map map = new HashMap();
            map.put("value",xfzlbList.get(i).getId());
            map.put("label",xfzlbList.get(i).getXfzlbmc());
            arrays.add(map);
        }
        return R.ok().put("xfzlbList",arrays);
    }

}
