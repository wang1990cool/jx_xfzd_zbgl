package io.admin.modules.bzpb.grpb.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.bzpb.grpb.dao.VgrpbbzviewDao;
import io.admin.modules.bzpb.grpb.entity.VgrpbbzviewEntity;
import io.admin.modules.bzpb.grpb.service.VgrpbbzviewService;


@Service("vgrpbbzviewService")
public class VgrpbbzviewServiceImpl extends ServiceImpl<VgrpbbzviewDao, VgrpbbzviewEntity> implements VgrpbbzviewService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String ssbmmc=(String)params.get("ssbmmc");
        Page<VgrpbbzviewEntity> page = this.selectPage(
                new Query<VgrpbbzviewEntity>(params).getPage(),
                new EntityWrapper<VgrpbbzviewEntity>().
                        like(StringUtils.isNotBlank(ssbmmc), "bmmc", ssbmmc)
        );

        return new PageUtils(page);
    }

}
