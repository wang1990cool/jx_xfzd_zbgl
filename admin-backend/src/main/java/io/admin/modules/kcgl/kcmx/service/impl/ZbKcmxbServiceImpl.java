package io.admin.modules.kcgl.kcmx.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.kcgl.kcmx.dao.ZbKcmxbDao;
import io.admin.modules.kcgl.kcmx.entity.ZbKcmxbEntity;
import io.admin.modules.kcgl.kcmx.service.ZbKcmxbService;


@Service("zbKcmxbService")
public class ZbKcmxbServiceImpl extends ServiceImpl<ZbKcmxbDao, ZbKcmxbEntity> implements ZbKcmxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String zt=(String)params.get("zt");
        String ztxs=(String)params.get("ztxs");
        String ssbmmc=(String)params.get("ssbmmc");
        String zbmc=(String)params.get("zbmc");
        String xssbmmc=(String)params.get("xssbmmc");
        String rybh=(String)params.get("rybh");
        String ryxm=(String)params.get("ryxm");
        String cph=(String)params.get("cph");
        Page<ZbKcmxbEntity> page = this.selectPage(
                new Query<ZbKcmxbEntity>(params).getPage(),
                new EntityWrapper<ZbKcmxbEntity>().
                        like(StringUtils.isNotBlank(ssbmmc), "ssbmmc", ssbmmc).
                        like(StringUtils.isNotBlank(zbmc), "zblbmc", zbmc).
                        like(StringUtils.isNotBlank(xssbmmc), "xssbmmc", xssbmmc).
                        like(StringUtils.isNotBlank(ztxs), "ztxs", ztxs).
                        like(StringUtils.isNotBlank(zt), "zt", zt).
                        like(StringUtils.isNotBlank(rybh), "zt", rybh).
                        like(StringUtils.isNotBlank(ryxm), "zt", ryxm).
                        like(StringUtils.isNotBlank(cph), "zt", cph)

        );

        return new PageUtils(page);
    }

    @Override
    public String getXlh(String zblbid){
        List<ZbKcmxbEntity> list=baseMapper.selectList(new EntityWrapper<ZbKcmxbEntity>().
                like(StringUtils.isNotBlank(zblbid), "zbid", zblbid)
        );
        String [] array = new String[list.size()]; //保存已经存在的号
        if(list.size() > 0){
            for(int i = 0;i < list.size(); i++){
                array[i] = list.get(i).getZbbm();
            }
        }
        String SerialNum = createSerialNum(array, 3);// 创建后3位
        String xlh = zblbid + SerialNum;
        return xlh;
    }
    /**
     * 获得下一个流水号
     * @param array
     * @param len
     * @return
     */
    public String createSerialNum(String[] array, int len){
        int maxNum = 1; //初始化最大值为1
        String SerialNum;
        if(array.length > 0){
            for(int i = 0; i < array.length ; i++){
                String num = array[i];
                String previousSerialNum = num.substring(num.length() - len); //获得方案号号后3位
                int newNum = Integer.parseInt(previousSerialNum.replaceAll("^(0+)", ""));// 去掉前面的0
                if(newNum > maxNum){
                    maxNum = newNum;
                }
            }
            SerialNum = String.format("%0" + len + "d", (maxNum + 1)); // 数值前面加0，保存为len位
            return SerialNum;
        }else{
            SerialNum = String.format("%0" + len + "d",  1);
            return  SerialNum;
        }
    }

    @Override
    public List<ZbKcmxbEntity> selectList(String ssbmid, int ztm){
        List<ZbKcmxbEntity> kcxxList = baseMapper.selectList(new EntityWrapper<ZbKcmxbEntity>().
                like(StringUtils.isNotBlank(ssbmid), "ssbmid", ssbmid).
                eq("ztm",ztm));

        return kcxxList;
    }

    @Override
    public List<ZbKcmxbEntity> selectList(String zbid,int ztm,String ssbmmc){
        List<ZbKcmxbEntity> ryxxList = baseMapper.selectList(new EntityWrapper<ZbKcmxbEntity>().
                like(StringUtils.isNotBlank(zbid), "zbid", zbid).
                like(StringUtils.isNotBlank(ssbmmc), "ssbmmc", ssbmmc).
                eq("ztm",ztm));

        return ryxxList;
    }

    public ZbKcmxbEntity selectByzbbm(String zbbm){
        List<ZbKcmxbEntity> ryxxList = baseMapper.selectList(new EntityWrapper<ZbKcmxbEntity>().
                like(StringUtils.isNotBlank(zbbm), "zbbm", zbbm));

        return ryxxList.get(0);
    }


}
