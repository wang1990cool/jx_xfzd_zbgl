package io.admin.modules.bzpb.clpb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.bzpb.clpb.dao.VclpbviewDao;
import io.admin.modules.bzpb.clpb.entity.VclpbviewEntity;
import io.admin.modules.bzpb.clpb.service.VclpbviewService;


@Service("vclpbviewService")
public class VclpbviewServiceImpl extends ServiceImpl<VclpbviewDao, VclpbviewEntity> implements VclpbviewService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<VclpbviewEntity> page = this.selectPage(
                new Query<VclpbviewEntity>(params).getPage(),
                new EntityWrapper<VclpbviewEntity>()
        );

        return new PageUtils(page);
    }

}
