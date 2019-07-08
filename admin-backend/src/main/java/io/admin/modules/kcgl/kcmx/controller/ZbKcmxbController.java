package io.admin.modules.kcgl.kcmx.controller;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import io.admin.modules.borrow.controller.ZplPrinter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.kcgl.kcmx.service.ZbKcmxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;

import javax.print.*;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.event.PrintServiceAttributeListener;

import static io.admin.common.utils.ShiroUtils.getUserEntity;


/**
 * 装备库存明细信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-01 11:13:25
 */
@RestController
@RequestMapping("kcgl/zbkcmxb")
public class ZbKcmxbController {
    @Autowired
    private ZbKcmxbService zbKcmxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("kcgl:zbkcmxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zbKcmxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("kcgl:zbkcmxb:info")
    public R info(@PathVariable("id") Long id){
			ZbKcmxbEntity zbKcmxb = zbKcmxbService.selectById(id);

        return R.ok().put("zbkcmxb", zbKcmxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("kcgl:zbkcmxb:save")
    public R save(@RequestBody ZbKcmxbEntity zbKcmxb){
			zbKcmxbService.insert(zbKcmxb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("kcgl:zbkcmxb:update")
    public R update(@RequestBody ZbKcmxbEntity zbKcmxb){
        zbKcmxbService.updateById(zbKcmxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("kcgl:zbkcmxb:delete")
    public R delete(@RequestBody Long[] ids){
			zbKcmxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/select")
    public R selectList(@RequestParam String zbid){
        List<ZbKcmxbEntity> kcmxList= zbKcmxbService.selectList(zbid,1,getUserEntity().getUnit());
        ArrayList arrays = new ArrayList();
        for (int i=0;i<kcmxList.size();i++){
            Map map = new HashMap();
            map.put("value",kcmxList.get(i).getZbbm());
            map.put("label",kcmxList.get(i).getZbbm()+ kcmxList.get(i).getZbmc());
            arrays.add(map);
        }

        return R.ok().put("zbbmList",arrays);
    }
    @RequestMapping("/print")
    public R  printLabel(@RequestBody Long[] ids ) {
        ZplPrinter p = new ZplPrinter("ZDesigner GT800-300dpi EPL");
      for(int i=0;i<ids.length;i++){
          ZbKcmxbEntity zbKcmxb = zbKcmxbService.selectById( ids[i] );
          p.resetZpl();

          printBarcode(p,zbKcmxb.getZbbm(),zbKcmxb.getSsbmmc(),zbKcmxb.getZbmc());
      }
        return R.ok();
    }

    private static boolean printBarcode (ZplPrinter p,String zbbm,String ssbmmc,String zbmc){
        //1.打印单个条码
        String bar0 = zbbm;//条码内容
        //String bar0Zpl = "^FO380,600^BY3,3.0,100^BCN,,Y,N,N^FD${data}^FS";//条码样式模板
        // String bar0Zpl = "^FO66,133^BY5,3.0,160^BCR,,N,N,N^FD${data}^FS";//条码样式模板
        //    String bar0Zpl = "^FO110,110^BY6,3.0,280^BCN,,Y,N,N^FD${data}^FS";//条码样式模板
        String bar0Zpl = "^FO320,100^AAN,200,210^BY4,3.0,180^BCN,,N,N,N^FD${data}^FS";//条码样式模板
        p.setBarcode(bar0, bar0Zpl);
        p.setChar(bar0,450,320,50,50);
        p.setText(ssbmmc+" "+ zbmc, 350, 380, 30, 30, 20, 2, 2, 24);
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }



}
