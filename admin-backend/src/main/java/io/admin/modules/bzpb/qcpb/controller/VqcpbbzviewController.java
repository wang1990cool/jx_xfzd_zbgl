package io.admin.modules.bzpb.qcpb.controller;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.admin.modules.bzpb.qcpb.entity.VqcpbbzviewEntity;
import io.admin.modules.bzpb.qcpb.service.VqcpbbzviewService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * VIEW
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 14:39:01
 */
@RestController
@RequestMapping("bzpb/vqcpbbzview")
public class VqcpbbzviewController {
    @Autowired
    private VqcpbbzviewService vqcpbbzviewService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bzpb:vqcpbbzview:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = vqcpbbzviewService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{zblbmc}")
    @RequiresPermissions("bzpb:vqcpbbzview:info")
    public R info(@PathVariable("zblbmc") String zblbmc){
			VqcpbbzviewEntity vqcpbbzview = vqcpbbzviewService.selectById(zblbmc);

        return R.ok().put("vqcpbbzview", vqcpbbzview);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bzpb:vqcpbbzview:save")
    public R save(@RequestBody VqcpbbzviewEntity vqcpbbzview){
			vqcpbbzviewService.insert(vqcpbbzview);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bzpb:vqcpbbzview:update")
    public R update(@RequestBody VqcpbbzviewEntity vqcpbbzview){
			vqcpbbzviewService.updateById(vqcpbbzview);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bzpb:vqcpbbzview:delete")
    public R delete(@RequestBody String[] zblbmcs){
			vqcpbbzviewService.deleteBatchIds(Arrays.asList(zblbmcs));

        return R.ok();
    }

    @RequestMapping(value="/export",method = RequestMethod.POST)
    @RequiresPermissions("bzpb:vqcpbbzview:export")
    public void export(@RequestParam Map<String, Object> params,HttpServletResponse response) throws Exception {
        params.remove("t");
        String ssbmmc=(String)params.get("ssbmmc");
        //xlsx 2007以上版本
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode( "学生考试报名信息表.xlsx","UTF-8"));
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("匹配信息");
        int rowNum = 1;
        String[] headers = {"序号", "部门名称","装备类别", "装备名称","最小标准配备", "备份标准配备","当前库存","缺少数量"};
        //headers表示excel表中第一行的表头
        XSSFRow row = sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            row.createCell(i).setCellValue(headers[i]);
        }
        List<VqcpbbzviewEntity> qcppxxList=vqcpbbzviewService.selectList(new EntityWrapper<VqcpbbzviewEntity>().
                like(StringUtils.isNotBlank(ssbmmc), "bmmc", ssbmmc)
        );

        //在表中存放查询到的数据放入对应的列
        for (VqcpbbzviewEntity vqcpbbzviewEntity : qcppxxList) {
            XSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(rowNum);
            row1.createCell(1).setCellValue(vqcpbbzviewEntity.getBmmc());
            row1.createCell(2).setCellValue(vqcpbbzviewEntity.getZblbmc());
            row1.createCell(3).setCellValue(vqcpbbzviewEntity.getZbmc());
            row1.createCell(4).setCellValue(vqcpbbzviewEntity.getZbslmin());
            row1.createCell(5).setCellValue(vqcpbbzviewEntity.getBfzbsl());
            row1.createCell(6).setCellValue(vqcpbbzviewEntity.getZbsl());
            row1.createCell(7).setCellValue(vqcpbbzviewEntity.getZbslmin()-vqcpbbzviewEntity.getZbsl());

            rowNum++;
        }
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }



}
