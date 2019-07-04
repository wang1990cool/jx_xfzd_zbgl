package io.admin.modules.borrow.service.impl;

import io.admin.modules.borrow.entity.JyJczbmxbEntity;
import io.admin.modules.borrow.entity.ZbJrsqbEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.borrow.dao.ZbJrsqmxbDao;
import io.admin.modules.borrow.entity.ZbJrsqmxbEntity;
import io.admin.modules.borrow.service.ZbJrsqmxbService;
import org.springframework.transaction.annotation.Transactional;


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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByJrsqIds(String[] jrsqIds) {
        baseMapper.deleteByJrsqIds(jrsqIds);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public  List<ZbJrsqmxbEntity> selectByJrsqid(String jrsqid){
        List<ZbJrsqmxbEntity> list = baseMapper.selectList(
                new EntityWrapper<ZbJrsqmxbEntity>().
                        like("jrsqid", jrsqid)
        );

        return list;
    }

}
