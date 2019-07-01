package io.admin.modules.dic.ryxx.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.dic.ryxx.dao.ZdRyxxbDao;
import io.admin.modules.dic.ryxx.entity.ZdRyxxbEntity;
import io.admin.modules.dic.ryxx.service.ZdRyxxbService;


@Service("zdRyxxbService")
public class ZdRyxxbServiceImpl extends ServiceImpl<ZdRyxxbDao, ZdRyxxbEntity> implements ZdRyxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String ssbmmc=(String)params.get("ssbmmc");
        String ryxm=(String)params.get("ryxm");
        Page<ZdRyxxbEntity> page = this.selectPage(
                new Query<ZdRyxxbEntity>(params).getPage(),
                new EntityWrapper<ZdRyxxbEntity>().
                        like(StringUtils.isNotBlank(ssbmmc), "ssbmmc", ssbmmc).
                        like(StringUtils.isNotBlank(ryxm), "ryxm", ryxm)
        );

        return new PageUtils(page);
    }

    @Override
    public List<ZdRyxxbEntity> selectByssbmid(String ssbmid){
        List<ZdRyxxbEntity> ryxxList = baseMapper.selectList(new EntityWrapper<ZdRyxxbEntity>().
                like(StringUtils.isNotBlank(ssbmid), "ssbmid", ssbmid));

        return ryxxList;
    }

}
