package io.admin.modules.dic.xfzlb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.dic.xfzlb.dao.ZdXfzlbxxbDao;
import io.admin.modules.dic.xfzlb.entity.ZdXfzlbxxbEntity;
import io.admin.modules.dic.xfzlb.service.ZdXfzlbxxbService;


@Service("zdXfzlbxxbService")
public class ZdXfzlbxxbServiceImpl extends ServiceImpl<ZdXfzlbxxbDao, ZdXfzlbxxbEntity> implements ZdXfzlbxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdXfzlbxxbEntity> page = this.selectPage(
                new Query<ZdXfzlbxxbEntity>(params).getPage(),
                new EntityWrapper<ZdXfzlbxxbEntity>()
        );

        return new PageUtils(page);
    }

}
