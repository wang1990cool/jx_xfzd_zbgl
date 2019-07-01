package io.admin.modules.dic.zblb.controller;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.modules.app.entity.UserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.dic.zblb.entity.ZdZblbxxbEntity;
import io.admin.modules.dic.zblb.service.ZdZblbxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;

import static io.admin.common.utils.ShiroUtils.getUserEntity;


/**
 * 装备类别信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-06-29 08:05:52
 */
@RestController
@RequestMapping("dic/zdzblbxxb")
public class ZdZblbxxbController {
    @Autowired
    private ZdZblbxxbService zdZblbxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("dic:zdzblbxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zdZblbxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("dic:zdzblbxxb:info")
    public R info(@PathVariable("id") Long id){
			ZdZblbxxbEntity zdZblbxxb = zdZblbxxbService.selectById(id);

        return R.ok().put("zdzblbxxb", zdZblbxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dic:zdzblbxxb:save")
    public R save(@RequestBody ZdZblbxxbEntity zdZblbxxb){
        zdZblbxxb.setCreateUserId(getUserEntity().getUserId());
        zdZblbxxb.setCreateTime(new Date());
        zdZblbxxb.setLbid(zdZblbxxbService.getXlh());
        zdZblbxxbService.insert(zdZblbxxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dic:zdzblbxxb:update")
    public R update(@RequestBody ZdZblbxxbEntity zdZblbxxb){
        zdZblbxxb.setCreateTime(new Date());
			zdZblbxxbService.updateById(zdZblbxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dic:zdzblbxxb:delete")
    public R delete(@RequestBody Long[] ids){
			zdZblbxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/select")
    public R selectList(){
        List<ZdZblbxxbEntity> zblbList= zdZblbxxbService.selectList(new EntityWrapper<ZdZblbxxbEntity>());
        ArrayList arrays = new ArrayList();
        for (int i=0;i<zblbList.size();i++){
            Map map = new HashMap();
            map.put("value",zblbList.get(i).getLbid());
            map.put("label",zblbList.get(i).getLbmc());
            arrays.add(map);
        }
        return R.ok().put("zblbList",arrays);
    }

}
