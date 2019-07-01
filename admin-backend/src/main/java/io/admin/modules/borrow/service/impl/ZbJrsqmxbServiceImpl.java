package io.admin.modules.borrow.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.borrow.dao.ZbJrsqmxbDao;
import io.admin.modules.borrow.entity.ZbJrsqmxbEntity;
import io.admin.modules.borrow.service.ZbJrsqmxbService;


@Service("zbJrsqmxbService")
public class ZbJrsqmxbServiceImpl extends ServiceImpl<ZbJrsqmxbDao, ZbJrsqmxbEntity> implements ZbJrsqmxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZbJrsqmxbEntity> page = this.selectPage(
                new Query<ZbJrsqmxbEntity>(params).getPage(),
                new EntityWrapper<ZbJrsqmxbEntity>()
        );

        return new PageUtils(page);
    }

}
