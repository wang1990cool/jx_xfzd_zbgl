package io.admin.modules.kcgl.kcxx.controller;

import java.io.InputStream;
import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.common.enums.KcztEnum;
import io.admin.modules.dic.zbmc.entity.ZdZbmcxxbEntity;
import io.admin.modules.dic.zbmc.service.ZdZbmcxxbService;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.kcgl.kcmx.service.ZbKcmxbService;
import io.admin.modules.kcgl.kcxx.entity.ZbKcxxbVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.admin.modules.kcgl.kcxx.entity.ZbKcxxbEntity;
import io.admin.modules.kcgl.kcxx.service.ZbKcxxbService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 库存信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-01 11:17:05
 */
@RestController
@RequestMapping("kcgl/zbkcxxb")
public class ZbKcxxbController {
    @Autowired
    private ZbKcxxbService zbKcxxbService;
    @Autowired
    private ZbKcmxbService zbKcmxbService;
    @Autowired
    private ZdZbmcxxbService zdZbmcxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("kcgl:zbkcxxb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zbKcxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("kcgl:zbkcxxb:info")
    public R info(@PathVariable("id") Integer id){
			ZbKcxxbEntity zbKcxxb = zbKcxxbService.selectById(id);

        return R.ok().put("zbKcxxb", zbKcxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("kcgl:zbkcxxb:save")
    public R save(@RequestBody ZbKcxxbVo zbKcxxb){
            int rksl=zbKcxxb.getZbsl();
            for(int i=0 ;i<rksl;i++){
                ZbKcmxbEntity zbKcmxb=new ZbKcmxbEntity();
                zbKcmxb.setZbbm(zbKcmxbService.getXlh(zbKcxxb.getZbid()));
                zbKcmxb.setZbid(zbKcxxb.getZbid());
                zbKcmxb.setZbmc(zbKcxxb.getZbmc());
                zbKcmxb.setSsbmid(zbKcxxb.getSsbmid());
                zbKcmxb.setSsbmmc(zbKcxxb.getSsbmmc());
                zbKcmxb.setZtm(1);
                zbKcmxb.setZtxs("在库");
                zbKcmxb.setZt(zbKcxxb.getSsbmmc());
                zbKcmxb.setXssbmid(zbKcxxb.getSsbmid());
                zbKcmxb.setXssbmmc(zbKcxxb.getSsbmmc());
                zbKcmxb.setScrq(zbKcxxb.getScrq());
                zbKcmxb.setCreateTime(new Date());
                zbKcmxbService.insert(zbKcmxb);
            }
/*
            ZbKcxxbEntity zbKcxx=zbKcxxbService.selectByzbid(zbKcxxb.getZbid(),zbKcxxb.getSsbmmc());
            if(zbKcxx==null){
                zbKcxxbService.insert(zbKcxxb);
            }else{
                zbKcxx.setZbsl(zbKcxx.getZbsl()+zbKcxxb.getZbsl());
                zbKcxxbService.updateById(zbKcxx);
            }
*/
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("kcgl:zbkcxxb:update")
    public R update(@RequestBody ZbKcxxbEntity zbKcxxb){
			zbKcxxbService.updateById(zbKcxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("kcgl:zbkcxxb:delete")
    public R delete(@RequestBody Integer[] ids){
			zbKcxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    @RequestMapping(value = "/import")
    public R upload(@RequestParam MultipartFile file) {
        String fileName = file.getOriginalFilename();
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }

        Workbook wb = null;
        Sheet sheet = null;

        int errorNum = 0;
        try {
            InputStream is = file.getInputStream();
//        if (isExcel2003) {
//            wb = new HSSFWorkbook(is);
//        } else {
            wb = new XSSFWorkbook(is);
//        }
            sheet = wb.getSheetAt(0);

            Map map = new HashMap();

            List<String> list = new ArrayList<>();
            for (int i = 6; i < sheet.getLastRowNum() + 1; i++) {
                Row row = sheet.getRow(i);

                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                String zbmc = row.getCell(1).getStringCellValue();

                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                String index = row.getCell(0).getStringCellValue();

                map.put("zbmc", zbmc);
                List<ZdZbmcxxbEntity> zdZbmcxxList = zdZbmcxxbService.selectByMap(map);

                if (zdZbmcxxList.size() == 0 ) {
                    list.add(index + ": " +zbmc);
                    continue;
                } else {
                    ZdZbmcxxbEntity zbmcxxbEntity = zdZbmcxxList.get(0);
                    List<ZbKcxxbEntity> zbKcxxbEntityList = zbKcxxbService.selectByMap(map);
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    int rksl = Integer.parseInt(row.getCell(2).getStringCellValue());

                    if (zbKcxxbEntityList.size() > 0 ) {
                        Map map1 = new HashMap();
                        map1.put("zbmc", zbmc);
                        map1.put("ztxs", "在库");

                        List<ZbKcmxbEntity> zbKcmxbEntityList = zbKcmxbService.selectByMap(map1);

                        if (zbKcmxbEntityList.size() == 0) {
                            for(int j = 0 ; j < rksl; j++){
                                ZbKcmxbEntity zbKcmxb = new ZbKcmxbEntity();
                                zbKcmxb.setZbbm(zbKcmxbService.getXlh(zbmcxxbEntity.getZbid()));
                                zbKcmxb.setZbid(zbmcxxbEntity.getZbid());
                                zbKcmxb.setZbmc(zbmc);
                                zbKcmxb.setSsbmid(1l);
                                zbKcmxb.setSsbmmc("雨山中队");
                                zbKcmxb.setZtm(KcztEnum.ZC.getCode());
                                zbKcmxb.setZtxs("在车");
                                zbKcmxb.setZt("皖EX5547");
                                zbKcmxb.setXssbmid(1l);
                                zbKcmxb.setXssbmmc("雨山中队");
                                zbKcmxb.setScrq("");
                                zbKcmxb.setCreateTime(new Date());
                                zbKcmxbService.insert(zbKcmxb);
                            }
                        } else {
                            for (ZbKcmxbEntity mx : zbKcmxbEntityList) {
                                mx.setZtm(KcztEnum.ZC.getCode());
                                mx.setZtxs("在车");
                                mx.setZt("皖EX5547");
                                zbKcmxbService.updateById(mx);
                            }
                        }
                    } else {
                        ZbKcxxbEntity kcxxbEntity = new ZbKcxxbEntity();
                        kcxxbEntity.setZbid(zbmcxxbEntity.getZbid());
                        kcxxbEntity.setZbmc(zbmc);
                        kcxxbEntity.setZbsl(rksl);
                        kcxxbEntity.setSsbmid(1l);
                        kcxxbEntity.setSsbmmc("雨山中队");
                        zbKcxxbService.insert(kcxxbEntity);

                        for(int j = 0 ; j < rksl; j++){
                            ZbKcmxbEntity zbKcmxb = new ZbKcmxbEntity();
                            zbKcmxb.setZbbm(zbKcmxbService.getXlh(zbmcxxbEntity.getZbid()));
                            zbKcmxb.setZbid(zbmcxxbEntity.getZbid());
                            zbKcmxb.setZbmc(zbmc);
                            zbKcmxb.setSsbmid(1l);
                            zbKcmxb.setSsbmmc("雨山中队");
                            zbKcmxb.setZtm(KcztEnum.ZC.getCode());
                            zbKcmxb.setZtxs("在车");
                            zbKcmxb.setZt("皖EX5547");
                            zbKcmxb.setXssbmid(1l);
                            zbKcmxb.setXssbmmc("雨山中队");
                            zbKcmxb.setScrq("");
                            zbKcmxb.setCreateTime(new Date());
                            zbKcmxbService.insert(zbKcmxb);
                        }
                    }

                }
                for (String kc : list){
                    System.out.println(kc);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return R.ok();
    }
}
