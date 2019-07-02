package io.admin.modules.dic.xfzclpb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.dic.xfzclpb.dao.ZdXfzclpbxxbDao;
import io.admin.modules.dic.xfzclpb.entity.ZdXfzclpbxxbEntity;
import io.admin.modules.dic.xfzclpb.service.ZdXfzclpbxxbService;


@Service("zdXfzclpbxxbService")
public class ZdXfzclpbxxbServiceImpl extends ServiceImpl<ZdXfzclpbxxbDao, ZdXfzclpbxxbEntity> implements ZdXfzclpbxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdXfzclpbxxbEntity> page = this.selectPage(
                new Query<ZdXfzclpbxxbEntity>(params).getPage(),
                new EntityWrapper<ZdXfzclpbxxbEntity>()
        );

        return new PageUtils(page);
    }

}
