package io.admin.modules.sys.controller;


import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.common.utils.ShiroUtils;
import io.admin.modules.borrow.service.JyJczbmxbService;
import io.admin.modules.bzpb.qcpb.entity.VqcpbbzviewEntity;
import io.admin.modules.bzpb.qcpb.service.VqcpbbzviewService;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.kcgl.kcmx.service.ZbKcmxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/dashboard")
public class DashboardController {

    @Autowired
    private ZbKcmxbService zbKcmxbService;

    @Autowired
    private VqcpbbzviewService vqcpbbzviewService;

    @GetMapping("/kcSize")
    public R kcSize(){
       String szbm = ShiroUtils.getUserEntity().getUnit();

       Map map = new HashMap();
       map.put("xssbmmc", szbm);
       map.put("ztxs", "在库");

       // 在库 装备信息
        List<ZbKcmxbEntity> zkZbxxList = zbKcmxbService.selectByMap(map);

        // 在车 装备信息
        map.put("ztxs", "在车");
        List<ZbKcmxbEntity> zcZbxxList = zbKcmxbService.selectByMap(map);

        // 在人 装备信息
        map.put("ztxs", "在人");
        List<ZbKcmxbEntity> zrZbxxList = zbKcmxbService.selectByMap(map);

        List<Integer> kcSize = new ArrayList<>();
        kcSize.add(zkZbxxList.size());
        kcSize.add(zcZbxxList.size());
        kcSize.add(zrZbxxList.size());

        return R.ok().put("kc", kcSize);
    }

    @RequestMapping("/zblist")
    public R zbList(@RequestParam Map<String, Object> params) {
        // 异常状态信息
        String yc = (String) params.get("yc");
        String[] yczt = null;
        if (yc != null) {
           yczt = yc.split(",");
        }

        String zbmc = (String) params.get("zbmc");

        List<VqcpbbzviewEntity> list =  vqcpbbzviewService.selectByZbmcAndYczt(zbmc, yczt);

        return R.ok().put("data", list);
    }

}
