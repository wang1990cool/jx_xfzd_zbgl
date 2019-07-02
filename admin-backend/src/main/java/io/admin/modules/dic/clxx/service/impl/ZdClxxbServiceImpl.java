package io.admin.modules.dic.clxx.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.dic.clxx.dao.ZdClxxbDao;
import io.admin.modules.dic.clxx.entity.ZdClxxbEntity;
import io.admin.modules.dic.clxx.service.ZdClxxbService;


@Service("zdClxxbService")
public class ZdClxxbServiceImpl extends ServiceImpl<ZdClxxbDao, ZdClxxbEntity> implements ZdClxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String ssbmmc=(String)params.get("ssbmmc");
        String cph=(String)params.get("cph");
        Page<ZdClxxbEntity> page = this.selectPage(
                new Query<ZdClxxbEntity>(params).getPage(),
                new EntityWrapper<ZdClxxbEntity>().
                        like(StringUtils.isNotBlank(ssbmmc), "ssbmmc", ssbmmc).
                        like(StringUtils.isNotBlank(cph), "cph", cph)
        );

        return new PageUtils(page);
    }
    @Override
    public List<ZdClxxbEntity> selectByssbmmc(String ssbmmc){
        List<ZdClxxbEntity> clxxList = baseMapper.selectList(new EntityWrapper<ZdClxxbEntity>().
                like(StringUtils.isNotBlank(ssbmmc), "ssbmmc", ssbmmc));

        return clxxList;
    }

}
