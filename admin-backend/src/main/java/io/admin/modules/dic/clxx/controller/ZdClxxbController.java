package io.admin.modules.dic.clxx.controller;

import java.util.*;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.dic.clxx.entity.ZdClxxbEntity;
import io.admin.modules.dic.clxx.service.ZdClxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;

import static io.admin.common.utils.ShiroUtils.getUserEntity;


/**
 * 库存信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-01 20:09:18
 */
@RestController
@RequestMapping("dic/zdclxxb")
public class ZdClxxbController {
    @Autowired
    private ZdClxxbService zdClxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dic:zdclxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdClxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("dic:zdclxxb:info")
    public R info(@PathVariable("id") Integer id){
			ZdClxxbEntity zdClxxb = zdClxxbService.selectById(id);

        return R.ok().put("zdclxxb", zdClxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dic:zdclxxb:save")
    public R save(@RequestBody ZdClxxbEntity zdClxxb){
			zdClxxbService.insert(zdClxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dic:zdclxxb:update")
    public R update(@RequestBody ZdClxxbEntity zdClxxb){
			zdClxxbService.updateById(zdClxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dic:zdclxxb:delete")
    public R delete(@RequestBody Integer[] ids){
			zdClxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/select")
    public R selectList(@RequestParam String ssbmid){
        List<ZdClxxbEntity> clxxList= zdClxxbService.selectByssbmmc(ssbmid);
        ArrayList arrays = new ArrayList();
        for (int i=0;i<clxxList.size();i++){
            Map map = new HashMap();
            map.put("value",clxxList.get(i).getId());
            map.put("label",clxxList.get(i).getCph());
            arrays.add(map);
        }

        return R.ok().put("clxxList",arrays);
    }

    @RequestMapping("/selectByssbmmc")
    public R selectList(){
        String ssbmmc=getUserEntity().getUnit();
        List<ZdClxxbEntity> clxxList= zdClxxbService.selectByssbmmc(ssbmmc);
        ArrayList arrays = new ArrayList();
        for (int i=0;i<clxxList.size();i++){
            Map map = new HashMap();
            map.put("value",clxxList.get(i).getId());
            map.put("label",clxxList.get(i).getCph());
            arrays.add(map);
        }

        return R.ok().put("clxxList",arrays);
    }


}
