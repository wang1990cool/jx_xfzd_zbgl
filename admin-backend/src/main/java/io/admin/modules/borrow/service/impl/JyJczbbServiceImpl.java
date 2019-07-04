package io.admin.modules.borrow.service.impl;

import io.admin.modules.borrow.entity.ZbJrsqmxbEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.borrow.dao.JyJczbbDao;
import io.admin.modules.borrow.entity.JyJczbbEntity;
import io.admin.modules.borrow.service.JyJczbbService;
import org.springframework.transaction.annotation.Transactional;


@Service("jyJczbbService")
public class JyJczbbServiceImpl extends ServiceImpl<JyJczbbDao, JyJczbbEntity> implements JyJczbbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JyJczbbEntity> page = this.selectPage(
                new Query<JyJczbbEntity>(params).getPage(),
                new EntityWrapper<JyJczbbEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<JyJczbbEntity> selectByJrsqid(String jrsqid){
        List<JyJczbbEntity> list = baseMapper.selectList(
                new EntityWrapper<JyJczbbEntity>().
                        like("jrsqid", jrsqid)
        );

        return list;
    }
}
