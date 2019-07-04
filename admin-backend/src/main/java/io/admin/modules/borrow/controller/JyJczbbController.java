package io.admin.modules.borrow.controller;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.common.utils.ShiroUtils;
import io.admin.modules.borrow.entity.JyJczbmxbEntity;
import io.admin.modules.borrow.entity.ZbJrsqbEntity;
import io.admin.modules.borrow.entity.ZbJrsqmxbEntity;
import io.admin.modules.borrow.service.JyJczbmxbService;
import io.admin.modules.borrow.service.ZbJrsqbService;
import io.admin.modules.borrow.service.ZbJrsqmxbService;
import io.admin.modules.borrow.vo.JyJczbVo;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.kcgl.kcmx.service.ZbKcmxbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.borrow.entity.JyJczbbEntity;
import io.admin.modules.borrow.service.JyJczbbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;

/**
 *
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-07-03 09:03:51
 */

@RestController
@RequestMapping("borrow/jyjczb")
public class JyJczbbController {
    @Autowired
    private JyJczbbService jyJczbbService;

    @Autowired
    private JyJczbmxbService jyJczbmxService;

    @Autowired
    private ZbKcmxbService zbKcmxbService;

    @Autowired
    private ZbJrsqmxbService zbJrsqmxbService;

    @Autowired
    private ZbJrsqbService jrsqbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("borrow:jyjczb:list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("jcbmmc", ShiroUtils.getUserEntity().getUnit());
        PageUtils page = jrsqbService.queryPageByZtm(params, new String[]{"3","4"});
        return R.ok().put("page", page);
    }

    /**
     * 借出装备列表
     */
    @RequestMapping("/zblist")
    public R zblist(@RequestParam Map<String, Object> params){
        List<JyJczbVo> jyJczbVoList = getJyJczbVo(params);
        return R.ok().put("data", jyJczbVoList);
    }

    /**
     * 借出装备列表
     */
    @RequestMapping("/reveice")
    public R reveice(@RequestBody List<JyJczbbEntity> list){
        if (list.get(0).getId() == null) {
            for (JyJczbbEntity jczb : list) {
                jczb.setCreateTime(new Date());
                jczb.setCreateUserId(ShiroUtils.getUserEntity().getUsername());
                this.addJczbmx(jczb);
            }
            jyJczbbService.insertBatch(list);
        } else {
            for (JyJczbbEntity jczb : list) {
                this.addJczbmx(jczb);
            }
            jyJczbbService.updateBatchById(list);
        }
        return R.ok();
    }


    public void addJczbmx(JyJczbbEntity jczb) {
        List<ZbKcmxbEntity> kcmxList = this.getKcmx(jczb);

        int count = 0;
        int sjsl = jczb.getSjsl();
        Map map = new HashMap();
        map.put("jrsqid", jczb.getJrsqid());
        map.put("zbid", jczb.getZbid());

        // 查看当前借出装备明细中是否存在与借出单和装备ID相同的装备
        List<JyJczbmxbEntity> jczbmxbist = jyJczbmxService.selectByMap(map);
        if (jczbmxbist.size() > 0) {
            count = 0;
            // 若修改的实际库存大于现在的实际库存
            if (sjsl > jczbmxbist.size()) {
                for (ZbKcmxbEntity kcmx : kcmxList) {
                    this.insertJczbmx(kcmx, jczb);
                    this.updateKcmx(kcmx,4,"借出", jczb.getSqbmid(),jczb.getSqbmmc());
                    zbKcmxbService.updateById(kcmx);
                    if (++count == jczb.getSjsl() - jczbmxbist.size()){
                        break;
                    }
                }
            } else  if (sjsl < jczbmxbist.size()){
                count = 0;
                // 若修改的实际库存大于现在的实际库存
                for (JyJczbmxbEntity jczbmx : jczbmxbist){
                    String zbbm = jczbmx.getZbbm();
                    Map delMap = new HashMap();
                    delMap.put("zbbm", zbbm);

                    jyJczbmxService.deleteByMap(delMap);
                    ZbKcmxbEntity kcmx = zbKcmxbService.selectByzbbm(zbbm);
                    this.updateKcmx(kcmx,1,"在库", kcmx.getSsbmid(),kcmx.getSsbmmc());
                    if (jczbmxbist.size() - (++count) == sjsl){
                        break;
                    }
                }
            }
        }else {
            // 没用领用的状态
            count = 0;
            for (ZbKcmxbEntity kcmx : kcmxList) {
                this.insertJczbmx(kcmx, jczb);
                this.updateKcmx(kcmx,4,"借出", jczb.getSqbmid(),jczb.getSqbmmc());
                if (++count == jczb.getSjsl()){
                    break;
                }
            }
        }

        List<ZbJrsqbEntity> jrsqbEntities  = jrsqbService.selectList(
                new EntityWrapper<ZbJrsqbEntity>().
                        eq("jrsqid", jczb.getJrsqid())
        );
        jrsqbEntities.get(0).setZtm(4);
        jrsqbEntities.get(0).setZt("已领用");
        jrsqbService.updateById(jrsqbEntities.get(0));

    }

