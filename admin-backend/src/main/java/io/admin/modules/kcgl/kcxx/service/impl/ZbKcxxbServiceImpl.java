package io.admin.modules.kcgl.kcxx.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.kcgl.kcxx.dao.ZbKcxxbDao;
import io.admin.modules.kcgl.kcxx.entity.ZbKcxxbEntity;
import io.admin.modules.kcgl.kcxx.service.ZbKcxxbService;


@Service("zbKcxxbService")
public class ZbKcxxbServiceImpl extends ServiceImpl<ZbKcxxbDao, ZbKcxxbEntity> implements ZbKcxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZbKcxxbEntity> page = this.selectPage(
                new Query<ZbKcxxbEntity>(params).getPage(),
                new EntityWrapper<ZbKcxxbEntity>()
        );

        return new PageUtils(page);
    }

    public ZbKcxxbEntity selectByzbid(String zbid,String ssbmmc){
        List<ZbKcxxbEntity> zbList=baseMapper.selectList(new EntityWrapper<ZbKcxxbEntity>().
                like(StringUtils.isNotBlank(zbid), "zbid", zbid).
//                like(StringUtils.isNotBlank(ssbmid), "ssbmid", ssbmid).
                like(StringUtils.isNotBlank(ssbmmc), "ssbmmc", ssbmmc));
        if(zbList.size()>0){
            return zbList.get(0);
        }else{
            return null;
        }
    }

}
