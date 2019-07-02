package io.admin.modules.bzpb.qcpb.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.bzpb.qcpb.dao.VqcpbbzviewDao;
import io.admin.modules.bzpb.qcpb.entity.VqcpbbzviewEntity;
import io.admin.modules.bzpb.qcpb.service.VqcpbbzviewService;


@Service("vqcpbbzviewService")
public class VqcpbbzviewServiceImpl extends ServiceImpl<VqcpbbzviewDao, VqcpbbzviewEntity> implements VqcpbbzviewService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String ssbmmc=(String)params.get("ssbmmc");
        Page<VqcpbbzviewEntity> page = this.selectPage(
                new Query<VqcpbbzviewEntity>(params).getPage(),
                new EntityWrapper<VqcpbbzviewEntity>().
                        like(StringUtils.isNotBlank(ssbmmc), "bmmc", ssbmmc)
        );

        return new PageUtils(page);
    }

}