    public void insertJczbmx(ZbKcmxbEntity kcmx ,JyJczbbEntity jczb){
        JyJczbmxbEntity jczbmx = new JyJczbmxbEntity();
        jczbmx.setZblbid(jczb.getZblbid());
        jczbmx.setZblbmc(jczb.getZblbmc());
        jczbmx.setZbid(kcmx.getZbid());
        jczbmx.setZbbm(kcmx.getZbbm());
        jczbmx.setZbmc(kcmx.getZbmc());
        jczbmx.setZt("借用");
        jczbmx.setJrsqid(jczb.getJrsqid());
        jyJczbmxService.insert(jczbmx);
    }

    public void updateKcmx(ZbKcmxbEntity kcmx, int ztm, String ztxs, Long xszbmid,String xszbmmc) {
        kcmx.setXssbmid(xszbmid);
        kcmx.setXssbmmc(xszbmmc);
        kcmx.setZtm(ztm);
        kcmx.setZtxs(ztxs);
        zbKcmxbService.updateById(kcmx);
    }



    // 获得
    public List<JyJczbVo> getJyJczbVo(Map<String, Object> params) {
        // 借用单
        ZbJrsqbEntity jrsq = jrsqbService.selectByMap(params).get(0);
        // 借用单明细
        List<ZbJrsqmxbEntity> jrsqmxList = zbJrsqmxbService.selectByMap(params);
        // 领用单
        List<JyJczbbEntity> jyJczbList = jyJczbbService.selectByMap(params);
        // 在库装备明细
        List<List<ZbKcmxbEntity>> kcmxs = this.getKcmxs(jrsqmxList);

        List<JyJczbVo> jyJczbVoList = new ArrayList<>();

        if (jyJczbList.size() > 0){
            jyJczbVoList = JyJczbVo.updateJczbVos(jyJczbList, jrsq,  jrsqmxList, kcmxs);
        }else {
            jyJczbVoList =  JyJczbVo.createJczbVos(jrsq, jrsqmxList, kcmxs);
        }

        return jyJczbVoList;
    }


    /**
     * 根据借出装备 获得在库装备的明细
     * @param jczbbEntity
     * @return
     */
    public List<ZbKcmxbEntity> getKcmx(JyJczbbEntity jczbbEntity ) {
        List<ZbKcmxbEntity> kcmxbList = zbKcmxbService.selectList(
                new EntityWrapper<ZbKcmxbEntity>().
                        eq("zbid", jczbbEntity.getZbid()).
                        eq("ztxs", "在库")
        );
        return kcmxbList;
    }

    /**
     * 根据借用单中的装备ID 获得在库的装备List
     */
    public List<List<ZbKcmxbEntity>> getKcmxs(List<ZbJrsqmxbEntity> jrsqmxbEntityList ){
        List<List<ZbKcmxbEntity>> lists = new ArrayList<>();

        for (ZbJrsqmxbEntity jrsqmxbEntity : jrsqmxbEntityList){
            List<ZbKcmxbEntity> kcmxbEntityList = zbKcmxbService.selectList(
                    new EntityWrapper<ZbKcmxbEntity>().
                            eq("zbid", jrsqmxbEntity.getZbid()).
                            eq("ztxs", "在库")
            );
            lists.add(kcmxbEntityList);
        }
        return lists;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("borrow:jyjczbb:info")
    public R info(@PathVariable("id") Long id){
		JyJczbbEntity jyJczbb = jyJczbbService.selectById(id);

        return R.ok().put("jyJczbb", jyJczbb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("borrow:jyjczbb:save")
    public R save(@RequestBody JyJczbbEntity jyJczbb){
		jyJczbbService.insert(jyJczbb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("borrow:jyjczbb:update")
    public R update(@RequestBody JyJczbbEntity jyJczbb){
		jyJczbbService.updateById(jyJczbb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("borrow:jyjczbb:delete")
    public R delete(@RequestBody Long[] ids){
		jyJczbbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
