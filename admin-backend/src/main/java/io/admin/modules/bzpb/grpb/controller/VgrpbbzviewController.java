package io.admin.modules.bzpb.grpb.controller;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.modules.bzpb.qcpb.entity.VqcpbbzviewEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.admin.modules.bzpb.grpb.entity.VgrpbbzviewEntity;
import io.admin.modules.bzpb.grpb.service.VgrpbbzviewService;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * VIEW
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-02 22:35:45
 */
@RestController
@RequestMapping("bzpb/vgrpbbzview")
public class VgrpbbzviewController {
    @Autowired
    private VgrpbbzviewService vgrpbbzviewService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bzpb:vgrpbbzview:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = vgrpbbzviewService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{zblbmc}")
    @RequiresPermissions("bzpb:vgrpbbzview:info")
    public R info(@PathVariable("zblbmc") String zblbmc){
			VgrpbbzviewEntity vgrpbbzview = vgrpbbzviewService.selectById(zblbmc);

        return R.ok().put("vgrpbbzview", vgrpbbzview);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bzpb:vgrpbbzview:save")
    public R save(@RequestBody VgrpbbzviewEntity vgrpbbzview){
			vgrpbbzviewService.insert(vgrpbbzview);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bzpb:vgrpbbzview:update")
    public R update(@RequestBody VgrpbbzviewEntity vgrpbbzview){
			vgrpbbzviewService.updateById(vgrpbbzview);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bzpb:vgrpbbzview:delete")
    public R delete(@RequestBody String[] zblbmcs){
			vgrpbbzviewService.deleteBatchIds(Arrays.asList(zblbmcs));

        return R.ok();
    }

    @RequestMapping(value="/export",method = RequestMethod.POST)
    @RequiresPermissions("bzpb:vgrpbbzview:export")
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
        String[] headers = {"序号", "部门名称","装备类别", "装备名称","标准配备", "标准配备比","消防员人数","标准配备要求数量","当前实力","缺少数量"};
        //headers表示excel表中第一行的表头
        XSSFRow row = sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            row.createCell(i).setCellValue(headers[i]);
        }
        List<VgrpbbzviewEntity> qcppxxList=vgrpbbzviewService.selectList(new EntityWrapper<VgrpbbzviewEntity>().
                like(StringUtils.isNotBlank(ssbmmc), "bmmc", ssbmmc)
        );

        //在表中存放查询到的数据放入对应的列
        for (VgrpbbzviewEntity vqcpbbzviewEntity : qcppxxList) {
            XSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(rowNum);
            row1.createCell(1).setCellValue(vqcpbbzviewEntity.getBmmc());
            row1.createCell(2).setCellValue(vqcpbbzviewEntity.getZblbmc());
            row1.createCell(3).setCellValue(vqcpbbzviewEntity.getZbmc());
            row1.createCell(4).setCellValue(vqcpbbzviewEntity.getBzpbsl());
            row1.createCell(5).setCellValue(vqcpbbzviewEntity.getBzbfb());
            row1.createCell(6).setCellValue(vqcpbbzviewEntity.getRysl());
            row1.createCell(7).setCellValue(vqcpbbzviewEntity.getBzsl());
            row1.createCell(8).setCellValue(vqcpbbzviewEntity.getZbsl());
            row1.createCell(9).setCellValue(vqcpbbzviewEntity.getBzsl()-vqcpbbzviewEntity.getZbsl());

            rowNum++;
        }
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}
