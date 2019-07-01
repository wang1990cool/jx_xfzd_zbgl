package io.admin.modules.dic.xfzzbpb.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.dic.xfzzbpb.entity.ZdXfzbzpbxxbEntity;
import io.admin.modules.dic.xfzzbpb.service.ZdXfzbzpbxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;



/**
 * 消防站标准配备信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-29 11:10:15
 */
@RestController
@RequestMapping("dic/zdxfzbzpbxxb")
public class ZdXfzbzpbxxbController {
    @Autowired
    private ZdXfzbzpbxxbService zdXfzbzpbxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dic:zdxfzbzpbxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdXfzbzpbxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("dic:zdxfzbzpbxxb:info")
    public R info(@PathVariable("id") Long id){
			ZdXfzbzpbxxbEntity zdXfzbzpbxxb = zdXfzbzpbxxbService.selectById(id);

        return R.ok().put("zdxfzbzpbxxb", zdXfzbzpbxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dic:zdxfzbzpbxxb:save")
    public R save(@RequestBody ZdXfzbzpbxxbEntity zdXfzbzpbxxb){
			zdXfzbzpbxxbService.insert(zdXfzbzpbxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dic:zdxfzbzpbxxb:update")
    public R update(@RequestBody ZdXfzbzpbxxbEntity zdXfzbzpbxxb){
			zdXfzbzpbxxbService.updateById(zdXfzbzpbxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dic:zdxfzbzpbxxb:delete")
    public R delete(@RequestBody Long[] ids){
			zdXfzbzpbxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

/*
    @RequestMapping("/select")
    public R selectList(@RequestParam String hybm){
        List<ZdXfzbzpbxxbEntity> grlxList= zdXfzbzpbxxbService.selectByHybm(hybm);
        ArrayList arrays = new ArrayList();
        for (int i=0;i<grlxList.size();i++){
            Map map = new HashMap();
            map.put("value",grlxList.get(i).getGrlxm());
            map.put("label",grlxList.get(i).getGrlx());
            arrays.add(map);
        }

        return R.ok().put("grlxList",arrays);
    }
*/

}
