package io.admin.modules.borrow.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.borrow.dao.ZbJrsqbDao;
import io.admin.modules.borrow.entity.ZbJrsqbEntity;
import io.admin.modules.borrow.service.ZbJrsqbService;


@Service("zbJrsqbService")
public class ZbJrsqbServiceImpl extends ServiceImpl<ZbJrsqbDao, ZbJrsqbEntity> implements ZbJrsqbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZbJrsqbEntity> page = this.selectPage(
                new Query<ZbJrsqbEntity>(params).getPage(),
                new EntityWrapper<ZbJrsqbEntity>()
        );

        return new PageUtils(page);
    }

}
