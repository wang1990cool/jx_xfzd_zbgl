package io.admin.modules.dic.bmgl.controller;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.dic.bmgl.entity.ZdBmmcxxbEntity;
import io.admin.modules.dic.bmgl.service.ZdBmmcxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 部门信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-30 21:54:12
 */
@RestController
@RequestMapping("dic/zdbmmcxxb")
public class ZdBmmcxxbController {
    @Autowired
    private ZdBmmcxxbService zdBmmcxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dic:zdbmmcxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdBmmcxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("dic:zdbmmcxxb:info")
    public R info(@PathVariable("id") Long id){
			ZdBmmcxxbEntity zdBmmcxxb = zdBmmcxxbService.selectById(id);

        return R.ok().put("zdbmmcxxb", zdBmmcxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dic:zdbmmcxxb:save")
    public R save(@RequestBody ZdBmmcxxbEntity zdBmmcxxb){
			zdBmmcxxbService.insert(zdBmmcxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dic:zdbmmcxxb:update")
    public R update(@RequestBody ZdBmmcxxbEntity zdBmmcxxb){
			zdBmmcxxbService.updateById(zdBmmcxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dic:zdbmmcxxb:delete")
    public R delete(@RequestBody Long[] ids){
			zdBmmcxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/select")
    public R selectList(){
        List<ZdBmmcxxbEntity> bmmcList= zdBmmcxxbService.selectList(new EntityWrapper<ZdBmmcxxbEntity>());
        ArrayList arrays = new ArrayList();
        for (int i=0;i<bmmcList.size();i++){
            Map map = new HashMap();
            map.put("value",bmmcList.get(i).getId());
            map.put("label",bmmcList.get(i).getBmmc());
            arrays.add(map);
        }
        return R.ok().put("bmmcList",arrays);
    }

}
